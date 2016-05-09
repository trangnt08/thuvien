/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Trang
 */
public class Utils {
    
    
    public int hieu2Ngay(Calendar muon, Calendar tra){
        if(muon!=null && tra!=null){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String ngaymuon = format1.format(muon.getTime());
        String ngaytra = format1.format(tra.getTime());
         
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();


        // Định nghĩa 2 mốc thời gian ban đầu
        Date date1 = Date.valueOf(ngaymuon);
        Date date2 = Date.valueOf(ngaytra);

        c1.setTime(date1);
        c2.setTime(date2);

        long noDay = (c2.getTime().getTime() - c1.getTime().getTime())
                / (24 * 3600 * 1000);
        
        return (int)noDay;
        }
        return 0;
    }
}
