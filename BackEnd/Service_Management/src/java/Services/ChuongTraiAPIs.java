/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BusinessHandler.ChuongTraiBUS;
import DAO.ChuongTraiAdapter;
import DAO.NhanVienAdapter;
import Models.DataAccess.Barn.BarnRequest;
import Models.DataAccess.Barn.BarnResponse;
import Models.DataAccess.Barn.SelectBarnRequest;
import Models.DataAccess.DeleteResponse;
import Models.DataAccess.SelectResponse;
import Models.TokenData;
import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import pojos.Chuongtrai;

/**
 *
 * @author Tu
 */
@Path("ChuongTrai")
public class ChuongTraiAPIs {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NhanVienAPIs
     */
    public ChuongTraiAPIs() {
    }

    @POST
    @Path("get")
    @Produces("application/json")
    @Consumes("application/json")
    public String get(String json) {
        Gson gson = new Gson();
        SelectBarnRequest request = gson.fromJson(json, SelectBarnRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)) {
            try {
                Chuongtrai result = ChuongTraiAdapter.getSingle(request.MaCT);
                if (result == null) {
                    response.Errors.add("Không có kết quả.");
                    response.IsError = true;
                } else {
                    response.Data = result;
                }
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }

    @POST
    @Path("getAll")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAll(String json) {
        Gson gson = new Gson();
        SelectBarnRequest request = gson.fromJson(json, SelectBarnRequest.class);
        SelectResponse response = new SelectResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            try {
                response.Data = ChuongTraiAdapter.getAll(token.AgencyId);;
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("getAllBy1")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAlLBy1(String json) {
        Gson gson = new Gson();
        SelectBarnRequest request = gson.fromJson(json, SelectBarnRequest.class);
        SelectResponse response = new SelectResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 1);
        if (token != null){
            try {
                response.Data = ChuongTraiAdapter.getAll(token.AgencyId);;
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("getAllAvailables")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAllAvailables(String json) {
        Gson gson = new Gson();
        SelectBarnRequest request = gson.fromJson(json, SelectBarnRequest.class);
        SelectResponse response = new SelectResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            try {
                response.Data = ChuongTraiAdapter.getAllAvailables(token.AgencyId);;
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }

    @POST
    @Path("add")
    @Produces("application/json")
    @Consumes("application/json")
    public String add(String json) {
        Gson gson = new Gson();
        BarnRequest request = gson.fromJson(json, BarnRequest.class);
        BarnResponse response = new BarnResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            if (ChuongTraiBUS.validateInformation(request, response)) {
                request.Data.setMachinhanh(token.AgencyId);
                try {
                    if (ChuongTraiAdapter.add(request.Data)) {
                        response.Data = "Thêm thành công.";
                    } else {
                        response.Errors.add("Thêm thất bại.");
                        response.IsError = true;
                    }
                } catch (Exception ex) {
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            }
        }
        return gson.toJson(response);
    }

    @POST
    @Path("delete")
    @Produces("application/json")
    @Consumes("application/json")
    public String delete(String json) {
        Gson gson = new Gson();
        SelectBarnRequest request = gson.fromJson(json, SelectBarnRequest.class);
        DeleteResponse response = new DeleteResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)) {
            try {
                int result = ChuongTraiAdapter.delete(request.MaCT);
                if (result != 1) {
                    response.Errors.add("Xóa chuồng trại thất bại.");
                    response.IsError = true;
                } else {
                    response.Data = "Xóa chuồng trại thành công.";
                }
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }

    @POST
    @Path("recover")
    @Produces("application/json")
    @Consumes("application/json")
    public String recover(String json) {
        Gson gson = new Gson();
        SelectBarnRequest request = gson.fromJson(json, SelectBarnRequest.class);
        DeleteResponse response = new DeleteResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)) {
            try {
                int result = ChuongTraiAdapter.recover(request.MaCT);
                if (result != 1) {
                    response.Errors.add("Khôi phục chuồng trại thất bại.");
                    response.IsError = true;
                } else {
                    response.Data = "Khôi phục chuồng trại thành công.";
                }
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }

    @POST
    @Path("update")
    @Produces("application/json")
    @Consumes("application/json")
    public String update(String json) {
        Gson gson = new Gson();
        BarnRequest request = gson.fromJson(json, BarnRequest.class);
        BarnResponse response = new BarnResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)) {
            if (BusinessHandler.ChuongTraiBUS.validateInformation(request, response)) {
                try {
                    if (ChuongTraiAdapter.update(request.Data)) {
                        response.Data = "Cập nhật thành công!";
                    } else {
                        response.Errors.add("Cập nhật thất bại!");
                        response.IsError = true;
                    }
                } catch (Exception ex) {
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            }
        }
        return gson.toJson(response);
    }
}
