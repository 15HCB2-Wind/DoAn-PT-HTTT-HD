/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DataAccess.Export;

import Models.DataAccess.InsertRequest;
import java.util.List;
import pojos.Chitietxuatsua;
import pojos.Phieuxuat;

/**
 *
 * @author BuaNhot
 */
public class InsertExportRequest extends InsertRequest {
    public Phieuxuat Data;
    public List<Chitietxuatsua> Details;
}
