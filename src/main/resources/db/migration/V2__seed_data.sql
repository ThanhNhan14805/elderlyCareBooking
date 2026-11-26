-- ------------------------------------------------------------------------------
-- 1. BẢNG NGƯỜI DÙNG (USERS) - 25 DÒNG (8 Khách, 15 Tasker, 2 Admin)
-- ------------------------------------------------------------------------------
INSERT INTO users (user_id, username, phone_number, password, full_name, email, role) VALUES
-- Khách hàng (Customers: 1 - 8)
(1, 'nhan_nguyen', '0978458129', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Nhan Nguyen', 'nhan.nguyen@gmail.com', 'CUSTOMER'),
(2, 'bich_tran',   '0912345678', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Trần Thị Bích', 'bich.tran@gmail.com', 'CUSTOMER'),
(3, 'duc_le',      '0987654321', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Lê Văn Đức', 'duc.le@gmail.com', 'CUSTOMER'),
(4, 'hoang_pham',  '0903112233', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Phạm Minh Hoàng', 'hoang.pham@yahoo.com', 'CUSTOMER'),
(5, 'huong_nguyen','0938445566', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Nguyễn Thị Thu Hương', 'huong.nguyen@gmail.com', 'CUSTOMER'),
(6, 'son_dang',    '0947889900', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Đặng Thanh Sơn', 'son.dang@outlook.com', 'CUSTOMER'),
(7, 'oanh_vo',     '0961223344', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Võ Thị Kim Oanh', 'oanh.vo@gmail.com', 'CUSTOMER'),
(8, 'huy_doan',    '0973556677', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Đoàn Gia Huy', 'huy.doan@gmail.com', 'CUSTOMER'),

-- Chăm sóc viên / Tasker (Caregivers: 9 - 23)
(9,  'mai_tasker',   '0988776655', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Nguyễn Thị Mai', 'mai.tasker@ecare.vn', 'CAREGIVER'),
(10, 'binh_care',    '0909123456', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Trần Văn Bình', 'binh.care@ecare.vn', 'CAREGIVER'),
(11, 'lan_nurse',    '0933456789', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Lê Thị Ngọc Lan', 'lan.nurse@ecare.vn', 'CAREGIVER'),
(12, 'tuyet_care',   '0977889900', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Phạm Thị Tuyết', 'tuyet.care@ecare.vn', 'CAREGIVER'),
(13, 'long_care',    '0918223344', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Võ Hoàng Long', 'long.care@ecare.vn', 'CAREGIVER'),
(14, 'yen_nurse',    '0944556677', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Hoàng Thị Yến', 'yen.nurse@ecare.vn', 'CAREGIVER'),
(15, 'duc_tasker',   '0966778899', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Nguyễn Văn Đức', 'duc.tasker@ecare.vn', 'CAREGIVER'),
(16, 'nga_care',     '0981998877', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Đinh Thị Thanh Nga', 'nga.care@ecare.vn', 'CAREGIVER'),
(17, 'viet_physio',  '0908776611', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Bùi Quốc Việt', 'viet.physio@ecare.vn', 'CAREGIVER'),
(18, 'thuy_nurse',   '0932114455', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Nguyễn Thị Bích Thúy', 'thuy.nurse@ecare.vn', 'CAREGIVER'),
(19, 'ha_care',      '0971239876', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Trần Thị Thu Hà', 'ha.care@ecare.vn', 'CAREGIVER'),
(20, 'tuan_care',    '0919882211', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Vũ Anh Tuấn', 'tuan.care@ecare.vn', 'CAREGIVER'),
(21, 'dung_care',    '0948773322', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Lê Thị Thùy Dung', 'dung.care@ecare.vn', 'CAREGIVER'),
(22, 'hanh_nurse',   '0963445511', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Phan Thị Hồng Hạnh', 'hanh.nurse@ecare.vn', 'CAREGIVER'),
(23, 'thanh_care',   '0985112299', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Đỗ Văn Thành', 'thanh.care@ecare.vn', 'CAREGIVER'),

-- Quản trị viên (Admins: 24 - 25)
(24, 'admin_system', '0900000001', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Quản Trị Viên Hệ Thống', 'admin@ecare.vn', 'ADMIN'),
(25, 'supervisor',   '0900000002', '$2a$12$L7UbQiMQXgGvzwDwediiGebE.ZH9wD.IErVFET/KQZPc4NPD/GkrK', 'Giám Sát Viên Ca Trực', 'supervisor@ecare.vn', 'ADMIN');

-- ------------------------------------------------------------------------------
-- 2. BẢNG QUẢN LÝ PHIÊN JWT & REFRESH TOKEN (USER_REFRESH_TOKENS) - 16 DÒNG
-- ------------------------------------------------------------------------------
INSERT INTO user_refresh_tokens (token_id, user_id, token_hash, is_revoked, expires_at) VALUES
(1,  1,  'rt_hash_live_user1_device_iphone14', FALSE, CURRENT_TIMESTAMP + INTERVAL '30 days'),
(2,  1,  'rt_hash_revoked_user1_old_session',  TRUE,  CURRENT_TIMESTAMP - INTERVAL '2 days'),
(3,  2,  'rt_hash_live_user2_android_s23',    FALSE, CURRENT_TIMESTAMP + INTERVAL '14 days'),
(4,  3,  'rt_hash_live_user3_ipad_pro',       FALSE, CURRENT_TIMESTAMP + INTERVAL '20 days'),
(5,  4,  'rt_hash_expired_user4_web_login',   FALSE, CURRENT_TIMESTAMP - INTERVAL '5 days'),
(6,  5,  'rt_hash_live_user5_iphone13',       FALSE, CURRENT_TIMESTAMP + INTERVAL '7 days'),
(7,  6,  'rt_hash_revoked_user6_logout',      TRUE,  CURRENT_TIMESTAMP + INTERVAL '10 days'),
(8,  7,  'rt_hash_live_user7_samsung',        FALSE, CURRENT_TIMESTAMP + INTERVAL '25 days'),
(9,  8,  'rt_hash_live_user8_xiaomi',         FALSE, CURRENT_TIMESTAMP + INTERVAL '30 days'),
(10, 9,  'rt_hash_live_tasker_mai_app',       FALSE, CURRENT_TIMESTAMP + INTERVAL '30 days'),
(11, 10, 'rt_hash_live_tasker_binh_app',      FALSE, CURRENT_TIMESTAMP + INTERVAL '15 days'),
(12, 11, 'rt_hash_live_tasker_lan_app',       FALSE, CURRENT_TIMESTAMP + INTERVAL '28 days'),
(13, 12, 'rt_hash_revoked_tasker_tuyet_change_pass', TRUE, CURRENT_TIMESTAMP + INTERVAL '3 days'),
(14, 13, 'rt_hash_live_tasker_long_app',      FALSE, CURRENT_TIMESTAMP + INTERVAL '12 days'),
(15, 17, 'rt_hash_live_tasker_viet_app',      FALSE, CURRENT_TIMESTAMP + INTERVAL '21 days'),
(16, 24, 'rt_hash_live_admin_dashboard',      FALSE, CURRENT_TIMESTAMP + INTERVAL '1 day');

-- ------------------------------------------------------------------------------
-- 3. BẢNG HỒ SƠ NGƯỜI CAO TUỔI (CARE_RECIPIENTS) - 16 DÒNG
-- ------------------------------------------------------------------------------
INSERT INTO care_recipients (recipient_id, customer_id, full_name, gender, year_of_birth, mobility_level, medical_notes, special_notes) VALUES
(1,  1, 'Lê Thị Hoa',        'FEMALE', 1946, 'ASSISTED',    'Cao huyết áp, thoái hóa khớp gối, khó khăn khi lên xuống cầu thang', 'Mẹ thích nghe nhạc Trịnh, ăn thanh đạm ít muối'),
(2,  1, 'Nguyễn Văn Tuấn',   'MALE',   1943, 'INDEPENDENT', 'Tiểu đường type 2 mức độ nhẹ, kiểm soát bằng thuốc viên', 'Bác còn minh mẫn, thích đánh cờ tướng buổi chiều'),
(3,  2, 'Trần Văn Hùng',     'MALE',   1950, 'INDEPENDENT', 'Rối loạn tiền đình, thỉnh thoảng chóng mặt khi đổi tư thế', 'Cần nhắc nhở uống nước lọc thường xuyên'),
(4,  2, 'Đặng Thị Lành',     'FEMALE', 1941, 'BEDRIDDEN',   'Liệt nửa người sau đột quỵ nhồi máu não, ăn qua ống sonde dạ dày', 'Cần lật trở người 2 tiếng/lần chống loét tì đè'),
(5,  3, 'Lê Văn Phát',       'MALE',   1948, 'ASSISTED',    'Di chứng tai biến nhẹ, tay phải yếu, đi lại cần gậy 3 chân', 'Cần hỗ trợ dìu đi vệ sinh và tắm rửa'),
(6,  3, 'Vũ Thị Chắt',       'FEMALE', 1937, 'BEDRIDDEN',   'Suy kiệt tuổi già, lú lẫn nhớ quên (Alzheimer giai đoạn muộn)', 'Cần người kiên nhẫn, nói chuyện nhẹ nhàng'),
(7,  4, 'Phạm Văn Dũng',     'MALE',   1952, 'ASSISTED',    'Sau phẫu thuật thay khớp háng nhân tạo, đang tập phục hồi chức năng', 'Tuân thủ bài tập vật lý trị liệu của bác sĩ Chợ Rẫy'),
(8,  4, 'Nguyễn Thị Mai Lan','FEMALE', 1955, 'INDEPENDENT', 'Huyết áp ổn định, mất ngủ kinh niên', 'Thích đi dạo công viên 15 phút vào 16:30'),
(9,  5, 'Bùi Văn Tiến',      'MALE',   1945, 'BEDRIDDEN',   'Hôn mê nhẹ sau xuất huyết não, thở oxy ngắt quãng, lưu ống thông tiểu', 'Theo dõi sát nhịp thở và nồng độ SpO2'),
(10, 5, 'Đỗ Thị Năm',        'FEMALE', 1951, 'INDEPENDENT', 'Đau dạ dày mạn tính, viêm phế quản co thắt', 'Tránh gió lùa, uống nước ấm'),
(11, 6, 'Đặng Văn Quý',      'MALE',   1940, 'BEDRIDDEN',   'Gãy cổ xương đùi bó bột nằm tại chỗ, viêm phổi ứ đọng', 'Cần vỗ rung long đờm và lau người bằng nước ấm'),
(12, 6, 'Trần Thị Lựu',      'FEMALE', 1944, 'ASSISTED',    'Bệnh Parkinson giai đoạn 3, run tay chân, bước đi dễ ngã', 'Luôn có người đi kèm sát bên cạnh'),
(13, 7, 'Võ Thành Đạt',      'MALE',   1949, 'ASSISTED',    'Suy tim độ 2, hay mệt khi gắng sức, phù nhẹ 2 mu bàn chân', 'Không cho ăn mặn, kiêng nước ngọt có gas'),
(14, 7, 'Lê Thị Nga',        'FEMALE', 1954, 'INDEPENDENT', 'Mắt kém do đục thủy tinh thể, thính lực giảm', 'Nói chậm, to rõ ràng trước mặt cụ'),
(15, 8, 'Đoàn Hữu Nghị',     'MALE',   1947, 'INDEPENDENT', 'Tiền đình, hay quên chìa khóa và kính lão', 'Thích xem thời sự lúc 19:00'),
(16, 8, 'Hồ Thị Mộng',       'FEMALE', 1942, 'ASSISTED',    'Thoát vị đĩa đệm cột sống thắt lưng, đau lưng nhiều khi đứng lâu', 'Cần hỗ trợ xoa bóp dầu ngải cứu vào buổi tối');

-- ------------------------------------------------------------------------------
-- 4. BẢNG HỒ SƠ CHĂM SÓC VIÊN (CAREGIVERS) - 15 DÒNG
-- ------------------------------------------------------------------------------
INSERT INTO caregivers (caregiver_id, user_id, experience_years, qualification, bio, is_available, avg_rating, total_jobs_done) VALUES
(1,  9,  5, 'Cử nhân Điều dưỡng ĐH Y Dược TP.HCM', '5 năm kinh nghiệm chăm sóc người cao tuổi sau tai biến và theo dõi tim mạch.', TRUE,  4.95, 185),
(2,  10, 4, 'Hộ lý BV Chợ Rẫy (Khoa Nội Tim mạch)', 'Chuyên môn đo sinh hiệu chuẩn xác, kiên nhẫn và chu đáo với các cụ nằm liệt giường.', TRUE,  4.90, 142),
(3,  11, 6, 'Cử nhân Điều dưỡng BV Đại học Y Dược', 'Từng tu nghiệp chăm sóc người già tại Nhật Bản (Kaigo), thành thạo kỹ thuật xoay trở.', TRUE,  5.00, 220),
(4,  12, 3, 'Chứng chỉ bCare Professional bTaskee', 'Kỹ năng giao tiếp nhẹ nhàng, xoa bóp bấm huyệt thư giãn cho người cao tuổi.', TRUE,  4.85, 96),
(5,  13, 7, 'Kỹ thuật viên Phục hồi chức năng BV 115', 'Chuyên hỗ trợ phục hồi vận động sau mổ xương đùi và di chứng đột quỵ não.', TRUE,  4.98, 260),
(6,  14, 2, 'Trung cấp Y tế (Chuyên ngành Hộ lý)', 'Tận tụy, trung thực, cẩn thận trong việc chia liều thuốc và hỗ trợ ăn uống.', TRUE,  4.80, 54),
(7,  15, 8, 'Y sĩ Đa khoa - Viện Lão khoa Trung ương', '8 năm chăm sóc chuyên sâu các bệnh lý lão khoa, xử lý tốt các tình huống tăng huyết áp đột ngột.', FALSE, 4.92, 310),
(8,  16, 3, 'Chứng chỉ Điều dưỡng Lão khoa Chữ thập đỏ', 'Yêu thương người già như người thân, khéo nấu các món cháo súp dinh dưỡng.', TRUE,  4.88, 88),
(9,  17, 10, 'Cử nhân Vật lý Trị liệu ĐH Y Hà Nội', '10 năm chuyên trị cứng khớp, co rút cơ và tập đi cho người bệnh cao tuổi.', TRUE,  4.99, 320),
(10, 18, 5, 'Điều dưỡng viên BV Nhân dân Gia Định', 'Am hiểu tâm lý người cao tuổi, có kinh nghiệm xử lý vết loét ép tì đè độ 1 và 2.', TRUE,  4.91, 165),
(11, 19, 2, 'Chứng chỉ Sơ cấp cứu & Chăm sóc Người già', 'Trẻ trung, nhiệt tình, sẵn sàng làm ca đêm và hỗ trợ tại bệnh viện.', TRUE,  4.75, 42),
(12, 20, 1, 'Học viên tốt nghiệp Lớp bCare Starter', 'Chăm chỉ, nhanh nhẹn, luôn tuân thủ đúng nội quy và thời gian ca trực.', TRUE,  5.00, 15),
(13, 21, 4, 'Điều dưỡng chuyên khoa Thần kinh BV Chợ Quán', 'Chuyên chăm sóc người bệnh Parkinson và sa sút trí tuệ Alzheimer.', FALSE, 4.86, 130),
(14, 22, 6, 'Cử nhân Điều dưỡng BV Hùng Vương', 'Tận tâm, có chứng chỉ dinh dưỡng lâm sàng cho bệnh nhân tiểu đường.', TRUE,  4.94, 205),
(15, 23, 0, 'Thành viên mới vượt qua kỳ thi sát hạch bTaskee', 'Hồ sơ lý lịch tư pháp trong sạch, đã qua đào tạo sơ cứu 100 giờ.', TRUE,  5.00, 0);

-- ------------------------------------------------------------------------------
-- 5. BẢNG ĐƠN ĐẶT LỊCH (BOOKINGS) - 18 DÒNG
-- ------------------------------------------------------------------------------
INSERT INTO bookings (
    booking_id, booking_code, customer_id, recipient_id, caregiver_id,
    service_type, location_type, address, address_detail,
    duration_hours, work_date, start_time, end_time,
    prefer_favorite_tasker, is_weekly_recurring, work_notes,
    contact_name, contact_phone, service_price, discount_amount, total_price,
<<<<<<< HEAD
    payment_status, status, check_in_time, check_out_time
=======
    payment_method, payment_status, status
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80
) VALUES
(1, 'BC261026-001', 1, 1, 1, 'HOURLY', 'HOME',
 '402B Lê Văn Lương, Phường Tân Hưng, Quận 7, TP.HCM', 'Chung cư Sunrise City, Tháp V6, Căn 12.04',
 4, CURRENT_DATE - INTERVAL '2 days', '14:00:00', '18:00:00',
 TRUE, FALSE, 'Nhà có nuôi mèo hiền. Cần theo dõi huyết áp lúc 15:00 và hỗ trợ mẹ đi lại nhẹ nhàng.',
<<<<<<< HEAD
 'Nhan Nguyen', '0978458129', 450000, 0, 450000, 'PAID', 'COMPLETED',
 (CURRENT_DATE - INTERVAL '2 days') + TIME '13:55:00', (CURRENT_DATE - INTERVAL '2 days') + TIME '18:05:00'),
=======
 'Nhan Nguyen', '0978458129', 450000, 0, 450000, 'BPAY', 'PAID', 'COMPLETED'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(2, 'BC261026-002', 2, 4, 2, 'HOURLY', 'HOSPITAL',
 '201B Nguyễn Chí Thanh, Phường 12, Quận 5, TP.HCM', 'Bệnh viện Chợ Rẫy, Lầu 4, Khoa Nội Thần Kinh, Giường 18',
 2, CURRENT_DATE - INTERVAL '2 days', '08:00:00', '10:00:00',
 FALSE, FALSE, 'Cụ nằm liệt, hỗ trợ thay tã dán và cho ăn súp dinh dưỡng qua ống sonde.',
<<<<<<< HEAD
 'Trần Thị Bích', '0912345678', 300000, 30000, 270000, 'PAID', 'COMPLETED',
 (CURRENT_DATE - INTERVAL '2 days') + TIME '07:58:00', (CURRENT_DATE - INTERVAL '2 days') + TIME '10:02:00'),
=======
 'Trần Thị Bích', '0912345678', 300000, 30000, 270000, 'VNPAY', 'PAID', 'COMPLETED'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(3, 'BC261026-003', 4, 7, 5, 'HOURLY', 'HOME',
 '120 Nguyễn Thị Thập, Khu đô thị Him Lam, Quận 7, TP.HCM', 'Nhà phố 3 tầng, bấm chuông số 2',
 6, CURRENT_DATE - INTERVAL '1 day', '08:00:00', '14:00:00',
 TRUE, TRUE, 'Dìu bác tập đi với khung tập và xoa bóp cơ chân tránh teo cơ.',
<<<<<<< HEAD
 'Phạm Minh Hoàng', '0903112233', 630000, 50000, 580000, 'PAID', 'COMPLETED',
 (CURRENT_DATE - INTERVAL '1 day') + TIME '07:55:00', (CURRENT_DATE - INTERVAL '1 day') + TIME '14:00:00'),
=======
 'Phạm Minh Hoàng', '0903112233', 630000, 50000, 580000, 'MOMO', 'PAID', 'COMPLETED'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(4, 'BC261026-004', 3, 6, 3, 'HOURLY', 'HOME',
 '56 Đường số 9, KDC Trung Sơn, Bình Chánh, TP.HCM', 'Biệt thự cổng màu trắng',
 8, CURRENT_DATE - INTERVAL '1 day', '08:00:00', '16:00:00',
 FALSE, TRUE, 'Bà nhớ nhớ quên quên, nhắc nhở uống thuốc tim đúng 9:00 sáng.',
<<<<<<< HEAD
 'Lê Văn Đức', '0987654321', 800000, 0, 800000, 'PAID', 'COMPLETED',
 (CURRENT_DATE - INTERVAL '1 day') + TIME '08:02:00', (CURRENT_DATE - INTERVAL '1 day') + TIME '16:05:00'),
=======
 'Lê Văn Đức', '0987654321', 800000, 0, 800000, 'CREDIT_CARD', 'PAID', 'COMPLETED'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(5, 'BC261026-005', 5, 9, 7, 'STAY_247', 'HOME',
 '88 Song Hành, Phường An Phú, TP. Thủ Đức, TP.HCM', 'Căn hộ chung cư Masteri An Phú, Block B, Phòng 1802',
 24, CURRENT_DATE - INTERVAL '3 days', '07:00:00', '07:00:00',
 TRUE, FALSE, 'Ca trực 24/24 theo dõi cụ ông thở oxy, gia đình chuẩn bị sẵn phòng nghỉ riêng cho Tasker.',
<<<<<<< HEAD
 'Nguyễn Thị Thu Hương', '0938445566', 1800000, 100000, 1700000, 'PAID', 'COMPLETED',
 (CURRENT_DATE - INTERVAL '3 days') + TIME '06:55:00', (CURRENT_DATE - INTERVAL '2 days') + TIME '07:00:00'),
=======
 'Nguyễn Thị Thu Hương', '0938445566', 1800000, 100000, 1700000, 'VNPAY', 'PAID', 'COMPLETED'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(6, 'BC261026-006', 7, 13, 10, 'MONTHLY_RECURRING', 'HOME',
 '15 Lê Duẩn, Phường Bến Nghé, Quận 1, TP.HCM', 'Căn hộ lầu 5, thang máy thẻ từ',
 4, CURRENT_DATE - INTERVAL '4 days', '07:30:00', '11:30:00',
 FALSE, TRUE, 'Gói chăm sóc định kỳ buổi sáng: đo huyết áp, chuẩn bị bữa sáng và dạo bộ.',
<<<<<<< HEAD
 'Võ Thị Kim Oanh', '0961223344', 450000, 0, 450000, 'PAID', 'COMPLETED',
 (CURRENT_DATE - INTERVAL '4 days') + TIME '07:25:00', (CURRENT_DATE - INTERVAL '4 days') + TIME '11:30:00'),
=======
 'Võ Thị Kim Oanh', '0961223344', 450000, 0, 450000, 'BPAY', 'PAID', 'COMPLETED'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(7, 'BC261026-007', 6, 11, 4, 'HOURLY', 'HOSPITAL',
 '215 Hồng Bàng, Phường 11, Quận 5, TP.HCM', 'BV Đại học Y Dược TP.HCM, Lầu 7, Khoa Chấn Thương Chỉnh Hình',
 6, CURRENT_DATE - INTERVAL '5 days', '12:00:00', '18:00:00',
 FALSE, FALSE, 'Cụ mới mổ gãy xương, hỗ trợ vệ sinh tại giường và canh bình truyền dịch.',
<<<<<<< HEAD
 'Đặng Thanh Sơn', '0947889900', 630000, 0, 630000, 'PAID', 'COMPLETED',
 (CURRENT_DATE - INTERVAL '5 days') + TIME '11:58:00', (CURRENT_DATE - INTERVAL '5 days') + TIME '18:02:00'),
=======
 'Đặng Thanh Sơn', '0947889900', 630000, 0, 630000, 'CASH', 'PAID', 'COMPLETED'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(8, 'BC261026-008', 8, 15, 8, 'HOURLY', 'HOME',
 '34 Mai Chí Thọ, Phường An Khánh, TP. Thủ Đức, TP.HCM', 'Chung cư New City, Tháp Babylon, Căn B-08.12',
 2, CURRENT_DATE - INTERVAL '6 days', '16:00:00', '18:00:00',
 TRUE, FALSE, 'Dìu cụ ông đi bộ trong khuôn viên chung cư và trò chuyện thư giãn.',
<<<<<<< HEAD
 'Đoàn Gia Huy', '0973556677', 300000, 0, 300000, 'PAID', 'COMPLETED',
 (CURRENT_DATE - INTERVAL '6 days') + TIME '15:56:00', (CURRENT_DATE - INTERVAL '6 days') + TIME '18:00:00'),
=======
 'Đoàn Gia Huy', '0973556677', 300000, 0, 300000, 'MOMO', 'PAID', 'COMPLETED'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(9, 'BC261026-009', 1, 1, 1, 'HOURLY', 'HOME',
 '402B Lê Văn Lương, Phường Tân Hưng, Quận 7, TP.HCM', 'Sunrise City Tháp V6 Căn 12.04',
 4, CURRENT_DATE, '14:00:00', '18:00:00',
 TRUE, TRUE, 'Ca chiều định kỳ, mẹ đã ăn trưa xong, cần đo huyết áp và cho uống thuốc lúc 15h.',
<<<<<<< HEAD
 'Nhan Nguyen', '0978458129', 450000, 0, 450000, 'PAID', 'IN_PROGRESS',
 CURRENT_DATE + TIME '13:57:00', NULL),
=======
 'Nhan Nguyen', '0978458129', 450000, 0, 450000, 'BPAY', 'PAID', 'IN_PROGRESS'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(10, 'BC261026-010', 2, 4, 2, 'HOURLY', 'HOSPITAL',
 '201B Nguyễn Chí Thanh, Phường 12, Quận 5, TP.HCM', 'Bệnh viện Chợ Rẫy, Lầu 4 Phòng 402',
 8, CURRENT_DATE, '08:00:00', '16:00:00',
 FALSE, FALSE, 'Ca trực ngày tại viện, hỗ trợ bác sĩ hút đờm và lau người chống sốt.',
<<<<<<< HEAD
 'Trần Thị Bích', '0912345678', 800000, 50000, 750000, 'PAID', 'IN_PROGRESS',
 CURRENT_DATE + TIME '08:05:00', NULL),
=======
 'Trần Thị Bích', '0912345678', 800000, 50000, 750000, 'VNPAY', 'PAID', 'IN_PROGRESS'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(11, 'BC261026-011', 4, 7, 5, 'HOURLY', 'HOME',
 '120 Nguyễn Thị Thập, Him Lam, Quận 7, TP.HCM', 'Nhà riêng tầng trệt',
 2, CURRENT_DATE, '15:00:00', '17:00:00',
 TRUE, FALSE, 'Tập cử động khớp gối và cơ tứ đầu đùi sau mổ.',
<<<<<<< HEAD
 'Phạm Minh Hoàng', '0903112233', 300000, 0, 300000, 'PAID', 'IN_PROGRESS',
 CURRENT_DATE + TIME '14:55:00', NULL),
=======
 'Phạm Minh Hoàng', '0903112233', 300000, 0, 300000, 'CREDIT_CARD', 'PAID', 'IN_PROGRESS'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(12, 'BC261026-012', 3, 5, 3, 'HOURLY', 'HOME',
 '56 Đường số 9, KDC Trung Sơn, Bình Chánh, TP.HCM', 'Khu biệt thự 56',
 4, CURRENT_DATE + INTERVAL '1 day', '08:30:00', '12:30:00',
 FALSE, FALSE, 'Tasker Lan đã tiếp nhận đơn, chuẩn bị đến hỗ trợ cụ ông.',
<<<<<<< HEAD
 'Lê Văn Đức', '0987654321', 450000, 0, 450000, 'UNPAID', 'ASSIGNED', NULL, NULL),
=======
 'Lê Văn Đức', '0987654321', 450000, 0, 450000, 'CASH', 'UNPAID', 'ASSIGNED'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(13, 'BC261026-013', 5, 10, 8, 'HOURLY', 'HOME',
 '88 Song Hành, Phường An Phú, TP. Thủ Đức, TP.HCM', 'Căn hộ Masteri An Phú B-1802',
 6, CURRENT_DATE + INTERVAL '2 days', '08:00:00', '14:00:00',
 TRUE, FALSE, 'Tasker Nga đã nhận việc, gia đình đã xác nhận qua điện thoại.',
<<<<<<< HEAD
 'Nguyễn Thị Thu Hương', '0938445566', 630000, 0, 630000, 'PAID', 'ASSIGNED', NULL, NULL),
=======
 'Nguyễn Thị Thu Hương', '0938445566', 630000, 0, 630000, 'VNPAY', 'PAID', 'ASSIGNED'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(14, 'BC261026-014', 6, 12, 9, 'STAY_247', 'HOME',
 '12 Phan Đình Phùng, Phường 2, Quận Phú Nhuận, TP.HCM', 'Nhà 2 mặt tiền hẻm lớn',
 24, CURRENT_DATE + INTERVAL '3 days', '08:00:00', '08:00:00',
 FALSE, FALSE, 'Chăm sóc cụ bà Parkinson ca 24 tiếng.',
<<<<<<< HEAD
 'Đặng Thanh Sơn', '0947889900', 1800000, 150000, 1650000, 'PAID', 'ASSIGNED', NULL, NULL),
=======
 'Đặng Thanh Sơn', '0947889900', 1800000, 150000, 1650000, 'BPAY', 'PAID', 'ASSIGNED'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(15, 'BC261026-015', 7, 14, NULL, 'HOURLY', 'HOME',
 '15 Lê Duẩn, Phường Bến Nghé, Quận 1, TP.HCM', 'Căn hộ số 504',
 4, CURRENT_DATE + INTERVAL '1 day', '13:30:00', '17:30:00',
 FALSE, FALSE, 'Cụ bà mắt kém, cần người đọc sách báo và phụ giúp chuẩn bị cơm chiều.',
<<<<<<< HEAD
 'Võ Thị Kim Oanh', '0961223344', 450000, 0, 450000, 'PAID', 'PENDING', NULL, NULL),
=======
 'Võ Thị Kim Oanh', '0961223344', 450000, 0, 450000, 'VNPAY', 'PAID', 'PENDING'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(16, 'BC261026-016', 1, 2, NULL, 'HOURLY', 'HOME',
 '402B Lê Văn Lương, Phường Tân Hưng, Quận 7, TP.HCM', 'Sunrise City Tháp V6 Căn 12.04',
 2, CURRENT_DATE + INTERVAL '2 days', '16:00:00', '18:00:00',
 TRUE, FALSE, 'Đi bộ dạo mát cùng bác Tuấn, ưu tiên Tasker từng chăm người cao tuổi.',
<<<<<<< HEAD
 'Nhan Nguyen', '0978458129', 300000, 0, 300000, 'PAID', 'PENDING', NULL, NULL),
=======
 'Nhan Nguyen', '0978458129', 300000, 0, 300000, 'BPAY', 'PAID', 'PENDING'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(17, 'BC261026-017', 8, 16, NULL, 'HOURLY', 'HOSPITAL',
 '110 Trần Hưng Đạo, Phường 7, Quận 5, TP.HCM', 'Bệnh viện Nguyễn Trãi, Khoa Lão',
 6, CURRENT_DATE + INTERVAL '1 day', '06:00:00', '12:00:00',
 FALSE, FALSE, 'Hỗ trợ người nhà trông cụ buổi sáng trong lúc đi làm thủ tục bảo hiểm.',
<<<<<<< HEAD
 'Đoàn Gia Huy', '0973556677', 630000, 0, 630000, 'UNPAID', 'PENDING', NULL, NULL),
=======
 'Đoàn Gia Huy', '0973556677', 630000, 0, 630000, 'CASH', 'UNPAID', 'PENDING'),
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80

(18, 'BC261026-018', 2, 3, NULL, 'HOURLY', 'HOME',
 '72 Nguyễn Huệ, Phường Bến Nghé, Quận 1, TP.HCM', 'Căn hộ lầu 3',
 4, CURRENT_DATE - INTERVAL '3 days', '09:00:00', '13:00:00',
 FALSE, FALSE, 'Gia đình có việc về quê đột xuất nên xin hủy ca làm việc.',
<<<<<<< HEAD
 'Trần Thị Bích', '0912345678', 450000, 0, 450000, 'REFUNDED', 'CANCELLED', NULL, NULL);

-- ------------------------------------------------------------------------------
-- 6. BẢNG LỊCH SỬ GIAO DỊCH THANH TOÁN (PAYMENTS) - 19 DÒNG
-- Phủ đủ trạng thái: SUCCESS, PENDING, FAILED (thử thanh toán lại), REFUNDED
-- ------------------------------------------------------------------------------
INSERT INTO payments (payment_id, booking_id, transaction_code, amount, payment_method, status, paid_at, gateway_response) VALUES
(1,  1,  'TXN-BPAY-001',  450000,  'BPAY',        'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '2 days', '{"code":"00","message":"Success"}'),
(2,  2,  'TXN-VNPAY-002A', 270000, 'VNPAY',       'FAILED',   NULL,                                    '{"code":"51","message":"Insufficient balance"}'),
(3,  2,  'TXN-VNPAY-002B', 270000, 'VNPAY',       'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '2 days', '{"code":"00","message":"Success"}'),
(4,  3,  'TXN-MOMO-003',  580000,  'MOMO',        'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '1 day',  '{"resultCode":0,"message":"Successful"}'),
(5,  4,  'TXN-CARD-004',  800000,  'CREDIT_CARD', 'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '1 day',  '{"status":"CAPTURED"}'),
(6,  5,  'TXN-VNPAY-005', 1700000, 'VNPAY',       'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '3 days', '{"code":"00","message":"Success"}'),
(7,  6,  'TXN-BPAY-006',  450000,  'BPAY',        'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '4 days', '{"code":"00","message":"Success"}'),
(8,  7,  'TXN-CASH-007',  630000,  'CASH',        'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '5 days', '{"note":"Tasker collected cash"}'),
(9,  8,  'TXN-MOMO-008',  300000,  'MOMO',        'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '6 days', '{"resultCode":0,"message":"Successful"}'),
(10, 9,  'TXN-BPAY-009',  450000,  'BPAY',        'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '3 hours','{"code":"00","message":"Success"}'),
(11, 10, 'TXN-VNPAY-010', 750000,  'VNPAY',       'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '5 hours','{"code":"00","message":"Success"}'),
(12, 11, 'TXN-CARD-011',  300000,  'CREDIT_CARD', 'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '2 hours','{"status":"CAPTURED"}'),
(13, 12, 'TXN-CASH-012',  450000,  'CASH',        'PENDING',  NULL,                                    NULL),
(14, 13, 'TXN-VNPAY-013', 630000,  'VNPAY',       'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '1 hour', '{"code":"00","message":"Success"}'),
(15, 14, 'TXN-BPAY-014',  1650000, 'BPAY',        'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '1 hour', '{"code":"00","message":"Success"}'),
(16, 15, 'TXN-VNPAY-015', 450000,  'VNPAY',       'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '30 minutes', '{"code":"00","message":"Success"}'),
(17, 16, 'TXN-BPAY-016',  300000,  'BPAY',        'SUCCESS',  CURRENT_TIMESTAMP - INTERVAL '20 minutes', '{"code":"00","message":"Success"}'),
(18, 17, 'TXN-CASH-017',  630000,  'CASH',        'PENDING',  NULL,                                    NULL),
(19, 18, 'TXN-MOMO-018',  450000,  'MOMO',        'REFUNDED', CURRENT_TIMESTAMP - INTERVAL '3 days', '{"resultCode":0,"message":"Refunded to wallet"}');

-- ------------------------------------------------------------------------------
-- 7. BẢNG NHẬT KÝ CA LÀM VIỆC (CARE_LOGS) - 16 DÒNG
=======
 'Trần Thị Bích', '0912345678', 450000, 0, 450000, 'MOMO', 'REFUNDED', 'CANCELLED');

-- ------------------------------------------------------------------------------
-- 6. BẢNG NHẬT KÝ CA LÀM VIỆC (CARE_LOGS) - 16 DÒNG
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80
-- ------------------------------------------------------------------------------
INSERT INTO care_logs (
    log_id, booking_id, blood_pressure_sys, blood_pressure_dia,
    heart_rate, temperature, meals_intake, medications_given, caregiver_notes
) VALUES
(1,  1, 120, 80, 74, 36.6, 'Ăn hết 1 chén cháo yến mạch thịt bằm', 'Uống Amlodipine 5mg hạ áp lúc 14:30', 'Cụ vui vẻ, trò chuyện minh mẫn, đã dìu đi bộ 10 phút quanh phòng khách.'),
(2,  1, 125, 82, 76, 36.7, 'Uống 1 hộp sữa Ensure Gold 200ml', 'Uống Canxi Corbiere sau bữa xế', 'Đo lại huyết áp trước khi kết thúc ca, các chỉ số đều rất ổn định.'),
(3,  2, 135, 88, 82, 37.0, 'Bơm ăn qua ống sonde 250ml súp xay nhuyễn', 'Thuốc chống đông máu theo y lệnh bác sĩ trực', 'Vệ sinh hút đờm họng sạch sẽ, trở mình nghiêng sang trái kê gối chống loét.'),
(4,  3, 118, 76, 70, 36.5, 'Ăn 1 đĩa mì xào mềm rau củ', 'Uống Glucosamine và thuốc giảm đau khớp', 'Tập gập duỗi khớp gối 20 lần, bác hợp tác tốt và không kêu đau nhiều.'),
(5,  4, 145, 92, 88, 37.2, 'Ăn được 1/2 chén súp cua gà xé', 'Đã cho uống thuốc huyết áp theo toa bác sĩ', 'Huyết áp lúc đầu giờ hơi cao (145/92), sau khi nghỉ ngơi và uống thuốc đã hạ về 130/85.'),
(6,  4, 128, 80, 78, 36.8, 'Uống 1 ly nước cam ấm ít đường', 'Không dùng thêm thuốc', 'Bà ngủ trưa ngon giấc 1 tiếng rưỡi, thức dậy tỉnh táo không cáu gắt.'),
(7,  5, 130, 85, 80, 37.8, 'Ăn súp dinh dưỡng 200ml', 'Uống Paracetamol 500mg do sốt nhẹ 37.8 độ C', 'Cụ có dấu hiệu hâm hấp sốt, đã chườm ấm nách và bẹn, sau 45 phút hạ còn 37.1 độ C.'),
(8,  5, 122, 78, 75, 36.9, 'Ăn cháo hạt sen nấu thịt bò bằm', 'Thuốc tim mạch và trợ khí phế quản', 'Đêm cụ ngủ êm, oxy ngắt quãng duy trì SpO2 trên 96%.'),
(9,  6, 115, 75, 68, 36.5, 'Ăn 1 tô hủ tiếu mềm thịt nạc', 'Uống thuốc lợi tiểu nhẹ buổi sáng theo toa', 'Kiểm tra 2 mu bàn chân không thấy dấu hiệu phù, bác đi lại nhẹ nhàng.'),
(10, 7, 124, 80, 76, 36.7, 'Ăn súp bột dinh dưỡng', 'Kháng sinh chống viêm theo y tá khoa', 'Thay ga trải giường bệnh viện, vỗ lưng long đờm cho cụ.'),
(11, 8, 120, 78, 72, 36.6, 'Ăn 1 hũ sữa chua nha đam', 'Không có thuốc buổi chiều', 'Dìu bác đi dạo công viên New City 20 phút hít thở không khí trong lành.'),
(12, 9, 122, 80, 75, 36.8, 'Vừa ăn nửa chén súp gà nấm hương', 'Đã nhắc uống thuốc huyết áp lúc 14:30', 'Huyết áp đo lúc 14:15 là 122/80 mmHg, cụ đang ngồi nghe đài radio.'),
(13, 10, 130, 84, 80, 37.1, 'Cho ăn 200ml sữa qua sonde lúc 10:00', 'Đã báo y tá thay bình truyền nước biển', 'Bác sĩ khoa vừa đi buồng khám lại, đánh giá phản xạ nuốt đang tiến triển.'),
(14, 11, 119, 78, 74, 36.6, 'Uống 1 cốc nước dừa tươi', 'Không dùng thuốc trong ca tập', 'Đã tập bước đi với khung tập 15 mét, dáng đi thẳng hơn so với tuần trước.'),
(15, 3, 120, 80, 70, 36.5, 'Ăn bữa xế 1 quả chuối sứ chín', 'Không dùng thuốc', 'Xoa bóp bắp chân và lòng bàn chân giúp máu lưu thông tốt.'),
(16, 4, 125, 82, 76, 36.7, 'Uống 1 ly sữa đậu nành ấm', 'Thuốc thảo dược an thần buổi tối', 'Cụ bà nghe kể chuyện xưa và chuẩn bị đi ngủ.');

-- ------------------------------------------------------------------------------
<<<<<<< HEAD
-- 8. BẢNG ĐÁNH GIÁ CỦA KHÁCH HÀNG (REVIEWS) - 8 DÒNG (1 review / 1 booking COMPLETED)
=======
-- 7. BẢNG ĐÁNH GIÁ CỦA KHÁCH HÀNG (REVIEWS) - 8 DÒNG (1 review / 1 booking COMPLETED)
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80
-- ------------------------------------------------------------------------------
INSERT INTO reviews (review_id, booking_id, customer_id, caregiver_id, rating, comment) VALUES
(1,  1, 1, 1, 5, 'Chị Mai rất nhiệt tình, chu đáo và nhẹ nhàng. Mẹ mình rất quý chị, đo huyết áp chuẩn và ghi chép cẩn thận. Chắc chắn sẽ đặt lại chị vào các ca sau!'),
(2,  2, 2, 2, 5, 'Anh Bình chăm sóc ở bệnh viện rất có kinh nghiệm, xoay trở cụ nằm liệt không hề bị đau. Gia đình rất yên tâm khi giao người thân cho anh.'),
(3,  3, 4, 5, 5, 'Anh Long hướng dẫn tập đi rất bài bản, bác đỡ đau hơn nhiều sau ca tập. Đúng chuẩn kỹ thuật viên chuyên nghiệp của viện 115.'),
(4,  4, 3, 3, 4, 'Cô Lan chăm sóc tốt, đúng giờ và sạch sẽ. Điểm trừ nhỏ là nói chuyện hơi to một chút, cụ lớn tuổi đôi khi giật mình.'),
(5,  5, 5, 7, 5, 'Chú Đức trực 24/24 cực kỳ có tâm, xử lý kịp thời cơn sốt đêm của bố tôi. Cảm ơn ứng dụng đã có những điều dưỡng tay nghề cao như vậy.'),
(6,  6, 7, 10, 5, 'Chị Thúy làm việc rất ngăn nắp, đến sớm 5 phút mỗi sáng để chuẩn bị. Bác rất hợp tính với chị.'),
(7,  7, 6, 4, 4, 'Chị Tuyết chăm cụ ở viện cẩn thận, biết hỗ trợ y tá thay băng. Sẽ tiếp tục ủng hộ dịch vụ bCare.'),
(8,  8, 8, 8, 5, 'Bạn Nga nói chuyện lễ phép, dìu cụ đi dạo rất an toàn. Cụ về khen bạn suốt buổi tối.');

-- ------------------------------------------------------------------------------
<<<<<<< HEAD
-- 9. BẢNG THÔNG BÁO NGƯỜI DÙNG (NOTIFICATIONS) - 18 DÒNG
=======
-- 8. BẢNG THÔNG BÁO NGƯỜI DÙNG (NOTIFICATIONS) - 18 DÒNG
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80
-- ------------------------------------------------------------------------------
INSERT INTO notifications (notification_id, user_id, title, content, is_read) VALUES
(1,  1, 'Đã có Tasker nhận việc!', 'Tasker Nguyễn Thị Mai đã nhận ca chăm sóc mẹ bạn vào ngày 26/10 lúc 14:00.', TRUE),
(2,  1, 'Tasker đã có mặt tại điểm hẹn', 'Tasker Nguyễn Thị Mai đã check-in tại Sunrise City Tháp V6 lúc 13:55.', TRUE),
(3,  1, 'Cập nhật sinh hiệu mới 🩺', 'Tasker Mai vừa đo huyết áp cho mẹ bạn: 122/80 mmHg (Chỉ số tim mạch ổn định).', FALSE),
(4,  1, 'Ca làm việc đã hoàn thành 🎉', 'Ca chăm sóc #BC261026-001 đã kết thúc. Vui lòng để lại đánh giá cho Tasker Mai nhé!', FALSE),
(5,  1, 'Ưu đãi dịch vụ bCare', 'Bạn nhận được mã ECARE50 giảm 50.000đ cho đơn đặt lịch tiếp theo trong tuần này.', FALSE),
(6,  2, 'Cảnh báo huyết áp cần theo dõi', 'Cụ Lành có huyết áp đo lúc 08:30 là 135/88 mmHg. Tasker Bình đang theo dõi sát.', FALSE),
(7,  3, 'Nhắc lịch ca làm ngày mai', 'Bạn có ca hẹn chăm sóc vào 08:30 ngày mai với Tasker Lê Thị Ngọc Lan.', FALSE),
(8,  4, 'Thanh toán thành công 💳', 'Đơn việc #BC261026-003 đã được thanh toán 580.000đ qua ví MoMo thành công.', TRUE),
(9,  5, 'Hạ sốt thành công', 'Nhiệt độ của cụ Bùi Văn Tiến đã hạ từ 37.8°C xuống 37.1°C sau khi chườm ấm.', TRUE),
(10, 9, 'Bạn nhận được đánh giá 5 sao! ⭐', 'Khách hàng Nhan Nguyen gửi lời khen: "Chị Mai rất nhiệt tình, chu đáo và nhẹ nhàng".', TRUE),
(11, 9, 'Tiền công đã vào ví bPay 💰', 'Bạn đã nhận 450.000đ từ ca làm việc #BC261026-001 tại Sunrise City Quận 7.', TRUE),
(12, 9, 'Nhắc nhở bắt đầu ca trực', 'Ca làm tại Quận 7 của bạn sẽ bắt đầu sau 30 phút nữa. Hãy chuẩn bị đến đúng giờ nhé!', TRUE),
(13, 10, 'Đơn việc mới phù hợp gần bạn!', 'Có đơn chăm sóc người bệnh 8 giờ tại Bệnh viện Chợ Rẫy cần tiếp nhận lúc 08:00.', TRUE),
(14, 11, 'Nhận việc thành công', 'Bạn đã tiếp nhận thành công đơn việc #BC261026-012 tại KDC Trung Sơn.', TRUE),
(15, 12, 'Có đơn việc mới tại Quận 1', 'Đơn chăm sóc cụ bà 4 giờ tại Lê Duẩn Q1 đang chờ Tasker nhận việc.', FALSE),
(16, 13, 'Lịch tập phục hồi chức năng', 'Lịch tập vật lý trị liệu cho bác Dũng bắt đầu lúc 15:00 chiều nay.', TRUE),
(17, 23, 'Hồ sơ đã được phê duyệt ✅', 'Chúc mừng bạn đã hoàn thành khóa đào tạo bCare. Bạn có thể bắt đầu nhận việc ngay!', FALSE),
(18, 24, 'Báo cáo ca trực trong ngày', 'Hệ thống ghi nhận 18 đơn đặt lịch hôm nay: 3 đơn đang chạy, 8 đơn hoàn thành an toàn.', FALSE);

-- ------------------------------------------------------------------------------
-- CẬP NHẬT LẠI SEQUENCES CỦA POSTGRESQL
-- ------------------------------------------------------------------------------
SELECT setval('users_user_id_seq', (SELECT COALESCE(MAX(user_id), 1) FROM users));
SELECT setval('user_refresh_tokens_token_id_seq', (SELECT COALESCE(MAX(token_id), 1) FROM user_refresh_tokens));
SELECT setval('care_recipients_recipient_id_seq', (SELECT COALESCE(MAX(recipient_id), 1) FROM care_recipients));
SELECT setval('caregivers_caregiver_id_seq', (SELECT COALESCE(MAX(caregiver_id), 1) FROM caregivers));
SELECT setval('bookings_booking_id_seq', (SELECT COALESCE(MAX(booking_id), 1) FROM bookings));
<<<<<<< HEAD
SELECT setval('payments_payment_id_seq', (SELECT COALESCE(MAX(payment_id), 1) FROM payments));
=======
>>>>>>> e6b6409f61d32af235234f9668d7c52af18e7f80
SELECT setval('care_logs_log_id_seq', (SELECT COALESCE(MAX(log_id), 1) FROM care_logs));
SELECT setval('reviews_review_id_seq', (SELECT COALESCE(MAX(review_id), 1) FROM reviews));
SELECT setval('notifications_notification_id_seq', (SELECT COALESCE(MAX(notification_id), 1) FROM notifications));
