/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.DataAccess.*;
import Models.*;
import BusinessHandler.KhoSuaBUS;
import DAO.KhoSuaAdapter;
import DAO.NhanVienAdapter;
import Models.DataAccess.Warehouse.WarehouseRequest;
import Models.DataAccess.Warehouse.WarehouseResponse;
import Models.DataAccess.Warehouse.WarehouseTransferRequest;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import pojos.Khosua;


/**
 * REST Web Service
 *
 * @author 19101994
 */
@Path("KhoSua")
public class KhoSuaAPIs {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of KhoSuaAPIs
     */
    public KhoSuaAPIs() {
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
                Khosua result = KhoSuaAdapter.getSingle(request.Predicates[0]);
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
    @Path("getAll")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAll(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            try{
                response.Data = KhoSuaAdapter.getAllOfAgency(token.AgencyId);
            }catch(Exception ex){
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("getAllAvailables")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAllAvailables(String json) {
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            try {
                response.Data = KhoSuaAdapter.getAllAvailables(token.AgencyId);;
            } catch (Exception ex) {
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("getAllAvailablesForTransfer")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAllAvailablesForTransfer(String json) {
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            try {
                response.Data = KhoSuaAdapter.getAllAvailablesForTransfer(token.AgencyId);;
            } catch (Exception ex) {
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
        WarehouseRequest request = gson.fromJson(json, WarehouseRequest.class);
        WarehouseResponse response = new WarehouseResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            if (KhoSuaBUS.insertUpdateValidate(request, response)){
                try{
                    request.Data.setMachinhanh(token.AgencyId);
                    if (KhoSuaAdapter.add(request.Data)){
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
                int result = KhoSuaAdapter.delete(request.Predicates[0]);
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
                int result = KhoSuaAdapter.recover(request.Predicates[0]);
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
        WarehouseRequest request = gson.fromJson(json, WarehouseRequest.class);
        WarehouseResponse response = new WarehouseResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            if (BusinessHandler.KhoSuaBUS.insertUpdateValidate(request, response)){
                try{
                    if (KhoSuaAdapter.update(request.Data)){
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
    @Path("transfer")
    @Produces("application/json")
    @Consumes("application/json")
    public String transfer(String json) {
        Gson gson = new Gson();
        WarehouseTransferRequest request = gson.fromJson(json, WarehouseTransferRequest.class);
        WarehouseResponse response = new WarehouseResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 2)){
            if (BusinessHandler.KhoSuaBUS.transferValidate(request, response)){
                try{
                    if (KhoSuaAdapter.transfer(request.From, request.To, request.Value)){
                        response.Data = "Chuyển kho thành công!";
                    }else{
                        response.Errors.add("Chuyển kho thất bại!");
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
