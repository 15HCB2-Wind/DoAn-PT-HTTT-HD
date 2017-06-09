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
    [RoutePrefix("phancong")]
    public class PhanCongAPIController : ApiController
    {
        [HttpGet]
        [Route("getall")]
        public HttpResponseMessage GetAll()
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, PhanCongRepository.GetInstance().GetAll());
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        [HttpGet]
        [Route("getfrom/{id}")]
        public HttpResponseMessage GetAll(string id)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, PhanCongRepository.GetInstance().GetFrom(id));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        [HttpPost]
        [Route("insert")]
        public HttpResponseMessage Insert([FromBody] PhanCong obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, PhanCongRepository.GetInstance().Insert(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        [HttpPut]
        [Route("update")]
        public HttpResponseMessage Update([FromBody] PhanCong obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, PhanCongRepository.GetInstance().Update(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }
    }
}
