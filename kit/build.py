import json,subprocess,sys,os,math,pathlib,wave,shutil
import numpy as np
from multiprocessing import Pool
R=pathlib.Path(__file__).parent;W=R/'work';O=R/'out';FPS=30
S=json.load(open(R/'script.json'));LN=S['lines']
def dur(f):
    return float(subprocess.check_output(['ffprobe','-v','error','-show_entries','format=duration','-of','csv=p=0',str(f)]).strip())
def timeline():
    TL=[];t=0.0;have=True
    for L in LN:
        f=R/'voice'/f"{L['id']}.mp3"
        if f.exists(): v=dur(f)
        else: have=False;v=len(L['text'].split())*0.29+0.45
        pad=0.35 if L['id']!='cta' else 0.6
        a=round(t*FPS)/FPS;b=round((t+v+pad)*FPS)/FPS
        TL.append(dict(a=a,b=b,v=a+0.12+v,vd=v));t=b
    return TL,have

def silences(f):
    sr=16000;w=W/'_al.wav'
    subprocess.run(['ffmpeg','-y','-v','error','-i',str(f),'-ar',str(sr),'-ac','1',str(w)],check=True)
    with wave.open(str(w)) as q:x=np.frombuffer(q.readframes(q.getnframes()),dtype=np.int16)/32768.
    h=int(sr*.01);n=len(x)//h;e=np.sqrt((x[:n*h].reshape(n,h)**2).mean(1)+1e-12)
    db=20*np.log10(e/e.max());sp=db>-38
    idx=np.where(sp)[0];st,en=idx[0]*.01,(idx[-1]+1)*.01
    sil=[];i=idx[0]
    while i<idx[-1]:
        if not sp[i]:
            j=i
            while not sp[j]:j+=1
            if (j-i)*.01>=.14:sil.append(((i+j)/2*.01,(j-i)*.01))
            i=j
        else:i+=1
    return st,en,sil
def align(L,f):
    st,en,sil=silences(f)
    phs=[[ (t.split('|')[0],t.split('|')[-1].replace('_',' ')) for t in ph.split(' ')] for ph in L['caps'].split(' / ')]
    wt=lambda sp:max(1,len(sp.split()))+(2 if sp.strip(',.!?:')=='HSK' else 0)
    pw=[sum(wt(sp) for _,sp in ph) for ph in phs];tot=sum(pw)
    bounds=[st];c=0
    for k in range(len(phs)-1):
        c+=pw[k];g=st+(en-st)*c/tot
        cand=[(abs(m-g),m) for m,d in sil if abs(m-g)<0.45 and m>bounds[-1]+.15]
        bounds.append(min(cand)[1] if cand else g)
    bounds.append(en)
    toks=[]
    for k,ph in enumerate(phs):
        a,b=bounds[k],bounds[k+1];ws=[wt(sp) for _,sp in ph];t0=a
        for (d,sp),w in zip(ph,ws):
            dd=(b-a)*w/sum(ws);toks.append(dict(t=d,a=t0,b=t0+dd,ph=k));t0+=dd
    # screen groups: theo cụm, không cắt giữa cụm nếu vừa 26 ký tự
    ln=lambda g:sum(len(t['t'])+1 for t in g)
    for t in toks:
        pass
    nums=[k for k in range(len(phs)) if len(phs[k])==1 and phs[k][0][0].endswith(':')]
    for k in nums:
        for t in toks:
            if t['ph']==k:t['ph']=k+1
    chunks=[]
    for k in range(len(phs)):
        ph=[t for t in toks if t['ph']==k]
        if not ph:continue
        if ln(ph)<=27:chunks.append(ph);continue
        best=None
        for j in range(len(ph)-1):
            if ph[j]['t'][-1] in ',:;' and ln(ph[:j+1])<=27 and ln(ph[j+1:])<=27:
                sc=abs(ln(ph[:j+1])-ln(ph[j+1:]))
                if best is None or sc<best[0]:best=(sc,j)
        if best:chunks+= [ph[:best[1]+1],ph[best[1]+1:]];continue
        cur=[]
        for t in ph:
            if cur and ln(cur)+len(t['t'])+1>27:chunks.append(cur);cur=[]
            cur.append(t)
        if cur:chunks.append(cur)
    G=[]
    for c in chunks:
        if G and G[-1][-1]['t'][-1] not in '.!?' and ln(G[-1])+ln(c)<=27:G[-1]+=c
        else:G.append(c)
    for k in range(1,len(G)):
        while ln(G[k])<13 and len(G[k-1])>3 and G[k-1][-1]['ph']==G[k][0]['ph'] and ln(G[k-1])-len(G[k-1][-1]['t'])-1>=13:
            G[k].insert(0,G[k-1].pop())
    return G
def scene(args):
    i,TL=args
    from playwright.sync_api import sync_playwright
    a,b=TL[i]['a'],TL[i]['b'];n=round((b-a)*FPS);fd=W/f'f{i}';shutil.rmtree(fd,ignore_errors=True);fd.mkdir(parents=True)
    with sync_playwright() as p:
        br=p.chromium.launch(executable_path='/usr/bin/google-chrome',args=['--no-sandbox','--disable-gpu','--allow-file-access-from-files'])
        pg=br.new_page(viewport={'width':1080,'height':1920});pg.goto('file://'+str(R/'page.html'));pg.evaluate('document.fonts.ready')
        pg.evaluate(f'setup({json.dumps(S)},{json.dumps(TL)})')
        sc=float(os.environ.get('SCALE','1'))
        for k in range(n):
            pg.evaluate(f'render({a+k/FPS})');pg.screenshot(path=str(fd/f'{k:05d}.jpg'),type='jpeg',quality=92)
        br.close()
    tmp=W/f's{i}.tmp.mp4'
    subprocess.run(['ffmpeg','-y','-v','error','-framerate',str(FPS),'-i',str(fd/'%05d.jpg'),'-c:v','libx264','-preset','veryfast','-crf','18','-pix_fmt','yuv420p',str(tmp)],check=True)
    os.rename(tmp,W/f's{i}.mp4');shutil.rmtree(fd);return i
def audio(TL,have,total):
    sr=44100;x=np.zeros(int(total*sr)+sr)
    if have:
        for L,t in zip(LN,TL):
            w=W/f"{L['id']}.wav";subprocess.run(['ffmpeg','-y','-v','error','-i',str(R/'voice'/f"{L['id']}.mp3"),'-ar',str(sr),'-ac','1',str(w)],check=True)
            with wave.open(str(w)) as f:d=np.frombuffer(f.readframes(f.getnframes()),dtype=np.int16)/32768
            s=int((t['a']+.12)*sr);x[s:s+len(d)]+=d
        x*=0.85/max(1e-6,np.abs(x).max())
    from scipy.signal import butter,lfilter
    rng=np.random.default_rng(1);sfx=np.zeros_like(x)
    for t in TL:
        n=int(.38*sr);env=np.sin(np.linspace(0,np.pi,n))**2;ns=rng.standard_normal(n)
        bb,aa=butter(2,[700/(sr/2),5000/(sr/2)],'band');y=lfilter(bb,aa,ns)*env;y/=np.abs(y).max()
        s=max(0,int((t['a']-.05)*sr));sfx[s:s+n]+=y[:len(sfx)-s]*0.06
    m=x+sfx;m=np.clip(m,-.98,.98)
    out=W/'mix.wav'
    with wave.open(str(out),'w') as f:f.setnchannels(1);f.setsampwidth(2);f.setframerate(sr);f.writeframes((m*32767).astype(np.int16).tobytes())
    return out
if __name__=='__main__':
    TL,have=timeline();json.dump(TL,open(W/'timing.json','w'),indent=1)
    if have:
        for L,t in zip(LN,TL):
            G=align(L,R/'voice'/f"{L['id']}.mp3")
            for g in G:
                for x in g:x['a']+=t['a']+.12;x['b']+=t['a']+.12
            L['_ch']=G
        json.dump(S,open(W/'script_aligned.json','w'),ensure_ascii=False,indent=1)
    total=TL[-1]['b'];print('voice' if have else 'NO VOICE (ước lượng)','total',total)
    if len(sys.argv)>1 and sys.argv[1]=='frames':
        sys.exit()
    with Pool(2) as p:p.map(scene,[(i,TL) for i in range(len(TL))])
    open(W/'list.txt','w').write(''.join(f"file 's{i}.mp4'\n" for i in range(len(TL))))
    subprocess.run(['ffmpeg','-y','-v','error','-f','concat','-i',str(W/'list.txt'),'-c','copy',str(W/'video.mp4')],check=True)
    m=audio(TL,have,total);fn='final.mp4' if have else 'preview_novoice.mp4'
    subprocess.run(['ffmpeg','-y','-v','error','-i',str(W/'video.mp4'),'-i',str(m),'-map','0:v','-map','1:a','-c:v','copy','-c:a','aac','-b:a','192k','-t',str(total),'-movflags','+faststart',str(O/fn)],check=True)
    print(O/fn)
