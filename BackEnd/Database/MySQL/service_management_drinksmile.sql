-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 10, 2017 lúc 10:41 SA
-- Phiên bản máy phục vụ: 5.7.14
-- Phiên bản PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `service_management_drinksmile`
--

DELIMITER $$
--
-- Thủ tục
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `tkSoLuongBo` (`maChiNhanh` VARCHAR(10))  BEGIN
	select x.machuong, x.tenchuong, COALESCE(z.sobolythuyet, 0) as sobolythuyet, x.dangchua, x.succhua
    from 
		(select a.machuong, count(a.mabo) as sobolythuyet
        from service_management_drinksmile.bo a, service_management_drinksmile.chuongtrai b
        where 	a.machuong = b.machuong and
				b.machinhanh = maChiNhanh
		group by b.machuong) z
        right join
		(select a.machuong, a.tenchuong, a.dangchua, a.succhua, a.daxoa
		from service_management_drinksmile.chuongtrai a
		where a.machinhanh = maChiNhanh) x on z.machuong = x.machuong;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `tkSoLuongBo_AllAgencies` ()  BEGIN
	select t.machinhanh, cn.tenchinhanh, sum(t.sobolythuyet) as sobolythuyet, sum(t.dangchua) as dangchua, sum(t.succhua) as succhuatoida
	from
		(select b.machuong, b.machinhanh, count(a.mabo) as sobolythuyet, b.dangchua, b.succhua
		from service_management_drinksmile.bo a, service_management_drinksmile.chuongtrai b
		where 	a.machuong = b.machuong and
				b.daxoa = false
		group by a.machuong) t, service_management_drinksmile.chinhanh cn
	where t.machinhanh = cn.machinhanh
	group by t.machinhanh;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bo`
--

CREATE TABLE `bo` (
  `mabo` varchar(10) NOT NULL,
  `machip` varchar(10) DEFAULT NULL,
  `mausac` varchar(50) DEFAULT NULL,
  `coditat` bit(1) DEFAULT NULL,
  `nhandang` varchar(50) DEFAULT NULL,
  `tinhtrang` varchar(50) DEFAULT NULL,
  `daxoa` bit(1) DEFAULT NULL,
  `machuong` varchar(10) DEFAULT NULL,
  `ngaynhan` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `bo`
--

INSERT INTO `bo` (`mabo`, `machip`, `mausac`, `coditat`, `nhandang`, `tinhtrang`, `daxoa`, `machuong`, `ngaynhan`) VALUES
('HCMB000001', 'C001', 'Màu vàng', b'0', 'Nó màu vàng', 'Đang ốm.', b'0', 'HCMCT00006', '2016-04-01'),
('HCMB000002', 'C002', 'Màu đỏ', b'0', 'Thì tao màu đỏ', 'Khỏe mạnh.', b'0', 'HCMCT00001', '2016-04-01'),
('HCMB000003', 'C003', 'Màu nâu', b'0', 'Nhận dạng làm gì, cho bò quét võng mạc à', '- - - - -', b'1', 'HCMCT00001', '2016-04-01'),
('HCMB000004', 'XR-CP01', 'Màu xanh đen', b'0', 'Hay đi lò cò', 'Khỏe mạnh.', b'0', 'HCMCT00001', '2016-04-01'),
('HCMB000005', 'LKa01', 'Màu xanh nâu đen', b'0', 'Bò đi lạc', 'Khỏe mạnh.', b'0', 'HCMCT00001', '2016-04-01'),
('HCMB000006', 'AEFR8', 'Nâu hồng', b'0', 'Nâu hồng là màu đết gì thế', 'Khỏe mạnh.', b'0', 'HCMCT00002', '2016-04-01'),
('HCMB000007', 'LGK90', 'Màu hường', b'0', 'Ai biết em ở đâu xin đưa e về', 'Khỏe mạnh.', b'0', 'HCMCT00002', '2016-04-01'),
('HCMB000008', 'LLGK909', 'Màu cà phê', b'1', 'Trên đầu có lỗ đạn', 'Khỏe mạnh.', b'0', 'HCMCT00002', '2016-04-01'),
('HCMB000009', 'GHN81', 'Màu ca cao', b'0', 'Đã thiến', 'Đang ốm.', b'0', 'HCMCT00006', '2016-04-01'),
('HCMB000010', 'C004', 'Màu cần', b'0', 'Con bò là con gì', 'Đang ốm.', b'0', 'HCMCT00006', '2016-04-01'),
('HCMB000011', 'C005', 'Màu kinh nguyệt', b'1', 'Đau đớn nhất cuộc đời em là gì', 'Khỏe mạnh.', b'0', 'HCMCT00003', '2016-04-01'),
('HCMB000012', 'GHA055', 'Màu chàm', b'1', 'Tao bị nguyền', 'Khỏe mạnh.', b'0', 'HCMCT00003', '2016-04-01'),
('HCMB000013', 'KL-A01', 'Màu tím', b'0', 'Thích bị chịch', 'Đang ốm.', b'0', 'HCMCT00006', '2016-04-01'),
('HCMB000014', 'LKa02', 'Bảy sắc cầu vồng', b'0', 'Cuồng dâm bò', 'Khỏe mạnh.', b'0', 'HCMCT00003', '2016-04-01'),
('HCMB000015', 'C006', 'Màu màu cái lồng', b'1', 'Chết rồi nhận dạng làm đết gì', '- - - - -', b'1', 'HCMCT00003', '2016-04-01'),
('HCMB000016', 'KL-A02', 'Transparent', b'0', 'Đời em là những chiện không ngờ', 'Khỏe mạnh.', b'0', 'HCMCT00012', '2016-05-01'),
('HCMB000017', 'LKa03', 'Opacity zero', b'1', 'Cant you see me', 'Đang ốm.', b'0', 'HCMCT00020', '2016-05-01'),
('HCMB000018', 'LKa04', 'Màu nền', b'0', 'Em là tắt kè', 'Đang ốm.', b'0', 'HCMCT00020', '2016-05-01'),
('HCMB000019', 'C007', 'Màu đen', b'0', 'Conan tuổi gì', 'Khỏe mạnh.', b'0', 'HCMCT00012', '2016-05-01'),
('HCMB000020', 'LKa05', 'Màu Hover', b'0', 'Đừng chạm vào em', 'Khỏe mạnh.', b'0', 'HCMCT00012', '2016-05-01'),
('HCMB000021', 'C008', 'Hidden', b'0', 'O_O', 'Khỏe mạnh.', b'0', 'HCMCT00013', '2016-05-01'),
('HCMB000022', 'C009', 'Màu bikini', b'0', 'Nghề tay trái là người mẫu bikini', 'Khỏe mạnh.', b'0', 'HCMCT00013', '2016-05-01'),
('HCMB000023', 'LKa06', 'Màu diva', b'1', 'Diva chuyên nghiệp', 'Khỏe mạnh.', b'0', 'HCMCT00013', '2016-05-01'),
('HCMB000024', 'C010', 'Màu lạ', b'0', 'Chủ tịch nước cũng chỉ là con bò', 'Khỏe mạnh.', b'0', 'HCMCT00013', '2016-05-01'),
('HCMB000025', 'LKa07', 'Màu victim', b'0', 'Victim for what?', 'Khỏe mạnh.', b'0', 'HCMCT00013', '2016-05-01'),
('HCMB000026', 'C011', 'Màu ecchi', b'1', 'Một tâm hồn dâm dục', 'Khỏe mạnh.', b'0', 'HCMCT00014', '2016-05-01'),
('HCMB000027', 'C012', 'Màu texas', b'0', 'I am Lucky Luke', 'Khỏe mạnh.', b'0', 'HCMCT00014', '2016-05-01'),
('HCMB000028', 'LKa08', 'Màu bầu trời', b'1', 'Sky đâu rồi', 'Đang ốm.', b'0', 'HCMCT00020', '2016-05-01'),
('HCMB000029', 'LKa09', 'Màu sọc', b'0', 'Không phải ngựa vằn đâu', 'Khỏe mạnh.', b'0', 'HCMCT00014', '2016-05-01'),
('HCMB000030', 'C013', 'Màu chịch', b'0', '69 kiểu 96 tư thế', 'Khỏe mạnh.', b'0', 'HCMCT00014', '2016-05-01'),
('HNB000001', 'UN-M01', 'Màu da', b'0', 'Thịt bò dai lắm', 'Đang ốm.', b'0', 'HNCT00006', '2016-05-01'),
('HNB000002', '37HJ-05', 'Màu X', b'1', 'Siêu nhân biến hình', 'Khỏe mạnh.', b'0', 'HNCT00001', '2016-05-01'),
('HNB000003', 'UN-M02', 'Màu tội ác', b'0', 'Thịt bò ngon nhất', 'Khỏe mạnh.', b'0', 'HNCT00001', '2016-05-01'),
('HNB000004', '37HJ-04', 'Màu chết chóc', b'1', 'Chết từ trong trứng, vậy sao có dị tật', '- - - - -', b'1', 'HNCT00001', '2016-05-01'),
('HNB000005', 'UN-M03', 'Màu huyền bí', b'0', 'Mày là ai', 'Khỏe mạnh.', b'0', 'HNCT00001', '2016-05-01'),
('HNB000006', 'UN-M04', 'Màu trắng', b'0', 'Em là con bò bình thường nhất', 'Khỏe mạnh.', b'0', 'HNCT00002', '2016-05-01'),
('HNB000007', '37HJ-03', 'Màu đốm', b'0', 'Chắc bố em là chó đốm', 'Khỏe mạnh.', b'0', 'HNCT00002', '2016-05-01'),
('HNB000008', '37HJ-02', 'Màu trinh', b'1', 'Còn trinh sao vô đây vắt sữa emm', 'Đang ốm.', b'0', 'HNCT00006', '2016-05-01'),
('HNB000009', 'UN-M05', 'Màu hi vọng', b'1', 'Hi vọng đi rồi thất vọng', 'Khỏe mạnh.', b'0', 'HNCT00002', '2016-05-01'),
('HNB000010', 'JGJ951', 'Màu kotex', b'0', 'Em bay chứ không bò', 'Đang ốm.', b'0', 'HNCT00006', '2016-05-01'),
('HNB000011', 'UN-M06', 'Màu hentai', b'1', 'Thiếu 1 dái là em', 'Khỏe mạnh.', b'0', 'HNCT00003', '2016-05-01'),
('HNB000012', '37HJ-01', 'Màu xấu', b'0', 'Tao có phải cá xấu đâu mà bò với trườn', 'Khỏe mạnh.', b'0', 'HNCT00003', '2016-05-01'),
('HNB000013', 'LMK-X51', 'Màu xám', b'1', 'Em mất trong một bữa tiệc', '- - - - -', b'1', 'HNCT00003', '2016-05-01'),
('HNB000014', 'UN-M07', 'Màu gì', b'1', 'Tao là ai và đây là đâu', 'Khỏe mạnh.', b'0', 'HNCT00003', '2016-05-01'),
('HNB000015', 'UN-M08', 'Màu zombie', b'1', 'Diễn viên zombie bò chuyện nghiệp', 'Khỏe mạnh.', b'0', 'HNCT00003', '2016-05-01');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chinhanh`
--

CREATE TABLE `chinhanh` (
  `machinhanh` varchar(10) NOT NULL,
  `tenchinhanh` varchar(50) DEFAULT NULL,
  `sodt` varchar(15) DEFAULT NULL,
  `diachi` varchar(100) DEFAULT NULL,
  `tinhtrang` varchar(50) DEFAULT NULL,
  `daxoa` bit(1) DEFAULT NULL,
  `quanly` varchar(10) DEFAULT NULL,
  `khotam` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `chinhanh`
--

INSERT INTO `chinhanh` (`machinhanh`, `tenchinhanh`, `sodt`, `diachi`, `tinhtrang`, `daxoa`, `quanly`, `khotam`) VALUES
('HCMCN001', 'Trại bò Tân Thành', '0909091101', 'Số 1 Lê Duẩn', 'Đang hoạt động.', b'0', 'HCMNV00002', 'HCMKS0001'),
('HCMCN002', 'Trại bò Thủ Đức', '0909091102', 'Số 2 Kha Vạn Cân', 'Đang hoạt động.', b'0', 'HCMNV00010', 'HCMKS0004'),
('HNCN001', 'Trại bò Hồ Gươm', '0909091103', 'Số 3 Hồ Gươm', 'Đang hoạt động.', b'0', 'HNNV00001', 'HNKS0001');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chuongtrai`
--

CREATE TABLE `chuongtrai` (
  `machuong` varchar(10) NOT NULL,
  `tenchuong` varchar(50) DEFAULT NULL,
  `succhua` int(11) DEFAULT NULL,
  `dangchua` int(11) DEFAULT NULL,
  `tinhtrang` varchar(50) DEFAULT NULL,
  `daxoa` bit(1) DEFAULT NULL,
  `machinhanh` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `chuongtrai`
--

INSERT INTO `chuongtrai` (`machuong`, `tenchuong`, `succhua`, `dangchua`, `tinhtrang`, `daxoa`, `machinhanh`) VALUES
('HCMCT00001', 'Chuồng Lấy Sữa - 01', 20, 3, 'Đang hoạt động.', b'0', 'HCMCN001'),
('HCMCT00002', 'Chuồng Lấy Sữa - 02', 20, 3, 'Đang hoạt động.', b'0', 'HCMCN001'),
('HCMCT00003', 'Chuồng Lấy Sữa - 03', 20, 3, 'Đang hoạt động.', b'0', 'HCMCN001'),
('HCMCT00004', 'Chuồng Lấy Sữa - 04', 20, 0, 'Đang hoạt động.', b'0', 'HCMCN001'),
('HCMCT00005', 'Chuồng Lấy Sữa - 05', 20, 0, 'Đang hoạt động.', b'0', 'HCMCN001'),
('HCMCT00006', 'Chuồng Hồi Sức - 01', 20, 4, 'Đang hoạt động.', b'0', 'HCMCN001'),
('HCMCT00007', 'Chuồng Hồi Sức - 02', 20, 0, 'Đang hoạt động.', b'0', 'HCMCN001'),
('HCMCT00008', 'Chuồng Sanh Đẻ - 01', 10, 0, 'Đang hoạt động.', b'0', 'HCMCN001'),
('HCMCT00009', 'Chuồng Sanh Đẻ - 02', 10, 0, 'Đang hoạt động.', b'0', 'HCMCN001'),
('HCMCT00010', 'Chuồng Bò Non - 01', 10, 0, 'Đang hoạt động.', b'0', 'HCMCN001'),
('HCMCT00011', 'Chuồng Bò Non - 02', 10, 0, 'Đang hoạt động.', b'0', 'HCMCN001'),
('HCMCT00012', 'Chuồng Lấy Sữa - 01', 20, 3, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HCMCT00013', 'Chuồng Lấy Sữa - 02', 20, 5, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HCMCT00014', 'Chuồng Lấy Sữa - 03', 20, 4, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HCMCT00015', 'Chuồng Lấy Sữa - 04', 20, 0, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HCMCT00016', 'Chuồng Lấy Sữa - 05', 20, 0, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HCMCT00017', 'Chuồng Lấy Sữa - 06', 20, 0, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HCMCT00018', 'Chuồng Lấy Sữa - 07', 20, 0, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HCMCT00019', 'Chuồng Lấy Sữa - 08', 20, 0, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HCMCT00020', 'Chuồng Hồi Sức - 01', 25, 3, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HCMCT00021', 'Chuồng Hồi Sức - 02', 25, 0, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HCMCT00022', 'Chuồng Sanh Đẻ - 01', 10, 0, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HCMCT00023', 'Chuồng Sanh Đẻ - 02', 10, 0, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HCMCT00024', 'Chuồng Bò Non - 01', 10, 0, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HCMCT00025', 'Chuồng Bò Non - 02', 10, 0, 'Đang hoạt động.', b'0', 'HCMCN002'),
('HNCT00001', 'Chuồng Lấy Sữa - 01', 30, 3, 'Đang hoạt động.', b'0', 'HNCN001'),
('HNCT00002', 'Chuồng Lấy Sữa - 02', 30, 3, 'Đang hoạt động.', b'0', 'HNCN001'),
('HNCT00003', 'Chuồng Lấy Sữa - 03', 30, 4, 'Đang hoạt động.', b'0', 'HNCN001'),
('HNCT00004', 'Chuồng Lấy Sữa - 04', 30, 0, 'Đang hoạt động.', b'0', 'HNCN001'),
('HNCT00005', 'Chuồng Lấy Sữa - 05', 30, 0, 'Đang hoạt động.', b'0', 'HNCN001'),
('HNCT00006', 'Chuồng Hồi Sức - 01', 30, 3, 'Đang hoạt động.', b'0', 'HNCN001'),
('HNCT00007', 'Chuồng Sanh Đẻ - 01', 20, 0, 'Đang hoạt động.', b'0', 'HNCN001'),
('HNCT00008', 'Chuồng Bò Non - 01', 20, 0, 'Đang hoạt động.', b'0', 'HNCN001');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `counter`
--

CREATE TABLE `counter` (
  `areaid` varchar(3) NOT NULL,
  `index_bo` int(11) DEFAULT '0',
  `index_chinhanh` int(11) DEFAULT '0',
  `index_chuongtrai` int(11) DEFAULT '0',
  `index_khosua` int(11) DEFAULT '0',
  `index_nhacungcap` int(11) DEFAULT '0',
  `index_nhanvien` int(11) DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `counter`
--

INSERT INTO `counter` (`areaid`, `index_bo`, `index_chinhanh`, `index_chuongtrai`, `index_khosua`, `index_nhacungcap`, `index_nhanvien`) VALUES
('HCM', 30, 2, 25, 5, 3, 17),
('HN', 15, 1, 8, 2, 2, 8);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khosua`
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
-- Đang đổ dữ liệu cho bảng `khosua`
--

INSERT INTO `khosua` (`makho`, `tenkho`, `succhua`, `luongsuaco`, `diachi`, `daxoa`, `tinhtrang`, `machinhanh`) VALUES
('HCMKS0001', 'Kho thường', 10000, 4261.769, '123 Trên mặt đất dươi bầu trời', b'0', 'Đang được sử dụng.', 'HCMCN001'),
('HCMKS0002', 'Kho lạnh - 1', 4000, 3000, 'Tìm được nói tao biết', b'0', 'Đang được sữa chửa.', 'HCMCN001'),
('HCMKS0003', 'Kho lạnh - 2', 5000, 0, 'Gần ngay trước mắt mà xa tận cùng trời', b'1', 'Ngưng hoạt động.', 'HCMCN001'),
('HCMKS0004', 'Kho thường', 10000, 4834.069, 'Giỏi tìm tao đi', b'0', 'Đang được sử dụng.', 'HCMCN002'),
('HCMKS0005', 'Kho lạnh', 5000, 3000, 'You cant see me \\m/', b'0', 'Đang được sử dụng.', 'HCMCN002'),
('HNKS0001', 'Kho thường', 10000, 3631.34, 'Everywhere you know', b'0', 'Đang được sử dụng.', 'HNCN001'),
('HNKS0002', 'Kho lạnh', 5000, 3000, 'Vũ trụ song song', b'0', 'Đang được sử dụng.', 'HNCN001');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `manhacungcap` varchar(10) NOT NULL,
  `ten` varchar(50) DEFAULT NULL,
  `diachi` varchar(100) DEFAULT NULL,
  `tinhtrang` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`manhacungcap`, `ten`, `diachi`, `tinhtrang`) VALUES
('HCMNCC0001', 'Cơ sở nuôi bò Thiên Địa', 'Tôi thích suy nghĩ của bạn', 'Đang hoạt động.'),
('HCMNCC0002', 'Cơ sở nuôi bò Long Thành', 'Hẳn là ở Long Thành', 'Đang hoạt động.'),
('HCMNCC0003', 'Trại bò giống Quy Nhơn', 'Quy Nhơn là chỗ quái nào nhỉ, nghe quen quen', 'Đang hoạt động.'),
('HNNCC0001', 'Trại bò Tung Của', 'Ai biết, đoán xem', 'Đang hoạt động.'),
('HNNCC0002', 'Cơ sở nuôi bò Bắc Cực', 'Ở Bắc Cực chứ ở đâu', 'Đang hoạt động.');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
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
  `maphanquyen` varchar(10) DEFAULT NULL,
  `ngayvaolam` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`manhanvien`, `hoten`, `gioitinh`, `ngaysinh`, `sodt`, `diachi`, `email`, `tentaikhoan`, `matkhau`, `tinhtrang`, `daxoa`, `machinhanh`, `maphanquyen`, `ngayvaolam`) VALUES
('HCMNV00001', 'Test01', 'Nam', '1992-10-19', '0454375335', '001 Any Street, HCM city, the Earth', '001@gmail.com', '001@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN001', 'PQ003', '2016-03-01'),
('HCMNV00002', 'Test02', 'Nam', '1987-03-01', '7532005537', '002 Any Street, HCM city, the Earth', '002@gmail.com', '002@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN001', 'PQ002', '2016-04-01'),
('HCMNV00003', 'Test03', 'Nam', '1985-08-05', '4524272727', '003 Any Street, HCM city, the Earth', '003@gmail.com', '003@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN001', 'PQ001', '2016-04-01'),
('HCMNV00004', 'Test04', 'Nữ', '1985-09-19', '72524742737', '004 Any Street, HCM city, the Earth', '004@gmail.com', '004@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN001', 'PQ001', '2016-04-01'),
('HCMNV00005', 'Test05', 'Nữ', '2017-05-30', '75240404525', '005 Any Street, HCM city, the Earth', '005@gmail.com', '005@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN001', 'PQ001', '2016-04-01'),
('HCMNV00006', 'Test06', 'Nam', '1981-05-18', '7872275347', '006 Any Street, HCM city, the Earth', '006@gmail.com', '006@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN001', 'PQ001', '2016-04-01'),
('HCMNV00007', 'Test07', 'Nữ', '1984-11-21', '0138727785', '007 Any Street, HCM city, the Earth', '007@gmail.com', '007@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN001', 'PQ001', '2016-04-01'),
('HCMNV00008', 'Test08', 'Nam', '1979-07-28', '707877594', '008 Any Street, HCM city, the Earth', '008@gmail.com', '008@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN001', 'PQ001', '2016-04-01'),
('HCMNV00009', 'Test09', 'Nam', '1983-12-29', '420667637', '009 Any Street, HCM city, the Earth', '009@gmail.com', '009@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN001', 'PQ001', '2016-04-01'),
('HCMNV00010', 'Test10', 'Nam', '1992-02-02', '1047377734', '010 Any Street, HCM city, the Earth', '010@gmail.com', '010@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN002', 'PQ002', '2016-05-01'),
('HCMNV00011', 'Test11', 'Nam', '1993-03-03', '2372437420', '011 Any Street, HCM city, the Earth', '011@gmail.com', '011@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN002', 'PQ001', '2016-05-01'),
('HCMNV00012', 'Test12', 'Nữ', '1994-04-04', '0453727543', '012 Any Street, HCM city, the Earth', '012@gmail.com', '012@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN002', 'PQ001', '2016-05-01'),
('HCMNV00013', 'Test13', 'Nam', '1985-12-05', '4878245453', '013 Any Street, HCM city, the Earth', '013@gmail.com', '013@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN002', 'PQ001', '2016-05-01'),
('HCMNV00014', 'Test14', 'Nữ', '1984-06-11', '9542424224', '014 Any Street, HCM city, the Earth', '014@gmail.com', '014@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN002', 'PQ001', '2016-05-01'),
('HCMNV00015', 'Test15', 'Nam', '1979-06-16', '5652442242', '015 Any Street, HCM city, the Earth', '015@gmail.com', '015@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN002', 'PQ001', '2016-05-01'),
('HCMNV00016', 'Test16', 'Nữ', '1994-10-19', '0454772455', '016 Any Street, HCM city, the Earth', '016@gmail.com', '016@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN002', 'PQ001', '2016-05-01'),
('HCMNV00017', 'Test17', 'Nam', '1988-10-10', '7845244254', '017 Any Street, HCM city, the Earth', '017@gmail.com', '017@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HCMCN002', 'PQ001', '2016-05-01'),
('HNNV00001', 'Test18', 'Nam', '1991-10-18', '8734745424', '018 Any Street, HN city, the Earth', '018@gmail.com', '018@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HNCN001', 'PQ002', '2016-05-01'),
('HNNV00002', 'Test19', 'Nữ', '1991-11-26', '4272474528', '019 Any Street, HN city, the Earth', '019@gmail.com', '019@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HNCN001', 'PQ001', '2016-05-01'),
('HNNV00003', 'Test20', 'Nam', '1989-01-25', '0445372752', '020 Any Street, HN city, the Earth', '020@gmail.com', '020@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HNCN001', 'PQ001', '2016-05-01'),
('HNNV00004', 'Test21', 'Nam', '1990-10-06', '4211343414', '021 Any Street, HN city, the Earth', '021@gmail.com', '021@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HNCN001', 'PQ001', '2016-05-01'),
('HNNV00005', 'Test22', 'Nam', '1977-10-03', '4234453437', '022 Any Street, HN city, the Earth', '022@gmail.com', '022@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HNCN001', 'PQ001', '2016-05-01'),
('HNNV00006', 'Test23', 'Nữ', '1988-02-28', '4275427457', '023 Any Street, HN city, the Earth', '023@gmail.com', '023@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HNCN001', 'PQ001', '2016-05-01'),
('HNNV00007', 'Test24', 'Nam', '1988-01-27', '2734374424', '024 Any Street, HN city, the Earth', '024@gmail.com', '024@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HNCN001', 'PQ001', '2016-05-01'),
('HNNV00008', 'Test25', 'Nam', '1985-11-19', '7437245273', '025 Any Street, HN city, the Earth', '025@gmail.com', '025@gmail.com', 'WVs&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT', 'Đang làm.', b'0', 'HNCN001', 'PQ001', '2016-05-01');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `maphanquyen` varchar(10) NOT NULL,
  `tenphanquyen` varchar(50) DEFAULT NULL,
  `capphanquyen` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `phanquyen`
--

INSERT INTO `phanquyen` (`maphanquyen`, `tenphanquyen`, `capphanquyen`) VALUES
('PQ001', 'Nhân viên chăm sóc', 1),
('PQ002', 'Quản lý chi nhánh', 2),
('PQ003', 'Tổng giám đốc', 3);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bo`
--
ALTER TABLE `bo`
  ADD PRIMARY KEY (`mabo`),
  ADD UNIQUE KEY `machip_UNIQUE` (`machip`);

--
-- Chỉ mục cho bảng `chinhanh`
--
ALTER TABLE `chinhanh`
  ADD PRIMARY KEY (`machinhanh`);

--
-- Chỉ mục cho bảng `chuongtrai`
--
ALTER TABLE `chuongtrai`
  ADD PRIMARY KEY (`machuong`);

--
-- Chỉ mục cho bảng `counter`
--
ALTER TABLE `counter`
  ADD PRIMARY KEY (`areaid`);

--
-- Chỉ mục cho bảng `khosua`
--
ALTER TABLE `khosua`
  ADD PRIMARY KEY (`makho`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`manhacungcap`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manhanvien`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD UNIQUE KEY `tentaikhoan_UNIQUE` (`tentaikhoan`);

--
-- Chỉ mục cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`maphanquyen`),
  ADD UNIQUE KEY `capphanquyen_UNIQUE` (`capphanquyen`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
