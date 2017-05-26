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
    [RoutePrefix("animalcare")]
    public class ChamSocAPIController : ApiController
    {
        //add new assignment
        [HttpPost]
        [Route("add")]
        public HttpResponseMessage AddChamSoc([FromBody] ChamSocRequest request)
        {
            var response = new ChamSocResponse();
            if (BusinessHandler.TokenBUS.tokenCheck(request, response, 1))
            {

                ChamSocBUS.AddChamSoc(request, ref response);
                if (response.IsError)
                    return Request.CreateResponse(HttpStatusCode.OK, response);
                else
                {
                    ChamSoc cs = ChamSocRepository.IsExistsChamSoc(request.Data);
                    if (cs!=null)
                    {
                        request.Data.MaChamSoc = cs.MaChamSoc;
                        if (ChamSocRepository.Update(request.Data) < 0)
                        {
                            response.Errors.Add("Lỗi hệ thống");
                            response.IsError = true;
                        }
                        else
                        {
                            response.Data = "Sửa thành công!";
                        }
                    }
                    else
                    {
                        if (ChamSocRepository.Insert(request.Data) < 0)
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
            }

            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //update assignment
        [HttpPost]
        [Route("update")]
        public HttpResponseMessage UpdateChamSoc([FromBody] ChamSocRequest request)
        {
            var response = new ChamSocResponse();
            if (BusinessHandler.TokenBUS.tokenCheck(request, response, 1))
            {
                ChamSocBUS.UpdateChamSoc(request, ref response);
                if (response.IsError)
                    return Request.CreateResponse(HttpStatusCode.OK, response);
                else
                {
                    if (ChamSocRepository.Update(request.Data) < 0)
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

        //GetListChamSocFromPhanCong
        [HttpPost]
        [Route("getAll")]
        public HttpResponseMessage getAll([FromBody] PhanCongRequest request)
        {
            var response = new ChamSocResponse();
            if (BusinessHandler.TokenBUS.tokenCheck(request, response, 1))
            {
                var result = ChamSocRepository.GetListChamSocFromPhanCong(request.Data);
                if (result == null)
                {
                    response.Errors.Add("Lỗi hệ thống");
                    response.IsError = true;
                }
                else
                    response.Data = result;
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }
    }
}
