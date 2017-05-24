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
                response.Errors.Add("Chưa cho " + request.Data.MaBo + "ăn!");
            }
            if (!request.Data.DaDonVeSinh)
            {
                response.IsError = true;
                response.Errors.Add("Chưa dọn dẹp vệ sinh bò " + request.Data.MaBo);
            }
            if (!(request.Data.DaVatSua && (request.Data.LuongSua <=0)))
            {
                response.IsError = true;
                response.Errors.Add("Hãy check đã vắt sữa!");
            }
            if (!response.IsError)
            {
                request.Data.TinhTrangCongViec = "Tốt";
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
            if (!(request.Data.DaVatSua && (request.Data.LuongSua <= 0)))
            {
                response.IsError = true;
                response.Errors.Add("Hãy check đã vắt sữa!");
            }
            if (!response.IsError)
            {
                request.Data.TinhTrangCongViec = "Tốt";
            }
        }
    }
}