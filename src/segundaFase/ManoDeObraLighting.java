/*
 * ManoDeObraLighting.java
 *
 * Created on June 16, 2008, 11:03 PM
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
public class ManoDeObraLighting extends javax.swing.JPanel implements Serializable, Printable {

    private double total1;
    private double total2;
    private double total3;
    private double total4;
    private double total5;
    private double total6;
    private double total7;
    private double total8;
    private static final long serialVersionUID = 3L;

    /** Creates new form ManoDeObraLighting */
    public ManoDeObraLighting() {
        initComponents();
        this.setSize(new Dimension(687, 548));
    }

    public ManoDeObraLighting(ArrayList<TextValuesComp> list) {
        initComponents();
        this.setSize(new Dimension(687, 548));
        myDeserialize(list);
    }

    public void makeEditable() {
        jFormattedTextField12.setEditable(true);
        jFormattedTextField13.setEditable(true);
        jFormattedTextField10.setEditable(true);
        jFormattedTextField4.setEditable(true);
        jFormattedTextField5.setEditable(true);
        jFormattedTextField21.setEditable(true);
        jFormattedTextField22.setEditable(true);
        jFormattedTextField11.setEditable(true);
    }

    public void makeUnEditable() {
        jFormattedTextField12.setEditable(false);
        jFormattedTextField13.setEditable(false);
        jFormattedTextField10.setEditable(false);
        jFormattedTextField4.setEditable(false);
        jFormattedTextField5.setEditable(false);
        jFormattedTextField21.setEditable(false);
        jFormattedTextField22.setEditable(false);
        jFormattedTextField11.setEditable(false);
        jFormattedTextField12.setBackground(jPanel1.getBackground());
        jFormattedTextField13.setBackground(jPanel1.getBackground());
        jFormattedTextField10.setBackground(jPanel1.getBackground());
        jFormattedTextField4.setBackground(jPanel1.getBackground());
        jFormattedTextField5.setBackground(jPanel1.getBackground());
        jFormattedTextField21.setBackground(jPanel1.getBackground());
        jFormattedTextField22.setBackground(jPanel1.getBackground());
        jFormattedTextField11.setBackground(jPanel1.getBackground());
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
        return temp;
    }

    private double getTotal() {
        double temp1 = 0;
        double temp2 = 0;
        try {
            temp1 = ((Number) jFormattedTextField8.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp2 = ((Number) jFormattedTextField16.getValue()).doubleValue();
        } catch (Exception e) {
        }
        return temp1 + temp2;
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

    private void rates1Multiply2(JFormattedTextField field1, JFormattedTextField field2) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            total5 = first * second;
            calculateTotal2();
        } catch (Exception e) {
        }
    }

    private void rates2Multiply2(JFormattedTextField field1, JFormattedTextField field2) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            total6 = first * second;
            calculateTotal2();
        } catch (Exception e) {
        }
    }

    private void rates3Multiply2(JFormattedTextField field1, JFormattedTextField field2) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            total7 = first * second;
            calculateTotal2();
        } catch (Exception e) {
        }
    }

    private void rates4Multiply2(JFormattedTextField field1, JFormattedTextField field2) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            total8 = first * second;
            calculateTotal2();
        } catch (Exception e) {
        }
    }

    private void calculateTotal1() {
        double result = (total1 + total2 + total3 + total4) * StartWizard.getWorkhours();
        jFormattedTextField7.setValue(result);
    }

    private void calculateTotal2() {
        double result = (total5 + total6 + total7 + total8) * StartWizard.getWorkhours();
        jFormattedTextField15.setValue(result);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jFormattedTextField17 = new javax.swing.JFormattedTextField();
        jFormattedTextField18 = new javax.swing.JFormattedTextField();
        jFormattedTextField19 = new javax.swing.JFormattedTextField();
        jFormattedTextField20 = new javax.swing.JFormattedTextField();
        jFormattedTextField21 = new javax.swing.JFormattedTextField();
        jFormattedTextField22 = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jFormattedTextField9 = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jFormattedTextField10 = new javax.swing.JFormattedTextField();
        jFormattedTextField11 = new javax.swing.JFormattedTextField();
        jFormattedTextField12 = new javax.swing.JFormattedTextField();
        jFormattedTextField13 = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jFormattedTextField14 = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        jFormattedTextField15 = new javax.swing.JFormattedTextField();
        jFormattedTextField16 = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jFormattedTextField23 = new javax.swing.JFormattedTextField();
        jFormattedTextField24 = new javax.swing.JFormattedTextField();
        jFormattedTextField25 = new javax.swing.JFormattedTextField();
        jFormattedTextField26 = new javax.swing.JFormattedTextField();

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel1.setText("Mano de Obra");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jLabel2.setText("Lighting");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tuberia"));
        jPanel2.setName("Tuberia Primaria"); // NOI18N
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel4.setText("Cantidad de Tuberia (pies lineales)");

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel7.setText("Personal Utilizado");

        jLabel10.setText("Electricistas");

        jLabel11.setText("Ayudante de Electricistas");

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField2.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField2.setName("jFormattedTextField2"); // NOI18N
        jFormattedTextField2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField2PropertyChange(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Inst. diaria");

        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField3.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField3.setName("jFormattedTextField3"); // NOI18N
        jFormattedTextField3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField3PropertyChange(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("$ x hora");

        jFormattedTextField4.setEditable(false);
        jFormattedTextField4.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField4.setName("jFormattedTextField4"); // NOI18N
        jFormattedTextField4.setValue(AdminValues.getElectricistaRate());
        jFormattedTextField4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField4PropertyChange(evt);
            }
        });

        jFormattedTextField5.setEditable(false);
        jFormattedTextField5.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField5.setName("jFormattedTextField5"); // NOI18N
        jFormattedTextField5.setValue(AdminValues.getAyudanteElectRate());
        jFormattedTextField5.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField5PropertyChange(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Dias de trabajo");

        jFormattedTextField6.setEditable(false);
        jFormattedTextField6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField6.setName("jFormattedTextField6"); // NOI18N
        jFormattedTextField6.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField6PropertyChange(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("$ x dia");

        jFormattedTextField7.setEditable(false);
        jFormattedTextField7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField7.setName("jFormattedTextField7"); // NOI18N
        jFormattedTextField7.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField7PropertyChange(evt);
            }
        });

        jFormattedTextField8.setEditable(false);
        jFormattedTextField8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField8.setName("jFormattedTextField8"); // NOI18N

        jLabel12.setText("Total");

        jLabel23.setText("Labor");

        jLabel24.setText("Foreman");

        jLabel25.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Qty.");

        jFormattedTextField17.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField17.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField17.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField17.setName("jFormattedTextField17"); // NOI18N
        jFormattedTextField17.setValue(0);
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

        jFormattedTextField19.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField19.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField19.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField19.setName("jFormattedTextField19"); // NOI18N
        jFormattedTextField19.setValue(0);
        jFormattedTextField19.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField19PropertyChange(evt);
            }
        });

        jFormattedTextField20.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField20.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField20.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField20.setName("jFormattedTextField20"); // NOI18N
        jFormattedTextField20.setValue(0);
        jFormattedTextField20.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField20PropertyChange(evt);
            }
        });

        jFormattedTextField21.setEditable(false);
        jFormattedTextField21.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField21.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField21.setName("jFormattedTextField21"); // NOI18N
        jFormattedTextField21.setValue(AdminValues.getLaborRate());
        jFormattedTextField21.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField21PropertyChange(evt);
            }
        });

        jFormattedTextField22.setEditable(false);
        jFormattedTextField22.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField22.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField22.setName("jFormattedTextField22"); // NOI18N
        jFormattedTextField22.setValue(AdminValues.getForemanRate());
        jFormattedTextField22.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField22PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(34, 34, 34)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jFormattedTextField17, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField18, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField19, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField20, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFormattedTextField5)
                    .addComponent(jFormattedTextField3)
                    .addComponent(jFormattedTextField4)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addComponent(jFormattedTextField21)
                    .addComponent(jFormattedTextField22))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField7)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(198, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(487, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel25))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jFormattedTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jFormattedTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jFormattedTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Alambrado"));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel3.setText("Total de Tramos");

        jLabel15.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel15.setText("Personal Utilizado");

        jLabel16.setText("Electricistas");

        jLabel17.setText("Ayudante de Electricistas");

        jLabel18.setText("Labor");

        jLabel21.setText("Foreman");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField1.setName("jFormattedTextField1"); // NOI18N
        jFormattedTextField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField1PropertyChange(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Inst. diaria");

        jFormattedTextField9.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField9.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField9.setName("jFormattedTextField9"); // NOI18N
        jFormattedTextField9.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField9PropertyChange(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("$ x hora");

        jFormattedTextField10.setEditable(false);
        jFormattedTextField10.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField10.setName("jFormattedTextField10"); // NOI18N
        jFormattedTextField10.setValue(AdminValues.getElectricistaRate());
        jFormattedTextField10.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField10PropertyChange(evt);
            }
        });

        jFormattedTextField11.setEditable(false);
        jFormattedTextField11.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField11.setName("jFormattedTextField11"); // NOI18N
        jFormattedTextField11.setValue(AdminValues.getAyudanteElectRate());
        jFormattedTextField11.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField11PropertyChange(evt);
            }
        });

        jFormattedTextField12.setEditable(false);
        jFormattedTextField12.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField12.setName("jFormattedTextField12"); // NOI18N
        jFormattedTextField12.setValue(AdminValues.getLaborRate());
        jFormattedTextField12.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField12PropertyChange(evt);
            }
        });

        jFormattedTextField13.setEditable(false);
        jFormattedTextField13.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField13.setName("jFormattedTextField13"); // NOI18N
        jFormattedTextField13.setValue(AdminValues.getForemanRate());
        jFormattedTextField13.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField13PropertyChange(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Dias de trabajo");

        jFormattedTextField14.setEditable(false);
        jFormattedTextField14.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField14.setName("jFormattedTextField14"); // NOI18N
        jFormattedTextField14.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField14PropertyChange(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("$ x dia");

        jFormattedTextField15.setEditable(false);
        jFormattedTextField15.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField15.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField15.setName("jFormattedTextField15"); // NOI18N
        jFormattedTextField15.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField15PropertyChange(evt);
            }
        });

        jFormattedTextField16.setEditable(false);
        jFormattedTextField16.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField16.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField16.setName("jFormattedTextField16"); // NOI18N

        jLabel22.setText("Total");

        jLabel26.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Qty.");

        jFormattedTextField23.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField23.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField23.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField23.setName("jFormattedTextField23"); // NOI18N
        jFormattedTextField23.setValue(0);
        jFormattedTextField23.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField23PropertyChange(evt);
            }
        });

        jFormattedTextField24.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField24.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField24.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField24.setName("jFormattedTextField24"); // NOI18N
        jFormattedTextField24.setValue(0);
        jFormattedTextField24.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField24PropertyChange(evt);
            }
        });

        jFormattedTextField25.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField25.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField25.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField25.setName("jFormattedTextField25"); // NOI18N
        jFormattedTextField25.setValue(0);
        jFormattedTextField25.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField25PropertyChange(evt);
            }
        });

        jFormattedTextField26.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField26.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField26.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField26.setName("jFormattedTextField26"); // NOI18N
        jFormattedTextField26.setValue(0);
        jFormattedTextField26.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField26PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField23, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField24, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField25, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField26, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jFormattedTextField13)
                            .addComponent(jFormattedTextField12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField15))
                        .addGap(106, 106, 106))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jFormattedTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel16, jLabel17, jLabel18, jLabel21});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel13)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel20)
                    .addComponent(jLabel26)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jFormattedTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jFormattedTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jFormattedTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jFormattedTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21)
                                            .addComponent(jFormattedTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(jFormattedTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(jFormattedTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jFormattedTextField2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField2PropertyChange
    fieldsDivide(jFormattedTextField2, jFormattedTextField3, jFormattedTextField6);
}//GEN-LAST:event_jFormattedTextField2PropertyChange

private void jFormattedTextField3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField3PropertyChange
    fieldsDivide(jFormattedTextField2, jFormattedTextField3, jFormattedTextField6);
}//GEN-LAST:event_jFormattedTextField3PropertyChange

private void jFormattedTextField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField1PropertyChange
    fieldsDivide(jFormattedTextField1, jFormattedTextField9, jFormattedTextField14);
}//GEN-LAST:event_jFormattedTextField1PropertyChange

private void jFormattedTextField9PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField9PropertyChange
    fieldsDivide(jFormattedTextField1, jFormattedTextField9, jFormattedTextField14);
}//GEN-LAST:event_jFormattedTextField9PropertyChange

private void jFormattedTextField6PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField6PropertyChange
    fieldsMultiply(jFormattedTextField6, jFormattedTextField7, jFormattedTextField8);
}//GEN-LAST:event_jFormattedTextField6PropertyChange

private void jFormattedTextField7PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField7PropertyChange
    fieldsMultiply(jFormattedTextField6, jFormattedTextField7, jFormattedTextField8);
}//GEN-LAST:event_jFormattedTextField7PropertyChange

private void jFormattedTextField14PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField14PropertyChange
    fieldsMultiply(jFormattedTextField14, jFormattedTextField15, jFormattedTextField16);
}//GEN-LAST:event_jFormattedTextField14PropertyChange

private void jFormattedTextField15PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField15PropertyChange
    fieldsMultiply(jFormattedTextField14, jFormattedTextField15, jFormattedTextField16);
}//GEN-LAST:event_jFormattedTextField15PropertyChange

private void jFormattedTextField17PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField17PropertyChange
    rates1Multiply(jFormattedTextField17, jFormattedTextField4);
}//GEN-LAST:event_jFormattedTextField17PropertyChange

private void jFormattedTextField4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField4PropertyChange
    rates1Multiply(jFormattedTextField17, jFormattedTextField4);
}//GEN-LAST:event_jFormattedTextField4PropertyChange

private void jFormattedTextField18PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField18PropertyChange
    rates2Multiply(jFormattedTextField18, jFormattedTextField5);
}//GEN-LAST:event_jFormattedTextField18PropertyChange

private void jFormattedTextField5PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField5PropertyChange
    rates2Multiply(jFormattedTextField18, jFormattedTextField5);
}//GEN-LAST:event_jFormattedTextField5PropertyChange

private void jFormattedTextField19PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField19PropertyChange
    rates3Multiply(jFormattedTextField19, jFormattedTextField21);
}//GEN-LAST:event_jFormattedTextField19PropertyChange

private void jFormattedTextField21PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField21PropertyChange
    rates3Multiply(jFormattedTextField19, jFormattedTextField21);
}//GEN-LAST:event_jFormattedTextField21PropertyChange

private void jFormattedTextField20PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField20PropertyChange
    rates4Multiply(jFormattedTextField20, jFormattedTextField22);
}//GEN-LAST:event_jFormattedTextField20PropertyChange

private void jFormattedTextField22PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField22PropertyChange
    rates4Multiply(jFormattedTextField20, jFormattedTextField22);
}//GEN-LAST:event_jFormattedTextField22PropertyChange

private void jFormattedTextField23PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField23PropertyChange
    rates1Multiply2(jFormattedTextField23, jFormattedTextField10);
}//GEN-LAST:event_jFormattedTextField23PropertyChange

private void jFormattedTextField10PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField10PropertyChange
    rates1Multiply2(jFormattedTextField23, jFormattedTextField10);
}//GEN-LAST:event_jFormattedTextField10PropertyChange

private void jFormattedTextField24PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField24PropertyChange
    rates2Multiply2(jFormattedTextField24, jFormattedTextField11);
}//GEN-LAST:event_jFormattedTextField24PropertyChange

private void jFormattedTextField11PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField11PropertyChange
    rates2Multiply2(jFormattedTextField24, jFormattedTextField11);
}//GEN-LAST:event_jFormattedTextField11PropertyChange

private void jFormattedTextField25PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField25PropertyChange
    rates3Multiply2(jFormattedTextField25, jFormattedTextField12);
}//GEN-LAST:event_jFormattedTextField25PropertyChange

private void jFormattedTextField12PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField12PropertyChange
    rates3Multiply2(jFormattedTextField25, jFormattedTextField12);
}//GEN-LAST:event_jFormattedTextField12PropertyChange

private void jFormattedTextField26PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField26PropertyChange
    rates4Multiply2(jFormattedTextField26, jFormattedTextField13);
}//GEN-LAST:event_jFormattedTextField26PropertyChange

private void jFormattedTextField13PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField13PropertyChange
    rates4Multiply2(jFormattedTextField26, jFormattedTextField13);
}//GEN-LAST:event_jFormattedTextField13PropertyChange

private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
    SiteAdmin.setManoDeObra(2, getTotal());
}//GEN-LAST:event_formComponentHidden

private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
    jPanel1.requestFocusInWindow();
}//GEN-LAST:event_jPanel1MouseClicked

private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
    jPanel1.requestFocusInWindow();
}//GEN-LAST:event_jPanel2MouseClicked

private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
    jPanel1.requestFocusInWindow();
}//GEN-LAST:event_jPanel3MouseClicked
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
    private javax.swing.JFormattedTextField jFormattedTextField20;
    private javax.swing.JFormattedTextField jFormattedTextField21;
    private javax.swing.JFormattedTextField jFormattedTextField22;
    private javax.swing.JFormattedTextField jFormattedTextField23;
    private javax.swing.JFormattedTextField jFormattedTextField24;
    private javax.swing.JFormattedTextField jFormattedTextField25;
    private javax.swing.JFormattedTextField jFormattedTextField26;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
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
    // End of variables declaration//GEN-END:variables
}
