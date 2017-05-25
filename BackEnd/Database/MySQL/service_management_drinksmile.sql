-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 25, 2017 at 01:22 PM
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
('Bo001', 'Chip001', 'Vàng', b'0', 'Nó màu vàng', 'Đang ốm.', b'0', 'CT003'),
('Bo002', 'Chip002', 'Đỏ', b'1', 'Nó màu đỏ', 'Khỏe mạnh.', b'0', 'CT001'),
('Bo003', 'Chip003', 'Nâu', b'1', 'Nó màu nâu', '- - - - -', b'1', 'CT002'),
('COW00004', 'XR-CP01', 'Xanh đen', b'0', 'Hay đi lò cò.', 'Khỏe mạnh.', b'0', 'CT001'),
('COW00005', 'LKa01', 'Xanh nâu đen', b'0', '', 'Khỏe mạnh.', b'0', 'CT002');

-- --------------------------------------------------------

--
-- Table structure for table `chinhanh`
--

CREATE TABLE `chinhanh` (
  `machinhanh` varchar(10) NOT NULL,
  `tenchinhanh` varchar(50) DEFAULT NULL,
  `sodt` varchar(15) DEFAULT NULL,
  `diachi` varchar(100) DEFAULT NULL,
  `tinhtrang` varchar(50) DEFAULT NULL,
  `daxoa` bit(1) DEFAULT NULL,
  `quanly` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chinhanh`
--

INSERT INTO `chinhanh` (`machinhanh`, `tenchinhanh`, `sodt`, `diachi`, `tinhtrang`, `daxoa`, `quanly`) VALUES
('CN001', 'Trại bò Tân Thành', '0909091109', 'Số 1 Lê Duẩn', 'Đang hoạt động', b'0', 'NV001');

-- --------------------------------------------------------

--
-- Table structure for table `chuongtrai`
--

CREATE TABLE `chuongtrai` (
  `machuong` varchar(10) NOT NULL,
  `tenchuong` varchar(50) DEFAULT NULL,
  `succhua` int(11) DEFAULT NULL,
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
  `diachi` varchar(100) DEFAULT NULL,
  `daxoa` bit(1) DEFAULT NULL,
  `tinhtrang` varchar(50) DEFAULT NULL,
  `machinhanh` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `khosua`
--

INSERT INTO `khosua` (`makho`, `tenkho`, `succhua`, `luongsuaco`, `diachi`, `daxoa`, `tinhtrang`, `machinhanh`) VALUES
('KS00001', 'Kho thường', 10000, 100, 'xxx yyy', b'0', 'Đang được sử dụng.', 'CN001'),
('KS00002', 'Kho lạnh', 4000, 0, 'asd qqq', b'0', 'Đang được sữa chửa.', 'CN001'),
('KS00003', 'Kho lạnh 2', 5000, 0, 'qwe qwe zzz', b'1', 'Ngưng hoạt động.', 'CN001');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `manhacungcap` varchar(10) NOT NULL,
  `ten` varchar(50) DEFAULT NULL,
  `diachi` varchar(100) DEFAULT NULL,
  `tinhtrang` varchar(50) DEFAULT NULL
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
  `sodt` varchar(15) DEFAULT NULL,
  `diachi` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `tentaikhoan` varchar(50) DEFAULT NULL,
  `matkhau` varchar(50) DEFAULT NULL,
  `tinhtrang` varchar(50) DEFAULT NULL,
  `daxoa` bit(1) DEFAULT NULL,
  `machinhanh` varchar(10) DEFAULT NULL,
  `maphanquyen` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`manhanvien`, `hoten`, `gioitinh`, `ngaysinh`, `sodt`, `diachi`, `email`, `tentaikhoan`, `matkhau`, `tinhtrang`, `daxoa`, `machinhanh`, `maphanquyen`) VALUES
('testGD', 'Giám đốc', 'Nam', NULL, NULL, NULL, 'c@gmail.com', 'giamdoc', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'CN001', 'PQ003'),
('testNV', 'Nhân viên', 'Nam', NULL, NULL, NULL, 'a@gmail.com', 'nhanvien', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'CN001', 'PQ001'),
('testQL', 'Quản lý', 'Nam', NULL, NULL, NULL, 'b@gmail.com', 'quanly', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'CN001', 'PQ002');

-- --------------------------------------------------------

--
-- Table structure for table `phanquyen`
--

CREATE TABLE `phanquyen` (
  `maphanquyen` varchar(10) NOT NULL,
  `tenphanquyen` varchar(50) DEFAULT NULL,
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
  ADD PRIMARY KEY (`mabo`),
  ADD UNIQUE KEY `machip_UNIQUE` (`machip`);

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
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`manhacungcap`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manhanvien`),
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
