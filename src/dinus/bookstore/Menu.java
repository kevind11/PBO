/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinus.bookstore;

import com.mysql.jdbc.Connection;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import static java.awt.Image.SCALE_SMOOTH;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.joda.time.DateTime;

/**
 *
 * @author ASUS
 */
public class Menu extends javax.swing.JFrame {
    Object judul[]={" Kode "," Judul "," Pengarang "," Tahun "," Harga "," Stok "," Gambar "};
    Object judul1[]={" Kode "};
    Object judul2[]={" Kode "," Judul "," Harga  "," Jumlah "," Total "};
    Object judul3[]={" Kode Nota "," Kode Buku "," Jumlah "," Total "};
    Object judul4[]={" Kode Nota "," ID Karyawan "," ID Pelanggan "," Tanggal "," Waktu "," SubTotal"};
    Object judul5[]={" ID "," Nama "};
    DefaultTableModel mdl,mdl1,mdl2,mdlnt,mdlak,mdlpel,mdlkar;
    PanelPic gmbr;
    List<PanelPic> first=new ArrayList<>();
    Connection con=null;
    ResultSet rS;
    PreparedStatement pS;
    Statement stm;
    String ID;
    String jabatan;
    File file,file1;  
    int row,kembalian,rowpoint=-1,rowpoint1=-1;
    String current="",current1="";
    String lengths;
    int bisa=0;
    int state=0,kon;
    boolean a,b,c,d,e,f,g,h,i,berhasil=false,ada;
    String regex2=".*\\s{2,}.*|^$";
    /**
     * Creates new form Menu
     * @param ID    
     * @param jabatan    
     */
    
    public Menu(String ID,String jabatan)  {
       
        initComponents();
        this.ID=ID;
        this.jabatan=jabatan;
        mdl=new DefaultTableModel(null,judul){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
                
            }
            @Override
            public Class<?> getColumnClass(int column) {
            switch(column) {
            case 1: return String.class;
            case 2: return String.class;
            case 6: return InputStream.class;
            default: return Integer.class;
        }
    }
            };
        mdl1=new DefaultTableModel(null,judul1){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
                
            }};
         mdl2=new DefaultTableModel(null,judul2){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
                
            }};
          mdlnt=new DefaultTableModel(null,judul3){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
                
            }};
           mdlak=new DefaultTableModel(null,judul4){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
                
            }};
           mdlpel=new DefaultTableModel(null,judul5){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
                
            }};
           mdlkar=new DefaultTableModel(null,judul5){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
                
            }};
        akses(jabatan);
        this.setIconImage(scalegmbr(this.getWidth(),this.getHeight()).getImage());
        this.setLocationRelativeTo(null);
        error1.setIcon(scalegmbr2(error1.getWidth(),error1.getHeight()));
        error2.setIcon(scalegmbr2(error2.getWidth(),error2.getHeight()));
        error3.setIcon(scalegmbr2(error3.getWidth(),error3.getHeight()));
        error4.setIcon(scalegmbr2(error4.getWidth(),error4.getHeight()));
        error5.setIcon(scalegmbr2(error5.getWidth(),error5.getHeight()));
        error6.setIcon(scalegmbr2(error6.getWidth(),error6.getHeight()));
        err1.setIcon(scalegmbr2(error1.getWidth(),error1.getHeight()));
        err2.setIcon(scalegmbr2(error2.getWidth(),error2.getHeight()));
        err3.setIcon(scalegmbr2(error3.getWidth(),error3.getHeight()));
        err4.setIcon(scalegmbr2(error4.getWidth(),error4.getHeight()));
        err5.setIcon(scalegmbr2(error5.getWidth(),error5.getHeight()));
        errx.setIcon(scalegmbr2(errx.getWidth(),errx.getHeight()));
        erry.setIcon(scalegmbr2(erry.getWidth(),erry.getHeight()));
        errr1.setIcon(scalegmbr2(errr1.getWidth(),errr1.getHeight()));
        errr2.setIcon(scalegmbr2(errr2.getWidth(),errr2.getHeight()));
        
        logos.setIcon(scalegmbr(logos.getWidth(),logos.getHeight()));
        logos1.setIcon(scalegmbr(logos.getWidth(),logos.getHeight()));
        logos2.setIcon(scalegmbr(logos.getWidth(),logos.getHeight()));
        logos3.setIcon(scalegmbr(logos3.getWidth(),logos3.getHeight()));
        logos4.setIcon(scalegmbr(logos4.getWidth(),logos4.getHeight()));
        logos5.setIcon(scalegmbr(logos4.getWidth(),logos4.getHeight()));
        logos6.setIcon(scalegmbr(logos4.getWidth(),logos4.getHeight()));
        logos7.setIcon(scalegmbr(logos7.getWidth(),logos7.getHeight()));
        logos8.setIcon(scalegmbr(logos7.getWidth(),logos7.getHeight()));
        tblbuku.getColumnModel().getColumn(6).setCellRenderer(new RenderTabel(-1,-1));
        tblbuku.setDefaultRenderer(Object.class,new RenderTabel(-1,-1));
        tblkode.setDefaultRenderer(Object.class,new RenderTabelDefault());
        tblnota.setDefaultRenderer(Object.class,new RenderTabelDefault());
        tbldnota.setDefaultRenderer(Object.class,new RenderTabel1(-1,-1));
        tblnot.setDefaultRenderer(Object.class,new RenderTabel1(-1,-1));
        tblpel.setDefaultRenderer(Object.class,new RenderTabel12(-1,-1));
        tblkar.setDefaultRenderer(Object.class,new RenderTabel12(-1,-1));
        Tanggal tgl=new Tanggal(waktu),tgl1=new Tanggal(waktu1),tgl2=new Tanggal(waktu2),tgl3=new Tanggal(waktu3);
        
        JTextFieldDateEditor editor = (JTextFieldDateEditor) jDateChooser1.getDateEditor();
        editor.setEditable(false);
        JTextFieldDateEditor editor2 = (JTextFieldDateEditor) jDateChooser2.getDateEditor();
        editor2.setEditable(false);
        JTextFieldDateEditor editor3 = (JTextFieldDateEditor) jDateChooser3.getDateEditor();
        editor3.setEditable(false);
        JTextFieldDateEditor editor4 = (JTextFieldDateEditor) jDateChooser4.getDateEditor();
        editor4.setEditable(false);
        JTextFieldDateEditor editor5 = (JTextFieldDateEditor) jDateChooser5.getDateEditor();
        editor5.setEditable(false);
        JTextFieldDateEditor editor6 = (JTextFieldDateEditor) dt1.getDateEditor();
        editor6.setEditable(false);
        JTextFieldDateEditor editor7 = (JTextFieldDateEditor) dt2.getDateEditor();
        editor7.setEditable(false);
        JTextFieldDateEditor editor8 = (JTextFieldDateEditor) dt3.getDateEditor();
        editor8.setEditable(false);
        JTextFieldDateEditor editor9 = (JTextFieldDateEditor) dt4.getDateEditor();
        editor9.setEditable(false);
        tgl.start();
        tgl1.start();
        tgl2.start();
        tgl3.start();
        ids.setText(ID+" / "+jabatan);
       ids1.setText(ID+" / "+jabatan);
       ids2.setText(ID+" / "+jabatan);
       ids3.setText(ID+" / "+jabatan);
       open_db();
       bacatable();
       bacatabelnota();
       tblnot();
       bacatblpel();
       bacakar(state);
       Dimension dim=new Dimension(75,23);
      
       
       tmbh.setMinimumSize(dim);
       btl.setMinimumSize(dim);
       dim=new Dimension(660,665);
       this.setSize(dim);
       error1.setToolTipText("Judul tidak valid");
       error2.setToolTipText("Pengarang tidak valid");
       error3.setToolTipText("Harga tidak valid");
       error4.setToolTipText("Jumlah Stok tidak valid");
       error5.setToolTipText("Gambar masih kosong");
       err1.setToolTipText("Kode Buku tidak valid");
       err2.setToolTipText("Harga Baru tidak valid");
       err3.setToolTipText("Stok (Tambah) tidak valid");
       err4.setToolTipText("Stok (Kurang) tidak valid");
       err5.setToolTipText("Gambar tidak valid");
       errx.setToolTipText("Uang tidak valid");
       erry.setToolTipText("ID tidak valid");
       errr1.setToolTipText("Kode Buku tidak valid");
       errr2.setToolTipText("Jumlah tidak valid");
       error6.setVisible(false);
       c10.setVisible(false);
      kosong1();
      kosong2(false);
      kode();
      nonota();
      awalNota();
      id1.setText(awalID());
      inputpel();
      jDialog1.setLocationRelativeTo(this);
      jDialog2.setLocationRelativeTo(this);
       editpel();
       cleanket();
       cleanket1();
       kar1();
       kar2(false);
       kar3();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sortbutton = new javax.swing.ButtonGroup();
        idbutton = new javax.swing.ButtonGroup();
        belibutton = new javax.swing.ButtonGroup();
        notabutton = new javax.swing.ButtonGroup();
        notbutton = new javax.swing.ButtonGroup();
        tglgrup = new javax.swing.ButtonGroup();
        pelbutton = new javax.swing.ButtonGroup();
        tglgrup2 = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        butkar = new javax.swing.ButtonGroup();
        butbl = new javax.swing.ButtonGroup();
        jDialog2 = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        lm = new javax.swing.JPasswordField();
        br = new javax.swing.JPasswordField();
        kbr = new javax.swing.JPasswordField();
        jButton13 = new javax.swing.JButton();
        jLabel108 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        panel1 = new dinus.bookstore.panel();
        logos = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbuku = new javax.swing.JTable();
        searchcombo = new javax.swing.JComboBox<>();
        searchtext = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        viewall = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        descending = new javax.swing.JButton();
        ascending = new javax.swing.JButton();
        bg = new javax.swing.JPanel();
        kodebutton = new javax.swing.JRadioButton();
        judulbutton = new javax.swing.JRadioButton();
        pengarangbutton = new javax.swing.JRadioButton();
        tahunbutton = new javax.swing.JRadioButton();
        hargabutton = new javax.swing.JRadioButton();
        bg1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        waktu = new javax.swing.JLabel();
        ids = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        panel2 = new dinus.bookstore.panel();
        formulir = new javax.swing.JLabel();
        tabform = new javax.swing.JTabbedPane();
        tab1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Gambar = new javax.swing.JLabel();
        browse = new javax.swing.JButton();
        gambarlabel = new javax.swing.JLabel();
        judulbuku = new javax.swing.JTextField();
        pengarangbuku = new javax.swing.JTextField();
        hargabuku = new javax.swing.JTextField();
        stokbuku = new javax.swing.JTextField();
        tahuncombo = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        error3 = new javax.swing.JLabel();
        error2 = new javax.swing.JLabel();
        kodebuku = new javax.swing.JLabel();
        error1 = new javax.swing.JLabel();
        error5 = new javax.swing.JLabel();
        error4 = new javax.swing.JLabel();
        tab2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        kodedit = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        checkharga = new javax.swing.JCheckBox();
        hargaedit = new javax.swing.JTextField();
        checktmbh = new javax.swing.JCheckBox();
        checkkurang = new javax.swing.JCheckBox();
        stok1edit = new javax.swing.JTextField();
        stokedit = new javax.swing.JTextField();
        checkgambar = new javax.swing.JCheckBox();
        gambaredit = new javax.swing.JLabel();
        browse1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        err4 = new javax.swing.JLabel();
        err3 = new javax.swing.JLabel();
        err1 = new javax.swing.JLabel();
        err2 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        okk = new javax.swing.JButton();
        err5 = new javax.swing.JLabel();
        tab3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        kodehapus = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        error6 = new javax.swing.JLabel();
        logos1 = new javax.swing.JLabel();
        tmbh = new javax.swing.JButton();
        btl = new javax.swing.JButton();
        panelhmmm = new dinus.bookstore.panel();
        bg2 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        waktu1 = new javax.swing.JLabel();
        ids1 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        def = new javax.swing.JRadioButton();
        pela = new javax.swing.JRadioButton();
        teks = new javax.swing.JTextField();
        oke = new javax.swing.JButton();
        ganti = new javax.swing.JButton();
        erry = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        nomnota = new javax.swing.JLabel();
        logos2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblkode = new javax.swing.JTable();
        tmbhs = new javax.swing.JButton();
        Edit = new javax.swing.JButton();
        hps = new javax.swing.JButton();
        btls = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jums = new javax.swing.JTextField();
        kods = new javax.swing.JTextField();
        errr1 = new javax.swing.JLabel();
        errr2 = new javax.swing.JLabel();
        Refresh = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblnota = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        totals = new javax.swing.JLabel();
        selse = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        clears = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        byr = new javax.swing.JTextField();
        errx = new javax.swing.JLabel();
        bayar = new javax.swing.JButton();
        panel6 = new dinus.bookstore.panel();
        logos4 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblnot = new javax.swing.JTable();
        searchcombo2 = new javax.swing.JComboBox<>();
        searchtext2 = new javax.swing.JTextField();
        search2 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        viewall2 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        descending2 = new javax.swing.JButton();
        ascending2 = new javax.swing.JButton();
        idbut = new javax.swing.JPanel();
        kodebutton2 = new javax.swing.JRadioButton();
        idkarbut = new javax.swing.JRadioButton();
        idpel = new javax.swing.JRadioButton();
        tglbut = new javax.swing.JRadioButton();
        tglbut1 = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        search3 = new javax.swing.JButton();
        setelah = new javax.swing.JRadioButton();
        sebelum = new javax.swing.JRadioButton();
        saat = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        panel5 = new dinus.bookstore.panel();
        logos3 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbldnota = new javax.swing.JTable();
        searchcombo1 = new javax.swing.JComboBox<>();
        searchtext1 = new javax.swing.JTextField();
        search1 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        viewall1 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        descending1 = new javax.swing.JButton();
        ascending1 = new javax.swing.JButton();
        bg3 = new javax.swing.JPanel();
        kodebutton1 = new javax.swing.JRadioButton();
        judulbutton1 = new javax.swing.JRadioButton();
        Jumlah = new javax.swing.JRadioButton();
        Total = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        panel7 = new dinus.bookstore.panel();
        logos5 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblpel = new javax.swing.JTable();
        searchcombo3 = new javax.swing.JComboBox<>();
        searchtext3 = new javax.swing.JTextField();
        search4 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        viewall3 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        descending3 = new javax.swing.JButton();
        ascending3 = new javax.swing.JButton();
        bg4 = new javax.swing.JPanel();
        idd = new javax.swing.JRadioButton();
        nm = new javax.swing.JRadioButton();
        bg5 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        waktu2 = new javax.swing.JLabel();
        ids2 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        isi1 = new javax.swing.JLabel();
        isi2 = new javax.swing.JLabel();
        isi3 = new javax.swing.JLabel();
        isi4 = new javax.swing.JLabel();
        isi5 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        search5 = new javax.swing.JButton();
        sebelum1 = new javax.swing.JRadioButton();
        setelah1 = new javax.swing.JRadioButton();
        saat1 = new javax.swing.JRadioButton();
        panel3 = new dinus.bookstore.panel();
        juduls = new javax.swing.JLabel();
        tabform1 = new javax.swing.JTabbedPane();
        tab4 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        nms = new javax.swing.JTextField();
        almts = new javax.swing.JTextField();
        hpss = new javax.swing.JTextField();
        c3 = new javax.swing.JLabel();
        c2 = new javax.swing.JLabel();
        id1 = new javax.swing.JLabel();
        c4 = new javax.swing.JLabel();
        kots = new javax.swing.JTextField();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        c1 = new javax.swing.JLabel();
        tab5 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        kodedit1 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        ceknm = new javax.swing.JCheckBox();
        nms1 = new javax.swing.JTextField();
        cekalmt = new javax.swing.JCheckBox();
        kots1 = new javax.swing.JTextField();
        almts1 = new javax.swing.JTextField();
        cekhp = new javax.swing.JCheckBox();
        c7 = new javax.swing.JLabel();
        c5 = new javax.swing.JLabel();
        c6 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        okk1 = new javax.swing.JButton();
        err10 = new javax.swing.JLabel();
        hpss1 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        cektgl = new javax.swing.JCheckBox();
        c8 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        c9 = new javax.swing.JLabel();
        tab6 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        kodehapus1 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        c10 = new javax.swing.JLabel();
        logos6 = new javax.swing.JLabel();
        tmbh1 = new javax.swing.JButton();
        btl1 = new javax.swing.JButton();
        panel8 = new dinus.bookstore.panel();
        logos7 = new javax.swing.JLabel();
        daftar = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblkar = new javax.swing.JTable();
        searchcombo4 = new javax.swing.JComboBox<>();
        searchtext4 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        bg7 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        waktu3 = new javax.swing.JLabel();
        ids3 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        kkrj = new javax.swing.JLabel();
        kbrh = new javax.swing.JLabel();
        kid = new javax.swing.JLabel();
        kalmt = new javax.swing.JLabel();
        khp = new javax.swing.JLabel();
        kgj = new javax.swing.JLabel();
        kbns = new javax.swing.JLabel();
        klhr = new javax.swing.JLabel();
        knm = new javax.swing.JLabel();
        searchcombo5 = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        descending4 = new javax.swing.JButton();
        ascending4 = new javax.swing.JButton();
        bg6 = new javax.swing.JPanel();
        idd1 = new javax.swing.JRadioButton();
        nm1 = new javax.swing.JRadioButton();
        ascending5 = new javax.swing.JButton();
        viewall6 = new javax.swing.JButton();
        ascending7 = new javax.swing.JButton();
        ascending8 = new javax.swing.JButton();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        saat2 = new javax.swing.JRadioButton();
        stlh2 = new javax.swing.JRadioButton();
        sblm2 = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        kar4 = new javax.swing.JRadioButton();
        sup4 = new javax.swing.JRadioButton();
        panel9 = new dinus.bookstore.panel();
        juduls1 = new javax.swing.JLabel();
        tabform2 = new javax.swing.JTabbedPane();
        tab7 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        nm3 = new javax.swing.JTextField();
        almt3 = new javax.swing.JTextField();
        hp3 = new javax.swing.JTextField();
        e1 = new javax.swing.JLabel();
        kt3 = new javax.swing.JTextField();
        dt1 = new com.toedter.calendar.JDateChooser();
        id3 = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        psw3 = new javax.swing.JPasswordField();
        dt2 = new com.toedter.calendar.JDateChooser();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        gj3 = new javax.swing.JTextField();
        e2 = new javax.swing.JLabel();
        e3 = new javax.swing.JLabel();
        e4 = new javax.swing.JLabel();
        e5 = new javax.swing.JLabel();
        e6 = new javax.swing.JLabel();
        e7 = new javax.swing.JLabel();
        e8 = new javax.swing.JLabel();
        tab8 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        kodedit2 = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        cpsw = new javax.swing.JCheckBox();
        nm4 = new javax.swing.JTextField();
        calmt = new javax.swing.JCheckBox();
        kt4 = new javax.swing.JTextField();
        almt4 = new javax.swing.JTextField();
        chp = new javax.swing.JCheckBox();
        e9 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        okk2 = new javax.swing.JButton();
        err11 = new javax.swing.JLabel();
        hp4 = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        clhr = new javax.swing.JCheckBox();
        dt3 = new com.toedter.calendar.JDateChooser();
        jLabel103 = new javax.swing.JLabel();
        cnm = new javax.swing.JCheckBox();
        psw4 = new javax.swing.JPasswordField();
        jLabel104 = new javax.swing.JLabel();
        cgj = new javax.swing.JCheckBox();
        gj4 = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        ckrj = new javax.swing.JCheckBox();
        dt4 = new com.toedter.calendar.JDateChooser();
        e10 = new javax.swing.JLabel();
        e11 = new javax.swing.JLabel();
        e12 = new javax.swing.JLabel();
        e14 = new javax.swing.JLabel();
        e15 = new javax.swing.JLabel();
        e13 = new javax.swing.JLabel();
        e16 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        cjbtn = new javax.swing.JCheckBox();
        box2 = new javax.swing.JComboBox<>();
        jLabel107 = new javax.swing.JLabel();
        bns4 = new javax.swing.JTextField();
        cbns = new javax.swing.JCheckBox();
        e19 = new javax.swing.JLabel();
        tab9 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        kodehapus2 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        e17 = new javax.swing.JLabel();
        box1 = new javax.swing.JComboBox<>();
        e18 = new javax.swing.JLabel();
        logos8 = new javax.swing.JLabel();
        tmbh2 = new javax.swing.JButton();
        btl2 = new javax.swing.JButton();
        panel4 = new dinus.bookstore.panel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        daftarbuku = new javax.swing.JMenuItem();
        tmbhbuku = new javax.swing.JMenuItem();
        nota = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        notaakhir = new javax.swing.JMenuItem();
        detailnota = new javax.swing.JMenuItem();
        pelanggan = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        karyawan = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog1.setBackground(new java.awt.Color(255, 153, 51));
        jDialog1.setMinimumSize(new java.awt.Dimension(130, 50));
        jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog1.setUndecorated(true);
        jDialog1.setResizable(false);
        jDialog1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDialog1MouseClicked(evt);
            }
        });
        jDialog1.getContentPane().setLayout(null);

        jPanel4.setBackground(new java.awt.Color(255, 0, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setMinimumSize(new java.awt.Dimension(130, 50));
        jPanel4.setPreferredSize(new java.awt.Dimension(130, 50));

        jLabel41.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("LOADING");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDialog1.getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 0, 130, 50);

        jDialog2.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog2.setBackground(new java.awt.Color(240, 154, 40));
        jDialog2.setMinimumSize(new java.awt.Dimension(371, 225));
        jDialog2.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog2.setUndecorated(true);
        jDialog2.setResizable(false);
        jDialog2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDialog2FocusGained(evt);
            }
        });
        jDialog2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog2KeyPressed(evt);
            }
        });
        jDialog2.getContentPane().setLayout(null);

        jPanel7.setBackground(new java.awt.Color(240, 154, 40));
        jPanel7.setMinimumSize(new java.awt.Dimension(371, 225));

        jLabel4.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("X");
        jLabel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel4MouseMoved(evt);
            }
        });
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });

        jLabel109.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel109.setText("Password Lama");

        jLabel110.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel110.setText("Password Baru");

        jLabel111.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel111.setText("Konfirmasi Password");

        lm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lmActionPerformed(evt);
            }
        });

        br.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jButton13.setText("Simpan");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel108.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel108.setText("UBAH PASSWORD");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(br, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lm, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel110)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton13)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel111)
                                        .addGap(18, 18, 18)
                                        .addComponent(kbr, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel109)
                                .addComponent(jLabel108)))
                        .addGap(32, 32, 32))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel108)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel109))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel110)
                            .addComponent(br, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(kbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton13)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jDialog2.getContentPane().add(jPanel7);
        jPanel7.setBounds(0, 0, 371, 230);

        jButton12.setText("jButton12");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("DINUS BOOKSTORE");
        setMinimumSize(new java.awt.Dimension(660, 675));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        panel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panel1ComponentShown(evt);
            }
        });

        logos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 100, 52), 2));
        logos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logosMouseEntered(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N
        jLabel1.setText("DAFTAR BUKU");

        tblbuku.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        tblbuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode", "Judul", "Pengarang", "Tahun", "Harga", "Stok", "Gambar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblbuku.setFocusable(false);
        tblbuku.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tblbuku.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblbuku.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblbukuMouseMoved(evt);
            }
        });
        tblbuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbukuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblbukuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblbukuMouseExited(evt);
            }
        });
        tblbuku.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tblbukuComponentShown(evt);
            }
        });
        tblbuku.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblbukuPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tblbuku);

        searchcombo.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        searchcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kode", "Judul", "Pengarang", "TahunTerbit", "Harga", " " }));

        searchtext.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        searchtext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchtextMouseEntered(evt);
            }
        });

        search.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        search.setText("Search");
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel2.setText("Search by :");

        viewall.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        viewall.setText("View All");
        viewall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewallMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel3.setText("Sort by      :");

        descending.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        descending.setText("Descending");
        descending.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descendingMouseClicked(evt);
            }
        });
        descending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descendingActionPerformed(evt);
            }
        });

        ascending.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        ascending.setText("Ascending");
        ascending.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ascendingMouseClicked(evt);
            }
        });
        ascending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascendingActionPerformed(evt);
            }
        });

        bg.setBackground(new java.awt.Color(240, 154, 40));

        kodebutton.setBackground(null);
        sortbutton.add(kodebutton);
        kodebutton.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        kodebutton.setText("Kode");

        judulbutton.setBackground(null);
        sortbutton.add(judulbutton);
        judulbutton.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        judulbutton.setText("Judul");

        sortbutton.add(pengarangbutton);
        pengarangbutton.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        pengarangbutton.setText("Pengarang");
        pengarangbutton.setOpaque(false);

        sortbutton.add(tahunbutton);
        tahunbutton.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        tahunbutton.setText(" Tahun Terbit");
        tahunbutton.setOpaque(false);

        sortbutton.add(hargabutton);
        hargabutton.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        hargabutton.setText("Harga");
        hargabutton.setOpaque(false);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kodebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(judulbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(pengarangbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tahunbutton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(hargabutton)
                .addGap(20, 20, 20))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodebutton)
                    .addComponent(judulbutton)
                    .addComponent(pengarangbutton)
                    .addComponent(tahunbutton)
                    .addComponent(hargabutton))
                .addGap(10, 10, 10))
        );

        bg1.setBackground(new java.awt.Color(240, 154, 40));

        jLabel13.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel13.setText("Tanggal / Waktu  ");

        waktu.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        waktu.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                waktuComponentShown(evt);
            }
        });

        ids.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel12.setText("ID / Peran              :");

        jLabel14.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel14.setText(":");

        javax.swing.GroupLayout bg1Layout = new javax.swing.GroupLayout(bg1);
        bg1.setLayout(bg1Layout);
        bg1Layout.setHorizontalGroup(
            bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(bg1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ids, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(waktu, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        bg1Layout.setVerticalGroup(
            bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(waktu, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ids, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap())))
        );

        jButton1.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(ascending, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(descending))
                    .addComponent(bg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(bg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logos, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(searchtext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewall, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(38, 38, 38))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(bg1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(logos, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchtext, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewall, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ascending, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descending, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        getContentPane().add(panel1, "card2");

        formulir.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N
        formulir.setText("FORM INPUT BUKU");

        tabform.setBackground(new java.awt.Color(240, 154, 40));
        tabform.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        tabform.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabformFocusGained(evt);
            }
        });
        tabform.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabformMouseClicked(evt);
            }
        });

        tab1.setBackground(new java.awt.Color(240, 154, 40));
        tab1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel5.setText("Kode               ");

        jLabel6.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel6.setText("Judul              ");

        jLabel7.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel7.setText("Pengarang      ");

        jLabel8.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel8.setText("Tahun Terbit   ");

        jLabel9.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel9.setText("Harga              ");

        jLabel10.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel10.setText("Jumlah Stok    ");

        Gambar.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        Gambar.setText("Gambar             ");

        browse.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        browse.setText("Browse...");
        browse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseMouseClicked(evt);
            }
        });

        gambarlabel.setIcon(null);
        gambarlabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        judulbuku.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        pengarangbuku.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        pengarangbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pengarangbukuActionPerformed(evt);
            }
        });

        hargabuku.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        hargabuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargabukuActionPerformed(evt);
            }
        });

        stokbuku.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        tahuncombo.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        tahuncombo.setMaximumRowCount(5);
        tahuncombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2016", "2015", "2014", "2013", "2012", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990" }));

        jLabel15.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel15.setText("Maks (200KB)");

        error3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        error2.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        error1.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        error5.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        error5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                error5MouseMoved(evt);
            }
        });

        error4.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(30, 30, 30)
                        .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kodebuku, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab1Layout.createSequentialGroup()
                                .addComponent(judulbuku, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(error1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addComponent(Gambar)
                        .addGap(22, 22, 22)
                        .addComponent(gambarlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(browse)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(error5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tahuncombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7))
                        .addGap(25, 25, 25)
                        .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab1Layout.createSequentialGroup()
                                .addComponent(stokbuku, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(error4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab1Layout.createSequentialGroup()
                                .addComponent(pengarangbuku, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(error2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab1Layout.createSequentialGroup()
                                .addComponent(hargabuku, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(error3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(kodebuku, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(judulbuku, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                    .addComponent(error1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pengarangbuku, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(error2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(tahuncombo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(error3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hargabuku, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(error4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(stokbuku, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(browse)
                            .addComponent(error5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15))
                    .addComponent(Gambar)
                    .addComponent(gambarlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        tabform.addTab("Buku Baru", tab1);

        tab2.setBackground(new java.awt.Color(240, 154, 40));
        tab2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tab2FocusGained(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel19.setText("Kode Buku Edit ");

        jLabel16.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel16.setText("Harga Baru");

        jLabel17.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel17.setText("Stok (Tambah)");

        jLabel18.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel18.setText("Gambar Baru");

        checkharga.setOpaque(false);
        checkharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkhargaActionPerformed(evt);
            }
        });

        hargaedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaeditActionPerformed(evt);
            }
        });

        checktmbh.setOpaque(false);
        checktmbh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checktmbhActionPerformed(evt);
            }
        });

        checkkurang.setOpaque(false);
        checkkurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkkurangActionPerformed(evt);
            }
        });

        stok1edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stok1editActionPerformed(evt);
            }
        });

        checkgambar.setOpaque(false);
        checkgambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkgambarActionPerformed(evt);
            }
        });

        gambaredit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        browse1.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        browse1.setText("Browse...");
        browse1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browse1MouseClicked(evt);
            }
        });
        browse1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browse1ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel22.setText("Maks (500KB)");

        jLabel24.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel24.setText("Stok (Kurang)");

        okk.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        okk.setText("OK");
        okk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(32, 32, 32))
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkkurang)
                            .addComponent(checktmbh)
                            .addComponent(checkgambar)))
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkharga)))
                .addGap(18, 18, 18)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addComponent(stokedit, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(err3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addComponent(hargaedit, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(err2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addComponent(kodedit, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(err1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addComponent(gambaredit, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(okk)
                                    .addComponent(browse1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(err5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addComponent(stok1edit, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(err4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(err1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(kodedit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(okk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(hargaedit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkharga, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(err2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(checktmbh, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(stokedit, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(err3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab2Layout.createSequentialGroup()
                        .addGap(0, 18, Short.MAX_VALUE)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(err4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkkurang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(stok1edit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkgambar, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gambaredit, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(browse1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(err5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22)))
                        .addGap(16, 16, 16))))
        );

        tabform.addTab("Edit Buku", tab2);

        tab3.setBackground(new java.awt.Color(240, 154, 40));

        jLabel11.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel11.setText("Masukkan Kode Buku");

        jLabel20.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel20.setText("*Buku yang sudah dipasarkan/dibeli tidak bisa dihapus");

        kodehapus.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        kodehapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodehapusActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel21.setText("*Klik Kanan pada Kode Buku (Daftar Buku) untuk cara cepat");

        error6.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(kodehapus, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(error6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(kodehapus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(error6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        tabform.addTab("Hapus Buku", tab3);

        logos1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 100, 52), 2));
        logos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logos1MouseEntered(evt);
            }
        });

        tmbh.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        tmbh.setText("Tambah");
        tmbh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmbhActionPerformed(evt);
            }
        });

        btl.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        btl.setText("Clear");
        btl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btlMouseClicked(evt);
            }
        });
        btl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(formulir))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(tabform, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logos1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tmbh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(logos1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(tmbh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btl))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(formulir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tabform, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        tabform.getAccessibleContext().setAccessibleName("Buku Baru");

        getContentPane().add(panel2, "card3");

        bg2.setBackground(new java.awt.Color(240, 154, 40));

        jLabel23.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel23.setText("Tanggal / Waktu  ");

        waktu1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        waktu1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                waktu1ComponentShown(evt);
            }
        });

        ids1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel25.setText("ID / Peran              :");

        jLabel26.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel26.setText(":");

        javax.swing.GroupLayout bg2Layout = new javax.swing.GroupLayout(bg2);
        bg2.setLayout(bg2Layout);
        bg2Layout.setHorizontalGroup(
            bg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addGroup(bg2Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ids1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(waktu1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        bg2Layout.setVerticalGroup(
            bg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(waktu1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(jLabel26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ids1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg2Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addContainerGap())))
        );

        jPanel1.setBackground(new java.awt.Color(240, 154, 40));

        jLabel28.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel28.setText("ID");

        idbutton.add(def);
        def.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        def.setSelected(true);
        def.setText("Default");
        def.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defActionPerformed(evt);
            }
        });

        idbutton.add(pela);
        pela.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        pela.setText("Pelanggan");
        pela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pelaActionPerformed(evt);
            }
        });

        teks.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        teks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teksActionPerformed(evt);
            }
        });

        oke.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        oke.setText("OK");
        oke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okeActionPerformed(evt);
            }
        });

        ganti.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        ganti.setText("Ganti");
        ganti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gantiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(def)
                .addGap(18, 18, 18)
                .addComponent(pela)
                .addGap(18, 18, 18)
                .addComponent(teks, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(erry, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(oke)
                .addGap(18, 18, 18)
                .addComponent(ganti)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(def)
                    .addComponent(pela)
                    .addComponent(teks)
                    .addComponent(oke, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(ganti, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(erry, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel27.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N
        jLabel27.setText("Nota");

        nomnota.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        nomnota.setText("(No .");

        logos2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 100, 52), 2));
        logos2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logos2MouseEntered(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(240, 154, 40));

        tblkode.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        tblkode.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Kode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblkode.setFocusable(false);
        tblkode.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblkodeMouseMoved(evt);
            }
        });
        tblkode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkodeMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblkodeMouseExited(evt);
            }
        });
        jScrollPane2.setViewportView(tblkode);

        tmbhs.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        tmbhs.setText("Tambah");
        tmbhs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmbhsActionPerformed(evt);
            }
        });

        Edit.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        Edit.setText("Edit");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        hps.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        hps.setText("Hapus");
        hps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hpsActionPerformed(evt);
            }
        });

        btls.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        btls.setText("Batal");
        btls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlsActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel30.setText("Kode");

        jLabel31.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel31.setText("Jumlah");

        jums.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jums.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumsActionPerformed(evt);
            }
        });

        kods.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        kods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodsActionPerformed(evt);
            }
        });

        Refresh.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        Refresh.setText("Refresh");
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(kods, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jums, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errr1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(errr2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tmbhs)
                        .addGap(33, 33, 33)
                        .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(hps, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btls, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Refresh))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel30)
                                        .addComponent(kods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(errr1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel31)
                                        .addComponent(jums, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(errr2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(Refresh))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tmbhs)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Edit)
                                .addComponent(hps)
                                .addComponent(btls)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblnota.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        tblnota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode", "Judul", "Harga", "Jumlah", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblnota.setFocusable(false);
        tblnota.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblnotaMouseMoved(evt);
            }
        });
        tblnota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnotaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblnotaMouseExited(evt);
            }
        });
        jScrollPane3.setViewportView(tblnota);

        jLabel32.setFont(new java.awt.Font("Century", 1, 18)); // NOI18N
        jLabel32.setText("Sub Total");

        totals.setFont(new java.awt.Font("Century", 1, 18)); // NOI18N
        totals.setText("Rp 0");

        selse.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        selse.setText("Selesai");
        selse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selseActionPerformed(evt);
            }
        });

        ubah.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        ubah.setText("Ubah");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });

        clears.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        clears.setText("Clear");
        clears.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearsActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Century", 1, 16)); // NOI18N
        jLabel34.setText("Bayar");

        jLabel36.setFont(new java.awt.Font("Century", 1, 16)); // NOI18N
        jLabel36.setText("Rp");

        byr.setFont(new java.awt.Font("Century", 1, 16)); // NOI18N

        bayar.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        bayar.setText("Bayar");
        bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelhmmmLayout = new javax.swing.GroupLayout(panelhmmm);
        panelhmmm.setLayout(panelhmmmLayout);
        panelhmmmLayout.setHorizontalGroup(
            panelhmmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelhmmmLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelhmmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelhmmmLayout.createSequentialGroup()
                        .addGroup(panelhmmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelhmmmLayout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel36)
                                .addGap(18, 18, 18)
                                .addComponent(byr, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(errx, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(panelhmmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelhmmmLayout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE))
                            .addGroup(panelhmmmLayout.createSequentialGroup()
                                .addGroup(panelhmmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ubah, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                    .addComponent(selse, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                    .addComponent(clears, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                    .addComponent(bayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(totals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelhmmmLayout.createSequentialGroup()
                        .addGroup(panelhmmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bg2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelhmmmLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nomnota, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logos2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelhmmmLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(36, 36, 36))
        );
        panelhmmmLayout.setVerticalGroup(
            panelhmmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelhmmmLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelhmmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logos2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelhmmmLayout.createSequentialGroup()
                        .addComponent(bg2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelhmmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomnota)
                            .addComponent(jLabel27))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(panelhmmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelhmmmLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totals, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(selse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ubah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clears)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelhmmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelhmmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(jLabel36)
                        .addComponent(byr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bayar)
                    .addComponent(errx, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panelhmmm, "card4");

        panel6.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panel6ComponentShown(evt);
            }
        });

        logos4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 100, 52), 2));
        logos4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logos4MouseEntered(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N
        jLabel38.setText("DAFTAR NOTA");

        tblnot.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        tblnot.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Nota", "ID Karyawan", "ID Pelanggan", "Tanggal", "Waktu", "SubTotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblnot.setFocusable(false);
        tblnot.setSelectionBackground(Color.WHITE);
        tblnot.setSelectionForeground(Color.BLACK);
        tblnot.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblnotMouseMoved(evt);
            }
        });
        tblnot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnotMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblnotMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblnotMouseExited(evt);
            }
        });
        tblnot.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tblnotComponentShown(evt);
            }
        });
        tblnot.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblnotPropertyChange(evt);
            }
        });
        jScrollPane5.setViewportView(tblnot);

        searchcombo2.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        searchcombo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kode Nota", "ID Karyawan", "ID Pelanggan", " " }));

        searchtext2.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        searchtext2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchtext2MouseEntered(evt);
            }
        });

        search2.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        search2.setText("Search");
        search2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                search2MouseEntered(evt);
            }
        });
        search2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search2ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel39.setText("Search by :");

        viewall2.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        viewall2.setText("View All");
        viewall2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewall2MouseClicked(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel40.setText("Sort by      :");

        descending2.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        descending2.setText("Descending");
        descending2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descending2MouseClicked(evt);
            }
        });
        descending2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descending2ActionPerformed(evt);
            }
        });

        ascending2.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        ascending2.setText("Ascending");
        ascending2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ascending2MouseClicked(evt);
            }
        });
        ascending2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascending2ActionPerformed(evt);
            }
        });

        idbut.setBackground(new java.awt.Color(240, 154, 40));

        kodebutton2.setBackground(null);
        notbutton.add(kodebutton2);
        kodebutton2.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        kodebutton2.setText("Kode Nota");

        idkarbut.setBackground(null);
        notbutton.add(idkarbut);
        idkarbut.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        idkarbut.setText("ID Karyawan");

        notbutton.add(idpel);
        idpel.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        idpel.setText("ID Pelanggan");
        idpel.setOpaque(false);

        notbutton.add(tglbut);
        tglbut.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        tglbut.setText("Tanggal");
        tglbut.setOpaque(false);

        notbutton.add(tglbut1);
        tglbut1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        tglbut1.setText("Sub Total");
        tglbut1.setOpaque(false);

        javax.swing.GroupLayout idbutLayout = new javax.swing.GroupLayout(idbut);
        idbut.setLayout(idbutLayout);
        idbutLayout.setHorizontalGroup(
            idbutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idbutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kodebutton2)
                .addGap(31, 31, 31)
                .addComponent(idkarbut)
                .addGap(18, 18, 18)
                .addComponent(idpel)
                .addGap(18, 18, 18)
                .addComponent(tglbut)
                .addGap(18, 18, 18)
                .addComponent(tglbut1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        idbutLayout.setVerticalGroup(
            idbutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, idbutLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(idbutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodebutton2)
                    .addComponent(idkarbut)
                    .addComponent(idpel)
                    .addComponent(tglbut)
                    .addComponent(tglbut1))
                .addGap(10, 10, 10))
        );

        jButton3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("dd-MM-yyyy");
        jDateChooser1.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jDateChooser1.setPreferredSize(new java.awt.Dimension(40, 20));
        jDateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser1MouseClicked(evt);
            }
        });

        search3.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        search3.setText("Search");
        search3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search3MouseClicked(evt);
            }
        });
        search3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search3ActionPerformed(evt);
            }
        });

        tglgrup.add(setelah);
        setelah.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        setelah.setText("Setelah");

        tglgrup.add(sebelum);
        sebelum.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        sebelum.setText("Sebelum");

        tglgrup.add(saat);
        saat.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        saat.setSelected(true);
        saat.setText("Saat");

        jLabel29.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel29.setText("Tanggal");

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ascending2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(descending2))
                    .addComponent(idbut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel6Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
                        .addComponent(logos4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5)
                    .addGroup(panel6Layout.createSequentialGroup()
                        .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchcombo2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel6Layout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(saat))
                            .addComponent(searchtext2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(setelah)
                            .addComponent(search2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sebelum)
                            .addComponent(viewall2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(search3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel6Layout.createSequentialGroup()
                        .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(38, 38, 38))
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel38))
                    .addGroup(panel6Layout.createSequentialGroup()
                        .addComponent(logos4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchcombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchtext2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewall2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(search3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sebelum)
                        .addComponent(setelah)
                        .addComponent(saat))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idbut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ascending2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descending2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        getContentPane().add(panel6, "cardnot");

        panel5.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panel5ComponentShown(evt);
            }
        });

        logos3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 100, 52), 2));
        logos3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logos3MouseEntered(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N
        jLabel33.setText("DAFTAR DETAIL NOTA");

        tbldnota.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        tbldnota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Nota", "Kode Buku", "Jumlah", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbldnota.setFocusable(false);
        tbldnota.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tbldnota.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbldnota.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tbldnotaMouseMoved(evt);
            }
        });
        tbldnota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldnotaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbldnotaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tbldnotaMouseExited(evt);
            }
        });
        tbldnota.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tbldnotaComponentShown(evt);
            }
        });
        tbldnota.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbldnotaPropertyChange(evt);
            }
        });
        jScrollPane4.setViewportView(tbldnota);

        searchcombo1.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        searchcombo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kode Nota", "Kode Buku" }));

        searchtext1.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        searchtext1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchtext1MouseEntered(evt);
            }
        });

        search1.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        search1.setText("Search");
        search1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search1MouseClicked(evt);
            }
        });
        search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search1ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel35.setText("Search by :");

        viewall1.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        viewall1.setText("View All");
        viewall1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewall1MouseClicked(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel37.setText("Sort by      :");

        descending1.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        descending1.setText("Descending");
        descending1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descending1MouseClicked(evt);
            }
        });
        descending1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descending1ActionPerformed(evt);
            }
        });

        ascending1.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        ascending1.setText("Ascending");
        ascending1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ascending1MouseClicked(evt);
            }
        });
        ascending1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascending1ActionPerformed(evt);
            }
        });

        bg3.setBackground(new java.awt.Color(240, 154, 40));

        kodebutton1.setBackground(null);
        notabutton.add(kodebutton1);
        kodebutton1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        kodebutton1.setText("Kode Nota");

        judulbutton1.setBackground(null);
        notabutton.add(judulbutton1);
        judulbutton1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        judulbutton1.setText("Kode Buku");

        notabutton.add(Jumlah);
        Jumlah.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        Jumlah.setText("Jumlah");
        Jumlah.setOpaque(false);
        Jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JumlahActionPerformed(evt);
            }
        });

        notabutton.add(Total);
        Total.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        Total.setText("Total");
        Total.setOpaque(false);

        javax.swing.GroupLayout bg3Layout = new javax.swing.GroupLayout(bg3);
        bg3.setLayout(bg3Layout);
        bg3Layout.setHorizontalGroup(
            bg3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kodebutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(judulbutton1)
                .addGap(18, 18, 18)
                .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Total)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        bg3Layout.setVerticalGroup(
            bg3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bg3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(judulbutton1)
                    .addComponent(kodebutton1)
                    .addComponent(Jumlah)
                    .addComponent(Total))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jButton2.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
            .addGroup(panel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel5Layout.createSequentialGroup()
                                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addComponent(ascending1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(descending1)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel33)
                                .addGap(88, 88, 88)
                                .addComponent(logos3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(searchcombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchtext1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(viewall1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38))))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel33))
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addComponent(logos3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchcombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchtext1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewall1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addGap(11, 11, 11)
                .addComponent(bg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ascending1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descending1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        getContentPane().add(panel5, "cardnota");

        panel7.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panel7ComponentShown(evt);
            }
        });

        logos5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 100, 52), 2));
        logos5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logos5MouseEntered(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N
        jLabel42.setText("DAFTAR PELANGGAN");

        tblpel.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        tblpel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblpel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblpel.setFocusable(false);
        tblpel.setSelectionBackground(new java.awt.Color(240, 154, 40));
        tblpel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblpelMouseMoved(evt);
            }
        });
        tblpel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblpelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblpelMouseExited(evt);
            }
        });
        tblpel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tblpelComponentShown(evt);
            }
        });
        tblpel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblpelPropertyChange(evt);
            }
        });
        jScrollPane6.setViewportView(tblpel);

        searchcombo3.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        searchcombo3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nama", "Alamat", "NoHP" }));

        searchtext3.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        searchtext3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchtext3MouseEntered(evt);
            }
        });

        search4.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        search4.setText("Search");
        search4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search4MouseClicked(evt);
            }
        });
        search4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search4ActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel43.setText("Search by :");

        viewall3.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        viewall3.setText("View All");
        viewall3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewall3MouseClicked(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel44.setText("Sort by      :");

        descending3.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        descending3.setText("Descending");
        descending3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descending3MouseClicked(evt);
            }
        });
        descending3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descending3ActionPerformed(evt);
            }
        });

        ascending3.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        ascending3.setText("Ascending");
        ascending3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ascending3MouseClicked(evt);
            }
        });
        ascending3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascending3ActionPerformed(evt);
            }
        });

        bg4.setBackground(new java.awt.Color(240, 154, 40));

        idd.setBackground(null);
        pelbutton.add(idd);
        idd.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        idd.setText("ID");

        nm.setBackground(null);
        pelbutton.add(nm);
        nm.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        nm.setText("Nama");

        javax.swing.GroupLayout bg4Layout = new javax.swing.GroupLayout(bg4);
        bg4.setLayout(bg4Layout);
        bg4Layout.setHorizontalGroup(
            bg4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idd, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(nm, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        bg4Layout.setVerticalGroup(
            bg4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bg4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idd)
                    .addComponent(nm))
                .addGap(10, 10, 10))
        );

        bg5.setBackground(new java.awt.Color(240, 154, 40));

        jLabel45.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel45.setText("Tanggal / Waktu  ");

        waktu2.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        waktu2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                waktu2ComponentShown(evt);
            }
        });

        ids2.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N

        jLabel46.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel46.setText("ID / Peran              :");

        jLabel47.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel47.setText(":");

        javax.swing.GroupLayout bg5Layout = new javax.swing.GroupLayout(bg5);
        bg5.setLayout(bg5Layout);
        bg5Layout.setHorizontalGroup(
            bg5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bg5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addGroup(bg5Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bg5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ids2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(waktu2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        bg5Layout.setVerticalGroup(
            bg5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bg5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(waktu2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bg5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(jLabel47)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bg5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ids2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg5Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addContainerGap())))
        );

        jButton4.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(240, 154, 40));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jLabel48.setFont(new java.awt.Font("Century", 0, 16)); // NOI18N
        jLabel48.setText("KETERANGAN PELANGGAN");

        jLabel49.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel49.setText("ID");

        jLabel50.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel50.setText("Nama");

        jLabel51.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel51.setText("Alamat");

        jLabel52.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel52.setText("No HP");

        jLabel53.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel53.setText("Tgl Lahir");

        jButton5.setText("<");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText(">");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        isi1.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        isi2.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        isi3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        isi4.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        isi5.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        jButton7.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        jButton7.setText("Info Nota");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 47, Short.MAX_VALUE)
                        .addComponent(jLabel48)
                        .addGap(49, 49, 49))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel52)
                                    .addComponent(jLabel53))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(isi3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(isi4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(isi5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel50)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(isi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(isi2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addGap(13, 13, 13))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel48)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(isi1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isi2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(isi3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(isi4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(isi5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 33, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton5)
                                    .addComponent(jButton6)
                                    .addComponent(jButton7)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel54.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel54.setText("Tanggal Lahir");

        jDateChooser2.setDateFormatString("dd-MM-yyyy");

        search5.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        search5.setText("Search");
        search5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search5MouseClicked(evt);
            }
        });
        search5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search5ActionPerformed(evt);
            }
        });

        tglgrup2.add(sebelum1);
        sebelum1.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        sebelum1.setText("Sebelum");

        tglgrup2.add(setelah1);
        setelah1.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        setelah1.setText("Setelah");

        tglgrup2.add(saat1);
        saat1.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        saat1.setSelected(true);
        saat1.setText("Saat");

        javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel7Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel7Layout.createSequentialGroup()
                        .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(bg5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logos5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(panel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(35, 35, 35))))
            .addGroup(panel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel7Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel7Layout.createSequentialGroup()
                        .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel7Layout.createSequentialGroup()
                                .addComponent(bg4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ascending3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(descending3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panel7Layout.createSequentialGroup()
                                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(searchcombo3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel54)
                                    .addComponent(jLabel44))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(searchtext3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel7Layout.createSequentialGroup()
                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(saat1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel7Layout.createSequentialGroup()
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addComponent(search4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(panel7Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(setelah1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(viewall3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sebelum1))
                                .addGap(18, 18, 18)
                                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(search5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))))
                        .addGap(35, 35, 35))))
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel7Layout.createSequentialGroup()
                        .addComponent(bg5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel42))
                    .addGroup(panel7Layout.createSequentialGroup()
                        .addComponent(logos5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel7Layout.createSequentialGroup()
                        .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchcombo3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchtext3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewall3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(sebelum1)
                                .addComponent(setelah1)
                                .addComponent(saat1))))
                    .addComponent(search5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bg4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(descending3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ascending3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(83, 83, 83))
        );

        getContentPane().add(panel7, "cardpel");

        juduls.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N
        juduls.setText("FORM INPUT PELANGGAN");

        tabform1.setBackground(new java.awt.Color(240, 154, 40));
        tabform1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        tabform1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabform1FocusGained(evt);
            }
        });
        tabform1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabform1MouseClicked(evt);
            }
        });

        tab4.setBackground(new java.awt.Color(240, 154, 40));
        tab4.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N

        jLabel56.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel56.setText("ID");

        jLabel57.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel57.setText("Nama");

        jLabel58.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel58.setText("Alamat");

        jLabel59.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel59.setText("Kota");

        jLabel60.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel60.setText("No HP");

        jLabel61.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel61.setText("Tgl Lahir");

        nms.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        almts.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        almts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almtsActionPerformed(evt);
            }
        });

        hpss.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        hpss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hpssActionPerformed(evt);
            }
        });

        c3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        c3.setIcon(scalegmbr2(23,23));
        c3.setToolTipText("No HP tidak valid");
        c3.setMaximumSize(new java.awt.Dimension(23, 23));
        c3.setMinimumSize(new java.awt.Dimension(23, 23));
        c3.setPreferredSize(new java.awt.Dimension(23, 23));

        c2.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        c2.setIcon(scalegmbr2(23,23));
        c2.setToolTipText("Alamat/Kota tidak valid");
        c2.setMaximumSize(new java.awt.Dimension(23, 23));
        c2.setMinimumSize(new java.awt.Dimension(23, 23));
        c2.setPreferredSize(new java.awt.Dimension(23, 23));

        c4.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        c4.setIcon(scalegmbr2(23,23));
        c4.setToolTipText("Tgl Lahir tidak valid");
        c4.setMaximumSize(new java.awt.Dimension(23, 23));
        c4.setMinimumSize(new java.awt.Dimension(23, 23));
        c4.setPreferredSize(new java.awt.Dimension(23, 23));

        kots.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        kots.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kotsActionPerformed(evt);
            }
        });

        jDateChooser3.setDateFormatString("dd-MM-yyy");

        c1.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        c1.setIcon(scalegmbr2(23,23));
        c1.setToolTipText("Nama tidak valid");
        c1.setMaximumSize(new java.awt.Dimension(23, 23));
        c1.setMinimumSize(new java.awt.Dimension(23, 23));
        c1.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab4Layout.createSequentialGroup()
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab4Layout.createSequentialGroup()
                                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel58)
                                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel60)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab4Layout.createSequentialGroup()
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab4Layout.createSequentialGroup()
                                .addComponent(nms, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab4Layout.createSequentialGroup()
                                .addComponent(almts, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(id1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kots, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(tab4Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(c4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tab4Layout.createSequentialGroup()
                                    .addComponent(hpss, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(id1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nms, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(almts, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel58)))
                .addGap(18, 18, 18)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kots, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(c4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hpss, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(260, 260, 260))
        );

        tabform1.addTab("Pelanggan Baru", tab4);

        tab5.setBackground(new java.awt.Color(240, 154, 40));
        tab5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tab5FocusGained(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel63.setText("ID Pelanggan Edit ");

        jLabel64.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel64.setText("Nama ");

        jLabel65.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel65.setText("Alamat ");

        jLabel66.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel66.setText("No Hp");
        jLabel66.setToolTipText("");

        ceknm.setOpaque(false);
        ceknm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ceknmActionPerformed(evt);
            }
        });

        nms1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nms1ActionPerformed(evt);
            }
        });

        cekalmt.setOpaque(false);
        cekalmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekalmtActionPerformed(evt);
            }
        });

        kots1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kots1ActionPerformed(evt);
            }
        });

        cekhp.setOpaque(false);
        cekhp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekhpActionPerformed(evt);
            }
        });

        c7.setIcon(scalegmbr2(23,23));
        c7.setToolTipText("Alamat/Kota tidak valid");
        c7.setMaximumSize(new java.awt.Dimension(23, 23));
        c7.setMinimumSize(new java.awt.Dimension(23, 23));
        c7.setPreferredSize(new java.awt.Dimension(23, 23));

        c5.setIcon( scalegmbr2(23,23));
        c5.setToolTipText("ID Pelanggan tidak valid");
        c5.setMaximumSize(new java.awt.Dimension(23, 23));
        c5.setMinimumSize(new java.awt.Dimension(23, 23));
        c5.setPreferredSize(new java.awt.Dimension(23, 23));

        c6.setIcon(scalegmbr2(23,23));
        c6.setToolTipText("Nama tidak valid");
        c6.setMaximumSize(new java.awt.Dimension(23, 23));
        c6.setMinimumSize(new java.awt.Dimension(23, 23));
        c6.setPreferredSize(new java.awt.Dimension(23, 23));

        jLabel68.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel68.setText("Kota");

        okk1.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        okk1.setText("OK");
        okk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okk1ActionPerformed(evt);
            }
        });

        hpss1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hpss1ActionPerformed(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel67.setText("Tgl Lahir");

        cektgl.setOpaque(false);
        cektgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cektglActionPerformed(evt);
            }
        });

        c8.setIcon(scalegmbr2(23,23));
        c8.setToolTipText("No HP tidak valid");
        c8.setMaximumSize(new java.awt.Dimension(23, 23));
        c8.setMinimumSize(new java.awt.Dimension(23, 23));
        c8.setPreferredSize(new java.awt.Dimension(23, 23));

        jDateChooser4.setDateFormatString("dd-MM-yyyy");

        c9.setIcon(scalegmbr2(23,23));
        c9.setToolTipText("Tgl Lahir tidak valid");
        c9.setMaximumSize(new java.awt.Dimension(23, 23));
        c9.setMinimumSize(new java.awt.Dimension(23, 23));
        c9.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout tab5Layout = new javax.swing.GroupLayout(tab5);
        tab5.setLayout(tab5Layout);
        tab5Layout.setHorizontalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okk1)
                        .addGap(36, 36, 36))
                    .addGroup(tab5Layout.createSequentialGroup()
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(tab5Layout.createSequentialGroup()
                                    .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(tab5Layout.createSequentialGroup()
                                            .addComponent(jLabel65)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(tab5Layout.createSequentialGroup()
                                            .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(tab5Layout.createSequentialGroup()
                                                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cekalmt)
                                        .addComponent(cekhp)
                                        .addComponent(cektgl)))
                                .addGroup(tab5Layout.createSequentialGroup()
                                    .addComponent(jLabel64)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ceknm))
                                .addComponent(jLabel63))
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab5Layout.createSequentialGroup()
                                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab5Layout.createSequentialGroup()
                                        .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(tab5Layout.createSequentialGroup()
                                                .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(c9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(tab5Layout.createSequentialGroup()
                                                .addComponent(hpss1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(c8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(tab5Layout.createSequentialGroup()
                                                .addComponent(nms1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(c6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(46, 46, 46)
                                        .addComponent(err10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(kots1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(tab5Layout.createSequentialGroup()
                                        .addComponent(kodedit1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(c5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 36, Short.MAX_VALUE))
                            .addGroup(tab5Layout.createSequentialGroup()
                                .addComponent(almts1)
                                .addGap(18, 18, 18)
                                .addComponent(c7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))))))
        );
        tab5Layout.setVerticalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(c5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel63)
                        .addComponent(kodedit1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(okk1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(nms1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ceknm, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(c6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cekalmt, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(almts1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(c7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab5Layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addComponent(kots1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(hpss1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(err10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cekhp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(c8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(cektgl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(c9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98))
        );

        tabform1.addTab("Edit Pelanggan", tab5);

        tab6.setBackground(new java.awt.Color(240, 154, 40));

        jLabel69.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel69.setText("Masukkan ID Pelanggan");

        jLabel70.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel70.setText("*Pelanggan yang sudah aktif melakukan transaksi tidak bisa dihapus");

        kodehapus1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        kodehapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodehapus1ActionPerformed(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel71.setText("*Klik Kanan pada ID Pelanggan (Daftar Pelanggan) untuk cara cepat");

        c10.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        c10.setIcon(scalegmbr2(23,23));
        c10.setToolTipText("ID Pelanggan tidak valid");
        c10.setMaximumSize(new java.awt.Dimension(23, 23));
        c10.setMinimumSize(new java.awt.Dimension(23, 23));
        c10.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout tab6Layout = new javax.swing.GroupLayout(tab6);
        tab6.setLayout(tab6Layout);
        tab6Layout.setHorizontalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab6Layout.createSequentialGroup()
                .addGroup(tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(kodehapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(c10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel71))
                    .addGroup(tab6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel70)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab6Layout.setVerticalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab6Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(kodehapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(c10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel70)
                .addContainerGap(231, Short.MAX_VALUE))
        );

        tabform1.addTab("Hapus Pelanggan", tab6);

        logos6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 100, 52), 2));
        logos6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logos6MouseEntered(evt);
            }
        });

        tmbh1.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        tmbh1.setText("Tambah");
        tmbh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmbh1ActionPerformed(evt);
            }
        });

        btl1.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        btl1.setText("Clear");
        btl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btl1MouseClicked(evt);
            }
        });
        btl1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btl1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(juduls))
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(tabform1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logos6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btl1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tmbh1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(logos6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(tmbh1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btl1))
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(juduls, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tabform1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(178, Short.MAX_VALUE))
        );

        getContentPane().add(panel3, "cardpels");

        panel8.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panel8ComponentShown(evt);
            }
        });

        logos7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 100, 52), 2));
        logos7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logos7MouseEntered(evt);
            }
        });

        daftar.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N
        daftar.setText("DAFTAR KARYAWAN");

        tblkar.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        tblkar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblkar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblkar.setFocusable(false);
        tblkar.setSelectionBackground(new java.awt.Color(240, 154, 40));
        tblkar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblkarMouseMoved(evt);
            }
        });
        tblkar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblkarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblkarMouseExited(evt);
            }
        });
        tblkar.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tblkarComponentShown(evt);
            }
        });
        tblkar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblkarPropertyChange(evt);
            }
        });
        jScrollPane7.setViewportView(tblkar);

        searchcombo4.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        searchcombo4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Nama", "Alamat", "NoHP", "Gaji" }));

        searchtext4.setFont(new java.awt.Font("Century", 0, 13)); // NOI18N
        searchtext4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchtext4MouseEntered(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel62.setText("Search by :");

        bg7.setBackground(new java.awt.Color(240, 154, 40));

        jLabel73.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel73.setText("Tanggal / Waktu  ");

        waktu3.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        waktu3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                waktu3ComponentShown(evt);
            }
        });

        ids3.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N

        jLabel74.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel74.setText("ID / Peran              :");

        jLabel75.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel75.setText(":");

        javax.swing.GroupLayout bg7Layout = new javax.swing.GroupLayout(bg7);
        bg7.setLayout(bg7Layout);
        bg7Layout.setHorizontalGroup(
            bg7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bg7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74)
                    .addGroup(bg7Layout.createSequentialGroup()
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bg7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ids3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(waktu3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        bg7Layout.setVerticalGroup(
            bg7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bg7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(waktu3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bg7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel73)
                        .addComponent(jLabel75)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bg7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ids3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg7Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addContainerGap())))
        );

        jPanel5.setBackground(new java.awt.Color(240, 154, 40));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jLabel76.setFont(new java.awt.Font("Century", 0, 16)); // NOI18N
        jLabel76.setText("KETERANGAN KARYAWAN");

        jLabel77.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel77.setText("ID");

        jLabel78.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel78.setText("Nama");

        jLabel79.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel79.setText("Alamat");

        jLabel80.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel80.setText("No HP");

        jLabel81.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel81.setText("Tgl Kerja");

        jButton9.setText("<");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText(">");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        jButton11.setText("Info Nota");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel82.setText("Gaji");

        jLabel83.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel83.setText("Tgl Lahir");

        jLabel84.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel84.setText("Tgl Berhenti");

        jLabel85.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel85.setText("Bonus");

        kkrj.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        kkrj.setText("jLabel4");

        kbrh.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        kbrh.setText("jLabel4");

        kid.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        kalmt.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        khp.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        khp.setText("jLabel4");

        kgj.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        kgj.setText("jLabel4");

        kbns.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        kbns.setText("jLabel4");

        klhr.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        klhr.setText("jLabel4");

        knm.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10)
                        .addGap(13, 13, 13))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel81)
                            .addComponent(jLabel78)
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel80)
                            .addComponent(jLabel82)
                            .addComponent(jLabel85)
                            .addComponent(jLabel84)
                            .addComponent(jLabel83))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(kkrj, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(kbrh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(21, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel76)
                                .addGap(49, 49, 49))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kbns, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(klhr, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kgj, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(khp, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kalmt, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kid, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(knm, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel76)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(kid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(knm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kalmt, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel80)
                    .addComponent(khp, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kgj))
                .addGap(1, 1, 1)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kbns))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(klhr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kkrj))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kbrh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addContainerGap())
        );

        searchcombo5.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        searchcombo5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tgl Lahir", "Tgl Kerja", "Tgl Berhenti" }));

        jLabel55.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel55.setText("Sort by      :");

        descending4.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        descending4.setText("Descending");
        descending4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descending4MouseClicked(evt);
            }
        });
        descending4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descending4ActionPerformed(evt);
            }
        });

        ascending4.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        ascending4.setText("Ascending");
        ascending4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ascending4MouseClicked(evt);
            }
        });
        ascending4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascending4ActionPerformed(evt);
            }
        });

        bg6.setBackground(new java.awt.Color(240, 154, 40));

        idd1.setBackground(null);
        pelbutton.add(idd1);
        idd1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        idd1.setText("ID");

        nm1.setBackground(null);
        pelbutton.add(nm1);
        nm1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        nm1.setText("Nama");

        javax.swing.GroupLayout bg6Layout = new javax.swing.GroupLayout(bg6);
        bg6.setLayout(bg6Layout);
        bg6Layout.setHorizontalGroup(
            bg6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idd1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(nm1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        bg6Layout.setVerticalGroup(
            bg6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bg6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idd1)
                    .addComponent(nm1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ascending5.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        ascending5.setText("Search");
        ascending5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ascending5MouseClicked(evt);
            }
        });
        ascending5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascending5ActionPerformed(evt);
            }
        });

        viewall6.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        viewall6.setText("View All");
        viewall6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewall6MouseClicked(evt);
            }
        });
        viewall6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewall6ActionPerformed(evt);
            }
        });

        ascending7.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        ascending7.setText("Clear");
        ascending7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ascending7MouseClicked(evt);
            }
        });
        ascending7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascending7ActionPerformed(evt);
            }
        });

        ascending8.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        ascending8.setText("Search");
        ascending8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ascending8MouseClicked(evt);
            }
        });
        ascending8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascending8ActionPerformed(evt);
            }
        });

        jDateChooser5.setDateFormatString("dd-MM-yyyy");

        butkar.add(saat2);
        saat2.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        saat2.setSelected(true);
        saat2.setText("Saat");

        butkar.add(stlh2);
        stlh2.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        stlh2.setText("Setelah");

        butkar.add(sblm2);
        sblm2.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        sblm2.setText("Sebelum");
        sblm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sblm2ActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(240, 154, 40));

        jButton8.setText("OK");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        butbl.add(kar4);
        kar4.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        kar4.setSelected(true);
        kar4.setText("Karyawan");
        kar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kar4MouseClicked(evt);
            }
        });
        kar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kar4ActionPerformed(evt);
            }
        });

        butbl.add(sup4);
        sup4.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        sup4.setText("Supervisor");
        sup4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sup4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kar4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sup4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sup4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kar4)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panel8Layout = new javax.swing.GroupLayout(panel8);
        panel8.setLayout(panel8Layout);
        panel8Layout.setHorizontalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel8Layout.createSequentialGroup()
                        .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel8Layout.createSequentialGroup()
                                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panel8Layout.createSequentialGroup()
                                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(daftar)
                                    .addComponent(bg7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(logos7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38))
                    .addGroup(panel8Layout.createSequentialGroup()
                        .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel8Layout.createSequentialGroup()
                                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(searchcombo4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(searchcombo5, 0, 97, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel8Layout.createSequentialGroup()
                                        .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(saat2)
                                        .addGap(14, 14, 14))
                                    .addGroup(panel8Layout.createSequentialGroup()
                                        .addComponent(searchtext4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel8Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(stlh2))
                                    .addComponent(ascending5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sblm2)
                                    .addComponent(viewall6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ascending7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ascending8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel8Layout.createSequentialGroup()
                                .addComponent(bg6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ascending4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(descending4)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panel8Layout.setVerticalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel8Layout.createSequentialGroup()
                        .addComponent(bg7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(daftar))
                    .addComponent(logos7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel8Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchcombo4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchtext4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ascending5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewall6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ascending7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchcombo5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ascending8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saat2)
                        .addComponent(stlh2)
                        .addComponent(sblm2))
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bg6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(descending4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ascending4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        getContentPane().add(panel8, "cardkar");

        juduls1.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N
        juduls1.setText("FORM INPUT KARYAWAN");

        tabform2.setBackground(new java.awt.Color(240, 154, 40));
        tabform2.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        tabform2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabform2FocusGained(evt);
            }
        });
        tabform2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabform2MouseClicked(evt);
            }
        });

        tab7.setBackground(new java.awt.Color(240, 154, 40));
        tab7.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N

        jLabel72.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel72.setText("ID");

        jLabel86.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel86.setText("Nama");

        jLabel87.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel87.setText("Alamat");

        jLabel88.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel88.setText("Kota");

        jLabel89.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel89.setText("No HP");

        jLabel90.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel90.setText("Tgl Lahir");

        nm3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        almt3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        almt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almt3ActionPerformed(evt);
            }
        });

        hp3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        hp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hp3ActionPerformed(evt);
            }
        });

        e1.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        e1.setIcon(scalegmbr2(23,23));
        e1.setToolTipText("ID Karyawan tidak valid");
        e1.setMaximumSize(new java.awt.Dimension(23, 23));
        e1.setMinimumSize(new java.awt.Dimension(23, 23));
        e1.setPreferredSize(new java.awt.Dimension(23, 23));

        kt3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        kt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kt3ActionPerformed(evt);
            }
        });

        dt1.setDateFormatString("dd-MM-yyy");

        id3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N

        jLabel100.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel100.setText("Password");

        dt2.setDateFormatString("dd-MM-yyy");

        jLabel101.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel101.setText("Tgl Kerja");

        jLabel102.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel102.setText("Gaji");

        gj3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        gj3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gj3ActionPerformed(evt);
            }
        });

        e2.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        e2.setIcon(scalegmbr2(23,23));
        e2.setToolTipText("Password tidak valid");
        e2.setMaximumSize(new java.awt.Dimension(23, 23));
        e2.setMinimumSize(new java.awt.Dimension(23, 23));
        e2.setPreferredSize(new java.awt.Dimension(23, 23));

        e3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        e3.setIcon(scalegmbr2(23,23));
        e3.setToolTipText("Nama tidak valid");
        e3.setMaximumSize(new java.awt.Dimension(23, 23));
        e3.setMinimumSize(new java.awt.Dimension(23, 23));
        e3.setPreferredSize(new java.awt.Dimension(23, 23));

        e4.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        e4.setIcon(scalegmbr2(23,23));
        e4.setToolTipText("Alamat/Kota tidak valid");
        e4.setMaximumSize(new java.awt.Dimension(23, 23));
        e4.setMinimumSize(new java.awt.Dimension(23, 23));
        e4.setPreferredSize(new java.awt.Dimension(23, 23));

        e5.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        e5.setIcon(scalegmbr2(23,23));
        e5.setToolTipText("No HP tidak valid");
        e5.setMaximumSize(new java.awt.Dimension(23, 23));
        e5.setMinimumSize(new java.awt.Dimension(23, 23));
        e5.setPreferredSize(new java.awt.Dimension(23, 23));

        e6.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        e6.setIcon(scalegmbr2(23,23));
        e6.setToolTipText("Gaji tidak valid");
        e6.setMaximumSize(new java.awt.Dimension(23, 23));
        e6.setMinimumSize(new java.awt.Dimension(23, 23));
        e6.setPreferredSize(new java.awt.Dimension(23, 23));

        e7.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        e7.setIcon(scalegmbr2(23,23));
        e7.setToolTipText("Tgl Lahir tidak valid");
        e7.setMaximumSize(new java.awt.Dimension(23, 23));
        e7.setMinimumSize(new java.awt.Dimension(23, 23));
        e7.setPreferredSize(new java.awt.Dimension(23, 23));

        e8.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        e8.setIcon(scalegmbr2(23,23));
        e8.setToolTipText("Tgl Kerja tidak valid");
        e8.setMaximumSize(new java.awt.Dimension(23, 23));
        e8.setMinimumSize(new java.awt.Dimension(23, 23));
        e8.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout tab7Layout = new javax.swing.GroupLayout(tab7);
        tab7.setLayout(tab7Layout);
        tab7Layout.setHorizontalGroup(
            tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab7Layout.createSequentialGroup()
                        .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(dt1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(e7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab7Layout.createSequentialGroup()
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(dt2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(e8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab7Layout.createSequentialGroup()
                        .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel87)
                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel102, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel89, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(23, 23, 23)
                        .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab7Layout.createSequentialGroup()
                                .addComponent(gj3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(e6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab7Layout.createSequentialGroup()
                                .addComponent(nm3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(e3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab7Layout.createSequentialGroup()
                                .addComponent(almt3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(e4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(kt3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab7Layout.createSequentialGroup()
                                .addComponent(hp3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(e5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab7Layout.createSequentialGroup()
                                .addComponent(psw3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(e2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(tab7Layout.createSequentialGroup()
                        .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab7Layout.createSequentialGroup()
                                .addComponent(jLabel100)
                                .addGap(27, 27, 27)
                                .addComponent(id3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel72))
                        .addGap(18, 18, 18)
                        .addComponent(e1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        tab7Layout.setVerticalGroup(
            tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(e1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(id3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab7Layout.createSequentialGroup()
                        .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(psw3))
                            .addComponent(e2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(tab7Layout.createSequentialGroup()
                                .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab7Layout.createSequentialGroup()
                                        .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(nm3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(e4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(almt3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel87))))
                                    .addComponent(e3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(kt3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(hp3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(e5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(gj3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(e6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dt1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(e7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dt2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(e8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        tabform2.addTab("Karyawan Baru", tab7);

        tab8.setBackground(new java.awt.Color(240, 154, 40));
        tab8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tab8FocusGained(evt);
            }
        });

        jLabel91.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel91.setText("ID Karyawan Edit ");

        jLabel92.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel92.setText("Nama ");

        jLabel93.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel93.setText("Alamat ");

        jLabel94.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel94.setText("Gaji");
        jLabel94.setToolTipText("");

        cpsw.setOpaque(false);
        cpsw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpswActionPerformed(evt);
            }
        });

        nm4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nm4ActionPerformed(evt);
            }
        });

        calmt.setOpaque(false);
        calmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calmtActionPerformed(evt);
            }
        });

        kt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kt4ActionPerformed(evt);
            }
        });

        chp.setOpaque(false);
        chp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chpActionPerformed(evt);
            }
        });

        e9.setIcon( scalegmbr2(23,23));
        e9.setToolTipText("ID Karyawan tidak valid");
        e9.setMaximumSize(new java.awt.Dimension(23, 23));
        e9.setMinimumSize(new java.awt.Dimension(23, 23));
        e9.setPreferredSize(new java.awt.Dimension(23, 23));

        jLabel95.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel95.setText("Kota");

        okk2.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        okk2.setText("OK");
        okk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okk2ActionPerformed(evt);
            }
        });

        hp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hp4ActionPerformed(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel96.setText("Tgl Lahir");

        clhr.setOpaque(false);
        clhr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clhrActionPerformed(evt);
            }
        });

        dt3.setDateFormatString("dd-MM-yyyy");

        jLabel103.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel103.setText("Password");

        cnm.setOpaque(false);
        cnm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnmActionPerformed(evt);
            }
        });

        jLabel104.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel104.setText("No Hp");
        jLabel104.setToolTipText("");

        cgj.setOpaque(false);
        cgj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cgjActionPerformed(evt);
            }
        });

        gj4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gj4ActionPerformed(evt);
            }
        });

        jLabel105.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel105.setText("Tgl Kerja");

        ckrj.setOpaque(false);
        ckrj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckrjActionPerformed(evt);
            }
        });

        dt4.setDateFormatString("dd-MM-yyyy");

        e10.setIcon( scalegmbr2(23,23));
        e10.setToolTipText("Password tidak valid");
        e10.setMaximumSize(new java.awt.Dimension(23, 23));
        e10.setMinimumSize(new java.awt.Dimension(23, 23));
        e10.setPreferredSize(new java.awt.Dimension(23, 23));

        e11.setIcon( scalegmbr2(23,23));
        e11.setToolTipText("Nama tidak valid");
        e11.setMaximumSize(new java.awt.Dimension(23, 23));
        e11.setMinimumSize(new java.awt.Dimension(23, 23));
        e11.setPreferredSize(new java.awt.Dimension(23, 23));

        e12.setIcon( scalegmbr2(23,23));
        e12.setToolTipText("Alamat/Kota tidak valid");
        e12.setMaximumSize(new java.awt.Dimension(23, 23));
        e12.setMinimumSize(new java.awt.Dimension(23, 23));
        e12.setPreferredSize(new java.awt.Dimension(23, 23));

        e14.setIcon( scalegmbr2(23,23));
        e14.setToolTipText("Gaji tidak valid");
        e14.setMaximumSize(new java.awt.Dimension(23, 23));
        e14.setMinimumSize(new java.awt.Dimension(23, 23));
        e14.setPreferredSize(new java.awt.Dimension(23, 23));

        e15.setIcon( scalegmbr2(23,23));
        e15.setToolTipText("Tgl Lahir tidak valid");
        e15.setMaximumSize(new java.awt.Dimension(23, 23));
        e15.setMinimumSize(new java.awt.Dimension(23, 23));
        e15.setPreferredSize(new java.awt.Dimension(23, 23));

        e13.setIcon( scalegmbr2(23,23));
        e13.setToolTipText("No HP tidak valid");
        e13.setMaximumSize(new java.awt.Dimension(23, 23));
        e13.setMinimumSize(new java.awt.Dimension(23, 23));
        e13.setPreferredSize(new java.awt.Dimension(23, 23));

        e16.setIcon( scalegmbr2(23,23));
        e16.setToolTipText("Tgl Kerja tidak valid");
        e16.setMaximumSize(new java.awt.Dimension(23, 23));
        e16.setMinimumSize(new java.awt.Dimension(23, 23));
        e16.setPreferredSize(new java.awt.Dimension(23, 23));

        jLabel106.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel106.setText("Jabatan");

        cjbtn.setOpaque(false);
        cjbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cjbtnActionPerformed(evt);
            }
        });

        box2.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        box2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kasir", "Supervisor" }));

        jLabel107.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel107.setText("Bonus");

        bns4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bns4ActionPerformed(evt);
            }
        });

        cbns.setOpaque(false);
        cbns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnsActionPerformed(evt);
            }
        });

        e19.setIcon( scalegmbr2(23,23));
        e19.setToolTipText("Bonus tidak valid");
        e19.setMaximumSize(new java.awt.Dimension(23, 23));
        e19.setMinimumSize(new java.awt.Dimension(23, 23));
        e19.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout tab8Layout = new javax.swing.GroupLayout(tab8);
        tab8.setLayout(tab8Layout);
        tab8Layout.setHorizontalGroup(
            tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okk2)
                        .addGap(36, 36, 36))
                    .addGroup(tab8Layout.createSequentialGroup()
                        .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tab8Layout.createSequentialGroup()
                                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chp)
                                    .addComponent(clhr)
                                    .addComponent(cgj)
                                    .addComponent(ckrj)
                                    .addComponent(cjbtn)))
                            .addComponent(jLabel91)
                            .addGroup(tab8Layout.createSequentialGroup()
                                .addComponent(jLabel93)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(calmt))
                            .addGroup(tab8Layout.createSequentialGroup()
                                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel103, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cpsw, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cnm, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab8Layout.createSequentialGroup()
                                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbns)))
                        .addGap(18, 18, 18)
                        .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab8Layout.createSequentialGroup()
                                .addComponent(almt4, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(e12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(45, Short.MAX_VALUE))
                            .addGroup(tab8Layout.createSequentialGroup()
                                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tab8Layout.createSequentialGroup()
                                        .addComponent(bns4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(e19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(tab8Layout.createSequentialGroup()
                                        .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(tab8Layout.createSequentialGroup()
                                                .addComponent(dt3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(e15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(tab8Layout.createSequentialGroup()
                                                .addComponent(gj4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(e14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(75, 75, 75)
                                        .addComponent(err11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(kt4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(tab8Layout.createSequentialGroup()
                                        .addComponent(kodedit2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(e9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(tab8Layout.createSequentialGroup()
                                        .addComponent(nm4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(e11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(tab8Layout.createSequentialGroup()
                                        .addComponent(psw4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(e10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(tab8Layout.createSequentialGroup()
                                        .addComponent(hp4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(e13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(tab8Layout.createSequentialGroup()
                                        .addComponent(dt4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(e16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(box2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        tab8Layout.setVerticalGroup(
            tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(e9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel91)
                        .addComponent(kodedit2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(okk2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel103, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(cpsw, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(psw4))
                    .addComponent(e10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab8Layout.createSequentialGroup()
                        .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nm4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cnm, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(calmt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(almt4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(e12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(ckrj, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab8Layout.createSequentialGroup()
                                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                    .addComponent(kt4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(tab8Layout.createSequentialGroup()
                                        .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(hp4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(e13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(tab8Layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(err11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(tab8Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab8Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(e14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(gj4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addComponent(cgj, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(clhr, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dt3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dt4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel106, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(cjbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(box2))
                        .addGap(18, 18, 18)
                        .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbns, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(e19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tab8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bns4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(tab8Layout.createSequentialGroup()
                        .addComponent(e11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabform2.addTab("Edit Karyawan", tab8);

        tab9.setBackground(new java.awt.Color(240, 154, 40));

        jLabel97.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel97.setText("Masukkan ID Karyawan");

        jLabel98.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel98.setText("*Karyawan yang sudah aktif bekerja tidak bisa dihapus");

        kodehapus2.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        kodehapus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodehapus2ActionPerformed(evt);
            }
        });

        jLabel99.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel99.setText("*Klik Kanan pada ID Karyawan (Daftar Karyawan) untuk cara cepat");

        e17.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        e17.setIcon(scalegmbr2(23,23));
        e17.setToolTipText("ID Karyawan tidak valid");
        e17.setMaximumSize(new java.awt.Dimension(23, 23));
        e17.setMinimumSize(new java.awt.Dimension(23, 23));
        e17.setPreferredSize(new java.awt.Dimension(23, 23));

        box1.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        box1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hapus", "Pecat" }));
        box1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box1ActionPerformed(evt);
            }
        });

        e18.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        e18.setIcon(scalegmbr2(23,23));
        e18.setToolTipText("Tanggal  Pecat tidak valid");
        e18.setMaximumSize(new java.awt.Dimension(23, 23));
        e18.setMinimumSize(new java.awt.Dimension(23, 23));
        e18.setPreferredSize(new java.awt.Dimension(23, 23));

        javax.swing.GroupLayout tab9Layout = new javax.swing.GroupLayout(tab9);
        tab9.setLayout(tab9Layout);
        tab9Layout.setHorizontalGroup(
            tab9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab9Layout.createSequentialGroup()
                .addGroup(tab9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel99))
                    .addGroup(tab9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel98))
                    .addGroup(tab9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(tab9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab9Layout.createSequentialGroup()
                                .addComponent(box1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(e18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tab9Layout.createSequentialGroup()
                                .addComponent(jLabel97)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(kodehapus2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(e17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        tab9Layout.setVerticalGroup(
            tab9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab9Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(tab9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(kodehapus2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab9Layout.createSequentialGroup()
                        .addComponent(e17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(13, 13, 13)
                .addGroup(tab9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(box1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel99)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel98)
                .addContainerGap(341, Short.MAX_VALUE))
        );

        tabform2.addTab("Hapus/Pecat Karyawan", tab9);

        logos8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 100, 52), 2));
        logos8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logos8MouseEntered(evt);
            }
        });

        tmbh2.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        tmbh2.setText("Tambah");
        tmbh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmbh2ActionPerformed(evt);
            }
        });

        btl2.setFont(new java.awt.Font("Century", 0, 11)); // NOI18N
        btl2.setText("Clear");
        btl2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btl2MouseClicked(evt);
            }
        });
        btl2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btl2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel9Layout = new javax.swing.GroupLayout(panel9);
        panel9.setLayout(panel9Layout);
        panel9Layout.setHorizontalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel9Layout.createSequentialGroup()
                .addGroup(panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel9Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(juduls1))
                    .addGroup(panel9Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(tabform2, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logos8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btl2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tmbh2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        panel9Layout.setVerticalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel9Layout.createSequentialGroup()
                        .addComponent(logos8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(tmbh2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btl2))
                    .addGroup(panel9Layout.createSequentialGroup()
                        .addComponent(juduls1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tabform2, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        getContentPane().add(panel9, "cardinpel");

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
        );

        getContentPane().add(panel4, "card7");

        jMenu.setText("Buku");
        jMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuActionPerformed(evt);
            }
        });

        daftarbuku.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        daftarbuku.setText("Daftar Buku");
        daftarbuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                daftarbukuMouseClicked(evt);
            }
        });
        daftarbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftarbukuActionPerformed(evt);
            }
        });
        jMenu.add(daftarbuku);

        tmbhbuku.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        tmbhbuku.setText("Tambah & Edit Buku");
        tmbhbuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tmbhbukuMouseClicked(evt);
            }
        });
        tmbhbuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmbhbukuActionPerformed(evt);
            }
        });
        jMenu.add(tmbhbuku);

        jMenuBar1.add(jMenu);

        nota.setText("Nota");

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Transaksi");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        nota.add(jMenuItem10);

        notaakhir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        notaakhir.setText("Nota Akhir");
        notaakhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notaakhirActionPerformed(evt);
            }
        });
        nota.add(notaakhir);

        detailnota.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        detailnota.setText("Detail Nota");
        detailnota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailnotaActionPerformed(evt);
            }
        });
        nota.add(detailnota);

        jMenuBar1.add(nota);

        pelanggan.setText("Pelanggan");

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Daftar Pelanggan");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        pelanggan.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem8.setText("Tambah & Edit  Pelanggan");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        pelanggan.add(jMenuItem8);

        jMenuBar1.add(pelanggan);

        karyawan.setText("Karyawan");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Daftar Karyawan");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        karyawan.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Tambah & Edit Karyawan");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        karyawan.add(jMenuItem1);

        jMenuBar1.add(karyawan);

        jMenu3.setText("System");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Ubah Password");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem6.setText("Log Out");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel1ComponentShown
      
       
    }//GEN-LAST:event_panel1ComponentShown

    private void tblbukuComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tblbukuComponentShown
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblbukuComponentShown

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        
        
 
    }//GEN-LAST:event_searchActionPerformed

    private void viewallMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewallMouseClicked
        // TODO add your handling code here:
        //this.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SwingWorker mySwingWorker = new SwingWorker(){
            @Override
            protected Object doInBackground() throws Exception{ 
                bacatable();
               return null;
            }
        };
        mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("state")) {
               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                  jDialog1.dispose();
               }
            }
         }
      });
        mySwingWorker.execute();
        jDialog1.setVisible(true);
    }//GEN-LAST:event_viewallMouseClicked

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        // TODO add your handling code here:
        String regex="\\d+";
       
        if(searchtext.getText().isEmpty()){
           bersih(mdl);
            setTable();
        }
        else if(!searchtext.getText().matches(regex)&&(searchcombo.getSelectedItem()=="Kode"||searchcombo.getSelectedItem()=="TahunTerbit"||searchcombo.getSelectedItem()=="Harga")){
            bersih(mdl);
            setTable();
        }
        else{
         SwingWorker mySwingWorker = new SwingWorker(){
         @Override
         protected Object doInBackground() throws Exception {

              String sql;
             try{
                stm = con.createStatement();
                if(searchcombo.getSelectedItem()=="Kode"||searchcombo.getSelectedItem()=="TahunTerbit"||searchcombo.getSelectedItem()=="Harga"){
                     sql="select * from bukum where "+searchcombo.getSelectedItem().toString()+"="+searchtext.getText()+"";
                }
                else {
                     sql="select * from bukum where "+searchcombo.getSelectedItem().toString()+" LIKE '%"+searchtext.getText()+"%'";
                }
                rS = stm.executeQuery(sql);
                
                bersih(mdl);
                while(rS.next()){
                    try {
                        BufferedImage im = ImageIO.read(rS.getBinaryStream("Gambar"));
                        Image img=new ImageIcon(im).getImage();
                        ImageIcon resize=new ImageIcon(img.getScaledInstance(80,60,Image.SCALE_SMOOTH));
                        mdl.addRow(new Object[]{rS.getInt("Kode"),rS.getString("Judul"),rS.getString("Pengarang"),rS.getInt("TahunTerbit"),rS.getInt("Harga"),rS.getInt("Jumlah_Stok"),resize});
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                 
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
           
             
            return null;
         }
      };
        mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("state")) {
               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                  jDialog1.dispose();
               }
            }
         }
      });
        mySwingWorker.execute();
        jDialog1.setVisible(true);
          setTable(); 
        }
        
        
        
        
        
    }//GEN-LAST:event_searchMouseClicked

    private void descendingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descendingMouseClicked
        // TODO add your handling code here:
        if(mdl.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Tabel Kosong");
        }
        else if(kodebutton.isSelected()||judulbutton.isSelected()||pengarangbutton.isSelected()||tahunbutton.isSelected()||hargabutton.isSelected()){
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblbuku.getModel());
            tblbuku.setRowSorter(sorter);
            int i;
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            int columnIndexToSort;
        
            if(kodebutton.isSelected()){
                columnIndexToSort=0;
                sortKeys.clear();
                sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();  
            }
            else if(judulbutton.isSelected()){
               columnIndexToSort=1;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(pengarangbutton.isSelected()){
               columnIndexToSort=2;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(tahunbutton.isSelected()){
               columnIndexToSort=3;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(hargabutton.isSelected()){
               columnIndexToSort=4;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();

            }

            for(i=0;i<=6;i++){
                sorter.setSortable(i,false);
            }
        }
         
        
        
        
       
    }//GEN-LAST:event_descendingMouseClicked

   
    private void descendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descendingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descendingActionPerformed

    private void ascendingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ascendingMouseClicked
        // TODO add your handling code here:
        
        if(mdl.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Tabel Kosong");
        }
        else if(kodebutton.isSelected()||judulbutton.isSelected()||pengarangbutton.isSelected()||tahunbutton.isSelected()||hargabutton.isSelected()){
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblbuku.getModel());
            tblbuku.setRowSorter(sorter);
            int i;
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            int columnIndexToSort;
        
            if(kodebutton.isSelected()){
                columnIndexToSort=0;
                sortKeys.clear();
                sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();  
            }
            else if(judulbutton.isSelected()){
               columnIndexToSort=1;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(pengarangbutton.isSelected()){
               columnIndexToSort=2;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(tahunbutton.isSelected()){
               columnIndexToSort=3;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(hargabutton.isSelected()){
               columnIndexToSort=4;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();

            }

            for(i=0;i<=6;i++){
                sorter.setSortable(i,false);
            }
        }
         
    }//GEN-LAST:event_ascendingMouseClicked

    private void ascendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascendingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ascendingActionPerformed

    private void tblbukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbukuMouseClicked
        // TODO add your handling code here:
        int kolom;
        int baris;
        Point pt=evt.getPoint();
   
       if(SwingUtilities.isRightMouseButton(evt)&&"Admin".equals(jabatan)){
           
           if(tblbuku.columnAtPoint(pt)==0){
               gantiPanel("card3");
               tabform.setSelectedIndex(2);
               tmbh.setText("Hapus");
               formulir.setText("FORM HAPUS BUKU");
               
              int hasil=(int)tblbuku.getValueAt(tblbuku.rowAtPoint(pt),0);
               kodehapus.setText(Integer.toString(hasil));
               
           }
       }
       else if(SwingUtilities.isLeftMouseButton(evt)) {
          if(evt.getClickCount()==2){
         
            kolom=tblbuku.getSelectedColumn();
            if(kolom==6){
                
              
                SwingWorker mySwingWorker = new SwingWorker(){
                    int row=(int) tblbuku.getValueAt(tblbuku.getSelectedRow(),0);
                   
                    @Override
                    protected Object doInBackground() throws Exception{ 
                        if(first.size()==0){
                            gmbr=new PanelPic(con,row,first);
                            first.add(gmbr);
                        }
                        else {
                            int len=0;
                            while(len<=first.size()-1){
                                if(first.get(len).value==row){
                                    first.get(len).dispose();
                                    gmbr=new PanelPic(con,row,first);
                                    first.set(len, gmbr);
                                    break;
                                }
                                len++;
                            }
                            if(len>first.size()-1){
                                gmbr=new PanelPic(con,row,first);
                                if(gmbr.ada){
                                    first.add(gmbr); 
                                }
                               
                            }
                           
                        }
                       return null;
                    }
                };
                mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                 @Override
                 public void propertyChange(PropertyChangeEvent evt) {
                    if (evt.getPropertyName().equals("state")) {
                       if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                          jDialog1.dispose();
                          if(gmbr.ada){
                              gmbr.setVisible(true);
                              gmbr.setState(java.awt.Frame.NORMAL);
                          }
                          else {
                               gmbr.dispose();
                              JOptionPane.showMessageDialog(null,"Gambar tidak ditemukan");
                          }
                        
                          
                          
                       }
                    }
                 }
              });
                mySwingWorker.execute();
                jDialog1.setVisible(true);
                
                
            }
            else if(kolom==1&&jabatan!="Kasir"){
                gantiPanel("cardnota");
                searchcombo1.setSelectedIndex(1);
                searchtext1.setText(Integer.toString((int)tblbuku.getValueAt(tblbuku.getSelectedRow(),0)));
                search1MouseClicked(evt);
                
            }
        } 
          else if(tblbuku.getSelectedColumn()==0&&!"Kasir".equals(jabatan)){
           gantiPanel("card3");
            tabform.setSelectedIndex(1);
               tmbh.setText("Edit");
               formulir.setText("FORM EDIT BUKU");
               int hasil=(int)tblbuku.getValueAt(tblbuku.getSelectedRow(),0);
               kodedit.setText(Integer.toString(hasil));
               visible(1);
       }
               
        
       }
       
          
       
       
    }//GEN-LAST:event_tblbukuMouseClicked

    private void searchtextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchtextMouseEntered
        // TODO add your handling code here:
       
    }//GEN-LAST:event_searchtextMouseEntered

    private void logosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logosMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_logosMouseEntered

    private void tblbukuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbukuMouseEntered
        // TODO add your handling code here: 
        
        
    }//GEN-LAST:event_tblbukuMouseEntered

    private void tblbukuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbukuMouseExited
        // TODO add your handling code here:
      tblbuku.setDefaultRenderer(Object.class,new RenderTabel(-1,-1));
      tblbuku.setDefaultRenderer(ImageIcon.class,new RenderTabel(-1,-1));
      this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tblbukuMouseExited

    @SuppressWarnings("empty-statement")
    private void tblbukuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbukuMouseMoved
        // TODO add your handling code here:
       Point pt=evt.getPoint();
       int row=tblbuku.rowAtPoint(pt);
       int column=tblbuku.columnAtPoint(pt);
       tblbuku.setDefaultRenderer(Object.class,new RenderTabel(row,column));
       tblbuku.setDefaultRenderer(ImageIcon.class,new RenderTabel(row,column));
       if((column==1||column==0)&&!jabatan.equals("Kasir")){
           this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       }
       else if(column==6){
           this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       }
       else {
           this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
       }
        
            
    }//GEN-LAST:event_tblbukuMouseMoved

    private void daftarbukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftarbukuMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_daftarbukuMouseClicked

    private void tmbhbukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmbhbukuMouseClicked
        // TODO add your handling code here:
     
                                 
    }//GEN-LAST:event_tmbhbukuMouseClicked

    private void tmbhbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmbhbukuActionPerformed
        // TODO add your handling code here:
          gantiPanel("card3");
       
          
          
          
    }//GEN-LAST:event_tmbhbukuActionPerformed

    private void jMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuActionPerformed

    private void daftarbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftarbukuActionPerformed
        // TODO add your handling code here:
         gantiPanel("card2");
         
         
         
    }//GEN-LAST:event_daftarbukuActionPerformed

    private void logos1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logos1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_logos1MouseEntered

    private void waktuComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_waktuComponentShown
        // TODO add your handling code here:
       
    }//GEN-LAST:event_waktuComponentShown

    private void hargabukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargabukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargabukuActionPerformed

    private void pengarangbukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pengarangbukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pengarangbukuActionPerformed

    private void browseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseMouseClicked
        // TODO add your handling code here:
        JFileChooser jfc=new JFileChooser();
        jfc.setFileFilter(new FileFilter(){
        @Override
            public boolean accept(File f) {
                String name = f.getName().toLowerCase();
                return ((name.endsWith(".png") ||
                        name.endsWith(".jpg") ||
                        name.endsWith(".gif") )&&
                        f.length() <=0.5f * (1024 * 1024));
            }
        @Override
            public String getDescription() {
                return "Images Maks 200KB";
            }
        });
       
       //jfc.addPropertyChangeListener((PropertyChangeListener) josh);
        int returnVal =jfc.showOpenDialog(this);
             
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            file=jfc.getSelectedFile();
            String name=file.getAbsolutePath();
            Image image=new ImageIcon(name).getImage();
            gambarlabel.setIcon(new ImageIcon(image.getScaledInstance(gambarlabel.getWidth(),gambarlabel.getHeight(),SCALE_SMOOTH)));
            
            
        }
        
    }//GEN-LAST:event_browseMouseClicked

    private void tabformFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabformFocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tabformFocusGained

    private void tab2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tab2FocusGained
        // TODO add your handling code here:
     
    }//GEN-LAST:event_tab2FocusGained

    private void tabformMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabformMouseClicked
        // TODO add your handling code here:
        switch (tabform.getSelectedIndex()) {
            case 1:
                formulir.setText("FORM EDIT BUKU");
                tmbh.setText("Edit");
                break;
            case 2:
                formulir.setText("FORM HAPUS BUKU");
                tmbh.setText("Hapus");
                break;
            default:
                formulir.setText("FORM INPUT BUKU");
                tmbh.setText("Tambah");   
                break;
        }
    }//GEN-LAST:event_tabformMouseClicked

    private void btlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btlMouseClicked
        // TODO add your handling code here:
        switch (tabform.getSelectedIndex()) {
            case 0:
                kosong1();
                break;
            case 1:
                kosong2(false);
                break;
            default:
                kodehapus.setText("");
                error6.setVisible(false);
                break;
        }
        
    }//GEN-LAST:event_btlMouseClicked

    private void tmbhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmbhActionPerformed
        // TODO add your handling code here:
        berhasil=false;
        String regex="\\d+";
        switch (tabform.getSelectedIndex()) {
            case 0:   
               
                    
                    
                        SwingWorker mySwingWorker = new SwingWorker(){
                            @Override
                            protected Object doInBackground() throws Exception{
                                if(testambah()){
                                    String pil[]={"Ya","Tidak"};
                                    int exit=JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin menambah Buku dengan Kode = "+kodebuku.getText()+"?","Tambah Buku",YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,pil,pil[0]);
                                    if(exit==JOptionPane.YES_OPTION){
                                        berhasil=true;
                                        stm=con.createStatement();
                                        stm.executeUpdate("insert into bukum(Kode,Judul,Pengarang,TahunTerbit,Harga,Jumlah_Stok) VALUES("+kodebuku.getText()+","+"'"+judulbuku.getText()+"',"+"'"+pengarangbuku.getText()+"',"+tahuncombo.getSelectedItem()+","+hargabuku.getText()+","+stokbuku.getText()+")");

                                        stm.executeUpdate("delete from hapus where kode="+kodebuku.getText());
                                        try {
                                            FileInputStream fis;
                                            File image = new File(file.getAbsolutePath());
                                            pS=con.prepareStatement("update bukum set Gambar=? where Kode="+kodebuku.getText());
                                            fis = new FileInputStream(image);
                                            pS.setBinaryStream(1,fis,(int)image.length());
                                            pS.executeUpdate();  

                                        } catch (FileNotFoundException ex) {
                                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                                return null;
                            }
                        };
                        mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {
                            
                            @Override
                            public void propertyChange(PropertyChangeEvent evt) {
                                if (evt.getPropertyName().equals("state")) {
                                    if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                                        jDialog1.dispose();
                                    }
                                }
                            }
                        });
                        mySwingWorker.execute();
                        jDialog1.setVisible(true);
                        if(berhasil){
                            kode();
                            kosong1();
                            JOptionPane.showMessageDialog(this,"Buku berhasil ditambahkan");
                        }
                        
                        
                        
                    
            break;
            case 1:
         
            
                 
                 
                   
                   
                        
                             mySwingWorker = new SwingWorker(){
                                @Override
                                protected Object doInBackground() throws Exception{ 
                                    if(visible(2)){
                                        a=checkharga.isSelected();
                                        b=checktmbh.isSelected();
                                        c=checkkurang.isSelected();
                                        d=checkgambar.isSelected();
                                        if(a==false&&b==false&&c==false&&d==false){
       
                                            JOptionPane.showMessageDialog(null,"Pilih minimal 1 bagian untuk diedit"); 
        
                                    }            
                                        else if(beneredit(a,b,c,d)){
                                        String pil[]={"Ya","Tidak"};
                                        int exit=JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin mengedit Buku dengan Kode = "+kodedit.getText()+"?","Edit Buku",YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,pil,pil[0]);
                                        if(exit==JOptionPane.YES_OPTION){
                                        berhasil=true;  
                                     try {
                                        stm=con.createStatement();
                                        if(a){
                                          stm.executeUpdate("update bukum set Harga="+hargaedit.getText()+" where Kode="+kodedit.getText());  
                                        }
                                        if(b){
                                          rS=stm.executeQuery("select Jumlah_Stok from bukum where Kode="+kodedit.getText());
                                          rS.next();
                                          int i=rS.getInt("Jumlah_Stok")+Integer.parseInt(stokedit.getText());
                                          stm.executeUpdate("update bukum set Jumlah_Stok="+Integer.toString(i)+" where Kode="+kodedit.getText()); 
                                        }
                                        if(c){
                                          rS=stm.executeQuery("select Jumlah_Stok from bukum where Kode="+kodedit.getText());
                                          rS.next();
                                          int i=rS.getInt("Jumlah_Stok")-Integer.parseInt(stok1edit.getText());
                                          stm.executeUpdate("update bukum set Jumlah_Stok="+i+" where Kode="+kodedit.getText());
                                        }
                                        if(d){
                                            try {
                                                FileInputStream fis;
                                                File image = new File(file1.getAbsolutePath());
                                                pS=con.prepareStatement("update bukum set Gambar=? where Kode="+kodedit.getText());
                                                fis = new FileInputStream(image);
                                                pS.setBinaryStream(1,fis,(int)image.length());
                                                pS.executeUpdate();
                                                
                                            }catch (FileNotFoundException ex) {
                                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }   
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                        } 
                                    }
                                    }
                                    return null;
                                }
                             };
                            mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                                @Override
                                public void propertyChange(PropertyChangeEvent evt) {
                                   if (evt.getPropertyName().equals("state")) {
                                      if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                                         jDialog1.dispose();
                                      }
                                   }
                                    }
                                });
                            mySwingWorker.execute();
                            jDialog1.setVisible(true);
                            if(berhasil){
                                kosong2(false);
                                JOptionPane.showMessageDialog(this,"Buku berhasil diedit");
                            }
                            
                          
                   
                   
                      
                   
                
               
             
         
               
                
                break;
            default:
                
                if(kodehapus.getText().matches(regex)){
                   mySwingWorker = new SwingWorker(){
                        @Override
                        protected Object doInBackground() throws Exception{ 
                             try {
                                stm=con.createStatement();
                                rS=stm.executeQuery("select Kode from bukum where Kode="+kodehapus.getText());
                                if(rS.next()){
                                    rS=stm.executeQuery("select KodeBuku from beli where KodeBuku="+kodehapus.getText());
                                    if(!rS.next()){
                                        error6.setVisible(false);
                                        String pil[]={"Ya","Tidak"};
                                        int exit=JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin menghapus Buku dengan Kode = "+kodehapus.getText()+"?","Hapus Buku",YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,pil,pil[0]);
                                        if(exit==JOptionPane.YES_OPTION){
                                            stm.executeUpdate("delete from bukum where Kode="+kodehapus.getText());
                                            stm.executeUpdate("insert into hapus values("+kodehapus.getText()+")");
                                            kodehapus.setText("");
                                            
                                            berhasil=true;
                                        }
                                    }
                                    else {
                                        error6.setToolTipText("Kode Buku sudah aktif");
                                        error6.setVisible(true);
                                    }
                                }
                                else {
                                    error6.setToolTipText("Kode Buku tidak valid");
                                    error6.setVisible(true);
                                }
                            }
                            catch (SQLException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    
                            return null;
                        }
                    };
                    mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                           if (evt.getPropertyName().equals("state")) {
                              if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                                 jDialog1.dispose();
                              }
                           }
                        }
                    });
                    mySwingWorker.execute();
                    jDialog1.setVisible(true);
                    if(berhasil){
                        kode();
                        JOptionPane.showMessageDialog(this,"Buku berhasil dihapus");
                        
                    }
                }
                else{
                    error6.setToolTipText("Kode Buku tidak valid");
                    error6.setVisible(true);
                }       break;   
        }
    }//GEN-LAST:event_tmbhActionPerformed

    private void btlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_btlActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        String pil[]={"Ya","Tidak"};
        int exit=JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin keluar akun?","Keluar Akun",YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,pil,pil[0]);
        if(exit==JOptionPane.YES_OPTION){
            peskluar();
            Iterator<PanelPic> itr=first.listIterator();
            while(itr.hasNext()){
                PanelPic x=itr.next();
                x.dispose();
                itr.remove();
            }
            this.dispose();
            Login news=new Login();
            news.setVisible(true);
            
            
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        searchtext.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        String pil[]={"Ya","Tidak"};
        int exit=JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin keluar akun?","Keluar Akun",YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,pil,pil[0]);
        if(exit==JOptionPane.YES_OPTION){
            
        peskluar();
        Iterator<PanelPic> itr=first.listIterator();
        while(itr.hasNext()){
            PanelPic x=itr.next();
            x.dispose();
            itr.remove();
        }
            this.dispose();
            
            Login news=new Login();
            news.setVisible(true);
           
        }
    }//GEN-LAST:event_formWindowClosing

    private void error5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_error5MouseMoved
        // TODO add your handling code here:
      
        
    }//GEN-LAST:event_error5MouseMoved

    private void kodehapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodehapusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodehapusActionPerformed

    private void checkhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkhargaActionPerformed
        // TODO add your handling code here:
        if(checkharga.isSelected()){
            hargaedit.setEditable(true);
        }
        else {
            hargaedit.setEditable(false);
            err2.setVisible(false);
        }
        
    }//GEN-LAST:event_checkhargaActionPerformed

    private void checktmbhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checktmbhActionPerformed
        // TODO add your handling code here:
         if(checktmbh.isSelected()){
            stokedit.setEditable(true);
            checkkurang.setSelected(false);
            stok1edit.setEditable(false);
             stok1edit.setText("");
             err4.setVisible(false);
        }
         else {
            stokedit.setEditable(false);
            err3.setVisible(false);
            
            
         }
    }//GEN-LAST:event_checktmbhActionPerformed

    private void checkgambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkgambarActionPerformed
        // TODO add your handling code here:
        if(checkgambar.isSelected()){
            browse1.setEnabled(true);
        }
         else {
            browse1.setEnabled(false);
             gambaredit.setIcon(null);
             err5.setVisible(false);
            
         }
    }//GEN-LAST:event_checkgambarActionPerformed

    private void browse1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browse1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_browse1MouseClicked

    private void hargaeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaeditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaeditActionPerformed

    private void stok1editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stok1editActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stok1editActionPerformed

    private void checkkurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkkurangActionPerformed
        // TODO add your handling code here:
         if(checkkurang.isSelected()){
            stok1edit.setEditable(true);
            checktmbh.setSelected(false);
            stokedit.setEditable(false);
            stokedit.setText("");
             err3.setVisible(false);
        }
         else {
            stokedit.setEditable(false);
           
            err4.setVisible(false);
         }
    }//GEN-LAST:event_checkkurangActionPerformed

    private void browse1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browse1ActionPerformed
        // TODO add your handling code here:
         JFileChooser jfc=new JFileChooser();
        jfc.setFileFilter(new FileFilter(){
        @Override
            public boolean accept(File f) {
                String name = f.getName().toLowerCase();
                return ((name.endsWith(".png") ||
                        name.endsWith(".jpg") ||
                        name.endsWith(".gif")  )&&
                        f.length() <=0.5f * (1024 * 1024));
            }
        @Override
            public String getDescription() {
                return "Images Maks 200KB";
            }
        });
       
       //jfc.addPropertyChangeListener((PropertyChangeListener) josh);
        int returnVal =jfc.showOpenDialog(this);
         
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            file1=jfc.getSelectedFile();
            String name=file1.getAbsolutePath();
            Image image=new ImageIcon(name).getImage();
            gambaredit.setIcon(new ImageIcon(image.getScaledInstance(gambaredit.getWidth(),gambaredit.getHeight(),SCALE_SMOOTH)));
            
            
        }
    }//GEN-LAST:event_browse1ActionPerformed

    private void okkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okkActionPerformed
        // TODO add your handling code here:
        visible(1);
        
    }//GEN-LAST:event_okkActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        gantiPanel("card4");
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void tblbukuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblbukuPropertyChange
        // TODO add your handling code here:
      
    }//GEN-LAST:event_tblbukuPropertyChange

    private void waktu1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_waktu1ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_waktu1ComponentShown

    private void logos2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logos2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_logos2MouseEntered

    private void teksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teksActionPerformed

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        // TODO add your handling code here:
        tmbhs.setText("  Ganti  ");
        kods.setText("");
        jums.setText("");
        bisa=1;
        hps.setEnabled(false);
        btls.setEnabled(true);
        Edit.setEnabled(false);
        kods.setEditable(false);
    }//GEN-LAST:event_EditActionPerformed

    private void jumsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumsActionPerformed

    private void kodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodsActionPerformed

    private void defActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defActionPerformed
        // TODO add your handling code here:
        if(def.isSelected()){
            teks.setText("");
            teks.setEditable(false);
            oke.setEnabled(false);
            ganti.setEnabled(false);
            erry.setVisible(false);
            
        }
    }//GEN-LAST:event_defActionPerformed

    private void pelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pelaActionPerformed
        // TODO add your handling code here:
         if(pela.isSelected()){
            teks.setText("");
            oke.setEnabled(true);
            teks.setEditable(true);
            bayar.setVisible(true);
            byr.setVisible(true);
            
        }
    }//GEN-LAST:event_pelaActionPerformed

    private void okeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okeActionPerformed
        
         cekpelanggan(1);
        
    }//GEN-LAST:event_okeActionPerformed

    private void tmbhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmbhsActionPerformed
        // TODO add your handling code here:
        if("Tambah".equals(tmbhs.getText())){
            if(cekkode()){
                try{
                    stm=con.createStatement();
                    rS=stm.executeQuery("select Jumlah_Stok from bukum where Kode="+kods.getText());
                    rS.next();
                    if(rS.getInt("Jumlah_Stok")-Integer.parseInt(jums.getText())<0){
                        JOptionPane.showMessageDialog(null,"Stok buku tidak mencukupi");
                    }
                    else {
                        int stok=rS.getInt("Jumlah_Stok")-Integer.parseInt(jums.getText());
                        stm.executeUpdate("update bukum set Jumlah_Stok="+Integer.toString(stok)+" where Kode="+kods.getText());
                        int baris=mdl2.getRowCount()-1;
                        
                        while(baris!=-1){
                            if((int)mdl2.getValueAt(baris,0)==Integer.parseInt(kods.getText())){
                                mdl2.setValueAt((int)mdl2.getValueAt(baris,3)+Integer.parseInt(jums.getText()),baris,3);
                                mdl2.setValueAt((int)mdl2.getValueAt(baris,2)*(int)mdl2.getValueAt(baris,3),baris,4);
                                break;
                            }
                            baris--;
                        }
                        if(baris==-1){
                            
                            rS=stm.executeQuery("select Kode,Judul,Harga from bukum where Kode="+kods.getText());
                            rS.next();
                            int total=rS.getInt("Harga")*Integer.parseInt(jums.getText());
                            mdl2.addRow(new Object[]{rS.getInt("Kode"),rS.getString("Judul"),rS.getInt("Harga"),Integer.parseInt(jums.getText()),total}); 
                        }

                        tblnota.setModel(mdl2);
                        Edit.setEnabled(true);
                        hps.setEnabled(true);
                        kods.setText("");
                        jums.setText("");
                        hitung();
                    }
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        else if(tmbhs.getText()=="  Ganti  ") {
            if(cekkode()){
                try {
                    stm=con.createStatement();
                    rS=stm.executeQuery("select Jumlah_Stok from bukum where Kode="+kods.getText());
                    rS.next();
                    int hmm=rS.getInt("Jumlah_Stok");
                    
                    int stok=(int)mdl2.getValueAt(this.row,3)+rS.getInt("Jumlah_Stok");
                   
                    stm.executeUpdate("update bukum set Jumlah_Stok="+Integer.toString(stok)+" where Kode="+kods.getText());
                    ResultSet rS2;
                    Statement stms=con.createStatement();
                    rS2=stms.executeQuery("select Jumlah_Stok from bukum where Kode="+kods.getText());
                    rS2.next();
                    if(rS2.getInt("Jumlah_Stok")-Integer.parseInt(jums.getText())<0){
                        JOptionPane.showMessageDialog(null,"Stok buku tidak mencukupi");
                    }
                    else {
                        int stok2=rS2.getInt("Jumlah_Stok")-Integer.parseInt(jums.getText());
                        
                        stms.executeUpdate("update bukum set Jumlah_Stok="+Integer.toString(stok2)+" where Kode="+kods.getText());
                        mdl2.setValueAt(Integer.parseInt(jums.getText()),this.row,3);
                        int total=(int)mdl2.getValueAt(this.row,2)* Integer.parseInt(jums.getText());
                        mdl2.setValueAt(total,this.row,4);
                        tblnota.setModel(mdl2);
                        fin();
                        hitung(); 
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }
        }
        else {
            if(cekkode()){
                try {
                
                    stm=con.createStatement();
                    rS=stm.executeQuery("select Jumlah_Stok from bukum where Kode="+kods.getText());
                    rS.next();
                    int stok=rS.getInt("Jumlah_Stok")+Integer.parseInt(jums.getText());
                    stm.executeUpdate("update bukum set Jumlah_Stok="+Integer.toString(stok)+" where Kode="+kods.getText());
                    mdl2.removeRow(this.row);
                    //tblnota.setModel(mdl2);
                    fin();
                    if(mdl2.getRowCount()==0){
                        Edit.setEnabled(false);
                        hps.setEnabled(false);
                    }
                    hitung();
                } catch (SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
          
            
            
            
        }
        }
    }//GEN-LAST:event_tmbhsActionPerformed

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        // TODO add your handling code here:
        refreshmdl1();
    }//GEN-LAST:event_RefreshActionPerformed

    private void clearsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearsActionPerformed
        // TODO add your handling code here:
        
           String pil[]={"Ya","Tidak"};
           int exit=JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin reset nota?","Reset Nota",YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,pil,pil[0]);
           if(exit==JOptionPane.YES_OPTION){        
           peskluar();
           awalNota();
           }
        
    }//GEN-LAST:event_clearsActionPerformed

    private void tblkodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkodeMouseClicked
        // TODO add your handling code here:
        if(bisa==0){
            int row=tblkode.getSelectedRow();
            int col=tblkode.getSelectedColumn();
            int kode=(int) tblkode.getValueAt(row, col);
            kods.setText(Integer.toString(kode));
            
        }
        
    }//GEN-LAST:event_tblkodeMouseClicked

    private void tblnotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnotaMouseClicked
        // TODO add your handling code here:
        if(bisa==1){
            this.row=tblnota.getSelectedRow();
            int kode=(int)tblnota.getValueAt(row,0);
            int jum=(int)tblnota.getValueAt(row,3);
            kods.setText(Integer.toString(kode));
            jums.setText(Integer.toString(jum));
            
        }
    }//GEN-LAST:event_tblnotaMouseClicked

    private void hpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hpsActionPerformed
        // TODO add your handling code here:
       Edit.setEnabled(false);
       btls.setEnabled(true);
       tmbhs.setText("Simpan ");
       hps.setEnabled(false);
       bisa=1;
       kods.setEditable(false);
       jums.setEditable(false);
       kods.setText("");
       jums.setText("");
    }//GEN-LAST:event_hpsActionPerformed

    private void btlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlsActionPerformed
        // TODO add your handling code here:
        fin();
        errr1.setVisible(false);
        errr2.setVisible(false);
    }//GEN-LAST:event_btlsActionPerformed

    private void selseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selseActionPerformed
        // TODO add your handling code here:
        if(mdl2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Nota Barang masih kosong");
        }
        else {
            tmbhs.setEnabled(false);
            Edit.setEnabled(false);
            hps.setEnabled(false);
            btls.setEnabled(false);
            selse.setEnabled(false);
            ubah.setEnabled(true);
            bayar.setEnabled(true);
            byr.setEditable(true);
            kods.setText("");
           jums.setText("");
           kods.setEditable(false);
           jums.setEditable(false);
            bisa=-1;
           
        }
    }//GEN-LAST:event_selseActionPerformed

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        // TODO add your handling code here:
        bisa=0;
        tmbhs.setEnabled(true);
        selse.setEnabled(true);
        ubah.setEnabled(false);
        bayar.setEnabled(false);
        byr.setEditable(false);
        errx.setVisible(false);
        kods.setEditable(true);
        jums.setEditable(true);
        if(mdl2.getRowCount()!=0){
            Edit.setEnabled(true);
            hps.setEnabled(true);
        }
        
    }//GEN-LAST:event_ubahActionPerformed

    private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
        // TODO add your handling code here:
        String regex="\\d+";
        berhasil=false;
        if(def.isSelected()){
            
            if(byr.getText().matches(regex)&&Integer.parseInt(byr.getText())>=Integer.parseInt(totals.getText().substring(3))){
               errx.setVisible(false);
               
               SwingWorker mySwingWorker = new SwingWorker(){
                    @Override
                    protected Object doInBackground() throws Exception{
                       kembalian=inputnota("default",Integer.parseInt(byr.getText()));
                       return null;
                    }
                };
                mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                       if (evt.getPropertyName().equals("state")) {
                          if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                             jDialog1.dispose();
                          }
                       }
                    }
                 });
                mySwingWorker.execute();
                jDialog1.setVisible(true);
                
                    JOptionPane.showMessageDialog(null,"Pembayaran lunas\nKembali : Rp "+kembalian);
                    nonota();
                    awalNota();
                
               
                
            }
            else {
                errx.setVisible(true);
            }
        }
        else {
            
            if(byr.getText().matches(regex)&&Integer.parseInt(byr.getText())>=Integer.parseInt(totals.getText().substring(3))){
                    errx.setVisible(false);
                    SwingWorker mySwingWorker = new SwingWorker(){
                    @Override
                    protected Object doInBackground() throws Exception{
                         if(cekpelanggan(0)){
                             berhasil=true;
                            kembalian=inputnota(teks.getText(),Integer.parseInt(byr.getText()));
                         }
                            return null;
                        }
                    };
                    mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                           if (evt.getPropertyName().equals("state")) {
                              if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                                 jDialog1.dispose();
                              }
                           }
                        }
                     });
                    mySwingWorker.execute();
                    jDialog1.setVisible(true);
                        if(berhasil){
                            JOptionPane.showMessageDialog(this,"Pembayaran lunas\nKembali : Rp "+kembalian);
                            nonota();
                            awalNota();
                        }
                    
                }
                else {
                    errx.setVisible(true);
                }
        }
    }//GEN-LAST:event_bayarActionPerformed

    private void gantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gantiActionPerformed
        // TODO add your handling code here:
        oke.setEnabled(true);
        ganti.setEnabled(false);
        teks.setEditable(true);
    }//GEN-LAST:event_gantiActionPerformed

    private void logos3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logos3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_logos3MouseEntered

    private void tbldnotaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldnotaMouseMoved
        // TODO add your handling code here:
      Point pt=evt.getPoint();
       int row=tbldnota.rowAtPoint(pt);
       int column=tbldnota.columnAtPoint(pt);
       tbldnota.setDefaultRenderer(Object.class,new RenderTabel1(row,column));
       if(column==0||column==1){
           this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       }
       else {
           this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
       }
    }//GEN-LAST:event_tbldnotaMouseMoved

    private void tbldnotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldnotaMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            int row=tbldnota.getSelectedRow();
            int col=tbldnota.getSelectedColumn();
            if(col==1){
                gantiPanel("card2");
                int i=(int)tbldnota.getValueAt(row, col);
                searchtext.setText(Integer.toString(i));
                searchcombo.setSelectedIndex(0);
                searchMouseClicked(evt);
                
            }
            else if(col==0){
                gantiPanel("cardnot");
                searchcombo2.setSelectedIndex(0);
                String i=(String)tbldnota.getValueAt(row, col);
                searchtext2.setText(i);
                search2MouseClicked(evt);
            }
        }
    }//GEN-LAST:event_tbldnotaMouseClicked

    private void tbldnotaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldnotaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbldnotaMouseEntered

    private void tbldnotaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldnotaMouseExited
        // TODO add your handling code here:
         tbldnota.setDefaultRenderer(Object.class,new RenderTabel1(-1,-1));
         this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tbldnotaMouseExited

    private void tbldnotaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tbldnotaComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_tbldnotaComponentShown

    private void tbldnotaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbldnotaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tbldnotaPropertyChange

    private void searchtext1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchtext1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtext1MouseEntered

    private void search1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search1MouseClicked
        // TODO add your handling code here:
        String regex="\\d+";
        if(searchtext1.getText().isEmpty()){
            bersih(mdlnt);
            tbldnota.setModel(mdlnt);
            tbldnota.setRowSorter(null);
        }
        else if(searchcombo1.getSelectedItem()=="Kode Nota") {
            bersih(mdlnt);
            SwingWorker mySwingWorker = new SwingWorker(){
            @Override
            protected Object doInBackground() throws Exception{ 
                try {
                    stm=con.createStatement();
                    rS=stm.executeQuery("select * from beli where Kode LIKE '%"+searchtext1.getText()+"%'");
                    
                    while(rS.next()){
                      mdlnt.addRow(new Object[]{rS.getString("Kode"),rS.getInt("KodeBuku"),rS.getInt("Jumlah"),rS.getInt("Total")}); 
                    }
                    
                   
                } catch (SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            
               return null;
            }
            };
            mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
             @Override
             public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("state")) {
                   if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                      jDialog1.dispose();
                   }
                }
             }
            });
            mySwingWorker.execute();
            jDialog1.setVisible(true);
            tbldnota.setModel(mdlnt);
            tbldnota.setRowSorter(null); 
        }
        else {
            if(searchtext1.getText().matches(regex)){
                bersih(mdlnt);
                 SwingWorker mySwingWorker = new SwingWorker(){
                    @Override
                    protected Object doInBackground() throws Exception{ 
                        try {
                    
                            stm=con.createStatement();
                            rS=stm.executeQuery("select * from beli where KodeBuku="+searchtext1.getText());
                            
                            while(rS.next()){
                              mdlnt.addRow(new Object[]{rS.getString("Kode"),rS.getInt("KodeBuku"),rS.getInt("Jumlah"),rS.getInt("Total")}); 
                            }
                            
                            } catch (SQLException ex) {
                                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        
                       return null;
                    }
                };
                mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                 @Override
                 public void propertyChange(PropertyChangeEvent evt) {
                    if (evt.getPropertyName().equals("state")) {
                       if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                          jDialog1.dispose();
                       }
                    }
                 }
                });
                mySwingWorker.execute();
                jDialog1.setVisible(true);
                tbldnota.setModel(mdlnt);
                tbldnota.setRowSorter(null); 
            }
            else {
              bersih(mdlnt);
              tbldnota.setModel(mdlnt);
              tbldnota.setRowSorter(null);  
            }
        }
    }//GEN-LAST:event_search1MouseClicked

    private void search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_search1ActionPerformed

    private void viewall1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewall1MouseClicked
        // TODO add your handling code here:
         SwingWorker mySwingWorker = new SwingWorker(){
            @Override
            protected Object doInBackground() throws Exception{ 
                bacatabelnota();
               return null;
            }
        };
        mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("state")) {
               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                  jDialog1.dispose();
               }
            }
         }
      });
        mySwingWorker.execute();
        jDialog1.setVisible(true);
       
    }//GEN-LAST:event_viewall1MouseClicked

    private void descending1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descending1MouseClicked
        // TODO add your handling code here:
         if(mdlnt.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Tabel Kosong");
        }
        else if(kodebutton1.isSelected()||judulbutton1.isSelected()||Jumlah.isSelected()||Total.isSelected()){
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbldnota.getModel());
            tbldnota.setRowSorter(sorter);
            int i;
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            int columnIndexToSort;
        
            if(kodebutton1.isSelected()){
                columnIndexToSort=0;
                sortKeys.clear();
                sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();  
            }
            else if(judulbutton1.isSelected()){
               columnIndexToSort=1;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(Jumlah.isSelected()){
               columnIndexToSort=2;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(Total.isSelected()){
               columnIndexToSort=3;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }

            for(i=0;i<=3;i++){
                sorter.setSortable(i,false);
            }
        }
    }//GEN-LAST:event_descending1MouseClicked

    private void descending1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descending1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descending1ActionPerformed

    private void ascending1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ascending1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ascending1MouseClicked

    private void ascending1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascending1ActionPerformed
        // TODO add your handling code here:
         if(mdlnt.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Tabel Kosong");
        }
        else if(kodebutton1.isSelected()||judulbutton1.isSelected()||Jumlah.isSelected()||Total.isSelected()){
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbldnota.getModel());
            tbldnota.setRowSorter(sorter);
            int i;
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            int columnIndexToSort;
        
            if(kodebutton1.isSelected()){
                columnIndexToSort=0;
                sortKeys.clear();
                sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();  
            }
            else if(judulbutton1.isSelected()){
               columnIndexToSort=1;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(Jumlah.isSelected()){
               columnIndexToSort=2;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(Total.isSelected()){
               columnIndexToSort=3;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }

            for(i=0;i<=3;i++){
                sorter.setSortable(i,false);
            }
        }
    }//GEN-LAST:event_ascending1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        searchtext1.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void panel5ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel5ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_panel5ComponentShown

    private void detailnotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailnotaActionPerformed
        // TODO add your handling code here:
        gantiPanel("cardnota");
    }//GEN-LAST:event_detailnotaActionPerformed

    private void JumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JumlahActionPerformed

    private void logos4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logos4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_logos4MouseEntered

    private void tblnotMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnotMouseMoved
        // TODO add your handling code here:
        Point pt=evt.getPoint();
        int row=tblnot.rowAtPoint(pt);
        int col=tblnot.columnAtPoint(pt);
        tblnot.setDefaultRenderer(Object.class,new RenderTabel1(row,col));
        if(col==0||col==2){
           this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       }
        else if(col==1&&jabatan.equals("Admin")){
            this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
       else {
           this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
       }
    }//GEN-LAST:event_tblnotMouseMoved

    private void tblnotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnotMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            int row=tblnot.getSelectedRow();
            int col=tblnot.getSelectedColumn();
            if(col==0){
                gantiPanel("cardnota");
                searchtext1.setText((String)tblnot.getValueAt(row,0));
                searchcombo1.setSelectedIndex(0);
                search1MouseClicked(evt);
            }
            else if(col==2){
                if(!tblnot.getValueAt(row, col).equals("default")){
                gantiPanel("cardpel");
                searchtext3.setText((String)tblnot.getValueAt(row,2));
                searchcombo3.setSelectedIndex(0);
                search4MouseClicked(evt);
                SwingWorker mySwingWorker = new SwingWorker(){
                    @Override
                    protected Object doInBackground() throws Exception{ 
                        rowpoint=0;
                        keterangan(0);
                        tblpel.setRowSelectionInterval(rowpoint,rowpoint);
                        return null;
                    }
                };
                mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                       if (evt.getPropertyName().equals("state")) {
                          if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                             jDialog1.dispose();
                          }
                       }
                    }
                });
                mySwingWorker.execute();
                jDialog1.setVisible(true);
                current=isi1.getText();
                } 
            }
            else if(col==1&&jabatan.equals("Admin")){
                if(!tblnot.getValueAt(row, col).equals("0")){
                gantiPanel("cardkar");
                daftar.setText("DAFTAR KARYAWAN");
                state=0;
                kar4.setSelected(true);
                if(searchcombo4.getItemCount()==6){
                    searchcombo4.removeItemAt(5);
                }
                
                searchtext4.setText((String)tblnot.getValueAt(row,1));
                searchcombo4.setSelectedIndex(0);
                ascending5MouseClicked(evt);
                SwingWorker mySwingWorker = new SwingWorker(){
                    @Override
                    protected Object doInBackground() throws Exception{ 
                        rowpoint1=0;
                        karket(0);
                        tblkar.setRowSelectionInterval(rowpoint1,rowpoint1);
                        return null;
                    }
                };
                mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                       if (evt.getPropertyName().equals("state")) {
                          if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                             jDialog1.dispose();
                          }
                       }
                    }
                });
                mySwingWorker.execute();
                jDialog1.setVisible(true);
                current1=kid.getText();
                } 
            }
            
        }
    }//GEN-LAST:event_tblnotMouseClicked

    private void tblnotMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnotMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblnotMouseEntered

    private void tblnotMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnotMouseExited
        // TODO add your handling code here:
        tblnot.setDefaultRenderer(Object.class,new RenderTabel1(-1,-1));
        this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tblnotMouseExited

    private void tblnotComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tblnotComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_tblnotComponentShown

    private void tblnotPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblnotPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tblnotPropertyChange

    private void searchtext2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchtext2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtext2MouseEntered

    private void search2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search2MouseClicked
        // TODO add your handling code here:
        if(searchtext2.getText().isEmpty()){
            bersih(mdlak);
            tblnot.setModel(mdlak);
            tblnot.setRowSorter(null);
        }
        else {
            bersih(mdlak);
            SwingWorker mySwingWorker = new SwingWorker(){
            @Override
            protected Object doInBackground() throws Exception{ 
               try {
                stm=con.createStatement();
                String cari;
                if(searchcombo2.getSelectedIndex()==0){
                    rS=stm.executeQuery("select * from nota_akhir where Kode LIKE '%"+searchtext2.getText()+"%'");
                }
                else if(searchcombo2.getSelectedIndex()==1){
                    rS=stm.executeQuery("select * from nota_akhir where IDKaryawan LIKE '%"+searchtext2.getText()+"%'");
                }
                else {
                    rS=stm.executeQuery("select * from nota_akhir where No LIKE '%"+searchtext2.getText()+"%'");
                }
                
                
                while(rS.next()){
                    Date date,date1;
                    date=rS.getDate("TglBeli");
                    date1=rS.getTime("WaktuBeli");
                    SimpleDateFormat ft =new SimpleDateFormat ("dd-MM-yyyy"),ft1=new SimpleDateFormat ("HH:mm:ss");
                    mdlak.addRow(new Object[]{rS.getString("Kode"),rS.getString("IDKaryawan"),rS.getString("No"),ft.format(date),ft1.format(date1),rS.getInt("SubTotal")}); 
                }
            
                
                } catch (SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } 
                return null;
            }
            };
            mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
             @Override
             public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("state")) {
                   if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                      jDialog1.dispose();
                   }
                }
             }
            });
            mySwingWorker.execute();
            jDialog1.setVisible(true);
            tblnot.setRowSorter(null);
            tblnot.setModel(mdlak);
            
                   
        }
    }//GEN-LAST:event_search2MouseClicked

    private void search2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search2ActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_search2ActionPerformed

    private void viewall2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewall2MouseClicked
        // TODO add your handling code here:
         SwingWorker mySwingWorker = new SwingWorker(){
            @Override
            protected Object doInBackground() throws Exception{ 
               tblnot();
               return null;
            }
        };
        mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("state")) {
               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                  jDialog1.dispose();
               }
            }
         }
      });
        mySwingWorker.execute();
        jDialog1.setVisible(true);
       
    }//GEN-LAST:event_viewall2MouseClicked

    private void descending2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descending2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_descending2MouseClicked

    private void descending2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descending2ActionPerformed
        // TODO add your handling code here:
        if(mdlak.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Tabel Kosong");
        }
        else if(kodebutton2.isSelected()||idkarbut.isSelected()||idpel.isSelected()||tglbut.isSelected()||tglbut1.isSelected()){
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblnot.getModel());
            tblnot.setRowSorter(sorter);
            int i;
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            int columnIndexToSort;
        
            if(kodebutton2.isSelected()){
                columnIndexToSort=0;
                sortKeys.clear();
                sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();  
            }
            else if(idkarbut.isSelected()){
               columnIndexToSort=1;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(idpel.isSelected()){
               columnIndexToSort=2;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(tglbut.isSelected()){
               columnIndexToSort=3;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
               
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else {
               columnIndexToSort=5;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
               
               sorter.setSortKeys(sortKeys);
               sorter.sort(); 
            }

            for(i=0;i<=5;i++){
                sorter.setSortable(i,false);
            }
        }
    }//GEN-LAST:event_descending2ActionPerformed

    private void ascending2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ascending2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ascending2MouseClicked

    private void ascending2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascending2ActionPerformed
        // TODO add your handling code here:
        if(mdlak.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Tabel Kosong");
        }
        else if(kodebutton2.isSelected()||idkarbut.isSelected()||idpel.isSelected()||tglbut.isSelected()||tglbut1.isSelected()){
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblnot.getModel());
            tblnot.setRowSorter(sorter);
            int i;
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            int columnIndexToSort;
        
            if(kodebutton2.isSelected()){
                columnIndexToSort=0;
                sortKeys.clear();
                sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();  
            }
            else if(idkarbut.isSelected()){
               columnIndexToSort=1;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(idpel.isSelected()){
               columnIndexToSort=2;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else if(tglbut.isSelected()){
               columnIndexToSort=3;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
              
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
            else {
               columnIndexToSort=5;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
               
               sorter.setSortKeys(sortKeys);
               sorter.sort();  
            }
            for(i=0;i<=5;i++){
                sorter.setSortable(i,false);
            }
        }
    }//GEN-LAST:event_ascending2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        searchtext2.setText("");
        jDateChooser1.setCalendar(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void panel6ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel6ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_panel6ComponentShown

    private void notaakhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notaakhirActionPerformed
        // TODO add your handling code here:
        gantiPanel("cardnot");
    }//GEN-LAST:event_notaakhirActionPerformed

    private void search3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_search3MouseClicked

    private void search3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search3ActionPerformed
        // TODO add your handling code here:
        if(jDateChooser1.getDate()==null){
            bersih(mdlak);
            tblnot.setModel(mdlak);
            tblnot.setRowSorter(null);
        }
        else {
              bersih(mdlak);
             SwingWorker mySwingWorker = new SwingWorker(){
            @Override
            protected Object doInBackground() throws Exception{ 
                try {
                    stm=con.createStatement();
                    SimpleDateFormat ft=new SimpleDateFormat("YYYY-MM-dd");
                    if(saat.isSelected()){
                        rS=stm.executeQuery("select * from nota_akhir where TglBeli='"+ft.format(jDateChooser1.getDate())+"' order by WaktuBeli ASC");
                    }
                    else if(setelah.isSelected()){
                        rS=stm.executeQuery("select * from nota_akhir where TglBeli>'"+ft.format(jDateChooser1.getDate())+"' order by WaktuBeli ASC");
                    }
                    else {
                        rS=stm.executeQuery("select * from nota_akhir where TglBeli<'"+ft.format(jDateChooser1.getDate())+"' order by WaktuBeli ASC");
                    }


                    while(rS.next()){
                        Date date,date1;
                        date=rS.getDate("TglBeli");
                        date1=rS.getTime("WaktuBeli");
                        ft =new SimpleDateFormat ("dd-MM-yyyy");
                        mdlak.addRow(new Object[]{rS.getString("Kode"),rS.getString("IDKaryawan"),rS.getString("No"),ft.format(date),date1.toString(),rS.getInt("SubTotal")}); 
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
               return null;
            }
        };
        mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("state")) {
               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                  jDialog1.dispose();
               }
            }
         }
      });
        mySwingWorker.execute();
        jDialog1.setVisible(true);
        tblnot.setRowSorter(null);
        tblnot.setModel(mdlak);    
            
            
        }
    }//GEN-LAST:event_search3ActionPerformed

    private void jDateChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseClicked
        // TODO add your handling code here:
    
    }//GEN-LAST:event_jDateChooser1MouseClicked

    private void search2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_search2MouseEntered

    private void jDialog1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDialog1MouseClicked
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jDialog1MouseClicked

    private void logos5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logos5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_logos5MouseEntered

    private void tblpelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpelMouseMoved
        // TODO add your handling code here:
        Point pt=evt.getPoint();
        int r=tblpel.rowAtPoint(pt);
        int c=tblpel.columnAtPoint(pt);
        
        
       if(c==0&&!jabatan.equals("Kasir")){
             tblpel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
       else if(c==1)  {
             tblpel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
       else {
           tblpel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
       }
        tblpel.setDefaultRenderer(Object.class,new RenderTabel12(r,c));
       
    }//GEN-LAST:event_tblpelMouseMoved

    private void tblpelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpelMouseClicked
        // TODO add your handling code here:
      
        Point pt=evt.getPoint();
        int rows=tblpel.rowAtPoint(pt);
        int col=tblpel.columnAtPoint(pt);
        
        if(SwingUtilities.isRightMouseButton(evt)&&col==0&&!jabatan.equals("Kasir")){
            gantiPanel("cardpels");
            juduls.setText("FORM HAPUS PELANGGAN");
            tmbh1.setText("Hapus");
            c10.setVisible(false);
            tabform1.setSelectedIndex(2);
            kodehapus1.setText((String)tblpel.getValueAt(rows,0));
        }
        else if(SwingUtilities.isLeftMouseButton(evt)) {
            if(col==0&&!jabatan.equals("Kasir")){
               gantiPanel("cardpels");
                juduls.setText("FORM EDIT PELANGGAN");
                tmbh1.setText("Edit");
                editpel();
                tabform1.setSelectedIndex(1);
                kodedit1.setText((String)tblpel.getValueAt(rows,0));
                editpel1(1);
                
            }
            else if(col==1) {
                rowpoint=rows;
                SwingWorker mySwingWorker = new SwingWorker(){
                    @Override
                    protected Object doInBackground() throws Exception{
                        keterangan(rows);
                        if(!ada){
                           
                            cleanket();
                            
                            
                        }
                        return null;
                    }
                };
                mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                       if (evt.getPropertyName().equals("state")) {
                          if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                             jDialog1.dispose();
                          }
                       }
                    }
                });
                mySwingWorker.execute();
                jDialog1.setVisible(true);
                current=isi1.getText();
            }
        }
       
        
       
        
    }//GEN-LAST:event_tblpelMouseClicked

    private void tblpelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblpelMouseEntered

    private void tblpelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpelMouseExited
        // TODO add your handling code here:
        tblpel.setDefaultRenderer(Object.class,new RenderTabel12(-1,-1));
        tblpel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_tblpelMouseExited

    private void tblpelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tblpelComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_tblpelComponentShown

    private void tblpelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblpelPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tblpelPropertyChange

    private void searchtext3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchtext3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtext3MouseEntered

    private void search4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search4MouseClicked
        // TODO add your handling code here:
      
        String regex="\\d+";
        if(searchtext3.getText().isEmpty()){
            bersih(mdlpel);
            
        }
        else{
            SwingWorker mySwingWorker = new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception{ 
                   stm=con.createStatement();
                   bersih(mdlpel);
                   rS=stm.executeQuery("select ID,Nama from pelangganm where ID!='default' AND "+(String)searchcombo3.getSelectedItem()+" LIKE '%"+searchtext3.getText()+"%'");
                   while(rS.next()){
                       mdlpel.addRow(new Object[]{rS.getString("ID"),rS.getString("Nama")});
                   }
                   
                   
                   return null;
                }
            };
            mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if (evt.getPropertyName().equals("state")) {
                        if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                          jDialog1.dispose();
                        }
                    }
                 }
            });
            mySwingWorker.execute();
            jDialog1.setVisible(true);
        } 
        tblpel.setRowSorter(null);
        tblpel.setModel(mdlpel);
        getRowPoint();
        
    }//GEN-LAST:event_search4MouseClicked

    private void search4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search4ActionPerformed

    private void viewall3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewall3MouseClicked
        // TODO add your handling code here:
        
        SwingWorker mySwingWorker = new SwingWorker(){
            @Override
            protected Object doInBackground() throws Exception{ 
                bacatblpel();
                getRowPoint();
               return null;
            }
        };
        mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
               if (evt.getPropertyName().equals("state")) {
                  if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                     jDialog1.dispose();
                  }
               }
            }
        });
        mySwingWorker.execute();
        jDialog1.setVisible(true);
        
    }//GEN-LAST:event_viewall3MouseClicked

    private void descending3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descending3MouseClicked
        // TODO add your handling code here:
         
        
    }//GEN-LAST:event_descending3MouseClicked

    private void descending3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descending3ActionPerformed
        // TODO add your handling code here:
        if(mdlpel.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Tabel Kosong");
        }
        else if(idd.isSelected()||nm.isSelected()){
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblpel.getModel());
            tblpel.setRowSorter(sorter);
            int i;
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            int columnIndexToSort;
        
            if(idd.isSelected()){
                columnIndexToSort=0;
                sortKeys.clear();
                sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();  
                
            }
            else if(nm.isSelected()){
               columnIndexToSort=1;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
              
            }
            
           
            for(i=0;i<=1;i++){
                sorter.setSortable(i,false);
            }
           getRowPoint();
           
            
        }
        
    }//GEN-LAST:event_descending3ActionPerformed

    private void ascending3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ascending3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ascending3MouseClicked

    private void ascending3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascending3ActionPerformed
        // TODO add your handling code here:
        if(mdlpel.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Tabel Kosong");
        }
        else if(idd.isSelected()||nm.isSelected()){
            
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblpel.getModel());
            tblpel.setRowSorter(sorter);
            int i;
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            int columnIndexToSort;
        
            if(idd.isSelected()){
                columnIndexToSort=0;
                sortKeys.clear();
                sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();  
                
            }
            else if(nm.isSelected()){
               columnIndexToSort=1;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
               
            }
           

            for(i=0;i<=1;i++){
                sorter.setSortable(i,false);
            }
            getRowPoint();
            
        }
    }//GEN-LAST:event_ascending3ActionPerformed

    private void waktu2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_waktu2ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_waktu2ComponentShown

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        searchtext3.setText("");
        jDateChooser2.setCalendar(null);
       
    }//GEN-LAST:event_jButton4ActionPerformed

    private void panel7ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel7ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_panel7ComponentShown

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        gantiPanel("cardpel");
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void search5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_search5MouseClicked

    private void search5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search5ActionPerformed
        // TODO add your handling code here:
       
        if(jDateChooser2.getDate()==null){
            bersih(mdlpel);
            tblpel.setRowSorter(null);
            tblpel.setModel(mdlpel); 
            getRowPoint();

        }
        else {
              bersih(mdlpel);
             SwingWorker mySwingWorker = new SwingWorker(){
            @Override
            protected Object doInBackground() throws Exception{ 
                try {
                    stm=con.createStatement();
                    SimpleDateFormat ft=new SimpleDateFormat("YYYY-MM-dd");
                    if(saat1.isSelected()){
                        rS=stm.executeQuery("select ID,Nama from pelangganm where TglLahir='"+ft.format(jDateChooser2.getDate())+"'");
                    }
                    else if(setelah1.isSelected()){
                        rS=stm.executeQuery("select ID,Nama from pelangganm where TglLahir>'"+ft.format(jDateChooser2.getDate())+"' ");
                    }
                    else {
                        rS=stm.executeQuery("select ID,Nama from pelangganm where TglLahir<'"+ft.format(jDateChooser2.getDate())+"'");
                    }
                        
                    while(rS.next()){
                        mdlpel.addRow(new Object[]{rS.getString("ID"),rS.getString("Nama")}); 
                    }
                    tblpel.setRowSorter(null);
                    tblpel.setModel(mdlpel); 
                    getRowPoint();

                } catch (SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
               return null;
            }
        };
        mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("state")) {
               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                  jDialog1.dispose();
               }
            }
         }
      });
        mySwingWorker.execute();
        jDialog1.setVisible(true);
           
            
            
        }
        
    }//GEN-LAST:event_search5ActionPerformed

    private void almtsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almtsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_almtsActionPerformed

    private void hpssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hpssActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hpssActionPerformed

    private void ceknmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ceknmActionPerformed
        // TODO add your handling code here:
        if(ceknm.isSelected()){
            nms1.setEditable(true);
            
        }
        else {
            nms1.setEditable(false);
            nms1.setText("");
            c6.setVisible(false);
        }
    }//GEN-LAST:event_ceknmActionPerformed

    private void nms1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nms1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nms1ActionPerformed

    private void cekalmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekalmtActionPerformed
        // TODO add your handling code here:
          if(cekalmt.isSelected()){
            almts1.setEditable(true);
            kots1.setEditable(true);
            
        }
        else {
            almts1.setEditable(false);
            almts1.setText("");
            c7.setVisible(false);
            kots1.setEditable(false);
            kots1.setText("");
            c8.setVisible(false);
        }
    }//GEN-LAST:event_cekalmtActionPerformed

    private void kots1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kots1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kots1ActionPerformed

    private void cekhpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekhpActionPerformed
        // TODO add your handling code here:
        if(cekhp.isSelected()){
            hpss1.setEditable(true);
            
        }
        else {
           hpss1.setEditable(false);
           hpss1.setText("");
            c8.setVisible(false);
        }
    }//GEN-LAST:event_cekhpActionPerformed

    private void okk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okk1ActionPerformed
       
        editpel1(1);
    }//GEN-LAST:event_okk1ActionPerformed

    private void tab5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tab5FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tab5FocusGained

    private void kodehapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodehapus1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodehapus1ActionPerformed

    private void tabform1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabform1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tabform1FocusGained

    private void tabform1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabform1MouseClicked
        // TODO add your handling code here:
        if(tabform1.getSelectedIndex()==0){
            juduls.setText("FORM INPUT PELANGGAN");
            tmbh1.setText("Tambah");
        }
        else if(tabform1.getSelectedIndex()==1){
            juduls.setText("FORM EDIT PELANGGAN");
            tmbh1.setText("Edit");
        }
        else {
             juduls.setText("FORM HAPUS PELANGGAN");
             tmbh1.setText("Hapus");
        }
    }//GEN-LAST:event_tabform1MouseClicked

    private void logos6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logos6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_logos6MouseEntered

    private void tmbh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmbh1ActionPerformed
        // TODO add your handling code here:
        berhasil=false;
        if(tabform1.getSelectedIndex()==0){
           
                    SwingWorker mySwingWorker = new SwingWorker(){
                        @Override
                        protected Object doInBackground() throws Exception{ 
                             if(cekpel1()){
                                String pil[]={"Ya","Tidak"};
                                int exit=JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin menambah Pelanggan dengan ID= "+id1.getText()+"?","Tambah Pelanggan",YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,pil,pil[0]);
                                 if(exit==JOptionPane.YES_OPTION){
                                    berhasil=true;
                                    stm=con.createStatement();
                                    SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");
                                    stm.executeUpdate("insert into pelangganm values('"+id1.getText()+"','"+nms.getText()+"','"+almts.getText()+","+kots.getText()+"','"+hpss.getText()+"','"+ft.format(jDateChooser3.getDate())+"')");
                                    stm.executeUpdate("delete from hapuspel where ID='"+id1.getText()+"'");
                                 }
                             }     
                            return null;
                        }
                    };
                    mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                           if (evt.getPropertyName().equals("state")) {
                              if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                                 jDialog1.dispose();
                              }
                           }
                        }
                     });
                    mySwingWorker.execute();
                    jDialog1.setVisible(true);
                    if(berhasil){
                        id1.setText(awalID());
                        inputpel();
                        JOptionPane.showMessageDialog(this,"Pelanggan berhasil ditambahkan");
                    }
                
            
        }
        else if(tabform1.getSelectedIndex()==1){
            
                   SwingWorker mySwingWorker = new SwingWorker(){
                        @Override
                        protected Object doInBackground() throws Exception{ 
                            if(editpel1(0)){
                            boolean a=ceknm.isSelected();
                            boolean b=cekalmt.isSelected();
                            boolean c=cekhp.isSelected(); 
                            boolean d=cektgl.isSelected();
                            if(a==false&&b==false&&c==false&&d==false){
                               JOptionPane.showMessageDialog(null,"Pilih minimal 1 bagian untuk diedit");
                            }
                            else if(editpel3(a,b,c,d)){
                                String pil[]={"Ya","Tidak"};
                                int exit=JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin mengedit Pelanggan dengan ID= "+id1.getText()+"?","Tambah Pelanggan",YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,pil,pil[0]);
                                if(exit==JOptionPane.YES_OPTION){ 
                                    berhasil=true;
                                    if(a){
                                        stm.executeUpdate("update pelangganm set Nama='"+nms1.getText()+"' where ID='"+kodedit1.getText()+"'");
                                    }
                                    if(b){
                                        stm.executeUpdate("update pelangganm set Alamat='"+almts1.getText()+","+kots1.getText()+"' where ID='"+kodedit1.getText()+"'");
                                    }
                                    if(c){
                                        stm.executeUpdate("update pelangganm set NoHp='"+hpss1.getText()+"' where ID='"+kodedit1.getText()+"'");
                                    }
                                    if(d){
                                        SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");
                                        stm.executeUpdate("update pelangganm set TglLahir='"+ft.format(jDateChooser4.getDate())+"' where ID='"+kodedit1.getText()+"'");
                                    }

                                }
                            }
                            }
                           return null;
                        }
                    };
                    mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                           if (evt.getPropertyName().equals("state")) {
                              if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                                 jDialog1.dispose();
                              }
                           }
                        }
                    });
                    mySwingWorker.execute();
                    jDialog1.setVisible(true);
                    if(berhasil){
                        editpel();
                        JOptionPane.showMessageDialog(this,"Pelanggan berhasil diedit");
                    }
                
            
        }
        else {
            
            String regex="\\d+";
            if(!kodehapus1.getText().isEmpty()){
                SwingWorker mySwingWorker = new SwingWorker(){
                    @Override
                    protected Object doInBackground() throws Exception{ 
                       try {
                            stm=con.createStatement();
                            rS=stm.executeQuery("select ID from pelangganm where ID='"+kodehapus1.getText()+"' AND ID!='default'");
                            if(rS.next()){
                               rS=stm.executeQuery("select No from nota where No='"+kodehapus1.getText()+"'");
                               if(!rS.next()){
                                   rS=stm.executeQuery("select No from karyawanm where ID='"+ID+"'");
                                   rS.next();
                                   int no=rS.getInt("No");
                                   String regex="^"+Integer.toString(no)+"[A-Z]+";
                                   if(kodehapus1.getText().matches(regex)||jabatan.equals("Admin")){
                                        c10.setVisible(false);
                                        String pil[]={"Ya","Tidak"};
                                        int exit=JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin menghapus Pelanggan dengan ID= "+kodehapus1.getText()+"?","Hapus Pelanggan",YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,pil,pil[0]);
                                        if(exit==JOptionPane.YES_OPTION){
                                            stm.executeUpdate("delete from pelangganm where ID='"+kodehapus1.getText()+"'");
                                            stm.executeUpdate("insert into hapuspel VALUES('"+kodehapus1.getText()+"')");
                                            berhasil=true;
                                        }
                                   }
                                   else {
                                      c10.setToolTipText("Akses Hapus ditolak");
                                      c10.setVisible(true);
                                   }
                                  
                               }
                               else {
                                  c10.setToolTipText("ID Pelanggan sudah aktif");
                                  c10.setVisible(true);  
                               }
                            }
                            else {
                               c10.setToolTipText("ID Pelanggan tidak valid");
                               c10.setVisible(true); 
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       return null;
                    }
                };
                mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                       if (evt.getPropertyName().equals("state")) {
                          if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                             jDialog1.dispose();
                          }
                       }
                    }
                });
                mySwingWorker.execute();
                jDialog1.setVisible(true);
                if(berhasil){
                    id1.setText(awalID());
                    kodehapus1.setText("");
                    JOptionPane.showMessageDialog(this,"Pelanggan berhasil dihapus");
                }
            }
            else {
                c10.setToolTipText("ID Pelanggan tidak valid");
                c10.setVisible(true);
            }
        }
    }//GEN-LAST:event_tmbh1ActionPerformed

    private void btl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btl1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btl1MouseClicked

    private void btl1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btl1ActionPerformed
        // TODO add your handling code here:
       if(tabform1.getSelectedIndex()==0){
           inputpel();
       }
       else if(tabform1.getSelectedIndex()==1){
           editpel();
       }
       else {
          kodehapus1.setText("");
          c10.setVisible(false);
       }
    }//GEN-LAST:event_btl1ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        gantiPanel("cardpels");
       
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void kotsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kotsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kotsActionPerformed

    private void hpss1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hpss1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hpss1ActionPerformed

    private void cektglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cektglActionPerformed
        // TODO add your handling code here:
        if(cektgl.isSelected()){
            jDateChooser4.setEnabled(true);
            
        }
        else {
           jDateChooser4.setEnabled(false);
           jDateChooser4.setCalendar(null);
            c9.setVisible(false);
        }
    }//GEN-LAST:event_cektglActionPerformed

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code here:
        if(!isi1.getText().isEmpty()){
            gantiPanel("cardnot");
            searchcombo2.setSelectedIndex(2);
            searchtext2.setText(isi1.getText());
            search2MouseClicked(evt);
        }
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if(tblpel.getRowCount()!=0){
            SwingWorker mySwingWorker = new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception{ 
                   
                    if(rowpoint==tblpel.getRowCount()-1){
                        rowpoint=0;
                        keterangan(rowpoint);
                        
                    }
                    else {
                        rowpoint++;
                        keterangan(rowpoint);
                
                       
                    }
                    
                    if(!ada){
                        cleanket();
                    }
                    tblpel.setRowSelectionInterval(rowpoint,rowpoint);
                    
                    return null;
                }
            };
            mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                   if (evt.getPropertyName().equals("state")) {
                      if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                         jDialog1.dispose();
                      }
                   }
                }
             });
            mySwingWorker.execute();
            jDialog1.setVisible(true);
            current=isi1.getText();
            
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(tblpel.getRowCount()!=0){
            SwingWorker mySwingWorker = new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception{
                     
                    if(rowpoint==0||rowpoint==-1){
                        rowpoint=tblpel.getRowCount()-1;
                        keterangan(rowpoint);
                    }
                    else {
                        rowpoint--;
                        keterangan(rowpoint);
                    }
                    if(!ada){
                        cleanket();
                    }
                    tblpel.setRowSelectionInterval(rowpoint,rowpoint);
                    return null;
                }
            };
            mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                   if (evt.getPropertyName().equals("state")) {
                      if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                         jDialog1.dispose();
                      }
                   }
                }
             });
            mySwingWorker.execute();
            jDialog1.setVisible(true);
            current=isi1.getText();
        }
         
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tblkodeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkodeMouseMoved
        // TODO add your handling code here:
        if(bisa==0){
           this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       }
       else {
           this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
       }
    }//GEN-LAST:event_tblkodeMouseMoved

    private void tblkodeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkodeMouseExited
        // TODO add your handling code here:
           this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
       
    }//GEN-LAST:event_tblkodeMouseExited

    private void tblnotaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnotaMouseMoved
        // TODO add your handling code here:
        if(bisa==1){
           this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       }
       else {
           this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
       }
    }//GEN-LAST:event_tblnotaMouseMoved

    private void tblnotaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnotaMouseExited
        // TODO add your handling code here:
           this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
       
    }//GEN-LAST:event_tblnotaMouseExited

    private void logos7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logos7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_logos7MouseEntered

    private void tblkarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkarMouseMoved
        // TODO add your handling code here:
        Point pt=evt.getPoint();
        int rows=tblkar.rowAtPoint(pt);
        int cols=tblkar.columnAtPoint(pt);
        tblkar.setDefaultRenderer(Object.class,new RenderTabel12(rows, cols));
        tblkar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_tblkarMouseMoved

    private void tblkarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkarMouseClicked
        // TODO add your handling code here:
         Point pt=evt.getPoint();
        int rows=tblkar.rowAtPoint(pt);
        int col=tblkar.columnAtPoint(pt);
        
        if(SwingUtilities.isRightMouseButton(evt)&&col==0){
            gantiPanel("cardinpel");
            juduls1.setText("FORM HAPUS KARYAWAN");
            tmbh2.setText("Simpan");
            kar3();
            tabform2.setSelectedIndex(2);
            kodehapus2.setText((String)tblkar.getValueAt(rows,0));
        }
        else if(SwingUtilities.isLeftMouseButton(evt)) {
            if(col==0){
               gantiPanel("cardinpel");
                juduls1.setText("FORM EDIT KARYAWAN");
                tmbh2.setText("Edit");
                kar2(false);
                tabform2.setSelectedIndex(1);
                kodedit2.setText((String)tblkar.getValueAt(rows,0));
                ckar2(1);
                
            }
            else if(col==1) {
                rowpoint1=rows;
                SwingWorker mySwingWorker = new SwingWorker(){
                    @Override
                    protected Object doInBackground() throws Exception{
                        karket(rows);
                        return null;
                    }
                };
                mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                       if (evt.getPropertyName().equals("state")) {
                          if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                             jDialog1.dispose();
                          }
                       }
                    }
                });
                mySwingWorker.execute();
                jDialog1.setVisible(true);
                current1=kid.getText();
            }
        }
    }//GEN-LAST:event_tblkarMouseClicked

    private void tblkarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblkarMouseEntered

    private void tblkarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkarMouseExited
        // TODO add your handling code here:
        tblkar.setDefaultRenderer(Object.class,new RenderTabel12(-1,-1));
        
    }//GEN-LAST:event_tblkarMouseExited

    private void tblkarComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tblkarComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_tblkarComponentShown

    private void tblkarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblkarPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tblkarPropertyChange

    private void searchtext4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchtext4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtext4MouseEntered

    private void waktu3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_waktu3ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_waktu3ComponentShown

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if(tblkar.getRowCount()!=0){
            SwingWorker mySwingWorker = new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception{
                     
                    if(rowpoint1==0||rowpoint1==-1){
                        rowpoint1=tblkar.getRowCount()-1;
                       karket(rowpoint1);
                    }
                    else {
                        rowpoint1--;
                        karket(rowpoint1);
                    }
                    tblkar.setRowSelectionInterval(rowpoint1,rowpoint1);
                    return null;
                }
            };
            mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                   if (evt.getPropertyName().equals("state")) {
                      if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                         jDialog1.dispose();
                      }
                   }
                }
             });
            mySwingWorker.execute();
            jDialog1.setVisible(true);
            current1=kid.getText();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
         if(tblkar.getRowCount()!=0){
            SwingWorker mySwingWorker = new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception{ 
                   
                    if(rowpoint1==tblkar.getRowCount()-1){
                        rowpoint1=0;
                       karket(rowpoint1);
                        
                    }
                    else {
                        rowpoint1++;
                        karket(rowpoint1);
                
                       
                    }
                    tblkar.setRowSelectionInterval(rowpoint1,rowpoint1);
                    
                    return null;
                }
            };
            mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                   if (evt.getPropertyName().equals("state")) {
                      if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                         jDialog1.dispose();
                      }
                   }
                }
             });
            mySwingWorker.execute();
            jDialog1.setVisible(true);
            current1=kid.getText();
            
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        // TODO add your handling code here:
        if(!kid.getText().isEmpty()){
            gantiPanel("cardnot");
            searchcombo2.setSelectedIndex(1);
            searchtext2.setText(kid.getText());
            search2MouseClicked(evt);
        }
        
    }//GEN-LAST:event_jButton11MouseClicked

    private void panel8ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel8ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_panel8ComponentShown

    private void descending4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descending4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_descending4MouseClicked

    private void descending4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descending4ActionPerformed
        // TODO add your handling code here:
        if(mdlkar.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Tabel Kosong");
        }
        else if(idd1.isSelected()||nm1.isSelected()){
            
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblkar.getModel());
            tblkar.setRowSorter(sorter);
            int i;
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            int columnIndexToSort;
        
            if(idd1.isSelected()){
                columnIndexToSort=0;
                sortKeys.clear();
                sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();  
               
            }
            else if(nm1.isSelected()){
               columnIndexToSort=1;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
               
            }
           

            for(i=0;i<=1;i++){
                sorter.setSortable(i,false);
            }
            getRowPoint1();
        }
    }//GEN-LAST:event_descending4ActionPerformed

    private void ascending4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ascending4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ascending4MouseClicked

    private void ascending4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascending4ActionPerformed
        // TODO add your handling code here:
        if(mdlkar.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Tabel Kosong");
        }
        else if(idd1.isSelected()||nm1.isSelected()){
            
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblkar.getModel());
            tblkar.setRowSorter(sorter);
            int i;
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            int columnIndexToSort;
        
            if(idd1.isSelected()){
                columnIndexToSort=0;
                sortKeys.clear();
                sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();  
               
            }
            else if(nm1.isSelected()){
               columnIndexToSort=1;
               sortKeys.clear();
               sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
               
            }
           

            for(i=0;i<=1;i++){
                sorter.setSortable(i,false);
            }
            getRowPoint1();
        }
        
    }//GEN-LAST:event_ascending4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        gantiPanel("cardkar");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void ascending5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ascending5MouseClicked
        // TODO add your handling code here:
        String regex="\\d+";
        if(searchtext4.getText().isEmpty()){
            bersih(mdlkar);
            tblkar.setRowSorter(null);
            tblkar.setModel(mdlkar);
        }
        else{
            SwingWorker mySwingWorker = new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception{ 
                   stm=con.createStatement();
                   bersih(mdlkar);
                   if(searchcombo4.getSelectedIndex()==4||searchcombo4.getSelectedIndex()==5){
                      if(searchtext4.getText().matches(regex)){
                        if(state==0){
                            rS=stm.executeQuery("select ID,Nama from karyawanm where ID!='0' AND Gaji="+searchtext4.getText());
                        } 
                        else{
                            if(searchcombo4.getSelectedIndex()==4){
                                rS=stm.executeQuery("select k.ID as ID,k.Nama as Nama from karyawanm as k,supervisor as s where k.ID!='0' AND k.ID=s.ID AND k.Gaji="+searchtext4.getText());
                            }
                            else {
                                rS=stm.executeQuery("select k.ID as ID,k.Nama as Nama from karyawanm as k,supervisor as s where k.ID!='0' AND k.ID=s.ID AND s.Bonus="+searchtext4.getText());
                            }
                        }
                      }
                      
                   }
                   else {
                       if(state==0){
                          rS=stm.executeQuery("select ID,Nama from karyawanm where ID!='0' AND "+(String)searchcombo4.getSelectedItem()+" LIKE '%"+searchtext4.getText()+"%'");
                      } 
                      else{
                          rS=stm.executeQuery("select k.ID as ID,k.Nama as Nama from karyawanm as k,supervisor as s where k.ID!='0' AND k.ID=s.ID AND k."+(String)searchcombo4.getSelectedItem()+" LIKE '%"+searchtext4.getText()+"%'");
                      } 
                   }
                   
                   while(rS.next()){
                       mdlkar.addRow(new Object[]{rS.getString("ID"),rS.getString("Nama")});
                   }
                   tblkar.setRowSorter(null);
                   tblkar.setModel(mdlkar);
                  
                   getRowPoint1();
                   return null;
                }
            };
            mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if (evt.getPropertyName().equals("state")) {
                        if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                          jDialog1.dispose();
                        }
                    }
                 }
            });
            mySwingWorker.execute();
            jDialog1.setVisible(true);
        } 
        
    }//GEN-LAST:event_ascending5MouseClicked

    private void ascending5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascending5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ascending5ActionPerformed

    private void viewall6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewall6MouseClicked
        // TODO add your handling code here:
        SwingWorker mySwingWorker = new SwingWorker(){
            @Override
            protected Object doInBackground() throws Exception{ 
                bacakar(state);
               getRowPoint1();
               return null;
            }
        };
        mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("state")) {
               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                  jDialog1.dispose();
               }
            }
         }
      });
        mySwingWorker.execute();
        jDialog1.setVisible(true);
    }//GEN-LAST:event_viewall6MouseClicked

    private void viewall6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewall6ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_viewall6ActionPerformed

    private void ascending7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ascending7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ascending7MouseClicked

    private void ascending7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascending7ActionPerformed
        // TODO add your handling code here:
        searchtext4.setText("");
        jDateChooser5.setCalendar(null);
    }//GEN-LAST:event_ascending7ActionPerformed

    private void ascending8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ascending8MouseClicked
        // TODO add your handling code here:
        if(jDateChooser5.getCalendar()==null){
            bersih(mdlkar);
            tblkar.setRowSorter(null);
            tblkar.setModel(mdlkar); 
        }
        else {
            SwingWorker mySwingWorker = new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception{ 
                    stm=con.createStatement();
                    String z;
                    bersih(mdlkar);
                    if(searchcombo5.getSelectedIndex()==0){
                        z="TglLahir";
                    }
                    else if(searchcombo5.getSelectedIndex()==1){
                        z="TglKerja";
                    }
                    else{
                        z="TglBerhenti";
                    }
                
                    SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");
                    if(saat2.isSelected()){
                        if(state==0){
                            rS=stm.executeQuery("select ID,Nama from karyawanm where ID!='0' AND "+z+"='"+ft.format(jDateChooser5.getDate())+"'");
                        }
                        else{
                            rS=stm.executeQuery("select k.ID as ID,k.Nama as Nama from karyawanm as k,supervisor as s where k.ID!='0' AND k.ID=s.ID AND k."+z+"='"+ft.format(jDateChooser5.getDate())+"'");
                        }
                    }
                    else if(stlh2.isSelected()){
                        if(state==0){
                            rS=stm.executeQuery("select ID,Nama from karyawanm where ID!='0' AND "+z+">'"+ft.format(jDateChooser5.getDate())+"'");
                        }
                        else{
                           rS=stm.executeQuery("select k.ID as ID,k.Nama as Nama from karyawanm as k,supervisor as s where k.ID!='0' AND k.ID=s.ID AND k."+z+">'"+ft.format(jDateChooser5.getDate())+"'"); 
                        }
                    }
                    else {
                       if(state==0){
                            rS=stm.executeQuery("select ID,Nama from karyawanm where ID!='0' AND "+z+"<'"+ft.format(jDateChooser5.getDate())+"'");
                            
                        }
                        else{
                            rS=stm.executeQuery("select k.ID as ID,k.Nama as Nama from karyawanm as k,supervisor as s where k.ID!='0' AND k.ID=s.ID AND k."+z+"<'"+ft.format(jDateChooser5.getDate())+"'");
                        }
                    }
                    while(rS.next()){
                        mdlkar.addRow(new Object[]{rS.getString("ID"),rS.getString("Nama")});
                    }
                    tblkar.setRowSorter(null);
                    tblkar.setModel(mdlkar);

                   return null;
                }
            };
            mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                   if (evt.getPropertyName().equals("state")) {
                      if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                         jDialog1.dispose();
                      }
                   }
                }
            });
            mySwingWorker.execute();
            jDialog1.setVisible(true);
            getRowPoint1();
        }
    }//GEN-LAST:event_ascending8MouseClicked

    private void ascending8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascending8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ascending8ActionPerformed

    private void kar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kar4ActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_kar4ActionPerformed

    private void kar4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kar4MouseClicked
        // TODO add your handling code here:
      
    }//GEN-LAST:event_kar4MouseClicked

    private void sup4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sup4MouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_sup4MouseClicked

    private void sblm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sblm2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sblm2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
        SwingWorker mySwingWorker = new SwingWorker(){
            @Override
            protected Object doInBackground() throws Exception{ 
                if(kar4.isSelected()){
                    daftar.setText("DAFTAR KARYAWAN");
                    state=0;
                    searchcombo4.removeItemAt(5);
                     bacakar(state);
                }
                else {
                    daftar.setText("DAFTAR SUPERVISOR");
                    state=1;
                    if(searchcombo4.getItemCount()==5){
                        searchcombo4.addItem("Bonus");
                    }
                    
                     bacakar(state);
                }
                getRowPoint1();
               
               return null;
            }
        };
        mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("state")) {
               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                  jDialog1.dispose();
               }
            }
         }
      });
        mySwingWorker.execute();
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton8MouseClicked

    private void logos8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logos8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_logos8MouseEntered

    private void tmbh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmbh2ActionPerformed
        // TODO add your handling code here:
        berhasil=false;
        switch (tabform2.getSelectedIndex()) {
            case 0:
                {
                    SwingWorker mySwingWorker = new SwingWorker(){
                        @Override
                        protected Object doInBackground() throws Exception{
                            if(cekkar1()){
                                String pil[]={"Ya","Tidak"};
                                int exit=JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin menambah Karyawan dengan ID= "+id3.getText()+"?","Tambah Karyawan",YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,pil,pil[0]);
                                if(exit==JOptionPane.YES_OPTION){
                                    berhasil=true;
                                    SimpleDateFormat ft=new SimpleDateFormat("yyy-MM-dd");
                                    SimpleDateFormat ft1=new SimpleDateFormat("yyy-MM-dd");
                                    stm=con.createStatement();
                                    stm.executeUpdate("insert into karyawanm values('"+id3.getText()+"','"+ambilno()+"','"+nm3.getText()+"',md5('"+String.valueOf(psw3.getPassword())+"'),'"+almt3.getText()+","+kt3.getText()+"',"+hp3.getText()+","+gj3.getText()+",'"+ft.format(dt1.getDate())+"','"+ft1.format(dt2.getDate())+"',null)");
                                    stm.executeUpdate("delete from hapusno where No="+ambilno());
                                }
                            }
                            return null;
                        }
                    };
                    mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (evt.getPropertyName().equals("state")) {
                                if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                                    jDialog1.dispose();
                                }
                            }
                        }
                    });
                    mySwingWorker.execute();
                    jDialog1.setVisible(true);
                    if(berhasil){
                        kar1();
                        JOptionPane.showMessageDialog(this,"Karyawan berhasil ditambahkan");
                    }
                    break;
                }
            case 1:
                {
                    SwingWorker mySwingWorker = new SwingWorker(){
                        @Override
                        protected Object doInBackground() throws Exception{
                            if(ckar2(0)){
                                a=cpsw.isSelected();
                                b=cnm.isSelected();
                                c=calmt.isSelected();
                                d=chp.isSelected();
                                e=cgj.isSelected();
                                f=clhr.isSelected();
                                g=ckrj.isSelected();
                                h=cjbtn.isSelected();
                                i=cbns.isSelected();
                                if(a==false&&b==false&&c==false&&d==false&&e==false&&f==false&&g==false&&h==false&&i==false){
                                    JOptionPane.showMessageDialog(null,"Pilih minimal 1 bagian untuk diedit");
                                }
                                else if(cekkar2(a,b,c,d,e,f,g,i)){
                                    String pil[]={"Ya","Tidak"};
                                    int exit=JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin mengedit Karyawan dengan ID= "+kodedit2.getText()+"?","Edit Karyawan",YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,pil,pil[0]);
                                    if(exit==JOptionPane.YES_OPTION){
                                        berhasil=true;
                                        if(a){
                                            stm.executeUpdate("update karyawanm set Password=md5('"+String.valueOf(psw4.getPassword())+"') where ID='"+kodedit2.getText()+"'");
                                        }
                                        if(b){
                                            stm.executeUpdate("update karyawanm set Nama='"+nm4.getText()+"' where ID='"+kodedit2.getText()+"'");
                                        }
                                        if(c){
                                            stm.executeUpdate("update karyawanm set Alamat='"+almt4.getText()+","+kt4.getText()+"' where ID='"+kodedit2.getText()+"'");
                                        }
                                        if(d){ 
                                            stm.executeUpdate("update karyawanm set NoHP='"+hp4.getText()+"' where ID='"+kodedit2.getText()+"'");
                                        }
                                        if(e){
                                            stm.executeUpdate("update karyawanm set Gaji='"+gj4.getText()+"' where ID='"+kodedit2.getText()+"'");
                                        }
                                        if(f){
                                            SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");
                                            stm.executeUpdate("update karyawanm set TglLahir='"+ft.format(dt3.getDate())+"' where ID='"+kodedit2.getText()+"'");
                                            
                                        }
                                        if(g){
                                            SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");
                                            stm.executeUpdate("update karyawanm set TglKerja='"+ft.format(dt4.getDate())+"' where ID='"+kodedit2.getText()+"'");
                                        }
                                        if(h){
                                            
                                            if(box2.getSelectedIndex()==0){
                                                stm.executeUpdate("delete from supervisor where ID='"+kodedit2.getText()+"'");
                                            }
                                            else {
                                                stm.executeUpdate("insert into supervisor(ID) values('"+kodedit2.getText()+"')");
                                            }
                                        }
                                        if(i){
                                            stm.executeUpdate("update supervisor set Bonus='"+bns4.getText()+"' where ID='"+kodedit2.getText()+"'");
                                        }
                                        
                                        
                                    }
                                    
                                }
                            }
                            return null;
                        }  
                    };      mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (evt.getPropertyName().equals("state")) {
                                if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                                    jDialog1.dispose();
                                }
                            }
                        }
                    });     mySwingWorker.execute();
                    jDialog1.setVisible(true);
                    if(berhasil){
                        kar2(false);
                        JOptionPane.showMessageDialog(this,"Karyawan berhasil diedit");
                    }       break;
                }
            default:
                {
                    SwingWorker mySwingWorker = new SwingWorker(){
                        @Override
                        protected Object doInBackground() throws Exception{
                            if(cekkar3()){
                                if(box1.getSelectedIndex()==0){
                                    String pil[]={"Ya","Tidak"};
                                    kon=0;
                                    int exit=JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin menghapus Karyawan dengan ID= "+kodehapus2.getText()+"?","Hapus Karyawan",YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,pil,pil[0]);
                                    if(exit==JOptionPane.YES_OPTION){
                                        berhasil=true;
                                        rS=stm.executeQuery("select No from karyawanm where ID='"+kodehapus2.getText()+"'");
                                        rS.next();
                                        int no=rS.getInt("No");
                                        stm.executeUpdate("insert into hapusno values("+Integer.toString(no)+")");
                                        stm.executeUpdate("delete from karyawanm where ID='"+kodehapus2.getText()+"'");
                                    }
                                    
                                }
                                else {
                                    kon=1;
                                    String pil[]={"Ya","Tidak"};
                                    int exit=JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin memecat Karyawan dengan ID= "+kodehapus2.getText()+"?","Pecat Karyawan",YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,pil,pil[0]);
                                    if(exit==JOptionPane.YES_OPTION){
                                        berhasil=true;
                                        SimpleDateFormat ft=new SimpleDateFormat("yyy-MM-dd");
                                        stm.executeUpdate("update karyawanm set TglBerhenti=CURDATE() where ID='"+kodehapus2.getText()+"'");
                                    }
                                }
                            }
                            return null;
                        }
                    };  mySwingWorker.addPropertyChangeListener(new PropertyChangeListener(){
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (evt.getPropertyName().equals("state")) {
                                if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                                    jDialog1.dispose();
                                }
                            }
                        }
                    }); mySwingWorker.execute();
                    jDialog1.setVisible(true);
                    if(berhasil){
                        kar3();
                        if(kon==0){
                            JOptionPane.showMessageDialog(this,"Karyawan berhasil dihapus");
                        }
                        else {
                            JOptionPane.showMessageDialog(this,"Karyawan telah dipecat");
                        }
                    }   break;
                }
        }
    }//GEN-LAST:event_tmbh2ActionPerformed

    private void btl2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btl2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btl2MouseClicked

    private void btl2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btl2ActionPerformed
        // TODO add your handling code here:
        switch (tabform2.getSelectedIndex()) {
            case 0:
                kar1();
                break;
            case 1:
                kar2(false);
                break;
            default:
                kar3();
                break;
        }
    }//GEN-LAST:event_btl2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        gantiPanel("cardinpel");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void tabform2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabform2MouseClicked
        // TODO add your handling code here:
        if(tabform2.getSelectedIndex()==0){
            juduls1.setText("FORM INPUT KARYAWAN");
            tmbh2.setText("Tambah");
        }
        else if(tabform2.getSelectedIndex()==1){
            juduls1.setText("FORM EDIT KARYAWAN");
            tmbh2.setText("Edit");
        }
        else {
            juduls1.setText("FORM HAPUS KARYAWAN");
            tmbh2.setText("Simpan");
        }
    }//GEN-LAST:event_tabform2MouseClicked

    private void tabform2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabform2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tabform2FocusGained

    private void kodehapus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodehapus2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodehapus2ActionPerformed

    private void tab8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tab8FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tab8FocusGained

    private void clhrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clhrActionPerformed
        // TODO add your handling code here:
        if(clhr.isSelected()){
            dt3.setEnabled(true);
           
        }
        else {
            
            dt3.setEnabled(false);
           dt3.setCalendar(null);
            e15.setVisible(false);
        }
    }//GEN-LAST:event_clhrActionPerformed

    private void hp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hp4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hp4ActionPerformed

    private void okk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okk2ActionPerformed
        // TODO add your handling code here:
        ckar2(1);
    }//GEN-LAST:event_okk2ActionPerformed

    private void chpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chpActionPerformed
        // TODO add your handling code here:
        if(chp.isSelected()){
            hp4.setEditable(true);
           
        }
        else {
            
            hp4.setEditable(false);
            hp4.setText("");
            e13.setVisible(false);
        }
        
    }//GEN-LAST:event_chpActionPerformed

    private void kt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kt4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kt4ActionPerformed

    private void calmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calmtActionPerformed
        // TODO add your handling code here:
        if(calmt.isSelected()){
            almt4.setEditable(true);
            kt4.setEditable(true);
        }
        else {
            almt4.setEditable(false);
            kt4.setEditable(false);
            almt4.setText("");
            kt4.setText("");
            e12.setVisible(false);
        }
    }//GEN-LAST:event_calmtActionPerformed

    private void nm4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nm4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nm4ActionPerformed

    private void cpswActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpswActionPerformed
        // TODO add your handling code here:
        if(cpsw.isSelected()){
            psw4.setEditable(true);
        }
        else {
            psw4.setEditable(false);
            psw4.setText("");
            e10.setVisible(false);
        }
    }//GEN-LAST:event_cpswActionPerformed

    private void kt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kt3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kt3ActionPerformed

    private void hp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hp3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hp3ActionPerformed

    private void almt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almt3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_almt3ActionPerformed

    private void gj3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gj3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gj3ActionPerformed

    private void cnmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnmActionPerformed
        // TODO add your handling code here:
        if(cnm.isSelected()){
            nm4.setEditable(true);
        }
        else {
            nm4.setEditable(false);
            nm4.setText("");
            e11.setVisible(false);
        }
    }//GEN-LAST:event_cnmActionPerformed

    private void cgjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cgjActionPerformed
        // TODO add your handling code here:
        if(cgj.isSelected()){
            gj4.setEditable(true);
           
        }
        else {
            
            gj4.setEditable(false);
            gj4.setText("");
            e14.setVisible(false);
        }
    }//GEN-LAST:event_cgjActionPerformed

    private void gj4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gj4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gj4ActionPerformed

    private void ckrjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckrjActionPerformed
        // TODO add your handling code here:
        if(ckrj.isSelected()){
            dt4.setEnabled(true);
           
        }
        else {
            
            dt4.setEnabled(false);
           dt4.setCalendar(null);
            e16.setVisible(false);
        }
    }//GEN-LAST:event_ckrjActionPerformed

    private void cjbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cjbtnActionPerformed
        // TODO add your handling code here:
        if(cjbtn.isSelected()){
            box2.setEnabled(true);
           
        }
        else {
            
            box2.setEnabled(false);
           box2.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cjbtnActionPerformed

    private void box1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box1ActionPerformed
        // TODO add your handling code here:
        
                
    }//GEN-LAST:event_box1ActionPerformed

    private void bns4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bns4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bns4ActionPerformed

    private void cbnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnsActionPerformed
        // TODO add your handling code here:
        if(cbns.isSelected()){
            bns4.setEditable(true);
           
        }
        else {
            
            bns4.setEditable(false);
            bns4.setText("");
            e19.setVisible(false);
        }
    }//GEN-LAST:event_cbnsActionPerformed

    private void jLabel4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseMoved
        // TODO add your handling code here:
        jLabel4.setForeground(Color.red);
    }//GEN-LAST:event_jLabel4MouseMoved

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        // TODO add your handling code here:
        jLabel4.setForeground(Color.black);
    }//GEN-LAST:event_jLabel4MouseExited

    private void lmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lmActionPerformed

    private void brActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_brActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        jDialog2.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        if(password()){
            try {
                stm.executeUpdate("update karyawanm set Password=md5('"+String.valueOf(kbr.getPassword())+"') where ID='"+ID+"'");
                jDialog2.dispose();
                JOptionPane.showMessageDialog(this,"Password telah diubah");
                
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            JOptionPane.showMessageDialog(this,"Input tidak valid");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        
        
        jDialog2.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jDialog2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDialog2FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jDialog2FocusGained

    private void jDialog2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog2KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jDialog2KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       
        //</editor-fold>

        /* Create and display the form */
        
      
       
        
    }
    private boolean cekpelanggan(int i){
        boolean cek=true;
        try {
            // TODO add your handling code here:
            stm=con.createStatement();
            rS=stm.executeQuery("select ID from pelangganm where ID='"+teks.getText()+"' AND ID!='default'");
            if(rS.next()){
                if(i==1){
                    oke.setEnabled(false);
                    ganti.setEnabled(true);
                    teks.setEditable(false);
                  
                }
                erry.setVisible(false);
            }
            else {
                erry.setVisible(true);
                cek=false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cek;
    }
    public ImageIcon scalegmbr(int width,int height){
       Image image=new ImageIcon(getClass().getResource("/gambar/the-bookstore-logo-design.png")).getImage();
       return new ImageIcon(image.getScaledInstance(width,height, SCALE_SMOOTH));
    }
    public ImageIcon scalegmbr2(int width,int height){
       Image image=new ImageIcon(getClass().getResource("/gambar/warning-icon-png-2743.png")).getImage();
       return new ImageIcon(image.getScaledInstance(width,height, SCALE_SMOOTH));
    }
    private void open_db(){
    try{
       Class.forName("com.mysql.jdbc.Driver");
       con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","");
        
    }   
    catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(this,"Database Error");
        System.exit(0);
    }
    }
    private void bacatable(){
          try {
            // TODO add your handling code here:
            stm = con.createStatement();
            rS = stm.executeQuery("select * from bukum");
           bersih(mdl);
           
            while(rS.next()){
                try {
                    BufferedImage im = ImageIO.read(rS.getBinaryStream("Gambar"));
                    Image img=new ImageIcon(im).getImage();
                    ImageIcon resize=new ImageIcon(img.getScaledInstance(80,60,Image.SCALE_SMOOTH));
                    
                    mdl.addRow(new Object[]{rS.getInt("Kode"),rS.getString("Judul"),rS.getString("Pengarang"),rS.getInt("TahunTerbit"),rS.getInt("Harga"),rS.getInt("Jumlah_Stok"),resize});
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            setTable();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setTable(){
        tblbuku.setRowSorter(null);
        tblbuku.setModel(mdl);
        
     
    }
    
    private void gantiPanel(String x){
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(),x);
    }

    private void kosong1(){

        judulbuku.setText("");
        pengarangbuku.setText("");
        tahuncombo.setSelectedIndex(0);
        hargabuku.setText("");
        stokbuku.setText("");
        error1.setVisible(false);
        error2.setVisible(false);
        error3.setVisible(false);
        error4.setVisible(false);
        error5.setVisible(false);         
        gambarlabel.setIcon(null);
        
    }
     private void kosong2(boolean x){

        kodedit.setText("");
        hargaedit.setText("");
        stokedit.setText("");
        stok1edit.setText("");
        kodedit.setEditable(!x);
        hargaedit.setEditable(x);
        stokedit.setEditable(x);
        stok1edit.setEditable(x);
        gambaredit.setIcon(null);
        checkharga.setSelected(x);
        checktmbh.setSelected(x);
        checkkurang.setSelected(x);
        checkgambar.setSelected(x);
        checkharga.setEnabled(x);
        checktmbh.setEnabled(x);
        checkkurang.setEnabled(x);
        checkgambar.setEnabled(x);
        okk.setEnabled(!x);
        browse1.setEnabled(x);
        err1.setVisible(x);
        err2.setVisible(x);
        err3.setVisible(x);
        err4.setVisible(x);
        err5.setVisible(x);         
       
        
    }
    
    private boolean testambah(){
        String regex="\\d+";
        
        boolean hasil = true;
        if(judulbuku.getText().matches(regex2)||judulbuku.getText().startsWith(" ")){
            error1.setVisible(true);
            
            hasil=false;
        }
        else {
           error1.setVisible(false);
          
        }
        if(pengarangbuku.getText().matches(regex2)||pengarangbuku.getText().startsWith(" ")){
             error2.setVisible(true);
             
             hasil=false;
         }
        else {
            error2.setVisible(false);
            
        }
        if(!hargabuku.getText().matches(regex)){
            error3.setVisible(true);
            
            hasil=false;
        }
        else {
            error3.setVisible(false);
           
        }
        if(!stokbuku.getText().matches(regex)){
            error4.setVisible(true);
            
            hasil=false;
        }
        else {
           error4.setVisible(false);
            
        }
        if(gambarlabel.getIcon()==null){
            error5.setVisible(true);
            
            hasil=false;
        }
        else {
           error5.setVisible(false);
          
           
        }
        
        return hasil;
    }
    
    private void kode(){
        try {
            stm=con.createStatement();
            rS=stm.executeQuery("select kode from hapus order by kode ASC");
            if(rS.next()){
               int i=rS.getInt("kode");
               kodebuku.setText(Integer.toString(i)); 
               
            }
            else {
               rS=stm.executeQuery("select Kode from bukum order by Kode ASC");
               if(rS.next()){
                    rS.afterLast();
                    rS.previous();
                    int i=rS.getInt("Kode")+1;
                    kodebuku.setText(Integer.toString(i)); 
               }
               else {
                   kodebuku.setText("1"); 
               }
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   private void akses(String jabatan){
       if("Kasir".equals(jabatan)){
           tmbhbuku.setVisible(false);
          jMenuItem8.setVisible(false);
           karyawan.setVisible(false);
           notaakhir.setVisible(false);
           detailnota.setVisible(false);
           
       }
       else if("Supervisor".equals(jabatan)){
            tabform.setEnabledAt(0,false);
            tabform.setEnabledAt(2,false);
            tabform.setSelectedIndex(1);
            karyawan.setVisible(false);
            
       }
       
   }
   private boolean visible(int i){
       boolean y=true;
       String regex="\\d+";
       if(kodedit.getText().matches(regex)){
         boolean x=false;
            try {
                stm=con.createStatement();
                rS=stm.executeQuery("select Kode from bukum where Kode="+kodedit.getText());
                if(rS.next()){
                      if(i==1){ 
                        okk.setEnabled(x);
                        browse1.setEnabled(x);
                        checkharga.setEnabled(!x);
                        checktmbh.setEnabled(!x);
                        checkkurang.setEnabled(!x);
                        checkgambar.setEnabled(!x);
                        checkharga.setSelected(x);
                        checktmbh.setSelected(x);
                        checkkurang.setSelected(x);
                        checkgambar.setSelected(x);
                        gambaredit.setIcon(null);
                        err1.setVisible(x);
                        err2.setVisible(x);
                        err3.setVisible(x);
                        err4.setVisible(x);
                        err5.setVisible(x);  
                        hargaedit.setText("");
                        stokedit.setText("");
                        stok1edit.setText("");
                        kodedit.setEditable(x);
                        hargaedit.setEditable(x);
                        stokedit.setEditable(x);
                        stok1edit.setEditable(x);
       
                }
                      err1.setVisible(false);
                }
                else {
                   err1.setVisible(true); 
                   y=false;
                }

            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       else {
           y=false;
           err1.setVisible(true);
           
       }
       return y;
   }
   private boolean beneredit(boolean a,boolean b,boolean c,boolean d){
    boolean akhir=true;
    String regex="\\d+";
        if(a){
            if(hargaedit.getText().matches(regex)){
                err2.setVisible(false);
            }
            else {
                err2.setVisible(true);
                akhir=false;
            }
        }
        if(b){
            if(stokedit.getText().matches(regex)){
                err3.setVisible(false);
            }
            else {
                err3.setVisible(true);
                akhir=false;
            }
        }
        if(c){
            if(stok1edit.getText().matches(regex)){
                try {
                    stm=con.createStatement();
                    rS=stm.executeQuery("select Jumlah_Stok from bukum where Kode="+kodedit.getText());
                    rS.next();
                    if((rS.getInt("Jumlah_Stok")-Integer.parseInt(stok1edit.getText()))>=0){
                        err4.setVisible(false);
                    }

                    else {

                       err4.setVisible(true);
                       akhir=false; 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else {
                err4.setVisible(true);
                akhir=false;
            }
        }
        if(d){
            if(gambaredit.getIcon()!=null){

                err5.setVisible(false);

            }
            else {
               err5.setVisible(true);
               akhir=false; 
            }

        }
    return akhir;
}
   private void nonota(){
        try {
            stm=con.createStatement();
            rS=stm.executeQuery("select Kode from nota where IDKaryawan='"+ID+"'");
            ResultSet rS1;
            Statement stm2=con.createStatement();
            rS1=stm2.executeQuery("select No from karyawanm where ID='"+ID+"'");
            rS1.next();
         
            if(!rS.next()){
                nomnota.setText("( No. "+Integer.toString(rS1.getInt("No"))+"0 )");
               lengths=Integer.toString(rS1.getInt("No"))+"0";
                
            }
            else {
                rS.afterLast();
                rS.previous();
                
                int length=Integer.toString(rS1.getInt("No")).length();
                int ambil;
                if(rS.getString("Kode").length()-length==1){
                     ambil=Integer.parseInt(rS.getString("Kode").substring(rS.getString("Kode").length()-1))+1;
                }
                else {
                     ambil=Integer.parseInt(rS.getString("Kode").substring(length,rS.getString("Kode").length()))+1;
                }
                
                nomnota.setText("( No. "+Integer.toString(rS1.getInt("No"))+Integer.toString(ambil)+" )");
                lengths=Integer.toString(rS1.getInt("No"))+Integer.toString(ambil);
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
   private void awalNota(){
       teks.setEditable(false);
       oke.setEnabled(false);
       ganti.setEnabled(false);
       ubah.setEnabled(false);
       bayar.setEnabled(false);
       byr.setText("");
       byr.setEditable(false);
       Edit.setEnabled(false);
       btls.setEnabled(false);
       hps.setEnabled(false);
       errx.setVisible(false);
       erry.setVisible(false);
       errr1.setVisible(false);
       errr2.setVisible(false);
       tmbhs.setEnabled(true);
       tmbhs.setText("Tambah");
       teks.setText("");
       kods.setEditable(true);
       kods.setText("");
       jums.setText("");
       jums.setEditable(true);
       totals.setText("Rp 0");
       def.setSelected(true);
       bisa=0;
       selse.setEnabled(true);
       refreshmdl1();
        bersih(mdl2);
       tblnota.setModel(mdl2);
   }
   private boolean cekkode(){
       String regex="\\d+";
       boolean cek=true;
       if(!kods.getText().matches(regex)){
           errr1.setVisible(true);
           cek=false;
       }
       else {
           try {
               stm=con.createStatement();
               rS=stm.executeQuery("select Kode from bukum where Kode="+kods.getText());
               if(rS.next()){
                   errr1.setVisible(false);
               }
               else {
                   errr1.setVisible(true);
                   cek=false;
               }
           } catch (SQLException ex) {
               Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
           }  
       }
       if(jums.getText().matches(regex)&&!jums.getText().matches("0+")){
           errr2.setVisible(false);
       }
       else {
           errr2.setVisible(true);
           cek=false;
       }
       return cek;
   }
   private void refreshmdl1(){
       
       bersih(mdl1);
       try {
            stm=con.createStatement();
            rS=stm.executeQuery("select Kode from bukum");
            while(rS.next()){
                mdl1.addRow(new Object[]{rS.getInt("Kode")});
            }
            tblkode.setModel(mdl1);
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   private void fin(){
       Edit.setEnabled(true);
        bisa=0;
        hps.setEnabled(true);
        btls.setEnabled(false);
        kods.setEditable(true);
        jums.setEditable(true);
        kods.setText("");
        jums.setText("");
        tmbhs.setText("Tambah");
       
   }
   void hitung(){
       int baris=mdl2.getRowCount()-1;
       int total=0;
       while(baris!=-1){
            total=total+(int)mdl2.getValueAt(baris,4);
            baris--;
       }
       totals.setText("Rp "+Integer.toString(total));
       
   }
   void sorttblnota(){
       TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblnota.getModel());
            tblnota.setRowSorter(sorter);
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);
            sorter.sort(); 
            
   }
   void peskluar(){
          int baris=mdl2.getRowCount()-1;
        while(baris!=-1){
            try {
                stm=con.createStatement();
                int kode=(int)mdl2.getValueAt(baris,0);
                rS=stm.executeQuery("select Jumlah_Stok from bukum where Kode="+Integer.toString(kode));
                rS.next();
                int stok=rS.getInt("Jumlah_Stok")+(int)mdl2.getValueAt(baris,3);
                stm.executeUpdate("update bukum set Jumlah_Stok="+Integer.toString(stok)+" where Kode="+Integer.toString(kode));
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            baris--;
        }
   }
   int inputnota(String pels,int duit){
        int tot1=0;
        String kodbuk=null,jum,tot;
         int baris=0;
        try {
           
            stm=con.createStatement();
            stm.executeUpdate("insert into nota values('"+lengths+"','"+ID+"','"+pels+"',CURDATE(),CURTIME())");
            
            
            while(baris<=mdl2.getRowCount()-1){
                kodbuk=Integer.toString((int)mdl2.getValueAt(baris,0));
                jum=Integer.toString((int)mdl2.getValueAt(baris,3));
                tot=Integer.toString((int)mdl2.getValueAt(baris,4));
                tot1=tot1+Integer.parseInt(tot);
                stm.executeUpdate("insert into beli values('"+lengths+"',"+kodbuk+","+jum+","+tot+")");
                baris++;
            }
            
            
        } catch (SQLException ex) {
            
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return duit-tot1;
   }
   private void bacatabelnota(){
       try {
            // TODO add your handling code here:
            stm = con.createStatement();
            rS = stm.executeQuery("select * from beli order by Kode ASC");
            bersih(mdlnt);
            
            
            while(rS.next()){
                
                   mdlnt.addRow(new Object[]{rS.getString("Kode"),rS.getInt("KodeBuku"),rS.getInt("Jumlah"),rS.getInt("Total")}); 
            }
            tbldnota.setRowSorter(null);
            tbldnota.setModel(mdlnt);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   private void tblnot(){
        try {
            bersih(mdlak);
            stm=con.createStatement();
            rS=stm.executeQuery("select * from nota_akhir");
            while(rS.next()){
                Date date,date1;
                date=rS.getDate("TglBeli");
                date1=rS.getTime("WaktuBeli");
                SimpleDateFormat ft =new SimpleDateFormat ("dd-MM-yyyy"),ft1=new SimpleDateFormat ("HH:mm:ss");
                mdlak.addRow(new Object[]{rS.getString("Kode"),rS.getString("IDKaryawan"),rS.getString("No"),ft.format(date),ft1.format(date1),rS.getInt("SubTotal")}); 
            }
            tblnot.setRowSorter(null);
            tblnot.setModel(mdlak);
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
   }
   private void bersih(DefaultTableModel clean){
       int r=clean.getRowCount()-1;
            while(r!=-1){
                clean.removeRow(r);
                r--;
            }
   }
   private void bacatblpel(){
        try {
            bersih(mdlpel);
            stm=con.createStatement();
            rS=stm.executeQuery("select ID,Nama from pelangganm where ID!='default'");
            while(rS.next()){
                mdlpel.addRow(new Object[]{rS.getString("ID"),rS.getString("Nama")});
            }
            tblpel.setRowSorter(null);
            tblpel.setModel(mdlpel);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   private String awalID(){
       String hasil=null;
        try {
            stm=con.createStatement();
            rS=stm.executeQuery("select No from karyawanm where ID='"+ID+"'");
            rS.next();
            String depan=Integer.toString(rS.getInt("No"));
            String regex="^"+depan+"[A-Z]+";
            String akhir=null;
            boolean hapus=false;
            rS=stm.executeQuery("select ID from hapuspel where ID LIKE '"+depan+"%' order by ID ASC");
            while(rS.next()){
                if(rS.getString("ID").matches(regex)){
                    hasil=rS.getString("ID");
                    hapus=true;
                    break;
                }
            }
            if(!hapus){
                rS=stm.executeQuery("select ID from pelangganm where ID LIKE '"+depan+"%' order by ID DESC");
                while(rS.next()){
                    if(rS.getString("ID").matches(regex)){
                        akhir=rS.getString("ID");
                        break;
                    }
                }
                if(akhir!=null){
                    int len=akhir.length()-1;
                    if((int)akhir.charAt(len)==90){
                        hasil=akhir+"A";
                    }
                    else {
                       int next=(int) akhir.charAt(len)+1;
                       hasil=akhir.substring(0,len)+(char)next;
                    }
                }
                else {
                    hasil=depan+"A";
                }
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
   }
   private void keterangan(int row){
        try {
            stm=con.createStatement();
            rS=stm.executeQuery("select * from pelangganm where ID='"+(String)tblpel.getValueAt(row,0)+"'");
            if(rS.next()){
            ada=true;
            isi1.setText(rS.getString("ID"));
            isi2.setText(rS.getString("Nama"));
            isi3.setText(rS.getString("Alamat"));
            isi4.setText(rS.getString("NoHp"));
            Date date=rS.getDate("TglLahir");
            SimpleDateFormat ft=new SimpleDateFormat("dd-MM-yyyy");
            isi5.setText(ft.format(date));  } 
            else {
               ada=false; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
   private void cleanket(){
       isi1.setText("-");
       isi2.setText("-");
       isi3.setText("-");
       isi4.setText("-");
       isi5.setText("-");
   }
   private void  getRowPoint(){
       int len=0;
            while(len<=tblpel.getRowCount()-1){
                if(current.equals((String)tblpel.getValueAt(len,0))){
                    rowpoint=len;
                    break;
                }
                len++;
            }
            if(len>tblpel.getRowCount()-1){
                rowpoint=-1;
            }
            else {
                tblpel.setRowSelectionInterval(rowpoint, rowpoint);
            }
            
   }
   private void cleanket1(){
       kid.setText("-");
       knm.setText("-");
       kalmt.setText("-");
       khp.setText("-");
       kgj.setText("-");
       kbns.setText("-");
       klhr.setText("-");
       kkrj.setText("-");
       kbrh.setText("-");
       
       
   }
   private void  getRowPoint1(){
       int len=0;
            while(len<=tblkar.getRowCount()-1){
                if(current1.equals((String)tblkar.getValueAt(len,0))){
                    rowpoint1=len;
                    break;
                }
                len++;
            }
            if(len>tblkar.getRowCount()-1){
                rowpoint1=-1;
            }
            else {
                tblkar.setRowSelectionInterval(rowpoint1, rowpoint1);
            }
            
   }
   private void inputpel(){
       c1.setVisible(false);
       c2.setVisible(false);
       c3.setVisible(false);
       c4.setVisible(false);
       nms.setText("");
       almts.setText("");
       kots.setText("");
       hpss.setText("");
       jDateChooser3.setCalendar(null);;
   }
   private void editpel(){
       c5.setVisible(false);
       c6.setVisible(false);
       c7.setVisible(false);
       c8.setVisible(false);
       c9.setVisible(false);
       nms1.setText("");
       almts1.setText("");
       kots1.setText("");
       hpss1.setText("");
       nms1.setEditable(false);
       almts1.setEditable(false);
       kots1.setEditable(false);
       hpss1.setEditable(false);
       kodedit1.setText("");
       kodedit1.setEditable(true);
       jDateChooser4.setCalendar(null);
       jDateChooser4.setEnabled(false);
       ceknm.setSelected(false);
       cekalmt.setSelected(false);
       cekhp.setSelected(false);
       cektgl.setSelected(false);
       editpel2(false);
       
   }
   private void editpel2(boolean t){
       okk1.setEnabled(!t);
       ceknm.setEnabled(t);
       cekalmt.setEnabled(t);
       cekhp.setEnabled(t);
       cektgl.setEnabled(t);
   }
   private boolean cekpel1(){
       boolean o=true;
       if(nms.getText().matches(regex2)||nms.getText().startsWith(" ")){
           o=false;
           c1.setVisible(true);
       }
       else {
           c1.setVisible(false);
       }
       if(almts.getText().matches(regex2)||kots.getText().matches(regex2)||almts.getText().startsWith(" ")||kots.getText().startsWith(" ")){
            o=false;
            c2.setVisible(true);
       }
       else {
           c2.setVisible(false);
       }
       String regex="\\d+";
       if(!hpss.getText().matches(regex)){
            o=false;
            c3.setVisible(true);
       }
       else {
           c3.setVisible(false);
       }
       DateTime dm=new DateTime(jDateChooser3.getDate());
       if(jDateChooser3.getDate()==null){
           c4.setToolTipText("Tgl Lahir tidak valid");
           c4.setVisible(true);
           o=false;
       }
       else if(dm.plusYears(17).isAfterNow()){
           c4.setToolTipText("Usia min 17 tahun");
           c4.setVisible(true);
           o=false;
       }
       else {
           c4.setVisible(false);
       }
       return o;
    }
   private boolean editpel1(int kondisi){
       boolean q=false;
        try {
            // TODO add your handling code here:
            stm=con.createStatement();
            rS=stm.executeQuery("select ID from pelangganm where ID='"+kodedit1.getText()+"'");
            if(rS.next()){
                
                    c5.setVisible(false);
                    if(kondisi==1){
                         kodedit1.setEditable(false);
                         editpel2(true);
                    }

                    q=true;
                
               
                
            }
            else {
                c5.setToolTipText("ID Pelanggan tidak valid");
                c5.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
   }
   private boolean editpel3(boolean a,boolean b,boolean c,boolean d){
       boolean bener=true;
       String regex="\\d+";
       if(a){
          if(nms1.getText().matches(regex2)||nms1.getText().startsWith(" ")){
              c6.setVisible(true);
              bener=false;
          } 
          else {
              c6.setVisible(false);
          }
       }
       if(b){
           if(almts1.getText().matches(regex2)||almts1.getText().startsWith(" ")||kots1.getText().matches(regex2)||kots1.getText().startsWith(" ")){
              c7.setVisible(true);
              bener=false;
          } 
          else {
              c7.setVisible(false);
          }
       }
       if(c){
          if(!hpss1.getText().matches(regex)){
              c8.setVisible(true);
              bener=false;
          } 
          else {
              c8.setVisible(false);
          }
       }
       DateTime dm=new DateTime(jDateChooser4.getDate());
       if(d){
           if(jDateChooser4.getDate()==null){
              c9.setToolTipText("Tgl Lahir tidak valid");
              c9.setVisible(true);
              bener=false;
          } 
          else if(dm.plusYears(17).isAfterNow()){
              c9.setToolTipText("Usia min 17 tahun");
              c9.setVisible(true);
              bener=false;
           }
          else {
              c9.setVisible(false);
          }
       }
       return bener;
       
   }
   private void bacakar(int i){
        try {
            bersih(mdlkar);
            stm=con.createStatement();
            if(i==0){
              rS=stm.executeQuery("select ID,Nama from karyawanm where ID!='0'");  
            }
            else {
              rS=stm.executeQuery("select k.ID,k.Nama from karyawanm as k,supervisor as s where k.ID!='0' AND k.ID=s.ID");  
            }
            
            while(rS.next()){
                mdlkar.addRow(new Object[]{rS.getString("ID"),rS.getString("Nama")});
            }
            tblkar.setModel(mdlkar);
            tblkar.setRowSorter(null);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   private void karket(int row){
        try {
            stm=con.createStatement();
            rS=stm.executeQuery("select * from karyawanm where ID!='0' AND ID='"+(String)tblkar.getValueAt(row,0)+"'");
            if(rS.next()){
                kid.setText(rS.getString("ID"));
                knm.setText(rS.getString("Nama"));
                kalmt.setText(rS.getString("Alamat"));
                khp.setText(rS.getString("NoHP"));
                kgj.setText(Integer.toString(rS.getInt("Gaji")));
                SimpleDateFormat ft=new SimpleDateFormat("dd-MM-yyyy");    
                klhr.setText(ft.format(rS.getDate("TglLahir")));
                kkrj.setText(ft.format(rS.getDate("TglKerja")));
                if(rS.getDate("TglBerhenti")==null){
                    kbrh.setText("-");
                }
                else {
                    kbrh.setText(ft.format(rS.getDate("TglBerhenti")));
                }
                rS=stm.executeQuery("select * from supervisor where ID!='0' AND ID='"+(String)tblkar.getValueAt(row,0)+"'");
                if(rS.next()){
                    knm.setText(knm.getText()+" (Supervisor)");
                    if(rS.getInt("Bonus")==0){
                        kbns.setText("-");
                    }
                    else{
                    kbns.setText(Integer.toString(rS.getInt("Bonus")));
                    }
                }
                else {
                    kbns.setText("-");
                }
            }
            else {
                cleanket1();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   private void kar1(){
       e1.setVisible(false);
       e2.setVisible(false);
       e3.setVisible(false);
       e4.setVisible(false);
       e5.setVisible(false);
       e6.setVisible(false);
       e7.setVisible(false);
       e8.setVisible(false);
       id3.setText("");
       psw3.setText("");
       nm3.setText("");
       almt3.setText("");
       kt3.setText("");
       hp3.setText("");
       gj3.setText("");
       dt1.setCalendar(null);
       dt2.setCalendar(null);
   }
   private boolean cekkar1(){
       boolean cek=true,yes=false;
       if(id3.getText().matches(".*\\W.*")||id3.getText().isEmpty()){
           e1.setVisible(true);
           cek=false;
       }
       else {
           try {
               stm=con.createStatement();
               rS=stm.executeQuery("select ID from karyawanm");
               while(rS.next()){
                   if(rS.getString("ID").equalsIgnoreCase(id3.getText())){
                       yes=true;
                       cek=false;
                       e1.setVisible(true);
                       break;
                   }
               }
               if(!yes){
                   e1.setVisible(false);
               }
           } catch (SQLException ex) {
               Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
       if(String.valueOf(psw3.getPassword()).isEmpty()){
           e2.setVisible(true);
           cek=false;
       }
       else {
           e2.setVisible(false);
       }
       if(nm3.getText().startsWith(" ")||nm3.getText().matches(regex2)){
           e3.setVisible(true);
           cek=false;
       }
       else {
           e3.setVisible(false);
       }
       if(almt3.getText().startsWith(" ")||almt3.getText().matches(regex2)||kt3.getText().startsWith(" ")||kt3.getText().matches(regex2)){
           e4.setVisible(true);
           cek=false;
       }
       else {
           e4.setVisible(false);
       }
       if(!hp3.getText().matches("\\d+")){
           e5.setVisible(true);
           cek=false;
       }
       else {
           e5.setVisible(false);
       }
       if(!gj3.getText().matches("\\d+")){
           e6.setVisible(true);
           cek=false;
       }
       else {
           e6.setVisible(false);
       }
       DateTime dm=new DateTime(dt1.getDate());
       if(dt1.getDate()==null){
           e7.setToolTipText("Tgl Lahir tidak valid");
           e7.setVisible(true);
           cek=false;
       }
       else if(dm.plusYears(20).isAfterNow()||dm.plusYears(50).isBeforeNow()){
           e7.setToolTipText("Usia min 20 tahun & maks 50 tahun");
           e7.setVisible(true);
           cek=false;
       }
       else {
          e7.setVisible(false); 
       }
       
       SimpleDateFormat ft=new SimpleDateFormat("dd-MM-yyyy");
       
       try {
            Date dt=ft.parse(ft.format(new Date()));
            if(dt2.getDate()==null||dt.compareTo(dt2.getDate())>0){
           e8.setVisible(true);
           cek=false;
       }
       else {
          e8.setVisible(false); 
       }
        } catch (ParseException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return cek;
   }
   private String ambilno(){
       String no=null;
     
        try {
            stm=con.createStatement();
            rS=stm.executeQuery("select No from hapusno");
            if(rS.next()){
                no=Integer.toString(rS.getInt("No"));
            }
            else {
               rS=stm.executeQuery("select No from karyawanm order by No DESC"); 
               if(rS.next()){
                   no=Integer.toString(rS.getInt("No")+1);
               } 
               else {
                   no="1";
               }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return no;
   }
   private void  kar3(){
       kodehapus2.setText("");
       box1.setSelectedIndex(0);
       e18.setVisible(false);
       e17.setVisible(false);
      
   }
   private boolean cekkar3(){
       boolean cek=true;
       if(box1.getSelectedIndex()==1){
           try {
               rS=stm.executeQuery("select ID,TglKerja,TglBerhenti from karyawanm where ID!='0' AND ID='"+kodehapus2.getText()+"'");
               if(!rS.next()){
                   e17.setToolTipText("ID Karyawan tidak valid");
                    e17.setVisible(true);
                    cek=false;
                }
               else if(rS.getDate("TglBerhenti")!=null){
                    e17.setToolTipText("ID Karyawan telah dipecat");
                    e17.setVisible(true);
                    cek=false;
                   
               }
               else {
                    e17.setVisible(false);
                    if(new Date().compareTo(rS.getDate("TglKerja"))<0){
                         e18.setVisible(true);
                         cek=false;
                    }
                    else {
                        e18.setVisible(false);
                    }
               }
           } catch (SQLException ex) {
               Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           
                
            
       }
       else {
           try {
            stm=con.createStatement();
            rS=stm.executeQuery("select IDKaryawan from nota where IDKaryawan!='0' AND IDKaryawan='"+kodehapus2.getText()+"'");
            if(rS.next()){
                e17.setToolTipText("ID Karyawan sudah aktif");
                e17.setVisible(true);
                cek=false;
            }
            else {
                rS=stm.executeQuery("select ID from karyawanm where ID!='0' AND ID='"+kodehapus2.getText()+"'");
                if(!rS.next()){
                    e17.setToolTipText("ID Karyawan tidak valid");
                    e17.setVisible(true);
                    cek=false;
                }
                else {
                    e17.setVisible(false);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
                
       }
       
       return cek;
   }
   private void kar2(boolean t){
       kodedit2.setText("");
       kodedit2.setEditable(!t);
       psw4.setText("");
       nm4.setText("");
       almt4.setText("");
       kt4.setText("");
       hp4.setText("");
       gj4.setText("");
       bns4.setText("");
       psw4.setEditable(t);
       nm4.setEditable(t);
       almt4.setEditable(t);
       kt4.setEditable(t);
       hp4.setEditable(t);
       gj4.setEditable(t);
       bns4.setEditable(t);
       dt3.setCalendar(null);
       dt4.setCalendar(null);
       dt3.setEnabled(t);
       dt4.setEnabled(t);
       box2.setEnabled(t);
       box2.setSelectedIndex(0);
       okk2.setEnabled(!t);
       c(t);
   
      
       e9.setVisible(t);
       e10.setVisible(t);
       e11.setVisible(t);
       e12.setVisible(t);
       e13.setVisible(t);
       e14.setVisible(t);
       e15.setVisible(t);
       e16.setVisible(t);
       e19.setVisible(t);
       cpsw.setSelected(t);
       cnm.setSelected(t);
       calmt.setSelected(t);
       chp.setSelected(t);
       cgj.setSelected(t);
       clhr.setSelected(t);
       ckrj.setSelected(t);
       cjbtn.setSelected(t);
       cbns.setSelected(t);
      
   }
   private void c(boolean t){
       cpsw.setEnabled(t);
       cnm.setEnabled(t);
       calmt.setEnabled(t);
       chp.setEnabled(t);
       cgj.setEnabled(t);
       clhr.setEnabled(t);
       ckrj.setEnabled(t);
       cjbtn.setEnabled(t);
       cbns.setEnabled(t);
       
       
       
   }
   private boolean ckar2(int u){
       boolean cek=true;
        try {
            stm=con.createStatement();
            rS=stm.executeQuery("select ID from supervisor where ID='"+kodedit2.getText()+"' AND ID!='0'");
            if(rS.next()){
                if(u==1){
                c(true);
                okk2.setEnabled(false);
                kodedit2.setEditable(false);
                }
                
                e9.setVisible(false);
            }
            else { 
               rS=stm.executeQuery("select ID from karyawanm where ID='"+kodedit2.getText()+"' AND ID!='0'");
               if(rS.next()){
                    e9.setVisible(false);
                    if(u==1){
                        c(true);
                        cbns.setEnabled(false);
                        okk2.setEnabled(false);
                        kodedit2.setEditable(false);
                    }
               }
               else {
                   cek=false;
                   e9.setVisible(true);
               }
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
       return cek;
   }
   private boolean cekkar2(boolean a,boolean b,boolean c,boolean d,boolean e,boolean f,boolean g,boolean i){
       boolean cek=true;
       if(a){
            if(String.valueOf(psw4.getPassword()).isEmpty()){
                e10.setVisible(true);
                cek=false;
            } 
            else{
                e10.setVisible(false);
            }
       }
       if(b){
            if(nm4.getText().matches(regex2)||nm4.getText().startsWith(" ")){
                e11.setVisible(true);
                cek=false;
            } 
            else{
                e11.setVisible(false);
            }
           
       }
       if(c){
            if(almt4.getText().matches(regex2)||almt4.getText().startsWith(" ")||kt4.getText().matches(regex2)||kt4.getText().startsWith(" ")){
                e12.setVisible(true);
                cek=false;
            } 
            else{
                e12.setVisible(false);
            } 
       }
       if(d){
            if(!hp4.getText().matches("\\d+")){
               e13.setVisible(true);
               cek=false;
           } 
           else{
               e13.setVisible(false);
           }
       }
       if(e){
            if(!gj4.getText().matches("\\d+")){
                e14.setVisible(true);
                cek=false;
            } 
            else{
                e14.setVisible(false);
            } 
       }
       if(f){
            DateTime dm=new DateTime(dt3.getDate());
            if(dt3.getDate()==null){
                e15.setToolTipText("Tgl Lahir tidak valid");
                e15.setVisible(true);
                cek=false;
            }
            else if(dm.plusYears(20).isAfterNow()||dm.plusYears(50).isBeforeNow()){
                e15.setToolTipText("Usia min 20 tahun & maks 50 tahun");
                e15.setVisible(true);
                cek=false;
            }
            else {
               e15.setVisible(false); 
            }
       }
       if(g){
           SimpleDateFormat ft=new SimpleDateFormat("dd-MM-yyyy");
       
       try {
            Date dt=ft.parse(ft.format(new Date()));
            if(dt4.getDate()==null||dt.compareTo(dt4.getDate())>0){
           e16.setVisible(true);
           cek=false;
       }
       else {
          e16.setVisible(false); 
       }
        } catch (ParseException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       }
       if(i){
           if(!bns4.getText().matches("\\d+")){
                e19.setVisible(true);
                cek=false;
            } 
            else{
                e19.setVisible(false);
            } 
       }
       
       
       return cek;
   }
  
   private boolean password(){
       boolean pass=true;
        try {
            rS=stm.executeQuery("select ID from karyawanm where ID='"+ID+"' AND Password=md5('"+String.valueOf(lm.getPassword())+"')");
            if(rS.next()){
                
                if(String.valueOf(br.getPassword()).isEmpty()){
                   
                    pass=false;
                }
                else {
                   
                   if(String.valueOf(br.getPassword()).equals(String.valueOf(kbr.getPassword()))){
                      
                        
                   }
                   else{
                      
                         pass=false;
                   }
                           
                }
            }
            else {
                
                pass=false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
      return pass; 
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Edit;
    private javax.swing.JLabel Gambar;
    private javax.swing.JRadioButton Jumlah;
    private javax.swing.JButton Refresh;
    private javax.swing.JRadioButton Total;
    private javax.swing.JTextField almt3;
    private javax.swing.JTextField almt4;
    private javax.swing.JTextField almts;
    private javax.swing.JTextField almts1;
    private javax.swing.JButton ascending;
    private javax.swing.JButton ascending1;
    private javax.swing.JButton ascending2;
    private javax.swing.JButton ascending3;
    private javax.swing.JButton ascending4;
    private javax.swing.JButton ascending5;
    private javax.swing.JButton ascending7;
    private javax.swing.JButton ascending8;
    private javax.swing.JButton bayar;
    private javax.swing.ButtonGroup belibutton;
    private javax.swing.JPanel bg;
    private javax.swing.JPanel bg1;
    private javax.swing.JPanel bg2;
    private javax.swing.JPanel bg3;
    private javax.swing.JPanel bg4;
    private javax.swing.JPanel bg5;
    private javax.swing.JPanel bg6;
    private javax.swing.JPanel bg7;
    private javax.swing.JTextField bns4;
    private javax.swing.JComboBox<String> box1;
    private javax.swing.JComboBox<String> box2;
    private javax.swing.JPasswordField br;
    private javax.swing.JButton browse;
    private javax.swing.JButton browse1;
    private javax.swing.JButton btl;
    private javax.swing.JButton btl1;
    private javax.swing.JButton btl2;
    private javax.swing.JButton btls;
    private javax.swing.ButtonGroup butbl;
    private javax.swing.ButtonGroup butkar;
    private javax.swing.JTextField byr;
    private javax.swing.JLabel c1;
    private javax.swing.JLabel c10;
    private javax.swing.JLabel c2;
    private javax.swing.JLabel c3;
    private javax.swing.JLabel c4;
    private javax.swing.JLabel c5;
    private javax.swing.JLabel c6;
    private javax.swing.JLabel c7;
    private javax.swing.JLabel c8;
    private javax.swing.JLabel c9;
    private javax.swing.JCheckBox calmt;
    private javax.swing.JCheckBox cbns;
    private javax.swing.JCheckBox cekalmt;
    private javax.swing.JCheckBox cekhp;
    private javax.swing.JCheckBox ceknm;
    private javax.swing.JCheckBox cektgl;
    private javax.swing.JCheckBox cgj;
    private javax.swing.JCheckBox checkgambar;
    private javax.swing.JCheckBox checkharga;
    private javax.swing.JCheckBox checkkurang;
    private javax.swing.JCheckBox checktmbh;
    private javax.swing.JCheckBox chp;
    private javax.swing.JCheckBox cjbtn;
    private javax.swing.JCheckBox ckrj;
    private javax.swing.JButton clears;
    private javax.swing.JCheckBox clhr;
    private javax.swing.JCheckBox cnm;
    private javax.swing.JCheckBox cpsw;
    private javax.swing.JLabel daftar;
    private javax.swing.JMenuItem daftarbuku;
    private javax.swing.JRadioButton def;
    private javax.swing.JButton descending;
    private javax.swing.JButton descending1;
    private javax.swing.JButton descending2;
    private javax.swing.JButton descending3;
    private javax.swing.JButton descending4;
    private javax.swing.JMenuItem detailnota;
    private com.toedter.calendar.JDateChooser dt1;
    private com.toedter.calendar.JDateChooser dt2;
    private com.toedter.calendar.JDateChooser dt3;
    private com.toedter.calendar.JDateChooser dt4;
    private javax.swing.JLabel e1;
    private javax.swing.JLabel e10;
    private javax.swing.JLabel e11;
    private javax.swing.JLabel e12;
    private javax.swing.JLabel e13;
    private javax.swing.JLabel e14;
    private javax.swing.JLabel e15;
    private javax.swing.JLabel e16;
    private javax.swing.JLabel e17;
    private javax.swing.JLabel e18;
    private javax.swing.JLabel e19;
    private javax.swing.JLabel e2;
    private javax.swing.JLabel e3;
    private javax.swing.JLabel e4;
    private javax.swing.JLabel e5;
    private javax.swing.JLabel e6;
    private javax.swing.JLabel e7;
    private javax.swing.JLabel e8;
    private javax.swing.JLabel e9;
    private javax.swing.JLabel err1;
    private javax.swing.JLabel err10;
    private javax.swing.JLabel err11;
    private javax.swing.JLabel err2;
    private javax.swing.JLabel err3;
    private javax.swing.JLabel err4;
    private javax.swing.JLabel err5;
    private javax.swing.JLabel error1;
    private javax.swing.JLabel error2;
    private javax.swing.JLabel error3;
    private javax.swing.JLabel error4;
    private javax.swing.JLabel error5;
    private javax.swing.JLabel error6;
    private javax.swing.JLabel errr1;
    private javax.swing.JLabel errr2;
    private javax.swing.JLabel errx;
    private javax.swing.JLabel erry;
    private javax.swing.JLabel formulir;
    private javax.swing.JLabel gambaredit;
    private javax.swing.JLabel gambarlabel;
    private javax.swing.JButton ganti;
    private javax.swing.JTextField gj3;
    private javax.swing.JTextField gj4;
    private javax.swing.JTextField hargabuku;
    private javax.swing.JRadioButton hargabutton;
    private javax.swing.JTextField hargaedit;
    private javax.swing.JTextField hp3;
    private javax.swing.JTextField hp4;
    private javax.swing.JButton hps;
    private javax.swing.JTextField hpss;
    private javax.swing.JTextField hpss1;
    private javax.swing.JLabel id1;
    private javax.swing.JTextField id3;
    private javax.swing.JPanel idbut;
    private javax.swing.ButtonGroup idbutton;
    private javax.swing.JRadioButton idd;
    private javax.swing.JRadioButton idd1;
    private javax.swing.JRadioButton idkarbut;
    private javax.swing.JRadioButton idpel;
    private javax.swing.JLabel ids;
    private javax.swing.JLabel ids1;
    private javax.swing.JLabel ids2;
    private javax.swing.JLabel ids3;
    private javax.swing.JLabel isi1;
    private javax.swing.JLabel isi2;
    private javax.swing.JLabel isi3;
    private javax.swing.JLabel isi4;
    private javax.swing.JLabel isi5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField judulbuku;
    private javax.swing.JRadioButton judulbutton;
    private javax.swing.JRadioButton judulbutton1;
    private javax.swing.JLabel juduls;
    private javax.swing.JLabel juduls1;
    private javax.swing.JTextField jums;
    private javax.swing.JLabel kalmt;
    private javax.swing.JRadioButton kar4;
    private javax.swing.JMenu karyawan;
    private javax.swing.JLabel kbns;
    private javax.swing.JPasswordField kbr;
    private javax.swing.JLabel kbrh;
    private javax.swing.JLabel kgj;
    private javax.swing.JLabel khp;
    private javax.swing.JLabel kid;
    private javax.swing.JLabel kkrj;
    private javax.swing.JLabel klhr;
    private javax.swing.JLabel knm;
    private javax.swing.JLabel kodebuku;
    private javax.swing.JRadioButton kodebutton;
    private javax.swing.JRadioButton kodebutton1;
    private javax.swing.JRadioButton kodebutton2;
    private javax.swing.JTextField kodedit;
    private javax.swing.JTextField kodedit1;
    private javax.swing.JTextField kodedit2;
    private javax.swing.JTextField kodehapus;
    private javax.swing.JTextField kodehapus1;
    private javax.swing.JTextField kodehapus2;
    private javax.swing.JTextField kods;
    private javax.swing.JTextField kots;
    private javax.swing.JTextField kots1;
    private javax.swing.JTextField kt3;
    private javax.swing.JTextField kt4;
    private javax.swing.JPasswordField lm;
    private javax.swing.JLabel logos;
    private javax.swing.JLabel logos1;
    private javax.swing.JLabel logos2;
    private javax.swing.JLabel logos3;
    private javax.swing.JLabel logos4;
    private javax.swing.JLabel logos5;
    private javax.swing.JLabel logos6;
    private javax.swing.JLabel logos7;
    private javax.swing.JLabel logos8;
    private javax.swing.JRadioButton nm;
    private javax.swing.JRadioButton nm1;
    private javax.swing.JTextField nm3;
    private javax.swing.JTextField nm4;
    private javax.swing.JTextField nms;
    private javax.swing.JTextField nms1;
    private javax.swing.JLabel nomnota;
    private javax.swing.JMenu nota;
    private javax.swing.JMenuItem notaakhir;
    private javax.swing.ButtonGroup notabutton;
    private javax.swing.ButtonGroup notbutton;
    private javax.swing.JButton oke;
    private javax.swing.JButton okk;
    private javax.swing.JButton okk1;
    private javax.swing.JButton okk2;
    private dinus.bookstore.panel panel1;
    private dinus.bookstore.panel panel2;
    private dinus.bookstore.panel panel3;
    private dinus.bookstore.panel panel4;
    private dinus.bookstore.panel panel5;
    private dinus.bookstore.panel panel6;
    private dinus.bookstore.panel panel7;
    private dinus.bookstore.panel panel8;
    private dinus.bookstore.panel panel9;
    private dinus.bookstore.panel panelhmmm;
    private javax.swing.JRadioButton pela;
    private javax.swing.JMenu pelanggan;
    private javax.swing.ButtonGroup pelbutton;
    private javax.swing.JTextField pengarangbuku;
    private javax.swing.JRadioButton pengarangbutton;
    private javax.swing.JPasswordField psw3;
    private javax.swing.JPasswordField psw4;
    private javax.swing.JRadioButton saat;
    private javax.swing.JRadioButton saat1;
    private javax.swing.JRadioButton saat2;
    private javax.swing.JRadioButton sblm2;
    private javax.swing.JButton search;
    private javax.swing.JButton search1;
    private javax.swing.JButton search2;
    private javax.swing.JButton search3;
    private javax.swing.JButton search4;
    private javax.swing.JButton search5;
    private javax.swing.JComboBox<String> searchcombo;
    private javax.swing.JComboBox<String> searchcombo1;
    private javax.swing.JComboBox<String> searchcombo2;
    private javax.swing.JComboBox<String> searchcombo3;
    private javax.swing.JComboBox<String> searchcombo4;
    private javax.swing.JComboBox<String> searchcombo5;
    private javax.swing.JTextField searchtext;
    private javax.swing.JTextField searchtext1;
    private javax.swing.JTextField searchtext2;
    private javax.swing.JTextField searchtext3;
    private javax.swing.JTextField searchtext4;
    private javax.swing.JRadioButton sebelum;
    private javax.swing.JRadioButton sebelum1;
    private javax.swing.JButton selse;
    private javax.swing.JRadioButton setelah;
    private javax.swing.JRadioButton setelah1;
    private javax.swing.ButtonGroup sortbutton;
    private javax.swing.JRadioButton stlh2;
    private javax.swing.JTextField stok1edit;
    private javax.swing.JTextField stokbuku;
    private javax.swing.JTextField stokedit;
    private javax.swing.JRadioButton sup4;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JPanel tab5;
    private javax.swing.JPanel tab6;
    private javax.swing.JPanel tab7;
    private javax.swing.JPanel tab8;
    private javax.swing.JPanel tab9;
    private javax.swing.JTabbedPane tabform;
    private javax.swing.JTabbedPane tabform1;
    private javax.swing.JTabbedPane tabform2;
    private javax.swing.JRadioButton tahunbutton;
    private javax.swing.JComboBox<String> tahuncombo;
    private javax.swing.JTable tblbuku;
    private javax.swing.JTable tbldnota;
    private javax.swing.JTable tblkar;
    private javax.swing.JTable tblkode;
    private javax.swing.JTable tblnot;
    private javax.swing.JTable tblnota;
    private javax.swing.JTable tblpel;
    private javax.swing.JTextField teks;
    private javax.swing.JRadioButton tglbut;
    private javax.swing.JRadioButton tglbut1;
    private javax.swing.ButtonGroup tglgrup;
    private javax.swing.ButtonGroup tglgrup2;
    private javax.swing.JButton tmbh;
    private javax.swing.JButton tmbh1;
    private javax.swing.JButton tmbh2;
    private javax.swing.JMenuItem tmbhbuku;
    private javax.swing.JButton tmbhs;
    private javax.swing.JLabel totals;
    private javax.swing.JButton ubah;
    private javax.swing.JButton viewall;
    private javax.swing.JButton viewall1;
    private javax.swing.JButton viewall2;
    private javax.swing.JButton viewall3;
    private javax.swing.JButton viewall6;
    private javax.swing.JLabel waktu;
    private javax.swing.JLabel waktu1;
    private javax.swing.JLabel waktu2;
    private javax.swing.JLabel waktu3;
    // End of variables declaration//GEN-END:variables
}
