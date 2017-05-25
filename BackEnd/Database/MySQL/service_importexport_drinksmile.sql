-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 25, 2017 lúc 11:10 SA
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
