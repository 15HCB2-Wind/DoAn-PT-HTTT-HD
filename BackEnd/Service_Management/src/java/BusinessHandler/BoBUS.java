/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Models.DataAccess.Cow.CowRequest;
import Models.DataAccess.Cow.CowResponse;

/**
 *
 * @author 19101994
 */
public class BoBUS {
    
    public static boolean insertUpdateValidate(CowRequest request, CowResponse response) {
        return !response.IsError;
    }
    
}
