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
    [RoutePrefix("baocaoxuat")]
    public class BaoCaoXuatAPIController : ApiController
    {
        //lấy tất cả 
        [HttpGet]
        [Route("getall")]
        public HttpResponseMessage GetAll()
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, BaoCaoXuatRepository.GetInstance().GetAll());
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
                return Request.CreateResponse(HttpStatusCode.OK, BaoCaoXuatRepository.GetInstance().GetFrom(maBaoCao));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        //thêm báo cáo xuất
        [HttpPost]
        [Route("insert")]
        public HttpResponseMessage insert([FromBody] BaoCaoXuat obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, BaoCaoXuatRepository.GetInstance().Insert(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }

        //chỉnh sửa báo cáo xuất
        [HttpPost]
        [Route("update")]
        public HttpResponseMessage update([FromBody] BaoCaoXuat obj)
        {
            try
            {
                return Request.CreateResponse(HttpStatusCode.OK, BaoCaoXuatRepository.GetInstance().Update(obj));
            }
            catch (Exception ex)
            {
                return Request.CreateErrorResponse(HttpStatusCode.InternalServerError, ex);
            }
        }
    }
}
