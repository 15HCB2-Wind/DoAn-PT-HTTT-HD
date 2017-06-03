-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 02, 2017 lúc 11:43 SA
-- Phiên bản máy phục vụ: 5.7.14
-- Phiên bản PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `service_importexport_drinksmile`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietxuatsua`
--

CREATE TABLE `chitietxuatsua` (
  `machungtu` varchar(10) NOT NULL,
  `makho` varchar(10) DEFAULT NULL,
  `luongsuaxuat` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `counter`
--

CREATE TABLE `counter` (
  `areaid` varchar(3) NOT NULL,
  `index_phieuxuat` int(11) DEFAULT '0',
  `index_phieunhap` int(11) DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `counter`
--

INSERT INTO `counter` (`areaid`, `index_phieuxuat`, `index_phieunhap`) VALUES
('HCM', 0, 2),
('HN', 0, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhapbo`
--

CREATE TABLE `phieunhapbo` (
  `machungtu` varchar(10) NOT NULL,
  `ngaylap` date DEFAULT NULL,
  `ngaynhap` date DEFAULT NULL,
  `soluong` int(11) DEFAULT NULL,
  `dahuy` bit(1) DEFAULT NULL,
  `mancc` varchar(10) DEFAULT NULL,
  `manv` varchar(10) DEFAULT NULL,
  `macn` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `phieunhapbo`
--

INSERT INTO `phieunhapbo` (`machungtu`, `ngaylap`, `ngaynhap`, `soluong`, `dahuy`, `mancc`, `manv`, `macn`) VALUES
('HCMCT00001', '2017-03-01', '2017-04-01', 15, b'0', 'HCMNCC0001', 'HCMNV00001', 'HCMCN001'),
('HCMCT00002', '2017-04-01', '2017-05-01', 15, b'0', 'HCMNCC0003', 'HCMNV00001', 'HCMCN002'),
('HNCT00001', '2017-04-01', '2017-05-01', 15, b'0', 'HNNCC0002', 'HCMNV00001', 'HNCN001');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieuxuat`
--

CREATE TABLE `phieuxuat` (
  `machungtu` varchar(10) NOT NULL,
  `ngaylap` date DEFAULT NULL,
  `ngayxuat` date DEFAULT NULL,
  `tongluongsua` double DEFAULT NULL,
  `dahuy` bit(1) DEFAULT NULL,
  `lydo` varchar(100) DEFAULT NULL,
  `manv` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietxuatsua`
--
ALTER TABLE `chitietxuatsua`
  ADD PRIMARY KEY (`machungtu`);

--
-- Chỉ mục cho bảng `counter`
--
ALTER TABLE `counter`
  ADD PRIMARY KEY (`areaid`);

--
-- Chỉ mục cho bảng `phieunhapbo`
--
ALTER TABLE `phieunhapbo`
  ADD PRIMARY KEY (`machungtu`);

--
-- Chỉ mục cho bảng `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`machungtu`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
