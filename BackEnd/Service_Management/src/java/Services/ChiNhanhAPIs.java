/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BusinessHandler.ChiNhanhBUS;
import DAO.ChiNhanhAdapter;
import Models.DataAccess.Agency.AgencyRequest;
import Models.DataAccess.Agency.AgencyResponse;
import Models.DataAccess.Agency.DeleteAgencyRequest;
import Models.DataAccess.Agency.DeleteAgencyResponse;
import Models.DataAccess.SelectRequest;
import Models.DataAccess.SelectResponse;
import Models.TokenData;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Ut Hieu
 */
@Path("ChiNhanh")
public class ChiNhanhAPIs {
     @Context
    private UriInfo context;
     
    public ChiNhanhAPIs() {
    }
    
    @POST
    @Path("getAll")
    @Produces("application/json")
    @Consumes("application/json")
    public String get(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            //if (true){
                try{
                    response.Data = ChiNhanhAdapter.getAll();
                }catch(Exception ex){
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            //}
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("getFromArea")
    @Produces("application/json")
    @Consumes("application/json")
    public String getFromArea(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            if (true){
                try{
                    response.Data = ChiNhanhAdapter.getFromArea(request.Predicates[0]);
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
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            //if (true){
                try{
                    response.Data = ChiNhanhAdapter.getSingle(request.Predicates[0]);
                }catch(Exception ex){
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            //}
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("getMyAgency")
    @Produces("application/json")
    @Consumes("application/json")
    public String getMyAgency(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            //if (true){
                try{
                    response.Data = ChiNhanhAdapter.getSingle(token.AgencyId);
                }catch(Exception ex){
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            //}
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("add")
    @Produces("application/json")
    @Consumes("application/json")
    public String add(String json) {
        Gson gson = new Gson();
        AgencyRequest request = gson.fromJson(json, AgencyRequest.class);
        AgencyResponse response = new AgencyResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            if (ChiNhanhBUS.insertValidate(request, response)){
                try{
                    if (ChiNhanhAdapter.add(request)){ 
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
        DeleteAgencyRequest request = gson.fromJson(json, DeleteAgencyRequest.class);   
        DeleteAgencyResponse response = new DeleteAgencyResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            if (ChiNhanhBUS.deleteValidate(request, response)){
                try{
                    int result = ChiNhanhAdapter.delete(request.Data);
                    if (result != 1){
                        response.Errors.add("Xóa chi nhánh thất bại.");
                        response.IsError = true;
                    }else{
                        response.Data = "Xóa chi nhánh thành công.";
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
        DeleteAgencyRequest request = gson.fromJson(json, DeleteAgencyRequest.class);   
        DeleteAgencyResponse response = new DeleteAgencyResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            if (ChiNhanhBUS.deleteValidate(request, response)){
                try{
                    int result = ChiNhanhAdapter.recover(request.Data);
                    if (result != 1){
                        response.Errors.add("Khôi phục chi nhánh thất bại.");
                        response.IsError = true;
                    }else{
                        response.Data = "Khôi phục chi nhánh thành công.";
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
        AgencyRequest request = gson.fromJson(json, AgencyRequest.class);
        AgencyResponse response = new AgencyResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            if (BusinessHandler.ChiNhanhBUS.updateValidate(request, response)){
                try{
                    if (ChiNhanhAdapter.update(request)){
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
    @Path("getInfoAgency")
    @Produces("application/json")
    @Consumes("application/json")
    public String getInfoAgency(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            //if (true){
                try{
                    response.Data = ChiNhanhAdapter.getInfoAgency(token.AgencyId);
                }catch(Exception ex){
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            //}
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("getInfoCompany")
    @Produces("application/json")
    @Consumes("application/json")
    public String getInfoCompany(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 3);
        if (token != null){
            //if (true){
                try{
                    response.Data = ChiNhanhAdapter.getInfoCompany(token.AgencyId);
                }catch(Exception ex){
                    response.Errors.add("Lỗi hệ thống.");
                    response.IsError = true;
                }
            //}
        }
        return gson.toJson(response);
    }
}
