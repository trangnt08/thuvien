/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import pac1.Sach;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pac1.SachNhap;
import util.DataUtil;

/**
 *
 * @author Thuy Trang Nguyen
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    DataUtil db;
    private Sach sach;
    private ArrayList<String> arrTheloai = new ArrayList<>();
    private ArrayList<String> arrNxb = new ArrayList<>();
    LinkedList<Sach> listSach = new LinkedList<>();
    private DefaultTableModel  tableModel;
    private String time;
    public Home() {
        db = new DataUtil();
        this.setAlwaysOnTop(true);
        initComponents();
        jPanel2.setBackground(Color.decode("#66cc66"));
        jPanel5.setBackground(Color.decode("#66cc66"));
        jPanel7.setBackground(Color.decode("#66cc66"));
        jPanel9.setBackground(Color.decode("#66cc66"));
        SimpleDateFormat formatTime = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String currenTime = formatTime.format(cal.getTime());
        time = currenTime;
        SimpleDateFormat gio = new SimpleDateFormat("HH:mm:ss aaaaa");
        Calendar calH = Calendar.getInstance();
        String currenH = gio.format(calH.getTime());
        lbTime.setText("Ngày "+time);
        lbGio.setText(currenH);
        
        String sqlTheloai = "SELECT * FROM theloai";
        try {
             ResultSet rs = db.executeQuery(sqlTheloai); 
             while(rs.next()){
                 cbTheLoai.addItem(rs.getString(2));
                 arrTheloai.add(rs.getString(2));
            }
             System.err.println("theloai");
            } catch (Exception e) {
                System.err.println("fail theloai");
        }
        String sqlNXB = "SELECT * FROM nhaxuatban";
        
        try {
             ResultSet rs = db.executeQuery(sqlNXB); 
             while(rs.next()){
                 cbNXB.addItem(rs.getString(2));
                 arrNxb.add(rs.getString(2));
            }
            } catch (Exception e) {
                System.err.println("fail nxb");
        }
        loadData();
    }

    // load data vào bảng
    public void loadData(){
        String sql = "SELECT * FROM book";
        
        Vector data = new Vector();
        Vector cols = new Vector();
        cols.addElement("Mã sách");
        cols.addElement("Tên sách");
        cols.addElement("Tác giả");
        cols.addElement("Thể loại");
        cols.addElement("NXB");
        cols.addElement("Năm XB");
        cols.addElement("Số trang");
        cols.addElement("Giá");
        cols.addElement("Số lượng");
        cols.addElement("Đã cho mượn");
        cols.addElement("Số sách còn");
        listSach = db.getListSach(sql);
        for(int i=0;i<listSach.size();i++){
            Vector row = new Vector();
            row.addElement(listSach.get(i).getMasach());
            row.addElement(listSach.get(i).getTensach());
            row.addElement(listSach.get(i).getTacgia());
            row.addElement(listSach.get(i).getTheloai());
            row.addElement(listSach.get(i).getNxb());
            row.addElement(listSach.get(i).getNamxb());
            row.addElement(listSach.get(i).getSotrang());
            row.addElement(listSach.get(i).getGiatien());
            row.addElement(listSach.get(i).getSoluong());
            row.addElement(listSach.get(i).getChomuon());
            row.addElement(listSach.get(i).getSosachcon());
            data.add(row);
        }
        tbSach.setModel(new DefaultTableModel(data,cols));
    }
    public void loadLichsuNhap(String sql){
        //String sql = "SELECT masach,tensach,ngaynhap,soluongnhap FROM book natural join nhapsach";
        
        Vector data = new Vector();
        Vector cols = new Vector();
        cols.addElement("Mã sách");
        cols.addElement("Tên sách");
        cols.addElement("Ngày nhập");
        cols.addElement("Số lượng");
        try {
            ResultSet result = db.executeQuery(sql); 
            while(result.next()){
                Vector row = new Vector();
                row.addElement(result.getString("masach"));
                row.addElement(result.getString("tensach"));
                row.addElement(result.getString("ngaynhap"));
                row.addElement(result.getString("soluongnhap"));
                
                data.add(row);
                System.out.println("row "+result.getString(2));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbNhap.setModel(new DefaultTableModel(data,cols));
    }
    
    public Sach getInforBook(){
        String masach = etMaso.getText().toUpperCase();
        String tensach;
        String tacgia ;
        int sotrang=0;
        String theloai,nxb,giatien,namxb;
        int nhapthem=0, chomuon, sosachcon, soluong;

        tensach = etTensach.getText().trim();
        tacgia = etTacgia.getText().trim();
        if(etSotrang.getText().trim().isEmpty()) sotrang = 0;
        else sotrang = Integer.parseInt(etSotrang.getText().trim());
        theloai = cbTheLoai.getSelectedItem().toString();
        nxb = cbNXB.getSelectedItem().toString();
        namxb = etNam.getText().trim();
        System.out.println(namxb);
        giatien = etGiatien.getText().trim();
        if(etNhapvao.getText().trim().isEmpty()) nhapthem=0;
        else nhapthem = Integer.parseInt(etNhapvao.getText());
        chomuon = 0;
        sosachcon = 0;
        soluong = nhapthem + chomuon + sosachcon;
        Sach sach = new Sach(masach, tensach, tacgia, theloai, nxb, namxb,sotrang, giatien, soluong, chomuon, sosachcon);
        sach.setNhapthem(nhapthem);
        System.out.println(sach.getSoluong()+" "+sach.getChomuon()+" "+sach.getSosachcon());
        return sach;
    }
    
    public void nhapThem(){
        Sach sach = getInforBook();
        String masach = sach.getMasach();
        String tensach = sach.getTensach();
        String tacgia = sach.getTacgia();
        String theloai = sach.getTheloai();
        String nxb = sach.getNxb();
        String namxb = sach.getNamxb();
        int sotrang = sach.getSotrang();
        String giatien = sach.getGiatien();
        int soluong = sach.getSoluong();
        int chomuon = sach.getChomuon();
        int sosachcon = sach.getSosachcon();
        int nhapthem = sach.getNhapthem();
        sosachcon = soluong - chomuon;
        
        SachNhap sachNhap = new SachNhap();
        sachNhap.setMasach(masach);
        sachNhap.setTensach(tensach);
        sachNhap.setNgaynhap(time);
        sachNhap.setNhapthem(nhapthem);
        if(masach.isEmpty()){
            JOptionPane.showMessageDialog(this, "Mã sach không được trống");
            return;
        }
        else{
            String sqlRow = "SELECT * FROM book where masach = '" + masach +"'";
            Sach book = db.getSach(sqlRow);
            if(book==null){
                if(tensach.isEmpty() || tacgia.isEmpty() || (namxb.isEmpty())|| (sotrang == 0) || giatien.isEmpty() || (nhapthem==0)){
                    JOptionPane.showMessageDialog(this, "Hãy nhập đủ thông tin vào các mục");
                    return;
                }
                else{
                String sql = "INSERT INTO book(masach,tensach,tacgia,sotrang,theloai,nxb,namxb,giatien,soluong,chomuon,sosachcon) values('"+masach+"',N'"+tensach+"',N'"+tacgia+"','"+sotrang+"',N'"+theloai+"',N'"+nxb+"','"+namxb+"','"+giatien+"','"+soluong+"','"+chomuon+"','"+sosachcon+"')";
                 System.out.println(sql);        
                String sql1 = "INSERT INTO nhapsach(masach,ngaynhap,soluongnhap) values('"+sachNhap.getMasach()+"','"+sachNhap.getNgaynhap()+"','"+sachNhap.getNhapthem()+"'"+")";
                try {
                    int a = db.executeUpdate(sql);
                    int b = db.executeUpdate(sql1);
                    JOptionPane.showMessageDialog(this, "Đã hêm sách");
                    loadData();
                    } catch (Exception e) {
                        System.out.println("loi roi :(");
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Mã sách "+sach.getMasach()+" đã tồn tại\nVui lòng nhập lại");
            }
        }
    }
    
    public void sua(){
        Sach sach = getInforBook();
        String masach = sach.getMasach();
        String sqlRow = "SELECT * FROM book where masach = '" + masach +"'";
        Sach book = db.getSach(sqlRow);
        if(sach==null || masach.isEmpty() || book == null){
            JOptionPane.showMessageDialog(this, "Hãy kiểm tra lại ma sach");
        }
        else{
            if(sach.getTensach().isEmpty()) sach.setTensach(book.getTensach());
            if(sach.getTacgia().isEmpty()) sach.setTacgia(book.getTacgia());
            if(sach.getNamxb().isEmpty()) sach.setNamxb(book.getNamxb());
            if(sach.getSotrang() == 0) sach.setSotrang(book.getSotrang());
            if(sach.getGiatien().isEmpty()) sach.setGiatien(book.getGiatien());
            if(sach.getNamxb().isEmpty()) sach.setNamxb(book.getNamxb()); 
            int nhapthem;
            if(etNhapvao.getText().trim().isEmpty()) nhapthem = 0;
            else nhapthem = Integer.parseInt(etNhapvao.getText().trim());
            int chomuon = book.getChomuon();
            int sosachcon = book.getSosachcon();
            int soluong = nhapthem+chomuon+sosachcon;
            sosachcon = soluong - chomuon;
            sach.setChomuon(chomuon)
                    .setSosachcon(sosachcon)
                    .setSoluong(soluong);
            String sql = "UPDATE book set tensach = N'"+sach.getTensach()+"', tacgia=N'"+sach.getTacgia()+"',sotrang="+sach.getSotrang()+",theloai=N'"+sach.getTheloai()+"',nxb=N'"+sach.getNxb()+"',namxb='"+sach.getNamxb()+"',giatien='"+sach.getGiatien()+"',soluong="+sach.getSoluong()+",chomuon="+sach.getChomuon()+",sosachcon="+sach.getSosachcon() +" where masach = '"+sach.getMasach()+"'";
            SachNhap sachNhap = new SachNhap();
            sachNhap.setMasach(masach);
            sachNhap.setTensach(sach.getTensach());
            sachNhap.setNgaynhap(time);
            sachNhap.setNhapthem(nhapthem);
            String sql1 = "INSERT INTO nhapsach(masach,ngaynhap,soluongnhap) values('"+sachNhap.getMasach()+"','"+sachNhap.getNgaynhap()+"','"+sachNhap.getNhapthem()+"'"+")";      
            try {
                int a = db.executeUpdate(sql);
                int b;
                if(sachNhap.getNhapthem()!=0)
                    b = db.executeUpdate(sql1);
                JOptionPane.showMessageDialog(this, "Xong");
                loadData();
            } catch (Exception e) {
                System.out.println("loi roi :(");
            }
        }
    }
    
    public void xoa(){
        Sach sach = getInforBook();
        String masach = sach.getMasach();
        String sqlRow = "SELECT * FROM book where masach = '" + masach +"'";
        Sach book = db.getSach(sqlRow);
        if(sach==null || masach.isEmpty()){
            JOptionPane.showMessageDialog(this, "Hãy kiểm tra lại ma sach");
        }
        else{
            String sql = "DELETE FROM book WHERE masach = '"+masach+"'";
            try {
                int a = db.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Đã xóa");
                loadData();
            } catch (Exception e) {
                System.out.println("loi roi :(");
           }
        }
    }
    public void searchLs(){
        String ngayNhap = jTextField1.getText().trim();
        if(ngayNhap.isEmpty()){
            JOptionPane.showMessageDialog(this, "Hãy nhập ngày để tìm");
        }else{
            try {
                String sql = "SELECT masach,tensach,ngaynhap,soluongnhap FROM book natural join nhapsach WHERE ngaynhap = '" +ngayNhap +"'";
                loadLichsuNhap(sql);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy.\nKiểm tra lại ngày nhập");
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        etMaso = new javax.swing.JTextField();
        etTensach = new javax.swing.JTextField();
        etTacgia = new javax.swing.JTextField();
        cbTheLoai = new javax.swing.JComboBox<>();
        cbNXB = new javax.swing.JComboBox<>();
        etGiatien = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        etNhapvao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSach = new javax.swing.JTable();
        etSotrang = new javax.swing.JTextField();
        btClean = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        etNam = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btXoa = new javax.swing.JButton();
        btSua = new javax.swing.JButton();
        btThem = new javax.swing.JButton();
        btLsNhap = new javax.swing.JButton();
        btsachdaco = new javax.swing.JButton();
        lbTime = new javax.swing.JLabel();
        lbGio = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btBack = new javax.swing.JButton();
        btLs = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbNhap = new javax.swing.JTable();
        btTimLs = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        meQuanlisach = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        meQuanlinguoidung = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        meQuanlimuon = new javax.swing.JMenuItem();
        meQuanlitra = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem6.setText("jMenuItem6");

        jMenuItem7.setText("jMenuItem7");

        jMenu3.setText("jMenu3");

        jMenu7.setText("jMenu7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.CardLayout());

        label.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        label.setText("Nhập thông tin sách");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("QUẢN LÍ SÁCH");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Mã số sách");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Tên sách");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Tác giả");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Nhà XB");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Thể loại");

        etGiatien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etGiatienActionPerformed(evt);
            }
        });

        jLabel7.setText("Giá tiền");

        jLabel8.setText("Số nhập vào");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Số trang");

        tbSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Loại sách", "NXB", "Năm XB", "Giá ", "Số lượng", "Đã cho mượn", "Số sách còn"
            }
        ));
        tbSach.setRowHeight(22);
        tbSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSachMouseClicked(evt);
            }
        });
        tbSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSachKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbSach);

        btClean.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btClean.setText("Clean");
        btClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCleanActionPerformed(evt);
            }
        });

        jLabel10.setText("Năm XB");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 978, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(etTacgia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etTensach, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etMaso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(etGiatien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(etSotrang, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(etNhapvao, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(etNam, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(btClean, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbNXB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etMaso, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etSotrang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etTensach, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etGiatien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etTacgia, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addComponent(etNhapvao, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btClean, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etNam, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        btXoa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btXoa.setText("Xóa sách");
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
            }
        });

        btSua.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btSua.setText("Sửa  ");
        btSua.setActionCommand("Sửa /\nThêm sách đã có "); // NOI18N
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        btThem.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btThem.setText("Thêm sách mới");
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        btLsNhap.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btLsNhap.setText("Lịch sử nhập");
        btLsNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLsNhapActionPerformed(evt);
            }
        });

        btsachdaco.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btsachdaco.setText("Thêm sách đã có");
        btsachdaco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsachdacoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btsachdaco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btLsNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btsachdaco, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btLsNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbTime.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTime.setText("all hsh");

        lbGio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbGio.setText("jLabel13");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbGio, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addGap(47, 47, 47))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbGio, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel4, "card2");

        btBack.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btBack.setText("Quay lại");
        btBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBackActionPerformed(evt);
            }
        });

        btLs.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btLs.setText("Lịch sử nhập");
        btLs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btLs, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(btBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(btBack, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btLs, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(338, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("QUẢN LÍ SÁCH");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Lịch sử nhập sách");

        tbNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbNhap);

        btTimLs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/images.png"))); // NOI18N
        btTimLs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimLsActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Nhập ngày");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btTimLs, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btTimLs, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(358, 358, 358)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );

        jPanel1.add(jPanel6, "card3");

        jMenu4.setText("Home ");
        jMenuBar1.add(jMenu4);

        jMenu1.setText("Quản lý sách");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        meQuanlisach.setText("Quản lí sách");
        meQuanlisach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meQuanlisachActionPerformed(evt);
            }
        });
        jMenu1.add(meQuanlisach);

        jMenuBar1.add(jMenu1);

        jMenu6.setText("Quản lý người dùng");

        meQuanlinguoidung.setText("Quản lí người dùng");
        meQuanlinguoidung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meQuanlinguoidungActionPerformed(evt);
            }
        });
        jMenu6.add(meQuanlinguoidung);

        jMenuBar1.add(jMenu6);

        jMenu5.setText("Quản lý mượn trả");

        meQuanlimuon.setText("Quản lí mượn");
        meQuanlimuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meQuanlimuonActionPerformed(evt);
            }
        });
        jMenu5.add(meQuanlimuon);

        meQuanlitra.setText("Quản lí trả");
        meQuanlitra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meQuanlitraActionPerformed(evt);
            }
        });
        jMenu5.add(meQuanlitra);

        jMenuItem5.setText("Lịch sử");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuBar1.add(jMenu5);

        jMenu2.setText("Quản lý thể loại");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu8.setText("Quản lý NXB");
        jMenu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu8MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void meQuanlisachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meQuanlisachActionPerformed
        // TODO add your handling code here:
      jPanel1.removeAll();
      jPanel1.repaint();
      jPanel1.revalidate();
      
      jPanel1.add(jPanel4);
      jPanel1.repaint();
      jPanel1.revalidate();
    }//GEN-LAST:event_meQuanlisachActionPerformed

    private void meQuanlinguoidungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meQuanlinguoidungActionPerformed
        // TODO add your handling code here:
        QuanliNguoidung panUser = new QuanliNguoidung();
        jPanel1.removeAll();
      jPanel1.repaint();
      jPanel1.revalidate();
      
      jPanel1.add(panUser);
      jPanel1.repaint();
      jPanel1.revalidate();
//        
    }//GEN-LAST:event_meQuanlinguoidungActionPerformed

    private void etGiatienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etGiatienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etGiatienActionPerformed

    private void btCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCleanActionPerformed
        clean();
    }//GEN-LAST:event_btCleanActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        // TODO add your handling code here:
        int click = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?");
        if (click==JOptionPane.YES_OPTION) {
            xoa();
        }
    }//GEN-LAST:event_btXoaActionPerformed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        // TODO add your handling code here:
        sua();
    }//GEN-LAST:event_btSuaActionPerformed

    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        // TODO add your handling code here:
        nhapThem();
    }//GEN-LAST:event_btThemActionPerformed

    private void btLsNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLsNhapActionPerformed
        // TODO add your handling code here:
        jPanel1.removeAll();
        jPanel1.repaint();
        jPanel1.revalidate();
      
        jPanel1.add(jPanel6);
        jPanel1.repaint();
        jPanel1.revalidate();
        loadLichsuNhap("SELECT masach,tensach,ngaynhap,soluongnhap FROM book natural join nhapsach");
    }//GEN-LAST:event_btLsNhapActionPerformed

    private void btBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBackActionPerformed
        // TODO add your handling code here:
        jPanel1.removeAll();
      jPanel1.repaint();
      jPanel1.revalidate();
      
      jPanel1.add(jPanel4);
      jPanel1.repaint();
      jPanel1.revalidate();
    }//GEN-LAST:event_btBackActionPerformed

    public void select(){
        int i = tbSach.getSelectedRow();
        Sach sach1 = listSach.get(i);
        etMaso.setText(sach1.getMasach());
        etTensach.setText(sach1.getTensach());
        etTacgia.setText(sach1.getTacgia());
        etGiatien.setText(sach1.getGiatien());
        etSotrang.setText(sach1.getSotrang()+"");
        etNam.setText(sach1.getNamxb());
        int a=0,b=0;
        String c=sach1.getTheloai();
        String d = arrTheloai.get(a);
        if(c.equals(d)){
                cbTheLoai.setSelectedIndex(a);
        }else {
        while(!c.equals(d) && a<arrTheloai.size()){
            d = arrTheloai.get(a);
            a++;
        }
        a--;
        cbTheLoai.setSelectedIndex(a);
        }

        
        String e=sach1.getNxb();
        String f = arrNxb.get(b);
        if(e.equals(f)){
                cbNXB.setSelectedIndex(b);
        }else {
        while(!e.equals(f) && b<arrNxb.size()){
            f = arrNxb.get(b);
            b++;
        }
        b--;
        cbNXB.setSelectedIndex(b);
        }
    }
    public void clean(){
        etMaso.setText("");
        etTensach.setText("");
        etTacgia.setText("");
        etSotrang.setText("");
        etGiatien.setText("");
        etNam.setText("");
    }
    private void tbSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSachMouseClicked
        // TODO add your handling code here:
        select();
    }//GEN-LAST:event_tbSachMouseClicked

    private void tbSachKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSachKeyPressed
        // TODO add your handling code here:
        select();
    }//GEN-LAST:event_tbSachKeyPressed

    private void btsachdacoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsachdacoActionPerformed
        label.setText("Nhập số lướng sách muốn thêm vào ");
        if(etNhapvao.getText().isEmpty() || Integer.parseInt(etNhapvao.getText().trim())==0){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số sách nhập vào");
        }else
        sua();
    }//GEN-LAST:event_btsachdacoActionPerformed

    private void meQuanlimuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meQuanlimuonActionPerformed
        // TODO add your handling code here:
        Muon jp = new Muon();
      jPanel1.removeAll();
      jPanel1.repaint();
      jPanel1.revalidate();
      
      jPanel1.add(jp);
      jPanel1.repaint();
      jPanel1.revalidate();
    }//GEN-LAST:event_meQuanlimuonActionPerformed

    private void meQuanlitraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meQuanlitraActionPerformed
        // TODO add your handling code here:
        Tra jp = new Tra();
      jPanel1.removeAll();
      jPanel1.repaint();
      jPanel1.revalidate();
      
      jPanel1.add(jp);
      jPanel1.repaint();
      jPanel1.revalidate();
    }//GEN-LAST:event_meQuanlitraActionPerformed

    private void btLsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLsActionPerformed
        loadLichsuNhap("SELECT masach,tensach,ngaynhap,soluongnhap FROM book natural join nhapsach");
    }//GEN-LAST:event_btLsActionPerformed

    private void btTimLsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimLsActionPerformed
        // TODO add your handling code here:
        searchLs();
    }//GEN-LAST:event_btTimLsActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        LichSu jp = new LichSu();
      jPanel1.removeAll();
      jPanel1.repaint();
      jPanel1.revalidate();
      
      jPanel1.add(jp);
      jPanel1.repaint();
      jPanel1.revalidate();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu8MouseClicked
        // TODO add your handling code here:
        QLNXB tl = new QLNXB();
        jPanel1.removeAll();
      jPanel1.repaint();
      jPanel1.revalidate();
      
      jPanel1.add(tl);
      jPanel1.repaint();
      jPanel1.revalidate();
    }//GEN-LAST:event_jMenu8MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
         QLTheLoai tl = new QLTheLoai();
        jPanel1.removeAll();
      jPanel1.repaint();
      jPanel1.revalidate();
      
      jPanel1.add(tl);
      jPanel1.repaint();
      jPanel1.revalidate();
    }//GEN-LAST:event_jMenu2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBack;
    private javax.swing.JButton btClean;
    private javax.swing.JButton btLs;
    private javax.swing.JButton btLsNhap;
    private javax.swing.JButton btSua;
    private javax.swing.JButton btThem;
    private javax.swing.JButton btTimLs;
    private javax.swing.JButton btXoa;
    private javax.swing.JButton btsachdaco;
    private javax.swing.JComboBox<String> cbNXB;
    private javax.swing.JComboBox<String> cbTheLoai;
    private javax.swing.JTextField etGiatien;
    private javax.swing.JTextField etMaso;
    private javax.swing.JTextField etNam;
    private javax.swing.JTextField etNhapvao;
    private javax.swing.JTextField etSotrang;
    private javax.swing.JTextField etTacgia;
    private javax.swing.JTextField etTensach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lbGio;
    private javax.swing.JLabel lbTime;
    private javax.swing.JMenuItem meQuanlimuon;
    private javax.swing.JMenuItem meQuanlinguoidung;
    private javax.swing.JMenuItem meQuanlisach;
    private javax.swing.JMenuItem meQuanlitra;
    private javax.swing.JTable tbNhap;
    private javax.swing.JTable tbSach;
    // End of variables declaration//GEN-END:variables
}
