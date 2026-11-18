#!/usr/bin/env python3
"""Bộ lọc nội dung (Giai đoạn 1-2). Chạy TRƯỚC khi tạo giọng và dựng video:
   python3 kit/qa_content.py lesson.json
Thoát mã 1 nếu còn lỗi. Chỉ kiểm tra được phần máy kiểm tra được; phần kiến thức vẫn phải
do trợ lý kiểm duyệt độc lập đối chiếu nguồn (xem kit/QA_GATES.md).
Cấu trúc lesson.json: lesson_id, sources[{claim,url}], lines[{id,caps,segs[{k,text}]}]"""
import json, re, sys, unicodedata
L = json.load(open(sys.argv[1], encoding='utf-8'))
err, warn = [], []
HAN = re.compile(r'[一-鿿]')
TONE = set('āáǎàēéěèīíǐìōóǒòūúǔùǖǘǚǜ')
PYONLY = set('āēīōūǖǘǎěǐǒǔǚǜ')  # chỉ có trong pinyin, không có trong tiếng Việt
# âm tiết pinyin hợp lệ (không dấu); kiểm tra từng âm tiết trong ngoặc
INIT = ['zh','ch','sh','b','p','m','f','d','t','n','l','g','k','h','j','q','x','r','z','c','s','y','w','']
FIN = ['a','o','e','ai','ei','ao','ou','an','en','ang','eng','ong','er','i','ia','ie','iao','iu','ian','in','iang','ing','iong','u','ua','uo','uai','ui','uan','un','uang','ueng','ü','üe','üan','ün','v','ve','van','vn']
def strip_tone(s):
    out = ''
    for ch in unicodedata.normalize('NFD', s.lower()):
        if unicodedata.category(ch) == 'Mn' and ch != '̈': continue
        out += ch
    return unicodedata.normalize('NFC', out)
def valid_syl(s):
    s = strip_tone(s).replace('ü', 'v')
    if s in ('a','o','e','ai','ei','ao','ou','an','en','ang','eng','er','yi','wu','yu','ng','n','m','hm'): return True
    for i in sorted(INIT, key=len, reverse=True):
        if s.startswith(i) and s[len(i):].replace('ü','v') in [f.replace('ü','v') for f in FIN] + ['i','u','v','ue','ve']: return True
    return False
def seg_ok(w):
    w = w.lower(); ok = [True] + [False] * len(w)
    for e in range(1, len(w) + 1):
        for b in range(max(0, e - 6), e):
            if ok[b] and valid_syl(w[b:e]): ok[e] = True; break
    return ok[len(w)]
if not re.fullmatch(r'bai\d\d', str(L.get('lesson_id',''))): err.append('thiếu/sai lesson_id (dạng bai01)')
if not L.get('sources'): err.append('thiếu "sources": mỗi nội dung kiến thức phải có nguồn đối chiếu (giáo trình/từ điển/trang chính thống)')
for s in L.get('sources', []):
    if not str(s.get('url','')).startswith('http') or not s.get('claim'): err.append(f'nguồn không hợp lệ: {s}')
for ln in L['lines']:
    i = ln['id']; caps = ln['caps']; segs = ln['segs']
    zh_txt = ''.join(s['text'] for s in segs if s['k'] == 'zh')
    for s in segs:
        t = s['text']
        if s['k'] == 'vi':
            if HAN.search(t): err.append(f'{i}: đoạn VI chứa chữ Hán: {t}')
            if any(c in PYONLY for c in t.lower()): err.append(f'{i}: đoạn VI chứa pinyin có dấu thanh Trung: {t}')
            if re.search(r'\d|[/:()+\-=]', t): err.append(f'{i}: đoạn VI còn số/ký hiệu (phải viết thành chữ): {t}')
            if len(t.split()) > 18: err.append(f'{i}: đoạn VI quá dài (>18 từ): {t}')
            if re.search(r'\b(ni hảo|xiê xiê|chai chen|ni hao)\b', t.lower()): err.append(f'{i}: phiên âm kiểu Việt trong đoạn VI: {t}')
        else:
            if not HAN.search(t): err.append(f'{i}: đoạn ZH không có chữ Hán: {t}')
            if re.search(r'[A-Za-z\d]', t): err.append(f'{i}: đoạn ZH chứa chữ La-tinh/số (chỉ gửi chữ Hán): {t}')
    caps_han = ''.join(HAN.findall(caps))
    # chữ Hán hiển thị phải đúng bằng chữ Hán sẽ đọc, cùng thứ tự (cho phép ví dụ không đọc nếu đánh dấu ln.get('noread'))
    if not ln.get('noread') and caps_han != zh_txt: err.append(f'{i}: chữ Hán trên màn hình ({caps_han}) khác chữ Hán đọc ({zh_txt})')
    # pinyin trong ngoặc phải có dấu thanh (trừ thanh nhẹ) và hợp lệ
    for m in re.findall(r'\(([^()]*)\)', caps):
        if HAN.search(m) or not re.search(r'[a-zA-Zü]', m): continue
        for word in re.findall(r"[A-Za-zÀ-ɏ]+", m):
            if not seg_ok(word): err.append(f'{i}: pinyin lạ "{word}" trong "{m}"')
        if not any(c in TONE for c in m.lower()) and 'nhẹ' not in caps: warn.append(f'{i}: pinyin "{m}" không có dấu thanh (đúng nếu toàn thanh nhẹ)')
    if len(caps) > 60: warn.append(f'{i}: phụ đề dài ({len(caps)} ký tự), kiểm tra ngắt nhịp ≤2 dòng×27')
    if re.search(r'  |\s[,.;:!?]', caps): err.append(f'{i}: lỗi khoảng trắng/dấu câu trong phụ đề: {caps}')
for w in warn: print('CẢNH BÁO:', w)
for e in err: print('LỖI:', e)
print('OK' if not err else f'CÒN {len(err)} LỖI - dừng, sửa rồi chạy lại')
sys.exit(1 if err else 0)
