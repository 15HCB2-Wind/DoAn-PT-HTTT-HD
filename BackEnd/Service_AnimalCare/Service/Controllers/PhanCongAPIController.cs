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
        public HttpResponseMessage AddPhanCong([FromBody] PhanCongRequest request)
        {
            var response = new PhanCongResponse();
            if (request.FromDate > request.ToDate)
                response.Errors.Add("Ngày bắt đầu không được lớn hơn ngày kết thúc làm việc");
            if (string.IsNullOrEmpty(request.IdChuong))
                response.Errors.Add("Chuồng không tồn tại, hãy chọn chuồng khác");

            List<int> listdays = new List<int>();
            try
            {
                string[] temp = request.Days.Split(',');
                foreach (string item in temp)
                {
                    if (item == "CN")
                        listdays.Add(8);
                    else
                        listdays.Add(int.Parse(item));
                }
            }
            catch (Exception)
            {
                response.Errors.Add("Định dạng ngày làm việc ko đúng, ví dụ: 2,3,4,5,6,7,CN");
                response.IsError = true;
            }

            if (response.IsError)
                return Request.CreateResponse(HttpStatusCode.OK, response);
            else
            {
                try
                {
                    BusinessHandler.PhanCongBUS.AddPhanCong(request, ref response);
                }
                catch (Exception)
                {
                    response.Errors.Add("Lỗi hệ thống");
                    response.IsError = true;
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //update assignment
        [HttpPost]
        [Route("update")]
        public HttpResponseMessage UpdatePhanCong([FromBody] PhanCongRequest request)
        {
            var response = new PhanCongResponse();
            if (request.FromDate > request.ToDate)
                response.Errors.Add("Ngày bắt đầu không được lớn hơn ngày kết thúc làm việc");
            if (string.IsNullOrEmpty(request.IdChuong))
                response.Errors.Add("Chuồng không tồn tại, hãy chọn chuồng khác");

            List<int> listdays = new List<int>();
            try
            {
                string[] temp = request.Days.Split(',');
                foreach (string item in temp)
                {
                    if (item == "CN")
                        listdays.Add(8);
                    else
                        listdays.Add(int.Parse(item));
                }
            }
            catch (Exception)
            {
                response.Errors.Add("Định dạng ngày làm việc ko đúng, ví dụ: 2,3,4,5,6,7,CN");
                response.IsError = true;
            }

            if (response.IsError)
                return Request.CreateResponse(HttpStatusCode.OK, response);
            else
            {
                try
                {
                    BusinessHandler.PhanCongBUS.UpdatePhanCong(request, ref response);
                }
                catch (Exception)
                {
                    response.Errors.Add("Lỗi hệ thống");
                    response.IsError = true;
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //Lấy danh sách phân công của 1 chi nhánh
        [HttpPost]
        [Route("getlistphancongofchuongtrai")]
        public HttpResponseMessage GetAllFromChuongTrai([FromBody] PhanCongRequest request)
        {
            var response = new PhanCongResponse();
            try
            {
                BusinessHandler.PhanCongBUS.GetAllFromChuongTrai(request, ref response);
            }
            catch (Exception)
            {
                response.Errors.Add("Lỗi hệ thống");
                response.IsError = true;
            }

            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //Lấy danh sách phân công của 1 nhân viên
        [HttpPost]
        [Route("getlistphancongofnhanvien")]
        public HttpResponseMessage GetAllFromNhanVien([FromBody] PhanCongRequest request)
        {
            var response = new PhanCongResponse();
            try
            {
                BusinessHandler.PhanCongBUS.GetAllFromNhanVien(request, ref response);
            }
            catch (Exception)
            {
                response.Errors.Add("Lỗi hệ thống");
                response.IsError = true;
            }
            
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //Lấy 1 Phân công khi truyền vào mã phân công
        [HttpPost]
        [Route("getsinglephancong")]
        public HttpResponseMessage GetOneFromPhanCong([FromBody] PhanCongRequest request)
        {
            var response = new PhanCongResponse();
            try
            {
                BusinessHandler.PhanCongBUS.GetOneFromPhanCong(request, ref response);
            }
            catch (Exception)
            {
                response.Errors.Add("Lỗi hệ thống");
                response.IsError = true;
            }

            return Request.CreateResponse(HttpStatusCode.OK, response);
        }
    }
}
