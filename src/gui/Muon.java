/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.HeadlessException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.DataUtil;

/**
 *
 * @author sony
 */
public class Muon extends MyPanel {
    /**
     * Creates new form muon
     */
    public Muon() {
        super();
        initComponents();
        loadData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        etMaso = new javax.swing.JTextField();
        etTensach = new javax.swing.JTextField();
        etMathanhvien = new javax.swing.JTextField();
        etSoluong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSach = new javax.swing.JTable();
        etTennguoimuon = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Ngaymuon = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        etTiencoc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        etTongtien = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Ngaytra = new com.toedter.calendar.JDateChooser();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Quản lý đơn mượn");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Mã số sách");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Tên sách");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Mã thành viên");

        etMaso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                etMasoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etMasoKeyReleased(evt);
            }
        });

        etTensach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etTensachKeyReleased(evt);
            }
        });

        etMathanhvien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etMathanhvienKeyReleased(evt);
            }
        });

        etSoluong.setText("1");
        etSoluong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etSoluongKeyReleased(evt);
            }
        });

        jLabel7.setText("Số lượng");

        jLabel8.setText("Ngày mượn");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Tên người mượn");

        tbSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Loại sách", "NXB", "Số lượng", "Đã cho mượn", "Số sách còn"
            }
        ));
        jScrollPane1.setViewportView(tbSach);

        etTennguoimuon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etTennguoimuonKeyReleased(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Ngaymuon.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                NgaymuonPropertyChange(evt);
            }
        });

        jLabel5.setText("Tiền đặt cọc/1 quyển");

        etTiencoc.setText("50000");
        etTiencoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etTiencocKeyReleased(evt);
            }
        });

        jLabel6.setText("Tổng tiền");

        etTongtien.setText("50000");

        jLabel10.setText("Ngày trả dự kiến");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etMaso)
                            .addComponent(etTennguoimuon, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                            .addComponent(etTensach)
                            .addComponent(etMathanhvien))
                        .addGap(151, 151, 151)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Ngaytra, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(etSoluong, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(Ngaymuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(etTiencoc, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etTongtien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(537, 537, 537)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(506, 506, 506)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Ngaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etMaso, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Ngaytra, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(etTensach, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etMathanhvien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etTennguoimuon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(etTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(etTiencoc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int check =0, tongsach=0,sosachcon=0, soLuongMuon = 0,soluongsau=0;
        String masach = etMaso.getText().toUpperCase();
        String mathanhvien = etMathanhvien.getText().toUpperCase();
        
        //format định dạng ngày
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String ngaymuon,ngaytra;
        int soluong, tiencoc,tiencoctong;
        try{
            ngaymuon = format1.format(Ngaymuon.getCalendar().getTime());
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(this, "Bạn phải nhập ngày mượn");
            return;
        }
            ngaytra =  format1.format(Ngaytra.getCalendar().getTime());
//            ngaytra = format1.format(Ngaytra.getCalendar().getTime());
        
          //Kiểm tra đầu vào ma sach va user
        if(getSach(masach)!=null){
            check ++;
            tongsach = getSach(masach).getSoluong();
            sosachcon = getSach(masach).getSosachcon();
            System.out.print(sosachcon);
            soLuongMuon = getSach(masach).getChomuon();
        } 
        //check soluong phải là kiểu số và không âm
            try{
                soluong = Integer.parseInt(etSoluong.getText());
                if(!checkSoLuong(soluong))
                    return;
            }catch(NumberFormatException | HeadlessException e){
                JOptionPane.showMessageDialog(this, "Định dạng số lượng không phải là kiểu số");
                return;
            } 
        // check tiền đặt cọc
          try{
                tiencoc = Integer.parseInt(etTiencoc.getText());
                if(!checkSoLuong(tiencoc))
                    return;
            }catch(NumberFormatException | HeadlessException e){
                JOptionPane.showMessageDialog(this, "Định dạng tiền cọc không phải là kiểu số");
                return;
            }   
        //Kiểm tra đầu vào user
            if(getUser(mathanhvien)!=null)
                check ++;
        if(check==2){
            tiencoctong = Integer.parseInt(etTongtien.getText());
            if(sosachcon>soluong){//kiem tra xem còn đủ sách cho mượn hay ko
                if(getDonMuon(masach, mathanhvien, ngaymuon)==null){
                String sql = "INSERT INTO muon(id_user,ma_sach,ngay_muon,tra_du_kien,so_luong,trang_thai,tien_coc) values('"+mathanhvien+"','"+masach+"','"+ngaymuon+"','"+ngaytra+"','"+soluong+"',0,'"+tiencoctong+"')";
                String sql1 = "UPDATE book set chomuon = '"+(soLuongMuon+soluong)+"', sosachcon = '"+(sosachcon-soluong)+"' where masach = '"+masach+"'";
                try {
                        db.executeUpdate(sql);
                        db.executeUpdate(sql1);
                        updateDatabase();
                        JOptionPane.showMessageDialog(this,mathanhvien+ " đã mượn thành công "+soluong+" quyển vào ngày "+ngaymuon);
                        } catch (Exception e) {
                            e.printStackTrace();
                    }
                }
                else{
                    int tmp = getDonMuon(masach, mathanhvien, ngaymuon).getTiencoc();//tiền cọc cũ
                    soluongsau = soluong + getDonMuon(masach, mathanhvien, ngaymuon).getSoLuong();
                    getDonMuon(masach, mathanhvien, ngaymuon).setSoLuong(soluongsau);
                    String sql = "UPDATE muontra set so_luong='"+soluongsau+"',tien_coc='"+(tmp+tiencoctong)+"' where id="+getDonMuon(masach, mathanhvien, ngaymuon).getId()+"";
                    String sql1 = "UPDATE book set chomuon = '"+(soLuongMuon+soluong)+"', sosachcon = '"+(sosachcon-soluong)+"' where masach = '"+masach+"'";
                     try {
                         db.executeUpdate(sql);
                         db.executeUpdate(sql1);
                        updateDatabase();
                        JOptionPane.showMessageDialog(this, mathanhvien+" đã mượn thêm "+ soluong+" quyển vào ngày "+ngaymuon);
                        } catch (Exception e) {
                            e.printStackTrace();
                    }
                }
                loadData(); 
            }
            else{
                 JOptionPane.showMessageDialog(this, "Quyển sách có mã "+masach+" chỉ còn "+sosachcon+" quyển.");  
                 etSoluong.setText(sosachcon+"");
            }
        }
        else
            JOptionPane.showMessageDialog(this, "Bạn đã nhập sai hoặc thiếu thông tin. Xin vui lòng kiểm tra lại");
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void etMasoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etMasoKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_etMasoKeyPressed

    private void etMasoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etMasoKeyReleased
        // TODO add your handling code here:
        String masach = etMaso.getText().toUpperCase();
        etTensach.setText("");
        for(int i=0;i<book.size();i++){
            if(book.get(i).getMasach().toUpperCase().equals(masach)){
                etTensach.setText(book.get(i).getTensach());
                break;
            }
        }
    }//GEN-LAST:event_etMasoKeyReleased

    private void etMathanhvienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etMathanhvienKeyReleased
        // TODO add your handling code here:
        String mathanhvien = etMathanhvien.getText().toUpperCase();
        etTennguoimuon.setText("");
        for(int i=0;i<user.size();i++){
            if(user.get(i).getManguoidung().toUpperCase().equals(mathanhvien)){
                etTennguoimuon.setText(user.get(i).getTennguoidung());
                break;
            }
        }
    }//GEN-LAST:event_etMathanhvienKeyReleased

    private void etTensachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etTensachKeyReleased
        // TODO add your handling code here:
        String tensach = etTensach.getText().toUpperCase();
        etMaso.setText("");
        for(int i=0;i<book.size();i++){
            if(book.get(i).getTensach().toUpperCase().equals(tensach)){
                etMaso.setText(book.get(i).getMasach());
                break;
            }
        }
    }//GEN-LAST:event_etTensachKeyReleased

    private void etTennguoimuonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etTennguoimuonKeyReleased
        // TODO add your handling code here:
         String nguoimuon = etTennguoimuon.getText().toUpperCase();
        etMathanhvien.setText("");
        for(int i=0;i<user.size();i++){
            if(user.get(i).getTennguoidung().toUpperCase().equals(nguoimuon)){
                etMathanhvien.setText(user.get(i).getManguoidung());
                break;
            }
        }
    }//GEN-LAST:event_etTennguoimuonKeyReleased

    private void NgaymuonPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_NgaymuonPropertyChange
        // TODO add your handling code here:
        if(Ngaymuon.getCalendar()!=null){
            Calendar cal;
            cal = Ngaymuon.getCalendar();
            cal.add(Calendar.DATE, 7);
            Ngaytra.setCalendar(cal);
        }
        else
            Ngaymuon.setCalendar(Calendar.getInstance());
    }//GEN-LAST:event_NgaymuonPropertyChange

    private void etSoluongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etSoluongKeyReleased
        // TODO add your handling code here:
       tinhTien();
    }//GEN-LAST:event_etSoluongKeyReleased

    private void etTiencocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etTiencocKeyReleased
        // TODO add your handling code here:
        tinhTien();
    }//GEN-LAST:event_etTiencocKeyReleased
    
    
    @Override
    protected void loadData(){
        String sql = "SELECT * FROM muon where trang_thai=0 order by id DESC";
        
        Vector data = new Vector();
        Vector cols = new Vector();
        cols.addElement("ID");
        cols.addElement("ID User");
        cols.addElement("Mã sách");
        cols.addElement("Ngày mượn");
        cols.addElement("Ngày trả dự kiến");
        cols.addElement("Số lượng");
        cols.addElement("Tiền cọc");
//        cols.addElement("Trạng thái");
        try {
            ResultSet result = db.executeQuery(sql); 
            while(result.next()){
                Vector row = new Vector();
                row.addElement(result.getString(1));
                row.addElement(result.getString(2));
                row.addElement(result.getString(3));
                row.addElement(result.getString(4));
                row.addElement(result.getString(5));
                row.addElement(result.getString(6));
                row.addElement(result.getString(7));
//                row.addElement(result.getString(8));
                data.add(row);
               // System.out.println("row "+result.getString(2));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbSach.setModel(new DefaultTableModel(data,cols));
    }
    
    @Override
    protected int tinhTien(){
      if (etTiencoc.getText()!=null && etSoluong.getText()!=null){
        int tongtien = 0;
        int tiencoc = Integer.parseInt(etTiencoc.getText());
        int soluong = Integer.parseInt(etSoluong.getText());
        tongtien = tiencoc*soluong;
        etTongtien.setText(tongtien+"");
      }
      return 0;
    }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Ngaymuon;
    private com.toedter.calendar.JDateChooser Ngaytra;
    private javax.swing.JTextField etMaso;
    private javax.swing.JTextField etMathanhvien;
    private javax.swing.JTextField etSoluong;
    private javax.swing.JTextField etTennguoimuon;
    private javax.swing.JTextField etTensach;
    private javax.swing.JTextField etTiencoc;
    private javax.swing.JLabel etTongtien;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbSach;
    // End of variables declaration//GEN-END:variables
}
