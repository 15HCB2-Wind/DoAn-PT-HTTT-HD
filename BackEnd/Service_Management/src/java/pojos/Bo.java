package pojos;
// Generated May 29, 2017 11:39:08 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Bo generated by hbm2java
 */
public class Bo  implements java.io.Serializable {


     private String mabo;
     private String machip;
     private String mausac;
     private Boolean coditat;
     private String nhandang;
     private String tinhtrang;
     private Boolean daxoa;
     private String machuong;
     private Date ngaynhan;
     
     public String tenchuong;

    public Bo() {
    }

	
    public Bo(String mabo) {
        this.mabo = mabo;
    }
    public Bo(String mabo, String machip, String mausac, Boolean coditat, String nhandang, String tinhtrang, Boolean daxoa, String machuong, Date ngaynhan) {
       this.mabo = mabo;
       this.machip = machip;
       this.mausac = mausac;
       this.coditat = coditat;
       this.nhandang = nhandang;
       this.tinhtrang = tinhtrang;
       this.daxoa = daxoa;
       this.machuong = machuong;
       this.ngaynhan = ngaynhan;
    }
   
    public String getMabo() {
        return this.mabo;
    }
    
    public void setMabo(String mabo) {
        this.mabo = mabo;
    }
    public String getMachip() {
        return this.machip;
    }
    
    public void setMachip(String machip) {
        this.machip = machip;
    }
    public String getMausac() {
        return this.mausac;
    }
    
    public void setMausac(String mausac) {
        this.mausac = mausac;
    }
    public Boolean getCoditat() {
        return this.coditat;
    }
    
    public void setCoditat(Boolean coditat) {
        this.coditat = coditat;
    }
    public String getNhandang() {
        return this.nhandang;
    }
    
    public void setNhandang(String nhandang) {
        this.nhandang = nhandang;
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
    public String getMachuong() {
        return this.machuong;
    }
    
    public void setMachuong(String machuong) {
        this.machuong = machuong;
    }
    public Date getNgaynhan() {
        return this.ngaynhan;
    }
    
    public void setNgaynhan(Date ngaynhan) {
        this.ngaynhan = ngaynhan;
    }




}


