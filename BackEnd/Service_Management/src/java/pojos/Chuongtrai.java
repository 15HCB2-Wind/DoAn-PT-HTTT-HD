package pojos;
// Generated May 26, 2017 4:07:04 PM by Hibernate Tools 4.3.1



/**
 * Chuongtrai generated by hbm2java
 */
public class Chuongtrai  implements java.io.Serializable {


     private String machuong;
     private String tenchuong;
     private Integer succhua;
     private Integer dangchua;
     private String tinhtrang;
     private Boolean daxoa;
     private String machinhanh;

    public Chuongtrai() {
    }

	
    public Chuongtrai(String machuong) {
        this.machuong = machuong;
    }
    public Chuongtrai(String machuong, String tenchuong, Integer succhua, Integer dangchua, String tinhtrang, Boolean daxoa, String machinhanh) {
       this.machuong = machuong;
       this.tenchuong = tenchuong;
       this.succhua = succhua;
       this.dangchua = dangchua;
       this.tinhtrang = tinhtrang;
       this.daxoa = daxoa;
       this.machinhanh = machinhanh;
    }
   
    public String getMachuong() {
        return this.machuong;
    }
    
    public void setMachuong(String machuong) {
        this.machuong = machuong;
    }
    public String getTenchuong() {
        return this.tenchuong;
    }
    
    public void setTenchuong(String tenchuong) {
        this.tenchuong = tenchuong;
    }
    public Integer getSucchua() {
        return this.succhua;
    }
    
    public void setSucchua(Integer succhua) {
        this.succhua = succhua;
    }
    public Integer getDangchua() {
        return this.dangchua;
    }
    
    public void setDangchua(Integer dangchua) {
        this.dangchua = dangchua;
    }
    public String getTinhtrang() {
        return this.tinhtrang;
    }
    
    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
    public Boolean getDaxoa() {
        return this.daxoa;
    }
    
    public void setDaxoa(Boolean daxoa) {
        this.daxoa = daxoa;
    }
    public String getMachinhanh() {
        return this.machinhanh;
    }
    
    public void setMachinhanh(String machinhanh) {
        this.machinhanh = machinhanh;
    }




}


