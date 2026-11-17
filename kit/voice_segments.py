#!/usr/bin/env python3
"""Tạo giọng theo ĐOẠN (VI / ZH) rồi ghép, KHÔNG gửi phụ đề hiển thị cho MiniMax.
Vào : lesson.json  { "voice": "<voice_id>", "lines":[ {"id":"hook","caps":"phụ đề hiển thị",
        "segs":[{"k":"vi","text":"Xin chào các bạn."},{"k":"zh","text":"你好。"}] } ] }
Ra  : voice/<id>.mp3 (mỗi dòng) + work/segtimes.json (mốc bắt đầu/kết thúc từng đoạn, dùng đặt phụ đề)
TTS : nếu có biến MINIMAX_API_KEY -> gọi API trực tiếp. Nếu không, đọc sẵn mp3 đoạn ở seg/<id>_<n>.mp3
      (tác vụ đám mây tạo qua Composio rồi lưu vào đó). Khóa API không bao giờ ghi vào file/repo.
"""
import json, os, sys, subprocess, pathlib, re, urllib.request
R = pathlib.Path(sys.argv[2] if len(sys.argv) > 2 else '.').resolve()
L = json.load(open(R / (sys.argv[1] if len(sys.argv) > 1 else 'lesson.json')))
SEG, VOI, WORK = R/'seg', R/'voice', R/'work'
for d in (SEG, VOI, WORK): d.mkdir(exist_ok=True)
GAP = dict(vz=0.25, zz=0.40, line=0.50)   # VI<->ZH, giữa các từ ZH, hết câu
SPEED = float(os.environ.get('VOICE_SPEED', '0.97'))
ZH_SPEED = float(os.environ.get('ZH_SPEED', '0.75'))   # từ tiếng Trung đọc chậm hơn
# Tiếng Trung dùng GIỌNG NỮ TIẾNG TRUNG PHỔ THÔNG (Mandarin) có sẵn của MiniMax, KHÔNG dùng giọng clone tiếng Việt.
# Thử lần lượt từng giọng cho tới khi MiniMax chấp nhận; đổi bằng biến ZH_VOICE hoặc "zh_voice" trong lesson.json.
ZH_VOICES = [v for v in [os.environ.get('ZH_VOICE'), L.get('zh_voice')] if v] + [
    'Chinese (Mandarin)_News_Anchor', 'female-yujie', 'female-chengshu', 'Chinese (Mandarin)_Soft_Girl']
ZH_REPEAT = 2                                          # mỗi từ tiếng Trung đọc 2 lần
GAP_REPEAT = 0.55                                      # nghỉ giữa 2 lần đọc
NUM = 'không một hai ba bốn năm sáu bảy tám chín mười'.split()

def clean_vi(t):
    t = t.replace('HSK', 'hát ét ca')
    t = re.sub(r'\d+', lambda m: ' '.join(NUM[int(c)] for c in m.group()), t)
    t = re.sub(r'[/:()+\-–—"“”]', ', ', t)
    t = re.sub(r'[一-鿿]', '', t)
    t = re.sub(r'\s+', ' ', t).strip(' ,')
    if len(t.split()) > 18: print('CẢNH BÁO: đoạn VI >18 từ:', t)
    return t if t[-1:] in '.!?' else t + '.'

ZH_EDGE_VOICE = os.environ.get('ZH_EDGE_VOICE', L.get('zh_edge_voice', 'zh-CN-XiaoxiaoNeural'))   # giọng nữ Mandarin "Xiaoxiao"
ZH_EDGE_RATE = os.environ.get('ZH_EDGE_RATE', L.get('zh_edge_rate', '-30%'))                     # tốc độ đọc -30%
ZH_ENGINE = os.environ.get('ZH_ENGINE', 'edge')   # edge (Xiaoxiao, ưu tiên) | minimax

def tts_edge(text, out):
    """Xiaoxiao qua Edge TTS (pip install edge-tts). Trả True nếu tạo được file có âm thanh."""
    try:
        import asyncio, edge_tts
        async def go():
            await edge_tts.Communicate(text, ZH_EDGE_VOICE, rate=ZH_EDGE_RATE).save(str(out))
        asyncio.run(go())
        ok = out.exists() and out.stat().st_size > 1000
    except Exception as e:
        print(f"{out.name}: Edge TTS lỗi: {e}"); ok = False
    if ok: print(f"{out.name}: dùng giọng {ZH_EDGE_VOICE} rate {ZH_EDGE_RATE}")
    elif out.exists(): out.unlink()
    return ok

def tts(text, boost, out, speed=None, voices=None):
    if boost == 'Chinese' and ZH_ENGINE == 'edge' and tts_edge(text, out): return
    key = os.environ.get('MINIMAX_API_KEY')
    if not key: sys.exit(f'Thiếu {out.name}: Xiaoxiao (edge-tts) không chạy được và không có MINIMAX_API_KEY; hãy tạo sẵn đoạn mp3')
    for vid in (voices or [L['voice']]):
      if tts_one(text, boost, out, speed, vid, key): return
    sys.exit(f"{out.name}: không giọng nào được chấp nhận: {voices}")

def tts_one(text, boost, out, speed, vid, key):
    body = {"model": os.environ.get('MINIMAX_MODEL', 'speech-2.8-hd'), "text": text, "stream": False,
            "language_boost": boost, "output_format": "hex",
            "voice_setting": {"voice_id": vid, "speed": speed or SPEED, "vol": 1, "pitch": 0},
            "audio_setting": {"sample_rate": 32000, "bitrate": 128000, "format": "mp3", "channel": 1}}
    host = os.environ.get('MINIMAX_HOST', 'https://api.minimax.io')
    rq = urllib.request.Request(host + '/v1/t2a_v2', json.dumps(body).encode(),
         {'Authorization': 'Bearer ' + key, 'Content-Type': 'application/json'})
    r = json.load(urllib.request.urlopen(rq, timeout=120))
    if r['base_resp']['status_code'] != 0:
        print(f"{out.name}: giọng {vid} bị từ chối: {r['base_resp']}"); return False
    out.write_bytes(bytes.fromhex(r['data']['audio'])); print(f"{out.name}: dùng giọng {vid}"); return True

def dur(f):
    return float(subprocess.check_output(['ffprobe','-v','error','-show_entries','format=duration','-of','csv=p=0',str(f)]))

def norm(src, dst):  # cùng độ to cho VI và ZH, cắt lặng đầu/cuối
    subprocess.run(['ffmpeg','-y','-v','error','-i',str(src),'-ar','32000','-ac','1','-af',
      'silenceremove=start_periods=1:start_threshold=-45dB:stop_periods=1:stop_threshold=-45dB:stop_duration=0.15,loudnorm=I=-16:TP=-1.5:LRA=11',
      str(dst)], check=True)

def silence(sec, dst):
    subprocess.run(['ffmpeg','-y','-v','error','-f','lavfi','-i','anullsrc=r=32000:cl=mono','-t',str(sec),str(dst)], check=True)

times = {}
only = set(sys.argv[3:])
for ln in L['lines']:
    parts, t, rec, prev = [], 0.0, [], None
    for n, s in enumerate(ln['segs']):
        raw = SEG / f"{ln['id']}_{n}.mp3"
        txt = clean_vi(s['text']) if s['k'] == 'vi' else s['text'].strip()
        if s['k'] == 'zh' and not txt.endswith('。'): txt += '。'
        if not raw.exists() and (not only or ln['id'] in only):
            tts(txt, 'Vietnamese' if s['k'] == 'vi' else 'Chinese', raw, None if s['k'] == 'vi' else ZH_SPEED, None if s['k'] == 'vi' else ZH_VOICES)
        nf = SEG / f"{ln['id']}_{n}.n.mp3"; norm(raw, nf)
        d = dur(nf)
        if s['k'] == 'zh' and d > 1.8 + 0.25 * len(txt): print(f"CẢNH BÁO: đoạn ZH dài bất thường {ln['id']}_{n} {d:.2f}s {txt}")
        reps = ZH_REPEAT if s['k'] == 'zh' else 1
        if prev:
            g = GAP['zz'] if prev == s['k'] == 'zh' else GAP['vz']
            gf = SEG / f'gap_{g}.mp3'
            if not gf.exists(): silence(g, gf)
            parts.append(gf); t += g
        for r in range(reps):
            if r:
                gr = SEG / f'gap_{GAP_REPEAT}.mp3'
                if not gr.exists(): silence(GAP_REPEAT, gr)
                parts.append(gr); t += GAP_REPEAT
            parts.append(nf); rec.append(dict(n=n, rep=r + 1, k=s['k'], a=round(t, 3), b=round(t + d, 3), text=txt)); t += d
        prev = s['k']
    gf = SEG / 'gap_line.mp3'
    if not gf.exists(): silence(GAP['line'], gf)
    parts.append(gf)
    lst = SEG / f"{ln['id']}.txt"; lst.write_text(''.join(f"file '{p.name}'\n" for p in parts))
    subprocess.run(['ffmpeg','-y','-v','error','-f','concat','-i',str(lst),'-c:a','libmp3lame','-b:a','128k',str(VOI / f"{ln['id']}.mp3")], check=True)
    times[ln['id']] = dict(total=round(t + GAP['line'], 3), segs=rec)
    print(f"{ln['id']}: {t+GAP['line']:.2f}s, {len(rec)} đoạn")
json.dump(times, open(WORK / 'segtimes.json', 'w'), ensure_ascii=False, indent=1)
print('Đã ghi work/segtimes.json - dùng mốc này đặt phụ đề (trễ ≤0,15s), không chia theo số ký tự.')
