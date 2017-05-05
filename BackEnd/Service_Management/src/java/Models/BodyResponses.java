/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.*;

/**
 *
 * @author Shin'sLaptop
 */
public abstract class BodyResponses {
    public Object Data;
    public boolean IsError;
    public ArrayList<String> Errors;
    
    public BodyResponses(){
        Data = null;
        IsError = false;
        Errors = new ArrayList<>();
    }
}
