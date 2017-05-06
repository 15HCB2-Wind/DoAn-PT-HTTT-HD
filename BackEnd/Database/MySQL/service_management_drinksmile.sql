-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2017 at 01:32 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `service_management_drinksmile`
--

-- --------------------------------------------------------

--
-- Table structure for table `bo`
--

CREATE TABLE `bo` (
  `mabo` varchar(10) NOT NULL,
  `machip` varchar(10) DEFAULT NULL,
  `mausac` varchar(50) DEFAULT NULL,
  `coditat` bit(1) DEFAULT NULL,
  `nhandang` varchar(50) DEFAULT NULL,
  `tinhtrang` varchar(50) DEFAULT NULL,
  `daxoa` bit(1) DEFAULT NULL,
  `machuong` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bo`
--

INSERT INTO `bo` (`mabo`, `machip`, `mausac`, `coditat`, `nhandang`, `tinhtrang`, `daxoa`, `machuong`) VALUES
('Bo001', 'Chip001', 'Vàng', b'0', 'Nó màu vàng', 'Tốt', b'0', 'CT001'),
('Bo002', 'Chip002', 'Đỏ', b'0', 'Nó màu đỏ', 'Tốt', b'0', 'CT001'),
('Bo003', 'Chip003', 'Nâu', b'0', 'Nó màu nâu', 'Tốt', b'0', 'CT002');

-- --------------------------------------------------------

--
-- Table structure for table `chinhanh`
--

CREATE TABLE `chinhanh` (
  `machinhanh` varchar(10) NOT NULL,
  `tenchinhanh` varchar(45) DEFAULT NULL,
  `sodt` varchar(45) DEFAULT NULL,
  `diachi` varchar(45) DEFAULT NULL,
  `tinhtrang` varchar(45) DEFAULT NULL,
  `daxoa` bit(1) DEFAULT NULL,
  `quanly` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chinhanh`
--

INSERT INTO `chinhanh` (`machinhanh`, `tenchinhanh`, `sodt`, `diachi`, `tinhtrang`, `daxoa`, `quanly`) VALUES
('CN001', 'Trại bò Tân Thành', '0909091109', 'Số 1 Lê Duẩn', 'Đang hoạt động', b'0', 'NV001'),
('CN002', 'Trại bò Hoàng Nam', '0123456789', '66 Hoàng Văn Thụ', 'Đang hoạt động', b'0', 'NV002');

-- --------------------------------------------------------

--
-- Table structure for table `chuongtrai`
--

CREATE TABLE `chuongtrai` (
  `machuong` varchar(10) NOT NULL,
  `tenchuong` varchar(50) DEFAULT NULL,
  `succhua` double DEFAULT NULL,
  `tinhtrang` varchar(50) DEFAULT NULL,
  `daxoa` bit(1) DEFAULT NULL,
  `machinhanh` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chuongtrai`
--

INSERT INTO `chuongtrai` (`machuong`, `tenchuong`, `succhua`, `tinhtrang`, `daxoa`, `machinhanh`) VALUES
('CT001', 'Chuồng Hồi Sức-01', 2, 'Đang hoạt động', b'0', 'CN001'),
('CT002', 'Chuồng Lấy Sữa-02', 4, 'Đang hoạt động', b'0', 'CN001'),
('CT003', 'Chuồng Sanh Đẻ-01', 2, 'Đang hoạt động', b'0', 'CN001');

-- --------------------------------------------------------

--
-- Table structure for table `khosua`
--

CREATE TABLE `khosua` (
  `makho` varchar(10) NOT NULL,
  `tenkho` varchar(50) DEFAULT NULL,
  `succhua` double DEFAULT NULL,
  `luongsuaco` double DEFAULT NULL,
  `diachi` varchar(50) DEFAULT NULL,
  `daxoa` bit(1) DEFAULT NULL,
  `tinhtrang` varchar(50) DEFAULT NULL,
  `machinhanh` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manhanvien` varchar(10) NOT NULL,
  `hoten` varchar(50) DEFAULT NULL,
  `gioitinh` varchar(5) DEFAULT NULL,
  `ngaysinh` date DEFAULT NULL,
  `sodt` varchar(50) DEFAULT NULL,
  `diachi` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tentaikhoan` varchar(30) DEFAULT NULL,
  `matkhau` varchar(45) DEFAULT NULL,
  `tinhtrang` varchar(45) DEFAULT NULL,
  `daxoa` bit(1) DEFAULT NULL,
  `machinhanh` varchar(10) DEFAULT NULL,
  `maphanquyen` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`manhanvien`, `hoten`, `gioitinh`, `ngaysinh`, `sodt`, `diachi`, `email`, `tentaikhoan`, `matkhau`, `tinhtrang`, `daxoa`, `machinhanh`, `maphanquyen`) VALUES
('NV001', 'test1', 'Nam', '1994-12-13', '0909090909', 'tao lao', 'tao lao 1', 'tk001', '&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'okokok', b'0', 'CN001', 'PQ001');

-- --------------------------------------------------------

--
-- Table structure for table `phanquyen`
--

CREATE TABLE `phanquyen` (
  `maphanquyen` varchar(10) NOT NULL,
  `tenphanquyen` varchar(45) DEFAULT NULL,
  `capphanquyen` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `phanquyen`
--

INSERT INTO `phanquyen` (`maphanquyen`, `tenphanquyen`, `capphanquyen`) VALUES
('PQ001', 'Nhân viên chăm sóc', 1),
('PQ002', 'Quản lý chi nhánh', 2),
('PQ003', 'Tổng giám đốc', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bo`
--
ALTER TABLE `bo`
  ADD PRIMARY KEY (`mabo`);

--
-- Indexes for table `chinhanh`
--
ALTER TABLE `chinhanh`
  ADD PRIMARY KEY (`machinhanh`);

--
-- Indexes for table `chuongtrai`
--
ALTER TABLE `chuongtrai`
  ADD PRIMARY KEY (`machuong`);

--
-- Indexes for table `khosua`
--
ALTER TABLE `khosua`
  ADD PRIMARY KEY (`makho`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manhanvien`),
  ADD UNIQUE KEY `manhanvien_UNIQUE` (`manhanvien`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD UNIQUE KEY `tentaikhoan_UNIQUE` (`tentaikhoan`);

--
-- Indexes for table `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`maphanquyen`),
  ADD UNIQUE KEY `capphanquyen_UNIQUE` (`capphanquyen`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
