/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Models.Other.CheckTokenResponse;
import Models.Other.CheckTokenRequest;
import Config.Configs;
import Models.*;
import com.google.gson.Gson;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Shin'sLaptop
 */
public class TokenBUS {
    public static boolean tokenCheck(BodyRequests request, BodyResponses response, int role){
        if (!Configs.DEBUG_MODE){
            int times = 3;
            boolean fail = true;
            
            CheckTokenRequest ctRequest = new CheckTokenRequest();
            ctRequest.Token = request.Token;
            ctRequest.TokenPassword = Configs.TOKEN_PASSWORD;
            ctRequest.Role = role;

            do{
                try{
                    Client client = ClientBuilder.newClient();
                    Response res = client
                            .target(Configs.CHECK_TOKEN)
                            .request(MediaType.APPLICATION_JSON)
                            .post(Entity.json(new Gson().toJson(ctRequest)));

                    CheckTokenResponse result = res.readEntity(CheckTokenResponse.class);
                    fail = result == null;
                    if (!fail && result.IsError)
                        break;
                } catch (Exception ex){ }
            }while(fail && --times > 0);
            
            if (fail){
                response.Errors.add("Không thể truy cập đến máy chủ.");
                response.IsError = true;
            }
        }
        return !response.IsError;
    }
}
