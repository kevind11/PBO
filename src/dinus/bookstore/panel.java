/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinus.bookstore;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class panel extends JPanel{
    private Image image;
    public panel(){
       image=new ImageIcon(getClass().getResource("/gambar/ws_Brown_Ubuntu_2560x1600.jpg")).getImage();
        
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd=(Graphics2D)g.create();
        gd.drawImage(image,0,0,getWidth(),getHeight(),this);
        gd.dispose();
    }
   
    
          
}
