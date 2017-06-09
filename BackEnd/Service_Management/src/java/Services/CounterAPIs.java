/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.CounterAdapter;
import Models.DataAccess.*;
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
@Path("Counter")
public class CounterAPIs {

    @Context
    private UriInfo context;


    public CounterAPIs() {
    }
    
    @POST
    @Path("getAllAreas")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAllAreas(String json){
        Gson gson = new Gson();
        SelectRequest request = gson.fromJson(json, SelectRequest.class);
        SelectResponse response = new SelectResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            try{
                response.Data = CounterAdapter.getAllAreas();
            }catch(Exception ex){
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
}
