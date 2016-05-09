/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pac1.Sach;
import pac1.User;
import util.DataUtil;

/**
 *
 * @author Thuy Trang Nguyen
 */
public class QuanliNguoidung extends javax.swing.JPanel {

    private DataUtil db;
    private int check;
    
    private LinkedList<User> users = new LinkedList<User>();
    
    public QuanliNguoidung() {
        initComponents();
        jPanel3.setBackground(Color.decode("#66cc66"));
        jPanel2.setBackground(Color.decode("#66cc66"));
        jPanel6.setBackground(Color.decode("#66cc66"));
        jPanel7.setBackground(Color.decode("#66cc66"));
        check = 1;
        etMa.setEditable(false);
        etMa.setBackground(Color.DARK_GRAY);
        db = new DataUtil();
        loadUsers();
        
    }

    public void themNguoidung(){
        String mssv = etMSSV.getText().trim();
        String ten = etTen.getText().trim();
        if((mssv.isEmpty())||(ten.isEmpty())){
            JOptionPane.showMessageDialog(this, "Hãy nhập đủ thông tin");
            return;
        }
        
        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String currenTime = formatTime.format(cal.getTime());
        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String ngaysinh = "";
        try {
            ngaysinh = format1.format(dNgaysinh.getCalendar().getTime());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập ngày sinh");
        }
        
        String sql = "SELECT * FROM user WHERE mssv = '"+mssv+"'";
        User user = db.getUser(sql);
        if(user == null){
            String sql1 = "INSERT INTO user (name,mssv,ngaysinh,ngaydangki) values(N'"+ten+"',N'"+mssv+"',N'"+ngaysinh+"','"+currenTime+"')";
            try {
                int a = db.executeUpdate(sql1);
            } catch (Exception e) {
            }
            User u = db.getUser(sql);
            try {
                int b = db.executeUpdate("UPDATE user set user_id = '"+u.getManguoidung()+"' WHERE mssv = "+mssv);
                JOptionPane.showMessageDialog(this, "Đã thêm");
            } catch (Exception e) {
            }
        }else{
            JOptionPane.showMessageDialog(this, mssv+" đã tồn tại\nHãy nhập lại");
        } 
        loadUsers();
    }
    
    public void sua(){
        User u1 = getInfoUser();
        if(u1!=null){
        String ma = u1.getManguoidung();
        System.out.println(ma);
        String sqlRow = "SELECT * FROM user where user_id = '" + ma +"'";
        User u2 = db.getUser(sqlRow);
        if(u2==null || ma.isEmpty()){
            JOptionPane.showMessageDialog(this, "Hãy kiểm tra lại mã người dùng");
        }else{
            if(u1.getMssv().equals(u2.getMssv()) && u1.getTennguoidung().equalsIgnoreCase(u2.getTennguoidung()) && u1.getNgaysinh().equals(u2.getNgaysinh())){
                JOptionPane.showMessageDialog(this, "Bạn chưa thay đổi thông tin");
            }else{
            if(u1.getManguoidung().isEmpty()) u1.setTennguoidung(u2.getTennguoidung());
            if(u1.getMssv().isEmpty()) u1.setMssv(u2.getMssv());
            if(u1.getNgaysinh().isEmpty()) u1.setNgaysinh(u2.getNgaysinh());
            u1.setNgaydangki(u2.getNgaydangki());
            String sql = "UPDATE user set user_id = N'"+u1.getManguoidung()+"', name=N'"+u1.getTennguoidung()+"',mssv="+u1.getMssv()+",ngaysinh='"+u1.getNgaysinh()+"',ngaydangki='"+u1.getNgaydangki()+"' where user_id = '"+u1.getManguoidung()+"'";    
            try {
                int a= db.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Đã sửa");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thất bại");
            }
            }
        }
        }else{
            JOptionPane.showMessageDialog(this, "Hãy kiểm tra lại mã người dùng");
        }
        loadUsers();
    }
    
    public void xoa(){
        User u = getInfoUser();
        if(u==null || u.getManguoidung().isEmpty()){
            JOptionPane.showMessageDialog(this, "Hãy kiểm tra lại mã người dùng");
        }
        else{
            try {
                String ma = u.getManguoidung();
                String sql1 = "SELECT trang_thai FROM muon WHERE id_user = '"+ma+"'";
                System.out.println(sql1);
                ResultSet rs = db.executeQuery(sql1);
                while(rs.next()){
                rs.next();
                if(rs.getInt("trang_thai")==0){
                    JOptionPane.showMessageDialog(this, "Người dùng hiện đang mượn sách\nKhông thể xóa");
                    return;
                }
                }
                String sql = "DELETE FROM user WHERE user_id = '"+ma+"'";
                try {
                    db.executeUpdate(sql);
                    JOptionPane.showMessageDialog(this, "Đã xóa");
                    loadUsers();
                } catch (Exception e) {
                    System.out.println("loi roi :(");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(QuanliNguoidung.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        }
    }
    public User getInfoUser(){
        //int i = tbU1.getSelectedRow();
        String id,ten,mssv,ngaysinh,ngaydangki;
        id = etMa.getText().trim();
        ten = etTen.getText().trim();
        mssv = etMSSV.getText().trim();
        if(mssv.isEmpty()){
            return null;
        }else{
        User u = db.getUser("SELECT * FROM user WHERE user_id = '"+id+"'");
        if(u!=null){
            ngaydangki = u.getNgaydangki();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            ngaysinh = format.format(dNgaysinh.getCalendar().getTime());
            User u1 = new User(id, ten, mssv, ngaysinh, ngaydangki);
            return u1;
        }else{
            return null;
        }
        }
    }
    
    public void select(){
        int i = tbU1.getSelectedRow();
        User u = users.get(i);
        etMa.setEditable(true);
        etMa.setBackground(Color.WHITE);
        etMa.setText(u.getManguoidung());
        etTen.setText(u.getTennguoidung());
        etMSSV.setText(u.getMssv());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date dateValue = null;
        try {
            dateValue = date.parse(u.getNgaysinh());
        } catch (ParseException ex) {
          
        }
        dNgaysinh.setDate(dateValue); 
    }
    public void loadUsers(){
        String sql = "SELECT * FROM user";
        
        Vector data = new Vector();
        Vector cols = new Vector();
        cols.addElement("Mã người dùng");
        cols.addElement("Họ tên");
        cols.addElement("MSSV");
        cols.addElement("Ngày sinh");
        cols.addElement("Ngày đăng kí");
        cols.addElement("Trạng thái");
        users = db.getListUser(sql);
        System.out.println("size = "+users.size());
        for(int i=0;i<users.size();i++){
            Vector row = new Vector();
            row.addElement(users.get(i).getManguoidung());
            System.out.println(users.get(i).getTennguoidung());
            row.addElement(users.get(i).getTennguoidung());
            row.addElement(users.get(i).getMssv());
            row.addElement(users.get(i).getNgaysinh());
            row.addElement(users.get(i).getNgaydangki());
            row.addElement(users.get(i).getTrangthai());
            data.add(row);
        }
        tbU1.setModel(new DefaultTableModel(data,cols));
    }
    
    public void loadDetailUser(String mssv){
        if(mssv.isEmpty()){
            JOptionPane.showMessageDialog(this, "Hãy kiểm tra lại MSSV");
            return;
        }
        else{
            String sql = "SELECT * FROM user WHERE mssv = '"+mssv+"'";
            jPanel8.removeAll();
            jPanel8.repaint();
            jPanel8.revalidate();
            jPanel8.add(jPanel4);
            jPanel8.repaint();
            jPanel8.revalidate();
            
            User user = db.getUser(sql);
                
            if(user==null){
                JOptionPane.showMessageDialog(this, "Không tồn tại");
                return;
            }else{
                String ma = user.getManguoidung();
                String sqlRow = "SELECT * FROM muon,tra where id_user = '" + ma +"' AND id_muon = muon.id";
                String sql2 = "SELECT * FROM muon WHERE id_user = '" + ma +"' AND muon.id NOT IN(SELECT id_muon FROM tra)";
                jLabel8.setText("Mã người dùng: "+user.getManguoidung());
                jLabel9.setText("Họ tên: "+user.getTennguoidung());
                jLabel10.setText("MSSV: "+user.getMssv());
                jLabel11.setText("Ngày sinh: "+user.getNgaysinh());
                jLabel12.setText("Ngày đăng kí: "+user.getNgaydangki());
                Vector data = new Vector();
                Vector cols = new Vector();
                cols.addElement("STT");
                cols.addElement("Mã sách");
            //cols.addElement("Tên sách");
                cols.addElement("Số còn mượn");
                cols.addElement("Ngày mượn");
                cols.addElement("Ngày trả");
                cols.addElement("Số lượng trả");
                cols.addElement("Tiền cọc");
                cols.addElement("Tiền phải trả");
                cols.addElement("Trạng thái");
           
                try {
                    int i=1;
                    ResultSet rs = db.executeQuery(sqlRow);
                    while(rs.next()){
                    int soluong = rs.getInt("muon.so_luong"); 
                    int con = soluong;
                    int datra = rs.getInt("tra.so_luong");
                    Vector row = new Vector();
                    row.addElement(i);
                    row.addElement(rs.getString("ma_sach"));
                //row.addElement(rs.getString(""));
                    row.addElement(db.getSoLuongSachConMuon(rs.getInt("muon.id")));
                    row.addElement(rs.getString("ngay_muon"));
                    row.addElement(rs.getString("ngay_tra"));
                    row.addElement(rs.getString("tra.so_luong"));
                    row.addElement(rs.getString("tien_coc"));
                    row.addElement(rs.getString("so_tien"));
                    int t = rs.getInt("trang_thai");
                    if(t==1) row.addElement("Đã trả hết");
                    else row.addElement("Chưa trả hết");
                //row.addElement(rs.getString(""));
                //row.addElement(rs.getString(""));
                    data.add(row);
                    con = con -datra;
                    i++;
                }
                    rs = db.executeQuery(sql2);
                    while(rs.next()){
                        System.out.println(sql2);
                        Vector row = new Vector();
                        row.addElement(i);
                        row.addElement(rs.getString("ma_sach"));
                        row.addElement(rs.getInt("so_luong"));
                        row.addElement(rs.getString("ngay_muon"));
                        row.addElement("Chưa trả");
                        row.addElement(0);
                        row.addElement(rs.getString("tien_coc"));
                        row.addElement("Chưa tính");
                        row.addElement("Chưa trả hết");
                        data.add(row);
                        i++;
                    }
                } catch (Exception e) {
                }
                tbUsers.setModel(new DefaultTableModel(data,cols));
            }
        }
    }
    public void clean(){
        etMSSV.setText("");
        etTen.setText("");
        etMa.setText("");
        dNgaysinh.setCalendar(Calendar.getInstance());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btThem = new javax.swing.JButton();
        btSua = new javax.swing.JButton();
        btXoa = new javax.swing.JButton();
        btXem = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        etMa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        etTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        etMSSV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbU1 = new javax.swing.JTable();
        btClean2 = new javax.swing.JButton();
        dNgaysinh = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        teSearch2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbUsers = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btBack = new javax.swing.JButton();

        jPanel8.setLayout(new java.awt.CardLayout());

        btThem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btThem.setText("Đăng kí thành viên");
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        btSua.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btSua.setText("Sửa thông tin");
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        btXoa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btXoa.setText("Xóa");
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
            }
        });

        btXem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btXem.setText("Xem thông tin");
        btXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btXem, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(btXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(btSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btThem, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btXem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Mã người dùng");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Tên người dùng");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("MSSV");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Ngày sinh");

        tbU1.setModel(new javax.swing.table.DefaultTableModel(
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
        tbU1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbU1MouseClicked(evt);
            }
        });
        tbU1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbU1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbU1);

        btClean2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btClean2.setText("Clean");
        btClean2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClean2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etMSSV, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(etMa))
                        .addGap(206, 206, 206)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etTen, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(dNgaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btClean2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etMa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etTen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dNgaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btClean2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("QUẢN LÍ NGƯỜI DÙNG");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Thêm người dùng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(58, 58, 58)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(420, 420, 420))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel8.add(jPanel1, "card2");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("QUẢN LÍ NGƯỜI DÙNG");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(228, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/images.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tbUsers.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbUsers);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Mã người dùng");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Họ tên");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("MSSV");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Ngày sinh");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Ngày đăng kí");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(teSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                        .addGap(138, 138, 138)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teSearch2))
                .addGap(12, 12, 12)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        btBack.setText("Quay lại");
        btBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btBack, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btBack, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel8.add(jPanel4, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1253, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        // TODO add your handling code here:
        etMa.setEditable(false);
        etMa.setBackground(Color.DARK_GRAY);
        themNguoidung();
        loadUsers();
    }//GEN-LAST:event_btThemActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        // TODO add your handling code here:
        etMa.setEditable(true);
        etMa.setBackground(Color.WHITE);
        int click = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?");
        if (click==JOptionPane.YES_OPTION) {
            xoa();
        }
    }//GEN-LAST:event_btXoaActionPerformed
    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        sua();
        
    }//GEN-LAST:event_btSuaActionPerformed

    private void btXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXemActionPerformed
        // TODO add your handling code here:
        loadDetailUser(etMSSV.getText().trim());
    }//GEN-LAST:event_btXemActionPerformed

    private void tbU1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbU1KeyPressed
        select();
    }//GEN-LAST:event_tbU1KeyPressed

    private void btBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBackActionPerformed
        jPanel8.removeAll();
        jPanel8.repaint();
        jPanel8.revalidate();
        jPanel8.add(jPanel1);
        jPanel8.repaint();
        jPanel8.revalidate();
        loadUsers();
    }//GEN-LAST:event_btBackActionPerformed

    private void tbU1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbU1MouseClicked
        select();
    }//GEN-LAST:event_tbU1MouseClicked

    private void btClean2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClean2ActionPerformed
        clean();
    }//GEN-LAST:event_btClean2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String se = teSearch2.getText().trim();
        loadDetailUser(se);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBack;
    private javax.swing.JButton btClean2;
    private javax.swing.JButton btSua;
    private javax.swing.JButton btThem;
    private javax.swing.JButton btXem;
    private javax.swing.JButton btXoa;
    private com.toedter.calendar.JDateChooser dNgaysinh;
    private javax.swing.JTextField etMSSV;
    private javax.swing.JTextField etMa;
    private javax.swing.JTextField etTen;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbU1;
    private javax.swing.JTable tbUsers;
    private javax.swing.JTextField teSearch2;
    // End of variables declaration//GEN-END:variables
}
