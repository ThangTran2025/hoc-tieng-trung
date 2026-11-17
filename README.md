# Bộ dựng video bài học (Reel 9:16)
Nhánh `lesson-kit` chỉ chứa mã, không chứa khóa/bí mật.

Quy trình mỗi bài:
1. Soạn `lesson.json` (phụ đề `caps` tách riêng khỏi `segs` để đọc).
2. `python3 kit/voice_segments.py lesson.json .` -> `voice/*.mp3` + `work/segtimes.json`
   (đặt MINIMAX_API_KEY trên máy cá nhân; trên đám mây thì tạo sẵn `seg/<id>_<n>.mp3`).
3. `python3 kit/build.py` -> `out/final.mp4` (cần Chrome + playwright + ffmpeg).
4. `kit/fix_ending.py`: mẫu vá phần kết (3 giây màn hình kết thúc).
Quy tắc giọng: VI thuần ≤18 từ; ZH gửi chữ Hán từng từ bằng GIỌNG NỮ TIẾNG TRUNG PHỔ THÔNG (không dùng giọng clone Việt), đọc CHẬM (speed 0,75) và NHẮC LẠI 2 LẦN (nghỉ 0,55s); khoảng lặng cố định; loudnorm.
