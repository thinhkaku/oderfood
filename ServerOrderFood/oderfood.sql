-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 16, 2018 at 05:44 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banhangonline`
--

-- --------------------------------------------------------

--
-- Table structure for table `bangtin`
--

CREATE TABLE `bangtin` (
  `id` int(11) NOT NULL,
  `tenNguoiDang` varchar(20) NOT NULL,
  `noiDung` varchar(200) NOT NULL,
  `hinhAnh` varchar(100) NOT NULL,
  `thoiGian` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bangtin`
--

INSERT INTO `bangtin` (`id`, `tenNguoiDang`, `noiDung`, `hinhAnh`, `thoiGian`) VALUES
(1, 'Nguyễn Văn Thinh', 'Xin chào các bạn mình tên nguyễn văn thinh học lớp khoa học máy tính 4 trường đại học công nghiêp hà nội', '/image/staff/TbYzBygM1JpfPRAAAC1520249120667.png', '3/9/2018'),
(2, 'Nguyễn Văn Thịnh', 'For searching on listview check the link here.\r\nHow can I filter ListView data when typing on EditText in android\r\nIf you want make custom search you can use the code below\r\nActivity_main.xml', '/image/staff/TbYzBygM1JpfPRAAAC1520249120667.png', '4/9/2018'),
(3, 'nguyễn văn thinh', 'chào các bạn', '/image/staff/TbYzBygM1JpfPRAAAC1520249120667.png', '09-03-2018'),
(4, 'nguyễn văn thinh', 'chao cac ban', '/image/staff/TbYzBygM1JpfPRAAAC1520249120667.png', '09-03-2018'),
(5, 'nguyễn văn thinh', 'hello', '/image/staff/TbYzBygM1JpfPRAAAC1520249120667.png', '09-03-2018'),
(6, 'nguyễn văn thinh', 'hieu dau r', '/image/staff/TbYzBygM1JpfPRAAAC1520249120667.png', '09-03-2018'),
(7, 'nguyễn văn thinh', 'jhfgjh', '/image/staff/TbYzBygM1JpfPRAAAC1520249120667.png', '09-03-2018'),
(8, 'nguyễn văn thinh', 'ghgjhgjhgjgjhgjhgjhgjhgjhgjgjhgjgjhgjhgjhgjhgjhghjgjhgjhgjhgjhgjhgjhgjhgjhgjhgjhgjhgjhgjhgjhgjhgjhgjhgjgjhgjhgjhgjgjh', '/image/staff/TbYzBygM1JpfPRAAAC1520249120667.png', '09-03-2018');

-- --------------------------------------------------------

--
-- Table structure for table `danhsachban`
--

CREATE TABLE `danhsachban` (
  `tenBan` int(11) NOT NULL,
  `viTri` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `soGhe` int(11) NOT NULL,
  `ghiChu` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `tinhTrang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `danhsachban`
--

INSERT INTO `danhsachban` (`tenBan`, `viTri`, `soGhe`, `ghiChu`, `tinhTrang`) VALUES
(1, NULL, 3, NULL, 0),
(2, NULL, 3, NULL, 2),
(3, NULL, 3, NULL, 2),
(4, NULL, 3, NULL, 2),
(5, NULL, 3, NULL, 0),
(6, NULL, 3, NULL, 2),
(7, NULL, 6, NULL, 2),
(8, NULL, 6, NULL, 2),
(9, NULL, 5, NULL, 2);

-- --------------------------------------------------------

--
-- Table structure for table `danhsachmonan`
--

CREATE TABLE `danhsachmonan` (
  `tenMonAn` varchar(100) CHARACTER SET utf8 NOT NULL,
  `tenNhom` varchar(30) CHARACTER SET utf8 NOT NULL,
  `gia` varchar(100) NOT NULL,
  `tenDVTinh` varchar(50) CHARACTER SET utf8 NOT NULL,
  `tinhTrang` varchar(50) CHARACTER SET utf8 NOT NULL,
  `anhMonAn` text NOT NULL,
  `ghiChu` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `danhsachmonan`
--

INSERT INTO `danhsachmonan` (`tenMonAn`, `tenNhom`, `gia`, `tenDVTinh`, `tinhTrang`, `anhMonAn`, `ghiChu`) VALUES
('Canh bắp cải gói thịt', 'Món Canh', '20,000 VND', 'Tô', 'cohang', '/image/menu/food/5446.jpg', NULL),
('Canh gà cải bó xôi', 'Món Canh', '20,000 VND', 'Tô', 'cohang', '/image/menu/food/5358.jpg', NULL),
('Coca-Cola', 'Nước ngọt', '10,000 VND', 'Lon', 'cohang', '/image/menu/drink/cocacola-2.jpg', NULL),
('cơm', 'Món Canh', '20000 VND', 'Tô', 'cohang', '/image/staff/n7jsOfcRuiukDrAAAB1520649608253.png', '1'),
('Cơm chiên trái thơm', 'Món Cơm', '25,000 VND', 'Đĩa', 'cohang', '/image/menu/food/5368.jpg', NULL),
('Cơm tôm bí đỏ', 'Món Cơm', '25,000 VND', 'Đĩa', 'cohang', '/image/menu/food/5478.jpg', NULL),
('Louis Roederer Brut Premier', 'Rượu', '3,588,000 VND', 'Chai - 750ml', 'cohang', '/image/menu/drink/champagne-louis-roederer-brut-premier-nabuchodonosor.jpg', NULL),
('Pepsi', 'Nước ngọt', '10,000 VND', 'Lon', 'cohang', '/image/menu/drink/pepsi-master.png', NULL),
('Taittinger Magnum Brut Réserve', 'Rượu', '8,130,000 VND', 'Chai - 1500ml', 'cohang', '/image/menu/drink/taittinger-brut-reserve-.jpg', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `donvitinh`
--

CREATE TABLE `donvitinh` (
  `tenDVTinh` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `donvitinh`
--

INSERT INTO `donvitinh` (`tenDVTinh`) VALUES
('Chai - 1500ml'),
('Chai - 750ml'),
('Lon'),
('Tô'),
('Đĩa');

-- --------------------------------------------------------

--
-- Table structure for table `dsmonantheohd`
--

CREATE TABLE `dsmonantheohd` (
  `stt` int(11) NOT NULL,
  `idHoaDon` varchar(100) NOT NULL,
  `tenMonAn` varchar(100) CHARACTER SET utf8 NOT NULL,
  `soLuong` int(11) NOT NULL,
  `thanhTien` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dsmonantheohd`
--

INSERT INTO `dsmonantheohd` (`stt`, `idHoaDon`, `tenMonAn`, `soLuong`, `thanhTien`) VALUES
(13, 'nv00120180309120902', 'Coca-Cola', 3, '30,000 VND'),
(14, 'nv00120180309120902', 'Pepsi', 1, '10,000 VND'),
(24, 'nv00420180309174818', 'Coca-Cola', 1, '10,000 VND'),
(25, 'nv00520180313185311', 'Coca-Cola', 1, '10,000 VND'),
(26, 'nv00520180313185311', 'Pepsi', 1, '10,000 VND'),
(27, 'nv00520180313190508', 'Coca-Cola', 1, '10,000 VND'),
(28, 'nv00520180313190629', 'Coca-Cola', 1, '10,000 VND'),
(29, 'nv00520180313190807', 'Coca-Cola', 1, '10,000 VND'),
(30, 'nv00520180313190807', 'Canh bắp cải gói thịt', 1, '20,000 VND'),
(31, 'nv00520180313190807', 'Canh gà cải bó xôi', 1, '20,000 VND'),
(32, 'nv00520180313121242', 'Coca-Cola', 1, '10,000 VND'),
(33, 'nv00520180313121242', 'Canh gà cải bó xôi', 1, '20,000 VND'),
(34, 'nv00520180313191816', 'Coca-Cola', 1, '10,000 VND'),
(35, 'nv00520180313192014', 'Coca-Cola', 1, '10,000 VND'),
(36, 'nv00520180313192429', 'Coca-Cola', 2, '20,000 VND'),
(37, 'nv00520180313192508', 'Coca-Cola', 1, '10,000 VND'),
(38, 'nv00520180313202441', 'Coca-Cola', 2, '20,000 VND'),
(39, 'nv00520180313202441', 'Pepsi', 1, '10,000 VND'),
(40, 'nv00520180313204355', 'Pepsi', 1, '10,000 VND'),
(45, 'nv00520180314155533', 'Coca-Cola', 1, '10,000 VND'),
(48, 'nv00520180314232024', 'Coca-Cola', 1, '10,000 VND'),
(49, 'nv00520180315160831', 'Coca-Cola', 2, '20,000 VND'),
(50, 'nv00520180315160831', 'Pepsi', 2, '20,000 VND'),
(51, 'nv00520180315091041', 'Coca-Cola', 1, '10,000 VND'),
(52, 'nv00520180315091041', 'Pepsi', 1, '10,000 VND'),
(53, 'nv00520180315091506', 'Coca-Cola', 1, '10,000 VND'),
(54, 'nv00520180315092724', 'Coca-Cola', 1, '10,000 VND'),
(55, 'nv00520180315092724', 'Pepsi', 1, '10,000 VND'),
(56, 'nv00520180315163100', 'Coca-Cola', 1, '10,000 VND'),
(57, 'nv00520180315114259', 'Coca-Cola', 2, '20,000 VND'),
(58, 'nv00520180315184314', 'Coca-Cola', 1, '10,000 VND'),
(59, 'nv00520180315140613', 'Coca-Cola', 2, '20,000 VND'),
(60, 'nv00520180315140616', 'Pepsi', 1, '10,000 VND'),
(61, 'nv00520180315143026', 'Pepsi', 1, '10,000 VND'),
(62, 'nv00520180315143512', 'Canh gà cải bó xôi', 1, '20,000 VND'),
(63, 'nv00520180315143611', 'Canh gà cải bó xôi', 1, '20,000 VND'),
(64, 'nv00520180315144734', 'Coca-Cola', 1, '10,000 VND'),
(65, 'nv00520180315144737', 'Canh gà cải bó xôi', 1, '20,000 VND'),
(66, 'nv00520180315220116', 'Coca-Cola', 2, '20,000 VND'),
(67, 'nv00520180315145144', 'Coca-Cola', 1, '10,000 VND'),
(68, 'nv00520180315155547', 'Coca-Cola', 1, '10,000 VND'),
(69, 'nv00520180315155547', 'Pepsi', 1, '10,000 VND'),
(70, 'nv00520180315160411', 'Coca-Cola', 2, '20,000 VND'),
(71, 'nv00520180315160411', 'Pepsi', 1, '10,000 VND'),
(72, 'nv00520180315162710', 'Coca-Cola', 2, '20,000 VND'),
(73, 'nv00520180315162710', 'Pepsi', 2, '20,000 VND'),
(74, 'nv00520180315162736', 'Pepsi', 1, '10,000 VND'),
(75, 'nv00520180315162941', 'Coca-Cola', 1, '10,000 VND'),
(76, 'nv00520180315162941', 'Pepsi', 1, '10,000 VND'),
(77, 'nv00520180315163322', 'Coca-Cola', 1, '10,000 VND'),
(78, 'nv00520180315163334', 'Coca-Cola', 2, '20,000 VND'),
(79, 'nv00520180315163334', 'Pepsi', 3, '30,000 VND'),
(80, 'nv00520180315163805', 'Coca-Cola', 2, '20,000 VND'),
(81, 'nv00520180315163937', 'Pepsi', 4, '40,000 VND'),
(82, 'nv00520180315164028', 'Coca-Cola', 2, '20,000 VND'),
(83, 'nv00520180315164201', 'Coca-Cola', 2, '20,000 VND'),
(84, 'nv00520180315164201', 'Pepsi', 2, '20,000 VND'),
(85, 'nv00520180315164256', 'Coca-Cola', 1, '10,000 VND'),
(86, 'nv00520180315164422', 'Coca-Cola', 4, '40,000 VND'),
(87, 'nv00520180315164447', 'Coca-Cola', 2, '20,000 VND'),
(88, 'nv00520180315164447', 'Pepsi', 2, '20,000 VND'),
(89, 'nv00520180315164532', 'Coca-Cola', 2, '20,000 VND'),
(90, 'nv00520180315164532', 'Pepsi', 1, '10,000 VND'),
(91, 'nv00520180316162325', 'Coca-Cola', 2, '20,000 VND'),
(92, 'nv00520180316162503', 'Pepsi', 2, '20,000 VND'),
(93, 'nv00520180316233225', 'Coca-Cola', 2, '20,000 VND'),
(94, 'nv00520180316163655', 'Canh bắp cải gói thịt', 2, '40,000 VND'),
(95, 'nv00520180316234038', 'Canh bắp cải gói thịt', 1, '20,000 VND'),
(96, 'nv00520180316234038', 'Canh gà cải bó xôi', 2, '40,000 VND'),
(97, 'nv00520180316164418', 'Pepsi', 2, '20,000 VND'),
(98, 'nv00520180316164421', 'Coca-Cola', 3, '30,000 VND');

-- --------------------------------------------------------

--
-- Table structure for table `hoadonchothanhtoan`
--

CREATE TABLE `hoadonchothanhtoan` (
  `stt` int(11) NOT NULL,
  `tenBan` int(11) NOT NULL,
  `tenMonAn` varchar(100) CHARACTER SET utf8 NOT NULL,
  `soLuong` int(11) NOT NULL,
  `thoiGianGoi` datetime NOT NULL,
  `soNguoi` int(11) NOT NULL,
  `tinhTrangOder` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hoadonchothanhtoan`
--

INSERT INTO `hoadonchothanhtoan` (`stt`, `tenBan`, `tenMonAn`, `soLuong`, `thoiGianGoi`, `soNguoi`, `tinhTrangOder`) VALUES
(38, 6, 'Coca-Cola', 1, '2018-03-15 11:42:52', 5, 0),
(73, 4, 'Pepsi', 2, '2018-03-15 16:40:47', 9, 0),
(74, 2, 'Coca-Cola', 4, '2018-03-15 16:41:07', 9, 0),
(75, 3, 'Coca-Cola', 3, '2018-03-15 16:41:50', 4, 0),
(80, 8, 'Coca-Cola', 2, '2018-03-15 16:45:25', 6, 0),
(81, 8, 'Pepsi', 1, '2018-03-15 16:45:25', 6, 0),
(82, 7, 'Coca-Cola', 2, '2018-03-15 16:47:06', 5, 0),
(83, 7, 'Pepsi', 2, '2018-03-15 16:47:06', 5, 0),
(92, 9, 'Canh bắp cải gói thịt', 1, '2018-03-16 16:41:27', 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `kho`
--

CREATE TABLE `kho` (
  `tenNguyenLieu` varchar(100) CHARACTER SET utf8 NOT NULL,
  `giaNguyenLieu` float NOT NULL,
  `tenDVTinh` varchar(50) CHARACTER SET utf8 NOT NULL,
  `soLuongTon` float NOT NULL,
  `ngayNhap` date NOT NULL,
  `gia` varchar(100) NOT NULL,
  `tenNhaCC` varchar(100) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `loaisp`
--

CREATE TABLE `loaisp` (
  `id` int(3) NOT NULL,
  `tenloaisp` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loaisp`
--

INSERT INTO `loaisp` (`id`, `tenloaisp`) VALUES
(1, 'Áo khoác'),
(2, 'Áo thun, áo polo'),
(3, 'Áo sơ mi'),
(4, 'Quần shorts'),
(5, 'Quần jeans'),
(6, 'Quần kaki, tây'),
(7, 'Quần áo thể thao');

-- --------------------------------------------------------

--
-- Table structure for table `nguyenlieutheomonan`
--

CREATE TABLE `nguyenlieutheomonan` (
  `stt` int(11) NOT NULL,
  `tenMonAn` varchar(100) CHARACTER SET utf8 NOT NULL,
  `tenNguyenLieu` varchar(100) CHARACTER SET utf8 NOT NULL,
  `tenDVTinh` varchar(50) CHARACTER SET utf8 NOT NULL,
  `soLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `idNhanVien` varchar(30) NOT NULL,
  `tenNhanVien` varchar(30) CHARACTER SET utf8 NOT NULL,
  `gioiTinh` varchar(10) CHARACTER SET utf8 NOT NULL,
  `ngaySinh` date NOT NULL,
  `queQuan` varchar(50) CHARACTER SET utf8 NOT NULL,
  `soDienThoai` varchar(12) NOT NULL,
  `chucVu` varchar(20) CHARACTER SET utf8 NOT NULL,
  `ngayVao` date NOT NULL,
  `luongNgay` varchar(100) NOT NULL,
  `userPass` varchar(100) NOT NULL,
  `anhDaiDien` text NOT NULL,
  `online` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`idNhanVien`, `tenNhanVien`, `gioiTinh`, `ngaySinh`, `queQuan`, `soDienThoai`, `chucVu`, `ngayVao`, `luongNgay`, `userPass`, `anhDaiDien`, `online`) VALUES
('nv001', 'nguyễn văn thinh', 'Nam', '0000-00-01', 'hà nam', '0971129897', 'QL', '0000-00-01', '500000', 'thinhkaku-1', '/image/staff/TbYzBygM1JpfPRAAAC1520249120667.png', 0),
('nv004', 'tran quoc khanh', 'Nam', '1900-01-01', 'ha nam', '0489494', 'BB', '1900-01-01', '500000', 'khanh-1', '/image/staff/6gzU82MuKoq7VCAAAH1520617618077.png', 0),
('nv005', 'tran trung hieu', 'Nam', '1900-01-01', 'ha nam', '00545644', 'BB', '1900-01-01', '500000', 'hieu-1', '/image/staff/mALKaJvCgefmGhAAAC1520647424965.png', 1),
('nv006', 'lam', 'Nam', '0002-12-31', 'ha  nam', '056564564', 'BB', '0002-12-31', '50000', 'lam-1', '/image/staff/n7jsOfcRuiukDrAAAB1520649547722.png', 0);

-- --------------------------------------------------------

--
-- Table structure for table `nhommonan`
--

CREATE TABLE `nhommonan` (
  `tenNhom` varchar(30) CHARACTER SET utf8 NOT NULL,
  `soLuong` int(11) NOT NULL,
  `loai` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nhommonan`
--

INSERT INTO `nhommonan` (`tenNhom`, `soLuong`, `loai`) VALUES
('Món Canh', 2, 'food'),
('Món Cơm', 2, 'food'),
('Nước ngọt', 2, 'drink'),
('Rượu', 2, 'drink');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensp` varchar(100) NOT NULL,
  `giasp` int(11) NOT NULL,
  `ha1` varchar(200) NOT NULL,
  `ha2` varchar(200) NOT NULL,
  `ha3` varchar(200) NOT NULL,
  `motasp` varchar(5000) NOT NULL,
  `idloaisp` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensp`, `giasp`, `ha1`, `ha2`, `ha3`, `motasp`, `idloaisp`) VALUES
(1, 'KMAN - FreeShip Áo Khoác Kaki Phong Cách 2017 - KM239', 199000, 'https://media3.scdn.vn/img2/2017/10_20/CPeJ8K_simg_02d57e_50x50_maxb.jpg', 'https://media3.scdn.vn/img2/2017/10_20/NNxR92_simg_03ae43_697-697-49-23_cropf_simg_02d57e_50x50_maxb.jpg', 'https://media3.scdn.vn/img2/2017/10_20/4Duk36_simg_02d57e_50x50_maxb.jpg', 'Áo Khoác Kaki 2 lớp, bên trong có thêm lớp lót dù, mặc rất nhẹ và thỏa mái...\r\n\r\n- Chất Liệu: Kaki cao cấp - Không phai màu - Không dộp - bao giặt\r\n\r\n- Kiểu Dáng: cổ đứng có bo bên trong tạo cảm giác ấm áp, có bách nút khóa cổ , phối viền năng động\r\n \r\n- Tạo cho bạn 1 vẻ ngoài thật nam tính và khỏe khoắn', 1),
(2, 'Áo khoác kaki bomber nam - BT47', 129000, 'https://media3.scdn.vn/img2/2017/10_9/ao-khoac-kaki-bomber-nam-1m4G3-g5G28u_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/10_9/ao-khoac-kaki-bomber-nam-1m4G3-Oeif2y_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/10_9/ao-khoac-kaki-bomber-nam-1m4G3-4fYo0L_simg_d0daf0_800x1200_max.jpg', 'Áo khoác kaki bomber nam \r\nMàu sắc: Trắng, Đen, \r\nChất liệu: áo khoác kaki 2 lớp , bên trong lót dù \r\nXuất xứ: Việt Nam\r\nKích thước: M , L \r\n \r\n  - Size M: Phù hợp với nam từ 45kg - 55kg \r\n  - Size L: Phù hợp với nam từ 56kg-66kg', 1),
(3, 'áo thun dài tay nam - AT19', 89000, 'https://media3.scdn.vn/img2/2017/10_19/ao-thun-dai-tay-nam-1m4G3-r0EZjF.jpg', 'https://media3.scdn.vn/img2/2017/10_19/ao-thun-dai-tay-nam-1m4G3-zrah13.jpg', 'https://media3.scdn.vn/img2/2017/10_19/ao-thun-dai-tay-nam-1m4G3-ixvpgo.jpg', 'Màu sắc: Trắng,Đen\r\nKích thước: M,L', 2),
(4, 'Áo thun nam body cổ tròn form Hàn Quốc - A203', 46000, 'https://media3.scdn.vn/img1/2015/8_7/ao-thun-nam-body-co-tron-form-han-quoc-1m4G3-ab2edd_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img1/2015/8_7/ao-thun-nam-body-co-tron-form-han-quoc-1m4G3-f4a525_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img1/2015/8_7/ao-thun-nam-body-co-tron-form-han-quoc-1m4G3-13cc26_simg_d0daf0_800x1200_max.jpg', '\r\nÁo thun nam body tay ngắn phong cách thời trang với thiết kế trẻ trung và cá tính với cổ tim nổi bật, cho các bạn nam tự tin khoe cá tính sôi nổi.\r\nChất liệu thun cotton  mang lại sự thoải mái, thoáng mát, thấm hút mồ hôi tốt, phù hợp với các bạn trẻ yêu thích hoạt động.\r\nSize S: phù hợp với bạn từ 45 - 55kg.\r\nSize M: phù hợp với bạn từ 55 - 65kg.\r\nSize L: phù hợp với bạn từ 65 -75kg.\r\nSize XL: phù hợp với bạn  75-80kg\r\nSize XXL: phù hợp với bạn >80kg', 2),
(5, 'Áo Sơ Mi Hoa Thời Trang TD222 - TD222', 169000, 'https://media3.scdn.vn/img2/2017/6_6/ao-so-mi-hoa-thoi-trang-td222-1m4G3-UxBZD9_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/6_6/ao-so-mi-hoa-thoi-trang-td222-1m4G3-SPLWL2_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/6_6/ao-so-mi-hoa-thoi-trang-td222-1m4G3-SPLWL2_simg_d0daf0_800x1200_max.jpg', '_Tên Sản Phẩm: Áo sơ hoa cao cấp\r\n+From:bình thường. chọn lớn hơn 1 size nếu thích mặc thoải mái nhé\r\n+Size: M L XL. Từ 47-70kg dễ dàng lựa chọn\r\n+ Chất liệu vải kate co giãn, thoải mái khi vận động\r\n+Áo thiết kế hoa nhỏ thời trang, cổ áo có thể cài thành cổ trụ hoặc có thể bẻ ra làm có cổ vẫn tiện nhé\r\n+ Màu sắc: 2 màu. xanh đen, trắng', 3),
(6, 'ÁO SƠ MI NAM IN HÌNH TÊ GIÁC - LD540 - LD540', 199000, 'https://media3.scdn.vn/img2/2017/9_5/ao-so-mi-nam-in-hinh-te-giac-ld540-1m4G3-2n97Dq_simg_a1bd47_726-726-0-12_cropf_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/9_5/ao-so-mi-nam-in-hinh-te-giac-ld540-1m4G3-EVIbbO_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/9_5/ao-so-mi-nam-in-hinh-te-giac-ld540-1m4G3-orvSLP_simg_d0daf0_800x1200_max.jpg', 'Thông tin sản phẩm:\r\nXuất xứ: VIỆT NAM\r\nChất liệu: cotton hàn quốc\r\nKiểu dáng: Thiết kế theo form chuẩn\r\nTrọng lượng: 300g\r\nSize: M, L, XL\r\nĐiểm đặc biệt:\r\nThiết kế  cổ bẻ, tay ngắn, có nút gài phía trước chắc chắn, đường may tỉ mỉ, áo thiết kế form vừa vặn giúp tôn được đường nét mạnh mẽ, nam tính của các chàng trai\r\nMàu sắc tinh tế phối với họa tiết nổi bật, mang đến sự thanh lịch, cuốn hút và trẻ trung cho các bạn nam.\r\nVới chất liệu cotton lạnh co dãn rất phù hợp để sử dụng thoải mái cho môi trường công sở, đi chơi; mẫu áo có thiết kế như thế này sẽ giúp các bạn nam thể hiện được sự tươi trẻ, năng động, thanh lịch và dễ dàng khoe được vóc dáng cân đối của cơ thể.', 3),
(7, 'NK 0606 - QUẦN SHORT KAKI NAM THỜI TRANG - MÀU ĐEN - QS_04 - QS_04A', 159000, 'https://media3.scdn.vn/img2/2017/6_13/nk-0606-quan-short-kaki-nam-thoi-trang-mau-den-qs04-1m4G3-BNUsUV_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/6_13/nk-0606-quan-short-kaki-nam-thoi-trang-mau-den-qs04-1m4G3-jsXsR4_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/6_13/nk-0606-quan-short-kaki-nam-thoi-trang-mau-den-qs04-1m4G3-UZfnD8_simg_d0daf0_800x1200_max.jpg', '-     Những chiếc quần short kaki nam với nhiều màu sác trẻ trung đang được nhiều bạn nam lựa chọn để thể hiện phong cách mang hơi thở hiện đại của mình\r\n-     Quần short kaki nam Với thiết kế ống đứng giúp bạn thật tự tin và thoải mái khi xuống phố hay sánh bước bên những người thân yêu . Đặc biệt bạn có thể dễ dàng điều chỉnh ống quần bằng cách xăn nhẹ phần ống.Màu sắc nhã nhặn, đường chỉ chắc chắn, chất liệu vải thoáng mát là những ưu điểm mang chiếc quần đến với các bạn trẻ .\r\n    Quần short kaki nam Bạn có thể dễ dàng kết hợp với tất cả loại áo như thun, sơ mi .. cùng một đôi giày,vài phụ kiện thích hợp để có 1 set đồ hoàn hảo', 4),
(8, 'NK 0606 - QUẦN SHORT THÊ THAO US NAVY - MÀU XÁM ĐẬM - ST_49 - ST_49', 139000, 'https://media3.scdn.vn/img2/2017/7_14/nk-0606-quan-short-the-thao-us-navy-mau-xam-dam-st49-1m4G3-efdE8x_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/7_14/nk-0606-quan-short-the-thao-us-navy-mau-xam-dam-st49-1m4G3-AEHuB8_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/7_14/nk-0606-quan-short-the-thao-us-navy-mau-xam-dam-st49-1m4G3-w1GUx6_simg_d0daf0_800x1200_max.jpg', 'Quần short nam chất liệu vào thể thao, co giãn, có thể mặc ở nhà hoặc khi chơi thể thao đều rất thoải mái.\r\nMàu sắc thanh lịch, kiểu dài ngang gối, tạo phong cách trẻ trung, năng động.\r\nĐường may chắc chắn, chất liệu bền có độ co giãn tạo cho bạn cảm giác thoải mái khi vận động.\r\nKiểu dáng trẻ trung, thích hợp cho các bạn trẻ năng động hay tham gia chơi thể thao.\r\nQuần thiết kế cạp thun, có 02 dây rút và 2 túi xẻ rộng bên hông, thoải mái đựng điện thoại hay những vật dụng cá nhân khi cần.\r\nThích hợp cho bạn nam từ 50 - 75kg.', 4),
(9, 'Quần jeans ống côn skinny nam phong cách - Mã ĐN1012 - ĐN1012', 255000, 'https://media3.scdn.vn/img1/2016/2_24/quan-jeans-ong-con-skinny-nam-phong-cach-ma-dn1012-1m4G3-ef01ce_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img1/2016/2_24/quan-jeans-ong-con-skinny-nam-phong-cach-ma-dn1012-1m4G3-dadb90_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img1/2016/2_24/quan-jeans-ong-con-skinny-nam-phong-cach-ma-dn1012-1m4G3-8dc787_simg_d0daf0_800x1200_max.jpg', '- Hàng nhập cao cấp. Shop cam kết không bán hàng nhái, hàng gia công kém chất lượng. Hàng như hình.\r\n \r\n- Được đổi hàng trong vòng 7 ngày.\r\n\r\n- Cần thông tin tư vấn thêm về sp hoặc gặp bất cứ vấn đề gì trong quá trình đặt hàng, quý khách vui lòng gọi số: \r\n\r\n\r\n0911.220.599 - 0927.409.369 (8h-21h30) để được hỗ trợ. ', 5),
(10, 'Quần jean nam ống suông SOC592 - SOC592', 350000, 'https://media3.scdn.vn/img2/2017/3_2/quan-jean-nam-ong-suong-soc592-1m4G3-vx6Fx4_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/10_13/quan-jean-nam-ong-suong-soc592-1m4G3-9j8IXb_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/10_13/quan-jean-nam-ong-suong-soc592-1m4G3-ORNFm5_simg_d0daf0_800x1200_max.jpg', 'Quần jean nam ống suông\r\nSize NAM : 29 30 31 32 34\r\nHàng Việt Nam - ống suông\r\nChất liệu: vải jean cao cấp\r\n1 áo - 350.000\r\nĐơn hàng > 200.000 --> hỗ trợ ship 20.000\r\nĐơn hàng > 375.000 --> hỗ trợ ship 30.000', 5),
(11, 'Quần tây nam cao cấp miễn phí vận chuyển - quanauden', 199000, 'https://media3.scdn.vn/img1/2016/8_6/quan-tay-nam-cao-cap-mien-phi-van-chuyen-1m4G3-DpXbhf_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img1/2016/8_6/quan-tay-nam-cao-cap-mien-phi-van-chuyen-1m4G3-S9qp9d_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img1/2016/8_6/quan-tay-nam-cao-cap-mien-phi-van-chuyen-1m4G3-HSJuYb_simg_d0daf0_800x1200_max.jpg', '  Nếu một chiếc quần Jean ống côn thật sự chỉ dành cho các chàng có vóc người cao thanh mảnh, thì Quần Tây cao cấp không kén người mặc đến như vậy. Bởi chất liệu mềm mại cùng kiểu dáng sang trọng và lịch lãm, nên quần Tây phù hợp hầu hết các dáng người. Thoải mái diện đi làm, đi học hay đến những nơi cần sự chỉnh chu trong trang phục, Quần Tây nam cao cấp là một gợi ý tuyện vời cho gu thời trang cực \"Hàn Quốc\" cho chàng', 6),
(12, ' COMBO 3 QUẦN JOGGER NAM - COMBO', 490000, 'https://media3.scdn.vn/img2/2017/9_23/combo-3-quan-jogger-nam-1m4G3-UuaTEA_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/9_23/combo-3-quan-jogger-nam-1m4G3-ksXa79_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/9_23/combo-3-quan-jogger-nam-1m4G3-ISebNV_simg_d0daf0_800x1200_max.jpg', ' Full size : M , L, XL , XXL\r\n Vải Kaki cotton mềm , dày dặn\r\n Fom dáng thể thao , mặc thoải mái\r\n Thích hợp với nhiều loại giày - Đi làm đi chơi đều rất đẹp\r\n Hàng chính hãng TONINO\r\n-----------------------\r\n BẢO ĐẢM HÀNG Y HÌNH, KHÔNG ĐÚNG TẶNG LUÔN QUẦN!\r\n CAM KẾT ĐẢM BẢO CHẤT LƯỢNG MẪU MÃ\r\n HOÀN TIỀN NẾU KHÔNG ƯNG - ĐỔI TRẢ TRONG 48H\r\n GIAO HÀNG TOÀN QUỐC - NHẬN HÀNG XONG MỚI THANH TOÁN!\r\n TỔNG KHO SỈ LẺ TOÀN QUỐC\r\n-----------------------\r\nHƯỚNG DẪN CHỌN SIZE:\r\n(Form quần thoải mái, rộng rãi mọi người ước lượng size hoặc inbox cho shop để shop tư vấn nhé. Size quần có thể tăng nếu người mặc có bắp chân to hoặc tập gym)\r\n- Size M: Từ 48 - 59kg\r\n- Size L: Từ 60 - 68kg \r\n- Size XL: Từ 69 - 78kg \r\n- Size XXL: Từ 79 - 95kg \r\nSau khi đặt màu của quần, shop sẽ gọi tư vấn trực tiếp đến quý khách chọn size', 6),
(13, 'Bộ thể thao nam thun lạnh cao cấp 2017 - BK105', 165000, 'https://media3.scdn.vn/img2/2017/9_12/bo-the-thao-nam-thun-lanh-cao-cap-2017-1m4G3-Gy4kCH.jpg', 'https://media3.scdn.vn/img2/2017/9_12/bo-the-thao-nam-thun-lanh-cao-cap-2017-1m4G3-X9iLMp.jpg', 'https://media3.scdn.vn/img2/2017/9_12/bo-the-thao-nam-thun-lanh-cao-cap-2017-1m4G3-nrDK7x.jpg', 'Chất liệu: Thun lạnh cao cấp\r\nSize:M,L,XL\r\n\r\nChọn màu QK vui lòng ghi mã màu vào phần ghi chú để shop phục vụ được chính xác\r\nHOT LINE: 0934.384.777 ZALO, LINE\r\n\r\nGIÁ BUÔN - SỈ LIÊN HỆ HOT LINE, GIÁ TỐT  0934.384.777 ZALO, VIBER\r\nĐể tránh tình trạng đặt hàng không thành công và nhanh nhất quý khách có thể gửi tin nhắn cho shop để shop hổ trợ đặt hộ:\r\nmã sản phẩm+màu sắc+họ tên+số điện thoại gửi đến 0934.384.777(zalo,sms)\r\nBaokhanh store tin rằng quý khách sẽ hài lòng với sản phẩm và giá cả của shop\r\nXin chân thành chân ơn quý khách đã ủng hộ shop', 7),
(14, 'QUẦN THỂ THAO NAM PHỐI SỌC TRẮNG PHONG CÁCH - SH05 - SH05', 169000, 'https://media3.scdn.vn/img2/2017/6_16/quan-the-thao-nam-phoi-soc-trang-phong-cach-sh05-1m4G3-28oD39_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/6_16/quan-the-thao-nam-phoi-soc-trang-phong-cach-sh05-1m4G3-crLS2j_simg_d0daf0_800x1200_max.jpg', 'https://media3.scdn.vn/img2/2017/6_16/quan-the-thao-nam-phoi-soc-trang-phong-cach-sh05-1m4G3-kHLsbm_simg_d0daf0_800x1200_max.jpg', 'Kiểu dáng thể thao, khỏe khoắn, đường chỉ may đẹp, tính thẩm mĩ cao\r\nĐược thiết kế cho các hoạt động thể thao, dã ngoại hoặc cũng có thể đi ngoài trời nắng,...\r\nDễ phối đồ cùng các trang phục khác: áo thun, áo ba lỗ, áo sơ mi, ...\r\nThông tin sản phẩm:\r\nXuất xứ: VIỆT NAM\r\nChất liệu:  Thun nỉ ngoại nhập khẩu cao cấp mềm\r\nKiểu dáng: Thiết kế theo form chuẩn\r\nMàu sắc: Đen, Xám\r\nTrọng lượng: 500g\r\nSize: M, L, XL', 7);

-- --------------------------------------------------------

--
-- Table structure for table `thongkehoadon`
--

CREATE TABLE `thongkehoadon` (
  `idHoaDon` varchar(100) NOT NULL,
  `idNhanVien` varchar(30) NOT NULL,
  `tenBan` int(11) NOT NULL,
  `thoiGianThanhToan` datetime NOT NULL,
  `tongTien` varchar(100) NOT NULL,
  `soNguoi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `thongkehoadon`
--

INSERT INTO `thongkehoadon` (`idHoaDon`, `idNhanVien`, `tenBan`, `thoiGianThanhToan`, `tongTien`, `soNguoi`) VALUES
('nv00120180309120902', 'nv001', 5, '2018-03-09 12:09:02', '40,000 VND', 3),
('nv00420180309174818', 'nv004', 6, '2018-03-09 17:48:18', '10,000 VND', 3),
('nv00520180313121242', 'nv005', 6, '2018-03-13 12:12:42', '30,000 VND', 6),
('nv00520180313185311', 'nv005', 8, '2018-03-13 18:53:11', '20,000 VND', 3),
('nv00520180313190508', 'nv005', 8, '2018-03-13 19:05:08', '10,000 VND', 6),
('nv00520180313190629', 'nv005', 5, '2018-03-13 19:06:29', '10,000 VND', 6),
('nv00520180313190807', 'nv005', 1, '2018-03-13 19:08:07', '50,000 VND', 6),
('nv00520180313191816', 'nv005', 8, '2018-03-13 19:18:16', '10,000 VND', 3),
('nv00520180313192014', 'nv005', 8, '2018-03-13 19:20:14', '10,000 VND', 2),
('nv00520180313192429', 'nv005', 8, '2018-03-13 19:24:29', '20,000 VND', 3),
('nv00520180313192508', 'nv005', 5, '2018-03-13 19:25:08', '10,000 VND', 6),
('nv00520180313202441', 'nv005', 4, '2018-03-13 20:24:41', '30,000 VND', 2),
('nv00520180313204355', 'nv005', 7, '2018-03-13 20:43:55', '10,000 VND', 2),
('nv00520180314155533', 'nv005', 8, '2018-03-14 15:55:33', '10,000 VND', 6),
('nv00520180314232024', 'nv005', 6, '2018-03-14 23:20:24', '10,000 VND', 1),
('nv00520180315091041', 'nv005', 5, '2018-03-15 09:10:41', '20,000 VND', 6),
('nv00520180315091506', 'nv005', 5, '2018-03-15 09:15:06', '10,000 VND', 1),
('nv00520180315092724', 'nv005', 6, '2018-03-15 09:27:24', '20,000 VND', 3),
('nv00520180315114259', 'nv005', 8, '2018-03-15 11:42:59', '20,000 VND', 3),
('nv00520180315140613', 'nv005', 3, '2018-03-15 14:06:13', '20,000 VND', 3),
('nv00520180315140616', 'nv005', 9, '2018-03-15 14:06:16', '10,000 VND', 3),
('nv00520180315143026', 'nv005', 5, '2018-03-15 14:30:26', '10,000 VND', 1),
('nv00520180315143512', 'nv005', 5, '2018-03-15 14:35:12', '20,000 VND', 3),
('nv00520180315143611', 'nv005', 5, '2018-03-15 14:36:11', '20,000 VND', 3),
('nv00520180315144734', 'nv005', 8, '2018-03-15 14:47:34', '10,000 VND', 6),
('nv00520180315144737', 'nv005', 5, '2018-03-15 14:47:37', '20,000 VND', 6),
('nv00520180315145144', 'nv005', 5, '2018-03-15 14:51:44', '10,000 VND', 3),
('nv00520180315155547', 'nv005', 5, '2018-03-15 15:55:47', '20,000 VND', 5),
('nv00520180315160411', 'nv005', 5, '2018-03-15 16:04:11', '30,000 VND', 3),
('nv00520180315160831', 'nv005', 5, '2018-03-15 16:08:31', '40,000 VND', 5),
('nv00520180315162710', 'nv005', 8, '2018-03-15 16:27:10', '40,000 VND', 5),
('nv00520180315162736', 'nv005', 9, '2018-03-15 16:27:36', '10,000 VND', 6),
('nv00520180315162941', 'nv005', 2, '2018-03-15 16:29:41', '20,000 VND', 3),
('nv00520180315163100', 'nv005', 6, '2018-03-15 16:31:00', '10,000 VND', 3),
('nv00520180315163322', 'nv005', 3, '2018-03-15 16:33:22', '10,000 VND', 9),
('nv00520180315163334', 'nv005', 5, '2018-03-15 16:33:34', '50,000 VND', 5),
('nv00520180315163805', 'nv005', 5, '2018-03-15 16:38:05', '20,000 VND', 3),
('nv00520180315163937', 'nv005', 3, '2018-03-15 16:39:37', '40,000 VND', 3),
('nv00520180315164028', 'nv005', 5, '2018-03-15 16:40:28', '20,000 VND', 5),
('nv00520180315164201', 'nv005', 8, '2018-03-15 16:42:01', '40,000 VND', 5),
('nv00520180315164256', 'nv005', 1, '2018-03-15 16:42:56', '10,000 VND', 3),
('nv00520180315164422', 'nv005', 1, '2018-03-15 16:44:22', '40,000 VND', 6),
('nv00520180315164447', 'nv005', 8, '2018-03-15 16:44:47', '40,000 VND', 1),
('nv00520180315164532', 'nv005', 7, '2018-03-15 16:45:32', '30,000 VND', 1),
('nv00520180315184314', 'nv005', 8, '2018-03-15 18:43:14', '10,000 VND', 2),
('nv00520180315220116', 'nv005', 5, '2018-03-15 22:01:16', '20,000 VND', 8),
('nv00520180315231220', 'nv005', 8, '2018-03-15 23:12:20', '0 VND', 0),
('nv00520180315232815', 'nv005', 8, '2018-03-15 23:28:15', '0 VND', 5),
('nv00520180315232836', 'nv005', 8, '2018-03-15 23:28:36', '0 VND', 5),
('nv00520180315232840', 'nv005', 8, '2018-03-15 23:28:40', '0 VND', 5),
('nv00520180316162325', 'nv005', 5, '2018-03-16 16:23:25', '20,000 VND', 5),
('nv00520180316162503', 'nv005', 5, '2018-03-16 16:25:03', '20,000 VND', 6),
('nv00520180316163655', 'nv005', 9, '2018-03-16 16:36:55', '40,000 VND', 6),
('nv00520180316164418', 'nv005', 5, '2018-03-16 16:44:18', '20,000 VND', 6),
('nv00520180316164421', 'nv005', 1, '2018-03-16 16:44:21', '30,000 VND', 5),
('nv00520180316233225', 'nv005', 5, '2018-03-16 23:32:25', '20,000 VND', 3),
('nv00520180316234038', 'nv005', 9, '2018-03-16 23:40:38', '60,000 VND', 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bangtin`
--
ALTER TABLE `bangtin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `danhsachban`
--
ALTER TABLE `danhsachban`
  ADD PRIMARY KEY (`tenBan`);

--
-- Indexes for table `danhsachmonan`
--
ALTER TABLE `danhsachmonan`
  ADD PRIMARY KEY (`tenMonAn`),
  ADD KEY `DanhSachMonAn_fk0` (`tenNhom`),
  ADD KEY `DanhSachMonAn_fk1` (`tenDVTinh`);

--
-- Indexes for table `donvitinh`
--
ALTER TABLE `donvitinh`
  ADD PRIMARY KEY (`tenDVTinh`);

--
-- Indexes for table `dsmonantheohd`
--
ALTER TABLE `dsmonantheohd`
  ADD PRIMARY KEY (`stt`),
  ADD KEY `DSMonAnTheoHD_fk0` (`idHoaDon`),
  ADD KEY `DSMonAnTheoHD_fk1` (`tenMonAn`);

--
-- Indexes for table `hoadonchothanhtoan`
--
ALTER TABLE `hoadonchothanhtoan`
  ADD PRIMARY KEY (`stt`),
  ADD KEY `HoaDonChoThanhToan_fk0` (`tenBan`),
  ADD KEY `HoaDonChoThanhToan_fk1` (`tenMonAn`);

--
-- Indexes for table `kho`
--
ALTER TABLE `kho`
  ADD PRIMARY KEY (`tenNguyenLieu`),
  ADD KEY `Kho_fk0` (`tenDVTinh`);

--
-- Indexes for table `loaisp`
--
ALTER TABLE `loaisp`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nguyenlieutheomonan`
--
ALTER TABLE `nguyenlieutheomonan`
  ADD PRIMARY KEY (`stt`),
  ADD KEY `NguyenLieuTheoMonAn_fk0` (`tenMonAn`),
  ADD KEY `NguyenLieuTheoMonAn_fk1` (`tenNguyenLieu`),
  ADD KEY `NguyenLieuTheoMonAn_fk2` (`tenDVTinh`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`idNhanVien`);

--
-- Indexes for table `nhommonan`
--
ALTER TABLE `nhommonan`
  ADD PRIMARY KEY (`tenNhom`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `thongkehoadon`
--
ALTER TABLE `thongkehoadon`
  ADD PRIMARY KEY (`idHoaDon`),
  ADD KEY `ThongKeHoaDon_fk0` (`idNhanVien`),
  ADD KEY `ThongKeHoaDon_fk1` (`tenBan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bangtin`
--
ALTER TABLE `bangtin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `dsmonantheohd`
--
ALTER TABLE `dsmonantheohd`
  MODIFY `stt` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- AUTO_INCREMENT for table `hoadonchothanhtoan`
--
ALTER TABLE `hoadonchothanhtoan`
  MODIFY `stt` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- AUTO_INCREMENT for table `loaisp`
--
ALTER TABLE `loaisp`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `nguyenlieutheomonan`
--
ALTER TABLE `nguyenlieutheomonan`
  MODIFY `stt` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `danhsachmonan`
--
ALTER TABLE `danhsachmonan`
  ADD CONSTRAINT `DanhSachMonAn_fk0` FOREIGN KEY (`tenNhom`) REFERENCES `nhommonan` (`tenNhom`),
  ADD CONSTRAINT `DanhSachMonAn_fk1` FOREIGN KEY (`tenDVTinh`) REFERENCES `donvitinh` (`tenDVTinh`);

--
-- Constraints for table `dsmonantheohd`
--
ALTER TABLE `dsmonantheohd`
  ADD CONSTRAINT `DSMonAnTheoHD_fk0` FOREIGN KEY (`idHoaDon`) REFERENCES `thongkehoadon` (`idHoaDon`),
  ADD CONSTRAINT `DSMonAnTheoHD_fk1` FOREIGN KEY (`tenMonAn`) REFERENCES `danhsachmonan` (`tenMonAn`);

--
-- Constraints for table `hoadonchothanhtoan`
--
ALTER TABLE `hoadonchothanhtoan`
  ADD CONSTRAINT `HoaDonChoThanhToan_fk0` FOREIGN KEY (`tenBan`) REFERENCES `danhsachban` (`tenBan`),
  ADD CONSTRAINT `HoaDonChoThanhToan_fk1` FOREIGN KEY (`tenMonAn`) REFERENCES `danhsachmonan` (`tenMonAn`);

--
-- Constraints for table `kho`
--
ALTER TABLE `kho`
  ADD CONSTRAINT `Kho_fk0` FOREIGN KEY (`tenDVTinh`) REFERENCES `donvitinh` (`tenDVTinh`);

--
-- Constraints for table `nguyenlieutheomonan`
--
ALTER TABLE `nguyenlieutheomonan`
  ADD CONSTRAINT `NguyenLieuTheoMonAn_fk0` FOREIGN KEY (`tenMonAn`) REFERENCES `danhsachmonan` (`tenMonAn`),
  ADD CONSTRAINT `NguyenLieuTheoMonAn_fk1` FOREIGN KEY (`tenNguyenLieu`) REFERENCES `kho` (`tenNguyenLieu`),
  ADD CONSTRAINT `NguyenLieuTheoMonAn_fk2` FOREIGN KEY (`tenDVTinh`) REFERENCES `donvitinh` (`tenDVTinh`);

--
-- Constraints for table `thongkehoadon`
--
ALTER TABLE `thongkehoadon`
  ADD CONSTRAINT `ThongKeHoaDon_fk0` FOREIGN KEY (`idNhanVien`) REFERENCES `nhanvien` (`idNhanVien`),
  ADD CONSTRAINT `ThongKeHoaDon_fk1` FOREIGN KEY (`tenBan`) REFERENCES `danhsachban` (`tenBan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
