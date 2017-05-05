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
using System.Threading.Tasks;
using System.Web;

namespace Service.BusinessHandler
{
    public class AccountAPIChecker
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

        public static bool CPassValidate(CPassBodyRequest request, ref CPassBodyRessponse response)
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

        public static bool CFPassValidate(CFPassBodyRequest request, ref CFPassBodyRessponse response)
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

        public static bool FPassValidate(FPassBodyRequest request, ref FPassBodyRessponse response)
        {
            return !response.IsError;
        }

        public static bool TokenValidate(BodyRequest request)
        {
            return Token.Validate(request.Token, (tokenValue, predicates) =>
            {
                try
                {
                    var tokenV = tokenValue as NhanVien;
                    var userId = Convert.ToString(predicates[0]);
                    var role = Convert.ToInt32(predicates[1]);
                    return tokenV.MaNV.Equals(userId) && tokenV.CapPQ == role;
                }
                catch
                {
                    return false;
                }
            }, request.TokenPredicate1, request.TokenPredicate2);
        }

        public static async void SyncPassword2ManagementServiceAsync(string newp)
        {
            while (new HttpClient().PostAsync("http://localhost:29370/hcm/nhanvien/insert", new StringContent(JsonConvert.SerializeObject(newp), Encoding.UTF8, "application/json")).Result.StatusCode != HttpStatusCode.OK) { }
        }
    }
}