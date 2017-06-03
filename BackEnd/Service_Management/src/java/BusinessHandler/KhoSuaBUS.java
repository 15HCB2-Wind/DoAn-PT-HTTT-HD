/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import DAO.KhoSuaAdapter;
import Models.DataAccess.DeleteRequest;
import Models.DataAccess.DeleteResponse;
import Models.DataAccess.Warehouse.WarehouseRequest;
import Models.DataAccess.Warehouse.WarehouseResponse;
import Models.DataAccess.Warehouse.WarehouseTransferRequest;

/**
 *
 * @author 19101994
 */
public class KhoSuaBUS {

    public static boolean insertUpdateValidate(WarehouseRequest request, WarehouseResponse response) {
        if (request.Data.getSucchua() <= 0) {
            response.Errors.add("Sức chứa phải lớn hơn 0!");
            response.IsError = true;
        }
        if (request.Data.getTenkho() == null || request.Data.getTenkho().isEmpty()) {
            response.Errors.add("Tên kho không được để trống!");
            response.IsError = true;
        }
        return !response.IsError;
    }

    public static boolean transferValidate(WarehouseTransferRequest request, WarehouseResponse response) {
        if (request.From == null || request.To == null || request.From.equals(request.To)) {
            response.Errors.add("Kho chuyển không hợp lệ!");
            response.IsError = true;
        }
        if (request.Value <= 0) {
            response.Errors.add("Giá trị chuyển không hợp lệ!");
            response.IsError = true;
        }
        return !response.IsError;
    }

    public static boolean deleteValidate(DeleteRequest request, DeleteResponse response) {
        if (request.Predicates.length <= 0) {
            response.Errors.add("Kho xóa không hợp lệ!");
            response.IsError = true;
        }
        return !response.IsError;
    }
}
