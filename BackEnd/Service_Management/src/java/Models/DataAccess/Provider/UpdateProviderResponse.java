/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DataAccess.Provider;

import Models.DataAccess.UpdateResponse;
import java.util.ArrayList;

/**
 *
 * @author Ut Hieu
 */
public class UpdateProviderResponse extends UpdateResponse{
     public ArrayList<String> NameErrors;
    
    public UpdateProviderResponse(){
        NameErrors = new ArrayList<>();
    }
}
