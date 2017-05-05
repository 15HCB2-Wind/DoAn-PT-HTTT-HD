/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Models.UpdatePersonnelRequest;
import Models.UpdatePersonnelResponse;

/**
 *
 * @author Shin'sLaptop
 */
public class NhanVienAPIsChecker {
    public static boolean updateNhanVienChecker(UpdatePersonnelRequest request, UpdatePersonnelResponse response){
        if (request.Data.getHoten().isEmpty()){
            response.Errors.add("Họ tên không được để trống!");
            response.IsError = true;
        }
        return !response.IsError;
    }
}
