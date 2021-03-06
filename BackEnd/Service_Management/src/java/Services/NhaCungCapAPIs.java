/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BusinessHandler.NhaCungCapBUS;
import Models.DataAccess.*;
import DAO.NhaCungCapAdapter;
import Models.DataAccess.Provider.InsertProviderRequest;
import Models.DataAccess.Provider.InsertProviderResponse;
import Models.DataAccess.Provider.UpdateProviderRequest;
import Models.DataAccess.Provider.UpdateProviderResponse;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;



/**
 * REST Web Service
 *
 * @author Shin-Desktop
 */
@Path("NhaCungCap")
public class NhaCungCapAPIs {

    @Context
    private UriInfo context;


    public NhaCungCapAPIs() {
    }
    
    
    @POST
    @Path("getAll")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAll(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            try{
                response.Data = NhaCungCapAdapter.getAll();
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
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            try{
                response.Data = NhaCungCapAdapter.getSingle(request.Predicates[0]);
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
        InsertProviderRequest request = gson.fromJson(json, InsertProviderRequest.class);
        InsertProviderResponse response = new InsertProviderResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            if (NhaCungCapBUS.insertValidate(request, response)){
                try{
                    if (NhaCungCapAdapter.add(request.AreaId, request.Data)){
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
    @Path("update")
    @Produces("application/json")
    @Consumes("application/json")
    public String update(String json) {
        Gson gson = new Gson();
        UpdateProviderRequest request = gson.fromJson(json, UpdateProviderRequest.class);
        UpdateProviderResponse response = new UpdateProviderResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            if (NhaCungCapBUS.updateValidate(request, response)){
                try{
                    if (NhaCungCapAdapter.update(request.Data)){
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
