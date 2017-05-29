using Service.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Service.BusinessHandler
{
    public class ChamSocBUS
    {
        public static void AddChamSoc(ChamSocRequest request, ref ChamSocResponse response)
        {
            if (!request.Data.DaChoAn)
            {
                response.IsError = true;
                response.Errors.Add("Chưa cho " + request.Data.MaBo + " ăn!");
            }
            if (!request.Data.DaDonVeSinh)
            {
                response.IsError = true;
                response.Errors.Add("Chưa dọn dẹp vệ sinh bò " + request.Data.MaBo);
            }
            if (!response.IsError)
            {
                request.Data.DaVatSua = !(request.Data.LuongSua <= 0);
                request.Data.NgayGhiNhan = DateTime.Now;
            }
        }

        public static void UpdateChamSoc(ChamSocRequest request, ref ChamSocResponse response)
        {
            if (!request.Data.DaChoAn)
            {
                response.IsError = true;
                response.Errors.Add("Chưa cho " + request.Data.MaBo + "ăn!");
            }
            if (!request.Data.DaDonVeSinh)
            {
                response.IsError = true;
                response.Errors.Add("Chưa dọn dẹp vệ sinh bò " + request.Data.MaBo);
            }
            if (!response.IsError)
            {
                request.Data.DaVatSua = !(request.Data.LuongSua <= 0);
                request.Data.NgayGhiNhan = DateTime.Now;
            }
        }

        public static void AddTinhTrangBo(TinhTrangBoRequest request, ref TinhTrangBoResponse response)
        {
            if (request.Data.CanNang<=0 || request.Data.ChieuCao<=0)
            {
                response.IsError = true;
                response.Errors.Add("Chiều cao hoặc cân nặng sai.");
            }
        }

    }
}