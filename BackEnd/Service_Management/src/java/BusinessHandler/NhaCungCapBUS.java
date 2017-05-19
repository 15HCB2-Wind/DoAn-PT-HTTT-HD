/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Models.DataAccess.Provider.InsertProviderRequest;
import Models.DataAccess.Provider.InsertProviderResponse;
import Models.DataAccess.Provider.UpdateProviderRequest;
import Models.DataAccess.Provider.UpdateProviderResponse;

/**
 *
 * @author Ut Hieu
 */
public class NhaCungCapBUS {
     public static boolean insertValidate(InsertProviderRequest request, InsertProviderResponse response){
        if (request.Data.getTen().isEmpty()){
            response.NameErrors.add("Họ tên không được để trống!");
            response.IsError = true;
        }
        return !response.IsError;
    }
    
    public static boolean updateValidate(UpdateProviderRequest request, UpdateProviderResponse response){
        if (request.Data.getTen().isEmpty()){
            response.NameErrors.add("Họ tên không được để trống!");
            response.IsError = true;
        }
        return !response.IsError;
    }
}
