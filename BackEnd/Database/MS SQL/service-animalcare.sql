USE [master]
GO
/****** Object:  Database [Service_AnimalCare]    Script Date: 6/11/2017 10:49:02 PM ******/
CREATE DATABASE [Service_AnimalCare]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Service_AnimalCare', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS_2012\MSSQL\DATA\Service_AnimalCare.mdf' , SIZE = 6144KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Service_AnimalCare_log', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS_2012\MSSQL\DATA\Service_AnimalCare_log.ldf' , SIZE = 29504KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Service_AnimalCare] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Service_AnimalCare].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Service_AnimalCare] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET ARITHABORT OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [Service_AnimalCare] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Service_AnimalCare] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Service_AnimalCare] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Service_AnimalCare] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Service_AnimalCare] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Service_AnimalCare] SET  MULTI_USER 
GO
ALTER DATABASE [Service_AnimalCare] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Service_AnimalCare] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Service_AnimalCare] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Service_AnimalCare] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [Service_AnimalCare]
GO
/****** Object:  StoredProcedure [dbo].[db_ReportTinhTrangBo]    Script Date: 6/11/2017 10:49:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[db_ReportTinhTrangBo] @macn varchar(10) , @ngaybatdau date
As
	if @macn is null
	begin
		select t.NgayGhiNhan, avg(t.cannang) as cannang, avg(t.chieucao) as chieucao, t.MaChiNhanh
		from
		(
		select a.NgayGhiNhan, b.CanNang, b.ChieuCao, a.MaChiNhanh
		from ChamSoc a, TinhTrangBo b
		where b.ThoiGianGhiNhan = 
			(
				select max(temp.ThoiGianGhiNhan)
				from TinhTrangBo temp 
				where temp.MaChamSoc = b.MaChamSoc
			) and
			a.MaChamSoc = b.MaChamSoc and
			DATEDIFF(DAY, @ngaybatdau, b.ThoiGianGhiNhan) < 14 and
			DATEDIFF(DAY, @ngaybatdau, b.ThoiGianGhiNhan) >= 0
		group by a.NgayGhiNhan, b.CanNang, b.ChieuCao, a.MaChiNhanh
		) t
		group by t.NgayGhiNhan, t.MaChiNhanh
		order by t.NgayGhiNhan
	end
	else
	begin
		select t.NgayGhiNhan, avg(t.cannang) as cannang, avg(t.chieucao) as chieucao
		from
		(
		select a.NgayGhiNhan, b.CanNang, b.ChieuCao
		from ChamSoc a, TinhTrangBo b
		where a.MaChiNhanh = @macn and
			b.ThoiGianGhiNhan = 
			(
				select max(temp.ThoiGianGhiNhan)
				from TinhTrangBo temp 
				where temp.MaChamSoc = b.MaChamSoc
			) and
			a.MaChamSoc = b.MaChamSoc and
			DATEDIFF(DAY, @ngaybatdau, b.ThoiGianGhiNhan) < 14 and
			DATEDIFF(DAY, @ngaybatdau, b.ThoiGianGhiNhan) >= 0
		group by a.NgayGhiNhan, b.CanNang, b.ChieuCao
		) t
		group by t.NgayGhiNhan
		order by t.NgayGhiNhan
	end
GO
/****** Object:  Table [dbo].[ChamSoc]    Script Date: 6/11/2017 10:49:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ChamSoc](
	[MaChamSoc] [varchar](15) NOT NULL,
	[NgayGhiNhan] [date] NULL,
	[TinhTrangCongViec] [nvarchar](50) NULL,
	[LuongSua] [real] NULL,
	[DaChoAn] [bit] NULL,
	[DaDonVeSinh] [bit] NULL,
	[DaVatSua] [bit] NULL,
	[MaPhanCong] [varchar](10) NULL,
	[MaBo] [varchar](10) NULL,
	[MaChiNhanh] [varchar](10) NULL,
 CONSTRAINT [PK_ChamSoc] PRIMARY KEY CLUSTERED 
(
	[MaChamSoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Counter]    Script Date: 6/11/2017 10:49:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Counter](
	[areaid] [varchar](3) NOT NULL,
	[index_phancong] [int] NULL CONSTRAINT [DF_Tables_Counter_phancong]  DEFAULT ((0)),
	[index_chamsoc] [int] NULL CONSTRAINT [DF_Tables_Counter_chamsoc]  DEFAULT ((0)),
 CONSTRAINT [PK_Tables_Counter] PRIMARY KEY CLUSTERED 
(
	[areaid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PhanCong]    Script Date: 6/11/2017 10:49:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PhanCong](
	[MaPhanCong] [varchar](10) NOT NULL,
	[NgayBatDau] [date] NULL,
	[NgayKetThuc] [date] NULL,
	[NgayTrongTuan] [nvarchar](50) NULL,
	[MaNV] [varchar](10) NOT NULL,
	[MaChuong] [varchar](10) NOT NULL,
 CONSTRAINT [PK_PhanCong] PRIMARY KEY CLUSTERED 
(
	[MaPhanCong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TinhTrangBo]    Script Date: 6/11/2017 10:49:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TinhTrangBo](
	[MaChamSoc] [varchar](15) NOT NULL,
	[ThoiGianGhiNhan] [datetime] NOT NULL,
	[CanNang] [real] NULL,
	[ChieuCao] [real] NULL,
 CONSTRAINT [PK_TinhTrangBo] PRIMARY KEY CLUSTERED 
(
	[MaChamSoc] ASC,
	[ThoiGianGhiNhan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
USE [master]
GO
ALTER DATABASE [Service_AnimalCare] SET  READ_WRITE 
GO
