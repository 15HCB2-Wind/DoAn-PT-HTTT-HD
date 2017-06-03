/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BusinessHandler.PhieuXuatBUS;
import DAO.PhieuXuatAdapter;
import Models.DataAccess.DeleteResponse;
import Models.DataAccess.SaleMilk.InsertSaleMilkRequest;
import Models.DataAccess.SaleMilk.InsertSaleMilkResponse;
import Models.DataAccess.SaleMilk.SelectSaleMilkRequest;
import Models.DataAccess.SelectRequest;
import Models.DataAccess.SelectResponse;
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

    @GET
    @Path("getAll")
    @Produces("application/json")
    public String getAll(){
        return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(PhieuXuatAdapter.getAll());
    }
    
    //xem danh sách phiếu nhập bò
    @POST
    @Path("getAllMilk")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAllMilk(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){// kiem tra quyen truy cap 
                 //NhanVienBUS.getSingleValidate(request, response)
//            if (PhieuNhapBoBUS.getSingleValidate(request, response)){
                try{
                    response.Data = PhieuXuatAdapter.getAll();
                }catch(Exception ex){
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
//            }
        }
        return gson.toJson(response);
        
//        try{
//            response.Data = PhieuNhapBoAdapter.getAll();
//        }catch(Exception ex){
//            response.Errors.add("Lỗi hệ thống.");
//            response.IsError = true;
//        }
//        return gson.toJson(response);
    }
    
    //thêm phiếu xuất sữa
    @POST
    @Path("addMilk")
    @Produces("application/json")
    @Consumes("application/json")
    public String addMilk(String json){
        Gson gson = new Gson();
        InsertSaleMilkRequest request = gson.fromJson(json, InsertSaleMilkRequest.class);
        InsertSaleMilkResponse response = new InsertSaleMilkResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            if (PhieuXuatBUS.insertValidate(request, response)){
                try{
                    if (PhieuXuatAdapter.add(request.Data)){
                        response.Data = "Thêm thành công.";
                        
                        //lấy mã vừa thêm
                        String t;
                        t = request.Data.getMachungtu();
                        System.out.print(t);
                        
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
    
    //xem danh sách phiếu nhập bò
    @POST
    @Path("getSingle")
    @Produces("application/json")
    @Consumes("application/json")
    public String getSingle(String json){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        SelectSaleMilkRequest request = gson.fromJson(json, SelectSaleMilkRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){// kiem tra quyen truy cap 
                 //NhanVienBUS.getSingleValidate(request, response)
//            if (PhieuNhapBoBUS.getSingleValidate(request, response)){
                try{
                    response.Data = PhieuXuatAdapter.getSingle(request.machungtu);
                }catch(Exception ex){
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
//            }
        }
        return gson.toJson(response);
    }
    
    //xóa phiếu
    @POST
    @Path("delete")
    @Produces("application/json")
    @Consumes("application/json")
    public String delete(String json) {
        Gson gson = new Gson();
        SelectSaleMilkRequest request = gson.fromJson(json, SelectSaleMilkRequest.class);   
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
    
    //recover
    @POST
    @Path("recover")
    @Produces("application/json")
    @Consumes("application/json")
    public String recover(String json) {
        Gson gson = new Gson();
        SelectSaleMilkRequest request = gson.fromJson(json, SelectSaleMilkRequest.class);   
        DeleteResponse response = new DeleteResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            try {
                int result = PhieuXuatAdapter.recover(request.machungtu);
                if (result != 1) {
                    response.Errors.add("phục hồi phiếu thất bại.");
                    response.IsError = true;
                }else{
                    response.Data = "phục hồi thành công.";
                }
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
    
    //cập nhật phiếu
    @POST
    @Path("update")
    @Produces("application/json")
    @Consumes("application/json")
    public String update(String json) {
        Gson gson = new Gson();
        InsertSaleMilkRequest request = gson.fromJson(json, InsertSaleMilkRequest.class);
        InsertSaleMilkResponse response = new InsertSaleMilkResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)) {
                try {
                    if (PhieuXuatAdapter.update(request.Data)) {
                        //NhanVienBUS.sync(0, request.Data);
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
        return gson.toJson(response);
    }
}
