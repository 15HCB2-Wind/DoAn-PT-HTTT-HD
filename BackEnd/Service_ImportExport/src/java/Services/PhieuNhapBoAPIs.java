/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import BusinessHandler.PhieuNhapBoBUS;
import DAO.PhieuNhapBoAdapter;
import Models.DataAccess.BillImportCow.InsertBillImportCowReponse;
import Models.DataAccess.BillImportCow.InsertBillImportCowRequest;
import Models.DataAccess.BillImportCow.SelectBillImportCowRequest;
import Models.DataAccess.DeleteResponse;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import pojos.Phieunhapbo;
import Models.DataAccess.SelectRequest;
import Models.DataAccess.SelectResponse;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Response;

/**
 *
 * @author Shin-Desktop
 */
@Path("PhieuNhapBo")
public class PhieuNhapBoAPIs {
    @Context
    private UriInfo context;
    
    public PhieuNhapBoAPIs(){
        
    }
    
    //xem danh sách phiếu nhập bò
    @POST
    @Path("getAll")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAll(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){// kiem tra quyen truy cap 
                 //NhanVienBUS.getSingleValidate(request, response)
//            if (PhieuNhapBoBUS.getSingleValidate(request, response)){
                try{
                    response.Data = PhieuNhapBoAdapter.getAll();
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
    
    //thêm phiếu nhập bò
    @POST
    @Path("add")
    @Produces("application/json")
    @Consumes("application/json")
    public String add(String json){
        Gson gson = new Gson();
        InsertBillImportCowRequest request = gson.fromJson(json, InsertBillImportCowRequest.class);
        InsertBillImportCowReponse response = new InsertBillImportCowReponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            if (PhieuNhapBoBUS.insertValidate(request, response)){
                try{
                    if (PhieuNhapBoAdapter.add(request.Data)){
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
    
    //xem danh sách phiếu nhập bò
    @POST
    @Path("getSingle")
    @Produces("application/json")
    @Consumes("application/json")
    public String getSingle(String json){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        SelectBillImportCowRequest request = gson.fromJson(json, SelectBillImportCowRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){// kiem tra quyen truy cap 
                 //NhanVienBUS.getSingleValidate(request, response)
//            if (PhieuNhapBoBUS.getSingleValidate(request, response)){
                try{
                    response.Data = PhieuNhapBoAdapter.getSingle(request.machungtu);
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
        SelectBillImportCowRequest request = gson.fromJson(json, SelectBillImportCowRequest.class);   
        DeleteResponse response = new DeleteResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            try {
                int result = PhieuNhapBoAdapter.delete(request.machungtu);
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
        SelectBillImportCowRequest request = gson.fromJson(json, SelectBillImportCowRequest.class);   
        DeleteResponse response = new DeleteResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            try {
                int result = PhieuNhapBoAdapter.recover(request.machungtu);
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
        InsertBillImportCowRequest request = gson.fromJson(json, InsertBillImportCowRequest.class);
        InsertBillImportCowReponse response = new InsertBillImportCowReponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)) {
                try {
                    if (PhieuNhapBoAdapter.update(request.Data)) {
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
