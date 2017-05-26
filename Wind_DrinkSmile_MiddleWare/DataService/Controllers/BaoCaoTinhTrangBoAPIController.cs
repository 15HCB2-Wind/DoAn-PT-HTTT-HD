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
    [RoutePrefix("baocaotinhtrangbo")]
    public class BaoCaoTinhTrangBoAPIController : ApiController
    {
        //lấy tất cả 
        [HttpGet]
        [Route("getall")]
        public HttpResponseMessage GetAll()
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, BaoCaoTinhTrangBoRepository.GetInstance().GetAll());
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        //lấy theo mã Báo cáo
        [HttpGet]
        [Route("getfrom/{maBaoCao}")]
        public HttpResponseMessage GetAll(string maBaoCao)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, BaoCaoTinhTrangBoRepository.GetInstance().GetFrom(maBaoCao));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        //thêm báo cáo tình trạng bò
        [HttpPost]
        [Route("insert")]
        public HttpResponseMessage insert([FromBody] BaoCaoTinhTrangBo obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK,BaoCaoTinhTrangBoRepository.GetInstance().Insert(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        //chỉnh sửa báo cáo tình trạng bò
        [HttpPost]
        [Route("update")]
        public HttpResponseMessage update([FromBody] BaoCaoTinhTrangBo obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, BaoCaoTinhTrangBoRepository.GetInstance().Update(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

    }
}
