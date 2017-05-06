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
  `manv` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
  `lydo` varchar(50) DEFAULT NULL,
  `manv` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
