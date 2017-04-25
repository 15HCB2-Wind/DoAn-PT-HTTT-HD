using DataAccess.Repositories;
using DomainData;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace DataService.Controllers
{
    [RoutePrefix("chitietbaocaoxuat")]
    public class ChiTietBaoCaoXuatAPIController : ApiController 
    {
        [HttpGet]
        [Route("getall")]
        public HttpResponseMessage GetAll()
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChiTietBaoCaoXuatRepository.GetInstance().GetAll());
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
                return Request.CreateResponse(HttpStatusCode.OK, ChiTietBaoCaoXuatRepository.GetInstance().GetFrom(id));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        [HttpPost]
        [Route("insert")]
        public HttpResponseMessage Insert([FromBody] ChiTietBaoCaoXuat obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChiTietBaoCaoXuatRepository.GetInstance().Insert(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        [HttpPut]
        [Route("update")]
        public HttpResponseMessage Update([FromBody] ChiTietBaoCaoXuat obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChiTietBaoCaoXuatRepository.GetInstance().Update(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }
    }
}

