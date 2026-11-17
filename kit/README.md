# Bộ dựng video Reel tiếng Trung (9:16)
- script.json: voice + lines[]; mỗi line: id (hook,s1..s5,cta), text (lời đọc, viết chữ), step 0..6, zh, py, title, sub, chips|chat|hz, caps (phụ đề: cụm cách nhau " / ", từ viết `hiển_thị|chữ_đọc`, dấu _ = khoảng trắng).
- Đặt voice/<id>.mp3 rồi chạy: python3 build.py  -> out/final.mp4 (chưa có khung bìa).
- Khung bìa: sửa cover.html, chụp PNG 1080x1920 bằng Playwright, ghép 0.5s đầu (xem skill topic-to-reel-video, bước 3b).
- Cần: python3, numpy, scipy, playwright, /usr/bin/google-chrome, ffmpeg, font Inter + Noto Sans CJK.
- Không chứa khóa/bí mật.
