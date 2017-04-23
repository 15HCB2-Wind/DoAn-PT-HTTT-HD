using System;
using System.Collections.Generic;
using DomainData;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using DataAccess.Repositories;

namespace DataService.Controllers
{
    [RoutePrefix("chuongtrai")]
    public class ChuongTraiAPIController : ApiController
    {
        [HttpGet]
        [Route("getall")]
        public HttpResponseMessage GetAll()
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK,ChuongTraiRepository.GetInstance().GetAll());
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError,ex);
            }
        }

        [HttpGet]
        [Route("getfrom/{id}")]
        public HttpResponseMessage GetAll(string id)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChuongTraiRepository.GetInstance().GetFrom(id));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        [HttpPost]
        [Route("insert")]
        public HttpResponseMessage Insert([FromBody] ChuongTrai obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChuongTraiRepository.GetInstance().Insert(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        [HttpPut]
        [Route("update")]
        public HttpResponseMessage Update([FromBody] ChuongTrai obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChuongTraiRepository.GetInstance().Update(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        [HttpPut]
        [Route("delete")]
        public HttpResponseMessage Delete([FromBody] ChuongTrai obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChuongTraiRepository.GetInstance().Delete(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }
    }
}
