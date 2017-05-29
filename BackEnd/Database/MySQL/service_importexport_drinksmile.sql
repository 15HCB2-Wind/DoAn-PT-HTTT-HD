-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2017 at 04:12 AM
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

-- --------------------------------------------------------

--
-- Table structure for table `chitietxuatsua`
--

CREATE TABLE `chitietxuatsua` (
  `machungtu` varchar(10) NOT NULL,
  `makho` varchar(10) DEFAULT NULL,
  `luongsuaxuat` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
('HCMCT00001', '2017-03-01', '2017-04-01', 15, b'0', 'HCMNCC0001', 'HCMNV00001', 'HCMCN001'),
('HCMCT00002', '2017-04-01', '2017-05-01', 15, b'0', 'HCMNCC0003', 'HCMNV00001', 'HCMCN002'),
('HNCT00001', '2017-04-01', '2017-05-01', 15, b'0', 'HNNCC0002', 'HCMNV00001', 'HNCN001');

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
  `manv` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tables_counter`
--

CREATE TABLE `tables_counter` (
  `key` varchar(3) NOT NULL,
  `phieuxuat` int(11) DEFAULT '0',
  `phieunhap` int(11) DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tables_counter`
--

INSERT INTO `tables_counter` (`key`, `phieuxuat`, `phieunhap`) VALUES
('HCM', 0, 2),
('HN', 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietxuatsua`
--
ALTER TABLE `chitietxuatsua`
  ADD PRIMARY KEY (`machungtu`);

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

--
-- Indexes for table `tables_counter`
--
ALTER TABLE `tables_counter`
  ADD PRIMARY KEY (`key`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
