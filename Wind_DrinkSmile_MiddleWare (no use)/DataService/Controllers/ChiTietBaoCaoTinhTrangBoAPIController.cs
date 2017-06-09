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
    [RoutePrefix("chitietbaocaotinhtrangbo")]
    public class ChiTietBaoCaoTinhTrangBoAPIController : ApiController
    {
        [HttpGet]
        [Route("getall")]
        public HttpResponseMessage GetAll()
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChiTietBaoCaoTinhTrangBoRepository.GetInstance().GetAll());
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
                return Request.CreateResponse(HttpStatusCode.OK, ChiTietBaoCaoTinhTrangBoRepository.GetInstance().GetFrom(id));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        [HttpPost]
        [Route("insert")]
        public HttpResponseMessage Insert([FromBody] ChiTietBaoCaoTinhTrangBo obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChiTietBaoCaoTinhTrangBoRepository.GetInstance().Insert(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        [HttpPut]
        [Route("update")]
        public HttpResponseMessage Update([FromBody] ChiTietBaoCaoTinhTrangBo obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, ChiTietBaoCaoTinhTrangBoRepository.GetInstance().Update(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }
    }
}