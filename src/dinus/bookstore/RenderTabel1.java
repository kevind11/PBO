/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinus.bookstore;

import java.awt.Color;
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
public class RenderTabel1 extends DefaultTableCellRenderer{
   int row,column;
  
   public RenderTabel1(int row,int column){
       this.row=row;
       this.column=column;
       
   }
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus,int row, int column){
       
       JLabel gambar;
 
       gambar=(JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
      
       table.setRowHeight(row, 25);
       
       /*if(column==0||column==1||column==2){
           table.getColumnModel().getColumn(column).setPreferredWidth(30);
       }*/
       
       if(this.row==row&&this.column==column){
           gambar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(247,12,11), 1));
       } 
       
       gambar.setHorizontalAlignment(SwingConstants.CENTER);
       
       
     return gambar;       
   }
   
}
