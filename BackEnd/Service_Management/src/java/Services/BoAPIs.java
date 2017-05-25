/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.DataAccess.*;
import Models.*;
import BusinessHandler.BoBUS;
import DAO.BoAdapter;
import DAO.ChuongTraiAdapter;
import DAO.NhanVienAdapter;
import Models.DataAccess.Cow.CowRequest;
import Models.DataAccess.Cow.CowResponse;
import com.google.gson.Gson;
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
    
    @POST
    @Path("get")
    @Produces("application/json")
    @Consumes("application/json")
    public String get(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            try{
                Bo result = BoAdapter.getSingle(request.Predicates[0]);
                if (result == null){
                    response.Errors.add("Không tìm thấy kho.");
                    response.IsError = true;
                }else{
                    response.Data = result;
                }
            }catch(Exception ex){
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("getAllOfAgency")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAllOfAgency(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            try{
                List<Bo> result = BoAdapter.getAllOfAgency(NhanVienAdapter.getSingle(token.UserId).getMachinhanh());
                result.forEach((obj) -> {
                    obj.tenchuong = ChuongTraiAdapter.getSingle(obj.getMachuong()).getTenchuong();
                });
                response.Data = result;
            }catch(Exception ex){
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("getAllOfBarn")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAllOfBarn(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            try{
                response.Data = BoAdapter.getAllOfBarn(request.Predicates[0]);
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
    public String add(String json) {
        Gson gson = new Gson();
        CowRequest request = gson.fromJson(json, CowRequest.class);
        CowResponse response = new CowResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            request.Data.setMachuong(request.BarnId);
            if (BoBUS.insertUpdateValidate(request, response)){
                try{
                    if (BoAdapter.add(request.Data)){
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
            try{
                int result = BoAdapter.delete(request.Predicates[0]);
                if (result != 1){
                    response.Errors.add("Xóa thất bại.");
                    response.IsError = true;
                }else{
                    response.Data = "Xóa thành công.";
                }
            }catch(Exception ex){
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
        DeleteRequest request = gson.fromJson(json, DeleteRequest.class);   
        DeleteResponse response = new DeleteResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            try{
                int result = BoAdapter.recover(request.Predicates[0]);
                if (result != 1){
                    response.Errors.add("Khôi phục thất bại.");
                    response.IsError = true;
                }else{
                    response.Data = "Khôi phục thành công.";
                }
            }catch(Exception ex){
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
        CowRequest request = gson.fromJson(json, CowRequest.class);
        CowResponse response = new CowResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            if (BusinessHandler.BoBUS.insertUpdateValidate(request, response)){
                try{
                    if (BoAdapter.update(request.Data)){
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
}
