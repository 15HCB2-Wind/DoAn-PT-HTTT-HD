/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessHandler;

import Models.DataAccess.SelectRequest;
import Models.DataAccess.SelectResponse;
import Models.DataAccess.Warehouse.WarehouseRequest;
import Models.DataAccess.Warehouse.WarehouseResponse;

/**
 *
 * @author 19101994
 */
public class KhoSuaBUS {

    public static boolean insertUpdateValidate(WarehouseRequest request, WarehouseResponse response) {
        return !response.IsError;
    }
    
}
