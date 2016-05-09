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
public class Sach {
    int id;
    String masach;
    String tensach;
    String tacgia;
    int sotrang;
    String theloai;
    String nxb;
    String namxb;
    String giatien;
    int soluong;
    int nhapthem;
    int chomuon;
    int sosachcon;
//    private int id;
//    private String masach;
//    private String tensach;
//    private String tacgia;
//    private int sotrang;
//    private String theloai;
//    private String nxb;
//    private String namxb;
//    private String giatien;
//    private int soluong;
//    private int nhapthem;
//    private int chomuon;
//    private int sosachcon;

    public Sach(){
        
    }
    public Sach(String ma,String ten, String tg,String tloai,String xb, String nam,int trang,String gia,int tong,int muon, int con){
        masach = ma;
        tensach = ten;
        tacgia = tg;
        theloai = tloai;
        nxb = xb;
        namxb = nam;
        sotrang = trang;
        giatien = gia;
        soluong = tong;
        chomuon = muon;
        sosachcon = con;
    }
    public int getId() {
        return id;
    }

    public Sach setId(int id) {
        this.id = id;
        return this;
    }

    public String getMasach() {
        return masach;
    }

    public Sach setMasach(String masach) {
        this.masach = masach;
        return this;
    }

    public String getTensach() {
        return tensach;
    }

    public Sach setTensach(String tensach) {
        this.tensach = tensach;
        return this;
    }

    public String getTacgia() {
        return tacgia;
    }

    public Sach setTacgia(String tacgia) {
        this.tacgia = tacgia;
        return this;
    }

    public int getSotrang() {
        return sotrang;
    }

    public Sach setSotrang(int sotrang) {
        this.sotrang = sotrang;
        return this;
    }

    public String getTheloai() {
        return theloai;
    }

    public Sach setTheloai(String theloai) {
        this.theloai = theloai;
        return this;
    }

    public String getNxb() {
        return nxb;
    }

    public Sach setNxb(String nxb) {
        this.nxb = nxb;
        return this;
    }

    public String getNamxb() {
        return namxb;
    }

    public Sach setNamxb(String namxb) {
        this.namxb = namxb;
        return this;
    }
    

    public String getGiatien() {
        return giatien;
    }

    public Sach setGiatien(String giatien) {
        this.giatien = giatien;
        return this;
    }

    
    public int getSoluong() {
        return soluong;
    }

    public Sach setSoluong(int soluong) {
        this.soluong = soluong;
        return this;
    }

    public int getNhapthem() {
        return nhapthem;
    }

    public Sach setNhapthem(int nhapthem) {
        this.nhapthem = nhapthem;
        return this;
    }

    public int getChomuon() {
        return chomuon;
    }

    public Sach setChomuon(int chomuon) {
        this.chomuon = chomuon;
        return this;
    }

    public int getSosachcon() {
        return sosachcon;
    }

    public Sach setSosachcon(int sosachcon) {
        this.sosachcon = sosachcon;
        return this;
    }

    Object setSoluong(String soluong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
