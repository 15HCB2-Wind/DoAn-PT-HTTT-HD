using DataAccess.Repositories;
using DomainData;
using Service.BusinessHandler;
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
    [RoutePrefix("animalcare")]
    public class ChamSocAPIController : ApiController
    {
        //add cham soc
        [HttpPost]
        [Route("add")]
        public HttpResponseMessage AddChamSoc([FromBody] ChamSocRequest request)
        {
            var response = new ChamSocResponse();
            var tokendata = TokenBUS.tokenData(request,response,1);
            if (!response.IsError)
            {
                ChamSocBUS.AddChamSoc(request, ref response);
                if (response.IsError)
                    return Request.CreateResponse(HttpStatusCode.OK, response);
                else
                {
                    ChamSoc cs = ChamSocRepository.IsExistsChamSoc(request.Data);
                    if (cs != null)
                    {
                        request.Data.MaChamSoc = cs.MaChamSoc;
                        ChamSocBUS.UpdateChamSoc(request, ref response);
                        if (response.IsError)
                            return Request.CreateResponse(HttpStatusCode.OK, response);

                        if (cs.LuongSua != request.Data.LuongSua)
                        {
                            if (!ChamSocBUS.UpdateMilk(request.Token, request.Data.LuongSua - cs.LuongSua))
                            {
                                response.Errors.Add("Lỗi hệ thống");
                                response.IsError = true;
                                return Request.CreateResponse(HttpStatusCode.OK, response);
                            }
                        }

                        if (ChamSocRepository.Update(request.Data) < 0)
                        {
                            response.Errors.Add("Lỗi hệ thống");
                            response.IsError = true;
                        }
                        else
                        {
                            response.Data = "Sửa thành công!";
                        }
                    }
                    else
                    {
                        if (ChamSocBUS.UpdateMilk(request.Token, request.Data.LuongSua))
                        {
                            request.Data.MaChiNhanh = tokendata.AgencyId;
                            if (ChamSocRepository.Insert(request.Data) < 0)
                            {
                                response.Errors.Add("Lỗi hệ thống");
                                response.IsError = true;
                            }
                            else
                            {
                                response.Data = "Thêm thành công!";
                            }
                        }

                        else
                        {
                            response.Errors.Add("Lỗi hệ thống");
                            response.IsError = true;
                        }

                    }

                }
            }

            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //add tinhtrangbo
        [HttpPost]
        [Route("addTinhTrangBo")]
        public HttpResponseMessage AddTinhTrangBo([FromBody] TinhTrangBoRequest request)
        {
            var response = new TinhTrangBoResponse();
            var tokendata = TokenBUS.tokenData(request, response, 1);
            if (response.IsError)
            {
                ChamSocBUS.AddTinhTrangBo(request, ref response);
                if (response.IsError)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, response);
                }
                else
                {
                    ChamSoc cs = ChamSocRepository.AddTinhTrangBo(request.Data);
                    if (cs == null)
                    {
                        response.Errors.Add("Lỗi hệ thống, hãy chăm sóc bò trước khi đo đạc thông số");
                        response.IsError = true;
                    }
                    else
                    {
                        ChamSocBUS.UpdateCowState(request.Token, cs.MaBo, request.TinhTrang);
                        response.Data = cs;
                    }
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        [HttpPost]
        [Route("getTinhTrangBo")]
        public HttpResponseMessage GetTinhTrangBo([FromBody] TinhTrangBoRequest request)
        {
            var response = new TinhTrangBoResponse();
            if (BusinessHandler.TokenBUS.tokenCheck(request, response, 1))
            {
                List<TinhTrangBo> cs = ChamSocRepository.GetTinhTrangBo(request.Data);
                if (cs == null)
                {
                    response.Errors.Add("Lỗi hệ thống");
                    response.IsError = true;
                }
                else
                {
                    response.Data = cs;
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //update cham soc
        [HttpPost]
        [Route("update")]
        public HttpResponseMessage UpdateChamSoc([FromBody] ChamSocRequest request)
        {
            var response = new ChamSocResponse();
            if (BusinessHandler.TokenBUS.tokenCheck(request, response, 1))
            {
                ChamSocBUS.UpdateChamSoc(request, ref response);
                if (response.IsError)
                    return Request.CreateResponse(HttpStatusCode.OK, response);
                else
                {

                    if (ChamSocRepository.Update(request.Data) < 0)
                    {
                        response.Errors.Add("Lỗi hệ thống");
                        response.IsError = true;
                    }
                    else
                    {
                        response.Data = "Sửa thành công!";
                    }
                }
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //GetListChamSocFromPhanCong
        [HttpPost]
        [Route("getAll")]
        public HttpResponseMessage getAll([FromBody] PhanCongRequest request)
        {
            var response = new ChamSocResponse();
            if (BusinessHandler.TokenBUS.tokenCheck(request, response, 1))
            {
                var result = ChamSocRepository.GetListChamSocFromPhanCong(request.Data);
                if (result == null)
                {
                    response.Errors.Add("Lỗi hệ thống");
                    response.IsError = true;
                }
                else
                    response.Data = result;
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }

        //report tinhtrangbo
        [HttpPost]
        [Route("reportTinhTrangBo")]
        public HttpResponseMessage reportTinhTrangBo([FromBody] ReportTinhTrangBoRequest request)
        {
            var response = new ReportTinhTrangBoResponse();
            var tokendata = TokenBUS.tokenData(request, response, 3);
            if (!response.IsError)
            {
                ChamSocBUS.ReportTinhTrangBo(request,ref response);
                if (response.IsError)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, response);
                }
                else
                {
                    response.Data = ChamSocRepository.ReportTinhTrangBo_Day(tokendata.AgencyId,request.NgayBatDau);
                }
                
            }
            return Request.CreateResponse(HttpStatusCode.OK, response);
        }
    }
}
