/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import DAO.ChiNhanhAdapter;
import DAO.CounterAdapter;
import DAO.NhanVienAdapter;
import Models.DataAccess.Agency.AgencyRequest;
import Models.DataAccess.Agency.AgencyResponse;
import Models.DataAccess.Agency.DeleteAgencyRequest;
import Models.DataAccess.Agency.DeleteAgencyResponse;
import pojos.Chinhanh;
import pojos.Nhanvien;

/**
 *
 * @author Ut Hieu
 */
public class ChiNhanhBUS {
    public static boolean insertValidate(AgencyRequest request, AgencyResponse response){
        if (request.CN_Data.getTenchinhanh() == null || request.CN_Data.getTenchinhanh().isEmpty()){
            response.Errors.add("Tên không được để trống!");
            response.IsError = true;
        }else if (CounterAdapter.getAreaCounter(request.AreaId) == null){
            response.Errors.add("Khu vực chi nhánh không hợp lệ!");
            response.IsError = true;
        }else if (!ChiNhanhAdapter.isValidName(request.CN_Data.getTenchinhanh())) {
            response.Errors.add("Tên chi nhánh đã tồn tại!");
            response.IsError = true;
        }else if (request.Mode){
            if (request.QL_Data1 == null || request.QL_Data1.isEmpty()){
                response.Errors.add("Mã quản lý không được để trống!");
                response.IsError = true;
            }else{
                Nhanvien man = NhanVienAdapter.getSingle(request.QL_Data1);
                if (man == null){
                    response.Errors.add("Mã quản lý không hợp lệ!");
                    response.IsError = true;
                } else if (!man.getMaphanquyen().equals("PQ001")) {
                    response.Errors.add("Nhân viên không thể cùng lúc quản lý 2 chi nhánh!");
                    response.IsError = true;
                }
            }
        }else{
            if (request.QL_Data2.getHoten() == null || request.QL_Data2.getHoten().isEmpty() || 
                    request.QL_Data2.getEmail() == null || request.QL_Data2.getEmail().isEmpty() ||
                    !NhanVienAdapter.checkEmail(request.QL_Data2.getEmail())){
                response.Errors.add("Thông tin nhân viên quản lý không thể để trống!");
                response.IsError = true;
            }
        }
        return !response.IsError;
    }
    
    public static boolean updateValidate(AgencyRequest request, AgencyResponse response){
        Chinhanh updated = ChiNhanhAdapter.getSingle(request.CN_Data.getMachinhanh());
        if (updated == null){
            response.Errors.add("Không tìm thấy chi nhánh!");
            response.IsError = true;
        }else if (request.CN_Data.getTenchinhanh() == null || request.CN_Data.getTenchinhanh().isEmpty()){
            response.Errors.add("Tên không được để trống!");
            response.IsError = true;
        }else if (CounterAdapter.getAreaCounter(request.AreaId) == null){
            response.Errors.add("Khu vực chi nhánh không hợp lệ!");
            response.IsError = true;
        }else if (!updated.getTenchinhanh().equals(request.CN_Data.getTenchinhanh()) && !ChiNhanhAdapter.isValidName(request.CN_Data.getTenchinhanh())) {
            response.Errors.add("Tên chi nhánh đã tồn tại!");
            response.IsError = true;
        }else if (request.Mode){
            if (request.QL_Data1 == null || request.QL_Data1.isEmpty()){
                response.Errors.add("Mã quản lý không được để trống!");
                response.IsError = true;
            }else{
                Nhanvien man = NhanVienAdapter.getSingle(request.QL_Data1);
                if (man == null){
                    response.Errors.add("Mã quản lý không hợp lệ!");
                    response.IsError = true;
                } else if (!updated.getQuanly().equals(man.getManhanvien()) && !man.getMaphanquyen().equals("PQ001")) {
                    response.Errors.add("Nhân viên không thể cùng lúc quản lý 2 chi nhánh!");
                    response.IsError = true;
                }
            }
        }else{
            if (request.QL_Data2.getHoten() == null || request.QL_Data2.getHoten().isEmpty() || 
                    request.QL_Data2.getEmail() == null || request.QL_Data2.getEmail().isEmpty() ||
                    !NhanVienAdapter.checkEmail(request.QL_Data2.getEmail())){
                response.Errors.add("Thông tin nhân viên quản lý không thể để trống!");
                response.IsError = true;
            }
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
