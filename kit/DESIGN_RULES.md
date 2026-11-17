# Quy tắc minh họa và hiệu ứng (đồng bộ theo lời đọc)

Mốc thời gian lấy từ `work/segtimes.json` (bắt đầu/kết thúc từng đoạn, kể cả lần nhắc lại). Mỗi hiệu ứng bắt đầu cùng lúc (trễ ≤0,15 giây) với đoạn tiếng tương ứng, không đặt theo số ký tự.

1. **Khi giọng Trung đọc một từ**: thẻ chữ Hán lớn xuất hiện (zoom-in nhẹ + phát sáng cyan), pinyin có dấu thanh bên dưới, nghĩa tiếng Việt dưới cùng. Lần đọc lặp thứ 2: thẻ nảy nhẹ (pulse) và viền sáng chạy quanh để nhấn mạnh; tắt dần sau khi hết đoạn.
2. **Khi giọng Việt giải thích một ý**: hiện minh họa vẽ sẵn bằng SVG/CSS cho đúng ý đó (biểu tượng chào hỏi, bàn tay vẫy, miệng phát âm, sóng âm, đường cong 4 thanh điệu, số đếm bằng que/chấm, gia đình, đồ ăn...), nền tối navy, màu nhấn cyan, bo góc, đổ bóng mềm.
3. **Thanh điệu**: vẽ đường cong thanh (ngang, lên, xuống-lên, xuống) chạy theo giọng; thanh nào đang đọc thì sáng, các thanh khác mờ.
4. **Chuyển cảnh**: mỗi ý một cảnh, chuyển bằng trượt/mờ ngắn (≤0,35 giây); thanh tiến trình đầu khung sáng theo cảnh hiện tại.
5. **Chữ luôn dễ đọc**: chữ Hán ≥ 150px, pinyin ≥ 64px, phụ đề ≥ 56px, nằm trong vùng an toàn (cách mép ≥ 6%, đáy 18%); không để minh họa che chữ; mỗi lúc chỉ nhấn mạnh MỘT thứ.
6. **Không dùng**: ảnh có bản quyền, nhân vật/logo có sẵn, emoji lá cờ, hình người thật. Chỉ vẽ tự do bằng SVG/CSS.
7. **Kiểm tra**: trích khung ở giữa mỗi đoạn tiếng Trung và mỗi ý chính để xem minh họa đúng ý, đẹp, không tràn, không đè chữ; cảnh nào trống hoặc xấu thì sửa trước khi push.
