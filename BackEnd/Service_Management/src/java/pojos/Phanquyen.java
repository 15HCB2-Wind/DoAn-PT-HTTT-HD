package pojos;
// Generated May 5, 2017 12:35:10 PM by Hibernate Tools 4.3.1



/**
 * Phanquyen generated by hbm2java
 */
public class Phanquyen  implements java.io.Serializable {


     private String maphanquyen;
     private String tenphanquyen;
     private Integer capphanquyen;

    public Phanquyen() {
    }

	
    public Phanquyen(String maphanquyen) {
        this.maphanquyen = maphanquyen;
    }
    public Phanquyen(String maphanquyen, String tenphanquyen, Integer capphanquyen) {
       this.maphanquyen = maphanquyen;
       this.tenphanquyen = tenphanquyen;
       this.capphanquyen = capphanquyen;
    }
   
    public String getMaphanquyen() {
        return this.maphanquyen;
    }
    
    public void setMaphanquyen(String maphanquyen) {
        this.maphanquyen = maphanquyen;
    }
    public String getTenphanquyen() {
        return this.tenphanquyen;
    }
    
    public void setTenphanquyen(String tenphanquyen) {
        this.tenphanquyen = tenphanquyen;
    }
    public Integer getCapphanquyen() {
        return this.capphanquyen;
    }
    
    public void setCapphanquyen(Integer capphanquyen) {
        this.capphanquyen = capphanquyen;
    }




}


