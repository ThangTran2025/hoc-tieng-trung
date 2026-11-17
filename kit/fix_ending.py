import subprocess, sys, numpy as np
from PIL import Image, ImageDraw, ImageFont, ImageFilter

W, H = 1080, 1920
FS = W * H * 3
T0 = 77.0           # first replaced frame time
FPS = 30
SRC = 'v.mp4'
OUT = sys.argv[1] if len(sys.argv) > 1 else 'fixed_raw.mp4'
EXTRA = 90          # +3.0 s end screen

TIMELINE = [  # (start time, text) on the final video timeline
    (0.0, "Bạn trả lời ở phần bình luận nhé."),
    (77.45, "Ngày mai là bài 2:"),
    (78.90, "bốn thanh điệu và quy tắc đọc."),
    (80.50, "Hãy theo dõi Học Tiếng Trung"),
    (81.95, "Mỗi Ngày để không bỏ lỡ!"),
]
font = ImageFont.truetype('/usr/share/fonts/opentype/inter/Inter-ExtraBold.otf', 62)

def text_at(t):
    cur = TIMELINE[0][1]
    for s, tx in TIMELINE:
        if t >= s:
            cur = tx
    return cur

def wrap(txt):
    d = ImageDraw.Draw(Image.new('RGB', (10, 10)))
    w = lambda s: d.textlength(s, font=font)
    if txt.startswith('Bạn trả lời'):
        return ['Bạn trả lời ở phần bình luận', 'nhé.']
    if w(txt) <= 880:
        return [txt]
    words = txt.split(' ')
    best = None
    for i in range(1, len(words)):
        a, b = ' '.join(words[:i]), ' '.join(words[i:])
        m = max(w(a), w(b))
        if best is None or m < best[0]:
            best = (m, a, b)
    return [best[1], best[2]]

def bilinear(c_tl, c_tr, c_bl, c_br, w, h):
    xs = np.linspace(0, 1, w)[None, :, None]
    ys = np.linspace(0, 1, h)[:, None, None]
    tl, tr, bl, br = [np.array(c, float)[None, None, :] for c in (c_tl, c_tr, c_bl, c_br)]
    return (tl * (1 - xs) * (1 - ys) + tr * xs * (1 - ys) + bl * (1 - xs) * ys + br * xs * ys)

X0, X1, Y0, Y1 = 90, 990, 1350, 1562
mask = Image.new('L', (X1 - X0, Y1 - Y0), 0)
ImageDraw.Draw(mask).rounded_rectangle((0, 0, X1 - X0 - 1, Y1 - Y0 - 1), radius=24, fill=255)
mask_np = (np.array(mask, float) / 255.0)[:, :, None]

cache = {}
def overlay(frame, txt):
    a = np.frombuffer(frame, np.uint8).reshape(H, W, 3).copy()
    def avg(x, y):
        return a[y - 3:y + 4, x - 3:x + 4].reshape(-1, 3).mean(0)
    fill = bilinear(avg(X0 + 20, Y0 + 14), avg(X1 - 20, Y0 + 14), avg(X0 + 20, Y1 - 14), avg(X1 - 20, Y1 - 14), X1 - X0, Y1 - Y0)
    reg = a[Y0:Y1, X0:X1].astype(float)
    a[Y0:Y1, X0:X1] = (reg * (1 - mask_np) + fill * mask_np).astype(np.uint8)
    key = txt
    if key not in cache:
        lines = wrap(txt)
        lay = Image.new('RGBA', (W, H), (0, 0, 0, 0))
        d = ImageDraw.Draw(lay)
        lh = 62 * 1.3
        cy = 1456 - lh * (len(lines) - 1) / 2
        sh = Image.new('RGBA', (W, H), (0, 0, 0, 0))
        ds = ImageDraw.Draw(sh)
        for i, ln in enumerate(lines):
            y = cy + i * lh
            ds.text((540, y + 4), ln, font=font, fill=(0, 0, 0, 200), anchor='mm')
            d.text((540, y), ln, font=font, fill=(255, 255, 255, 255), anchor='mm')
        sh = sh.filter(ImageFilter.GaussianBlur(5))
        cache[key] = Image.alpha_composite(sh, lay)
    img = Image.fromarray(a).convert('RGBA')
    img = Image.alpha_composite(img, cache[key]).convert('RGB')
    return np.asarray(img).tobytes()

dec = subprocess.Popen(['ffmpeg', '-v', 'error', '-ss', str(T0), '-i', SRC, '-f', 'rawvideo', '-pix_fmt', 'rgb24', '-'], stdout=subprocess.PIPE)
frames = []
while True:
    b = dec.stdout.read(FS)
    if len(b) < FS:
        break
    frames.append(b)
n = len(frames)
print('tail frames', n, file=sys.stderr)

enc = subprocess.Popen(['ffmpeg', '-v', 'error', '-y', '-i', SRC, '-f', 'rawvideo', '-pix_fmt', 'rgb24', '-s', f'{W}x{H}', '-framerate', str(FPS), '-i', '-',
                        '-filter_complex', f'[0:v]trim=end={T0},setpts=PTS-STARTPTS,format=yuv420p[a];[1:v]setpts=PTS-STARTPTS,format=yuv420p[b];[a][b]concat=n=2:v=1:a=0[v];[0:a]apad=pad_dur=3,atrim=end={n / FPS + T0 + EXTRA / FPS}[au]',
                        '-map', '[v]', '-map', '[au]', '-c:v', 'libx264', '-preset', 'veryfast', '-crf', '18', '-profile:v', 'high', '-pix_fmt', 'yuv420p', '-r', '30',
                        '-c:a', 'aac', '-b:a', '160k', '-movflags', '+faststart', OUT], stdin=subprocess.PIPE)

seq = list(range(n)) + list(range(n - 2, n - 2 - 65, -1)) + list(range(n - 65, n - 65 + 25))
assert len(seq) == n + EXTRA, len(seq)
for i, idx in enumerate(seq):
    t = T0 + i / FPS
    enc.stdin.write(overlay(frames[idx], text_at(t)))
enc.stdin.close()
enc.wait()
print('done', enc.returncode)
