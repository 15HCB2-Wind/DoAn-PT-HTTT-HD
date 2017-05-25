using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Service.Models;
using DataAccess.Repositories;
using DomainData;

namespace Service.BusinessHandler
{
    public class PhanCongBUS
    {
        public static bool AddPhanCong(PhanCongRequest request, ref PhanCongResponse response)
        {
            var data = request.Data;
            if (data.NgayBatDau > data.NgayKetThuc)
            {
                response.Errors.Add("Ngày bắt đầu không được lớn hơn ngày kết thúc làm việc");
                response.IsError = true;
            }
            if (string.IsNullOrEmpty(data.MaChuong))
            {
                response.Errors.Add("Chuồng không tồn tại, hãy chọn chuồng khác");
                response.IsError = true;
            }
            if (string.IsNullOrEmpty(data.MaNV))
            {
                response.Errors.Add("Chưa chọn nhân viên phân công");
                response.IsError = true;                
            }

            List<int> listdays = new List<int>();
            try
            {
                string[] temp = data.NgayTrongTuan.Split(',');
                foreach (string item in temp)
                {
                    if (item == "CN")
                        listdays.Add(8);
                    else
                        listdays.Add(int.Parse(item));
                }
            }
            catch (Exception)
            {
                response.Errors.Add("Hãy chọn ngày làm việc!");
                response.IsError = true;
            }
            if (response.IsError)
            {
                return false;
            }
            return true;
        }

        public static bool UpdatePhanCong(PhanCongRequest request, ref PhanCongResponse response)
        {
            var data = request.Data;
            if (data.NgayBatDau > data.NgayKetThuc)
            {
                response.Errors.Add("Ngày bắt đầu không được lớn hơn ngày kết thúc làm việc");
                response.IsError = true;
            }
            if (string.IsNullOrEmpty(data.MaChuong))
            {
                response.Errors.Add("Chuồng không tồn tại, hãy chọn chuồng khác");
                response.IsError = true;
            }

            List<int> listdays = new List<int>();
            try
            {
                string[] temp = data.NgayTrongTuan.Split(',');
                foreach (string item in temp)
                {
                    if (item == "CN")
                        listdays.Add(8);
                    else
                        listdays.Add(int.Parse(item));
                }
            }
            catch (Exception)
            {
                response.Errors.Add("Hãy chọn ngày làm việc!");
                response.IsError = true;
            }
            if (response.IsError)
            {
                return false;
            }
            return true;
        }

        public static void GetAllFromNhanVien(PhanCongRequest request, ref PhanCongResponse response)
        {
            PhanCongRepository pc_repository = new PhanCongRepository();
            var result = PhanCongRepository.GetAllFromNhanVien(request.Data);
            if (result != null)
            {
                foreach (var item in result)
                {
                    item.NgayBatDauFormatted = item.NgayBatDau.ToString("yyyy-MM-dd");
                    item.NgayKetThucFormatted = item.NgayKetThuc.ToString("yyyy-MM-dd");    
                }
                response.Data = result;
            }
            else
            {
                response.Errors.Add("Có lỗi xảy ra!");
                response.IsError = true;
            }
        }

        public static void GetOneFromPhanCong(PhanCongRequest request, ref PhanCongResponse response)
        {
            PhanCongRepository pc_repository = new PhanCongRepository();
            var result = PhanCongRepository.GetOneFromPhanCong(request.Data);
            if (result != null)
            {
                result.NgayBatDauFormatted = result.NgayBatDau.ToString("yyyy-MM-dd");
                result.NgayKetThucFormatted = result.NgayKetThuc.ToString("yyyy-MM-dd");
                response.Data = result;
            }
            else
            {
                response.Errors.Add("Có lỗi xảy ra!");
                response.IsError = true;
            }
        }

        public static void GetAllFromChuongTrai(PhanCongRequest request, ref PhanCongResponse response)
        {
            PhanCongRepository pc_repository = new PhanCongRepository();
            response.Data = PhanCongRepository.GetAllFromChuongTrai(request.Data);
        }

        public static void GetAllFromAgency(ListNhanVienChiNhanh request, ref PhanCongResponse response)
        {
            PhanCongRepository pc_repository = new PhanCongRepository();
            string condition = "";
            for (int i = 0; i < request.Data.Count; i++)
            {
                if (i == request.Data.Count - 1)
                {
                    condition = condition + "MaNV='" + request.Data[i].manhanvien + "'";
                }
                else
                {
                    condition = condition + "MaNV='" + request.Data[i].manhanvien + "' or ";
                }
            }
            if (!string.IsNullOrEmpty(condition))
                condition = "(" + condition + ")";
            var result = PhanCongRepository.GetAllFromAgency(condition);
            if (result != null)
            {
                foreach (var item in result)
                {
                    item.NgayBatDauFormatted = item.NgayBatDau.ToString("dd-MM-yyyy");
                    item.NgayKetThucFormatted = item.NgayKetThuc.ToString("dd-MM-yyyy");
                    foreach (var rqitem in request.Data)
                    {
                        if (item.MaNV == rqitem.manhanvien)
                        {
                            item.HoTen = rqitem.hoten;
                        }
                    }
                }
                response.Data = result;
            }
            else
            {
                response.Errors.Add("Có lỗi xảy ra!");
                response.IsError = true;
            }
        }
    }
}