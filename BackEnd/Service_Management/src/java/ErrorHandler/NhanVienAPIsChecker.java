/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ErrorHandler;

import Models.UpdatePersonnelBodyResponse;
import pojos.Nhanvien;

/**
 *
 * @author Shin'sLaptop
 */
public class NhanVienAPIsChecker {
    public static boolean updateNhanVienChecker(Nhanvien request, UpdatePersonnelBodyResponse response){
        if (request.getHoten().isEmpty()){
            response.Errors.add("Họ tên không được để trống!");
            response.IsError = true;
        }
        return !response.IsError;
    }
}
