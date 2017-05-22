/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BusinessHandler.ChuongTraiBUS;
import DAO.ChuongTraiAdapter;
import Models.DataAccess.Barn.BarnRequest;
import Models.DataAccess.Barn.BarnResponse;
import Models.DataAccess.Barn.SelectBarnRequest;
import Models.DataAccess.DeleteResponse;
import Models.DataAccess.SelectResponse;
import com.google.gson.Gson;
import java.util.List;
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
    public Response get(String json) {
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
        return response.json();
    }

    @POST
    @Path("getAll")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getAll(String json) {
        Gson gson = new Gson();
        SelectBarnRequest request = gson.fromJson(json, SelectBarnRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)) {
            try {
                List<Chuongtrai> result = ChuongTraiAdapter.getAll(request.MaCN);
                response.Data = result;
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return response.json();
    }

    @POST
    @Path("add")
    @Produces("application/json")
    @Consumes("application/json")
    public Response add(String json) {
        Gson gson = new Gson();
        BarnRequest request = gson.fromJson(json, BarnRequest.class);
        BarnResponse response = new BarnResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)) {
            if (ChuongTraiBUS.validateInformation(request, response)) {
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
        return response.json();
    }

    @POST
    @Path("delete")
    @Produces("application/json")
    @Consumes("application/json")
    public Response delete(String json) {
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
        return response.json();
    }

    @POST
    @Path("recover")
    @Produces("application/json")
    @Consumes("application/json")
    public Response recover(String json) {
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
        return response.json();
    }

    @POST
    @Path("update")
    @Produces("application/json")
    @Consumes("application/json")
    public Response update(String json) {
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
        return response.json();
    }
}
