/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinus.bookstore;

import java.awt.Component;
import java.awt.Image;
import static java.awt.Image.SCALE_DEFAULT;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ASUS
 */
public class RenderTabelDefault extends DefaultTableCellRenderer{
   
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus,int row, int column){
       
       JLabel gambar;
       
            gambar=(JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       
           
           if(column==0){
               table.getColumnModel().getColumn(column).setPreferredWidth(25);
           }
           else if(column==3){
               table.getColumnModel().getColumn(column).setPreferredWidth(35);
           }
           else if(column==2){
               table.getColumnModel().getColumn(column).setPreferredWidth(45);
           }
             table.setRowHeight(row,25);
       
       
      
       
       gambar.setHorizontalAlignment(SwingConstants.CENTER);
       
           //gambar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,255), 1));
       
       
     return gambar;       
   }
   
}
