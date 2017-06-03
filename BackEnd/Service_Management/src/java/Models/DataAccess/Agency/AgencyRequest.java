/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DataAccess.Agency;

import Models.DataAccess.InsertRequest;
import pojos.Chinhanh;
import pojos.Nhanvien;

/**
 *
 * @author Ut Hieu
 */
public class AgencyRequest  extends InsertRequest{
    public Chinhanh CN_Data;
    public String AreaId;
    public String QL_Data1;
    public Nhanvien QL_Data2;
    public boolean Mode;
}
