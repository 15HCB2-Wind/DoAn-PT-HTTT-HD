using DomainData;
using Newtonsoft.Json;
using Service.Helpers;
using Service.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Web;

namespace Service.BusinessHandler
{
    public class AccountBUS
    {
        public static bool LoginValidate(LoginBodyRequest request, ref LoginBodyResponse response)
        {
            if (string.IsNullOrEmpty(request.Username) || string.IsNullOrEmpty(request.Password) || request.Username.Count() > 100 || request.Password.Count() > 50)
            {
                response.Errors.Add("Đăng nhập không hợp lệ.");
                response.IsError = true;
            }
            return !response.IsError;
        }

        public static bool CPassValidate(CPassBodyRequest request, ref CPassBodyResponse response)
        {
            if (string.IsNullOrEmpty(request.OldPassword))
            {
                response.OldPassword_Errors.Add("Mật khẩu cũ không được để trống.");
                response.IsError = true;
            }
            if (request.OldPassword.Count() > 50)
            {
                response.OldPassword_Errors.Add("Mật khẩu cũ quá dài.");
                response.IsError = true;
            }
            if (string.IsNullOrEmpty(request.NewPassword))
            {
                response.NewPassword_Errors.Add("Mật khẩu mới không được để trống.");
                response.IsError = true;
            }
            if (request.NewPassword.Count() > 50)
            {
                response.NewPassword_Errors.Add("Mật khẩu mới quá dài.");
                response.IsError = true;
            }
            return !response.IsError;
        }

        public static bool CFPassValidate(CFPassBodyRequest request, ref CFPassBodyResponse response)
        {
            if (string.IsNullOrEmpty(request.NewPassword))
            {
                response.NewPassword_Errors.Add("Mật khẩu mới không được để trống.");
                response.IsError = true;
            }
            if (request.NewPassword.Count() > 50)
            {
                response.NewPassword_Errors.Add("Mật khẩu mới quá dài.");
                response.IsError = true;
            }
            return !response.IsError;
        }

        public static bool FPassValidate(FPassBodyRequest request, ref FPassBodyResponse response)
        {
            return !response.IsError;
        }

        public static void SyncPassword2ManagementServiceAsync(object obj)
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
                        fail = client.PostAsync(string.Format("{0}{1}", Configs.MANAGEMENT_SERVICE, "NhanVien/sync"), new StringContent(JsonConvert.SerializeObject(obj), Encoding.UTF8, "application/json")).Result.StatusCode != HttpStatusCode.OK;
                    }
                    catch { }
                } while (fail && --times > 0);
                (t as Thread).Abort();
            });
            thread.Start(thread);
        }
    }
}