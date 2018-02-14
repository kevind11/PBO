/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinus.bookstore;

import java.awt.Component;
import java.awt.Image;
import static java.awt.Image.SCALE_DEFAULT;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ASUS
 */
public class RenderTabel extends DefaultTableCellRenderer{
   int row,column;
   
   public RenderTabel(int row,int column){
       this.row=row;
       this.column=column;
       
   }
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus,int row, int column){
       
       JLabel gambar= new JLabel();
       if(column==6){
           
           
           
           gambar.setIcon((ImageIcon)table.getValueAt(row,column));
           
           
           
       }     
       else {
            gambar=(JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
       }
      
       //gambar=(JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       table.setRowHeight(row, 60);
       table.getColumnModel().getColumn(column).setPreferredWidth(77); 
      
       gambar.setHorizontalAlignment(SwingConstants.CENTER);
       if(this.row==row&&this.column==column){
           gambar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(247,12,11), 1));
       }
       
     return gambar;       
   }
   public  ImageIcon scalegmbr(ImageIcon file){
       Image image=file.getImage();
       return new ImageIcon(image.getScaledInstance(80,60,SCALE_DEFAULT));
   }
}
