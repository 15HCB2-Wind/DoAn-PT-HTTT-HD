/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.DataAccess.Personnel.UpdatePersonnelRequest;
import Models.DataAccess.Personnel.InsertPersonnelRequest;
import Models.DataAccess.Personnel.InsertPersonnelResponse;
import Models.DataAccess.Personnel.UpdatePersonnelResponse;
import Models.Other.ChangePasswordRequest;
import Models.Other.ChangePasswordResponse;
import Models.DataAccess.*;
import Models.*;
import BusinessHandler.NhanVienBUS;
import DAO.NhanVienAdapter;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import pojos.Nhanvien;


/**
 * REST Web Service
 *
 * @author Shin-Desktop
 */
@Path("NhanVien")
public class NhanVienAPIs {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NhanVienAPIs
     */
    public NhanVienAPIs() {
    }
    
    @POST
    @Path("get")
    @Produces("application/json")
    @Consumes("application/json")
    public String get(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            if (NhanVienBUS.getSingleValidate(request, response)){
                try{
                    Nhanvien result = NhanVienAdapter.getSingle(request.Predicates[0]);
                    if (result == null){
                        response.Errors.add("Không tìm thấy nhân viên.");
                        response.IsError = true;
                    }else{
                        response.Data = result;
                    }
                }catch(Exception ex){
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
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
        InsertPersonnelRequest request = gson.fromJson(json, InsertPersonnelRequest.class);
        InsertPersonnelResponse response = new InsertPersonnelResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            if (NhanVienBUS.insertValidate(request, response)){
                try{
                    if (NhanVienAdapter.add(request.Data)){
                        NhanVienBUS.sync(1, request.Data);
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
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("delete")
    @Produces("application/json")
    @Consumes("application/json")
    public String delete(String json) {
        Gson gson = new Gson();
        DeleteRequest request = gson.fromJson(json, DeleteRequest.class);   
        DeleteResponse response = new DeleteResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            if (NhanVienBUS.deleteValidate(request, response)){
                try{
                    int result = NhanVienAdapter.delete(request.Predicates[0]);
                    if (result != 1){
                        response.Errors.add("Xóa nhân viên thất bại.");
                        response.IsError = true;
                    }else{
                        NhanVienBUS.sync(-1, NhanVienAdapter.getSingle(request.Predicates[0]));
                        response.Data = "Xóa nhân viên thành công.";
                    }
                }catch(Exception ex){
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
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
        DeleteRequest request = gson.fromJson(json, DeleteRequest.class);   
        DeleteResponse response = new DeleteResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            if (NhanVienBUS.deleteValidate(request, response)){
                try{
                    int result = NhanVienAdapter.recover(request.Predicates[0]);
                    if (result != 1){
                        response.Errors.add("Khôi phục nhân viên thất bại.");
                        response.IsError = true;
                    }else{
                        NhanVienBUS.sync(1, NhanVienAdapter.getSingle(request.Predicates[0]));
                        response.Data = "Khôi phục nhân viên thành công.";
                    }
                }catch(Exception ex){
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
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
        UpdatePersonnelRequest request = gson.fromJson(json, UpdatePersonnelRequest.class);
        UpdatePersonnelResponse response = new UpdatePersonnelResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            if (BusinessHandler.NhanVienBUS.updateValidate(request, response)){
                try{
                    if (NhanVienAdapter.update(request.Data)){
                        NhanVienBUS.sync(0, request.Data);
                        response.Data = "Cập nhật thành công!";
                    }else{
                        response.Errors.add("Cập nhật thất bại!");
                        response.IsError = true;
                    }
                }catch(Exception ex){
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            }
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("sync")
    @Produces("application/json")
    @Consumes("application/json")
    public String sync(String json) {
        Gson gson = new Gson();
        ChangePasswordRequest request = gson.fromJson(json, ChangePasswordRequest.class);   
        ChangePasswordResponse response = new ChangePasswordResponse();
        try{
            response.IsError = NhanVienAdapter.changePassword(request) != 1;
        }catch(Exception ex){
            response.IsError = true;
        }
        return gson.toJson(response);
    }
}
