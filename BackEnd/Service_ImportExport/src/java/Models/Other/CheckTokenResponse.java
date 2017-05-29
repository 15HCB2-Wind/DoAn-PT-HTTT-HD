/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Other;

import Models.BodyResponse;
import Models.TokenData;
import java.util.ArrayList;

/**
 *
 * @author 19101994
 */
public class CheckTokenResponse extends BodyResponse {
    public TokenData Data;
    public boolean IsError;
    public ArrayList<String> Errors;
    public boolean IsTokenTimeout;
    
    public CheckTokenResponse(){
        Data = null;
        IsError = false;
        Errors = new ArrayList<>();
        IsTokenTimeout = false;
    }
}
