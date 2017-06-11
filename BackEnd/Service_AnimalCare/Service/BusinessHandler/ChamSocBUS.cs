using DomainData;
using Newtonsoft.Json;
using Service.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading;
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

        public static void ReportTinhTrangBo(ReportTinhTrangBoRequest request,ref ReportTinhTrangBoResponse response)
        {
            if (request.NgayBatDau > DateTime.Now)
            {
                response.IsError = true;
                response.Errors.Add("Lỗi ngày bắt đầu lớn hơn hiện tại!");
            }
        }

        public static void UpdateCowState(string token, string id, string tinhtrang)
        {
            var thread = new Thread((object t) =>
            {
                int times = 3;
                bool fail = true;
                var client = new HttpClient();
                do
                {
                    try
                    {
                        var result = client.PostAsync(Configs.UPDATE_COWSTATE, new StringContent(JsonConvert.SerializeObject(new UpdateCow { Token = token, Id = id, NewState = tinhtrang }), Encoding.UTF8, "application/json")).Result.Content.ReadAsAsync<object>().Result;
                        fail = result == null;
                    }
                    catch { }
                } while (fail && --times > 0);
                (t as Thread).Abort();
            });
            thread.Start(thread);
        }

        public static bool UpdateMilk(string token, float value)
        {
            KhoSuaResponse result = null;
            int times = 3;
            bool fail = true;
            var client = new HttpClient();
            do
            {
                try
                {
                    result = client.PostAsync(Configs.UPDATE_MILK, new StringContent(JsonConvert.SerializeObject(new UpdateMilkRequest { Token = token, Value = value }), Encoding.UTF8, "application/json")).Result.Content.ReadAsAsync<KhoSuaResponse>().Result;
                    fail = result == null;
                }
                catch { }
            } while (fail && --times > 0);
            if (result == null)
                return false;
            return !result.IsError;
        }
    }
}