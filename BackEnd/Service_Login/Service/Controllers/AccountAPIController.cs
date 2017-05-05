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
            if (BusinessHandler.AccountAPIChecker.LoginValidate(request, ref response))
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
                catch (Exception ex)
                {
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ex);
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
            if (BusinessHandler.AccountAPIChecker.CPassValidate(request, ref response))
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
                            BusinessHandler.AccountAPIChecker.SyncPassword2ManagementServiceAsync(new
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
                catch (Exception ex)
                {
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ex);
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
            if (BusinessHandler.AccountAPIChecker.FPassValidate(request, ref response))
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
                catch (Exception ex)
                {
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ex);
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
            if (BusinessHandler.AccountAPIChecker.CFPassValidate(request, ref response))
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
                            BusinessHandler.AccountAPIChecker.SyncPassword2ManagementServiceAsync(new
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
                catch (Exception ex)
                {
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ex);
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //sync from management service
        [HttpPost]
        [Route("sync")]
        public HttpResponseMessage Sync([FromBody] SyncBodyRequest request)
        {
            try
            {
                switch (request.SyncType)
                {
                    case 1:
                        return Request.CreateResponse(HttpStatusCode.OK, NhanVienRepository.GetInstance().Add(new NhanVien()
                        {
                            MaNV = request.Id,
                            HoTen = request.FullName,
                            TenTaiKhoan = request.Username,
                            MatKhau = request.Password,
                            CapPQ = request.PermissionLevel,
                        }));
                    case 0:
                        return Request.CreateResponse(HttpStatusCode.OK, NhanVienRepository.GetInstance().Update(new NhanVien()
                        {
                            MaNV = request.Id,
                            HoTen = request.FullName,
                            TenTaiKhoan = request.Username,
                            MatKhau = request.Password,
                            CapPQ = request.PermissionLevel,
                        }));
                    case -1:
                        return Request.CreateResponse(HttpStatusCode.OK, NhanVienRepository.GetInstance().Delete(request.Id));
                    default:
                        return Request.CreateResponse(HttpStatusCode.NoContent);
                }
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ex);
            }
        }

        //check token
        [HttpPost]
        [Route("checktoken")]
        public HttpResponseMessage CheckToken([FromBody] CheckTokenBodyRequest request)
        {
            if (request.TokenPassword == Configs.TOKEN_PASSWORD)
            {
                var response = new CheckTokenBodyResponse();
                response.Data = Token.Get(request.Token);
                return Request.CreateResponse(HttpStatusCode.OK, response);
            }
            return Request.CreateResponse(HttpStatusCode.OK, false);
        }
    }
}
