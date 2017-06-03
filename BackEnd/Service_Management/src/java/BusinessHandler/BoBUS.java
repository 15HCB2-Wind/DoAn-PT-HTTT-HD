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
        if (request.Data.getMachip() == null || request.Data.getMachip().isEmpty()) {
            response.Errors.add("Mã chip không được để trống!");
            response.IsError = true;
        }
        if (request.Data.getMachuong() == null || request.Data.getMachuong().isEmpty()) {
            response.Errors.add("Xin hãy chọn chuồng!");
            response.IsError = true;
        }
        return !response.IsError;
    }
    
}
