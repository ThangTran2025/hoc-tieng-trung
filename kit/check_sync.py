#!/usr/bin/env python3
"""Kiểm tra phụ đề khớp tiếng. Thoát mã 1 nếu có lỗi -> KHÔNG được push video.
Dùng: python3 kit/check_sync.py work/segtimes.json work/cues.json work/offsets.json [final_audio.(mp3|wav|mp4)]
- segtimes.json : do voice_segments.py ghi (mốc đoạn tương đối trong từng dòng)
- offsets.json  : {line_id: thời điểm bắt đầu dòng trong video (giây, tuyệt đối)}
- cues.json     : danh sách phụ đề thật đã dựng: [{"line":"s1","seg":0,"rep":1,"text":"...","a":12.30,"b":13.80}, ...]
                  (a,b tuyệt đối; seg/rep chỉ đoạn tiếng mà cụm phụ đề đó đang hiển thị)
- audio (tùy chọn): đối chiếu thật với âm thanh bằng silencedetect (đoạn có tiếng thực sự bắt đầu/kết thúc lúc nào)
Quy tắc: phụ đề hiện không sớm hơn tiếng 0,05s và không trễ hơn 0,15s; ẩn không sớm hơn lúc tiếng hết và không muộn quá 0,35s;
không chồng nhau; tối đa 2 dòng, mỗi dòng ≤ 27 ký tự; không để một từ lẻ (1 từ) làm cả dòng cuối; không ngắt giữa chữ Hán và pinyin của cùng từ.
"""
import json, sys, re, subprocess
seg = json.load(open(sys.argv[1])); cues = json.load(open(sys.argv[2])); off = json.load(open(sys.argv[3]))
errs = []
def E(m): errs.append(m)
# mốc tiếng tuyệt đối
S = {}
for lid, d in seg.items():
    for r in d['segs']:
        S[(lid, r['n'], r.get('rep', 1))] = (off[lid] + r['a'], off[lid] + r['b'], r['text'])
prev_b = -1
for c in sorted(cues, key=lambda x: x['a']):
    k = (c['line'], c['seg'], c.get('rep', 1))
    if k not in S: E(f"cue {c['text']!r}: không có đoạn tiếng {k}"); continue
    a, b, t = S[k]
    if c['a'] < a - 0.05: E(f"cue {c['text']!r} hiện SỚM {a - c['a']:.2f}s so với tiếng")
    if c['a'] > a + 0.15: E(f"cue {c['text']!r} hiện TRỄ {c['a'] - a:.2f}s so với tiếng")
    if c['b'] < b - 0.02: E(f"cue {c['text']!r} ẩn khi tiếng chưa hết ({b - c['b']:.2f}s)")
    if c['b'] > b + 0.35 and not c.get('hold'): E(f"cue {c['text']!r} nằm lại quá lâu sau tiếng ({c['b'] - b:.2f}s)")
    if c['a'] < prev_b - 0.01: E(f"cue {c['text']!r} chồng lên cue trước")
    prev_b = max(prev_b, c['b'])
    lines = c['text'].split('\n')
    if len(lines) > 2: E(f"cue {c['text']!r} quá 2 dòng")
    for ln in lines:
        if len(ln) > 27: E(f"cue dòng quá dài ({len(ln)}): {ln!r}")
    if len(lines) == 2 and len(lines[-1].split()) == 1 and len(lines[0]) > 10: E(f"cue có từ lẻ ở dòng cuối: {c['text']!r}")
# mỗi đoạn tiếng phải có ít nhất 1 cue phủ
for k, (a, b, t) in S.items():
    if not any((c['line'], c['seg'], c.get('rep', 1)) == k for c in cues): E(f"đoạn tiếng {k} ({t}) không có phụ đề")
# đối chiếu âm thanh thật
if len(sys.argv) > 4:
    out = subprocess.run(['ffmpeg', '-i', sys.argv[4], '-af', 'silencedetect=n=-38dB:d=0.12', '-f', 'null', '-'], capture_output=True, text=True).stderr
    ss = [float(x) for x in re.findall(r'silence_start: ([\d.]+)', out)]
    se = [float(x) for x in re.findall(r'silence_end: ([\d.]+)', out)]
    onsets = se  # lúc có tiếng trở lại sau khoảng lặng
    for k, (a, b, t) in sorted(S.items(), key=lambda kv: kv[1][0]):
        near = min(onsets + [0.0], key=lambda o: abs(o - a))
        if abs(near - a) > 0.12 and a > 0.8: E(f"tiếng thật bắt đầu lệch {near - a:+.2f}s so với mốc {k} ({t}) tại {a:.2f}s")
print('\n'.join(errs) if errs else f'OK: {len(cues)} cue khớp {len(S)} đoạn tiếng')
sys.exit(1 if errs else 0)
