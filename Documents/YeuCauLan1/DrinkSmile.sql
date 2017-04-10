USE [master]
GO
/****** Object:  Database [DrinkSmile]    Script Date: 4/10/2017 3:32:44 PM ******/
CREATE DATABASE [DrinkSmile]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DrinkSmile', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\DrinkSmile.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'DrinkSmile_log', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\DrinkSmile_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [DrinkSmile] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DrinkSmile].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DrinkSmile] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DrinkSmile] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DrinkSmile] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DrinkSmile] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DrinkSmile] SET ARITHABORT OFF 
GO
ALTER DATABASE [DrinkSmile] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DrinkSmile] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [DrinkSmile] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DrinkSmile] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DrinkSmile] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DrinkSmile] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DrinkSmile] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DrinkSmile] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DrinkSmile] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DrinkSmile] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DrinkSmile] SET  DISABLE_BROKER 
GO
ALTER DATABASE [DrinkSmile] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DrinkSmile] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DrinkSmile] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DrinkSmile] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DrinkSmile] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DrinkSmile] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DrinkSmile] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DrinkSmile] SET RECOVERY FULL 
GO
ALTER DATABASE [DrinkSmile] SET  MULTI_USER 
GO
ALTER DATABASE [DrinkSmile] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DrinkSmile] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DrinkSmile] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DrinkSmile] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [DrinkSmile]
GO
/****** Object:  Table [dbo].[BaoCaoTinhTrangBo]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BaoCaoTinhTrangBo](
	[MaBaoCao] [varchar](10) NOT NULL,
	[NgayBatDau] [date] NOT NULL,
	[NgayKetThuc] [date] NOT NULL,
	[NgayLap] [date] NOT NULL,
	[TongSoBo] [int] NOT NULL,
	[SoBoBenh] [int] NOT NULL,
	[MaNV] [varchar](10) NOT NULL,
 CONSTRAINT [PK_BaoCaoTinhTrangBo] PRIMARY KEY CLUSTERED 
(
	[MaBaoCao] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[BaoCaoXuat]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BaoCaoXuat](
	[MaBaoCao] [varchar](10) NOT NULL,
	[NgayBatDau] [date] NOT NULL,
	[NgayKetThuc] [date] NOT NULL,
	[NgayLap] [date] NOT NULL,
	[TongLuongSua] [real] NOT NULL,
	[MaNV] [varchar](10) NOT NULL,
 CONSTRAINT [PK_BaoCaoXuat] PRIMARY KEY CLUSTERED 
(
	[MaBaoCao] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Bo]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Bo](
	[MaBo] [varchar](10) NOT NULL,
	[MaChip] [varchar](10) NOT NULL,
	[MauSac] [nvarchar](50) NULL,
	[CoDiTat] [bit] NOT NULL,
	[NhanDang] [nvarchar](50) NULL,
	[TinhTrang] [nvarchar](50) NOT NULL,
	[DaXoa] [bit] NOT NULL,
	[MaChuong] [varchar](10) NOT NULL,
 CONSTRAINT [PK_Bo] PRIMARY KEY CLUSTERED 
(
	[MaBo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ChamSoc]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ChamSoc](
	[MaPhanCong] [varchar](10) NOT NULL,
	[MaBo] [varchar](10) NOT NULL,
	[MaChamSoc] [varchar](50) NOT NULL,
	[LuongSuaVat] [real] NOT NULL,
	[TinhTrangCongViec] [nvarchar](50) NOT NULL,
	[DaDonVeSinh] [bit] NOT NULL,
	[DaChoAn] [bit] NOT NULL,
	[DaVatSua] [bit] NOT NULL,
	[NgayGhiNhan] [date] NOT NULL,
 CONSTRAINT [PK_ChamSoc_1] PRIMARY KEY CLUSTERED 
(
	[MaChamSoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ChiNhanh]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ChiNhanh](
	[MaChiNhanh] [varchar](10) NOT NULL,
	[TenChiNhanh] [nvarchar](50) NOT NULL,
	[SoDT] [varchar](50) NULL,
	[DiaChi] [nvarchar](50) NULL,
	[TinhTrang] [nvarchar](50) NOT NULL,
	[DaXoa] [bit] NOT NULL,
	[QuanLy] [varchar](10) NOT NULL,
 CONSTRAINT [PK_ChiNhanh] PRIMARY KEY CLUSTERED 
(
	[MaChiNhanh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ChiTietBaoCaoTinhTrangBo]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ChiTietBaoCaoTinhTrangBo](
	[MaBo] [varchar](10) NOT NULL,
	[MaBaoCao] [varchar](10) NOT NULL,
	[TinhTrang] [nvarchar](50) NULL,
 CONSTRAINT [PK_ChiTietBaoCaoTinhTrangBo] PRIMARY KEY CLUSTERED 
(
	[MaBo] ASC,
	[MaBaoCao] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ChiTietBaoCaoXuat]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ChiTietBaoCaoXuat](
	[MaBaoCao] [varchar](10) NOT NULL,
	[MaKho] [varchar](10) NOT NULL,
	[LuongSuaXuat] [real] NOT NULL,
 CONSTRAINT [PK_ChiTietBaoCaoXuat] PRIMARY KEY CLUSTERED 
(
	[MaBaoCao] ASC,
	[MaKho] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ChiTietXuatSua]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ChiTietXuatSua](
	[MaChungTu] [varchar](10) NOT NULL,
	[MaKho] [varchar](10) NOT NULL,
	[LuongSuaXuat] [real] NOT NULL,
 CONSTRAINT [PK_ChiTietXuatSua] PRIMARY KEY CLUSTERED 
(
	[MaChungTu] ASC,
	[MaKho] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ChuongTrai]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ChuongTrai](
	[MaChuong] [varchar](10) NOT NULL,
	[TenChuong] [nvarchar](50) NOT NULL,
	[SucChua] [real] NOT NULL,
	[TinhTrang] [nvarchar](50) NOT NULL,
	[DaXoa] [bit] NOT NULL,
	[MaChiNhanh] [varchar](10) NOT NULL,
 CONSTRAINT [PK_ChuongTrai] PRIMARY KEY CLUSTERED 
(
	[MaChuong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[KhoSua]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[KhoSua](
	[MaKho] [varchar](10) NOT NULL,
	[Tenkho] [nvarchar](50) NOT NULL,
	[SucChua] [real] NOT NULL,
	[LuongSuaCo] [real] NOT NULL,
	[DiaChi] [nvarchar](50) NULL,
	[DaXoa] [bit] NOT NULL,
	[TinhTrang] [nvarchar](50) NOT NULL,
	[MaChiNhanh] [varchar](10) NOT NULL,
 CONSTRAINT [PK_KhoSua] PRIMARY KEY CLUSTERED 
(
	[MaKho] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[MaNCC] [varchar](10) NOT NULL,
	[TenNCC] [nvarchar](50) NOT NULL,
	[TinhTrang] [nvarchar](50) NOT NULL,
	[DiaChi] [nvarchar](50) NULL,
 CONSTRAINT [PK_NhaCungCap] PRIMARY KEY CLUSTERED 
(
	[MaNCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NhanVien](
	[Email] [nvarchar](50) NULL,
	[GioiTinh] [nvarchar](10) NOT NULL,
	[HoTen] [nvarchar](50) NOT NULL,
	[MaNV] [varchar](10) NOT NULL,
	[MatKhau] [nvarchar](50) NOT NULL,
	[NgaySinh] [date] NULL,
	[SoDT] [varchar](50) NULL,
	[TaiKhoan] [nvarchar](50) NOT NULL,
	[TinhTrang] [nvarchar](50) NOT NULL,
	[DaXoa] [bit] NOT NULL,
	[DiaChi] [nvarchar](50) NULL,
	[ChiNhanh] [varchar](10) NOT NULL,
	[MaPhanQuyen] [varchar](10) NOT NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PhanCong]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PhanCong](
	[MaPhanCong] [varchar](10) NOT NULL,
	[NgayBatDau] [date] NOT NULL,
	[NgayKetThuc] [date] NOT NULL,
	[NgayTrongTuan] [varchar](20) NOT NULL,
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
/****** Object:  Table [dbo].[PhanQuyen]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PhanQuyen](
	[MaPhanQuyen] [varchar](10) NOT NULL,
	[CapPhanQuyen] [int] NOT NULL,
	[TenPhanQuyen] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_PhanQuyen] PRIMARY KEY CLUSTERED 
(
	[MaPhanQuyen] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PhieuNhapBo]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PhieuNhapBo](
	[MaChungTu] [varchar](10) NOT NULL,
	[NgayLap] [date] NOT NULL,
	[NgayNhap] [date] NOT NULL,
	[SoLuong] [int] NOT NULL,
	[DaHuy] [bit] NOT NULL,
	[MaNCC] [varchar](10) NOT NULL,
	[MaNV] [varchar](10) NOT NULL,
 CONSTRAINT [PK_PhieuNhapBo] PRIMARY KEY CLUSTERED 
(
	[MaChungTu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PhieuXuat]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PhieuXuat](
	[MaChungTu] [varchar](10) NOT NULL,
	[NgayLap] [date] NOT NULL,
	[NgayXuat] [date] NOT NULL,
	[TongLuongSua] [real] NOT NULL,
	[DaHuy] [bit] NOT NULL,
	[LyDo] [nvarchar](50) NULL,
	[MaNV] [varchar](10) NOT NULL,
 CONSTRAINT [PK_PhieuXuat] PRIMARY KEY CLUSTERED 
(
	[MaChungTu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TinhTrangBo]    Script Date: 4/10/2017 3:32:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TinhTrangBo](
	[MaChamSoc] [varchar](50) NOT NULL,
	[ThoiGianGhiNhan] [datetime] NOT NULL,
	[ChieuCao] [real] NOT NULL,
	[CanNang] [real] NOT NULL,
 CONSTRAINT [PK_TinhTrangBo] PRIMARY KEY CLUSTERED 
(
	[MaChamSoc] ASC,
	[ThoiGianGhiNhan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[PhanQuyen] ([MaPhanQuyen], [CapPhanQuyen], [TenPhanQuyen]) VALUES (N'PQ001', 1, N'Tổng giám đốc')
INSERT [dbo].[PhanQuyen] ([MaPhanQuyen], [CapPhanQuyen], [TenPhanQuyen]) VALUES (N'PQ002', 2, N'Quản lý chi nhánh')
INSERT [dbo].[PhanQuyen] ([MaPhanQuyen], [CapPhanQuyen], [TenPhanQuyen]) VALUES (N'PQ003', 3, N'Nhân viên chăm sóc')
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_ChamSoc_Unique]    Script Date: 4/10/2017 3:32:44 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_ChamSoc_Unique] ON [dbo].[ChamSoc]
(
	[MaPhanCong] ASC,
	[MaBo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[BaoCaoTinhTrangBo] ADD  CONSTRAINT [DF_BaoCaoTinhTrangBo_TongSoBo]  DEFAULT ((0)) FOR [TongSoBo]
GO
ALTER TABLE [dbo].[BaoCaoTinhTrangBo] ADD  CONSTRAINT [DF_BaoCaoTinhTrangBo_SoBoBenh]  DEFAULT ((0)) FOR [SoBoBenh]
GO
ALTER TABLE [dbo].[BaoCaoXuat] ADD  CONSTRAINT [DF_BaoCaoXuat_TongLuongSua]  DEFAULT ((0)) FOR [TongLuongSua]
GO
ALTER TABLE [dbo].[Bo] ADD  CONSTRAINT [DF_Bo_CoDiTat]  DEFAULT ((0)) FOR [CoDiTat]
GO
ALTER TABLE [dbo].[Bo] ADD  CONSTRAINT [DF_Bo_DaXoa]  DEFAULT ((0)) FOR [DaXoa]
GO
ALTER TABLE [dbo].[ChamSoc] ADD  CONSTRAINT [DF_ChamSoc_LuongSuaVat]  DEFAULT ((0)) FOR [LuongSuaVat]
GO
ALTER TABLE [dbo].[ChamSoc] ADD  CONSTRAINT [DF_ChamSoc_TinhTrangCongViec]  DEFAULT (N'Chưa hoàn thành') FOR [TinhTrangCongViec]
GO
ALTER TABLE [dbo].[ChamSoc] ADD  CONSTRAINT [DF_ChamSoc_DaDonVeSinh]  DEFAULT ((0)) FOR [DaDonVeSinh]
GO
ALTER TABLE [dbo].[ChamSoc] ADD  CONSTRAINT [DF_ChamSoc_DaChoAn]  DEFAULT ((0)) FOR [DaChoAn]
GO
ALTER TABLE [dbo].[ChamSoc] ADD  CONSTRAINT [DF_ChamSoc_DaVatSua]  DEFAULT ((0)) FOR [DaVatSua]
GO
ALTER TABLE [dbo].[ChiNhanh] ADD  CONSTRAINT [DF_ChiNhanh_TinhTrang]  DEFAULT (N'Đang Hoạt Động') FOR [TinhTrang]
GO
ALTER TABLE [dbo].[ChiNhanh] ADD  CONSTRAINT [DF_ChiNhanh_DaXoa]  DEFAULT ((0)) FOR [DaXoa]
GO
ALTER TABLE [dbo].[ChiTietBaoCaoXuat] ADD  CONSTRAINT [DF_ChiTietBaoCaoXuat_LuongSuaXuat]  DEFAULT ((0)) FOR [LuongSuaXuat]
GO
ALTER TABLE [dbo].[ChiTietXuatSua] ADD  CONSTRAINT [DF_ChiTietXuatSua_LuongSuaXuat]  DEFAULT ((0)) FOR [LuongSuaXuat]
GO
ALTER TABLE [dbo].[ChuongTrai] ADD  CONSTRAINT [DF_ChuongTrai_SucChua]  DEFAULT ((0)) FOR [SucChua]
GO
ALTER TABLE [dbo].[ChuongTrai] ADD  CONSTRAINT [DF_ChuongTrai_TinhTrang]  DEFAULT (N'Đang Hoạt Động') FOR [TinhTrang]
GO
ALTER TABLE [dbo].[ChuongTrai] ADD  CONSTRAINT [DF_ChuongTrai_DaXoa]  DEFAULT ((0)) FOR [DaXoa]
GO
ALTER TABLE [dbo].[KhoSua] ADD  CONSTRAINT [DF_KhoSua_SucChua]  DEFAULT ((0)) FOR [SucChua]
GO
ALTER TABLE [dbo].[KhoSua] ADD  CONSTRAINT [DF_KhoSua_LuongSuaCo]  DEFAULT ((0)) FOR [LuongSuaCo]
GO
ALTER TABLE [dbo].[KhoSua] ADD  CONSTRAINT [DF_KhoSua_DaXoa]  DEFAULT ((0)) FOR [DaXoa]
GO
ALTER TABLE [dbo].[KhoSua] ADD  CONSTRAINT [DF_KhoSua_TinhTrang]  DEFAULT (N'Đang Hoạt Động') FOR [TinhTrang]
GO
ALTER TABLE [dbo].[NhaCungCap] ADD  CONSTRAINT [DF_NhaCungCap_TinhTrang]  DEFAULT (N'Đang Hoạt Động') FOR [TinhTrang]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [DF_NhanVien_GioiTinh]  DEFAULT (N'Nam') FOR [GioiTinh]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [DF_NhanVien_TinhTrang]  DEFAULT (N'Đang Hoạt Động') FOR [TinhTrang]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [DF_NhanVien_DaXoa]  DEFAULT ((0)) FOR [DaXoa]
GO
ALTER TABLE [dbo].[PhieuNhapBo] ADD  CONSTRAINT [DF_PhieuNhapBo_SoLuong]  DEFAULT ((0)) FOR [SoLuong]
GO
ALTER TABLE [dbo].[PhieuNhapBo] ADD  CONSTRAINT [DF_PhieuNhapBo_DaHuy]  DEFAULT ((0)) FOR [DaHuy]
GO
ALTER TABLE [dbo].[PhieuXuat] ADD  CONSTRAINT [DF_PhieuXuat_TongLuongSua]  DEFAULT ((0)) FOR [TongLuongSua]
GO
ALTER TABLE [dbo].[PhieuXuat] ADD  CONSTRAINT [DF_PhieuXuat_DaHuy]  DEFAULT ((0)) FOR [DaHuy]
GO
ALTER TABLE [dbo].[TinhTrangBo] ADD  CONSTRAINT [DF_TinhTrangBo_ChieuCao]  DEFAULT ((0)) FOR [ChieuCao]
GO
ALTER TABLE [dbo].[TinhTrangBo] ADD  CONSTRAINT [DF_TinhTrangBo_CanNang]  DEFAULT ((0)) FOR [CanNang]
GO
ALTER TABLE [dbo].[BaoCaoTinhTrangBo]  WITH CHECK ADD  CONSTRAINT [FK_BaoCaoTinhTrangBo_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[BaoCaoTinhTrangBo] CHECK CONSTRAINT [FK_BaoCaoTinhTrangBo_NhanVien]
GO
ALTER TABLE [dbo].[BaoCaoXuat]  WITH CHECK ADD  CONSTRAINT [FK_BaoCaoXuat_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[BaoCaoXuat] CHECK CONSTRAINT [FK_BaoCaoXuat_NhanVien]
GO
ALTER TABLE [dbo].[Bo]  WITH CHECK ADD  CONSTRAINT [FK_Bo_ChuongTrai] FOREIGN KEY([MaChuong])
REFERENCES [dbo].[ChuongTrai] ([MaChuong])
GO
ALTER TABLE [dbo].[Bo] CHECK CONSTRAINT [FK_Bo_ChuongTrai]
GO
ALTER TABLE [dbo].[ChamSoc]  WITH CHECK ADD  CONSTRAINT [FK_ChamSoc_Bo] FOREIGN KEY([MaBo])
REFERENCES [dbo].[Bo] ([MaBo])
GO
ALTER TABLE [dbo].[ChamSoc] CHECK CONSTRAINT [FK_ChamSoc_Bo]
GO
ALTER TABLE [dbo].[ChamSoc]  WITH CHECK ADD  CONSTRAINT [FK_ChamSoc_PhanCong] FOREIGN KEY([MaPhanCong])
REFERENCES [dbo].[PhanCong] ([MaPhanCong])
GO
ALTER TABLE [dbo].[ChamSoc] CHECK CONSTRAINT [FK_ChamSoc_PhanCong]
GO
ALTER TABLE [dbo].[ChiNhanh]  WITH CHECK ADD  CONSTRAINT [FK_ChiNhanh_NhanVien] FOREIGN KEY([QuanLy])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[ChiNhanh] CHECK CONSTRAINT [FK_ChiNhanh_NhanVien]
GO
ALTER TABLE [dbo].[ChiTietBaoCaoTinhTrangBo]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietBaoCaoTinhTrangBo_BaoCaoTinhTrangBo] FOREIGN KEY([MaBaoCao])
REFERENCES [dbo].[BaoCaoTinhTrangBo] ([MaBaoCao])
GO
ALTER TABLE [dbo].[ChiTietBaoCaoTinhTrangBo] CHECK CONSTRAINT [FK_ChiTietBaoCaoTinhTrangBo_BaoCaoTinhTrangBo]
GO
ALTER TABLE [dbo].[ChiTietBaoCaoTinhTrangBo]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietBaoCaoTinhTrangBo_Bo] FOREIGN KEY([MaBo])
REFERENCES [dbo].[Bo] ([MaBo])
GO
ALTER TABLE [dbo].[ChiTietBaoCaoTinhTrangBo] CHECK CONSTRAINT [FK_ChiTietBaoCaoTinhTrangBo_Bo]
GO
ALTER TABLE [dbo].[ChiTietBaoCaoXuat]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietBaoCaoXuat_BaoCaoXuat] FOREIGN KEY([MaBaoCao])
REFERENCES [dbo].[BaoCaoXuat] ([MaBaoCao])
GO
ALTER TABLE [dbo].[ChiTietBaoCaoXuat] CHECK CONSTRAINT [FK_ChiTietBaoCaoXuat_BaoCaoXuat]
GO
ALTER TABLE [dbo].[ChiTietBaoCaoXuat]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietBaoCaoXuat_KhoSua] FOREIGN KEY([MaKho])
REFERENCES [dbo].[KhoSua] ([MaKho])
GO
ALTER TABLE [dbo].[ChiTietBaoCaoXuat] CHECK CONSTRAINT [FK_ChiTietBaoCaoXuat_KhoSua]
GO
ALTER TABLE [dbo].[ChiTietXuatSua]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietXuatSua_KhoSua] FOREIGN KEY([MaKho])
REFERENCES [dbo].[KhoSua] ([MaKho])
GO
ALTER TABLE [dbo].[ChiTietXuatSua] CHECK CONSTRAINT [FK_ChiTietXuatSua_KhoSua]
GO
ALTER TABLE [dbo].[ChiTietXuatSua]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietXuatSua_PhieuXuat] FOREIGN KEY([MaChungTu])
REFERENCES [dbo].[PhieuXuat] ([MaChungTu])
GO
ALTER TABLE [dbo].[ChiTietXuatSua] CHECK CONSTRAINT [FK_ChiTietXuatSua_PhieuXuat]
GO
ALTER TABLE [dbo].[ChuongTrai]  WITH CHECK ADD  CONSTRAINT [FK_ChuongTrai_ChiNhanh] FOREIGN KEY([MaChiNhanh])
REFERENCES [dbo].[ChiNhanh] ([MaChiNhanh])
GO
ALTER TABLE [dbo].[ChuongTrai] CHECK CONSTRAINT [FK_ChuongTrai_ChiNhanh]
GO
ALTER TABLE [dbo].[KhoSua]  WITH CHECK ADD  CONSTRAINT [FK_KhoSua_ChiNhanh] FOREIGN KEY([MaChiNhanh])
REFERENCES [dbo].[ChiNhanh] ([MaChiNhanh])
GO
ALTER TABLE [dbo].[KhoSua] CHECK CONSTRAINT [FK_KhoSua_ChiNhanh]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_ChiNhanh] FOREIGN KEY([ChiNhanh])
REFERENCES [dbo].[ChiNhanh] ([MaChiNhanh])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_ChiNhanh]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_PhanQuyen] FOREIGN KEY([MaPhanQuyen])
REFERENCES [dbo].[PhanQuyen] ([MaPhanQuyen])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_PhanQuyen]
GO
ALTER TABLE [dbo].[PhanCong]  WITH CHECK ADD  CONSTRAINT [FK_PhanCong_ChuongTrai] FOREIGN KEY([MaChuong])
REFERENCES [dbo].[ChuongTrai] ([MaChuong])
GO
ALTER TABLE [dbo].[PhanCong] CHECK CONSTRAINT [FK_PhanCong_ChuongTrai]
GO
ALTER TABLE [dbo].[PhanCong]  WITH CHECK ADD  CONSTRAINT [FK_PhanCong_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[PhanCong] CHECK CONSTRAINT [FK_PhanCong_NhanVien]
GO
ALTER TABLE [dbo].[PhieuNhapBo]  WITH CHECK ADD  CONSTRAINT [FK_PhieuNhapBo_NhaCungCap] FOREIGN KEY([MaNCC])
REFERENCES [dbo].[NhaCungCap] ([MaNCC])
GO
ALTER TABLE [dbo].[PhieuNhapBo] CHECK CONSTRAINT [FK_PhieuNhapBo_NhaCungCap]
GO
ALTER TABLE [dbo].[PhieuNhapBo]  WITH CHECK ADD  CONSTRAINT [FK_PhieuNhapBo_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[PhieuNhapBo] CHECK CONSTRAINT [FK_PhieuNhapBo_NhanVien]
GO
ALTER TABLE [dbo].[PhieuXuat]  WITH CHECK ADD  CONSTRAINT [FK_PhieuXuat_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[PhieuXuat] CHECK CONSTRAINT [FK_PhieuXuat_NhanVien]
GO
USE [master]
GO
ALTER DATABASE [DrinkSmile] SET  READ_WRITE 
GO
