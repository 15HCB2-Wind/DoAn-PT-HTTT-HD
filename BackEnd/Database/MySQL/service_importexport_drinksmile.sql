-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2017 at 06:53 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `service_importexport_drinksmile`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `tkNhapBo` (`tuNgay` DATE, `denNgay` DATE)  BEGIN
	select machungtu, ngaynhap, soluong, mancc, macn, manv
	from service_importexport_drinksmile.phieunhapbo
	where 	ngaynhap between tuNgay and denNgay and
			dahuy = false;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `chitietxuatsua`
--

CREATE TABLE `chitietxuatsua` (
  `machungtu` varchar(10) NOT NULL,
  `makho` varchar(10) NOT NULL,
  `luongsuaxuat` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chitietxuatsua`
--

INSERT INTO `chitietxuatsua` (`machungtu`, `makho`, `luongsuaxuat`) VALUES
('HCMPX00001', 'HCMKS0001', 3000),
('HCMPX00002', 'HCMKS0004', 4000),
('HNPX00001', 'HNKS0001', 3000),
('HNPX00002', 'HNKS0001', 2000),
('HCMPX00003', 'HCMKS0001', 5000),
('HCMPX00004', 'HCMKS0004', 5000),
('HCMPX00001', 'HCMKS0002', 2000),
('HCMPX00002', 'HCMKS0005', 1000),
('HNPX00002', 'HNKS0002', 3000),
('HNPX00001', 'HNKS0002', 2000);

-- --------------------------------------------------------

--
-- Table structure for table `counter`
--

CREATE TABLE `counter` (
  `areaid` varchar(3) NOT NULL,
  `index_phieuxuat` int(11) DEFAULT '0',
  `index_phieunhap` int(11) DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `counter`
--

INSERT INTO `counter` (`areaid`, `index_phieuxuat`, `index_phieunhap`) VALUES
('HCM', 4, 2),
('HN', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieunhapbo`
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
-- Dumping data for table `phieunhapbo`
--

INSERT INTO `phieunhapbo` (`machungtu`, `ngaylap`, `ngaynhap`, `soluong`, `dahuy`, `mancc`, `manv`, `macn`) VALUES
('HCMPN00001', '2016-03-01', '2016-04-01', 15, b'0', 'HCMNCC0001', 'HCMNV00001', 'HCMCN001'),
('HCMPN00002', '2016-04-01', '2016-05-01', 15, b'0', 'HCMNCC0003', 'HCMNV00001', 'HCMCN002'),
('HNPN00001', '2016-04-01', '2016-05-01', 15, b'0', 'HNNCC0002', 'HCMNV00001', 'HNCN001');

-- --------------------------------------------------------

--
-- Table structure for table `phieuxuat`
--

CREATE TABLE `phieuxuat` (
  `machungtu` varchar(10) NOT NULL,
  `ngaylap` date DEFAULT NULL,
  `ngayxuat` date DEFAULT NULL,
  `tongluongsua` double DEFAULT NULL,
  `dahuy` bit(1) DEFAULT NULL,
  `lydo` varchar(100) DEFAULT NULL,
  `manv` varchar(10) DEFAULT NULL,
  `macn` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `phieuxuat`
--

INSERT INTO `phieuxuat` (`machungtu`, `ngaylap`, `ngayxuat`, `tongluongsua`, `dahuy`, `lydo`, `manv`, `macn`) VALUES
('HCMPX00001', '2016-11-01', '2016-11-08', 5000, b'0', 'Nguyên vật liệu cho nhà máy kẹo', 'HCMNV00002', 'HCMCN001'),
('HCMPX00002', '2016-11-01', '2016-11-08', 5000, b'0', 'Nguyên vật liệu cho nhà máy kẹo', 'HCMNV00010', 'HCMCN002'),
('HNPX00001', '2016-12-01', '2016-12-08', 5000, b'0', 'Nguyên vật liệu cho nhà máy kẹo', 'HNNV00001', 'HNCN001'),
('HNPX00002', '2017-06-01', '2017-06-08', 5000, b'0', 'Nguyên vật liệu cho nhà máy sửa', 'HNNV00001', 'HNCN001'),
('HCMPX00003', '2017-06-01', '2017-06-08', 5000, b'0', 'Nguyên vật liệu cho nhà máy sửa', 'HCMNV00002', 'HCMCN001'),
('HCMPX00004', '2017-06-01', '2017-06-08', 5000, b'0', 'Nguyên vật liệu cho nhà máy sửa', 'HCMNV00010', 'HCMCN002');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietxuatsua`
--
ALTER TABLE `chitietxuatsua`
  ADD PRIMARY KEY (`machungtu`,`makho`);

--
-- Indexes for table `counter`
--
ALTER TABLE `counter`
  ADD PRIMARY KEY (`areaid`);

--
-- Indexes for table `phieunhapbo`
--
ALTER TABLE `phieunhapbo`
  ADD PRIMARY KEY (`machungtu`);

--
-- Indexes for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`machungtu`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
