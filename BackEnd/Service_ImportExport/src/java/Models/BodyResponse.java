/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.google.gson.Gson;
import java.util.*;
import javax.ws.rs.core.Response;

/**
 *
 * @author Shin'sLaptop
 */
public abstract class BodyResponse {
    public Object Data;
    public boolean IsError;
    public ArrayList<String> Errors;
    public boolean IsTokenTimeout;
    
    public BodyResponse(){
        Data = null;
        IsError = false;
        Errors = new ArrayList<>();
        IsTokenTimeout = false;
    }
    public Response json(){
        return Response.ok(new Gson().toJson(this))
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
    
}