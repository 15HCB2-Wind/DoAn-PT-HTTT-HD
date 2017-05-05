/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Config.Configs;
import Models.BodyRequests;
import Models.BodyResponses;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pojos.Nhanvien;

/**
 *
 * @author Shin'sLaptop
 */
public class TokenChecker {
    public static boolean tokenCheck(BodyRequests request, BodyResponses response){
        
        Client client = ClientBuilder.newClient();
        Form form = new Form();
        form.param("Token", request.Token);
        
        Response responseToken = client
                .target(Configs.sync2LoginService)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.form(form));
        
        Nhanvien result = responseToken.readEntity(Nhanvien.class);
        
        if (result == null){
            response.Errors.add("Không thể kết nối tới máy chủ!");
            response.IsError = true;
        }
        
        return !response.IsError;
    }
}
