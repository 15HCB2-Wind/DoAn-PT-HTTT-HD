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
        public static bool LoginValidate(LoginRequest request, ref LoginResponse response)
        {
            if (string.IsNullOrEmpty(request.Username) || string.IsNullOrEmpty(request.Password) || request.Username.Count() > 50 || request.Password.Count() > 50)
            {
                response.Errors.Add("Đăng nhập không hợp lệ.");
                response.IsError = true;
            }
            return !response.IsError;
        }

        public static bool CPassValidate(CPassRequest request, ref CPassResponse response)
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

        public static bool CFPassValidate(CFPassRequest request, ref CFPassResponse response)
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

        public static void SyncPassword2ManagementServiceAsync(ChangePasswordRequest obj)
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
                        var result = client.PostAsync(Configs.SYNC_TO_MANAGEMENT_SERVICE, new StringContent(JsonConvert.SerializeObject(obj), Encoding.UTF8, "application/json")).Result.Content.ReadAsAsync<ChangePasswordResponse>().Result;
                        fail = result == null || result.IsError;
                    }
                    catch { }
                } while (fail && --times > 0);
                (t as Thread).Abort();
            });
            thread.Start(thread);
        }
    }
}