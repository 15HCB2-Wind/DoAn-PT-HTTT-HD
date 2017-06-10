/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Config.Configs;
import Models.BodyResponse;
import Models.DataAccess.SelectResponse;
import com.google.gson.Gson;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author BuaNhot
 */
public class PhieuXuatBUS {
    public static void updateMilk(String id, double value, String token){
        try{
            Client client = ClientBuilder.newClient();
            Response res = client
                    .target(Configs.SUB_MILK)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(String.format("{ Token: %s, Id: %s, Value: %f }", token, id, -value)));
        } catch (Exception ex){ }
    }
    
    public static boolean updateMilk_isReady2Sub(String id, double value, String token){
        try{
            Client client = ClientBuilder.newClient();
            Response res = client
                    .target(Configs.CHECK_SUB_MILK)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(String.format("{ Token: %s, Id: %s, Value: %f }", token, id, value)));
            
            String json = res.readEntity(String.class);
            SelectResponse result = new Gson().fromJson(json, SelectResponse.class);
            if (result != null){
                return !result.IsError;
            }
        } catch (Exception ex){ }
        return false;
    }
}
