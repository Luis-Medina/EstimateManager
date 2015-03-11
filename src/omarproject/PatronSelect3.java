/*
 * PatronSelect.java
 *
 * Created on December 19, 2007, 8:52 PM
 */
package omarproject;

import TableModels.MaterialesEnPosteModel;
import TableModels.PatronSearchModel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ToolTipManager;
import segundaFase.ColorRenderer;
import segundaFase.SiteAdmin;
import segundaFase.SiteMaterial;

/**
 *
 * @author  Luiso
 */
public class PatronSelect3 extends javax.swing.JFrame {

    private static MyList<PatronLine> patronLineasSelected = new MyList<PatronLine>();
    private static ArrayList<Patron> patronesSelected = new ArrayList<Patron>();
    private String clase;
    private Poste elPoste;
    private boolean newOne;
    private int posteIndex;
    private Vector heights = new Vector();
    private Vector classes = new Vector();
    private Vector<String> patronNames = new Vector<String>();
    private String tempAlto = "";
    private String tempClase = "";
    private JPopupMenu popup;
    private ArrayList<Patron> patronesOriginales = new ArrayList<Patron>();
    private double oldPostePrice;
    private double oldPosteTotalPrice;
    private double grandTotal;

    public PatronSelect3() {
        initComponents();
    }

    /** Creates new form PatronSelect */
    public PatronSelect3(Poste p, boolean tOrF, int index) {
        setList();
        setBoxes();
        initComponents();
        initializePopups();
        elPoste = p;
        posteIndex = index;
        if (p.getPatrones().size() != 0) {
            for (Patron patron : p.getPatrones()) {
                for (String name : patronNames) {
                    if (patron.getNumber().equalsIgnoreCase(name)) {
                        jList1.setSelectedValue(name, false);
                    }
                }
            }
        }
        newOne = tOrF;
        if (!newOne) {
            restoreOld();
        }
    }

    public String getPosteName() {
        return elPoste.getName();
    }

    private void restoreOld() {
        patronesOriginales = (ArrayList<Patron>) elPoste.getPatrones().clone();
        jTextField2.setText(elPoste.getName());
        oldPostePrice = elPoste.getPrice();
        oldPosteTotalPrice = elPoste.getTotalPrice();
        Number numberValue = (Number) oldPosteTotalPrice;
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String value = formatter.format(numberValue.doubleValue());
        jFormattedTextField2.setText(value);
        numberValue = (Number) oldPostePrice;
        value = formatter.format(numberValue.doubleValue());
        jFormattedTextField1.setText(value);
        jComboBox1.setSelectedItem(elPoste.getHeight());
        jComboBox2.setSelectedItem(elPoste.getClase());
    }

    private void initializePopups() {
        MouseListener popupListener = new PopupListener();
        jList1.addMouseListener(popupListener);
    }

    class PopupListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup = new JPopupMenu();
                JList list = (JList) e.getComponent();
                list.getModel();
                int index = list.locationToIndex(e.getPoint());
                final String s = patronNames.get(index);
                JMenuItem menuItem = new JMenuItem("Ver información detallada para el " +
                        "patrón " + s);
                menuItem.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jTabbedPane1.setSelectedIndex(2);
                        jTextField1.setText(s);
                        if (isValidPatron(s)) {
                            Patron p = Finders.findPatron(s);
                            createArticuloTable(p);
                        } else {
                            JOptionPane.showMessageDialog(null, "El patrón escrito no es correcto. Intente otra vez.");
                        }
                    }
                });
                popup.add(menuItem);
                popup.show(e.getComponent(),
                        e.getX(), e.getY());
            }
        }
    }

    private void setBoxes() {
        heights.add("");
        classes.add("");
        for (Poste p : StartWizard.postes) {
            if (!heights.contains(p.getHeight())) {
                heights.add(p.getHeight());
            }
            if (!classes.contains(p.getClase())) {
                classes.add(p.getClase());
            }
        }
    }

    private void setList() {
        for (Patron p : StartWizard.patrones) {
            patronNames.add(p.getNumber());
        }
    }

    private void createArticuloTable(Patron p) {
        PatronSearchModel psm = new PatronSearchModel(p.getLines());
        jTable1.setModel(psm);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
        CurrencyRenderer cr = new CurrencyRenderer();
        jTable1.setDefaultRenderer(Double.class, cr);
        IntegerRenderer ir = new IntegerRenderer();
        jTable1.setDefaultRenderer(Integer.class, ir);
        jTable1.setDefaultRenderer(Object.class, new ColorRenderer());
        ToolTipManager.sharedInstance().unregisterComponent(jTable1);
        ToolTipManager.sharedInstance().unregisterComponent(jTable1.getTableHeader());
    }

    private void createMaterialesEnPosteTable() {
        getCheckedPatrones();
        MaterialesEnPosteModel mepm = new MaterialesEnPosteModel(patronLineasSelected);
        jTable2.setModel(mepm);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(200);
        CurrencyRenderer cr = new CurrencyRenderer();
        jTable2.setDefaultRenderer(Double.class, cr);
        IntegerRenderer ir = new IntegerRenderer();
        jTable2.setDefaultRenderer(Integer.class, ir);
        jTable2.setDefaultRenderer(Object.class, new ColorRenderer());
        ToolTipManager.sharedInstance().unregisterComponent(jTable2);
        ToolTipManager.sharedInstance().unregisterComponent(jTable2.getTableHeader());
    }

    private boolean isValidPatron(String s) {
        for (Patron p : StartWizard.patrones) {
            if (p.getNumber().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    private MyList<PatronLine> getCheckedPatrones() {
        MyList<PatronLine> tempLineasSelected = new MyList<PatronLine>();
        for (int i : jList1.getSelectedIndices()) {
            String patronName = patronNames.get(i);
            Patron p = Finders.findPatron(patronName);
            ArrayList<PatronLine> temp = p.getLines();
            for (PatronLine pl : temp) {
                tempLineasSelected.addPatronLine(pl);
            }
        }
        return tempLineasSelected;
    }

    private void setCheckedPatrones() {
        patronesSelected.clear();
        patronLineasSelected = new MyList<PatronLine>();
        //if(!newOne)
        //    elPoste.removeAllPatrones();
        for (int i : jList1.getSelectedIndices()) {
            String patronName = patronNames.get(i);
            Patron p = Finders.findPatron(patronName);
            patronesSelected.add(p);
            ArrayList<PatronLine> temp = p.getLines();
            for (PatronLine pl : temp) {
                patronLineasSelected.addPatronLine(pl);
            }
        }
    }

    private void updatePrice() {
        double tempPrice = 0;
        for (int i : jList1.getSelectedIndices()) {
            String patronName = patronNames.get(i);
            Patron p = Finders.findPatron(patronName);
            tempPrice += p.getPrice();
        }
        jTextField3.setText(Utils.returnDollarValue(tempPrice));
        grandTotal = elPoste.getPrice() + tempPrice;
        jFormattedTextField2.setText(Utils.returnDollarValue(grandTotal));
        //elPoste.setTotalPrice(grandTotal);
    }

    private void updateMateriales() {
        for (PatronLine pl : patronLineasSelected) {
            SiteAdmin.getMyList().add(pl);
        }
        SiteAdmin.getArticuloTableModel().fireTableDataChanged();
    }

    private void removeOldArticulos() {
        for (Patron patron : patronesOriginales) {
            for (PatronLine pl : patron.getLines()) {
                SiteAdmin.getMyList().removePatronLine(pl);
            }
        }
       SiteAdmin.getArticuloTableModel().fireTableDataChanged();
    }

    private int doAddAction() {
        if (posteExists()) {
            JOptionPane.showMessageDialog(null, "El poste ya existe");
            return 0;
        } else {
            SiteAdmin.getPostes().add(elPoste);
            SiteAdmin.getPosteTableModel().fireTableDataChanged();
            updateMateriales();
            if (elPoste.getClase().equals("H-6") || elPoste.getClase().equals("H-8")) {
                for (SiteMaterial sm : SiteAdmin.getMaterialesSite()) {
                    if (sm.getName().equalsIgnoreCase("Base de poste")) {
                        sm.setQuantity(sm.getQuantity() + 1);
                        SiteAdmin.getSmm().fireTableDataChanged();
                    }
                }
            }
            return 1;
        }
    }

    private boolean posteExists() {
        for (Poste pos : SiteAdmin.getPostes()) {
            if (pos.getName().equals(jTextField2.getText())) {
                return true;
            }
        }
        return false;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Alto = new javax.swing.ButtonGroup();
        Clase = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList(patronNames);
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalles del poste");
        setLocationByPlatform(true);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 500, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jLabel2.setText("Nombre del poste");

        jLabel4.setText("Alto");

        jLabel5.setText("Clase");

        jLabel6.setText("Precio del poste");

        jFormattedTextField1.setEditable(false);
        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));

        jLabel7.setText("Precio con materiales");

        jFormattedTextField2.setEditable(false);
        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));

        jComboBox1.setModel(new DefaultComboBoxModel(heights));
        jComboBox1.setName("Alto"); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new DefaultComboBoxModel(classes));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField2)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(323, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        jTabbedPane1.addTab("Informacion General", jPanel7);

        jPanel1.setFocusCycleRoot(true);
        jPanel1.setFocusTraversalPolicyProvider(true);

        jList1.setFixedCellHeight(23);
        jList1.setFixedCellWidth(75);
        jList1.setLayoutOrientation(javax.swing.JList.VERTICAL_WRAP);
        jList1.setSelectionModel(new DefaultListSelectionModel()
            {
                public void setSelectionInterval(int index0, int index1) {
                    if (isSelectedIndex(index0))
                    super.removeSelectionInterval(index0, index1);
                    else
                    super.addSelectionInterval(index0, index1);
                }
            } );
            jList1.setVisibleRowCount(15);
            jScrollPane3.setViewportView(jList1);

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addContainerGap())
            );

            jTabbedPane1.addTab("Patrones", jPanel1);

            jLabel1.setText("Escriba aqui el patron del cual desea obtener informacion detallada:"); // NOI18N

            jButton3.setText("Buscar");
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
                }
            });

            jLabel8.setText("Ej. ac-a1");

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3))
                        .addComponent(jLabel1))
                    .addContainerGap(195, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(9, 9, 9)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jTable1.setAutoCreateRowSorter(true);
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Codigo", "Descripcion", "Cantidad", "Precio"
                }
            ));
            jScrollPane1.setViewportView(jTable1);

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addContainerGap())
            );

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("Informacion detallada", jPanel2);

            jTable2.setAutoCreateRowSorter(true);
            jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Codigo", "Descripcion", "Cantidad", "Precio"
                }
            ));
            jScrollPane2.setViewportView(jTable2);

            jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14));
            jLabel3.setText("Total:   ");

            jTextField3.setEditable(false);

            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(336, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("Materiales en el poste", jPanel5);

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

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2))
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(14, Short.MAX_VALUE))
            );

            layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton1))
                    .addContainerGap())
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        this.dispose();
        SiteAdmin.getOpenPostes().remove(this);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setCheckedPatrones();
        if (jTextField2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor escribir un nombre para el poste.");
            jTabbedPane1.setSelectedIndex(0);
        } else if (elPoste.getHeight() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor seleccionar un alto para el poste.");
            jTabbedPane1.setSelectedIndex(0);
        } else if (elPoste.getClase().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor seleccionar una clase para el poste.");
            jTabbedPane1.setSelectedIndex(0);
        } else {
            elPoste.setTotalPrice(grandTotal);
            elPoste.setPatrones(patronesSelected);
            elPoste.setName(jTextField2.getText());
            if (newOne) {
                if (doAddAction() == 1) {
                    JTable table = SiteAdmin.getTable2();
                    table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, table.getColumnCount(), true));
                    this.setVisible(false);
                    this.dispose();
                }
            } else {
                SiteAdmin.getPostes().set(posteIndex, elPoste);
                SiteAdmin.getPosteTableModel().fireTableDataChanged();
                removeOldArticulos();
                updateMateriales();
                this.setVisible(false);
                this.dispose();
            }
            SiteAdmin.getOpenPostes().remove(this);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String name = jTextField1.getText();
        if (isValidPatron(name)) {
            Patron p = Finders.findPatron(name);
            createArticuloTable(p);
        } else {
            JOptionPane.showMessageDialog(null, "El patrón escrito no es correcto. Intente otra vez.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        int i = jTabbedPane1.getSelectedIndex();
        if (i != 1 || i != 2) {
            try {
                updatePrice();
            } catch (Exception e) {
            }
        }
        if (jTabbedPane1.getSelectedIndex() == 3) {
            createMaterialesEnPosteTable();
            try {
                updatePrice();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
    JComboBox box = (JComboBox) evt.getSource();
    tempAlto = box.getSelectedItem().toString();
    if (tempAlto.equals("")) {
        jFormattedTextField1.setText("------");
    } else {
        try {
            elPoste.setHeight(Integer.parseInt(tempAlto));
        } catch (Exception e) {
        }
        if (elPoste.getPrice() != 0) {
            try {
                Number numberValue = (Number) elPoste.getPrice();
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                String value = formatter.format(numberValue.doubleValue());
                jFormattedTextField1.setText(value);
            } catch (Exception e) {
            }
        }
    }
    if (!jFormattedTextField2.getText().equals("")) {
        updatePrice();
    }
}//GEN-LAST:event_jComboBox1ActionPerformed

private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
    JComboBox box = (JComboBox) evt.getSource();
    tempClase = box.getSelectedItem().toString();
    if (tempClase.equals("")) {
        jFormattedTextField1.setText("------");
    } else {
        if (tempClase.equalsIgnoreCase("OCTAGONAL 33'") || tempClase.equalsIgnoreCase("CUADRADO 33'")) {
            jComboBox1.setSelectedItem(33);
        }
        elPoste.setClase(tempClase);
        if (elPoste.getPrice() != 0) {
            try {
                Number numberValue = (Number) elPoste.getPrice();
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                String value = formatter.format(numberValue.doubleValue());
                jFormattedTextField1.setText(value);
            } catch (Exception e) {
            }
        }
    }
    if (!jFormattedTextField2.getText().equals("")) {
        updatePrice();
    }
}//GEN-LAST:event_jComboBox2ActionPerformed

private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    SiteAdmin.getOpenPostes().remove(this);
}//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new PatronSelect3().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Alto;
    private javax.swing.ButtonGroup Clase;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
