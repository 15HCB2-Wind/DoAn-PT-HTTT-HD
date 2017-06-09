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
    [RoutePrefix("bo")]
    public class BoAPIController : ApiController
    {
        //lấy tất cả 
        [HttpGet]
        [Route("getall")]
        public HttpResponseMessage GetAll()
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, BoRepository.GetInstance().GetAll());
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        //lấy theo mã bò
        [HttpGet]
        [Route("getfrom/{maBo}")]
        public HttpResponseMessage GetAll(string maBo)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, BoRepository.GetInstance().GetFrom(maBo));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        //thêm bò
        [HttpPost]
        [Route("insert")]
        public HttpResponseMessage insert([FromBody] Bo obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, BoRepository.GetInstance().Insert(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        //chỉnh sửa bò
        [HttpPost]
        [Route("update")]
        public HttpResponseMessage update([FromBody] Bo obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, BoRepository.GetInstance().Update(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }
    }
}
