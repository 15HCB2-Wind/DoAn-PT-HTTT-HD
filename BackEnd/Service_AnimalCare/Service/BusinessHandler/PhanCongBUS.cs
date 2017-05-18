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
        public static PhanCong Parser(PhanCongRequest request)
        {
            return new PhanCong()
            {
                MaPhanCong = request.IdPhanCong,
                MaChuong = request.IdChuong,
                MaNV = request.IdNhanVien,
                NgayBatDau = request.FromDate,
                NgayKetThuc = request.ToDate,
                NgayTrongTuan = request.Days
            };
        }
        public static bool AddPhanCong(PhanCongRequest request, ref PhanCongResponse response)
        {
            PhanCong pc = Parser(request);
            string error="";
            try
            {
                error = PhanCongRepository.Insert(pc);
                int t = int.Parse(error);
            }
            catch (Exception)
            {
                response.IsError = true;
                response.Errors.Add(error);
            }
            return true;
        }

        public static bool UpdatePhanCong(PhanCongRequest request, ref PhanCongResponse response)
        {
            PhanCong pc = Parser(request);
            string error = "";
            try
            {
                error = PhanCongRepository.Update(pc);
                int t = int.Parse(error);
            }
            catch (Exception)
            {
                response.IsError = true;
                response.Errors.Add(error);
            }
            return true;
        }

        public static void GetAllFromNhanVien(PhanCongRequest request, ref PhanCongResponse response)
        {
            PhanCong pc = Parser(request);
            PhanCongRepository pc_repository = new PhanCongRepository();
            response.Data = PhanCongRepository.GetAllFromNhanVien(pc);
        }

        public static void GetOneFromPhanCong(PhanCongRequest request, ref PhanCongResponse response)
        {
            PhanCong pc = Parser(request);
            PhanCongRepository pc_repository = new PhanCongRepository();
            response.Data = PhanCongRepository.GetOneFromPhanCong(pc);
        }

        public static void GetAllFromChuongTrai(PhanCongRequest request, ref PhanCongResponse response)
        {
            PhanCong pc = Parser(request);
            PhanCongRepository pc_repository = new PhanCongRepository();
            response.Data = PhanCongRepository.GetAllFromChuongTrai(pc);
        }
    }
}