/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import pac1.Sach;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pac1.DonMuon;
import pac1.DonTra;
import pac1.MuonTra;
import pac1.NXB;
import pac1.SachNhap;
import pac1.TheLoai;
import pac1.User;

/**
 *
 * @author Thuy Trang Nguyen
 */
public class DataUtil {
    public static Connection conn;
    //public static String DATA = "jdbc:sqlserver://THUYTRANGNGUYEN;databaseName=thuvien";
    /*
    public static String USER = "root";
    public static String PASS = "";
    public static final String className = "com.mysql.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/thuvien";
*/
    public  String USER = "root";
    public  String PASS = "";
    public  static final String className = "com.mysql.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/thuvien?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
    
     public  void connect(){
        try{
            Class.forName(className);
            conn = DriverManager.getConnection(url,USER,PASS);
            System.out.println("connect success");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "connect fail");
        }
    }
    public  Connection getConnection(){
        if(conn == null){
            connect();
        }
        return conn;
    }
    
    
    public ResultSet executeQuery(String sql){
        try {
            connect();
            Statement statement = conn.createStatement();
            statement.executeUpdate("SET CHARACTER SET UTF8");
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   
    public Sach getSach(String sql){
        Sach sach = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            if(rs!=null){
                sach = new Sach();
                String masach = rs.getString("masach");
                System.out.println("ma sach "+masach);
                String tensach = rs.getString(3);
                String tacgia = rs.getString(4);
                int sotrang = rs.getInt(5);
                String theloai = rs.getString(6);
                String nxb = rs.getString(7);
                String namxb = rs.getString(8);
                String giatien = rs.getString(9);
                int soluong = rs.getInt(10);
                int chomuon = rs.getInt(11);
                int sosachcon = rs.getInt(12);
                
                sach.setMasach(masach)
                    .setTensach(tensach)
                    .setTacgia(tacgia)
                    .setSotrang(sotrang)
                    .setTheloai(theloai)
                    .setNxb(nxb)
                    .setNamxb(namxb)
                    .setGiatien(giatien)
                    .setSoluong(soluong)
                    //.setNhapthem(nhapthem)
                    .setChomuon(chomuon)
                    .setSosachcon(sosachcon);
            }
            return sach;
            
        } catch (Exception e) {
      
        }
        return null;
    }
    public User getUser(String sql){
        User user = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            if(rs!=null){
                user = new User("U"+rs.getString(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                System.out.println("rs"+rs);
            }
            return user;
            
        } catch (Exception e) {
      
        }
        return null;
    }
    
    public TheLoai getTheloai(String sql){
        TheLoai tl = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            if(rs!=null){
                tl = new TheLoai();
                tl.setTheloai(rs.getString(2));
            }
            return tl;
            
        } catch (Exception e) {
      
        }
        return null;
    }
    
    public NXB getNXB(String sql){
        NXB nxb = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            if(rs!=null){
                nxb = new NXB();
                nxb.setTenNxb(rs.getString(2));
            }
            return nxb;
            
        } catch (Exception e) {
      
        }
        return null;
    }
    
    public void deleteRow(String sql){
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("delete error");
        }
    }
    
    public int executeUpdate(String sql){
        PreparedStatement pst = null;
       
        try {
            //String sql = "INSERT INTO book(masach,tensach,tacgia,theloai,nxb,giatien,soluong,nhapthem,chomuon,sosachcon) values('"+sach.getMasach()+"','"+sach.getTensach()+"','"+sach.getTacgia()+"','"+sach.getTheloai()+"','"+sach.getNxb()+"','"+sach.getGiatien()+"','"+sach.getSoluong()+"','"+sach.getNhapthem()+"','"+sach.getChomuon()+"','"+sach.getSosachcon()+"')";
            pst = conn.prepareStatement(sql);
            if(pst.executeUpdate(sql)>0) return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public LinkedList<Sach> getListSach(String sql){
        LinkedList<Sach> book = new LinkedList<Sach>();

         //String sqlSach = "SELECT * FROM book ";
        try {
            Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql); 
             while (rs.next()) {
                 Sach sach = new Sach(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(5),rs.getString(9),rs.getInt(10),rs.getInt(11),rs.getInt(12));
                 book.add(sach);
             }
             return book;
        } catch (Exception e) {
                System.err.println("fail sách");
                return null;
        }
    }
    public LinkedList<SachNhap> getTimSach(String sql){
        LinkedList<SachNhap> book = new LinkedList<>();
        try {
            Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql); 
             while (rs.next()) {
                 SachNhap sach = new SachNhap(rs.getString(2),rs.getString(3),rs.getInt(4));
                 book.add(sach);
             }
             return book;
        } catch (Exception e) {
                System.err.println("fail sách");
                return null;
        }
    }
    public LinkedList<User> getListUser(String sql){
        LinkedList<User> user = new LinkedList<User>();

        //String sqlUser = "SELECT * FROM user ";
        
        try {
            Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql); 
             while (rs.next()) {
                 User thanhvien = new User("U"+rs.getString(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                 user.add(thanhvien);
             }
             return user;
        } catch (Exception e) {
                System.err.println("fail user");
                return null;
        }
    }
    //Database util cho Mượn Trả
    public LinkedList<DonMuon> getListMuon(){
        LinkedList<DonMuon> muonTraList = new LinkedList<DonMuon>();

        String sqlUser = "SELECT * FROM muon";
        
        try {
            Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sqlUser); 
             while (rs.next()) {
                 DonMuon muonTra = new DonMuon();
                 muonTra.setId(rs.getInt("id"));
                 muonTra.setMaSach(rs.getString("ma_sach"));
                 muonTra.setMaThanhVien(rs.getString("id_user"));
                 muonTra.setNgayMuon(rs.getString("ngay_muon"));
                 muonTra.setNgayTraDuKien(rs.getString("tra_du_kien"));
                // muonTra.setTienNgay(rs.getInt("gia_ngay"));
                muonTra.setSoLuong(rs.getInt("so_luong"));
//                muonTra.setTongTien(rs.getInt("so_tien"));
                muonTra.setTrangThai(rs.getInt("trang_thai"));
                muonTra.setTiencoc(rs.getInt("tien_coc"));
//                muonTra.setSoluongdatra(rs.getInt("so_luong_da_tra"));
                muonTraList.add(muonTra);
             }
             return muonTraList;
        } catch (Exception e) {
                System.err.println("fail muon");
                return null;
        }
    }
    
    public LinkedList<DonTra> getListTra(){
        LinkedList<DonTra> traList = new LinkedList<DonTra>();

        String sqlUser = "SELECT * FROM tra";
        
        try {
            Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sqlUser); 
             while (rs.next()) {
                 DonTra muonTra = new DonTra();
                 muonTra.setId(rs.getInt("id"));
                 muonTra.setIdMuon(rs.getInt("id_muon"));
                 muonTra.setNgayTra(rs.getString("ngay_tra"));
                 muonTra.setSoLuong(rs.getInt("so_luong"));
                 muonTra.setSoTien(rs.getInt("so_tien"));
                traList.add(muonTra);
             }
             return traList;
        } catch (Exception e) {
                System.err.println("fail tra");
                return null;
        }
    }
    
    public int getSoLuongSachConMuon(int idMuon){
        String sql = "SELECT SUM(so_luong) FROM tra Where id_muon="+idMuon;
        String sql1 = "SELECT so_luong FROM muon Where id="+idMuon;
        int choMuon, daTra;
        try {
            Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql); 
             rs.next();
             daTra = rs.getInt(1);
             rs = statement.executeQuery(sql1); 
             rs.next();
             choMuon = rs.getInt(1);
             return choMuon-daTra;
        } catch (Exception e) {
                System.err.println("fail muon");
                return 0;
        }     
    }
    public int getSoTienMuonDaTra(int idMuon){
        String sql = "SELECT SUM(so_tien) FROM tra Where id_muon="+idMuon;
//        String sql1 = "SELECT so_luong FROM muon Where id="+idMuon;
        int choMuon, daTra;
        try {
            Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql); 
             rs.next();
             daTra = rs.getInt(1);
//             rs = statement.executeQuery(sql1); 
//             rs.next();
//             choMuon = rs.getInt(1);
             return daTra;
        } catch (Exception e) {
                System.err.println("fail muon");
                return 0;
        }     
    }
}