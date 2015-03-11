/*
 * SiteAdmin.java
 *
 * Created on May 29, 2008, 2:04 PM
 */
package segundaFase;

import TableModels.ArticuloTableModel;
import TableModels.PosteTableModel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ProgressMonitor;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import omarproject.*;

/**
 *
 * @author  Luiso
 */
public class SiteAdmin extends javax.swing.JFrame implements PropertyChangeListener {

    private static ArrayList<SiteMaterial> materialesSite;
    static ArrayList<PatronLine> conteoArticulosEnProy;
    static PatronLine[] patronLineasBase;
    static MyList conteoArticulosEnProy2;
    static ArrayList<Poste> postes;
    static ManoDeObraPrimaria mdop;
    static ManoDeObraSecundaria mdos;
    static ManoDeObraLighting mdol;
    static Excavacion exc;
    static Gruas gruas;
    static InstalacionPostes ip;
    static Misc misc;
    static PullboxManhole pm;
    static Totales totales;
    static TransforSeccion ts;
    static CableTV ctv;
    private String projectName;
    private static PosteTableModel ptm;
    private static ArticuloTableModel articuloModel;
    private static SiteMaterialModel smm;
    private static int currentIndex;
    private CardLayout cl;
    private static double[] manoDeObraTotal = new double[7];
    private static double excTotal = 0;
    private static double hormTotal = 0;
    private static double gruasTotal = 0;
    private static double miscTotal = 0;
    private static ArrayList<ArrayList<TextValuesComp>> siteInfo;
    private String dateCreated;
    private String lastDateModified;
    private String locale;
    private String comments;
    private int difficulty;
    private boolean savedBefore;
    private boolean notChanged;
    private String lastSavedFilename;
    private TableRowSorter<SiteMaterialModel> sorter;
    private ProgressMonitor progressMonitor;
    private Task task;
    private static ArrayList<PatronSelect3> openPostes;

    /** Creates new form SiteAdmin */
    public SiteAdmin() {
        initComponents();
        postes = new ArrayList<Poste>();
        siteInfo = new ArrayList<ArrayList<TextValuesComp>>();
        materialesSite = new ArrayList<SiteMaterial>();
        conteoArticulosEnProy = new ArrayList<PatronLine>();
        patronLineasBase = new PatronLine[StartWizard.getArticles().size()];
        jTabbedPane1.setMaximumSize(new Dimension(687, 548));
        jTabbedPane1.setSize(new Dimension(687, 548));
        jTabbedPane1.setPreferredSize(new Dimension(687, 548));
        jPanel1.setMaximumSize(new java.awt.Dimension(687, 548));
        for (Material m : StartWizard.getMaterials()) {
            materialesSite.add(new SiteMaterial(m.getName(), 0, m.getPrice()));
        }
        initializeTable();
        initializePosteTable();
        setList();
        cl = (CardLayout) jPanel3.getLayout();
        cl.maximumLayoutSize(jPanel3);
        cl.preferredLayoutSize(jPanel3);
        jLabel3.setVisible(false);
        for (Articulo a : StartWizard.getArticles()) {
            conteoArticulosEnProy.add(new PatronLine(a, 0));
        }
        for (int i = 0; i < patronLineasBase.length; i++) {
            Articulo a = StartWizard.getArticles().get(i);
            patronLineasBase[i] = new PatronLine(a, 0);
        }
        conteoArticulosEnProy2 = new MyList();
        initializeArticuloTable();
        setDateCreated();
        currentIndex = 0;
        savedBefore = false;
        openPostes = new ArrayList<PatronSelect3>();
        setRadios();
        setLocationRelativeTo(null);
    }

    public SiteAdmin(ArrayList<ArrayList<TextValuesComp>> temp, String name, String datec, String datem, int diff, String loc, String com, ArrayList<SiteMaterial> sm, ArrayList<Poste> p, ArrayList<PatronLine> pat, PatronLine[] patlin) {
        initComponents();
        materialesSite = new ArrayList<SiteMaterial>();
        for (Material m : StartWizard.getMaterials()) {
            materialesSite.add(new SiteMaterial(m.getName(), 0, m.getPrice()));
        }
        for(SiteMaterial m : materialesSite){
            for(SiteMaterial mat : sm)
                if(m.getName().equalsIgnoreCase(mat.getName())){
                    m.setQuantity(mat.getQuantity());
                }
        }
        postes = p;
        conteoArticulosEnProy = pat;
        patronLineasBase = patlin;
        jTabbedPane1.setMaximumSize(new Dimension(687, 548));
        jTabbedPane1.setSize(new Dimension(687, 548));
        jTabbedPane1.setPreferredSize(new Dimension(687, 548));
        jPanel1.setMaximumSize(new java.awt.Dimension(687, 548));
        initializeTable();
        initializePosteTable();
        setList();
        cl = (CardLayout) jPanel3.getLayout();
        cl.maximumLayoutSize(jPanel3);
        cl.preferredLayoutSize(jPanel3);
        jLabel3.setVisible(false);
        initializeArticuloTable();
        currentIndex = 0;
        siteInfo = temp;
        deserializeFields(siteInfo);
        dateCreated = datec;
        jTextField1.setText(name);
        jFormattedTextField1.setText(dateCreated);
        jFormattedTextField2.setText(datem);
        jComboBox2.setSelectedIndex(diff);
        jTextField2.setText(loc);
        jTextArea1.setText(com);
        savedBefore = true;
        lastSavedFilename = StartWizard.getFilename();
        updatePosteTotal();
        updateSiteTotal();
        updateArticulosTotal();
        openPostes = new ArrayList<PatronSelect3>();
        conteoArticulosEnProy2 = new MyList();
        setRadios();
    }
    
    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Terrera")) {
                
                
            } else {
                
                
            }
        }
    }

    /*
     * Method to add listeners to each radio button.
     */
    private void setRadios() {
        ButtonListener listener = new ButtonListener();
        //jRadioButton1.addActionListener(listener);
        //jRadioButton2.addActionListener(listener);
    }
    
    public static MyList getMyList(){
        return conteoArticulosEnProy2;
    }
    
    public static JTable getTable2(){
        return jTable2;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress".equals(evt.getPropertyName())) {
            int progress = (Integer) evt.getNewValue();
            progressMonitor.setProgress(progress);
            String message =
                    String.format("Completed %d%%.\n", progress);
            progressMonitor.setNote(message);
            //taskOutput.append(message);
            if (progressMonitor.isCanceled() || task.isDone()) {
                Toolkit.getDefaultToolkit().beep();
                if (progressMonitor.isCanceled()) {
                    task.cancel(true);
                    progressMonitor.close();
                //taskOutput.append("Task canceled.\n");
                } else {
                    progressMonitor.close();
                }
            //startButton.setEnabled(true);
            }
        }
    }

    class Task extends SwingWorker<Void, Void> {

        @Override
        public Void doInBackground() {
            setProgress(0);
            updateSiteMaterialTable();
            setProgress(33);
            updateMaterialesEnPosteTable();
            setProgress(66);
            updatePostesTable();
            setProgress(99);
            return null;
        }

        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            progressMonitor.setProgress(0);
            progressMonitor.close();
        }
    }

    public void updatePostesTable() {
        for (Poste p : postes) {
            for (Poste post : StartWizard.getPostes()) {
                if (p.getClase().equalsIgnoreCase(post.getClase()) && p.getHeight() == post.getHeight()) {
                    p.setPrice(post.getPrice());
                }
            }
        }
        for (Poste p : postes) {
            for (int j = 0; j < p.getPatrones().size(); j++) {
                for (Patron patron : StartWizard.getPatrones()) {
                    if (p.getPatrones().get(j).getNumber().equalsIgnoreCase(patron.getNumber())) {
                        p.getPatrones().set(j, patron);
                    }
                }
            }
        }
        ptm.fireTableDataChanged();
        updatePosteTotal();
    }

    public void updateMaterialesEnPosteTable() {
        for (int i = 0; i < conteoArticulosEnProy.size(); i++) {
            for (Articulo a : StartWizard.getArticles()) {
                Articulo aa = conteoArticulosEnProy.get(i).getArticle();
                if (aa.getDescription().equals(a.getDescription())) {
                    aa.setPrice(a.getPrice());
                    conteoArticulosEnProy.get(i).setPrice(aa.getPrice() * conteoArticulosEnProy.get(i).getQuantity());
                }
            }
        }
        ArrayList<PatronLine> patlinList = new ArrayList<PatronLine>();
        for (Poste p : postes) {
            for (Patron pat : p.getPatrones()) {
                for (PatronLine pl : pat.getLines()) {
                    patlinList.add(pl);
                }
            }
        }
        try {
            conteoArticulosEnProy.clear();
        } catch (Exception e) {
        }
        for (Articulo a : StartWizard.getArticles()) {
            conteoArticulosEnProy.add(new PatronLine(a, 0));
        }
        for (PatronLine pl : patlinList) {
            for (PatronLine patlin : conteoArticulosEnProy) {
                if (pl.getArticle().getNumber() == patlin.getArticle().getNumber()) {
                    patlin.addToQuantity(pl.getQuantity());
                }
            }
        }
        articuloModel.fireTableDataChanged();
        updateArticulosTotal();
    }

    public void updateSiteMaterialTable() {
        double siteTempTotal = 0;
        for (SiteMaterial siteMat : materialesSite) {
            for (Material mat : StartWizard.getMaterials()) {
                if (siteMat.getName().equals(mat.getName())) {
                    siteMat.setPrice(mat.getPrice());
                    siteMat.setTotalPrice(siteMat.getPrice() * siteMat.getQtyPlusOne());
                }
            }
            siteTempTotal += siteMat.getTotalPrice();
        }
        smm.fireTableDataChanged();
        updateSiteTotal();
    }

    public static ArrayList<SiteMaterial> getMaterialesSite() {
        return materialesSite;
    }

    public static void updateArticulosTotal() {
        jFormattedTextField6.setValue(articuloModel.getTotal());
    }

    public static SiteMaterialModel getSmm() {
        return smm;
    }

    private void deserializeFields(ArrayList<ArrayList<TextValuesComp>> temp) {
        mdop.myDeserialize(temp.get(0));
        mdos.myDeserialize(temp.get(1));
        mdol.myDeserialize(temp.get(2));
        exc.myDeserialize(temp.get(3));
        gruas.myDeserialize(temp.get(4));
        ip.myDeserialize(temp.get(5));
        pm.myDeserialize(temp.get(6));
        ts.myDeserialize(temp.get(7));
        ctv.myDeserialize(temp.get(8));
        misc.myDeserialize(temp.get(9));
        totales.myDeserialize(temp.get(10));
    }

    public static double getMaterialesSiteTotal() {
        try {
            return ((Number) jFormattedTextField3.getValue()).doubleValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public static double getMiscTotal() {
        return miscTotal;
    }

    private void initializeFilter() {
        jTextField3.getDocument().addDocumentListener(
                new DocumentListener() {

                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }

                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
    }

    private void newFilter() {
        RowFilter<SiteMaterialModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            String s = jTextField3.getText();
            rf = RowFilter.regexFilter("(?i)" + s, 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    public static void setMiscTotal(double miscTotal) {
        SiteAdmin.miscTotal = miscTotal;
    }

    public static double getGruasTotal() {
        return gruasTotal;
    }

    public static void setGruasTotal(double gruasTotal) {
        SiteAdmin.gruasTotal = gruasTotal;
    }

    public static double getExcTotal() {
        return excTotal;
    }

    public static void setExcTotal(double excTotal) {
        SiteAdmin.excTotal = excTotal;
    }

    public static double getHormTotal() {
        return hormTotal;
    }

    public static void setHormTotal(double hormTotal) {
        SiteAdmin.hormTotal = hormTotal;
    }

    public static double getManoDeObraTotal() {
        double sum = 0;
        for (double d : manoDeObraTotal) {
            sum += d;
        }
        return sum;
    }

    public static void setManoDeObra(int i, double d) {
        manoDeObraTotal[i] = d;
    }

    private void getFields() {
        siteInfo.add(mdop.mySerialize());
        siteInfo.add(mdos.mySerialize());
        siteInfo.add(mdol.mySerialize());
        siteInfo.add(exc.mySerialize());
        siteInfo.add(gruas.mySerialize());
        siteInfo.add(ip.mySerialize());
        siteInfo.add(pm.mySerialize());
        siteInfo.add(ts.mySerialize());
        siteInfo.add(ctv.mySerialize());
        siteInfo.add(misc.mySerialize());
        siteInfo.add(totales.mySerialize());
    }

    private void setList() {
        mdop = new ManoDeObraPrimaria();
        mdos = new ManoDeObraSecundaria();
        mdol = new ManoDeObraLighting();
        ts = new TransforSeccion();
        pm = new PullboxManhole();
        ip = new InstalacionPostes();
        gruas = new Gruas();
        exc = new Excavacion();
        ctv = new CableTV();
        misc = new Misc();
        totales = new Totales();
        jPanel3.add(mdop, "mdop");
        jPanel3.add(mdos, "mdos");
        jPanel3.add(mdol, "mdol");
        jPanel3.add(ts, "ts");
        jPanel3.add(pm, "pm");
        jPanel3.add(ip, "ip");
        jPanel3.add(gruas, "gruas");
        jPanel3.add(exc, "exc");
        jPanel3.add(ctv, "ctv");
        jPanel3.add(misc, "misc");
        jPanel3.add(totales, "totales");
    }

    public void replacePane(JTabbedPane pane) {
        jTabbedPane1 = pane;
    }

    private void setDateCreated() {
        String DATE_FORMAT_NOW = "MMMMM dd, yyyy hh:mm aaa";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        String text = sdf.format(cal.getTime());
        jFormattedTextField1.setText(text);
        dateCreated = text;
    }

    public static ArrayList<PatronLine> getConteo() {
        return conteoArticulosEnProy;
    }

    public static ArrayList<Poste> getPostes() {
        return postes;
    }

    public static void updateSiteTotal() {
        double d = smm.getTotal();
        jFormattedTextField7.setValue(d);
        double d2 = ((Number) jFormattedTextField4.getValue()).doubleValue();
        double total = d + d * d2;
        jFormattedTextField3.setValue(total);
    }

    public static void updatePosteTotal() {
        jFormattedTextField5.setValue(ptm.getTotal());
        jFormattedTextField8.setValue(ptm.getPosteOnlyTotal());
    }

    public static PatronLine[] getPatronLineasBase() {
        return patronLineasBase;
    }

    public JPanel getMaterialsPanel() {
        return jPanel2;
    }

    public JPanel getAdminPanel() {
        return jPanel3;
    }

    public JScrollPane getScroll() {
        return jScrollPane1;
    }

    public JPanel getInfoPanel() {
        return jPanel4;
    }

    public static void setLoggedIn() {
        jLabel2.setVisible(false);
        jLabel3.setVisible(true);
        mdop.makeEditable();
        mdos.makeEditable();
        mdol.makeEditable();
        ts.makeEditable();
        pm.makeEditable();
        ip.makeEditable();
        ctv.makeEditable();
        misc.makeEditable();
    }

    public static void setLoggedOut() {
        jLabel2.setVisible(true);
        jLabel3.setVisible(false);
        mdop.makeUnEditable();
        mdos.makeUnEditable();
        mdol.makeUnEditable();
        ts.makeUnEditable();
        pm.makeUnEditable();
        ip.makeUnEditable();
        ctv.makeUnEditable();
        misc.makeUnEditable();
    }

    public static ManoDeObraPrimaria getMDOP() {
        return mdop;
    }

    public static ManoDeObraSecundaria getMDOS() {
        return mdos;
    }

    public static ManoDeObraLighting getMDOL() {
        return mdol;
    }

    public static Excavacion getExc() {
        return exc;
    }

    public static Gruas getGruas() {
        return gruas;
    }

    public static InstalacionPostes getIp() {
        return ip;
    }

    public static Misc getMisc() {
        return misc;
    }

    public static PullboxManhole getPm() {
        return pm;
    }

    public static TransforSeccion getTs() {
        return ts;
    }

    public static Totales getTotales() {
        return totales;
    }

    public static CableTV getCable() {
        return ctv;
    }

    private void updateFrame(String s, int i) {
        String result = null;
        if (s.equals("1) Mano de Obra - Primaria")) {
            result = "mdop";
        }
        if (s.equals("2) Mano de Obra - Secundaria")) {
            result = "mdos";
        }
        if (s.equals("3) Mano de Obra - Lighting")) {
            result = "mdol";
        }
        if (s.equals("4) Transformadores, Seccionadoras, etc.")) {
            result = "ts";
        }
        if (s.equals("5) Instalacion Pullboxes y Manhole")) {
            result = "pm";
        }
        if (s.equals("6) Instalacion Postes")) {
            result = "ip";
        }
        if (s.equals("7) Gruas y Transformadores")) {
            result = "gruas";
        }
        if (s.equals("8) Excavacion")) {
            result = "exc";
        }
        if (s.equals("9) CableTV y Telefono")) {
            result = "ctv";
        }
        if (s.equals("10) Administracion y Miscelaneo")) {
            result = "misc";
        }
        if (s.equals("11) Totales")) {
            result = "totales";
        }
        currentIndex = i;
        cl.show(jPanel3, result);
    }

    public static PosteTableModel getPosteTableModel() {
        return ptm;
    }

    public static ArticuloTableModel getArticuloTableModel() {
        return articuloModel;
    }

    private void initializeArticuloTable() {
        articuloModel = new ArticuloTableModel(conteoArticulosEnProy2);
        jTable3.setModel(articuloModel);
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTable3.getColumnModel().getColumn(2).setPreferredWidth(75);
        CurrencyRenderer cr = new CurrencyRenderer();
        jTable3.setDefaultRenderer(Double.class, cr);
        IntegerRenderer ir = new IntegerRenderer();
        jTable3.setDefaultRenderer(Integer.class, ir);
        jScrollPane4.getViewport().setBackground(Color.WHITE);
        jTable3.setDefaultRenderer(Object.class, new ColorRenderer());
        ToolTipManager.sharedInstance().unregisterComponent(jTable3);
        ToolTipManager.sharedInstance().unregisterComponent(jTable3.getTableHeader());
    }

    private void initializePosteTable() {
        ptm = new PosteTableModel(postes);
        jTable2.setModel(ptm);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(50);
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable2.getColumnModel().getColumn(4).setPreferredWidth(100);
        CurrencyRenderer cr = new CurrencyRenderer();
        jTable2.setDefaultRenderer(Double.class, cr);
        IntegerRenderer ir = new IntegerRenderer();
        jTable2.setDefaultRenderer(Integer.class, ir);
        jScrollPane3.getViewport().setBackground(Color.WHITE);
        jTable2.setDefaultRenderer(Object.class, new ColorRenderer());
        ToolTipManager.sharedInstance().unregisterComponent(jTable2);
        ToolTipManager.sharedInstance().unregisterComponent(jTable2.getTableHeader());
        jTable2.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int viewRow = jTable2.getSelectedRow();
                    if (viewRow != -1) {
                        int modelRow = jTable2.convertRowIndexToModel(viewRow);
                        Poste p = postes.get(modelRow);
                        int index = posteIsOpen(p);
                        if (index == -1) {
                            PatronSelect3 pe = new PatronSelect3(p, false, modelRow);
                            pe.setVisible(true);
                            openPostes.add(pe);
                        } else {
                            PatronSelect3 pe = openPostes.get(index);
                            pe.toFront();
                            pe.setState(Frame.NORMAL);
                        }
                    }
                }
            }
        });
    }

    private void initializeTable() {
        initializeFilter();
        smm = new SiteMaterialModel(materialesSite);
        sorter = new TableRowSorter<SiteMaterialModel>(smm);
        jTable1.setModel(smm);
        jTable1.setRowSorter(sorter);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(190);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(85);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(95);
        CurrencyRenderer cr = new CurrencyRenderer();
        jTable1.setDefaultRenderer(Double.class, cr);
        IntegerRenderer ir = new IntegerRenderer();
        jTable1.setDefaultRenderer(Integer.class, ir);
        jTable1.setDefaultRenderer(Object.class, new ColorRenderer());
        ToolTipManager.sharedInstance().unregisterComponent(jTable1);
        ToolTipManager.sharedInstance().unregisterComponent(jTable1.getTableHeader());
    }

    /* Quita los articulos necesarios despues de borrar un
     * poste de la lista.
     * */
    private void removeArticulos(Poste p) {
        ArrayList<PatronLine> lineasViejas = new ArrayList<PatronLine>();
        ArrayList<Patron> patronesOriginales = new ArrayList<Patron>();
        for (Patron pat : p.getPatrones()) {
            patronesOriginales.add(pat);
        }
        for (Patron pa : patronesOriginales) {
            for (PatronLine pl : pa.getLines()) {
                lineasViejas.add(pl);
            }
        }
        for (PatronLine pali : lineasViejas) {
            for (PatronLine patlin : conteoArticulosEnProy) {
                if (patlin.compareTo(pali) == 0) {
                    patlin.subtractFromQuantity(pali.getQuantity());
                }
            }
        }
        refreshArticuloTable();
    }

    private static void refreshArticuloTable() {
        articuloModel.doRefresh();
    }

    private void setModifiedDate() {
        String DATE_FORMAT_NOW = "MMMMM dd, yyyy hh:mm aaa";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        String text = sdf.format(cal.getTime());
        lastDateModified = text;
        jFormattedTextField2.setText(text);
    }

    private void getInfoToSave() {
        projectName = jTextField1.getText();
        setModifiedDate();
        locale = jTextField2.getText();
        comments = jTextArea1.getText();
        difficulty = jComboBox2.getSelectedIndex();
    }

    private void writeInfo(String filename) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(filename));
            out.writeObject(siteInfo);
            out.writeObject(projectName);
            out.writeObject(dateCreated);
            out.writeObject(lastDateModified);
            out.writeObject(difficulty);
            out.writeObject(locale);
            out.writeObject(comments);
            out.writeObject(materialesSite);
            out.writeObject(postes);
            out.writeObject(conteoArticulosEnProy);
            out.writeObject(patronLineasBase);
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void saveFile(String filename) {
        getFields();
        getInfoToSave();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        writeInfo(filename);
        savedBefore = true;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private String currentTimeAndDate() {
        String DATE_FORMAT_NOW = "MMMMM dd, yyyy hh:mm aaa";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        String text = sdf.format(cal.getTime());
        return text;
    }

    private int posteIsOpen(Poste p) {
        for (int i = 0; i < openPostes.size(); i++) {
            if (openPostes.get(i).getPosteName().equals(p.getName())) {
                return i;
            }
        }
        return -1;
    }
    
    public static ArrayList<PatronSelect3> getOpenPostes(){
        return openPostes;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        niveles = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane1.setPreferredSize(new Dimension(687,548));
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jTextField1 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CEG Engineering - Site Manager");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(687, 548));

        jTabbedPane1.setMaximumSize(new java.awt.Dimension(687, 548));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(687, 548));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(687, 548));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel4.setPreferredSize(new java.awt.Dimension(687, 548));

        jLabel4.setText("Nombre del proyecto:");

        jLabel5.setText("Dia Creado:");

        jLabel6.setText("Ultimo dia modificado:");

        jLabel7.setText("Dificultad:");

        jFormattedTextField1.setEditable(false);

        jFormattedTextField2.setEditable(false);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Facil", "Moderado", "Dificil" }));

        jLabel8.setText("Localizacion:");

        jLabel9.setText("Comentarios:");

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField2)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextField2)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(287, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(180, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Informacion General", jPanel4);

        jPanel2.setNextFocusableComponent(jPanel3);
        jPanel2.setPreferredSize(new java.awt.Dimension(687, 548));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jFormattedTextField3.setEditable(false);
        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Total");

        jFormattedTextField4.setEditable(false);
        jFormattedTextField4.setFormatterFactory(StartWizard.getMine());
        jFormattedTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField4.setValue(AdminValues.getIvu());

        jLabel15.setText("IVU");

        jFormattedTextField7.setEditable(false);
        jFormattedTextField7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel16.setText("Sub-Total");

        jLabel13.setText("Filtrar:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel16)
                    .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Materiales del site", jPanel2);

        jPanel3.setMaximumSize(new java.awt.Dimension(687, 548));
        jPanel3.setLayout(new java.awt.CardLayout());
        jTabbedPane1.addTab("Información del site", jPanel3);

        jPanel5.setPreferredSize(new java.awt.Dimension(687, 548));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        jButton5.setText("Añadir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Editar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Remover");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Total con materiales");

        jFormattedTextField5.setEditable(false);
        jFormattedTextField5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jFormattedTextField8.setEditable(false);
        jFormattedTextField8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Total de postes");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(52, 52, 52)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Postes", jPanel5);

        jPanel6.setPreferredSize(new java.awt.Dimension(687, 548));

        jTable3.setAutoCreateRowSorter(true);
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable3);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel11.setText("Total");

        jFormattedTextField6.setEditable(false);
        jFormattedTextField6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(565, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Materiales de los postes", jPanel6);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("<< Anterior");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Próximo>>");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("Ir a página específica:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1) Mano de Obra - Primaria", "2) Mano de Obra - Secundaria", "3) Mano de Obra - Lighting", "4) Transformadores, Seccionadoras, etc.", "5) Instalacion Pullboxes y Manhole", "6) Instalacion Postes", "7) Gruas y Transformadores", "8) Excavacion", "9) CableTV y Telefono", "10) Administracion y Miscelaneo", "11) Totales" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton4});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Login");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("Logout");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jMenu3.setText("File");

        jMenuItem3.setText("Save");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Save As");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);
        jMenu3.add(jSeparator2);

        jMenuItem6.setText("Close");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar2.add(jMenu3);

        jMenu5.setText("Edit");

        jMenuItem1.setText("Actualizar materiales de este proyecto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem1);

        jMenuBar2.add(jMenu5);

        jMenu1.setText("Print");

        jMenu2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenu2.setText("Print table..");

        jMenuItem7.setText("Materiales del site");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("Postes");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("Materiales postes");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenu1.add(jMenu2);

        jMenu4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenu4.setText("Print specific page..");

        jMenuItem5.setText("Primaria");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem11.setText("Secundaria");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuItem12.setText("Lighting");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuItem13.setText("Transformadores");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuItem14.setText("Pullboxes");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem14);

        jMenuItem15.setText("Postes");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem15);

        jMenuItem16.setText("Gruas");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        jMenuItem17.setText("Excavación");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem17);

        jMenuItem18.setText("Cable TV");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem18);

        jMenuItem19.setText("Misceláneos");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem19);

        jMenuItem20.setText("Totales");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem20);

        jMenu1.add(jMenu4);

        jMenuItem10.setText("Print all \"Mano de obra\"");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuBar2.add(jMenu1);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    if (currentIndex <= 10) {
        currentIndex++;
    }
    if (currentIndex == 11) {
        currentIndex = 0;
    }
    cl.next(jPanel3);
    jComboBox1.setSelectedIndex(currentIndex);
}//GEN-LAST:event_jButton4ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    if (currentIndex >= 0) {
        currentIndex--;
    }
    if (currentIndex == -1) {
        currentIndex = 10;
    }
    cl.previous(jPanel3);
    jComboBox1.setSelectedIndex(currentIndex);
}//GEN-LAST:event_jButton3ActionPerformed

private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
    int i = jTabbedPane1.getSelectedIndex();
    if (i == 2) {
        jButton3.setVisible(true);
        jButton4.setVisible(true);
        jLabel1.setVisible(true);
        jComboBox1.setVisible(true);
        if (currentIndex == 10) {
            totales.setSiteTotal();
        }
    } else {
        jLabel1.setVisible(false);
        jComboBox1.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
    }
}//GEN-LAST:event_jTabbedPane1StateChanged

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
    JComboBox cb = (JComboBox) evt.getSource();
    String option = (String) cb.getSelectedItem();
    int index = cb.getSelectedIndex();
    updateFrame(option, index);
}//GEN-LAST:event_jComboBox1ActionPerformed

private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
    try {
        PasswordBox pb = new PasswordBox("SiteAdmin", this);
        pb.setVisible(true);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Password is not set!");
    }
}//GEN-LAST:event_jLabel2MouseClicked

private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
    setLoggedOut();
    jLabel2.setVisible(true);
    jLabel3.setVisible(false);
}//GEN-LAST:event_jLabel3MouseClicked

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if (savedBefore) {
        if (lastSavedFilename.endsWith(".proy")) {
            saveFile(lastSavedFilename);
        } else {
            saveFile(lastSavedFilename + ".proy");
        }
    } else {
        StartWizard.chooser.setFileFilter(StartWizard.getFilter());
        int returnVal = StartWizard.chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = StartWizard.chooser.getSelectedFile();
            String filename = selectedFile.getAbsolutePath();
            if (filename.endsWith(".proy")) {
                saveFile(filename);
            } else {
                saveFile(filename + ".proy");
            }
            lastSavedFilename = filename;
        }
    }
    this.setVisible(false);
    this.dispose();
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    this.setVisible(false);
    this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    PatronSelect3 pe = new PatronSelect3(new Poste(0, "", 0), true, 0);
    pe.setVisible(true);
    notChanged = false;
}//GEN-LAST:event_jButton5ActionPerformed

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    if (postes.size() > 0) {
        try {
            int viewRow = jTable2.getSelectedRow();
            if (viewRow != -1) {
                int modelRow = jTable2.convertRowIndexToModel(viewRow);
                Poste p = postes.get(modelRow);
                int index = posteIsOpen(p);
                if (index == -1) {
                    PatronSelect3 pe = new PatronSelect3(p, false, modelRow);
                    pe.setVisible(true);
                    openPostes.add(pe);
                } else {
                    PatronSelect3 pe = openPostes.get(index);
                    pe.toFront();
                    pe.setState(Frame.NORMAL);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor seleccionar un poste de la lista primero.");
        }
    }
}//GEN-LAST:event_jButton6ActionPerformed

private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    if (postes.size() > 0) {
        try {
            int viewRow = jTable2.getSelectedRow();
            if (viewRow != -1) {
                Object[] options = {"Sí", "No"};
                int option = JOptionPane.showOptionDialog(null, "Esta seguro que " +
                        "quiere borrar este poste de la lista?", "Confirmar",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, options, options[0]);
                if (option == 0) {
                    int modelRow = jTable2.convertRowIndexToModel(viewRow);
                    Poste p = ((PosteTableModel) ptm).removePosteAt(modelRow);
                    removeArticulos(p);
                    postes.remove(p);
                    notChanged = false;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor seleccionar un poste de la lista primero.");
        }
    }
}//GEN-LAST:event_jButton7ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    if (savedBefore) {//GEN-LAST:event_jMenuItem3ActionPerformed
            if (lastSavedFilename.endsWith(".proy")) {
                saveFile(lastSavedFilename);
            } else {
                saveFile(lastSavedFilename + ".proy");
            }
        } else {
            StartWizard.chooser.setFileFilter(StartWizard.getFilter());
            int returnVal = StartWizard.chooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = StartWizard.chooser.getSelectedFile();
                String filename = selectedFile.getAbsolutePath();
                if (filename.endsWith(".proy")) {
                    saveFile(filename);
                } else {
                    saveFile(filename + ".proy");
                }
                lastSavedFilename = filename;
            }
        }
    }

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    StartWizard.chooser.setFileFilter(StartWizard.getFilter());
    int returnVal = StartWizard.chooser.showSaveDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File selectedFile = StartWizard.chooser.getSelectedFile();
        String filename = selectedFile.getAbsolutePath();
        if (filename.endsWith(".proy")) {
            saveFile(filename);
        } else {
            saveFile(filename + ".proy");
        }
        lastSavedFilename = filename;
    }
}//GEN-LAST:event_jMenuItem4ActionPerformed

private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
    try {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        String subtotal = jFormattedTextField7.getText();
        String ivu = jFormattedTextField4.getText();
        String total = jFormattedTextField3.getText();
        String proyecto = jTextField1.getText();
        MessageFormat footerFormat = new MessageFormat(currentTimeAndDate() + "         Sub-total: " + subtotal + "  IVU: " + ivu +
                "  Total: " + total);
        MessageFormat headerFormat = new MessageFormat(proyecto);
        jTable1.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
    } catch (PrinterException ex) {//GEN-LAST:event_jMenuItem7ActionPerformed
            Logger.getLogger(SiteAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
    try {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        String total = jFormattedTextField5.getText();
        String proyecto = jTextField1.getText();
        MessageFormat footerFormat = new MessageFormat(currentTimeAndDate() + "         Total: " + total);
        MessageFormat headerFormat = new MessageFormat(proyecto);
        jTable2.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
    } catch (PrinterException ex) {
        Logger.getLogger(SiteAdmin.class.getName()).log(Level.SEVERE, null, ex);
    }
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
}//GEN-LAST:event_jMenuItem8ActionPerformed

private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
    try {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        String total = jFormattedTextField6.getText();
        String proyecto = jTextField1.getText();
        MessageFormat footerFormat = new MessageFormat(currentTimeAndDate() + "         Total: " + total);
        MessageFormat headerFormat = new MessageFormat(proyecto);
        jTable3.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
    } catch (PrinterException ex) {
        Logger.getLogger(SiteAdmin.class.getName()).log(Level.SEVERE, null, ex);
    }
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
}//GEN-LAST:event_jMenuItem9ActionPerformed

private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
    PrintUtilities.printComponent(mdop, 0);
}//GEN-LAST:event_jMenuItem5ActionPerformed

private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
    PrintUtilities.printComponent(mdos, 0);
}//GEN-LAST:event_jMenuItem11ActionPerformed

private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
    PrintUtilities.printComponent(mdol, 0);
}//GEN-LAST:event_jMenuItem12ActionPerformed

private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
    PrintUtilities.printComponent(ts, 0);
}//GEN-LAST:event_jMenuItem13ActionPerformed

private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
    PrintUtilities.printComponent(pm, 0);
}//GEN-LAST:event_jMenuItem14ActionPerformed

private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
    PrintUtilities.printComponent(ip, 0);
}//GEN-LAST:event_jMenuItem15ActionPerformed

private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
    PrintUtilities.printComponent(gruas, 0);
}//GEN-LAST:event_jMenuItem16ActionPerformed

private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
    PrintUtilities.printComponent(exc, 0);
}//GEN-LAST:event_jMenuItem17ActionPerformed

private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
    PrintUtilities.printComponent(ctv, 0);
}//GEN-LAST:event_jMenuItem18ActionPerformed

private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
    PrintUtilities.printComponent(misc, 0);
}//GEN-LAST:event_jMenuItem19ActionPerformed

private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
    PrintUtilities.printComponent(totales, 0);
}//GEN-LAST:event_jMenuItem20ActionPerformed

private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
    PrintUtilities.printComponent(this, 1);
}//GEN-LAST:event_jMenuItem10ActionPerformed

private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
    int input = JOptionPane.showConfirmDialog(this, "Quiere guardar su trabajo antes de salir?");
    if (input == JOptionPane.OK_OPTION) {
        if (savedBefore) {
            if (lastSavedFilename.endsWith(".proy")) {
                saveFile(lastSavedFilename);
            } else {
                saveFile(lastSavedFilename + ".proy");
            }
        } else {
            int returnVal = StartWizard.chooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = StartWizard.chooser.getSelectedFile();
                String filename = selectedFile.getAbsolutePath();
                if (filename.endsWith(".proy")) {
                    saveFile(filename);
                } else {
                    saveFile(filename + ".proy");
                }
                lastSavedFilename = filename;
            }
        }
        this.setVisible(false);
        this.dispose();
    } else if (input == JOptionPane.NO_OPTION) {
        this.setVisible(false);
        this.dispose();
    } else {
    }
}//GEN-LAST:event_jMenuItem6ActionPerformed

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    int input = JOptionPane.showConfirmDialog(this, "Quiere guardar su trabajo antes de salir?");
    if (input == JOptionPane.OK_OPTION) {
        if (savedBefore) {
            if (lastSavedFilename.endsWith(".proy")) {
                saveFile(lastSavedFilename);
            } else {
                saveFile(lastSavedFilename + ".proy");
            }
        } else {
            int returnVal = StartWizard.chooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = StartWizard.chooser.getSelectedFile();
                String filename = selectedFile.getAbsolutePath();
                if (filename.endsWith(".proy")) {
                    saveFile(filename);
                } else {
                    saveFile(filename + ".proy");
                }
                lastSavedFilename = filename;
            }
        }
        this.setVisible(false);
        this.dispose();
    } else if (input == JOptionPane.NO_OPTION) {
        this.setVisible(false);
        this.dispose();
    } else {
    }
}//GEN-LAST:event_formWindowClosing

private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
    notChanged = false;
}//GEN-LAST:event_jTable1PropertyChange

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    updatePostesTable();
    updateMaterialesEnPosteTable();
    updateSiteMaterialTable();
    final PreciosActualizadosWindow scw = new PreciosActualizadosWindow();
    Point p = this.getLocationOnScreen();
    p.translate(50, 50);
    scw.setLocation(p);
    scw.setVisible(true);
    ActionListener updateTask = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            scw.dispose();
            scw.setVisible(false);
        }
    };
    Timer timer = new Timer(500, updateTask);
    timer.setInitialDelay(1500);
    timer.setRepeats(false);
    timer.start();
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
}//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SiteAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private static javax.swing.JFormattedTextField jFormattedTextField3;
    private static javax.swing.JFormattedTextField jFormattedTextField4;
    private static javax.swing.JFormattedTextField jFormattedTextField5;
    private static javax.swing.JFormattedTextField jFormattedTextField6;
    private static javax.swing.JFormattedTextField jFormattedTextField7;
    private static javax.swing.JFormattedTextField jFormattedTextField8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private static javax.swing.JLabel jLabel2;
    private static javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private static javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.ButtonGroup niveles;
    // End of variables declaration//GEN-END:variables
}
