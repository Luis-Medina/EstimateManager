/*
 * DatabaseEdit.java
 *
 * Created on April 15, 2008, 4:29 AM
 */
package omarproject;

import TableModels.PatronDetailModel;
import TableModels.MaterialesEnManualModel;
import TableModels.MaterialesDeProyectoModel;
import TableModels.PatronesModel;
import TableModels.PostesModel;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import omarproject.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import segundaFase.ColorRenderer;
import segundaFase.TextValuesComp;

/**
 *
 * @author  Luiso
 */
public class DatabaseEdit extends javax.swing.JFrame {

    private String dirName = System.getProperty("user.dir");
    private static MaterialesEnManualModel psm;
    private static MaterialesDeProyectoModel mpm;
    private static PatronesModel patm;
    private static PostesModel postm;
    private static PatronDetailModel pdm;
    private DatFileFilter filter = new DatFileFilter();

    /** Creates new form DatabaseEdit */
    public DatabaseEdit() {
        initComponents();
        fillTables();
        ToolTipManager.sharedInstance().unregisterComponent(jTable1);
        ToolTipManager.sharedInstance().unregisterComponent(jTable2);
        ToolTipManager.sharedInstance().unregisterComponent(jTable3);
        ToolTipManager.sharedInstance().unregisterComponent(jTable4);
        ToolTipManager.sharedInstance().unregisterComponent(jTable5);
        ToolTipManager.sharedInstance().unregisterComponent(jTable1.getTableHeader());
        ToolTipManager.sharedInstance().unregisterComponent(jTable2.getTableHeader());
        ToolTipManager.sharedInstance().unregisterComponent(jTable3.getTableHeader());
        ToolTipManager.sharedInstance().unregisterComponent(jTable4.getTableHeader());
        ToolTipManager.sharedInstance().unregisterComponent(jTable5.getTableHeader());
    }

    public static JTable getTable1() {
        return jTable1;
    }

    public static JTable getTable2() {
        return jTable2;
    }

    public static JTable getTable3() {
        return jTable3;
    }

    public static JTable getTable5() {
        return jTable5;
    }

    public static String currentTimeAndDate() {
        String DATE_FORMAT_NOW = "MMMMM dd, yyyy hh:mm aaa";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        String text = sdf.format(cal.getTime());
        return text;
    }

    public static PatronDetailModel getPatronDetailModel() {
        return pdm;
    }

    private void fillMaterialesEnManualTable() {
        psm = new MaterialesEnManualModel(StartWizard.articulos);
        jTable1.setModel(psm);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(3);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(25);
        CurrencyRenderer cr = new CurrencyRenderer();
        jTable1.setDefaultRenderer(Double.class, cr);
        IntegerRenderer ir = new IntegerRenderer();
        jTable1.setDefaultRenderer(Integer.class, ir);
        jTable1.setDefaultRenderer(Object.class, new ColorRenderer());
    }

    private void fillMaterialesDeProyectoTable() {
        mpm = new MaterialesDeProyectoModel(StartWizard.materiales);
        jTable2.setModel(mpm);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(25);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(300);
        CurrencyRenderer cr = new CurrencyRenderer();
        jTable2.setDefaultRenderer(Double.class, cr);
        jTable2.setDefaultRenderer(Object.class, new ColorRenderer());
    }

    private void fillPatronesNameTable() {
        patm = new PatronesModel(StartWizard.patrones);
        jTable3.setModel(patm);
        jScrollPane4.getViewport().setBackground(Color.WHITE);
        jTable3.setDefaultRenderer(Object.class, new ColorRenderer());
        jTable3.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int viewRow = jTable3.getSelectedRow();
                    if (viewRow != -1) {
                        int modelRow = jTable3.convertRowIndexToModel(viewRow);
                        String name = (String) patm.getValueAt(modelRow, 0);
                        Patron p = Finders.findPatron(name);
                        pdm = new PatronDetailModel(p);
                        jTable4.setModel(pdm);
                        jTable4.setDefaultRenderer(Object.class, new ColorRenderer());
                        jTable4.setDefaultRenderer(Integer.class, new ColorRenderer());
                        jTable4.getColumnModel().getColumn(0).setPreferredWidth(15);
                        jTable4.getColumnModel().getColumn(1).setPreferredWidth(200);
                        jTable4.getColumnModel().getColumn(2).setPreferredWidth(15);
                    }
                }
            }
        });
    }

    private void fillPostesTable() {
        postm = new PostesModel(StartWizard.postes);
        jTable5.setModel(postm);
        jTable5.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable5.getColumnModel().getColumn(1).setPreferredWidth(15);
        jTable5.getColumnModel().getColumn(2).setPreferredWidth(15);
        CurrencyRenderer cr = new CurrencyRenderer();
        jTable5.setDefaultRenderer(Double.class, cr);
        IntegerRenderer ir = new IntegerRenderer();
        jTable5.setDefaultRenderer(Integer.class, ir);
        jTable5.setDefaultRenderer(Object.class, new ColorRenderer());
    }

    private void fillTables() {
        fillMaterialesEnManualTable();
        fillMaterialesDeProyectoTable();
        fillPatronesNameTable();
        fillPostesTable();
    }

    public static MaterialesEnManualModel getArticuloModel() {
        return psm;
    }

    public static MaterialesDeProyectoModel getMaterialesModel() {
        return mpm;
    }

    public static PatronesModel getPatronesModel() {
        return patm;
    }

    public static PostesModel getPostesModel() {
        return postm;
    }

    private boolean aPatronIsSelected() {
        try {
            jTable4.getModel().getValueAt(0, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Base de Datos");
        setLocationByPlatform(true);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new MaterialesEnManualModel(omarproject.StartWizard.articulos));
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Añadir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("Remover");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(477, 477, 477))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                        .addGap(21, 21, 21))))
        );

        jTabbedPane1.addTab("Materiales del manual", jPanel2);

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new MaterialesDeProyectoModel(omarproject.StartWizard.materiales));
        jTable2.setCellSelectionEnabled(true);
        jScrollPane2.setViewportView(jTable2);

        jButton4.setText("Añadir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Remover");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Materiales de proyecto", jPanel3);

        jTable3.setModel(new PatronesModel(omarproject.StartWizard.patrones));
        jTable3.setCellSelectionEnabled(true);
        jScrollPane3.setViewportView(jTable3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Propiedades del patron"));

        jTable4.setAutoCreateRowSorter(true);
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero", "Descripcion", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable4.setCellSelectionEnabled(true);
        jScrollPane4.setViewportView(jTable4);
        jTable4.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(15);
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable4.getColumnModel().getColumn(2).setPreferredWidth(15);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton7.setText("Añadir");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton10.setText("Remover");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton13.setText("+");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("__");
        jButton14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton14.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton13, jButton14});

        jTabbedPane1.addTab("Patrones", jPanel4);

        jTable5.setAutoCreateRowSorter(true);
        jTable5.setModel(new PostesModel(omarproject.StartWizard.postes));
        jTable5.setCellSelectionEnabled(true);
        jScrollPane5.setViewportView(jTable5);

        jButton11.setText("Añadir");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Remover");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Postes", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jButton9.setText("Cancel");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton2.setText("OK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Apply");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("Load");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem2.setText("Backup");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton3, jButton9});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton9)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(dirName + "\\primitiveData.dat"));
            out.writeObject(StartWizard.articulos);
            out.writeObject(StartWizard.materiales);
            out.writeObject(StartWizard.patrones);
            out.writeObject(StartWizard.postes);
            StartWizard.setDatabaseDate(currentTimeAndDate());
            out.writeObject(StartWizard.getDatabaseDate());
            out.writeObject(StartWizard.getAdminValues());
            out.close();
            final SaveCloseWindow scw = new SaveCloseWindow();
            Point p = jButton3.getLocationOnScreen();
            p.translate(-10, -10);
            scw.setLocation(p);
            scw.setVisible(true);
            final DatabaseEdit mine = this;
            ActionListener task = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    scw.dispose();
                    scw.setVisible(false);
                    mine.setVisible(false);
                    mine.dispose();
                }
            };
            Timer timer = new Timer(500, task);
            timer.setInitialDelay(1500);
            timer.setRepeats(false);
            timer.start();
        //JOptionPane.showMessageDialog(new JFrame(),
        // "Se creo el archivo exitosamente.");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        StartWizard.loadPrimitiveData();
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ArticuloAdd aa = new ArticuloAdd();
        aa.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(dirName + "\\primitiveData.dat"));
            out.writeObject(StartWizard.articulos);
            out.writeObject(StartWizard.materiales);
            out.writeObject(StartWizard.patrones);
            out.writeObject(StartWizard.postes);
            StartWizard.setDatabaseDate(currentTimeAndDate());
            out.writeObject(StartWizard.getDatabaseDate());
            out.writeObject(StartWizard.getAdminValues());
            out.close();
            final SaveCloseWindow scw = new SaveCloseWindow();
            Point p = jButton3.getLocationOnScreen();
            p.translate(-10, -10);
            scw.setLocation(p);
            scw.setVisible(true);
            ActionListener task = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    scw.dispose();
                    scw.setVisible(false);
                }
            };
            Timer timer = new Timer(500, task);
            timer.setInitialDelay(1500);
            timer.setRepeats(false);
            timer.start();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    MaterialAdd ma = new MaterialAdd();
    ma.setVisible(true);
}//GEN-LAST:event_jButton4ActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    try {
        int viewRow = jTable1.getSelectedRow();
        if (viewRow != -1) {
            Object[] options = {"Sí", "No"};
            int option = JOptionPane.showOptionDialog(null, "Esta seguro que " +
                    "quiere borrar este artículo de la lista?", "Confirmar",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
            if (option == 0) {
                int modelRow = jTable1.convertRowIndexToModel(viewRow);
                Articulo p = psm.removeArticuloAt(modelRow);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Por favor seleccionar un poste de la lista primero.");
    }
}//GEN-LAST:event_jButton5ActionPerformed

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    try {
        int viewRow = jTable2.getSelectedRow();
        if (viewRow != -1) {
            Object[] options = {"Sí", "No"};
            int option = JOptionPane.showOptionDialog(null, "Esta seguro que " +
                    "quiere borrar este material de la lista?", "Confirmar",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
            if (option == 0) {
                int modelRow = jTable2.convertRowIndexToModel(viewRow);
                mpm.removeMaterialAt(modelRow);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Por favor seleccionar un material de la lista primero.");
    }
}//GEN-LAST:event_jButton6ActionPerformed

private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    PatronAdd pa = new PatronAdd();
    pa.setVisible(true);
}//GEN-LAST:event_jButton7ActionPerformed

private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
    try {
        int viewRow = jTable3.getSelectedRow();
        if (viewRow != -1) {
            Object[] options = {"Sí", "No"};
            int option = JOptionPane.showOptionDialog(null, "Esta seguro que " +
                    "quiere borrar el patron seleccionado de la lista?", "Confirmar",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
            if (option == 0) {
                int modelRow = jTable3.convertRowIndexToModel(viewRow);
                Patron p = patm.removePatronAt(modelRow);
                StartWizard.patrones.remove(p);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Por favor seleccionar un patron de la lista primero.");
    }
}//GEN-LAST:event_jButton10ActionPerformed

private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
    PosteAdd pa = new PosteAdd();
    pa.setVisible(true);
}//GEN-LAST:event_jButton11ActionPerformed

private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
    try {
        int viewRow = jTable5.getSelectedRow();
        if (viewRow != -1) {
            Object[] options = {"Sí", "No"};
            int option = JOptionPane.showOptionDialog(null, "Esta seguro que " +
                    "quiere borrar el poste seleccionado de la lista?", "Confirmar",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
            if (option == 0) {
                int modelRow = jTable5.convertRowIndexToModel(viewRow);
                Poste p = postm.removePosteAt(modelRow);
                StartWizard.postes.remove(p);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Por favor seleccionar un patron de la lista primero.");
    }
}//GEN-LAST:event_jButton12ActionPerformed

private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
    if (aPatronIsSelected()) {
        int viewRow = jTable3.getSelectedRow();
        int modelRow = jTable3.convertRowIndexToModel(viewRow);
        String name = (String) patm.getValueAt(modelRow, 0);
        Patron p = Finders.findPatron(name);
        PatronLineAdd pla = new PatronLineAdd(p.getLines());
        pla.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(null, "No hay un patrón seleccionado!");
    }
}//GEN-LAST:event_jButton13ActionPerformed

private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
    try {
        int viewRow = jTable4.getSelectedRow();
        if (viewRow != -1) {
            Object[] options = {"Sí", "No"};
            int option = JOptionPane.showOptionDialog(null, "Esta seguro que " +
                    "quiere borrar la linea seleccionada de la lista?", "Confirmar",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
            if (option == 0) {
                int modelRow = jTable4.convertRowIndexToModel(viewRow);
                PatronLine p = pdm.removePatronLineAt(modelRow);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay una linea seleccionada!");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Por favor seleccionar una linea de la lista primero.");
    }
}//GEN-LAST:event_jButton14ActionPerformed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    StartWizard.chooser.setFileFilter(filter);
    File selectedFile = null;
    if (StartWizard.chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            ObjectOutputStream out = null;
            try {
                ObjectInputStream in = null;
                try {
                    selectedFile = StartWizard.chooser.getSelectedFile();
                    String filename = selectedFile.getAbsolutePath();
                    in = new ObjectInputStream(new FileInputStream(filename));
                    try {
                        StartWizard.articulos = (ArrayList<Articulo>) in.readObject();
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString());
                    }
                    try {
                        StartWizard.materiales = (ArrayList<Material>) in.readObject();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString());
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString());
                    }
                    try {
                        StartWizard.patrones = (ArrayList<Patron>) in.readObject();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString());
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString());
                    }
                    try {
                        StartWizard.postes = (ArrayList<Poste>) in.readObject();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString());
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString());
                    }
                    try {
                        StartWizard.databaseDate = (String) in.readObject();
                    } catch (IOException ex) {
                        //JOptionPane.showMessageDialog(null, ex.toString());
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString());
                    }
                    try {
                        StartWizard.adminValues = (ArrayList<TextValuesComp>) in.readObject();
                    } catch (IOException ex) {
                        //JOptionPane.showMessageDialog(null, ex.toString());
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, ex.toString());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(DatabaseEdit.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        in.close();
                    } catch (IOException ex) {
                        Logger.getLogger(DatabaseEdit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                out = new ObjectOutputStream(new FileOutputStream(dirName + "\\primitiveData.dat"));
                out.writeObject(StartWizard.articulos);
                out.writeObject(StartWizard.materiales);
                out.writeObject(StartWizard.patrones);
                out.writeObject(StartWizard.postes);
                out.writeObject(StartWizard.getDatabaseDate());
                out.writeObject(StartWizard.getAdminValues());
                out.close();
                mpm.setList(StartWizard.materiales);
                psm.setList(StartWizard.articulos);
                patm.setList(StartWizard.patrones);
                postm.setList(StartWizard.postes);
            } catch (IOException ex) {
                Logger.getLogger(DatabaseEdit.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(DatabaseEdit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    StartWizard.chooser.setFileFilter(filter);
    int returnVal = StartWizard.chooser.showSaveDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        ObjectOutputStream out = null;
            try {
                File selectedFile = StartWizard.chooser.getSelectedFile();
                String filename = selectedFile.getAbsolutePath();
                out = new ObjectOutputStream(new FileOutputStream(filename + ".dat"));
                out.writeObject(StartWizard.articulos);
                out.writeObject(StartWizard.materiales);
                out.writeObject(StartWizard.patrones);
                out.writeObject(StartWizard.postes);
                out.writeObject(StartWizard.getDatabaseDate());
                out.writeObject(StartWizard.getAdminValues());
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(DatabaseEdit.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(DatabaseEdit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
}//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DatabaseEdit().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private static javax.swing.JTabbedPane jTabbedPane1;
    private static javax.swing.JTable jTable1;
    private static javax.swing.JTable jTable2;
    private static javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private static javax.swing.JTable jTable5;
    // End of variables declaration//GEN-END:variables
}
