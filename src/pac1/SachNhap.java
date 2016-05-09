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
public class SachNhap extends Sach{
    private String ngaynhap;
    
    public SachNhap(){
        
    }
    public SachNhap(String ma,String ngay,int them){
        masach = ma;
        ngaynhap = ngay;
        nhapthem = them;
    }
    public String getNgaynhap() {
        return ngaynhap;
    }

    public Sach setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
        return this;
    }
}
