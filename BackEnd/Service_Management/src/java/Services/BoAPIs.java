/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.DataAccess.Cow.UpdateCowRequest;
import Models.DataAccess.Cow.InsertCowRequest;
import Models.DataAccess.Cow.InsertCowResponse;
import Models.DataAccess.Cow.UpdateCowResponse;
import Models.Other.ChangePasswordRequest;
import Models.Other.ChangePasswordResponse;
import Models.DataAccess.*;
import Models.*;
import BusinessHandler.BoBUS;
import DAO.BoAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import pojos.Bo;


/**
 * REST Web Service
 *
 * @author 19101994
 */
@Path("Bo")
public class BoAPIs {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BoAPIs
     */
    public BoAPIs() {
    }
    
//    @POST
//    @Path("get")
//    @Produces("application/json")
//    @Consumes("application/json")
//    public String get(String json){
//        Gson gson = new Gson();
//        SelectRequest request = gson.fromJson(json, SelectRequest.class);
//        SelectResponse response = new SelectResponse();
//        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
//            if (BoBUS.getSingleValidate(request, response)){
//                try{
//                    Bo result = BoAdapter.getSingle(request.Predicates[0]);
//                    if (result == null){
//                        response.Errors.add("Không tìm thấy nhân viên.");
//                        response.IsError = true;
//                    }else{
//                        response.Data = result;
//                    }
//                }catch(Exception ex){
//                    response.Errors.add("Lỗi hệ thống.");
//                    response.IsError = true;
//                }
//            }
//        }
//        return gson.toJson(response);
//    }
//    
//    @POST
//    @Path("getAll")
//    @Produces("application/json")
//    @Consumes("application/json")
//    public String getAll(String json){
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//        SelectRequest request = gson.fromJson(json, SelectRequest.class);
//        SelectResponse response = new SelectResponse();
//        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
//            if (BoBUS.getSingleValidate(request, response)){
//                try{
//                    List<Bo> result = BoAdapter.getAll(request.Predicates[0]);
//                    response.Data = result;
//                }catch(Exception ex){
//                    response.Errors.add("Lỗi hệ thống.");
//                    response.IsError = true;
//                }
//            }
//        }
//        return gson.toJson(response);
//    }
//    
//    @POST
//    @Path("add")
//    @Produces("application/json")
//    @Consumes("application/json")
//    public String add(String json) {
//        Gson gson = new Gson();
//        InsertCowRequest request = gson.fromJson(json, InsertCowRequest.class);
//        InsertCowResponse response = new InsertCowResponse();
//        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
//            if (BoBUS.insertValidate(request, response)){
//                try{
//                    if (BoAdapter.add(request.Data)){
//                        BoBUS.sync(1, request.Data);
//                        response.Data = "Thêm thành công.";
//                    }else{
//                        response.Errors.add("Thêm thất bại.");
//                        response.IsError = true;
//                    }
//                }catch(Exception ex){
//                    response.Errors.add("Lỗi hệ thống.");
//                    response.IsError = true;
//                }
//            }
//        }
//        return gson.toJson(response);
//    }
//    
//    @POST
//    @Path("delete")
//    @Produces("application/json")
//    @Consumes("application/json")
//    public String delete(String json) {
//        Gson gson = new Gson();
//        DeleteRequest request = gson.fromJson(json, DeleteRequest.class);   
//        DeleteResponse response = new DeleteResponse();
//        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
//            if (BoBUS.deleteValidate(request, response)){
//                try{
//                    int result = BoAdapter.delete(request.Predicates[0]);
//                    if (result != 1){
//                        response.Errors.add("Xóa nhân viên thất bại.");
//                        response.IsError = true;
//                    }else{
//                        BoBUS.sync(-1, BoAdapter.getSingle(request.Predicates[0]));
//                        response.Data = "Xóa nhân viên thành công.";
//                    }
//                }catch(Exception ex){
//                    response.Errors.add("Lỗi hệ thống.");
//                    response.IsError = true;
//                }
//            }
//        }
//        return gson.toJson(response);
//    }
//    
//    @POST
//    @Path("recover")
//    @Produces("application/json")
//    @Consumes("application/json")
//    public String recover(String json) {
//        Gson gson = new Gson();
//        DeleteRequest request = gson.fromJson(json, DeleteRequest.class);   
//        DeleteResponse response = new DeleteResponse();
//        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
//            if (BoBUS.deleteValidate(request, response)){
//                try{
//                    int result = BoAdapter.recover(request.Predicates[0]);
//                    if (result != 1){
//                        response.Errors.add("Khôi phục nhân viên thất bại.");
//                        response.IsError = true;
//                    }else{
//                        BoBUS.sync(1, BoAdapter.getSingle(request.Predicates[0]));
//                        response.Data = "Khôi phục nhân viên thành công.";
//                    }
//                }catch(Exception ex){
//                    response.Errors.add("Lỗi hệ thống.");
//                    response.IsError = true;
//                }
//            }
//        }
//        return gson.toJson(response);
//    }
//    
//    @POST
//    @Path("update")
//    @Produces("application/json")
//    @Consumes("application/json")
//    public String update(String json) {
//        Gson gson = new Gson();
//        UpdateCowRequest request = gson.fromJson(json, UpdateCowRequest.class);
//        UpdateCowResponse response = new UpdateCowResponse();
//        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
//            if (BusinessHandler.BoBUS.updateValidate(request, response)){
//                try{
//                    if (BoAdapter.update(request.Data)){
//                        response.Data = "Cập nhật thành công!";
//                    }else{
//                        response.Errors.add("Cập nhật thất bại!");
//                        response.IsError = true;
//                    }
//                }catch(Exception ex){
//                    response.Errors.add("Lỗi hệ thống.");
//                    response.IsError = true;
//                }
//            }
//        }
//        return gson.toJson(response);
//    }
}
