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
}
