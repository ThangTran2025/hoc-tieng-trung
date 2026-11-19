# Cổng kiểm soát chất lượng theo giai đoạn (BẮT BUỘC cho mọi bài)

Nguyên tắc: nội dung phải đúng chương trình học thực tế, học viên làm theo được. Không viết bài ảo, không bịa, không tưởng tượng. Không chắc thì bỏ, không đoán. Mỗi giai đoạn có một người kiểm duyệt ĐỘC LẬP (một tác tử phụ chưa từng thấy quá trình làm, nếu có công cụ Agent; nếu không thì tự kiểm theo từng điểm ở danh sách, ghi rõ vào notes). Giai đoạn nào chưa đạt thì dừng, sửa, kiểm lại; không chuyển giai đoạn kế.

## G1. Nội dung đầu vào (trước khi tạo giọng)
- Mỗi kiến thức (chữ Hán, pinyin, nghĩa, quy tắc phát âm) có NGUỒN đối chiếu từ trang chính thống (giáo trình Hán ngữ/HSK chính thức, từ điển uy tín) ghi trong `lesson.json` mục `sources` (claim + url). Cần tra web thật, không dựa vào trí nhớ.
- Đúng chương trình HSK 1 hiện hành, từ vựng cấp 1; không nêu số liệu (số từ, điểm thi, ngày thi) nếu chưa kiểm chứng; đúng thứ tự giáo án; không nhảy sang kiến thức bài sau.
- Mỗi ví dụ là câu/từ người Trung Quốc thực sự dùng; học viên làm theo được ngay (có hướng dẫn thực hành cụ thể, câu hỏi luyện tập có đáp án đúng).
- Chạy `python3 kit/qa_content.py lesson.json` phải ra OK (máy kiểm: đoạn VI không Hán/pinyin/số/ký hiệu, chữ Hán hiển thị khớp chữ Hán đọc, pinyin hợp lệ, có nguồn).
- Kiểm duyệt độc lập 1 (nội dung): đối chiếu từng chữ Hán và pinyin với nguồn, xác nhận không sai nghĩa, không sai quy tắc.

## G2. Chính tả và trình bày chữ
- Chính tả tiếng Việt đủ dấu, đúng từng từ; chữ Hán đúng nét/phồn giản (dùng giản thể); pinyin đúng dấu thanh và viết liền theo từ.
- Phụ đề: ≤2 dòng, mỗi dòng ≤27 ký tự, không ngắt chữ lẻ, không tràn ô (xem DESIGN_RULES 8-11).
- Kiểm duyệt độc lập 2 (chính tả): đọc lại toàn bộ chữ trên màn hình, liệt kê mọi lỗi; còn lỗi thì không dựng tiếp.

## G3. Giọng đọc
- Tiếng Việt chỉ giọng clone, tiếng Trung chỉ giọng Xiaoxiao -30% (xem README/prompt). Kiểm tra bằng máy: `check_sync.py` OK, `silencedetect` khớp mốc, không đoạn ZH nào im lặng hoặc cụt, mỗi đoạn VI nghe trọn câu.
- Kiểm duyệt độc lập 3 (giọng): so từng đoạn ZH với chữ Hán gửi đi (đúng chữ, đúng thanh), các chữ dễ sai như 哦 佛 迂 讷 ghi vào notes để người biết tiếng Trung nghe lại.

## G4. Hình ảnh và đồng bộ
- Minh họa xuất hiện đúng lúc từng phần được đọc; khung giữa mỗi đoạn được trích xem; không chữ tràn/đè.
- Kiểm duyệt độc lập 4 (hình): xem contact sheet toàn bộ cảnh, liệt kê lỗi bố cục.

## G5. Bàn giao
- Video có màn hình kết thúc giới thiệu chineselearn.asia (DESIGN_RULES 12), caption/mô tả có dòng "Học thêm tại chineselearn.asia".
- Chỉ đẩy lên GitHub và báo Telegram khi G1 đến G4 đều đạt. Tin Telegram ghi rõ kết quả từng cổng (đạt/chưa đạt và điểm còn cần người xem). Không đăng Facebook/YouTube khi chưa có "duyệt <mã>".

## G0. Kịch bản đã duyệt
- Nếu AUTHORIZED.json có `kich_ban` thì dùng nguyên file đó (đã được chủ sở hữu duyệt); không tự soạn lại. Chỉ rút ngắn khi vượt thời lượng, và ghi vào notes.
- Kiểm duyệt độc lập bằng tác tử phụ (Agent) là BẮT BUỘC ở G1, G3, G4; không có công cụ Agent thì nói rõ trong tin Telegram rằng chưa có kiểm duyệt độc lập.

## Quy trình chạy chuẩn (cấu hình theo tài liệu "Quy trình tự động sản xuất & kiểm duyệt video")
Áp dụng cho mọi bài. Ánh xạ từ tài liệu chung sang bộ kit hiện tại (video mẫu chuẩn = bài 01 đã đăng: 1080x1920, 30 fps, ~-16 LUFS, có khung bìa và màn kết):

0. **Nguyên tắc**: dùng script sẵn có của kit (`voice_segments.py`, `check_sync.py`, `build.py`, `page.html`, `qa_content.py`), không tự viết lại dựng video. Thư mục gốc (kịch bản, voice thô) chỉ đọc; mọi sản phẩm trung gian nằm trong `work/`. Mọi lần chạy ghi log vào `tmp/render.log`; render lỗi thì đọc log trước khi sửa.
1. **Phân tích mẫu (job_config)**: ghi `work/job_config.json` gồm độ phân giải, fps, thời lượng, codec, LUFS, và đặc tả hình (vị trí chữ, font, màu, bố cục) lấy từ bài 01. Lấy 16 khung đều từ bài 01 ghép lưới 4x4 thành `tmp/ref_sheet.jpg` và đối chiếu bằng mắt trước khi dựng.
2. **Chạy pipeline**: áp dụng job_config vào `build.py`/`page.html`; chuẩn hóa âm lượng `loudnorm`; nhạc nền (nếu có) không lấn giọng nói; xuất `tmp/final.mp4` (hoặc `tmp/video.mp4` theo quy ước hiện tại) và ghi log.
3. **Cổng kiểm duyệt tự động (bắt buộc đủ 3 lớp)**:
   - Kỹ thuật: ffprobe khớp 100% độ phân giải/fps với job_config; có đủ luồng hình và tiếng; `blackdetect` và `silencedetect` không có đoạn đen > 0,5 giây hoặc mất tiếng giữa chừng.
   - Thị giác: lấy 16 khung đều của video cuối thành `tmp/qa_sheet.jpg`, đối chiếu với `tmp/ref_sheet.jpg`: chữ đúng vị trí và style, không tràn viền, không cắt chữ, không che mặt/chủ thể, không vỡ hình hoặc sai tỷ lệ khung.
   - Đồng bộ nội dung: `check_sync.py` OK, khớp mốc với phụ đề, không cụt câu cuối.
4. **Vòng tự sửa**: nếu một tiêu chí không đạt, xác định nguyên nhân (tọa độ chữ, font, lệch timestamp, lỗi render), sửa `work/job_config.json` hoặc code, chạy lại bước 2 và 3. Tối đa **3 lần**. Sau 3 lần vẫn lỗi nghiêm trọng thì dừng, báo nguyên nhân kèm log; không đẩy lên GitHub và không gửi Telegram như bản đạt.
5. **Bàn giao**: chỉ khi đạt 100% cổng ở bước 3 mới đẩy lên nhánh `tmp-<mã>` và báo Telegram. Báo cáo gồm: đường dẫn/mã commit, thông số thực tế (độ phân giải, fps, thời lượng, dung lượng), kết quả đối chiếu thị giác với bài 01, và danh sách lỗi đã tự sửa trong vòng lặp. Không đăng Facebook/YouTube khi chưa có "duyệt <mã>".
