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
        public HttpResponseMessage Login([FromBody] PhanCongRequest request)
        {
            var response = new PhanCongResponse();
            BusinessHandler.PhanCongBUS.AddPhanCong(request, ref response);
            if (response.IsError)
            {
                response.Errors.Add("Lỗi hệ thống.");
                response.IsError = true;
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }
    }
}
