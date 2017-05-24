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
    public static boolean tokenCheck(BodyRequest request, BodyResponse response, int role){
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
                    
                    String json = res.readEntity(String.class);
                    CheckTokenResponse result = new Gson().fromJson(json, CheckTokenResponse.class);
//                    CheckTokenResponse result = res.readEntity(CheckTokenResponse.class);
                    fail = result == null;
                    if (!fail){
                        response.IsTokenTimeout = result.IsTokenTimeout;
                        if (result.IsError){
                            response.Errors.add("Không thể truy cập đến máy chủ.");
                            response.IsError = true;
                        }
                        break;
                    }
                } catch (Exception ex){ }
            }while(fail && --times > 0);
        }
        return !response.IsError && !response.IsTokenTimeout;
    }
    
    public static TokenData tokenData(BodyRequest request, BodyResponse response, int role){
        TokenData tokenData = null;
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
                    
                    String json = res.readEntity(String.class);
                    CheckTokenResponse result = new Gson().fromJson(json, CheckTokenResponse.class);
//                    CheckTokenResponse result = res.readEntity(CheckTokenResponse.class);
                    fail = result == null;
                    if (!fail){
                        response.IsTokenTimeout = result.IsTokenTimeout;
                        if (result.IsError){
                            response.Errors.add("Không thể truy cập đến máy chủ.");
                            response.IsError = true;
                            return null;
                        }else{
                            tokenData = result.Data;
                        }
                        break;
                    }
                } catch (Exception ex){ 
                    System.out.print(ex.getMessage());
                }
            }while(fail && --times > 0);
        }
        if (response.IsTokenTimeout){
            return null;
        }
        return tokenData;
    }
}
