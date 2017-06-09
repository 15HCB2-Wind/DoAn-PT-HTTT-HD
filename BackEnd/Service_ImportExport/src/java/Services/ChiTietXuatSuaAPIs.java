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
    
    
}
