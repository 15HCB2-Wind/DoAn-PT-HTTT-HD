using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using DataAccess.Repositories;
using DomainData;

namespace DataService.Controllers
{
    [RoutePrefix("chamsoc")]
    public class ChamSocAPIController : ApiController
    {
        //lấy tất cả 
        [HttpGet]
        [Route("getall")]
        public HttpResponseMessage GetAll()
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChamSocRepository.GetInstance().GetAll());
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        //lấy theo mã chăm sóc
        [HttpGet]
        [Route("getfrom/{maChamSoc}")]
        public HttpResponseMessage GetAll(string maChamSoc)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChamSocRepository.GetInstance().GetFrom(maChamSoc));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        //thêm bảng chăm sóc
        [HttpPost]
        [Route("insert")]
        public HttpResponseMessage insert([FromBody] ChamSoc obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChamSocRepository.GetInstance().Insert(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        //chỉnh sửa bảng chăm sóc
        [HttpPost]
        [Route("update")]
        public HttpResponseMessage update([FromBody] ChamSoc obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChamSocRepository.GetInstance().Update(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }
    }
}
