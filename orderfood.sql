-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 23, 2019 lúc 08:23 PM
-- Phiên bản máy phục vụ: 10.1.38-MariaDB
-- Phiên bản PHP: 7.1.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `orderfood`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
--

CREATE TABLE `bill` (
  `bill_id` int(11) NOT NULL,
  `bill_number_tb` int(11) NOT NULL,
  `bill_number_people` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `bill_create_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `bill`
--

INSERT INTO `bill` (`bill_id`, `bill_number_tb`, `bill_number_people`, `employee_id`, `bill_create_date`) VALUES
(8, 1, 10, 1, '2019-04-16 11:24:41'),
(9, 3, 2, 1, '2019-04-16 11:33:50'),
(11, 5, 14, 1, '2019-04-16 11:39:22'),
(13, 5, 14, 1, '2019-04-16 11:51:54'),
(14, 20, 4, 1, '2019-04-16 11:54:03'),
(15, 3, 2, 1, '2019-04-16 11:57:29'),
(16, 2, 12, 1, '2019-04-16 11:58:06'),
(17, 6, 1, 1, '2019-04-17 12:03:41'),
(18, 6, 2, 1, '2019-04-17 12:04:17'),
(19, 11, 5, 1, '2019-04-17 10:47:08'),
(20, 8, 5, 1, '2019-04-22 04:48:50'),
(21, 5, 4, 1, '2019-04-24 01:19:09');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill_detail`
--

CREATE TABLE `bill_detail` (
  `bill_detail_id` int(11) NOT NULL,
  `bill_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `bill_detail_create_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `bill_detail`
--

INSERT INTO `bill_detail` (`bill_detail_id`, `bill_id`, `menu_id`, `count`, `bill_detail_create_date`) VALUES
(2176, 9, 1, 0, '2019-04-16 11:33:51'),
(2179, 11, 1, 1, '2019-04-16 11:39:22'),
(2180, 11, 2, 3, '2019-04-16 11:39:22'),
(2181, 11, 3, 4, '2019-04-16 11:39:22'),
(2184, 13, 1, 1, '2019-04-16 11:51:54'),
(2185, 13, 2, 3, '2019-04-16 11:51:54'),
(2186, 13, 3, 4, '2019-04-16 11:51:54'),
(2187, 14, 1, 1, '2019-04-16 11:54:03'),
(2188, 15, 1, 1, '2019-04-16 11:57:29'),
(2189, 16, 1, 2, '2019-04-16 11:58:06'),
(2190, 16, 2, 3, '2019-04-16 11:58:06'),
(2191, 16, 3, 2, '2019-04-16 11:58:06'),
(2192, 16, 4, 1, '2019-04-16 11:58:06'),
(2193, 17, 1, 5, '2019-04-17 12:03:41'),
(2194, 18, 1, 4, '2019-04-17 12:04:18'),
(2195, 18, 2, 5, '2019-04-17 12:04:18'),
(2196, 19, 1, 5, '2019-04-17 10:47:08'),
(2197, 19, 2, 2, '2019-04-17 10:47:08'),
(2198, 19, 3, 1, '2019-04-17 10:47:08'),
(2199, 19, 4, 4, '2019-04-17 10:47:08'),
(2200, 20, 1, 1, '2019-04-22 04:48:50'),
(2201, 20, 2, 1, '2019-04-22 04:48:50'),
(2202, 20, 4, 2, '2019-04-22 04:48:50'),
(2203, 21, 1, 5, '2019-04-24 01:19:10'),
(2204, 21, 2, 3, '2019-04-24 01:19:10');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhmuc`
--

CREATE TABLE `danhmuc` (
  `MaDanhMuc` int(11) NOT NULL,
  `TenDanhMuc` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `danhmuc`
--

INSERT INTO `danhmuc` (`MaDanhMuc`, `TenDanhMuc`) VALUES
(1, 'Iphone'),
(2, 'SamSung'),
(3, 'Oppo'),
(4, 'Nokia'),
(5, 'Xiaomi');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(10) NOT NULL,
  `employee_username` text COLLATE utf8_unicode_ci NOT NULL,
  `employee_pass` text COLLATE utf8_unicode_ci NOT NULL,
  `employee_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `employee_image` text COLLATE utf8_unicode_ci NOT NULL,
  `employee_phone` text COLLATE utf8_unicode_ci NOT NULL,
  `employee_address` text COLLATE utf8_unicode_ci NOT NULL,
  `employee_email` text COLLATE utf8_unicode_ci NOT NULL,
  `employee_dateofbirth` date NOT NULL,
  `employee_position_id` int(10) NOT NULL,
  `employee_salary` int(10) NOT NULL,
  `employee_timestart` date NOT NULL,
  `employee_timefinish` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`employee_id`, `employee_username`, `employee_pass`, `employee_name`, `employee_image`, `employee_phone`, `employee_address`, `employee_email`, `employee_dateofbirth`, `employee_position_id`, `employee_salary`, `employee_timestart`, `employee_timefinish`) VALUES
(1, 'thinhkaku', '1', 'Nguyen Van BBB', '1553702573350etllkltimagesges.jpg', '0978664412', 'Ha Nam', 'nguyenvana@gmai.com', '1997-06-25', 1, 9000000, '0000-00-00', '0002-11-30'),
(2, 'tranhieu', '1', 'nguyen van b', '1553702573350etllkltimagesges.jpg', '012361469', 'Ha noi', 'thinhkaku.3k51@gmail.com ', '1997-11-30', 1, 40000, '2019-03-05', '0002-11-30'),
(3, 'thanhtam', '1', 'nguyen van b', '1553702573350etllkltimagesges.jpg', '012361469', 'Ha noi', 'thinhkaku.a3k51@gmail.com', '2019-03-05', 1, 40000, '2019-03-05', '0000-00-00'),
(10, 't', 't', 'NGUYỄN VĂN THỊNH', '1553702573350etllkltimagesges.jpg', '0971129897', 'Hà nam', 'thinhkaku.a3k51@gmail.com', '2019-03-21', 1, 90000, '2019-03-21', '0002-11-30'),
(21, 'th', 'a', 'a', '1553868870364tccktlcimagesges.jpg', '4', 'a', 'thinhkaku.3k51@gmail.com ', '1997-11-30', 1, 90000, '2019-03-21', '0002-11-30'),
(22, 'thtt', 't', 'nhưng ', '1555864600303atgllaaimagesges.jpg', '0971129897', 'hạhhjssjsjsn', '828272828', '1997-06-22', 1, 0, '2019-04-21', '0000-00-00'),
(23, 'jhdjjs', 'ddfff', 'fff', '1555864823763tellclgimagesges.jpg', '097119897', 'cđ', '5776777', '1997-06-25', 1, 0, '2019-04-21', '0000-00-00'),
(24, 'thsjssj', 'hsjnss', 'ssndkhsns', '1555865364069teckakcimagesges.jpg', '0971129897', 'gsjjajaj', 'thinhkaku_1997@yahoo.com.vn', '1997-06-25', 1, 500000, '2019-04-21', '0000-00-00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `finance`
--

CREATE TABLE `finance` (
  `finance_id` int(10) NOT NULL,
  `finance_note` text COLLATE utf8_unicode_ci NOT NULL,
  `finance_order_id` int(10) DEFAULT NULL,
  `finance_pay` int(10) NOT NULL,
  `finance_status` tinyint(4) NOT NULL,
  `finance_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `finance`
--

INSERT INTO `finance` (`finance_id`, `finance_note`, `finance_order_id`, `finance_pay`, `finance_status`, `finance_date`) VALUES
(1, 'Chu y ...', NULL, 20000, 1, '2019-03-05 00:00:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hang`
--

CREATE TABLE `hang` (
  `MaHang` int(11) NOT NULL,
  `TenHang` text COLLATE utf8_unicode_ci NOT NULL,
  `Gia` int(11) NOT NULL,
  `HinhAnh` text COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `MaDanhMuc` int(11) NOT NULL,
  `ManHinh` text COLLATE utf8_unicode_ci NOT NULL,
  `HeDieuHanh` text COLLATE utf8_unicode_ci NOT NULL,
  `CameraSau` text COLLATE utf8_unicode_ci NOT NULL,
  `CameraTruoc` text COLLATE utf8_unicode_ci NOT NULL,
  `CPU` text COLLATE utf8_unicode_ci NOT NULL,
  `RAM` text COLLATE utf8_unicode_ci NOT NULL,
  `BoNhoTrong` text COLLATE utf8_unicode_ci NOT NULL,
  `TheNho` text COLLATE utf8_unicode_ci NOT NULL,
  `TheSim` text COLLATE utf8_unicode_ci NOT NULL,
  `DungLuongPin` text COLLATE utf8_unicode_ci NOT NULL,
  `MoTa` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hang`
--

INSERT INTO `hang` (`MaHang`, `TenHang`, `Gia`, `HinhAnh`, `SoLuong`, `MaDanhMuc`, `ManHinh`, `HeDieuHanh`, `CameraSau`, `CameraTruoc`, `CPU`, `RAM`, `BoNhoTrong`, `TheNho`, `TheSim`, `DungLuongPin`, `MoTa`) VALUES
(1, 'iPhone Xs Max 512GB', 39990000, 'iphone-xs-max-512gb-gold-400x460.png', 500, 1, 'OLED, 6.5\", Super Retina', 'iOS 12', '2 camera 12 MP', '7 MP', '	Apple A12 Bionic 6 nhân', '	4 GB', '	512 GB', '	Không có', '	512 GB', '3174 mAh, có sạc nhanh', 'Là chiếc smartphone cao cấp nhất của Apple với mức giá khủng chưa từng có, bộ nhớ trong lên tới 512GB, iPhone XS Max 512GB - sở hữu cái tên khác biệt so với các thế hệ trước, trang bị tới 6.5 inch đầu tiên của hãng cùng các thiết kế cao cấp hiện đại từ chip A12 cho tới camera AI.'),
(2, 'iPhone Xs 64GB', 27990000, 'iphone-xs-gold-400x460.png', 1000, 1, 'OLED, 5.8\", Super Retina', '	iOS 12', '	2 camera 12 MP', '	7 MP', '	Apple A12 Bionic 6 nhân', '	4 GB', '64 GB', 'Không có', 'Nano SIM & eSIM, Hỗ trợ 4G', '2658 mAh, có sạc nhanh', 'Đến hẹn lại lên, năm nay Apple giới thiệu tới người dùng thế hệ tiếp theo với 3 phiên bản, trong đó có cái tên iPhone Xs với những nâng cấp mạnh mẽ về phần cứng đến hiệu năng, màn hình cùng hàng loạt các trang bị cao cấp khác. '),
(3, 'iPhone 8 Plus 256GB', 25790000, 'iphone-8-plus-256gb-red-400x460.png', 550, 1, 'LED-backlit IPS LCD, 5.5\", Retina HD', 'iOS 12', '2 camera 12 MP', '	7 MP', 'Apple A11 Bionic 6 nhân', '3 GB', '256', 'Không có', '1 Nano SIM, Hỗ trợ 4G', '	2691 mAh, có sạc nhanh', 'Thừa hưởng những thiết kế đã đạt đến độ chuẩn mực, thế hệ iPhone 8 Plus thay đổi phong cách bóng bẩy hơn và bổ sung hàng loạt tính năng cao cấp cho trải nghiệm sử dụng vô cùng tuyệt vời.'),
(4, 'iPhone Xr 64GB', 19990000, 'iphone-xr-black-400x460.png', 200, 1, 'IPS LCD, 6.1\", IPS LCD, 16 triệu màu	', '	iOS 12', '	12 MP', '7 MP', 'Apple A12 Bionic 6 nhân', '	3 GB', '	64 GB', 'Không có', '1 Nano SIM, Hỗ trợ 4G', '	2942 mAh, có sạc nhanh', 'Là chiếc điện thoại iPhone có mức giá dễ chịu, phù hợp với nhiều khách hàng hơn, iPhone Xr vẫn được ưu ái trang bị chip Apple A12 mạnh mẽ, màn hình tai thỏ cùng khả năng chống nước chống bụi.'),
(5, 'iPhone 7 Plus 32GB', 13999000, 'iphone-7-plus-gold-400x460.png', 300, 1, 'LED-backlit IPS LCD, 5.5\", Retina HD', 'iOS 12', '2 camera 12 MP', '	7 MP', '	Apple A10 Fusion 4 nhân 64-bit', '3 GB', '	32 GB', 'Không có', '1 Nano SIM, Hỗ trợ 4G', '	2900 mAh', 'Mặc dù giữ nguyên vẻ bề ngoài so với dòng điện thoại iPhone đời trước, bù lại iPhone 7 Plus 32GB lại được trang bị nhiều nâng cấp đáng giá như camera kép đầu tiên cũng như cấu hình mạnh mẽ.'),
(6, 'iPhone 7 32GB', 12990000, 'iphone-7-32gb-den-400x460.png', 200, 1, 'LED-backlit IPS LCD, 4.7\", Retina HD', 'iOS 12', '12 MP', '7 MP', 'Apple A10 Fusion 4 nhân 64-bit', '2 GB', '	32 GB', 'Không có', '\r\n1 Nano SIM, Hỗ trợ 4G', '	1960 mAh', 'iPhone 7 32GB vẫn mang trên mình thiết kế quen thuộc của từ thời iPhone 6 nhưng có nhiều thay đổi lớn về phần cứng, dàn loa stereo và cấu hình cực mạnh.'),
(7, 'iPhone 6s Plus 32GB', 10990000, 'iphone-6s-plus-32gb-400x450-400x450.png', 350, 1, '	LED-backlit IPS LCD, 5.5\", Retina HD', 'iOS 12', '12 MP', '5 MP', 'Apple A9 2 nhân 64-bit', '	2 GB', '32 GB', 'Không có', '1 Nano SIM, Hỗ trợ 4G', '	2750 mAh', 'iPhone 6s Plus 32 GB là phiên bản nâng cấp hoàn hảo từ iPhone 6 Plus với nhiều tính năng mới hấp dẫn.'),
(8, 'iPhone 6 32GB', 6990000, 'iphone-6-32gb-gold-4-400x460.png', 250, 1, '	LED-backlit IPS LCD, 4.7\", Retina HD', '	iOS 12', '	8 MP', '1.2 MP', 'Apple A8 2 nhân 64-bit', '1 GB', '	32 GB', 'Không có', '\r\n1 Nano SIM, Hỗ trợ 4G', '	1810 mAh', 'iPhone 6 là một trong những smartphone được yêu thích nhất của Apple. Lắng nghe nhu cầu về thiết kế, khả năng lưu trữ và giá cả, iPhone 6 32GB được chính thức phân phối chính hãng tại Việt Nam hứa hẹn sẽ là một sản phẩm rất \"Hot\".'),
(9, 'iPhone Xr 128GB', 21990000, 'iphone-xr-128gb-red-400x460.png', 156, 1, '	IPS LCD, 6.1\", IPS LCD, 16 triệu màu', '	iOS 12', '12 MP', '7 MP', '	Apple A12 Bionic 6 nhân', '	3 GB', '128 GB', 'Không có', '1 Nano SIM, Hỗ trợ 4G', '	2942 mAh, có sạc nhanh', 'Được xem là phiên bản iPhone giá rẻ đầy hoàn hảo, iPhone Xr 128GB khiến người dùng có nhiều sự lựa chọn hơn về màu sắc đa dạng nhưng vẫn sở hữu cấu hình mạnh mẽ và thiết kế sang trọng.\r\nMàn hình tràn viền công nghệ LCD - True Tone\r\nThay vì sở hữu màn hình OLED truyền thống, chiếc smartphone này sở hữu màn hình LCD.\r\n\r\nBù lại với công nghệ True Tone cùng màn hình tràn viền rộng tới 6.1 inch, mọi trải nghiệm trên máy vẫn đem lại sự thích thú và hoàn hảo, như dòng cao cấp khác của Apple.'),
(10, 'iPhone Xr 256GB', 23990000, 'iphone-xr-256gb-white-400x460.png', 260, 1, '	IPS LCD, 6.1\", IPS LCD, 16 triệu màu', 'iOS 12', '	12 MP', '7 MP', 'Apple A12 Bionic 6 nhân', '	3 GB', '256 GB', 'Không có', '1 Nano SIM, Hỗ trợ 4G', '2942 mAh, có sạc nhanh', 'Ngoài các phiên bản như thường lệ mọi năm, vào đêm 12/9 theo giờ Việt Nam, Apple đã cho ra mắt thêm một phiên bản iPhone khác với tên gọi iPhone Xr, có mức giá dễ chịu hơn nhưng vẫn mang đầy đủ các tính năng cao cấp đầy hấp dẫn.\r\nMượt mà mọi trải nghiệm nhờ chip Apple A12\r\nVới mỗi lần ra mắt, Apple lại giới thiệu một con chip mới và Apple A12 Bionic là con chip đầu tiên sản xuất với tiến trình 7nm được tích hợp trên iPhone Xr.'),
(11, 'iPhone X 256GB', 27990000, 'iphone-x-256gb-silver-400x460.png', 150, 1, 'OLED, 5.8\", Super Retina', 'iOS 12', '	2 camera 12 MP', '	7 MP', '	Apple A11 Bionic 6 nhân', '	3 GB', '	256 GB', 'Không có', '\r\n1 Nano SIM, Hỗ trợ 4G', '	2716 mAh, có sạc nhanh', 'iPhone X được đã được Apple cho ra mắt ngày 12/9 vừa rồi đánh dấu chặng đường 10 năm lần đầu tiên iPhone ra đời. iPhone X mang trên mình thiết kế hoàn toàn mới với màn hình Super Retina viền cực mỏng và trang bị nhiều công nghệ hiện đại như nhận diện khuôn mặt Face ID, sạc pin nhanh và sạc không dây cùng khả năng chống nước bụi cao cấp.'),
(12, 'iPhone 6 16GB Space Gray', 9990000, '636172339622394948_apple-Iphone-6-gold-16gb.jpg', 120, 1, '	LED-backlit IPS LCD, 4.7\", Retina HD', 'iOS 12', '	8 MP', '1.2 MP', '	Apple A8 2 nhân 64-bit', '	1 GB', '	32 GB', 'Không có', '\r\n1 Nano SIM, Hỗ trợ 4G', '	1810 mAh', 'Apple iPhone 6S mang đến cho bạn những trải nghiệm khác biệt hoàn toàn so với những gì bạn đã biết trước đây. Chỉ với một lần nhấn duy nhất, 3D Touch cho phép bạn làm mọi thao tác mình cần. Bức ảnh động Live Photos lưu lại những kỷ niệm sâu sắc một cách sống động. Cùng với nhiều tính năng hấp dẫn khác đang chờ bạn khám phá trên iPhone 6'),
(13, 'iPhone 5 16GB', 5000000, 'iPhone-5-l.jpg', 199, 1, 'LED-backlit IPS LCD, 4\", DVGA', '	iOS 6', '	8 MP', '	1.2 MP', 'Apple A6 2 nhân 32-bit', '	1 GB', '	16 GB', 'Không có', '\r\n1 Nano SIM, Hỗ trợ 4G', '1440 mAh', 'Phone 5  - Thay đổi tất cả một lần nữa\r\nSau bao tháng ngày mong chờ, cả thế giới Công nghệ đã được đón nhận sự ra đời của siêu phẩm điện thoại thông minh iPhone 5, một trong những chiếc điện thoại được mong mỏi nhất năm 2012. Với những cải tiến mạnh mẽ cả về mặt thiết kế lẫn phần cứng, nên ngay từ khi lên kệ, iPhone 5 liên tục cháy hàng. iPhone 5 hứa hẹn sẽ tiếp tục khẳng định vị trí dẫn đầu trên thị trường Smartphone hiện nay.\r\n\r\nThiết kế mới nguyên khối mỏng nhất trong các siêu phẩm của Apple\r\nNgay từ buổi lễ ra mắt, các nhà thiết kế của Apple đã khẳng định đây là chiếc điện thoại mỏng nhất mà họ từng làm, iPhone 5 sở hữu những thông số về kích thước thật đáng kinh ngạc, máy mỏng chỉ 7.6 mm và trọng lượng chưa tới 112g, nếu đem ra so sánh với các smartphone hiện nay thì iPhone 5 thực sự là một trong những chiếc điện thoại mỏng nhất, nhẹ nhất.'),
(14, 'iPhone 5S 16GB', 5990000, 'iphone-5s-16gb-7-400x460.png', 600, 1, '	LED-backlit IPS LCD, 4\", DVGA', '	iOS 10', '8 MP', '1.2 MP', '	Apple A7 2 nhân 64-bit', '	1 GB', '	16 GB', 'Không có', '\r\n1 Nano SIM, Hỗ trợ 4G', '	1560 mAh', 'Thiết kế sang trọng, gia công tỉ mỉ, tích hợp cảm biến vân tay cao cấp hơn, camera cho hình ảnh đẹp và sáng hơn.\r\nSang trọng trong thiết kế\r\nThiết kế được giữ nguyên so với phiên bản đầu là iPhone 5, các đường cắt kim loại được gia công tỉ mỉ, sáng bóng nhìn rất đẹp mắt, máy có 3 màu là Xám đậm, Vàng đồng và màu Bạc.'),
(15, 'iPhone SE 16GB', 6190000, 'iphone-se-16gb-400x460.png', 203, 1, '	IPS LCD, 4\", Retina', '	iOS 9', '	12 MP', '	1.2 MP', '	Apple A9 2 nhân 64-bit', '	2 GB', '16 GB', 'Không có', '1 Nano SIM, Hỗ trợ 4G', '1642 mAh', 'Chiếc iPhone 4 inch mới với nhiều cải tiến với tên gọi iPhone SE.\r\niPhone SE giữ nguyên thiết kế tinh tế, sang trọng\r\niPhone SE vẫn giữ nguyên thiết kế trên người tiền nhiệm iPhone 5S và không có nhiều sự thay đổi. Khi đặt 2 thiết bị cạnh nhau thì bạn sẽ không thể nào phân biệt được đâu là iPhone 5S và đâu là iPhone SE.'),
(16, 'Samsung Galaxy A6+ (2018)', 5990000, 'samsung-galaxy-a6-plus-2018-tet-giatot-400x460.png', 300, 2, 'Super AMOLED, 6\", Full HD+', 'Android 8.0 (Oreo)', '16 MP và 5 MP (2 camera)', '	24 MP', 'Qualcomm Snapdragon 450 8 nhân 64-bit', '	4 GB', '32 GB', 'MicroSD, hỗ trợ tối đa 256 GB', '2 Nano SIM, Hỗ trợ 4G', '3500 mAh', 'Sau nhiều đồn đoán thì cuối cùng Samsung Galaxy A6+ (2018) đã chính thức được Samsung  giới thiệu với một chút đổi mới trong thiết kế cũng như về camera của máy.'),
(17, 'Samsung Galaxy Note 9 512GB', 28490000, 'samsung-galaxy-note-9-512gb-blue-400x460.png', 260, 2, '	Super AMOLED, 6.4\", Quad HD+ (2K+)', '	Android 8.1 (Oreo)', '	2 camera 12 MP', '	8 MP', '	Exynos 9810 8 nhân 64-bit', '	8 GB', '512 GB', 'MicroSD, hỗ trợ tối đa 512 GB', '2 SIM Nano (SIM 2 chung khe thẻ nhớ), Hỗ trợ 4G', '4000 mAh, có sạc nhanh', 'Sau thành công vang dội của Galaxy Note 8 thì Samsung chính thức giới thiệu phiên bản tiếp theo tới người dùng cái tên Samsung Galaxy Note 9 mang trong mình hàng hoạt các thay đổi đột phá với điểm nhấn đặc biệt đến từ chiếc bút S-Pen thần thánh cùng một viên pin dung lượng khổng lồ tới 4.000 mAh.\r\n'),
(18, 'Samsung Galaxy Note 9', 22990000, 'samsung-galaxy-note-9-black-400x460-400x460.png', 555, 2, 'Super AMOLED, 6.4\", Quad HD+ (2K+)', 'Android 8.1 (Oreo)', '2 camera 12 MP', '	8 MP', '	Exynos 9810 8 nhân 64-bit', '	6 GB', '128 GB', '	MicroSD, hỗ trợ tối đa 512 GB', '2 SIM Nano (SIM 2 chung khe thẻ nhớ), Hỗ trợ 4G', '	4000 mAh, có sạc nhanh', 'Mang lại sự cải tiến đặc biệt trong cây bút S-Pen, siêu phẩm Samsung Galaxy Note 9 còn sở hữu dung lượng pin khủng lên tới 4.000 mAh cùng hiệu năng mạnh mẽ vượt bậc, xứng đáng là một trong những chiếc điện thoại cao cấp nhất của Samsung.'),
(19, 'Samsung Galaxy S9+ 128GB', 20990000, 'samsung-galaxy-s9-plus-128gb-400x460-400x460.png', 700, 2, 'Super AMOLED, 6.2\", Quad HD+ (2K+)', '	Android 8.0 (Oreo)', '2 camera 12 MP', '8 MP', '	Exynos 9810 8 nhân 64-bit', '	6 GB', '	128 GB', '	MicroSD, hỗ trợ tối đa 400 GB', '2 SIM Nano (SIM 2 chung khe thẻ nhớ), Hỗ trợ 4G', '3500 mAh, có sạc nhanh', 'Samsung Galaxy S9 Plus 128Gb, siêu phẩm smartphone hàng đầu trong thế giới Android đã ra mắt với màn hình vô cực, camera chuyên nghiệp như máy ảnh và hàng loạt những tính năng cao cấp đầy hấp dẫn.'),
(20, 'Samsung Galaxy S8 Plus', 17990000, 'samsung-galaxy-s8-plus-tim-2-400x460.png', 650, 2, 'Super AMOLED, 6.2\", Quad HD+ (2K+)', '	Android 7.0 (Nougat)', '12 MP', '	8 MP', 'Exynos 8895 8 nhân 64-bit', '	4 GB', '	64 GB', 'MicroSD, hỗ trợ tối đa 256 GB', '2 SIM Nano (SIM 2 chung khe thẻ nhớ), Hỗ trợ 4G', '3500 mAh, có sạc nhanh', 'Galaxy S8 và Galaxy S8 Plus hiện đang là chuẩn mực smartphone về thiết kế trong tương lai. Sau nhiều ngày được sử dụng, mình xin chia sẻ những cảm nhận chi tiết nhất về chiếc Galaxy S8 Plus - chiếc điện thoại Samsung đang có doanh số đặt hàng khủng nhất hiện tại.'),
(21, 'Samsung Galaxy Note 8', 14990000, 'samsung-galaxy-note8-tet-giatot-400x460.png', 500, 2, 'Super AMOLED, 6.3\", Quad HD+ (2K+)', '	Android 7.1 (Nougat)', '	2 camera 12 MP', '	8 MP', 'Exynos 8895 8 nhân 64-bit', '	6 GB', '	64 GB', 'MicroSD, hỗ trợ tối đa 256 GB', '\r\n2 Nano SIM, Hỗ trợ 4G', '	3300 mAh, có sạc nhanh', 'Galaxy Note 8 là niềm hy vọng vực lại dòng Note danh tiếng của điện thoại Samsung với diện mạo nam tính, sang trọng. Máy trang bị camera kép xóa phông thời thượng, màn hình vô cực như trên S8 Plus, bút S Pen cùng nhiều tính năng mới và nhiều công nghệ được ưu ái.'),
(22, 'Samsung Galaxy A9 (2018)', 12490000, 'samsung-galaxy-a9-2018-blue-400x460.png', 350, 2, 'Super AMOLED, 6.3\", Full HD+', '	Android 8.0 (Oreo)', '24 MP, 10 MP, 8 MP và 5 MP (4 camera)', '24 MP', 'Qualcomm Snapdragon 660 8 nhân', '	6 GB', '128 GB', '	MicroSD, hỗ trợ tối đa 512 GB', '2 Nano SIM, Hỗ trợ 4G', '	3800 mAh, có sạc nhanh', 'Samsung Galaxy A9 (2018) là chiếc điện thoại đầu tiên của Samsung sở hữu hệ thống camera ấn tượng với 4 thấu kính cùng hàng loạt các nâng cấp đáng giá về cấu hình và tính năng hiện đại khác.'),
(23, 'Samsung Galaxy A8+ (2018)', 9490000, 'samsung-galaxy-a8-plus-2018-gold-400x460.png', 60, 2, '	Super AMOLED, 6\", Full HD+', '	Android 7.1 (Nougat)', '	16 MP', '	16 MP và 8 MP (2 camera)', '	Exynos 7885 8 nhân 64-bit', '	6 GB', '	64 GB', 'MicroSD, hỗ trợ tối đa 256 GB', '2 Nano SIM, Hỗ trợ 4G', '3500 mAh, có sạc nhanh', 'Samsung Galaxy A8+ (2018) là phiên bản lớn hơn của chiếc Samsung Galaxy A8 (2018) phù hợp với những bạn yêu thích điện thoại có màn hình lớn và thời lượng pin bền bỉ.\r\n'),
(24, 'Samsung Galaxy A8 Star', 8990000, 'samsung-galaxy-a8-star-tet-giatot-400x460-400x460.png', 197, 2, 'Super AMOLED, 6.3\", Full HD+', '	Android 8.0 (Oreo)', '	24 MP và 16 MP (2 camera)', '	24 MP', 'Qualcomm Snapdragon 660 8 nhân', '	4 GB', '	64 GB', 'MicroSD, hỗ trợ tối đa 400 GB', '\r\n2 Nano SIM, Hỗ trợ 4G', '	3700 mAh, có sạc nhanh', 'Samsung Galaxy A8 Star là một biến thể mới của dòng A trong gia đình Samsung với sự nâng cấp vượt trội về camera cũng như thay đổi trong thiết kế.\r\nThiết kế mang vẻ đẹp hiện đại\r\nĐiện thoại Samsung mới sở hữu thiết kế vẫn khá quen thuộc với thân hình có phần nam tính, mạnh mẽ và hiện đại, đan xen một chút mềm mại đến từ các góc cạnh và mặt lưng của máy.'),
(25, 'Samsung Galaxy A7 (2018) 128GB', 7990000, 'samsung-galaxy-a7-2018-128gb-black-400x460.png', 369, 2, 'Super AMOLED, 6.0\", Full HD+', '	Android 8.0 (Oreo)', '	24 MP, 8 MP và 5 MP (3 camera)', '24 MP', '	Exynos 7885 8 nhân 64-bit', '6 GB', '128 GB', '	MicroSD, hỗ trợ tối đa 512 GB', '2 Nano SIM, Hỗ trợ 4G', '3300 mAh', 'Được nâng cấp cả RAM và bộ nhớ trong, Samsung Galaxy A7 (2018) 128GB đem lại khả năng đa nhiệm, lưu trữ tốt hơn. Đồng thời đây cũng là chiếc smartphone đầu tiên của Samsung được trang bị tới 3 camera ở mặt sau và cảm biến vân tay cạnh bên.\r\nMượt mà hơn với nâng cấp RAM 6 GB và chip Exynos 7885\r\nSức mạnh cung cấp cho Galaxy A7 (2018) 128 GB chính là con chip Exynos 7885, đáp ứng mượt mà cho các nhu cầu giải trí hằng ngày của bạn.'),
(26, 'Samsung Galaxy J8', 6290000, 'samsung-galaxy-j8-400x460.png', 380, 2, 'Super AMOLED, 6.0\", HD+', 'Android 8.0 (Oreo)', '	16 MP và 5 MP (2 camera)', '16 MP', 'Qualcomm Snapdragon 450 8 nhân 64-bit', '3 GB', '	32 GB', 'MicroSD, hỗ trợ tối đa 256 GB', '\r\n2 Nano SIM, Hỗ trợ 4G', '	3500 mAh', 'Sau nhiều thông tin rò rỉ thì Samsung Galaxy J8 đã chính thức được ra mắt với nhiều trang bị cao cấp với màn hình tràn viền, camera kép xóa phông cùng một hiệu năng ổn định.'),
(27, 'OPPO Find X', 20990000, 'oppo-find-x-2-400x460-400x460.png', 398, 3, 'AMOLED, 6.42\", Full HD+', 'Android 8.1 (Oreo)', '	20 MP và 16 MP (2 camera)', '25 MP', 'Snapdragon 845 8 nhân', '8 GB', '256 GB', 'Không có', '2 Nano SIM, Hỗ trợ 4G', '	3730 mAh, có sạc nhanh', 'OPPO Find X tạo nên một cú hích lớn trong làng smartphone hiện nay khi mang trong mình nhiều tính năng đột phá mà nổi bật nhất đến từ thiết kế sáng tạo và một hiệu năng đỉnh cao.'),
(28, 'OPPO R17 Pro', 19990000, 'oppo-r17-pro-2-400x460.png', 60, 3, 'AMOLED, 6.4\", Full HD+', '	ColorOS 5.2 (Android 8.1)', '	20 MP, 12 MP và TOF 3D (3 camera)', '25 MP', '	Snapdragon 710 8 nhân 64-bit', '	8 GB', '128 GB', 'Không có', '2 Nano SIM, Hỗ trợ 4G', '	3700 mAh, có sạc nhanh', 'OPPO R17 Pro được xem là chiếc smartphone sự hiện thân của cái đẹp khi được khoác lên mình một thiết kế trẻ trung và hiện đại, cùng với đó là hàng loạt các trang bị cao cấp đi từ cấu hình cho đến camera.\r\nThiết kế thời trang, phá cách\r\nOPPO R17 Pro sở hữu cho mình lối thiết kế mới sẽ khiến bạn phải mê mẩn đến từ chiếc tai thỏ hình giọt nước vô cùng quyến rũ.'),
(29, 'OPPO F9 6GB', 8490000, 'oppo-f9-6gb-red-400x460.png', 360, 3, 'LTPS LCD, 6.3\", Full HD+', 'LTPS LCD, 6.3\", Full HD+', '16 MP và 2 MP (2 camera)', '25 MP', '	MediaTek Helio P60 8 nhân 64-bit', '6 GB', '64 GB', 'MicroSD, hỗ trợ tối đa 256 GB', '2 Nano SIM, Hỗ trợ 4G', '3500 mAh, có sạc nhanh', 'Là chiếc điện thoại OPPO được nâng cấp cấu hình, cụ thể là RAM lên tới 6 GB, OPPO F9 6GB còn trang bị nhiều tính năng đột phá như sở hữu công nghệ sạc VOOC mới nhất, mặt lưng chuyển màu độc đáo, màn hình tràn viền giọt nước và camera chụp chân dung tích hợp trí tuệ nhân tạo A.I hoàn hảo.\r\nCấu hình mạnh mẽ với RAM lên tới 6 GB và chip Helio P60'),
(30, 'OPPO F9', 6999000, 'oppo-f9-xanh-mtp-400x460.png', 50, 3, '	LTPS LCD, 6.3\", Full HD+', 'ColorOS 5.2 (Android 8.1)', '16 MP và 2 MP (2 camera)', '	25 MP', '	MediaTek Helio P60 8 nhân 64-bit', '4 GB', '64 GB', 'MicroSD, hỗ trợ tối đa 256 GB', '\r\n2 Nano SIM, Hỗ trợ 4G', '	3500 mAh, có sạc nhanh', 'Là chiếc điện thoại OPPO mới nhất sở hữu công nghệ sạc VOOC đột phá, OPPO F9 còn được ưu ái nhiều tính năng nổi trội như thiết kế mặt lưng chuyển màu độc đáo, màn hình tràn viền giọt nước và camera chụp chân dung tích hợp trí tuệ nhân tạo A.I hoàn hảo.\r\nSạc VOOC nhanh đột phá\r\nTrong những giây phút gay cấn như chơi game điện thoại báo hết pin hay sáng dậy đi làm nhưng phát hiện quên sạc pin thì bộ sạc của OPPO F9 sẽ đem lại sự cứu trợ ngay lập tức.\r\n\r\nVới sạc VOOC 5V/AA, tốc độ sạc trở nên nhanh chóng so với các bộ sạc thông thường.'),
(31, 'OPPO F7 128GB', 6490000, 'oppo-f7-128gb-den-400x460.png', 98, 3, 'LTPS LCD, 6.23\", Full HD+', 'ColorOS 5.0 (Android 8.1)', '16 MP', '25 MP', 'MediaTek Helio P60 8 nhân 64-bit', '	6 GB', '	128 GB', '	MicroSD, hỗ trợ tối đa 256 GB', '\r\n2 Nano SIM, Hỗ trợ 4G', '3400 mAh', 'Tiếp nối sự thành công của OPPO F5 thì OPPO tiếp tục giới thiệu OPPO F7 128GB với mức giá tương đương nhưng mang trong mình thiết kế hoàn toàn mới cũng nhiều cải tiến đáng giá.\r\nThiết kế tai thỏ độc đáo\r\nTiếp tục với trào lưu thiết kế \"tai thỏ\" được nhiều nhà sản xuất áp dụng thì điện thoại OPPO mới này cũng không ngoại lệ.'),
(32, 'OPPO A7', 5990000, 'oppo-a7-400x460.png', 78, 3, 'IPS LCD, 6.2\", HD+', 'ColorOS 5.2 (Android 8.1)', '13 MP và 2 MP (2 camera)', '	16 MP', 'Qualcomm Snapdragon 450 8 nhân 64-bit', '4 GB', '	64 GB', 'MicroSD, hỗ trợ tối đa 256 GB', '\r\n2 Nano SIM, Hỗ trợ 4G', '4230 mAh', 'OPPO A7, chiếc điện thoại được xem là sự hiện thân của cái đẹp, cái sáng tạo mà OPPO mang đến cho người dùng với mặt lưng được tô điểm bởi những họa tiết 3D thú vị cùng chiếc tai thỏ hình giọt nước đáng yêu.\r\nThiết kế cực chất với mặt lưng 3D dạng lưới\r\nOPPO A7 được tạo nên nhờ với một ngôn ngữ thiết kế mới lạ, bắt mắt khi có phần mặt lưng phản chiếu 3D vân lưới ánh sáng cực đẹp và thu hút.'),
(33, 'OPPO F7', 5490000, 'oppo-f7-bac-400x460.png', 90, 3, '	LTPS LCD, 6.23\", Full HD+', 'ColorOS 5.0 (Android 8.1)', '16 MP', '16 MP', 'MediaTek Helio P60 8 nhân 64-bit', '4 GB', '64 GB', 'MicroSD, hỗ trợ tối đa 256 GB', '2 Nano SIM, Hỗ trợ 4G', '3400 mAh', 'Tiếp nối sự thành công của OPPO F5 thì OPPO tiếp tục tung ra OPPO F7 với điểm nhấn vẫn là camera selfie và thiết kế \"tai thỏ độc đáo\".\r\nThiết kế tai thỏ độc đáo\r\nOPPO F7 vẫn đi theo thiết kế \"tai thỏ\" mà Apple đã từng làm trên iPhone X.'),
(34, 'OPPO A7 32GB', 4999000, 'oppo-a7-32gb-tet-docquyen-400x460.png', 63, 3, '	IPS LCD, 6.2\", HD+', 'ColorOS 5.2 (Android 8.1)', '13 MP và 2 MP (2 camera)', '	16 MP', 'Qualcomm Snapdragon 450 8 nhân 64-bit', '	3 GB', '	32 GB', 'MicroSD, hỗ trợ tối đa 256 GB', '2 Nano SIM, Hỗ trợ 4G', '	4230 mAh', 'OPPO luôn mang có sự thay đổi trong thiết kế mới và chiếc điện thoại OPPO A7 đã đem đến cho người dùng những màu sắc mới đáng để lựa chọn với mặt lưng 3D cùng màn hình giọt nước siêu tràn viền.\r\nẤn tượng với sắc màu mới cùng mặt lưng 3D dạng lưới\r\nChiếc điện thoại mới của OPPO đem lại sự bắt mắt khi có phần mặt lưng phản chiếu 3D vân lưới ánh sáng, cuốn hút và gây ấn tượng với người dùng ngay từ cái nhìn đầu tiên.'),
(35, 'OPPO A3s 32GB', 4690000, 'oppo-a3s-32gb-400x460.png', 87, 3, '	IPS LCD, 6.2\", HD+', 'Android 8.1 (Oreo)', '13 MP và 2 MP (2 camera)', '8 MP', 'Qualcomm Snapdragon 450 8 nhân 64-bit', '	3 GB', '	32 GB', '	MicroSD, hỗ trợ tối đa 256 GB', '2 Nano SIM, Hỗ trợ 4G', '	4230 mAh', 'OPPO A3s 32GB là một chiếc smartphone mới của OPPO sở hữu giá rẻ hiếm hoi nhưng vẫn được trang bị màn hình tai thỏ và camera kép xu thế của năm 2018.\r\nThiết kế thời trang với màu sắc sang trọng\r\nOPPO A3s sở hữu cho mình vẻ bề ngoài sang trọng và tinh tế không kém gì các thiết bị cao cấp.'),
(36, 'OPPO F7 Youth', 4490000, 'oppo-f7-youth-2-400x460.png', 150, 3, '	LTPS LCD, 6.0\", Full HD+', 'ColorOS 5.0 (Android 8.1)', '	13 MP', '8 MP', 'MediaTek Helio P60 8 nhân 64-bit', '4 GB', '64 GB', '	MicroSD, hỗ trợ tối đa 256 GB', '\r\n2 Nano SIM, Hỗ trợ 4G', '3410 mAh', 'OPPO F7 Youth là một phiên bản rút gọn của OPPO F7 không tai thỏ, không cảm biến vân tay, camera độ phân giải thấp hơn nhưng vẫn sở hữu cấu hình mạnh mẽ như người đàn anh trước đó.\r\nThiết kế bắt mắt\r\nTuy không còn phần \"tai thỏ\" theo xu thế nhưng tổng thể chiếc điện thoại OPPO này vẫn giữ được sự trẻ trung, thời trang của OPPO F7.'),
(37, 'OPPO A3s', 3290000, 'oppo-a3s-red-400x460.png', 360, 3, 'IPS LCD, 6.2\", HD+', 'Android 8.1 (Oreo)', '13 MP và 2 MP (2 camera)', '	8 MP', 'Qualcomm Snapdragon 450 8 nhân 64-bit', '	2 GB', '16 GB', '	MicroSD, hỗ trợ tối đa 256 GB', '2 Nano SIM, Hỗ trợ 4G', '4230 mAh', 'OPPO A3s 16GB là một chiếc smartphone giá rẻ hiếm hoi trên thị trường sở hữu những tính năng khá hấp dẫn trong năm 2018 đó là camera kép ở mặt lưng và màn hình tai thỏ ở mặt trước.\r\nMàn hình tai thỏ cao cấp\r\nĐiểm ấn tượng đầu tiên trên OPPO A3s chính là phần tai thỏ bên trên màn hình tương tự như chiếc iPhone X tới từ Apple.\r\nĐặc điểm nổi bật của OPPO A3s\r\nOPPO A3s 16GB là một chiếc smartphone giá rẻ hiếm hoi trên thị trường sở hữu những tính năng khá hấp dẫn trong năm 2018 đó là camera kép ở mặt lưng và màn hình tai thỏ ở mặt trước.\r\nMàn hình tai thỏ cao cấp\r\nĐiểm ấn tượng đầu tiên trên OPPO A3s chính là phần tai thỏ bên trên màn hình tương tự như chiếc iPhone X tới từ Apple.\r\n\r\nOPPO A3s có màn hình 6.2 inch độ phân giải HD+, tỷ lệ màn hình đạt 88.8% mang lại không gian lớn cho làm việc và giải trí.\r\n\r\nTấm nền IPS cho màu sắc trung thực, hiển thị hình ảnh sắc nét, độ tương phản cao. Bao phủ mặt trước là kính cường lực Corning Gorilla Glass 3 chống chịu va đập, trầy xước vượt trội.\r\nCamera kép xoá phông chất lượng\r\nChiếc điện thoại OPPO này sở hữu hệ thống camera kép độc đáo với độ phân giải của hai camera lần lượt là 13 MP và 2 MP.\r\n\r\nTrong điều kiện ánh sáng đầy đủ máy cho những bức ảnh xóa phông ở mức khá, màu sắc hài hòa rất phù hợp cho các bạn đăng facebook \"sống ảo\".\r\nCamera trước của máy cũng có độ phân giải lên tới 8 MP, hỗ trợ selfie góc rộng, được trang bị sẵn các chế độ làm đẹp hứa hẹn sẽ không làm phụ lòng những bạn trẻ thích tự sướng.\r\n\r\n\r\n'),
(38, 'OPPO A3s 16GB (1853)', 3290000, 'oppo-a3s-16gb-red-400x460.png', 39, 3, 'IPS LCD, 6.2\", HD+', 'Android 8.1 (Oreo)', '13 MP và 2 MP (2 camera)', '8 MP', 'Qualcomm Snapdragon 450 8 nhân 64-bit', '2 GB', '	16 GB', 'MicroSD, hỗ trợ tối đa 256 GB', '2 Nano SIM, Hỗ trợ 4G', '4230 mAh', 'OPPO A3s (1853) là phiên bản được thay đổi một chút trong thiết kế nhưng vẫn sở hữu các tính năng hấp dẫn với một mức giá rẻ dễ chịu.\r\nMàn hình tai thỏ theo xu hướng\r\nTựa như các dòng máy cao cấp, OPPO A3s gây ấn tượng với màn hình tai thỏ theo xu hướng hiện nay.\r\n\r\nMàn hình điện thoại OPPO A3s 16GB (1853)\r\n\r\nChiếc điện thoại OPPO có độ phân giải HD+ rộng 6.2, đem lại tỷ lệ màn hình tới 88.8% cho không gian trải nghiệm rộng rãi và xem phim ưng mắt.\r\n\r\nTấm nền IPS cho màu sắc trung thực, hiển thị hình ảnh sắc nét, độ tương phản cao. Bao phủ mặt trước là kính cường lực Corning Gorilla Glass 3 chống chịu va đập, trầy xước vượt trội.\r\n\r\nPin khủng tới 4230 mAh\r\nOPPO A3s được ưu ái trang bị viên pin khủng đến 4230 mAh, cùng với chế độ tiết kiệm pin, máy sẽ đảm bảo cho bạn có thời gian sử dụng lâu dài trong suốt cả ngày.\r\n\r\nTrải nghiệm điện thoại OPPO A3s 16GB (1853)\r\n\r\nNgoài ra thì OPPO cũng loại bỏ đi cảm biến vân tay và bạn có thể mở khóa OPPO A3s bằng khuôn mặt với tốc độ khá ấn tượng.\r\n\r\nCamera selfie nổi tiếng về làm đẹp\r\nOPPO A3s trang bị hệ thống camera kép có độ phân giải lần lượt 13 MP và 2 MP.\r\n\r\nTrong điều kiện ánh sáng đầy đủ máy cho những bức ảnh xóa phông ở mức khá, màu sắc hài hòa, sẵn sàng để người rất phù hợp cho các bạn đăng facebook \"sống ảo\".\r\n\r\nGiao diện camera điện thoại OPPO A3s 16GB (1853)\r\n\r\nCamera trước của máy cũng có độ phân giải lên tới 8 MP, hỗ trợ selfie góc rộng, được trang bị sẵn các chế độ làm đẹp hứa hẹn sẽ không làm phụ lòng những bạn trẻ thích tự sướng.\r\n\r\nẢnh selfie từ điện thoại OPPO A3s 16GB (1853)\r\n\r\nCấu hình vẫn chiến được game hot ở cấu hình thấp\r\nOPPO A3s được trang bị vi xử lý Snapdragon 450 với 8 nhân đảm bảo thỏa mãn đa số nhu cầu sử dụng hằng ngày và chơi game thông dụng như Liên Quân, Mobile Legend ở mức cấu hình thấp.'),
(39, 'Nokia 8.1', 7990000, 'nokia-81-silver-18thangbh-400x460.png', 169, 4, '	IPS LCD, 6.18”, Full HD+', 'Android 9 Pie (Android One)', '	12 MP và 13 MP (2 camera)', '20 MP', '	Snapdragon 710 8 nhân 64-bit', '	4 GB', '64 GB', 'MicroSD, hỗ trợ tối đa 400 GB', '2 SIM Nano (SIM 2 chung khe thẻ nhớ), Hỗ trợ 4G', '2 SIM Nano (SIM 2 chung khe thẻ nhớ), Hỗ trợ 4G', 'Đặc điểm nổi bật của Nokia 8.1\r\n\r\nTìm hiểu thêm\r\nTìm hiểu thêm\r\nBộ sản phẩm chuẩn: Sạc, Tai nghe, Cáp, Cây lấy sim\r\n\r\nNokia 8.1 (là phiên bản quốc tế của Nokia X7), chiếc smartphone trong phân khúc cận cao cấp vừa được trình làng, sở hữu một cấu hình cao cấp kèm theo đó là sự nâng cấp mạnh mẽ về hệ thống camera với hàng loạt các tính năng chụp ảnh hiện đại.\r\nThiết kế mạnh mẽ, sang trọng\r\nChiếc điện thoại Nokia mới sở hữu cho mình lối thiết kế quen thuộc nhưng vẫn đậm chất của hãng trong sự kết hợp giữa khung kim loại cùng 2 mặt kính cao cấp.\r\n\r\nThiết kế điện thoại Nokia 8.1 (Nokia X7) chính hãng\r\n\r\nĐi theo trào lưu mới nên việc được trang bị một chiếc tai thỏ ở không gian mặt trước sẽ là lựa chọn thông minh để thu hút người dùng.\r\n\r\nCảm giác cầm nắm mà Nokia 8.1 mang lại vẫn vô cùng chắc chắn và thoải mái nhờ máy được bo cong mềm mại ở các góc.\r\n\r\nMàn hình điện thoại Nokia 8.1 (Nokia X7) chính hãng\r\n\r\nMột điểm nhấn nhẹ ở mặt lưng khi mà cụm camera kép, cảm biến vân tay và logo Nokia được bố trí thẳng hàng với nhau trông cực kì tinh tế.\r\n\r\nCảm biến vân tay điện thoại Nokia 8.1 (Nokia X7) chính hãng\r\n\r\nVề không gian Nokia 8.1 đem đến cho bạn một màn hình rộng rãi với kích thước lên đến 6.18 inch đi kèm độ phân giải Full HD+ cùng 81% diện tích trải nghiệm.\r\n\r\nMàn hình điện thoại Nokia 8.1 (Nokia X7) chính hãng\r\n\r\nĐồng thời, nhờ công nghệ PureDisplay, bạn sẽ xem được video chất lượng HDR10 đầy khác biệt. Hơn nữa, màn hình sẽ tự động điều chỉnh để mang lại cho bạn trải nghiệm xem tốt nhất theo từng điều kiện ánh sáng cụ thể.\r\n\r\nNhờ vậy mà các hoạt động giải trí như: xem phim, lướt web hay chơi game trên màn hình này sẽ trở nên tối ưu và thoải mái hơn rất nhiều.\r\n\r\nMượt mà với hệ điều hành Android 9 Pie (Android One)\r\nSức mạnh trên Nokia 8.1 được thể hiện qua con chip Snapdragon 710 đi kèm 4 GB RAM cùng 64 GB bộ nhớ trong.\r\n\r\nĐây là con chip thế hệ mới trong phân khúc cận cao cấp có hiệu năng mạnh mẽ và ổn định để bạn có thể chiến các tựa game khủng nhất hiện nay một cách mượt mà như Liên Quân hay PUBG ở cấu hình cao.\r\n\r\nChơi game trên điện thoại Nokia 8.1 chính hãng\r\n\r\nChưa hết, Nokia 8.1 còn được tiếp thêm sức mạnh nhờ hệ điều hành Android One ít sự tùy biến giúp máy hoạt động mượt mà hơn cũng như được hỗ trợ cập nhật lâu dài.'),
(40, 'Nokia 7 plus', 6990000, 'nokia-7-plus-5-400x460.png', 300, 4, '	IPS LCD, 6\", Full HD+', '	Android 8.0 (Oreo)', '12 MP và 13 MP (2 camera)', '16 MP', 'Qualcomm Snapdragon 660 8 nhân', '4 GB', '	64 GB', 'MicroSD, hỗ trợ tối đa 256 GB', '2 SIM Nano (SIM 2 chung khe thẻ nhớ), Hỗ trợ 4G', '	3800 mAh, có sạc nhanh', 'Nokia 7 Plus là chiếc smartphone đầu tiên đánh dấu bước đi đầu tiên của Nokia vào thế giới màn hình tỉ lệ 18:9.\r\nThiết kế đẹp mắt\r\nNokia 7 Plus mang trong mình thiết kế mới mẻ nhưng vẫn có những thứ rất quen thuộc với người dùng đã quen với thương hiệu Nokia.'),
(41, 'Nokia 6.1 Plus', 5990000, 'nokia-61-plus-3-400x460.png', 500, 4, 'IPS LCD, 5.8\", Full HD+', 'Android 8 Oreo (Android One)', '16 MP và 5 MP (2 camera)', '	16 MP', 'Qualcomm Snapdragon 636 8 nhân', '	4 GB', '	64 GB', 'MicroSD, hỗ trợ tối đa 400 GB', '2 SIM Nano (SIM 2 chung khe thẻ nhớ), Hỗ trợ 4G', '	3060 mAh, có sạc nhanh', 'Nokia 6.1 Plus (hoặc tên khác Nokia X6) đã khiến người dùng trở nên phấn khích khi lột xác hoàn toàn trong thiết kế đến từ chiếc tai thỏ phá cách cũng như hiệu năng được cải tiến vượt bậc so với các đối thủ của nó.\r\nSự phá cách trong thiết kế\r\nSự kết hợp giữa khung kim loại và kính cao cấp đã tạo nên một chiếc điện thoại Nokia với một dáng vẻ trông khá sang trọng và uyển chuyển trong thân hình có phần hơi nữ tính.'),
(42, 'Nokia 6.1 64GB', 4999000, 'nokia-6-new-64gb-blue-400x460.png', 300, 4, '	IPS LCD, 5.5\", Full HD', '	Android 8.0 (Oreo)', '16 MP', '8 MP', 'Qualcomm Snapdragon 630 8 nhân 64-bit', '4 GB', '64 GB', 'MicroSD, hỗ trợ tối đa 128 GB', '2 SIM Nano (SIM 2 chung khe thẻ nhớ), Hỗ trợ 4G', '3000 mAh, có sạc nhanh', 'Sau nhiều rò rỉ thì cuối cùng chiếc Nokia 6.1 64GB cũng đã chính thức ra mắt với một thiết kế sang trọng nhưng vẫn có gì đó đáng tiếc cho một chiếc smartphone ra mắt vào năm 2018.'),
(43, 'Nokia 5.1 Plus', 4790000, 'nokia-51-plus-black-400x460.png', 246, 4, '	IPS LCD, 5.8\", HD+', 'Android One', '	13 MP và 5 MP (2 camera)', '	8 MP', '	MediaTek Helio P60 8 nhân 64-bit', '3 GB', '	32 GB', 'MicroSD, hỗ trợ tối đa 256 GB', '2 SIM Nano (SIM 2 chung khe thẻ nhớ), Hỗ trợ 4G', '	3060 mAh, có sạc nhanh', 'Sau Nokia 6.1 Plus thì Nokia tiếp tục tung ra thị trường chiếc smartphone thứ 2 của mình sở hữu màn hình \"tai thỏ\" với tên gọi Nokia 5.1 Plus (Nokia X5) với mức giá dễ chịu và hấp dẫn.\r\nThiết kế đẳng cấp sang trọng;\r\nChiếc điện thoại Nokia sở hữu thiết kế khung kim loại sang trọng và chắc chắn, đem lại vẻ nam tính và mạnh mẽ cho người dùng.'),
(44, 'Nokia 6.1', 3590000, 'nokia-6-2018-2-400x460.png', 348, 4, 'IPS LCD, 5.5\", Full HD', 'Android 8.0 (Oreo)', '	16 MP', '8 MP', 'Qualcomm Snapdragon 630 8 nhân', '	3 GB', '	32 GB', 'MicroSD, hỗ trợ tối đa 128 GB', '\r\n2 SIM Nano (SIM 2 chung khe thẻ nhớ), Hỗ trợ 4G', '	3000 mAh, có sạc nhanh', 'Đặc điểm nổi bật của Nokia 6.1\r\n\r\nBộ sản phẩm chuẩn: Hộp, Sạc, Tai nghe, Sách hướng dẫn, Cáp, Cây lấy sim\r\n\r\nSau nhiều rò rỉ thì cuối cùng chiếc Nokia 6.1 (Nokia 6 new) cũng đã chính thức ra mắt với một thiết kế sang trọng nhưng vẫn có gì đó đáng tiếc cho một chiếc smartphone ra mắt vào năm 2018.\r\n\r\nThiết kế truyền thống mạnh mẽ, chắc chắn\r\nMáy vẫn giữ thiết kế truyền thống với chất liệu kim loại cứng cáp và đẹp mắt.\r\n'),
(45, 'Nokia 3.1 32GB', 3290000, 'nokia-31-docquyen-400x460.png', 360, 4, 'IPS LCD, 5.2\", HD+', 'Android 8.0 (Oreo)', '13 MP', '8 MP', 'MediaTek MT6750N 8 nhân', '3 GB', '32 GB', 'MicroSD, hỗ trợ tối đa 128 GB', '2 Nano SIM, Hỗ trợ 4G', '	2990 mAh', 'Nokia 3.1 là chiếc smartphone giá rẻ kế thừa sự thành công của mẫu Nokia 3 - chiếc smartphone bán chạy nhất của hãng trong năm ngoái.\r\nThiết kế nam tính chắc chắn\r\nChiếc điện thoại Nokia này mang trong mình một ngôn ngữ thiết kế gần giống với những chiếc điện thoại flagship cách đây 1 hay 2 năm.'),
(46, 'Nokia 3.1 16GB', 2890000, 'nokia-31-16gb-400x460.png', 59, 4, 'IPS LCD, 5.2\", HD+', 'Android 8.0 (Oreo)', '13 MP', '	8 MP', 'MediaTek MT6750N 8 nhân', '	2 GB', '16 GB', 'MicroSD, hỗ trợ tối đa 128 GB', '2 Nano SIM, Hỗ trợ 4G', '2990 mAh', 'Nokia 3.1 là chiếc smartphone giá rẻ kế thừa sự thành công của mẫu Nokia 3 - chiếc smartphone bán chạy nhất của hãng trong năm ngoái.\r\nHiệu năng phù hợp với tầm giá\r\nHiệu năng của chiếc điện thoại Nokia này được thiết kế cao hơn 30% so với chiếc Nokia 3 năm ngoái.\r\n\r\nGiao diện Android điện thoại Nokia 3.1 16GB\r\n\r\nMáy sử dụng chip MediaTek MT6750N kết hợp với 2 GB RAM và bộ nhớ trong 16 GB.\r\n\r\nTất nhiên điện thoại Nokia 3.1 vẫn sẽ chạy Android One với sự cập nhật và hỗ trợ lâu dài.\r\n\r\nCamera có khả năng lấy nét nhanh\r\nNokia 3.1 sở hữu camera chính độ phân giải 13 MP với khả năng lấy nét khá nhanh, giúp bạn bắt trọn khoảnh khắc đẹp trong cuộc sống.\r\n\r\nGiao diện camera điện thoại Nokia 3.1 16GB\r\n\r\nCamera selfie độ phân giải 8 MP giúp bạn có thể selfie vui vẻ với bạn bè hay thực hiện các cuộc gọi video call.\r\n\r\nMàn hình tràn viền nhỏ gọn\r\nKhá bất ngờ khi màn hình của máy đạt 5.2 inch, nhỏ hơn cả mẫu Nokia 2.1 có mức giá rẻ hơn.\r\n\r\nMàn hình điện thoại Nokia 3.1 16GB\r\n\r\nNhưng đổi lại Nokia 3.1 lại có màn hình tràn viền tỉ lệ 18:9 cùng độ phân giải HD+ sắc nét.');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `menu`
--

CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `menu_image` text COLLATE utf8_unicode_ci NOT NULL,
  `menu_describle` text COLLATE utf8_unicode_ci NOT NULL,
  `menu_type_id` int(11) NOT NULL,
  `menu_price` int(11) NOT NULL,
  `menu_count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `menu`
--

INSERT INTO `menu` (`menu_id`, `menu_name`, `menu_image`, `menu_describle`, `menu_type_id`, `menu_price`, `menu_count`) VALUES
(1, 'Ghẹ Rang Muối', 'ghe-rang-muoi1493864860.jpg', 'mang den mon an ngon', 1, 90000, 0),
(2, 'Mực xào tỏi', 'images.jpg', '1', 1, 100000, 0),
(3, 'Hải sản', '7d9b9d06b256fd34019750ad5a6180c7.jpg', '1', 1, 150000, 0),
(4, 'Lẩu nướng', '10-mon-an-nhau1.jpg', '1', 2, 50000, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `myitem`
--

CREATE TABLE `myitem` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `numberTable` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `create_at` datetime NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `myitem`
--

INSERT INTO `myitem` (`id`, `menu_id`, `numberTable`, `count`, `create_at`, `status`) VALUES
(17, 1, 6, 2, '2019-04-24 01:20:52', 0),
(18, 2, 6, 3, '2019-04-24 01:20:52', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `mytable`
--

CREATE TABLE `mytable` (
  `number` int(11) NOT NULL,
  `numpeople` int(11) NOT NULL,
  `check_tb` int(11) NOT NULL,
  `note` text COLLATE utf8_unicode_ci NOT NULL,
  `time_check` datetime NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `mytable`
--

INSERT INTO `mytable` (`number`, `numpeople`, `check_tb`, `note`, `time_check`, `status`) VALUES
(1, 0, 0, '', '2019-04-16 11:46:38', 0),
(2, 0, 0, '', '2019-04-16 11:58:06', 0),
(3, 0, 0, '', '2019-04-16 11:57:29', 0),
(4, 12, 0, '', '0000-00-00 00:00:00', 0),
(5, 0, 0, '', '2019-04-24 01:19:10', 0),
(6, 6, 1, '', '2019-04-24 01:20:52', 1),
(7, 0, 0, '', '0000-00-00 00:00:00', 0),
(8, 0, 0, '', '2019-04-22 04:48:50', 0),
(9, 2, 0, '', '0000-00-00 00:00:00', 0),
(10, 0, 0, '', '0000-00-00 00:00:00', 0),
(11, 0, 0, '', '2019-04-17 10:47:08', 0),
(12, 0, 0, '', '0000-00-00 00:00:00', 0),
(13, 0, 0, '', '0000-00-00 00:00:00', 0),
(14, 0, 0, '', '0000-00-00 00:00:00', 0),
(15, 0, 0, '', '0000-00-00 00:00:00', 0),
(16, 0, 0, '', '0000-00-00 00:00:00', 0),
(17, 0, 0, '', '0000-00-00 00:00:00', 0),
(18, 0, 0, '', '0000-00-00 00:00:00', 0),
(19, 0, 0, '', '0000-00-00 00:00:00', 0),
(20, 0, 0, '', '2019-04-16 11:54:03', 0),
(21, 0, 0, '', '0000-00-00 00:00:00', 0),
(22, 0, 0, '', '0000-00-00 00:00:00', 0),
(23, 0, 0, '', '0000-00-00 00:00:00', 0),
(24, 0, 0, '', '0000-00-00 00:00:00', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `position`
--

CREATE TABLE `position` (
  `position_id` int(10) NOT NULL,
  `position_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `position`
--

INSERT INTO `position` (`position_id`, `position_name`) VALUES
(1, 'Nhan vien');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `type`
--

CREATE TABLE `type` (
  `type_id` int(10) NOT NULL,
  `type_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `group_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `type`
--

INSERT INTO `type` (`type_id`, `type_name`, `group_id`) VALUES
(1, 'Món chính', 0),
(2, 'Món tráng miệng', 0),
(3, 'Đồ uống', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_id`),
  ADD KEY `FK_BILL_EMPLOYEE` (`employee_id`);

--
-- Chỉ mục cho bảng `bill_detail`
--
ALTER TABLE `bill_detail`
  ADD PRIMARY KEY (`bill_detail_id`),
  ADD KEY `FK_BILL_DETAIL_ID` (`bill_id`),
  ADD KEY `FK_MENU_ID_BILL_DETAIL` (`menu_id`);

--
-- Chỉ mục cho bảng `danhmuc`
--
ALTER TABLE `danhmuc`
  ADD PRIMARY KEY (`MaDanhMuc`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`),
  ADD KEY `FK_POSITION` (`employee_position_id`);

--
-- Chỉ mục cho bảng `finance`
--
ALTER TABLE `finance`
  ADD PRIMARY KEY (`finance_id`);

--
-- Chỉ mục cho bảng `hang`
--
ALTER TABLE `hang`
  ADD PRIMARY KEY (`MaHang`),
  ADD KEY `FK_HANG_DANHMUC` (`MaDanhMuc`);

--
-- Chỉ mục cho bảng `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menu_id`),
  ADD KEY `FK_TYPE` (`menu_type_id`);

--
-- Chỉ mục cho bảng `myitem`
--
ALTER TABLE `myitem`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_MENUID` (`menu_id`),
  ADD KEY `FK_TABLE` (`numberTable`);

--
-- Chỉ mục cho bảng `mytable`
--
ALTER TABLE `mytable`
  ADD PRIMARY KEY (`number`);

--
-- Chỉ mục cho bảng `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`position_id`);

--
-- Chỉ mục cho bảng `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`type_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `bill`
--
ALTER TABLE `bill`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `bill_detail`
--
ALTER TABLE `bill_detail`
  MODIFY `bill_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2205;

--
-- AUTO_INCREMENT cho bảng `danhmuc`
--
ALTER TABLE `danhmuc`
  MODIFY `MaDanhMuc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `hang`
--
ALTER TABLE `hang`
  MODIFY `MaHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT cho bảng `menu`
--
ALTER TABLE `menu`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `myitem`
--
ALTER TABLE `myitem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT cho bảng `mytable`
--
ALTER TABLE `mytable`
  MODIFY `number` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `type`
--
ALTER TABLE `type`
  MODIFY `type_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `FK_BILL_EMPLOYEE` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);

--
-- Các ràng buộc cho bảng `bill_detail`
--
ALTER TABLE `bill_detail`
  ADD CONSTRAINT `FK_BILL_DETAIL_ID` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`bill_id`),
  ADD CONSTRAINT `FK_MENU_ID_BILL_DETAIL` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`);

--
-- Các ràng buộc cho bảng `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FK_POSITION` FOREIGN KEY (`employee_position_id`) REFERENCES `position` (`position_id`);

--
-- Các ràng buộc cho bảng `hang`
--
ALTER TABLE `hang`
  ADD CONSTRAINT `FK_HANG_DANHMUC` FOREIGN KEY (`MaDanhMuc`) REFERENCES `danhmuc` (`MaDanhMuc`);

--
-- Các ràng buộc cho bảng `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `FK_TYPE` FOREIGN KEY (`menu_type_id`) REFERENCES `type` (`type_id`);

--
-- Các ràng buộc cho bảng `myitem`
--
ALTER TABLE `myitem`
  ADD CONSTRAINT `FK_MENUID` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
  ADD CONSTRAINT `FK_TABLE` FOREIGN KEY (`numberTable`) REFERENCES `mytable` (`number`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
