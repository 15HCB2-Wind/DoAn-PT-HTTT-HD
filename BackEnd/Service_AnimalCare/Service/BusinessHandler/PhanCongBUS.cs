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
                response.Errors.Add("Định dạng ngày làm việc ko đúng, ví dụ: 2,3,4,5,6,7,CN");
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
                response.Errors.Add("Định dạng ngày làm việc ko đúng, ví dụ: 2,3,4,5,6,7,CN");
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
            response.Data = PhanCongRepository.GetAllFromNhanVien(request.Data);
        }

        public static void GetOneFromPhanCong(PhanCongRequest request, ref PhanCongResponse response)
        {
            PhanCongRepository pc_repository = new PhanCongRepository();
            response.Data = PhanCongRepository.GetOneFromPhanCong(request.Data);
        }

        public static void GetAllFromChuongTrai(PhanCongRequest request, ref PhanCongResponse response)
        {
            PhanCongRepository pc_repository = new PhanCongRepository();
            response.Data = PhanCongRepository.GetAllFromChuongTrai(request.Data);
        }
    }
}