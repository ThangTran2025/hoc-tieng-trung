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
