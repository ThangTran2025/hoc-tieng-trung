import json,sys
from playwright.sync_api import sync_playwright
import pathlib
R=pathlib.Path('/home/claude/zh');TL=json.load(open(R/'work/timing.json'));S=json.load(open(R/'script.json'))
pts=[TL[0]['a']+1.6]+[t['a']+t['vd']*.6 for t in TL[1:]]
with sync_playwright() as p:
    b=p.chromium.launch(executable_path='/usr/bin/google-chrome',args=['--no-sandbox','--disable-gpu']);pg=b.new_page(viewport={'width':1080,'height':1920})
    pg.goto('file://'+str(R/'page.html'));pg.evaluate('document.fonts.ready');pg.evaluate(f'setup({json.dumps(S)},{json.dumps(TL)})')
    for k,t in enumerate(pts):
        pg.evaluate(f'render({t})');pg.screenshot(path=f'/home/claude/zh/work/p{k}.png')
from PIL import Image
ims=[Image.open(f'/home/claude/zh/work/p{k}.png').resize((360,640)) for k in range(len(pts))]
sh=Image.new('RGB',(360*4,640*2),'#000')
for k,im in enumerate(ims):sh.paste(im,((k%4)*360,(k//4)*640))
sh.save('/home/claude/zh/work/sheet.png')
