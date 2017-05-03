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
            if (ErrorHandler.AccountAPIChecker.LoginValidate(request, ref response))
            {
                try
                {
                    response.Data = NhanVienRepository.GetInstance().IsExistAndGet(request.Username, request.Password);
                    if (response.Data == null)
                    {
                        response.Errors.Add("Đăng nhập không thành công.");
                        response.IsError = true;
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
        public HttpResponseMessage Login([FromBody] FPassBodyRequest request)
        {
            return Request.CreateResponse(HttpStatusCode.NoContent);
        }

        //change pass
        [HttpPost]
        [Route("change")]
        public HttpResponseMessage Login([FromBody] CPassBodyRequest request)
        {
            return Request.CreateResponse(HttpStatusCode.NoContent);
        }

        //sync from management service
        [HttpPost]
        [Route("sync")]
        public HttpResponseMessage Sync([FromBody] SyncBodyRequest request)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, NhanVienRepository.GetInstance().Add(new NhanVien()
                {
                    MaNV = request.Id,
                    TenTaiKhoan = request.Username,
                    MatKhau = request.Password,
                    Email = request.Email,
                    MaPQ = request.PermissionId,
                }));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ex);
            }
        }
    }
}
