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
        IsTokenTimeout = false;
    
    public Response json(){
        return Response.ok(new Gson().toJson(this))
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
}
