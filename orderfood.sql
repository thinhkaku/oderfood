-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 17, 2019 lúc 08:34 PM
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
(2, 1, 1, 1, '0000-00-00 00:00:00'),
(3, 5, 14, 1, '2019-04-16 10:57:32'),
(4, 1, 1, 1, '0000-00-00 00:00:00'),
(5, 1, 1, 1, '0000-00-00 00:00:00'),
(8, 1, 10, 1, '2019-04-16 11:24:41'),
(9, 3, 2, 1, '2019-04-16 11:33:50'),
(10, 3, 2, 1, '2019-04-16 11:37:43'),
(11, 5, 14, 1, '2019-04-16 11:39:22'),
(12, 1, 10, 1, '2019-04-16 11:46:37'),
(13, 5, 14, 1, '2019-04-16 11:51:54'),
(14, 20, 4, 1, '2019-04-16 11:54:03'),
(15, 3, 2, 1, '2019-04-16 11:57:29'),
(16, 2, 12, 1, '2019-04-16 11:58:06'),
(17, 6, 1, 1, '2019-04-17 12:03:41'),
(18, 6, 2, 1, '2019-04-17 12:04:17'),
(19, 11, 5, 1, '2019-04-17 10:47:08');

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
(2175, 2, 3, 1, '0000-00-00 00:00:00'),
(2176, 9, 1, 0, '2019-04-16 11:33:51'),
(2177, 10, 1, 0, '2019-04-16 11:37:43'),
(2178, 2, 3, 1, '0000-00-00 00:00:00'),
(2179, 11, 1, 1, '2019-04-16 11:39:22'),
(2180, 11, 2, 3, '2019-04-16 11:39:22'),
(2181, 11, 3, 4, '2019-04-16 11:39:22'),
(2182, 12, 2, 2, '2019-04-16 11:46:38'),
(2183, 12, 1, 4, '2019-04-16 11:46:38'),
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
(2199, 19, 4, 4, '2019-04-17 10:47:08');

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
(21, 'th', 'a', 'a', '1553868870364tccktlcimagesges.jpg', '4', 'a', 'thinhkaku.3k51@gmail.com ', '1997-11-30', 1, 90000, '2019-03-21', '0002-11-30');

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
(11, 4, 8, 2, '2019-04-17 11:12:01', 0),
(13, 2, 8, 1, '2019-04-18 12:50:39', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `mytable`
--

CREATE TABLE `mytable` (
  `number` int(11) NOT NULL,
  `numpeople` int(11) NOT NULL,
  `check_tb` int(11) NOT NULL,
  `note` text COLLATE utf8_unicode_ci NOT NULL,
  `time_check` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `mytable`
--

INSERT INTO `mytable` (`number`, `numpeople`, `check_tb`, `note`, `time_check`) VALUES
(1, 0, 0, '', '2019-04-16 11:46:38'),
(2, 0, 0, '', '2019-04-16 11:58:06'),
(3, 0, 0, '', '2019-04-16 11:57:29'),
(4, 12, 0, '', '0000-00-00 00:00:00'),
(5, 2, 0, '', '0000-00-00 00:00:00'),
(6, 0, 0, '', '2019-04-17 12:04:18'),
(7, 0, 0, '', '0000-00-00 00:00:00'),
(8, 5, 1, '', '2019-04-17 11:12:01'),
(9, 2, 0, '', '0000-00-00 00:00:00'),
(10, 0, 0, '', '0000-00-00 00:00:00'),
(11, 0, 0, '', '2019-04-17 10:47:08'),
(12, 0, 0, '', '0000-00-00 00:00:00'),
(13, 0, 0, '', '0000-00-00 00:00:00'),
(14, 0, 0, '', '0000-00-00 00:00:00'),
(15, 0, 0, '', '0000-00-00 00:00:00'),
(16, 0, 0, '', '0000-00-00 00:00:00'),
(17, 0, 0, '', '0000-00-00 00:00:00'),
(18, 0, 0, '', '0000-00-00 00:00:00'),
(19, 0, 0, '', '0000-00-00 00:00:00'),
(20, 0, 0, '', '2019-04-16 11:54:03'),
(21, 0, 0, '', '0000-00-00 00:00:00'),
(22, 0, 0, '', '0000-00-00 00:00:00'),
(23, 0, 0, '', '0000-00-00 00:00:00'),
(24, 0, 0, '', '0000-00-00 00:00:00');

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
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `bill_detail`
--
ALTER TABLE `bill_detail`
  MODIFY `bill_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2200;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `menu`
--
ALTER TABLE `menu`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `myitem`
--
ALTER TABLE `myitem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

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
