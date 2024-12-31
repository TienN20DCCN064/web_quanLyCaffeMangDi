-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 31, 2024 lúc 05:45 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlcf`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chi_tiet_khuyen_mai`
--

CREATE TABLE `chi_tiet_khuyen_mai` (
  `MAKM` varchar(10) NOT NULL,
  `MASP` varchar(50) NOT NULL,
  `MASIZE` varchar(10) NOT NULL,
  `TILEGIAM` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chi_tiet_khuyen_mai`
--

INSERT INTO `chi_tiet_khuyen_mai` (`MAKM`, `MASP`, `MASIZE`, `TILEGIAM`) VALUES
('1', 'Caffe_Espresso', 'S', 20.00),
('km01', 'Caffe_Espresso', 'L', 20.00),
('km01', 'Caffe_Espresso', 'M', 10.00),
('km04', 'Caffe_Espresso', 'L', 13.00),
('km04', 'sp02', 'S', 30.00),
('km04', 'sp04', 'L', 35.00),
('km2', 'mmm1', 'M', 20.00),
('km2', 'sp01', 'M', 20.00),
('today', 'banhPmtx', 'L', 10.00),
('today', 'banh_chuoi', 'L', 10.00),
('today1', 'banh_caramel', 'L', 20.00);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chi_tiet_nguyen_lieu`
--

CREATE TABLE `chi_tiet_nguyen_lieu` (
  `MASP` varchar(50) NOT NULL,
  `MASIZE` varchar(10) NOT NULL,
  `MANL` varchar(10) NOT NULL,
  `SOLUONG` decimal(18,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chi_tiet_nguyen_lieu`
--

INSERT INTO `chi_tiet_nguyen_lieu` (`MASP`, `MASIZE`, `MANL`, `SOLUONG`) VALUES
('banhPmtx', 'L', 'banh_pmtx', 1.00),
('banh_caramel', 'L', 'banh_chuoi', 2.00),
('banh_chuoi', 'L', 'banh_chuoi', 1.00),
('c2', 'L', 'nn', 35.00),
('c2', 'M', 'nn', 30.00),
('c2', 'S', 'nn', 20.00),
('Caffe_Espresso', 'L', 'nl01', 10.00),
('Caffe_Espresso', 'L', 'nl02', 10.00),
('Caffe_Espresso', 'L', 'nl03', 10.00),
('Caffe_Espresso', 'L', 'nl04', 10.00),
('Caffe_Espresso', 'L', 'nl05', 10.00),
('Caffe_Espresso', 'L', 'nl08', 100.00),
('Caffe_Espresso', 'M', 'nl01', 5.00),
('Caffe_Espresso', 'M', 'nl02', 5.00),
('Caffe_Espresso', 'M', 'nl03', 5.00),
('Caffe_Espresso', 'M', 'nl05', 10.00),
('Caffe_Espresso', 'S', 'nl01', 3.00),
('Caffe_Espresso', 'S', 'nl02', 3.00),
('Caffe_Espresso', 'S', 'nl05', 3.00),
('Phindi cassia', 'L', 'nl04', 10.00),
('Phindi cassia', 'S', 'nl01', 10.00),
('Phindi Hanh Nhan', 'L', 'nl01', 10.00),
('Phindi Hanh Nhan', 'L', 'nl03', 10.00),
('Phindi Hanh Nhan', 'L', 'nl111', 10.00),
('Phindi Hanh Nhan', 'M', 'nl01', 15.00),
('Phindi Hanh Nhan', 'M', 'nl04', 15.00),
('Phindi Hanh Nhan', 'M', 'nl111', 15.00),
('Phindi Hanh Nhan', 'S', 'nl111', 15.00),
('Phindi socola', 'S', 'nl01', 10.00),
('phindi_KemSua', 'L', 'nl01', 10.00),
('phindi_KemSua', 'L', 'nl06', 10.00),
('phindi_KemSua', 'M', 'nl06', 10.00),
('phindi_KemSua', 'S', 'nl01', 10.00),
('sp01', 'L', 'nl01', 10.00),
('sp01', 'L', 'nl08', 5.00),
('sp01', 'L', 'nl11', 5.00),
('sp01', 'S', 'nl01', 5.00),
('sp01', 'S', 'nl10', 10.00),
('sp02', 'L', 'nl02', 10.00),
('sp02', 'M', 'nl02', 10.00),
('sp02', 'M', 'nl05', 10.00),
('sp02', 'M', 'nl16', 10.00),
('sp02', 'S', 'nl01', 10.00),
('sp03', 'L', 'nl01', 10.00),
('sp03', 'L', 'nl02', 10.00),
('sp03', 'L', 'nl04', 10.00),
('sp03', 'M', 'nl_cf', 20.00),
('sp03', 'S', 'nl_cf', 20.00),
('sp04', 'L', 'nl03', 10.00),
('sp04', 'L', 'nuoc_pessi', 20.00),
('sp04', 'M', 'nuoc_pessi', 15.00),
('sp04', 'S', 'nuoc_pessi', 10.00),
('sp09', 'L', 'nl02', 10.00),
('sp09', 'L', 'nl05', 10.00),
('sp09', 'L', 'nl111', 10.00),
('sp09', 'M', 'nl04', 10.00),
('sp09', 'M', 'nl05', 10.00),
('sp10', 'M', 'nl01', 10.00),
('sp10', 'M', 'nl04', 11.00),
('sp10', 'M', 'nl09', 10.00),
('sp10', 'S', 'nl01', 10.00),
('sp10', 'S', 'nl07', 10.00),
('sp6', 'L', 'nl01', 5.00),
('sp6', 'L', 'nl_cf', 15.00),
('sp6', 'S', 'nl01', 5.00),
('sp6', 'S', 'nl_cf', 10.00),
('tra_anhDao', 'L', 'nl10', 10.00),
('tra_anhDao', 'L', 'nl16', 15.00),
('tra_anhDao', 'S', 'nl01', 5.00),
('tra_anhDao', 'S', 'nl16', 35.00),
('tra_oi', 'L', 'nl16', 45.00),
('tra_oi', 'M', 'nl01', 10.00),
('tra_oi', 'S', 'nl16', 10.00),
('tra_senVang', 'L', 'nl16', 36.00),
('tra_senVang', 'M', 'nl01', 25.00),
('tra_senVang', 'S', 'nl16', 10.00),
('tra_thachVai', 'L', 'nl16', 10.00),
('tra_thachVai', 'M', 'nl16', 10.00),
('tra_thachVai', 'S', 'nl10', 10.00);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chi_tiet_san_pham`
--

CREATE TABLE `chi_tiet_san_pham` (
  `MASP` varchar(50) NOT NULL,
  `MASIZE` varchar(10) NOT NULL,
  `MACT` varchar(10) DEFAULT NULL,
  `GIAHIENTHOI` decimal(18,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chi_tiet_san_pham`
--

INSERT INTO `chi_tiet_san_pham` (`MASP`, `MASIZE`, `MACT`, `GIAHIENTHOI`) VALUES
('banhPmtx', 'L', NULL, 35000.00),
('banh_caramel', 'L', NULL, 35000.00),
('banh_chuoi', 'L', NULL, 35000.00),
('banh_tiramise', 'L', NULL, 35000.00),
('c2', 'L', NULL, 15000.00),
('c2', 'M', NULL, 10000.00),
('c2', 'S', NULL, 7000.00),
('Caffe_Espresso', 'L', 'fdsf', 50000.00),
('Caffe_Espresso', 'M', 'ct6', 30000.00),
('Caffe_Espresso', 'S', 'ct6', 20000.00),
('Phindi cassia', 'L', NULL, 45000.00),
('Phindi cassia', 'M', NULL, 30000.00),
('Phindi cassia', 'S', NULL, 30000.00),
('Phindi Hanh Nhan', 'L', NULL, 45000.00),
('Phindi Hanh Nhan', 'M', NULL, 30000.00),
('Phindi Hanh Nhan', 'S', NULL, 20000.00),
('Phindi socola', 'L', NULL, 45000.00),
('Phindi socola', 'M', NULL, 30000.00),
('Phindi socola', 'S', NULL, 20000.00),
('phindi_KemSua', 'L', NULL, 45000.00),
('phindi_KemSua', 'M', NULL, 30000.00),
('phindi_KemSua', 'S', NULL, 20000.00),
('sp01', 'L', 'trà sữa a', 50000.00),
('sp01', 'S', 'trà sữa a', 20000.00),
('sp02', 'L', 'ct6', 50000.00),
('sp02', 'M', 'ct6', 30000.00),
('sp02', 'S', 'Cappuccino', 20000.00),
('sp03', 'L', 'cafe sữa ', 60000.00),
('sp03', 'M', 'trà sữa a', 35000.00),
('sp03', 'S', NULL, 10000.00),
('sp04', 'L', 'pessi ', 20000.00),
('sp04', 'M', 'pessi ', 15000.00),
('sp04', 'S', 'pessi ', 10000.00),
('sp05', 'M', 'trà sữa a', 10000.00),
('sp06', 'M', NULL, 10000.00),
('sp07', 'L', 'cafe sữa ', 30000.00),
('sp07', 'M', 'cafe sữa ', 20000.00),
('sp07', 'S', 'cafe sữa ', 15000.00),
('sp09', 'L', NULL, 35000.00),
('sp09', 'M', NULL, 45000.00),
('sp10', 'M', 'Cappuccino', 100.00),
('sp10', 'S', NULL, 25000.00),
('sp6', 'L', 'caffe đen', 30000.00),
('sp6', 'S', 'caffe đen', 20000.00),
('tra_anhDao', 'L', NULL, 45000.00),
('tra_anhDao', 'M', NULL, 45000.00),
('tra_anhDao', 'S', NULL, 45000.00),
('tra_oi', 'L', NULL, 45000.00),
('tra_oi', 'M', NULL, 30000.00),
('tra_oi', 'S', NULL, 20000.00),
('tra_senVang', 'L', NULL, 45000.00),
('tra_senVang', 'M', NULL, 30000.00),
('tra_senVang', 'S', NULL, 20000.00),
('tra_thachVai', 'L', NULL, 45000.00),
('tra_thachVai', 'M', NULL, 30000.00),
('tra_thachVai', 'S', NULL, 20000.00);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `congthuc`
--

CREATE TABLE `congthuc` (
  `MACT` varchar(50) NOT NULL,
  `NGAYLAP` date DEFAULT curdate(),
  `CONGTHUC` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `congthuc`
--

INSERT INTO `congthuc` (`MACT`, `NGAYLAP`, `CONGTHUC`) VALUES
('cafe sữa ', '2024-12-17', 'Cà phê sữa:  Ly lớn (350ml): 100ml cà phê + 60ml sữa đặc + đá. Ly vừa (250ml): 80ml cà phê + 45ml sữa đặc + đá. Ly bé (150ml): 60ml cà phê + 30ml sữa đặc + đá.'),
('caffe latte', '2024-12-17', 'Cách pha Caffe Latte:  Pha 1 shot espresso (30ml). Hấp nóng và tạo bọt 180ml sữa tươi. Đổ sữa vào espresso, giữ lớp bọt mỏng trên mặt.'),
('caffe mocha', '2024-12-17', ' Cà phê Mocha:  Ly lớn (350ml): 60ml espresso + 20g ca cao + 150ml sữa + kem béo. Ly vừa (250ml): 45ml espresso + 15g ca cao + 120ml sữa + kem béo. Ly bé (150ml): 30ml espresso + 10g ca cao + 90ml sữa + kem béo.'),
('caffe phin', '2024-12-17', 'Ly lớn (350ml): 100ml cà phê phin + nước nóng hoặc đá. Ly vừa (250ml): 75ml cà phê phin + nước nóng hoặc đá. Ly bé (150ml): 50ml cà phê phin + nước nóng hoặc đá.'),
('caffe đen', '2024-12-01', 'Cà phê đen:\r\n\r\nLy lớn (350ml): 120ml cà phê + nước nóng + đá.\r\nLy vừa (250ml): 90ml cà phê + nước nóng + đá.\r\nLy bé (150ml): 60ml cà phê + nước nóng + đá.'),
('Cappuccino', '2024-12-09', 'Dung tích Cappuccino cho 3 ly lớn, vừa, và bé:\r\n\r\nLy lớn (350ml): 60ml espresso + 145ml sữa nóng + 145ml bọt sữa.\r\nLy vừa (250ml): 45ml espresso + 102.5ml sữa nóng + 102.5ml bọt sữa.\r\nLy bé (150ml): 30ml espresso + 60ml sữa nóng + 60ml bọt sữa.'),
('ct6', '2024-12-06', 'Một ly cà phê “Espresso phổ biến” bắt đầu với 10-15 gam cà phê xay, được pha trong 25 – 30 giây, ở áp suất 9 bar và nước nóng trong khoảng 93°C. Tỷ lệ pha (cà phê bột/Espresso thu được) thường vào khoảng 1:1,5–1:2,5.'),
('fdsf', '2024-12-06', 'Một ly cà phê “Espresso phổ biến” bắt đầu với 18-20 gam cà phê xay, được pha trong 25 – 30 giây, ở áp suất 9 bar và nước nóng trong khoảng 93°C. Tỷ lệ pha (cà phê bột/Espresso thu được) thường vào khoảng 1:1,5–1:2,5.'),
('pessi ', '2024-12-17', 'Ly lớn (350ml): \r\nLy vừa (250ml):\r\nLy bé (150ml): '),
('trà sữa a', '2024-12-17', 'Ủ 100g trà với 1.8 lít nước sôi trong 20 phút, lấy cốt trà. Trộn 320g đường + 240g bột béo + 2.5ml muối, thêm 300g sữa đặc. Khuấy hỗn hợp vào cốt trà, thêm 0.7kg đá, khuấy tan, giữ lạnh 8 tiếng.'),
('trà sữa ô long', '2024-12-09', '\r\nTrà sữa Ô Long:\r\n\r\nLy lớn (700ml): 200ml trà + 50g đường + 150ml sữa + đá.\r\nLy vừa (500ml): 150ml trà + 40g đường + 120ml sữa + đá.\r\nLy bé (350ml): 100ml trà + 30g đường + 90ml sữa + đá.');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ct_hoadon`
--

CREATE TABLE `ct_hoadon` (
  `ID` varchar(10) NOT NULL,
  `MASP` varchar(50) NOT NULL,
  `MASIZE` varchar(10) NOT NULL,
  `SOLUONG` int(11) DEFAULT NULL,
  `MAKM` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ct_hoadon`
--

INSERT INTO `ct_hoadon` (`ID`, `MASP`, `MASIZE`, `SOLUONG`, `MAKM`) VALUES
('HD16', 'c2', 'L', 1, '0'),
('HD16', 'c2', 'M', 1, '0'),
('HD16', 'Phindi Hanh Nhan', 'S', 1, '0'),
('HD237', 'banhPmtx', 'L', 1, 'today'),
('HD237', 'banh_caramel', 'L', 1, '0'),
('HD237', 'banh_chuoi', 'L', 4, 'today'),
('HD237', 'c2', 'L', 5, '0'),
('HD237', 'Phindi cassia', 'L', 1, '0'),
('HD237', 'Phindi Hanh Nhan', 'L', 1, '0'),
('HD237', 'sp03', 'L', 1, '0'),
('HD286', 'sp03', 'L', 1, '0'),
('HD464', 'sp03', 'L', 1, '0'),
('HD464', 'sp04', 'L', 2, 'km04'),
('HD553', 'banhPmtx', 'L', 2, 'today'),
('HD553', 'banh_caramel', 'L', 1, '0'),
('HD607', 'banh_caramel', 'L', 1, '0'),
('HD607', 'banh_chuoi', 'L', 1, 'today'),
('HD607', 'Phindi cassia', 'L', 1, '0'),
('HD607', 'sp01', 'S', 1, '0'),
('HD607', 'sp03', 'L', 1, '0'),
('HD607', 'sp03', 'M', 2, '0'),
('HD72', 'sp10', 'M', 1, '0'),
('HD737', 'banhPmtx', 'L', 1, 'today'),
('HD737', 'banh_caramel', 'L', 1, '0'),
('HD737', 'banh_chuoi', 'L', 2, 'today'),
('HD792', 'sp02', 'L', 1, '0'),
('HD964', 'c2', 'M', 1, '0'),
('HD964', 'Caffe_Espresso', 'L', 1, '0'),
('HD964', 'Caffe_Espresso', 'S', 2, '0'),
('HD964', 'Phindi cassia', 'S', 1, '0'),
('HD964', 'Phindi Hanh Nhan', 'M', 1, '0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ct_phieunhap`
--

CREATE TABLE `ct_phieunhap` (
  `MANH` varchar(10) NOT NULL,
  `MANL` varchar(10) NOT NULL,
  `SOLUONG` decimal(10,2) DEFAULT NULL,
  `DONGIA` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ct_phieunhap`
--

INSERT INTO `ct_phieunhap` (`MANH`, `MANL`, `SOLUONG`, `DONGIA`) VALUES
('111', 'nl01', 11.00, 111.00),
('NH1', 'nl01', 1.00, 11111.00),
('NH2', 'nl01', 1.00, 11111.00),
('NH2', 'nl07', 222.00, 222.00),
('Nh3', 'nl01', 10.00, 10.00),
('Nh3', 'nl12', 10.00, 10.00),
('nh4', 'nl01', 100.00, 10.00),
('rr', 'nl01', 222.00, 11.00);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `IDHD` varchar(10) NOT NULL,
  `NGAYLAP` date DEFAULT curdate(),
  `MANV` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TONGTIEN` decimal(15,2) DEFAULT NULL,
  `PHANLOAI` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`IDHD`, `NGAYLAP`, `MANV`, `TONGTIEN`, `PHANLOAI`) VALUES
('HD16', '2024-12-23', 'QL03', 45000.00, '1'),
('HD237', '2024-12-23', 'QL03', 417500.00, '1'),
('HD286', '2024-12-01', 'QL03', 60000.00, '1'),
('HD464', '2024-12-17', 'QL03', 86000.00, '1'),
('HD553', '2024-12-23', 'QL03', 98000.00, '1'),
('HD607', '2024-12-23', 'QL03', 261500.00, '1'),
('HD72', '2024-12-14', 'QL03', 100.00, '1'),
('HD737', '2024-12-23', 'QL03', 129500.00, '1'),
('HD792', '2024-12-14', 'QL03', 35000.00, '1'),
('HD964', '2024-12-23', 'QL03', 160000.00, '1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MAKM` varchar(50) NOT NULL,
  `TGBD` datetime DEFAULT NULL,
  `TGKT` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`MAKM`, `TGBD`, `TGKT`) VALUES
('0', '2024-11-01 17:27:08', '2999-11-16 17:27:08'),
('1', '2024-12-12 00:00:00', '2024-12-15 00:00:00'),
('km01', '2024-12-12 00:00:00', '2024-12-15 00:00:00'),
('km04', '2024-12-12 00:00:00', '2024-12-20 00:00:00'),
('km2', '2024-12-11 00:00:00', '2024-12-13 00:00:00'),
('today', '2024-12-22 00:00:00', '2024-12-30 00:00:00'),
('today1', '2024-12-22 00:00:00', '2024-12-24 00:00:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lichsu`
--

CREATE TABLE `lichsu` (
  `THOIGIAN` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `MASP` varchar(50) NOT NULL,
  `MASIZE` varchar(10) NOT NULL,
  `GIATHAYDOI` decimal(10,2) DEFAULT NULL,
  `GIACU` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `lichsu`
--

INSERT INTO `lichsu` (`THOIGIAN`, `MASP`, `MASIZE`, `GIATHAYDOI`, `GIACU`) VALUES
('2024-12-06 10:11:38', 'Caffe_Espresso', 'S', 40000.00, 1000.00),
('2024-12-06 10:11:43', 'Caffe_Espresso', 'L', 11000.00, 2000.00),
('2024-12-06 10:31:59', 'Caffe_Espresso', 'L', 20000.00, 20000.00),
('2024-12-06 10:58:21', 'Caffe_Espresso', 'L', 24000.00, 23000.00),
('2024-12-06 10:58:41', 'Caffe_Espresso', 'L', 25000.00, 24000.00),
('2024-12-06 11:11:54', 'Caffe_Espresso', 'L', 23000.00, 25000.00),
('2024-12-17 08:54:36', 'Caffe_Espresso', 'L', 22000.00, 10000.00),
('2024-12-17 08:54:40', 'Caffe_Espresso', 'L', 23000.00, 20000.00),
('2024-12-17 08:55:46', 'sp01', 'L', 35000.00, 40000.00),
('2024-12-17 09:21:48', 'Caffe_Espresso', 'L', 50000.00, 23000.00),
('2024-12-17 09:21:56', 'Caffe_Espresso', 'M', 30000.00, 10000.00),
('2024-12-17 09:22:02', 'Caffe_Espresso', 'S', 20000.00, 40000.00),
('2024-12-17 09:22:08', 'sp01', 'L', 50000.00, 35000.00),
('2024-12-17 09:22:38', 'sp01', 'S', 20000.00, 50000.00),
('2024-12-17 09:22:43', 'sp02', 'L', 50000.00, 35000.00),
('2024-12-17 09:22:51', 'sp02', 'M', 30000.00, 20000.00),
('2024-12-17 09:23:10', 'sp03', 'M', 35000.00, 15000.00);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisp`
--

CREATE TABLE `loaisp` (
  `MALOAI` varchar(10) NOT NULL,
  `TENLOAI` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisp`
--

INSERT INTO `loaisp` (`MALOAI`, `TENLOAI`) VALUES
('loai01', 'Caffe'),
('loai02', 'Nước Ngọt'),
('loai03', 'Trà Sữa'),
('loai04', 'Bánh'),
('LOAI5', 'Phindi'),
('LOAI6', 'Trà');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguyenlieu`
--

CREATE TABLE `nguyenlieu` (
  `MANL` varchar(255) NOT NULL,
  `TENNL` varchar(255) DEFAULT NULL,
  `DONVI` varchar(255) DEFAULT NULL,
  `SLTON` decimal(15,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nguyenlieu`
--

INSERT INTO `nguyenlieu` (`MANL`, `TENNL`, `DONVI`, `SLTON`) VALUES
('banh_caramel', 'Bánh Caramel', 'Cái', 130.00),
('banh_chuoi', 'Bánh Chuối', 'Cái', 85.00),
('banh_pmtx', 'Bánh Phô Mai Trà Xanh', 'Cái', 96.00),
('banh_tiramise', 'Bánh TIramise', 'Cái', 100.00),
('nl01', 'đường', 'g (gam)', 914.00),
('nl02', 'sữa đặc', '	g (gam)', 1642.00),
('nl03', 'bột mì', '	g (gam)', 25.00),
('nl04', 'bột cacao', '	g (gam)', 19631.00),
('nl05', 'sữa tươi', 'ml - (lit)', 69.00),
('nl06', 'bột nở', '	g (gam)', 100.00),
('nl07', 'mứt dừa', '	g (gam)', 100.00),
('nl08', 'nước cốt dừa', 'ml - (mililit)', 8755.00),
('nl09', 'bơ', '	g (gam)', 100.00),
('nl10', 'đá viên', '	g (gam)', 10.00),
('nl11', 'trân châu trắng', '	g (gam)', 15.00),
('nl111', 'Socola', 'g (gam)', 960.00),
('nl112', 'tuấn', '	g (gam)', 100.00),
('nl12', 'trân', 'g (gam)', 110.00),
('nl16', 'Trà xanh', 'ml', 10000.00),
('nl_cf', 'caffe', 'g', 960.00),
('nn', 'Nước Ngọt', 'ml', 9730.00),
('nuoc_pessi', 'pessi', 'ml', 10000.00);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MANV` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `HOTENNV` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `GIOITINH` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NGAYSINH` date DEFAULT NULL,
  `SDT` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DIACHI` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `EMAIL` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MANV`, `HOTENNV`, `GIOITINH`, `NGAYSINH`, `SDT`, `DIACHI`, `EMAIL`) VALUES
('1', 'TRẦN DẦN11dsfds', 'Nam', '2000-01-01', '0123456780', 'Cam Nghĩa 1', 'vantien18122002@gmailcom'),
('22', 'tiến', 'Nam', '2000-01-11', '0123456782', 'Cam Nghĩa 2', 'vantienn20dccn064@gmail.com'),
('ADMIN', 'LÊ VĂN TIẾN 1', 'Nữ', '2000-01-01', '0987918003', '210 MAN THIỆN 1', 'BATER@GMAIL.COM'),
('BH02', 'NGUYỄN HỮU ĐA', 'Nam', '2001-01-01', '0123456780', '210 MAN THIỆN1', '111@GMAIL.COM'),
('BH021', 'lê văn tiên1', 'Nam', '2000-01-01', '0123578544', 'Cam Nghĩa 1', 'vantien18122002@gmailcom'),
('BH03', 'Thái Văn Định', NULL, '2000-01-01', '0987918000', 'Cam Nghĩa ', 'bater@gmail.com'),
('BH05', 'Nguyễn V', NULL, '2000-01-01', '0987918000', 'Cam Nghĩa ', '.2407@gmail.c'),
('NV01', 'Lê Văn Vinh', 'Nam', '2002-12-18', '0399056780', 'vinhomes', 'n20dccn064@student.ptithcm.edu.vn'),
('Nv03', 'Nguyễn Thành Vinh', 'Nữ', '2000-01-12', '0123456782', 'Cam Nghĩa 2', 'vantienn20dccn064@gmail.com'),
('QL03', 'Thái Văn ĐỊnh', 'Nam', '2000-01-01', '0123485745', 'CAM RANH', 'BAR@GMAIL.COM');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhaphang`
--

CREATE TABLE `phieunhaphang` (
  `MANH` varchar(10) NOT NULL,
  `NGAYNHAP` timestamp NOT NULL DEFAULT current_timestamp(),
  `MANV` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhaphang`
--

INSERT INTO `phieunhaphang` (`MANH`, `NGAYNHAP`, `MANV`) VALUES
('1', '2024-12-20 05:27:28', 'QL03'),
('111', '2024-12-14 13:01:50', 'Nv03'),
('NH1', '2024-11-15 08:18:29', 'Nv03'),
('NH2', '2024-11-15 08:18:29', 'NV01'),
('Nh3', '2024-12-14 11:45:22', 'BH02'),
('nh4', '2024-12-14 11:45:48', 'BH02'),
('rr', '2024-12-14 13:00:45', 'QL03');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quyen`
--

CREATE TABLE `quyen` (
  `MAQUYEN` varchar(255) NOT NULL,
  `TENCV` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `quyen`
--

INSERT INTO `quyen` (`MAQUYEN`, `TENCV`) VALUES
('Q01', 'ADMIN'),
('Q02', 'Quản Li'),
('Q03', 'Bán Hàng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `MASP` varchar(50) NOT NULL,
  `TENSP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `HINHANH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MALOAI` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`MASP`, `TENSP`, `HINHANH`, `MALOAI`) VALUES
('banhPmtx', 'Bánh Phô Mai Trà Xanh', 'banh_phomaitraxanh.png', 'loai04'),
('banh_caramel', 'Bánh Caramel', 'banhCaramel1.jpg', 'loai04'),
('banh_chuoi', 'Bánh Chuối', 'banh_chuoi.png', 'loai04'),
('banh_tiramise', 'Bánh TIramise', 'banh_tiramise.png', 'loai04'),
('c2', 'Nước c2', 'c2.jpg', 'loai02'),
('Caffe_Espresso', 'Caffe_Espresso1', 'Caffe_Espresso.jpg', 'loai01'),
('mmm1', 'sdad', 'Caffe_Espresso.jpg', 'loai01'),
('Phindi cassia', 'Phindi Cassia', 'phindi cassia.jpg', 'LOAI5'),
('Phindi Hanh Nhan', 'Phindi Hạnh Nhân', 'phindi_hanhNhan.jpg', 'LOAI5'),
('Phindi socola', 'Phindi Chocolate', 'phindichoco.jpg', 'LOAI5'),
('phindi_KemSua', 'Phindi Kem Sữa', 'phindi_kemSua.jpg', 'LOAI5'),
('sp01', 'trà sữa a', 'trà đá.jpg', 'loai03'),
('sp02', 'Caffe Latte', 'tải xuống (3).jpg', 'loai01'),
('sp03', 'Caffe Mocha', 'trà sữa.jpg', 'loai03'),
('sp04', 'Pepsi', 'tải xuống (4).jpg', 'loai02'),
('sp05', 'Trà sữa Ô Long', 'trà sữa.jpg', 'loai03'),
('sp06', 'Bánh quy socola', 'banh quy.jpg', 'loai04'),
('sp07', 'Cà phê sữa (Milk Coffee)', 'tải xuống.jpg', 'loai01'),
('sp09', 'Cà phê phin', 'tải xuống (1).jpg', 'loai01'),
('sp10', 'Cappuccino', 'tải xuống (2).jpg', 'loai01'),
('sp6', 'Cà phê đen (Black Coffee)', '1639377797_ca-phe-den-da_6f4766ec0f8b4e929a8d916ae3c13254.jpg', 'loai01'),
('tra_anhDao', 'Trà Anh Đào', 'tra anh dao.jpg', 'LOAI6'),
('tra_oi', 'Trà Ổi Hồng', 'tra oi hong.jpg', 'LOAI6'),
('tra_senVang', 'Trà Sen Vàng', 'tra_senVang.jpg', 'LOAI6'),
('tra_thachVai', 'Trà Thạch Vải', 'tra_thachVai.jpg', 'LOAI6');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `size`
--

CREATE TABLE `size` (
  `MASIZE` varchar(10) NOT NULL,
  `TENSIZE` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `size`
--

INSERT INTO `size` (`MASIZE`, `TENSIZE`) VALUES
('L', 'Lớn'),
('M', 'Vừa'),
('S', 'Nhỏ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `USERNAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TRANGTHAI` tinyint(1) DEFAULT NULL,
  `MAQUYEN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`USERNAME`, `PASSWORD`, `TRANGTHAI`, `MAQUYEN`) VALUES
('Admin', 'Admin123', 1, 'Q01'),
('BH02', 'BH02123', 1, 'Q03'),
('BH03', 'BH03123', 0, 'Q03'),
('BH05', '123456', 1, 'Q03'),
('nv01', '634076', 1, 'Q01'),
('nv03', 'nv03123', 1, 'Q03'),
('QL03', '1', 1, 'Q02');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chi_tiet_khuyen_mai`
--
ALTER TABLE `chi_tiet_khuyen_mai`
  ADD PRIMARY KEY (`MAKM`,`MASP`,`MASIZE`),
  ADD KEY `MASP` (`MASP`),
  ADD KEY `MASIZE` (`MASIZE`);

--
-- Chỉ mục cho bảng `chi_tiet_nguyen_lieu`
--
ALTER TABLE `chi_tiet_nguyen_lieu`
  ADD PRIMARY KEY (`MASP`,`MASIZE`,`MANL`),
  ADD KEY `MASIZE` (`MASIZE`),
  ADD KEY `MANL` (`MANL`);

--
-- Chỉ mục cho bảng `chi_tiet_san_pham`
--
ALTER TABLE `chi_tiet_san_pham`
  ADD PRIMARY KEY (`MASP`,`MASIZE`),
  ADD KEY `MASIZE` (`MASIZE`),
  ADD KEY `chi_tiet_san_pham_ibfk_3` (`MACT`);

--
-- Chỉ mục cho bảng `congthuc`
--
ALTER TABLE `congthuc`
  ADD PRIMARY KEY (`MACT`);

--
-- Chỉ mục cho bảng `ct_hoadon`
--
ALTER TABLE `ct_hoadon`
  ADD PRIMARY KEY (`ID`,`MASP`,`MASIZE`),
  ADD KEY `FK_CT_HOADON_SANPHAM` (`MASP`),
  ADD KEY `FK_CT_HOADON_SIZE` (`MASIZE`),
  ADD KEY `FK_CT_HOADON_KHUYENMAI` (`MAKM`);

--
-- Chỉ mục cho bảng `ct_phieunhap`
--
ALTER TABLE `ct_phieunhap`
  ADD PRIMARY KEY (`MANH`,`MANL`),
  ADD KEY `FK_CT_PHIEUNHAP_NGUYENLIEU` (`MANL`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`IDHD`),
  ADD KEY `FK_HOADON_NHANVIEN` (`MANV`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`MAKM`);

--
-- Chỉ mục cho bảng `lichsu`
--
ALTER TABLE `lichsu`
  ADD PRIMARY KEY (`THOIGIAN`,`MASP`,`MASIZE`),
  ADD KEY `FK_LICHSU_SANPHAM` (`MASP`),
  ADD KEY `FK_LICHSU_SIZE` (`MASIZE`);

--
-- Chỉ mục cho bảng `loaisp`
--
ALTER TABLE `loaisp`
  ADD PRIMARY KEY (`MALOAI`);

--
-- Chỉ mục cho bảng `nguyenlieu`
--
ALTER TABLE `nguyenlieu`
  ADD PRIMARY KEY (`MANL`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MANV`),
  ADD KEY `idx_manv` (`MANV`);

--
-- Chỉ mục cho bảng `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD PRIMARY KEY (`MANH`),
  ADD KEY `FK_PHIEUNHAPHANG_MANV` (`MANV`);

--
-- Chỉ mục cho bảng `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`MAQUYEN`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MASP`),
  ADD KEY `MALOAI` (`MALOAI`);

--
-- Chỉ mục cho bảng `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`MASIZE`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`USERNAME`),
  ADD KEY `FK_TAIKHOAN_QUYEN` (`MAQUYEN`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chi_tiet_khuyen_mai`
--
ALTER TABLE `chi_tiet_khuyen_mai`
  ADD CONSTRAINT `chi_tiet_khuyen_mai_ibfk_1` FOREIGN KEY (`MAKM`) REFERENCES `khuyenmai` (`MAKM`),
  ADD CONSTRAINT `chi_tiet_khuyen_mai_ibfk_3` FOREIGN KEY (`MASIZE`) REFERENCES `size` (`MASIZE`);

--
-- Các ràng buộc cho bảng `chi_tiet_nguyen_lieu`
--
ALTER TABLE `chi_tiet_nguyen_lieu`
  ADD CONSTRAINT `chi_tiet_nguyen_lieu_ibfk_1` FOREIGN KEY (`MASP`) REFERENCES `sanpham` (`MASP`),
  ADD CONSTRAINT `chi_tiet_nguyen_lieu_ibfk_2` FOREIGN KEY (`MASIZE`) REFERENCES `size` (`MASIZE`),
  ADD CONSTRAINT `chi_tiet_nguyen_lieu_ibfk_3` FOREIGN KEY (`MANL`) REFERENCES `nguyenlieu` (`MANL`);

--
-- Các ràng buộc cho bảng `chi_tiet_san_pham`
--
ALTER TABLE `chi_tiet_san_pham`
  ADD CONSTRAINT `chi_tiet_san_pham_ibfk_1` FOREIGN KEY (`MASP`) REFERENCES `sanpham` (`MASP`),
  ADD CONSTRAINT `chi_tiet_san_pham_ibfk_2` FOREIGN KEY (`MASIZE`) REFERENCES `size` (`MASIZE`),
  ADD CONSTRAINT `chi_tiet_san_pham_ibfk_3` FOREIGN KEY (`MACT`) REFERENCES `congthuc` (`MACT`);

--
-- Các ràng buộc cho bảng `ct_hoadon`
--
ALTER TABLE `ct_hoadon`
  ADD CONSTRAINT `FK_CT_HOADON_KHUYENMAI` FOREIGN KEY (`MAKM`) REFERENCES `khuyenmai` (`MAKM`),
  ADD CONSTRAINT `FK_CT_HOADON_SANPHAM` FOREIGN KEY (`MASP`) REFERENCES `sanpham` (`MASP`),
  ADD CONSTRAINT `FK_CT_HOADON_SIZE` FOREIGN KEY (`MASIZE`) REFERENCES `size` (`MASIZE`),
  ADD CONSTRAINT `fk_ct_hoadon_hoadon` FOREIGN KEY (`ID`) REFERENCES `hoadon` (`IDHD`);

--
-- Các ràng buộc cho bảng `ct_phieunhap`
--
ALTER TABLE `ct_phieunhap`
  ADD CONSTRAINT `FK_CT_PHIEUNHAP_NGUYENLIEU` FOREIGN KEY (`MANL`) REFERENCES `nguyenlieu` (`MANL`),
  ADD CONSTRAINT `FK_CT_PHIEUNHAP_PHIEUNHAPHANG` FOREIGN KEY (`MANH`) REFERENCES `phieunhaphang` (`MANH`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `FK_HOADON_NHANVIEN` FOREIGN KEY (`MANV`) REFERENCES `nhanvien` (`MANV`);

--
-- Các ràng buộc cho bảng `lichsu`
--
ALTER TABLE `lichsu`
  ADD CONSTRAINT `FK_LICHSU_SANPHAM` FOREIGN KEY (`MASP`) REFERENCES `sanpham` (`MASP`),
  ADD CONSTRAINT `FK_LICHSU_SIZE` FOREIGN KEY (`MASIZE`) REFERENCES `size` (`MASIZE`);

--
-- Các ràng buộc cho bảng `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD CONSTRAINT `FK_PHIEUNHAPHANG_MANV` FOREIGN KEY (`MANV`) REFERENCES `nhanvien` (`MANV`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`MALOAI`) REFERENCES `loaisp` (`MALOAI`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_TAIKHOAN_NHANVIEN` FOREIGN KEY (`USERNAME`) REFERENCES `nhanvien` (`MANV`),
  ADD CONSTRAINT `FK_TAIKHOAN_QUYEN` FOREIGN KEY (`MAQUYEN`) REFERENCES `quyen` (`MAQUYEN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
