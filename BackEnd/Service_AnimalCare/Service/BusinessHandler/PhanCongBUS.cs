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
            PhanCong pc = new PhanCong
            {
                MaChuong = request.IdChuong,
                MaNV = request.IdNhanVien,
                NgayBatDau = request.FromDate,
                NgayKetThuc = request.ToDate,
                NgayTrongTuan = request.Days
            };
            PhanCongRepository pc_repository = new PhanCongRepository();
            string error="";
            try
            {
                error = pc_repository.Insert(pc);
            }
            catch (Exception)
            {
                response.IsError = true;
                response.Errors.Add(error);
            }
            return true;
        }
    }
}