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
        public HttpResponseMessage Login([FromBody] LoginRequest request)
        {
            var response = new LoginResponse();
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
                            Token = Token.Create(user, DateTime.Now.Ticks.ToString()),
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
        public HttpResponseMessage Change([FromBody] CPassRequest request)
        {
            var response = new CPassResponse();
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
                            BusinessHandler.AccountBUS.SyncPassword2ManagementServiceAsync(new ChangePasswordRequest
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
        public HttpResponseMessage Forget([FromBody] FPassRequest request)
        {
            var response = new FPassResponse();
            try
            {
                var user = NhanVienRepository.GetInstance().GetUserByEmail(request.Email);
                if (user != null)
                {
                    MailMessage mail = new MailMessage(Configs.EMAIL_SENDER, request.Email);
                    SmtpClient client = new SmtpClient();
                    client.Port = 25;
                    client.DeliveryMethod = SmtpDeliveryMethod.Network;
                    client.UseDefaultCredentials = false;
                    client.Host = "smtp.google.com";
                    mail.Subject = "Drink Smile - Change Password!";
                    mail.Body = string.Format("Click here to change your password:\n{0}token?={1}\nYou can not change your password after 10 minutes.", Configs.CHANGE_PASS_URL, Token.Create(user, DateTime.Now.Ticks.ToString(), 10));
                    client.Send(mail);
                }
                response.Data = "Kiểm tra email của bạn để thay lấy lại mật khẩu.";
            }
            catch
            {
                response.Errors.Add("Lỗi hệ thống.");
                response.IsError = true;
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //change pass from forget
        [HttpPost]
        [Route("cfpass")]
        public HttpResponseMessage Change([FromBody] CFPassRequest request)
        {
            var response = new CFPassResponse();
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
                            BusinessHandler.AccountBUS.SyncPassword2ManagementServiceAsync(new ChangePasswordRequest
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
        public HttpResponseMessage Sync([FromBody] SyncRequest request)
        {
            var response = new SyncResponse();
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
                            Email = request.Email,
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
                            Email = request.Email,
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
                response.IsError = result != 1;
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
        public HttpResponseMessage CheckToken([FromBody] CheckTokenRequest request)
        {
            var response = new CheckTokenResponse();
            try
            {
                if (request.TokenPassword == Configs.TOKEN_PASSWORD)
                {
                    var token = Token.Get(request.Token);
                    if (token == null)
                    {
                        response.IsError = true;
                    }
                    else
                    {
                        if (token.CapPQ == request.Role)
                        {
                            response.Data = token;
                        }
                        else
                        {
                            response.IsError = true;
                        }
                    }
                }
                else
                {
                    response.IsError = true;
                }
            }
            catch
            {
                response.IsError = true;
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }
    }
}
