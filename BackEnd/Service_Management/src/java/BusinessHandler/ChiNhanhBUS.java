/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Models.DataAccess.Agency.DeleteAgencyRequest;
import Models.DataAccess.Agency.DeleteAgencyResponse;
import Models.DataAccess.Agency.InsertAgencyRequest;
import Models.DataAccess.Agency.InsertAgencyResponse;
import Models.DataAccess.Agency.UpdateAgencyRequest;
import Models.DataAccess.Agency.UpdateAgencyResponse;
import Models.DataAccess.DeleteRequest;
import Models.DataAccess.DeleteResponse;

/**
 *
 * @author Ut Hieu
 */
public class ChiNhanhBUS {
    public static boolean insertValidate(InsertAgencyRequest request, InsertAgencyResponse response){
        if (request.Data.getTenchinhanh().isEmpty()){
            response.NameErrors.add("Tên Chi Nhánh không được để trống!");
            response.IsError = true;
        }
        if (request.Data.getQuanly().isEmpty()){
            response.ManagerErrors.add("Vui lòng chọn nhân viên quản lý");
            response.IsError = true;
        }
        return !response.IsError;
    }
    
    public static boolean updateValidate(UpdateAgencyRequest request, UpdateAgencyResponse response){
        if (request.Data.getTenchinhanh().isEmpty()){
            response.NameErrors.add("Tên Chi Nhánh không được để trống!");
            response.IsError = true;
        }
         if (request.Data.getQuanly().isEmpty()){
            response.ManagerErrors.add("Vui lòng chọn nhân viên quản lý");
            response.IsError = true;
        }
        return !response.IsError;
    }
    
    public static boolean deleteValidate(DeleteAgencyRequest request, DeleteAgencyResponse response){
        if (request.Data.getMachinhanh().equals("")){
            response.Errors.add("Không đủ tham số tìm kiếm!");
            response.IsError = true;
        }
        return !response.IsError;
    }
    
}
