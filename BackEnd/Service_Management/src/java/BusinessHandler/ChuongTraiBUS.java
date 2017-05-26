/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Models.DataAccess.Barn.BarnRequest;
import Models.DataAccess.Barn.BarnResponse;

/**
 *
 * @author Tu
 */
public class ChuongTraiBUS {

    public static boolean validateInformation(BarnRequest request, BarnResponse response) {
        if (request.Data.getTenchuong().isEmpty()) {
            response.NameErrors.add("Tên chuồng không được để trống!");
            response.IsError = true;
        }
        if (request.Data.getSucchua() < 0
                || request.Data.getSucchua() == null
                || request.Data.getSucchua() != (int) request.Data.getSucchua()) {
            response.SlotErrors.add("Sức chứa không hợp lệ");
            response.IsError = true;
        }
        if (request.Data.getDangchua() < 0
                || request.Data.getDangchua() == null
                || request.Data.getDangchua() != (int) request.Data.getDangchua()
                || request.Data.getDangchua() > request.Data.getSucchua()) {
            response.Slot1Errors.add("Quá sức chứa");
            response.IsError = true;
        }
        return !response.IsError;
    }
}
