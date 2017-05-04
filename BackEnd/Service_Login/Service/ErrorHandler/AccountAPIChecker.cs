using Service.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Service.ErrorHandler
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
    }
}