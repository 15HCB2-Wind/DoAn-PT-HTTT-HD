/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BusinessHandler.ChiTietXuatSuaBUS;
import DAO.ChiTietXuatSuaAdapter;
import Models.DataAccess.DeleteResponse;
import Models.DataAccess.DetailSaleMilk.InsertDetailSaleMilkRequest;
import Models.DataAccess.DetailSaleMilk.InsertDetailSaleMilkResponse;
import Models.DataAccess.DetailSaleMilk.SelectDetailSaleMilkRequest;
import Models.DataAccess.SelectRequest;
import Models.DataAccess.SelectResponse;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Shin-Desktop
 */
@Path("ChiTietXuatSua")
public class ChiTietXuatSuaAPIs {  
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NhanVienAPIs
     */
    public ChiTietXuatSuaAPIs() {
    }
    
    //xem danh sách phiếu xuất sữa bò
    @POST
    @Path("getAll")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAll(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){// kiem tra quyen truy cap 
                 //NhanVienBUS.getSingleValidate(request, response)
//            if (PhieuNhapBoBUS.getSingleValidate(request, response)){
                try{
                    response.Data = ChiTietXuatSuaAdapter.getAll();
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
    @Path("add")
    @Produces("application/json")
    @Consumes("application/json")
    public String add(String json){
        Gson gson = new Gson();
        InsertDetailSaleMilkRequest request = gson.fromJson(json, InsertDetailSaleMilkRequest.class);
        InsertDetailSaleMilkResponse response = new InsertDetailSaleMilkResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            if (ChiTietXuatSuaBUS.insertValidate(request, response)){
                try{
                    if (ChiTietXuatSuaAdapter.add(request.Data)){
                        response.Data = "Thêm thành công.";
                        
                        //lấy mã vừa thêm
                        String t;
                        t = request.Data.getId().getMachungtu();
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
        Gson gson = new Gson();
        SelectDetailSaleMilkRequest request = gson.fromJson(json, SelectDetailSaleMilkRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){// kiem tra quyen truy cap 
                 //NhanVienBUS.getSingleValidate(request, response)
//            if (PhieuNhapBoBUS.getSingleValidate(request, response)){
                try{
                    response.Data = ChiTietXuatSuaAdapter.getSingle(request.machungtu);
                }catch(Exception ex){
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
//            }
        }
        return gson.toJson(response);
    }
    
//    //xóa phiếu
//    @POST
//    @Path("delete")
//    @Produces("application/json")
//    @Consumes("application/json")
//    public String delete(String json) {
//        Gson gson = new Gson();
//        SelectDetailSaleMilkRequest request = gson.fromJson(json, SelectDetailSaleMilkRequest.class);   
//        DeleteResponse response = new DeleteResponse();
//        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
//            try {
//                int result = ChiTietXuatSuaAdapter.delete(request.machungtu);
//                if (result != 1) {
//                    response.Errors.add("Xóa phiếu thất bại.");
//                    response.IsError = true;
//                }else{
//                    response.Data = "Xóa thành công.";
//                }
//            } catch (Exception ex) {
//                response.Errors.add("Lỗi hệ thống.");
//                response.IsError = true;
//            }
//        }
//        return gson.toJson(response);
//    }
    
//    //recover
//    @POST
//    @Path("recover")
//    @Produces("application/json")
//    @Consumes("application/json")
//    public String recover(String json) {
//        Gson gson = new Gson();
//        SelectSaleMilkRequest request = gson.fromJson(json, SelectSaleMilkRequest.class);   
//        DeleteResponse response = new DeleteResponse();
//        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
//            try {
//                int result = PhieuXuatAdapter.recover(request.machungtu);
//                if (result != 1) {
//                    response.Errors.add("phục hồi phiếu thất bại.");
//                    response.IsError = true;
//                }else{
//                    response.Data = "phục hồi thành công.";
//                }
//            } catch (Exception ex) {
//                response.Errors.add("Lỗi hệ thống.");
//                response.IsError = true;
//            }
//        }
//        return gson.toJson(response);
//    }
    
    //cập nhật phiếu
    @POST
    @Path("update")
    @Produces("application/json")
    @Consumes("application/json")
    public String update(String json) {
        Gson gson = new Gson();
        InsertDetailSaleMilkRequest request = gson.fromJson(json, InsertDetailSaleMilkRequest.class);
        InsertDetailSaleMilkResponse response = new InsertDetailSaleMilkResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)) {
                try {
                    if (ChiTietXuatSuaAdapter.update(request.Data)) {
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
