/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pac1;

/**
 *
 * @author Thuy Trang Nguyen
 */
public class User {
    private String manguoidung;
    private String tennguoidung;
    private String mssv;
    private String ngaysinh;
    private String ngaydangki;
    private int trangthai;

    public User(){
        
    }
    public User(String ma,String ten,String msv,String ngay,String dki){
        this.manguoidung = ma;
        this.tennguoidung = ten;
        this.mssv = msv;
        this.ngaysinh = ngay;
        this.ngaydangki = dki;
    }
    public String getManguoidung() {
        return manguoidung;
    }

    public void setManguoidung(String manguoidung) {
        this.manguoidung = manguoidung;
    }

    public String getTennguoidung() {
        return tennguoidung;
    }

    public void setTennguoidung(String tennguoidung) {
        this.tennguoidung = tennguoidung;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    } 

    public String getNgaydangki() {
        return ngaydangki;
    }

    public void setNgaydangki(String ngaydangki) {
        this.ngaydangki = ngaydangki;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
    
}
