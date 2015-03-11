/*
 * PullboxManhole.java
 *
 * Created on June 16, 2008, 11:06 PM
 */
package segundaFase;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import omarproject.AdminValues;
import omarproject.StartWizard;

/**
 *
 * @author  Luiso
 */
public class PullboxManhole extends javax.swing.JPanel implements Serializable, Printable {

    private double total1;
    private double total2;
    private double total3;
    private double total4;
    private static final long serialVersionUID = 3L;

    /** Creates new form PullboxManhole */
    public PullboxManhole() {
        initComponents();
        this.setSize(new Dimension(687, 548));
    }

    public PullboxManhole(ArrayList<TextValuesComp> list) {
        initComponents();
        this.setSize(new Dimension(687, 548));
        myDeserialize(list);
    }
    
    public void makeEditable() {
        jFormattedTextField10.setEditable(true);
        jFormattedTextField15.setEditable(true);
        jFormattedTextField17.setEditable(true);
        jFormattedTextField19.setEditable(true);
    }

    public void makeUnEditable() {
        jFormattedTextField10.setEditable(false);
        jFormattedTextField15.setEditable(false);
        jFormattedTextField17.setEditable(false);
        jFormattedTextField19.setEditable(false);
        jFormattedTextField10.setBackground(jPanel1.getBackground());
        jFormattedTextField15.setBackground(jPanel1.getBackground());
        jFormattedTextField17.setBackground(jPanel1.getBackground());
        jFormattedTextField19.setBackground(jPanel1.getBackground());
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Graphics2D g2 = (Graphics2D) graphics;
        //  for faster printing, turn off double buffering
        this.setDoubleBuffered(false);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        Dimension d = this.getSize(); //get size of document
        double panelWidth = d.width;//width in pixels
        double panelHeight = d.height; //height in pixels
        double pageHeight = pageFormat.getImageableHeight(); //height of printer page
        double pageWidth = pageFormat.getImageableWidth(); //width of printer page
        double scale = (pageWidth) / panelWidth;
        //  shift Graphic to line up with beginning of print-imageable region
        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY() + 100);
        //  shift Graphic to line up with beginning of next page to print
        //g2.translate(0f, -pageIndex * pageHeight);
        //  scale the page so the width fits...
        g2.scale(scale, scale);
        this.paint(g2); //repaint the page for printing
        this.setDoubleBuffered(true);
        return Printable.PAGE_EXISTS;
    }

    public void myDeserialize(ArrayList<TextValuesComp> list) {
        for (TextValuesComp tvc : list) {
            for (Component c : jPanel2.getComponents()) {
                if (c.getClass() == JFormattedTextField.class) {
                    JFormattedTextField field = (JFormattedTextField) c;
                    if (field.getName().equals(tvc.getName())) {
                        field.setValue(tvc.getValue());
                    }
                }
            }
        }
        for (TextValuesComp tvc : list) {
            for (Component c : jPanel3.getComponents()) {
                if (c.getClass() == JFormattedTextField.class) {
                    JFormattedTextField field = (JFormattedTextField) c;
                    if (field.getName().equals(tvc.getName())) {
                        field.setValue(tvc.getValue());
                    }
                }
            }
        }
        for (TextValuesComp tvc : list) {
            for (Component c : jPanel4.getComponents()) {
                if (c.getClass() == JFormattedTextField.class) {
                    JFormattedTextField field = (JFormattedTextField) c;
                    if (field.getName().equals(tvc.getName())) {
                        field.setValue(tvc.getValue());
                    }
                }
            }
        }
    }

    public ArrayList<TextValuesComp> mySerialize() {
        Component[] original = jPanel2.getComponents();
        ArrayList<TextValuesComp> temp = new ArrayList<TextValuesComp>();
        for (Component c : original) {
            if (c.getClass() == JFormattedTextField.class) {
                JFormattedTextField field = (JFormattedTextField) c;
                if (field.getValue() != null) {
                    temp.add(new TextValuesComp(field.getName(), ((Number) field.getValue()).doubleValue()));
                }
            }
        }
        original = jPanel3.getComponents();
        for (Component c : original) {
            if (c.getClass() == JFormattedTextField.class) {
                JFormattedTextField field = (JFormattedTextField) c;
                if (field.getValue() != null) {
                    temp.add(new TextValuesComp(field.getName(), ((Number) field.getValue()).doubleValue()));
                }
            }
        }
        original = jPanel4.getComponents();
        for (Component c : original) {
            if (c.getClass() == JFormattedTextField.class) {
                JFormattedTextField field = (JFormattedTextField) c;
                if (field.getValue() != null) {
                    temp.add(new TextValuesComp(field.getName(), ((Number) field.getValue()).doubleValue()));
                }
            }
        }
        return temp;
    }

    private double getTotal() {
        double temp1 = 0;
        double temp2 = 0;
        double temp3 = 0;
        try {
            temp1 = ((Number) jFormattedTextField3.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp2 = ((Number) jFormattedTextField6.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp3 = ((Number) jFormattedTextField14.getValue()).doubleValue();
        } catch (Exception e) {
        }
        return temp1 + temp2 + temp3;
    }

    private void fieldsMultiply(JFormattedTextField field1, JFormattedTextField field2, JFormattedTextField field3) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            double result = first * second;
            field3.setValue(result);
        } catch (Exception e) {
        }
    }

    private void rates1Multiply(JFormattedTextField field1, JFormattedTextField field2) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            total1 = first * second;
            calculateTotal1();
        } catch (Exception e) {
        }
    }

    private void rates2Multiply(JFormattedTextField field1, JFormattedTextField field2) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            total2 = first * second;
            calculateTotal1();
        } catch (Exception e) {
        }
    }

    private void rates3Multiply(JFormattedTextField field1, JFormattedTextField field2) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            total3 = first * second;
            calculateTotal1();
        } catch (Exception e) {
        }
    }

    private void rates4Multiply(JFormattedTextField field1, JFormattedTextField field2) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            total4 = first * second;
            calculateTotal1();
        } catch (Exception e) {
        }
    }

    private void calculateTotal1() {
        double result = (total1 + total2 + total3 + total4) * StartWizard.getWorkhours();
        jFormattedTextField13.setValue(result);
    }

    private void fieldsDivide(JFormattedTextField field1, JFormattedTextField field2, JFormattedTextField field3) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            double result = Math.round(first / second);
            field3.setValue(result);
        } catch (Exception e) {
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jFormattedTextField9 = new javax.swing.JFormattedTextField();
        jFormattedTextField10 = new javax.swing.JFormattedTextField();
        jFormattedTextField13 = new javax.swing.JFormattedTextField();
        jFormattedTextField14 = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextField11 = new javax.swing.JFormattedTextField();
        jFormattedTextField12 = new javax.swing.JFormattedTextField();
        jFormattedTextField15 = new javax.swing.JFormattedTextField();
        jFormattedTextField16 = new javax.swing.JFormattedTextField();
        jFormattedTextField17 = new javax.swing.JFormattedTextField();
        jFormattedTextField18 = new javax.swing.JFormattedTextField();
        jFormattedTextField19 = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(687, 548));
        setPreferredSize(new java.awt.Dimension(687, 548));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(687, 548));
        jPanel1.setPreferredSize(new java.awt.Dimension(687, 548));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Instalación de PullBoxes (Incluye: Mano de Obra, Digger, Operador y Diesel) ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel2.setText("Cantidad de PullBoxes (URD-30)");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("$ x PullBox");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Total");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField1.setName("jFormattedTextField1"); // NOI18N
        jFormattedTextField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField1PropertyChange(evt);
            }
        });

        jFormattedTextField2.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField2.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField2.setName("jFormattedTextField2"); // NOI18N
        jFormattedTextField2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField2PropertyChange(evt);
            }
        });

        jFormattedTextField3.setEditable(false);
        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField3.setName("jFormattedTextField3"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField3, 0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))))
                .addGap(66, 66, 66))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel6.setText("Instalación Manhole");

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel7.setText("Cantidad Manhole (10x7x8)");

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("$ x Manhole");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Total");

        jFormattedTextField4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField4.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField4.setName("jFormattedTextField4"); // NOI18N
        jFormattedTextField4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField4PropertyChange(evt);
            }
        });

        jFormattedTextField5.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField5.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField5.setName("jFormattedTextField5"); // NOI18N
        jFormattedTextField5.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField5PropertyChange(evt);
            }
        });

        jFormattedTextField6.setEditable(false);
        jFormattedTextField6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField6.setName("jFormattedTextField6"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(89, 89, 89)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField5, 0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel10.setText("Instalación de Terminaciones (Transformadores)");

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel11.setText("Cantidad de Terminaciones + Pistolas");

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Inst. Diaria");

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Dias de Trabajo");

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel14.setText("Personal Utilizado");

        jLabel15.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("$ x hora");

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("$ x dia");

        jLabel17.setText("Electricistas");

        jFormattedTextField7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField7.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField7.setName("jFormattedTextField7"); // NOI18N
        jFormattedTextField7.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField7PropertyChange(evt);
            }
        });

        jFormattedTextField8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField8.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField8.setName("jFormattedTextField8"); // NOI18N
        jFormattedTextField8.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField8PropertyChange(evt);
            }
        });

        jFormattedTextField9.setEditable(false);
        jFormattedTextField9.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField9.setName("jFormattedTextField9"); // NOI18N
        jFormattedTextField9.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField9PropertyChange(evt);
            }
        });

        jFormattedTextField10.setEditable(false);
        jFormattedTextField10.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField10.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField10.setName("jFormattedTextField10"); // NOI18N
        jFormattedTextField10.setValue(AdminValues.getElectricistaRate());
        jFormattedTextField10.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField10PropertyChange(evt);
            }
        });

        jFormattedTextField13.setEditable(false);
        jFormattedTextField13.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField13.setName("jFormattedTextField13"); // NOI18N
        jFormattedTextField13.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField13PropertyChange(evt);
            }
        });

        jFormattedTextField14.setEditable(false);
        jFormattedTextField14.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField14.setName("jFormattedTextField14"); // NOI18N

        jLabel20.setText("Total");

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Qty.");

        jFormattedTextField11.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField11.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField11.setName("jFormattedTextField11"); // NOI18N
        jFormattedTextField11.setValue(0);
        jFormattedTextField11.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField11PropertyChange(evt);
            }
        });

        jFormattedTextField12.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField12.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField12.setName("jFormattedTextField12"); // NOI18N
        jFormattedTextField12.setValue(0);
        jFormattedTextField12.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField12PropertyChange(evt);
            }
        });

        jFormattedTextField15.setEditable(false);
        jFormattedTextField15.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField15.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField15.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField15.setName("jFormattedTextField15"); // NOI18N
        jFormattedTextField15.setValue(AdminValues.getAyudanteElectRate());
        jFormattedTextField15.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField15PropertyChange(evt);
            }
        });

        jFormattedTextField16.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField16.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField16.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField16.setName("jFormattedTextField16"); // NOI18N
        jFormattedTextField16.setValue(0);
        jFormattedTextField16.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField16PropertyChange(evt);
            }
        });

        jFormattedTextField17.setEditable(false);
        jFormattedTextField17.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField17.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField17.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField17.setName("jFormattedTextField17"); // NOI18N
        jFormattedTextField17.setValue(AdminValues.getLaborRate());
        jFormattedTextField17.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField17PropertyChange(evt);
            }
        });

        jFormattedTextField18.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField18.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField18.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField18.setName("jFormattedTextField18"); // NOI18N
        jFormattedTextField18.setValue(0);
        jFormattedTextField18.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField18PropertyChange(evt);
            }
        });

        jFormattedTextField19.setEditable(false);
        jFormattedTextField19.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField19.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField19.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField19.setName("jFormattedTextField19"); // NOI18N
        jFormattedTextField19.setValue(AdminValues.getForemanRate());
        jFormattedTextField19.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField19PropertyChange(evt);
            }
        });

        jLabel18.setText("Ayudante de Electricistas");

        jLabel19.setText("Labor");

        jLabel21.setText("Foreman");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFormattedTextField7)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(59, 59, 59)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField10, 0, 0, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField15, 0, 0, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField17, 0, 0, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField19, 0, 0, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField13, 0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField9, 0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)))
                .addGap(109, 109, 109))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(478, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel18, jLabel19, jLabel21});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel5))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jFormattedTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jFormattedTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

private void jFormattedTextField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField1PropertyChange
    fieldsMultiply(jFormattedTextField1, jFormattedTextField2, jFormattedTextField3);
}//GEN-LAST:event_jFormattedTextField1PropertyChange

private void jFormattedTextField2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField2PropertyChange
    fieldsMultiply(jFormattedTextField1, jFormattedTextField2, jFormattedTextField3);
}//GEN-LAST:event_jFormattedTextField2PropertyChange

private void jFormattedTextField4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField4PropertyChange
    fieldsMultiply(jFormattedTextField4, jFormattedTextField5, jFormattedTextField6);
}//GEN-LAST:event_jFormattedTextField4PropertyChange

private void jFormattedTextField5PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField5PropertyChange
    fieldsMultiply(jFormattedTextField4, jFormattedTextField5, jFormattedTextField6);
}//GEN-LAST:event_jFormattedTextField5PropertyChange

private void jFormattedTextField9PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField9PropertyChange
    fieldsMultiply(jFormattedTextField9, jFormattedTextField13, jFormattedTextField14);
}//GEN-LAST:event_jFormattedTextField9PropertyChange

private void jFormattedTextField13PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField13PropertyChange
    fieldsMultiply(jFormattedTextField9, jFormattedTextField13, jFormattedTextField14);
}//GEN-LAST:event_jFormattedTextField13PropertyChange

private void jFormattedTextField7PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField7PropertyChange
    fieldsDivide(jFormattedTextField7, jFormattedTextField8, jFormattedTextField9);
}//GEN-LAST:event_jFormattedTextField7PropertyChange

private void jFormattedTextField8PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField8PropertyChange
    fieldsDivide(jFormattedTextField7, jFormattedTextField8, jFormattedTextField9);
}//GEN-LAST:event_jFormattedTextField8PropertyChange

private void jFormattedTextField11PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField11PropertyChange
    rates1Multiply(jFormattedTextField11, jFormattedTextField10);
}//GEN-LAST:event_jFormattedTextField11PropertyChange

private void jFormattedTextField10PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField10PropertyChange
    rates1Multiply(jFormattedTextField11, jFormattedTextField10);
}//GEN-LAST:event_jFormattedTextField10PropertyChange

private void jFormattedTextField12PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField12PropertyChange
    rates2Multiply(jFormattedTextField12, jFormattedTextField15);
}//GEN-LAST:event_jFormattedTextField12PropertyChange

private void jFormattedTextField15PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField15PropertyChange
    rates2Multiply(jFormattedTextField12, jFormattedTextField15);
}//GEN-LAST:event_jFormattedTextField15PropertyChange

private void jFormattedTextField16PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField16PropertyChange
    rates3Multiply(jFormattedTextField16, jFormattedTextField17);
}//GEN-LAST:event_jFormattedTextField16PropertyChange

private void jFormattedTextField17PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField17PropertyChange
    rates3Multiply(jFormattedTextField16, jFormattedTextField17);
}//GEN-LAST:event_jFormattedTextField17PropertyChange

private void jFormattedTextField18PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField18PropertyChange
    rates4Multiply(jFormattedTextField18, jFormattedTextField19);
}//GEN-LAST:event_jFormattedTextField18PropertyChange

private void jFormattedTextField19PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField19PropertyChange
    rates4Multiply(jFormattedTextField18, jFormattedTextField19);
}//GEN-LAST:event_jFormattedTextField19PropertyChange

private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
    SiteAdmin.setManoDeObra(4, getTotal());
}//GEN-LAST:event_formComponentHidden

private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
    jPanel1.requestFocusInWindow();
}//GEN-LAST:event_jPanel2MouseClicked

private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
    jPanel1.requestFocusInWindow();
}//GEN-LAST:event_jPanel3MouseClicked

private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
    jPanel1.requestFocusInWindow();
}//GEN-LAST:event_jPanel4MouseClicked

private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
    jPanel1.requestFocusInWindow();
}//GEN-LAST:event_jPanel1MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField10;
    private javax.swing.JFormattedTextField jFormattedTextField11;
    private javax.swing.JFormattedTextField jFormattedTextField12;
    private javax.swing.JFormattedTextField jFormattedTextField13;
    private javax.swing.JFormattedTextField jFormattedTextField14;
    private javax.swing.JFormattedTextField jFormattedTextField15;
    private javax.swing.JFormattedTextField jFormattedTextField16;
    private javax.swing.JFormattedTextField jFormattedTextField17;
    private javax.swing.JFormattedTextField jFormattedTextField18;
    private javax.swing.JFormattedTextField jFormattedTextField19;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextField5;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JFormattedTextField jFormattedTextField7;
    private javax.swing.JFormattedTextField jFormattedTextField8;
    private javax.swing.JFormattedTextField jFormattedTextField9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
