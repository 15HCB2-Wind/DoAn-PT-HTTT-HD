/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DataAccess.Agency;

import Models.DataAccess.InsertResponse;
import java.util.ArrayList;

/**
 *
 * @author Ut Hieu
 */
public class InsertAgencyResponse extends InsertResponse {
    public ArrayList<String> NameErrors;
    
    public InsertAgencyResponse(){
        NameErrors = new ArrayList<>();
    }
}
