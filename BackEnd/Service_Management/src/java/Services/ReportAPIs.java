/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.BoAdapter;
import DAO.ChuongTraiAdapter;
import DAO.ReportAdapter;
import Models.DataAccess.Report.ReportRequest;
import Models.DataAccess.Report.ReportResponse;
import Models.TokenData;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Shin'sLaptop
 */
@Path("Report")
public class ReportAPIs {
    @Context
    private UriInfo context;
    
    public ReportAPIs() {
    }
    
    @POST
    @Path("getAllCowOfAgencyBy2")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAllCowOfAgencyBy2(String json) {
        Gson gson = new Gson();
        ReportRequest request = gson.fromJson(json, ReportRequest.class);   
        ReportResponse response = new ReportResponse();
        TokenData token = BusinessHandler.TokenBUS.tokenData(request, response, 2);
        if (token != null){
            try{
                request.macn = token.AgencyId;
                response.Data = ReportAdapter.getAllCowOfAgency(request);
            }catch(Exception ex){
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("getAllCowOfAgency")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAllCowOfAgency(String json) {
        Gson gson = new Gson();
        ReportRequest request = gson.fromJson(json, ReportRequest.class);   
        ReportResponse response = new ReportResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            try{
                response.Data = ReportAdapter.getAllCowOfAgency(request);
            }catch(Exception ex){
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
    
    @POST
    @Path("getAllCowOfCompany")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAllCowOfCompany(String json) {
        Gson gson = new Gson();
        ReportRequest request = gson.fromJson(json, ReportRequest.class);   
        ReportResponse response = new ReportResponse();
        if (BusinessHandler.TokenBUS.tokenCheck(request, response, 3)){
            try{
                response.Data = ReportAdapter.getAllCowOfCompany(request);
            }catch(Exception ex){
                response.Errors.add("Lỗi hệ thống.");
                response.IsError = true;
            }
        }
        return gson.toJson(response);
    }
}
