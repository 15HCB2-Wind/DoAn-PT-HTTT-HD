using DataAccess.Repositories;
using DomainData;
using Service.BusinessHandler;
using Service.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;

namespace Service.Controllers
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    [RoutePrefix("assignment")]
    public class PhanCongAPIController : ApiController
    {
        //add new assignment
        [HttpPost]
        [Route("add")]
        public HttpResponseMessage AddPhanCong([FromBody] PhanCongRequest request)
        {
            var response = new PhanCongResponse(); 
            if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)) {
                PhanCongBUS.AddPhanCong(request, ref response);
                if (response.IsError)
                    return Request.CreateResponse(HttpStatusCode.OK, response);
                else
                {
                    if (PhanCongRepository.Insert(request.Data) < 0)
                    {
                        response.Errors.Add("Lỗi hệ thống");
                        response.IsError = true;
                    }
                    else
                    {
                        response.Data = "Thêm thành công!";
                    }
                }
            }
            
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //update assignment
        [HttpPost]
        [Route("update")]
        public HttpResponseMessage UpdatePhanCong([FromBody] PhanCongRequest request)
        {
            var response = new PhanCongResponse();
            if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2))
            {
                PhanCongBUS.UpdatePhanCong(request, ref response);
                if (response.IsError)
                    return Request.CreateResponse(HttpStatusCode.OK, response);
                else
                {
                    if (PhanCongRepository.Update(request.Data) < 0)
                    {
                        response.Errors.Add("Lỗi hệ thống");
                        response.IsError = true;
                    }
                    else
                    {
                        response.Data = "Sửa thành công!";
                    }
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //Lấy danh sách phân công của 1 chuồng
        [HttpPost]
        [Route("getListAssignmentsOfBarn")]
        public HttpResponseMessage GetAllFromChuongTrai([FromBody] PhanCongRequest request)
        {
            var response = new PhanCongResponse();
            if (BusinessHandler.TokenBUS.tokenCheck(request, response, 1))
            {
                try
                {
                    BusinessHandler.PhanCongBUS.GetAllFromChuongTrai(request, ref response);
                }
                catch (Exception)
                {
                    response.Errors.Add("Lỗi hệ thống");
                    response.IsError = true;
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //Lấy danh sách phân công của 1 chi nhánh
        [HttpPost]
        [Route("getListAssignmentsOfAgency")]
        public HttpResponseMessage GetAllFromAgency([FromBody] ListNhanVienChiNhanh request)
        {
            var response = new PhanCongResponse();
            if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2))
            {
                try
                {
                    BusinessHandler.PhanCongBUS.GetAllFromAgency(request, ref response);
                }
                catch (Exception)
                {
                    response.Errors.Add("Lỗi hệ thống");
                    response.IsError = true;
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }


        //Lấy danh sách phân công của 1 nhân viên
        [HttpPost]
        [Route("getListAssignmentsOfEmployeer")]
        public HttpResponseMessage GetAllFromNhanVien([FromBody] PhanCongRequest request)
        {
            var response = new PhanCongResponse();
            TokenData tkdata = BusinessHandler.TokenBUS.tokenData(request, response, 1);
            if (tkdata != null)
            {
                try
                {
                    request.Data = new PhanCong();
                    request.Data.MaNV = tkdata.UserId;
                    BusinessHandler.PhanCongBUS.GetAllFromNhanVien(request, ref response);
                }
                catch (Exception)
                {
                    response.Errors.Add("Lỗi hệ thống");
                    response.IsError = true;
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //Lấy 1 Phân công khi truyền vào mã phân công
        [HttpPost]
        [Route("getSingleAssignment")]
        public HttpResponseMessage GetOneFromPhanCong([FromBody] PhanCongRequest request)
        {
            var response = new PhanCongResponse();
            if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2))
            {
                try
                {
                    BusinessHandler.PhanCongBUS.GetOneFromPhanCong(request, ref response);
                }
                catch (Exception)
                {
                    response.Errors.Add("Lỗi hệ thống");
                    response.IsError = true;
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }
    }
}
