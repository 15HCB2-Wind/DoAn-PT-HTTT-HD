/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Models.DataAccess.BillImportCow.InsertBillImportCowReponse;
import Models.DataAccess.BillImportCow.InsertBillImportCowRequest;
import Models.DataAccess.SelectRequest;
import Models.DataAccess.SelectResponse;

/**
 *
 * @author BuaNhot
 */
public class PhieuNhapBoBUS {
    
    public static boolean getSingleValidate(SelectRequest request, SelectResponse response){
        if (request.Predicate.length <= 0){
            response.Errors.add("Không đủ tham số tìm kiếm!");
            response.IsError = true;
        }
        return !response.IsError;
    }
    
    public static boolean insertValidate(InsertBillImportCowRequest request, InsertBillImportCowReponse response){
        if (request.Data.getMancc().isEmpty()){
            response.MaNhaCungCapError.add("Họ tên không được để trống!");
            response.IsError = true;
        }
        
        return !response.IsError;
    }
    
}
