/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BusinessHandler.PhieuXuatBUS;
import DAO.PhieuXuatAdapter;
import Models.DataAccess.DeleteResponse;
import Models.DataAccess.Export.InsertExportRequest;
import Models.DataAccess.Export.InsertExportResponse;
import Models.DataAccess.Export.SelectExportRequest;
import Models.DataAccess.SelectRequest;
import Models.DataAccess.SelectResponse;
import Models.TokenData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Shin-Desktop
 */
@Path("PhieuXuat")
public class PhieuXuatAPIs {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NhanVienAPIs
     */
    public PhieuXuatAPIs() {
    }
    
    @POST
    @Path("getAll")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAll(String json){
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            try{
                response.Data = PhieuXuatAdapter.getAll(token.AgencyId);
            }catch(Exception ex){
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("addMilk")
    @Produces("application/json")
    @Consumes("application/json")
    public String addMilk(String json){
        Gson gson = new Gson();
        InsertExportRequest request = gson.fromJson(json, InsertExportRequest.class);
        InsertExportResponse response = new InsertExportResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            try{
                request.Data.setManv(token.UserId);
                request.Data.setMacn(token.AgencyId);
                if (PhieuXuatAdapter.add(request)){
                    response.Data = "Thêm thành công.";
                }else{
                    response.Errors.add("Thêm thất bại.");
                    response.IsError = true;
                }
            }catch(Exception ex){
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("getSingle")
    @Produces("application/json")
    @Consumes("application/json")
    public String getSingle(String json){
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        SelectExportRequest request = gson.fromJson(json, SelectExportRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            try{
                response.Data = PhieuXuatAdapter.getSingle(request.machungtu);
            }catch(Exception ex){
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
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
        SelectExportRequest request = gson.fromJson(json, SelectExportRequest.class);   
        DeleteResponse response = new DeleteResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            try {
                int result = PhieuXuatAdapter.delete(request.machungtu);
                if (result != 1) {
                    response.Errors.add("Xóa phiếu thất bại.");
                    response.IsError = true;
                }else{
                    response.Data = "Xóa thành công.";
                }
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("exportNow")
    @Produces("application/json")
    @Consumes("application/json")
    public String exportNow(String json) {
        Gson gson = new Gson();
        SelectExportRequest request = gson.fromJson(json, SelectExportRequest.class);   
        DeleteResponse response = new DeleteResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            try {
                int result = PhieuXuatAdapter.exportNow(request, response);
                if (result != 1) {
                    response.Errors.add("Ghi nhận thất bại.");
                    response.IsError = true;
                }else{
                    response.Data = "Ghi nhận thành công.";
                }
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
}
