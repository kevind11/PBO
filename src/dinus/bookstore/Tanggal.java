/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinus.bookstore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author ASUS
 */
public class Tanggal implements ActionListener {
    Date date;
    String waktu;
    Timer timer;
    JLabel label;
    String dates,hari,bulan="";
    
    SimpleDateFormat ft =new SimpleDateFormat ("',' dd MM yyyy '/' HH:mm:ss");
    public Tanggal(JLabel label) {
        this.label=label;
         timer = new Timer(1000, this);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
      
      date=new Date();
      
      ft.format(date);
        switch (ft.getCalendar().get(Calendar.DAY_OF_WEEK)) {
            case 1:
                hari="Minggu ";
                break;
            case 2:
                hari="Senin ";
                break;
            case 3:
                hari="Selasa ";
                break;
            case 4:
                hari="Rabu ";
                break;
            case 5:
                hari="Kamis ";
                break;
            case 6:
                hari="Jumat ";
                break;
            default:
                hari="Sabtu ";
                break;
        }
        /*switch (ft.getCalendar().get(Calendar.MONTH)) {
            case 0:
                bulan="Januari ";
                break;
            case 1:
                bulan="Februari ";
                break;
            case 2:
                bulan="Maret";
                break;
            case 3:
                bulan="April ";
                break;
            case 4:
                bulan="Mei ";
                break;
            case 5:
                bulan="Juni ";
                break;
            case 6:
                bulan="Juli ";
            case 7:
                bulan="Agustus ";
                break;
            case 8:
                bulan="September ";
                break;
            case 9:
                bulan="Oktober ";
                break;
            case 10:
                bulan="November ";
                break;
            default:
                bulan="Desember ";
                break;
        }*/
        dates=hari+ft.format(date);
       label.setText(dates);
       
    }
    public void start(){
        timer.start();
    }
    
   
      
}
