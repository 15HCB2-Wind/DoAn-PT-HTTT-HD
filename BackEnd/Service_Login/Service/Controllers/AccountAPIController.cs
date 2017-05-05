using DataAccess.Repositories;
using Service.Models;
using Service;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;
using DomainData;
using Service.Helpers;
using System.Net.Mail;
using System.Text;

namespace Service.Controllers
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    [RoutePrefix("account")]
    public class AccountAPIController : ApiController
    {
        //login
        [HttpPost]
        [Route("login")]
        public HttpResponseMessage Login([FromBody] LoginBodyRequest request)
        {
            var response = new LoginBodyResponse();
            if (BusinessHandler.AccountBUS.LoginValidate(request, ref response))
            {
                try
                {
                    var user = NhanVienRepository.GetInstance().IsExistAndGet(request.Username, Security.Encrypt(request.Password));
                    if (user == null)
                    {
                        response.Errors.Add("Đăng nhập không thành công.");
                        response.IsError = true;
                    }
                    else
                    {
                        response.Data = new
                        {
                            Token = Token.Create(user, string.Format("{0}${1}${2}${3}", user.MaNV, user.CapPQ, user.TenTaiKhoan, DateTime.Now)),
                            FullName = user.HoTen,
                            PermissionLevel = user.CapPQ,
                        };
                    }
                }
                catch
                {
                    response.Errors.Add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //change pass
        [HttpPost]
        [Route("change")]
        public HttpResponseMessage Change([FromBody] CPassBodyRequest request)
        {
            var response = new CPassBodyResponse();
            if (BusinessHandler.AccountBUS.CPassValidate(request, ref response))
            {
                try
                {
                    var tokenValue = Token.Get(request.Token) as NhanVien;
                    if (tokenValue == null)
                    {
                        response.IsTokenTimeout = true;
                    }
                    else
                    {
                        var oldp = Security.Encrypt(request.OldPassword);
                        var newp = Security.Encrypt(request.NewPassword);
                        var result = NhanVienRepository.GetInstance().ChangePass(tokenValue.MaNV, oldp, newp);
                        if (result == 1)
                        {
                            BusinessHandler.AccountBUS.SyncPassword2ManagementServiceAsync(new
                            {
                                UserId = tokenValue.MaNV,
                                NewPass = newp
                            });
                            response.Data = "Thay đổi mật khẩu thành công.";
                        }
                        else
                        {
                            response.Errors.Add("Thay đổi mật khẩu thất bại.");
                            response.IsError = true;
                        }
                    }
                }
                catch
                {
                    response.Errors.Add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //forget pass
        [HttpPost]
        [Route("forget")]
        public HttpResponseMessage Forget([FromBody] FPassBodyRequest request)
        {
            var response = new FPassBodyResponse();
            if (BusinessHandler.AccountBUS.FPassValidate(request, ref response))
            {
                try
                {
                    var tokenValue = Token.Get(request.Token) as NhanVien;
                    if (tokenValue == null)
                    {
                        response.IsTokenTimeout = true;
                    }
                    else
                    {
                        MailMessage mail = new MailMessage(Configs.EMAIL_SENDER, tokenValue.Email);
                        SmtpClient client = new SmtpClient();
                        client.Port = 25;
                        client.DeliveryMethod = SmtpDeliveryMethod.Network;
                        client.UseDefaultCredentials = false;
                        client.Host = "smtp.google.com";
                        mail.Subject = "Drink Smile - Change Password!";
                        mail.Body = string.Format("Click here to change your password:\n{0}token?={1}", Configs.CHANGE_PASS_URL, request.Token);
                        client.Send(mail);
                        response.Data = "Kiểm tra email của bạn để thay lấy lại mật khẩu.";
                    }
                }
                catch
                {
                    response.Errors.Add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //change pass from forget
        [HttpPost]
        [Route("cfpass")]
        public HttpResponseMessage Change([FromBody] CFPassBodyRequest request)
        {
            var response = new CFPassBodyResponse();
            if (BusinessHandler.AccountBUS.CFPassValidate(request, ref response))
            {
                try
                {
                    var tokenValue = Token.Get(request.Token) as NhanVien;
                    if (tokenValue == null)
                    {
                        response.IsTokenTimeout = true;
                    }
                    else
                    {
                        var newp = Security.Encrypt(request.NewPassword);
                        var result = NhanVienRepository.GetInstance().ChangePass(tokenValue.MaNV, newp);
                        if (result == 1)
                        {
                            BusinessHandler.AccountBUS.SyncPassword2ManagementServiceAsync(new
                            {
                                UserId = tokenValue.MaNV,
                                NewPass = newp
                            });
                            response.Data = "Thay đổi mật khẩu thành công.";
                        }
                        else
                        {
                            response.Errors.Add("Thay đổi mật khẩu thất bại.");
                            response.IsError = true;
                        }
                    }
                }
                catch
                {
                    response.Errors.Add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //sync from management service
        [HttpPost]
        [Route("sync")]
        public HttpResponseMessage Sync([FromBody] SyncBodyRequest request)
        {
            var response = new SyncBodyResponse();
            try
            {
                int result = -1;
                switch (request.SyncType)
                {
                    case 1:
                        result = NhanVienRepository.GetInstance().Add(new NhanVien()
                        {
                            MaNV = request.Id,
                            HoTen = request.FullName,
                            TenTaiKhoan = request.Username,
                            MatKhau = request.Password,
                            CapPQ = request.PermissionLevel,
                        });
                        break;
                    case 0:
                        result = NhanVienRepository.GetInstance().Update(new NhanVien()
                        {
                            MaNV = request.Id,
                            HoTen = request.FullName,
                            TenTaiKhoan = request.Username,
                            MatKhau = request.Password,
                            CapPQ = request.PermissionLevel,
                        });
                        break;
                    case -1:
                        result = NhanVienRepository.GetInstance().Delete(request.Id);
                        break;
                    default:
                        response.IsError = true;
                        break;
                }
                if (!response.IsError)
                {
                    switch (result)
                    {
                        case -1:
                        case 0:
                            response.IsError = true;
                            break;
                        case 1:
                            break;
                        default:
                            break;
                    }
                }
            }
            catch
            {
                response.IsError = true;
            }
            return Request.CreateResponse(HttpStatusCode.NoContent, response);
        }

        //check token
        [HttpPost]
        [Route("checktoken")]
        public HttpResponseMessage CheckToken([FromBody] CheckTokenBodyRequest request)
        {
            var response = new CheckTokenBodyResponse();
            if (request.TokenPassword == Configs.TOKEN_PASSWORD)
            {
                var token = Token.Get(request.Token);
                if (token == null)
                {
                    response.Data = token;
                }
                else
                {
                    response.IsError = true;
                }
            }
            else
            {
                response.IsError = true;
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }
    }
}
