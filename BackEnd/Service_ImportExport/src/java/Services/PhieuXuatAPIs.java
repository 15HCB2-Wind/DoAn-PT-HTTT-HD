/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.PhieuXuatAdapter;
import com.google.gson.GsonBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Shin-Desktop
 */
@Path("PhieuXuat")
public class PhieuXuatAPIs {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NhanVienAPIs
     */
    public PhieuXuatAPIs() {
    }

    @GET
    @Path("getAll")
    @Produces("application/json")
    public String getAll(){
        return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().toJson(PhieuXuatAdapter.getAll());
    }
    
}
