package pojos;
// Generated May 9, 2017 7:00:32 PM by Hibernate Tools 4.3.1



/**
 * Nhacungcap generated by hbm2java
 */
public class Nhacungcap  implements java.io.Serializable {


     private String manhacungcap;
     private String ten;
     private String diachi;
     private String tinhtrang;

    public Nhacungcap() {
    }

	
    public Nhacungcap(String manhacungcap) {
        this.manhacungcap = manhacungcap;
    }
    public Nhacungcap(String manhacungcap, String ten, String diachi, String tinhtrang) {
       this.manhacungcap = manhacungcap;
       this.ten = ten;
       this.diachi = diachi;
       this.tinhtrang = tinhtrang;
    }
   
    public String getManhacungcap() {
        return this.manhacungcap;
    }
    
    public void setManhacungcap(String manhacungcap) {
        this.manhacungcap = manhacungcap;
    }
    public String getTen() {
        return this.ten;
    }
    
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getDiachi() {
        return this.diachi;
    }
    
    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    public String getTinhtrang() {
        return this.tinhtrang;
    }
    
    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }




}

