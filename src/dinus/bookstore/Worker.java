/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinus.bookstore;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.SwingWorker;

/**
 *
 * @author ASUS
 */
public class Worker extends SwingWorker   {
    Login form;
    Menu x;
    String ID,jabatan;
    public Worker(Login form,String ID,String jabatan){
        this.form=form;
        this.ID=ID;
        this.jabatan=jabatan;
        this.addPropertyChangeListener(new PropertyChangeListener(){
         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("state")) {
               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                 form.dispose();
                 x.setVisible(true);
               }
            }
         }
      });
    }
    @Override
    protected Object doInBackground() throws Exception {
        x=new Menu(ID,jabatan);
        return null;
    }
    
    public static void main(String[] args) {
     
        Scanner in=new Scanner(System.in);
        String angka=in.next(),ntemp=null;
        int num,sum1,sum2,temp,i,limit=1000;
        
        if(angka.matches("\\d{6}")){
            num=Integer.parseInt(angka);
            
            if(num>=100000&&num<=999998){
               num++;
               angka=Integer.toString(num);
               sum1=(int)angka.charAt(0)+(int)angka.charAt(1)+(int)angka.charAt(2)-'0'-'0'-'0';
                
               sum2=(int)angka.charAt(3)+(int)angka.charAt(4)+(int)angka.charAt(5)-'0'-'0'-'0';
             
               int angkabl=Integer.parseInt(angka.substring(3)),angkadp=Integer.parseInt(angka.substring(0,3));
               ntemp=Integer.toString(angkabl);
               
               while(sum1!=sum2&&num<=999998){
                    sum2=0;
                    if(sum1==27){
                        limit=999;
                    }
                    while(sum1!=sum2&&angkabl<limit){
                        angkabl++;
                        ntemp=Integer.toString(angkabl);
                        sum2=0;
                        for(i=0;i<ntemp.length();i++){
                            sum2=(int)ntemp.charAt(i)-'0'+sum2;
                        }
                        
                    }
                    if(angkabl>=limit){
                        sum1++;
                        angkabl=0;
                        angka=Integer.toString(angkadp+1)+"000";
                        num=Integer.parseInt(angka);
                    }
               }
               if(sum1==sum2){
                   
                   switch (ntemp.length()) {
                       case 1:
                           angka=angka.substring(0,3)+"00"+ntemp;
                           break;
                       case 2:
                           angka=angka.substring(0,3)+"0"+ntemp;
                           break;
                       default:
                           System.out.println("sini");
                           angka=angka.substring(0,3)+ntemp;
                           break;
                   }
                   System.out.println(angka);
               }
               else {
                   System.out.println("Error");
               }
            }
            else {
                System.exit(0);
            }
            
        }
        else {
              System.exit(0);
        }
    }
    
}
