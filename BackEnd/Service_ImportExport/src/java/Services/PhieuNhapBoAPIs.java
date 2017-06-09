/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import BusinessHandler.PhieuNhapBoBUS;
import DAO.PhieuNhapBoAdapter;
import Models.DataAccess.Import.InsertImportReponse;
import Models.DataAccess.Import.InsertImportRequest;
import Models.DataAccess.Import.SelectImportRequest;
import Models.DataAccess.DeleteResponse;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import Models.DataAccess.SelectRequest;
import Models.DataAccess.SelectResponse;
import Models.TokenData;
import com.google.gson.GsonBuilder;

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
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            try{
                response.Data = PhieuNhapBoAdapter.getAll();
            }catch(Exception ex){
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
    public String add(String json){
        Gson gson = new Gson();
        InsertImportRequest request = gson.fromJson(json, InsertImportRequest.class);
        InsertImportReponse response = new InsertImportReponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 3);
        if (token != null){
            if (PhieuNhapBoBUS.insertValidate(request, response)){
                try{
                    request.Data.setManv(token.UserId);
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
    
    @POST
    @Path("getSingle")
    @Produces("application/json")
    @Consumes("application/json")
    public String getSingle(String json){
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        SelectImportRequest request = gson.fromJson(json, SelectImportRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            try{
                response.Data = PhieuNhapBoAdapter.getSingle(request.machungtu);
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
        SelectImportRequest request = gson.fromJson(json, SelectImportRequest.class);   
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
    
    @POST
    @Path("recover")
    @Produces("application/json")
    @Consumes("application/json")
    public String recover(String json) {
        Gson gson = new Gson();
        SelectImportRequest request = gson.fromJson(json, SelectImportRequest.class);   
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
    
    @POST
    @Path("update")
    @Produces("application/json")
    @Consumes("application/json")
    public String update(String json) {
        Gson gson = new Gson();
        InsertImportRequest request = gson.fromJson(json, InsertImportRequest.class);
        InsertImportReponse response = new InsertImportReponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)) {
            try {
                if (PhieuNhapBoAdapter.update(request.Data)) {
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
