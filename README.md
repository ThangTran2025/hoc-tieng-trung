# Bộ dựng video bài học (Reel 9:16)
Nhánh `lesson-kit` chỉ chứa mã, không chứa khóa/bí mật.

Quy trình mỗi bài:
1. Soạn `lesson.json` (phụ đề `caps` tách riêng khỏi `segs` để đọc).
2. `python3 kit/voice_segments.py lesson.json .` -> `voice/*.mp3` + `work/segtimes.json`
   (đặt MINIMAX_API_KEY trên máy cá nhân; trên đám mây thì tạo sẵn `seg/<id>_<n>.mp3`).
3. `python3 kit/build.py` -> `out/final.mp4` (cần Chrome + playwright + ffmpeg).
4. `kit/fix_ending.py`: mẫu vá phần kết (3 giây màn hình kết thúc).
Quy tắc giọng: VI thuần ≤18 từ; ZH gửi chữ Hán từng từ bằng GIỌNG NỮ TIẾNG TRUNG PHỔ THÔNG Xiaoxiao (zh-CN-XiaoxiaoNeural qua edge-tts, tốc độ -30%; dự phòng giọng Mandarin của MiniMax; không dùng giọng clone Việt), đọc CHẬM (speed 0,75) và NHẮC LẠI 2 LẦN (nghỉ 0,55s); khoảng lặng cố định; loudnorm.

QUY TẮC TÁCH GIỌNG: tiếng Việt chỉ do giọng clone đọc; tiếng Trung chỉ do giọng Trung (Xiaoxiao) đọc. Đoạn VI cấm chữ Hán, pinyin, phiên âm kiểu Việt (vd 'ni hảo'). Phụ đề chỉ để hiển thị, không dùng để tạo giọng.

CỔNG CHO PHÉP: script và tác vụ hằng ngày chỉ được tạo bài có mã trong kit/AUTHORIZED.json. Không tự ý thêm mã; chỉ thêm khi chủ sở hữu cho phép trong chat Claude. lesson.json phải có "lesson_id".

Quy tắc minh họa/hiệu ứng theo lời đọc: xem kit/DESIGN_RULES.md

KIỂM TRA KHỚP 100%: sau khi dựng, build phải ghi work/cues.json + work/offsets.json rồi chạy `python3 kit/check_sync.py work/segtimes.json work/cues.json work/offsets.json <âm thanh cuối>`; còn lỗi thì sửa và chạy lại, KHÔNG push khi chưa OK.
