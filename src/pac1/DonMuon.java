/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pac1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static util.DataUtil.conn;

/**
 *
 * @author Trang
 */
public class DonMuon {
    private int id;
    private String maSach;
    private String maThanhVien;
    private String ngayMuon;
    private String ngayTraDuKien;
    private int soLuong;
    private int tongTien;
    private int trangThai;
    private int tiencoc;
    private int soluongdatra;

    public int getSoluongdatra() {
//        try {
//            String sql = "Select sum(so_luong) from tra where id_muon='"+a+"' group by id_muon='"+a+"'";
//            Statement statement = conn.createStatement();
//            ResultSet rs = statement.executeQuery(sql);
//           while (rs.next()) {
//               soluongdatra = rs.getInt("sum(so_luong)");
//           }
//        } catch (SQLException ex) {
//            Logger.getLogger(DonMuon.class.getName()).log(Level.SEVERE, null, ex);
//        }
       // System.out.println(soluongdatra);
         return soluongdatra;
    }

    public void setSoluongdatra(int soluongdatra) {
        this.soluongdatra = soluongdatra;
    }

    public int getTiencoc() {
        return tiencoc;
    }

    public void setTiencoc(int tiencoc) {
        this.tiencoc = tiencoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaThanhVien() {
        return maThanhVien;
    }

    
    
    public void setMaThanhVien(String maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getNgayTraDuKien() {
        return ngayTraDuKien;
    }

    public void setNgayTraDuKien(String ngayTraDuKien) {
        this.ngayTraDuKien = ngayTraDuKien;
    }

    
    
    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }


//    public int getTienNgay() {
//        return tienNgay;
//    }
//
//    public void setTienNgay(int tienNgay) {
//        this.tienNgay = tienNgay;
//    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
