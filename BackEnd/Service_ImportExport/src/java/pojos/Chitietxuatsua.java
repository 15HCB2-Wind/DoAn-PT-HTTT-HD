package pojos;
// Generated May 5, 2017 9:58:08 PM by Hibernate Tools 4.3.1



/**
 * Chitietxuatsua generated by hbm2java
 */
public class Chitietxuatsua  implements java.io.Serializable {


     private String machungtu;
     private String makho;
     private Double luongsuaxuat;

    public Chitietxuatsua() {
    }

	
    public Chitietxuatsua(String machungtu) {
        this.machungtu = machungtu;
    }
    public Chitietxuatsua(String machungtu, String makho, Double luongsuaxuat) {
       this.machungtu = machungtu;
       this.makho = makho;
       this.luongsuaxuat = luongsuaxuat;
    }
   
    public String getMachungtu() {
        return this.machungtu;
    }
    
    public void setMachungtu(String machungtu) {
        this.machungtu = machungtu;
    }
    public String getMakho() {
        return this.makho;
    }
    
    public void setMakho(String makho) {
        this.makho = makho;
    }
    public Double getLuongsuaxuat() {
        return this.luongsuaxuat;
    }
    
    public void setLuongsuaxuat(Double luongsuaxuat) {
        this.luongsuaxuat = luongsuaxuat;
    }




}

