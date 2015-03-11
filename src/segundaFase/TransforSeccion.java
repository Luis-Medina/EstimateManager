/*
 * TransforSeccion.java
 *
 * Created on June 16, 2008, 11:13 PM
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
public class TransforSeccion extends javax.swing.JPanel implements Serializable, Printable {

    private double total1;
    private double total2;
    private double total3;
    private double total4;
    private double total5;
    private double total6;
    private double total7;
    private double total8;
    private static final long serialVersionUID = 3L;

    /** Creates new form TransforSeccion */
    public TransforSeccion() {
        initComponents();
        this.setSize(new Dimension(687, 548));
    }

    public TransforSeccion(ArrayList<TextValuesComp> list) {
        initComponents();
        this.setSize(new Dimension(687, 548));
        myDeserialize(list);
    }

    public void makeEditable() {
        jFormattedTextField9.setEditable(true);
        jFormattedTextField11.setEditable(true);
        jFormattedTextField12.setEditable(true);
        jFormattedTextField13.setEditable(true);
        jFormattedTextField22.setEditable(true);
        jFormattedTextField23.setEditable(true);
        jFormattedTextField24.setEditable(true);
        jFormattedTextField25.setEditable(true);
    }

    public void makeUnEditable() {
        jFormattedTextField9.setEditable(false);
        jFormattedTextField11.setEditable(false);
        jFormattedTextField12.setEditable(false);
        jFormattedTextField13.setEditable(false);
        jFormattedTextField22.setEditable(false);
        jFormattedTextField23.setEditable(false);
        jFormattedTextField24.setEditable(false);
        jFormattedTextField25.setEditable(false);
        jFormattedTextField9.setBackground(jPanel1.getBackground());
        jFormattedTextField11.setBackground(jPanel1.getBackground());
        jFormattedTextField12.setBackground(jPanel1.getBackground());
        jFormattedTextField13.setBackground(jPanel1.getBackground());
        jFormattedTextField22.setBackground(jPanel1.getBackground());
        jFormattedTextField23.setBackground(jPanel1.getBackground());
        jFormattedTextField24.setBackground(jPanel1.getBackground());
        jFormattedTextField25.setBackground(jPanel1.getBackground());
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
        try {
            temp1 = ((Number) jFormattedTextField10.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp2 = ((Number) jFormattedTextField27.getValue()).doubleValue();
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
        jFormattedTextField28.setValue(result);
    }

    private void calculateTotal2() {
        double result = (total5 + total6 + total7 + total8) * StartWizard.getWorkhours();
        jFormattedTextField26.setValue(result);
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

    private void fieldsAdd(JFormattedTextField field1, JFormattedTextField field2, JFormattedTextField field3, JFormattedTextField field4, JFormattedTextField field5) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            double third = ((Number) field3.getValue()).doubleValue();
            double result = first + second + third;
            field4.setValue(result);
            field5.setValue(result);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jFormattedTextField9 = new javax.swing.JFormattedTextField();
        jFormattedTextField10 = new javax.swing.JFormattedTextField();
        jFormattedTextField11 = new javax.swing.JFormattedTextField();
        jFormattedTextField12 = new javax.swing.JFormattedTextField();
        jFormattedTextField13 = new javax.swing.JFormattedTextField();
        jFormattedTextField14 = new javax.swing.JFormattedTextField();
        jLabel27 = new javax.swing.JLabel();
        jFormattedTextField28 = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jFormattedTextField15 = new javax.swing.JFormattedTextField();
        jFormattedTextField16 = new javax.swing.JFormattedTextField();
        jFormattedTextField17 = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jFormattedTextField18 = new javax.swing.JFormattedTextField();
        jFormattedTextField19 = new javax.swing.JFormattedTextField();
        jFormattedTextField20 = new javax.swing.JFormattedTextField();
        jFormattedTextField21 = new javax.swing.JFormattedTextField();
        jFormattedTextField22 = new javax.swing.JFormattedTextField();
        jFormattedTextField23 = new javax.swing.JFormattedTextField();
        jFormattedTextField24 = new javax.swing.JFormattedTextField();
        jFormattedTextField25 = new javax.swing.JFormattedTextField();
        jFormattedTextField26 = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jFormattedTextField27 = new javax.swing.JFormattedTextField();
        jLabel26 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(687, 548));
        setPreferredSize(new java.awt.Dimension(687, 548));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(687, 548));
        jPanel1.setPreferredSize(new java.awt.Dimension(687, 548));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel1.setText("Transormadores, Seccionadoras y Transclousures Pad Mounted ");

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setText("Total de Transformadores");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField1.setName("jFormattedTextField1"); // NOI18N
        jFormattedTextField1.setValue(0);
        jFormattedTextField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField1PropertyChange(evt);
            }
        });

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField2.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField2.setName("jFormattedTextField2"); // NOI18N
        jFormattedTextField2.setValue(0);
        jFormattedTextField2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField2PropertyChange(evt);
            }
        });

        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField3.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField3.setName("jFormattedTextField3"); // NOI18N
        jFormattedTextField3.setValue(0);
        jFormattedTextField3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField3PropertyChange(evt);
            }
        });

        jLabel3.setText("Total de Seccionadoras");

        jLabel4.setText("Total de Transclosures");

        jFormattedTextField4.setEditable(false);
        jFormattedTextField4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField4.setName("jFormattedTextField4"); // NOI18N

        jLabel18.setText("Total");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(291, 291, 291)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jFormattedTextField1, jFormattedTextField2, jFormattedTextField3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Instalación de Transformadores"));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel6.setText("Personal Utilizado");

        jLabel7.setText("Electricista");

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Qty.");

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("$ x hora");

        jLabel5.setText("Total");

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Dias de Trabajo");

        jLabel21.setText("Ayudante de Electricistas");

        jLabel22.setText("Labor");

        jLabel23.setText("Foreman");

        jFormattedTextField5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField5.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField5.setName("jFormattedTextField5"); // NOI18N
        jFormattedTextField5.setValue(0);
        jFormattedTextField5.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField5PropertyChange(evt);
            }
        });

        jFormattedTextField6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField6.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField6.setName("jFormattedTextField6"); // NOI18N
        jFormattedTextField6.setValue(0);
        jFormattedTextField6.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField6PropertyChange(evt);
            }
        });

        jFormattedTextField7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField7.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField7.setName("jFormattedTextField7"); // NOI18N
        jFormattedTextField7.setValue(0);
        jFormattedTextField7.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField7PropertyChange(evt);
            }
        });

        jFormattedTextField8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField8.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField8.setName("jFormattedTextField8"); // NOI18N
        jFormattedTextField8.setValue(0);
        jFormattedTextField8.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField8PropertyChange(evt);
            }
        });

        jFormattedTextField9.setEditable(false);
        jFormattedTextField9.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField9.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField9.setName("jFormattedTextField9"); // NOI18N
        jFormattedTextField9.setValue(AdminValues.getElectricistaRate());
        jFormattedTextField9.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField9PropertyChange(evt);
            }
        });

        jFormattedTextField10.setEditable(false);
        jFormattedTextField10.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField10.setName("jFormattedTextField10"); // NOI18N

        jFormattedTextField11.setEditable(false);
        jFormattedTextField11.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField11.setInputVerifier(StartWizard.getVerifier());
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
        jFormattedTextField12.setInputVerifier(StartWizard.getVerifier());
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
        jFormattedTextField13.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField13.setName("jFormattedTextField13"); // NOI18N
        jFormattedTextField13.setValue(AdminValues.getForemanRate());
        jFormattedTextField13.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField13PropertyChange(evt);
            }
        });

        jFormattedTextField14.setEditable(false);
        jFormattedTextField14.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField14.setName("jFormattedTextField14"); // NOI18N
        jFormattedTextField14.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField14PropertyChange(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("$ x dia");

        jFormattedTextField28.setEditable(false);
        jFormattedTextField28.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField28.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField28.setName("jFormattedTextField28"); // NOI18N
        jFormattedTextField28.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField28PropertyChange(evt);
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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(49, 49, 49))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel22)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel8))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField14)
                                    .addComponent(jFormattedTextField28, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jFormattedTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedestales"));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Cantidad de Pedestales");

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Ped. por dia");

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Dias de Trabajo");

        jFormattedTextField15.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField15.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField15.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField15.setName("jFormattedTextField15"); // NOI18N
        jFormattedTextField15.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField15PropertyChange(evt);
            }
        });

        jFormattedTextField16.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField16.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField16.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField16.setName("jFormattedTextField16"); // NOI18N
        jFormattedTextField16.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField16PropertyChange(evt);
            }
        });

        jFormattedTextField17.setEditable(false);
        jFormattedTextField17.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField17.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField17.setName("jFormattedTextField17"); // NOI18N
        jFormattedTextField17.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField17PropertyChange(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Personal Utilizado");

        jLabel15.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Qty.");

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("$ x hora");

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("$ x dia");

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

        jFormattedTextField21.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField21.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField21.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField21.setName("jFormattedTextField21"); // NOI18N
        jFormattedTextField21.setValue(0);
        jFormattedTextField21.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField21PropertyChange(evt);
            }
        });

        jFormattedTextField22.setEditable(false);
        jFormattedTextField22.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField22.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField22.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField22.setName("jFormattedTextField22"); // NOI18N
        jFormattedTextField22.setValue(AdminValues.getElectricistaRate());
        jFormattedTextField22.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField22PropertyChange(evt);
            }
        });

        jFormattedTextField23.setEditable(false);
        jFormattedTextField23.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField23.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField23.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField23.setName("jFormattedTextField23"); // NOI18N
        jFormattedTextField23.setValue(AdminValues.getAyudanteElectRate());
        jFormattedTextField23.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField23PropertyChange(evt);
            }
        });

        jFormattedTextField24.setEditable(false);
        jFormattedTextField24.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField24.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField24.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField24.setName("jFormattedTextField24"); // NOI18N
        jFormattedTextField24.setValue(AdminValues.getLaborRate());
        jFormattedTextField24.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField24PropertyChange(evt);
            }
        });

        jFormattedTextField25.setEditable(false);
        jFormattedTextField25.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField25.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField25.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField25.setName("jFormattedTextField25"); // NOI18N
        jFormattedTextField25.setValue(AdminValues.getForemanRate());
        jFormattedTextField25.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField25PropertyChange(evt);
            }
        });

        jFormattedTextField26.setEditable(false);
        jFormattedTextField26.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField26.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField26.setName("jFormattedTextField26"); // NOI18N
        jFormattedTextField26.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField26PropertyChange(evt);
            }
        });

        jLabel19.setText("Electricistas");

        jLabel20.setText("Ayudante de Electricistas");

        jLabel24.setText("Labor");

        jLabel25.setText("Foreman");

        jFormattedTextField27.setEditable(false);
        jFormattedTextField27.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField27.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField27.setName("jFormattedTextField27"); // NOI18N

        jLabel26.setText("Total");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField21)
                            .addComponent(jFormattedTextField20)
                            .addComponent(jFormattedTextField19)
                            .addComponent(jFormattedTextField18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jFormattedTextField16, 0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField23, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField24, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField25, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField26, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jFormattedTextField17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)))
                        .addGap(138, 138, 138))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel14, jLabel19, jLabel20, jLabel24, jLabel25});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel2, jPanel3, jPanel4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jFormattedTextField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField1PropertyChange
    fieldsAdd(jFormattedTextField1, jFormattedTextField2, jFormattedTextField3, jFormattedTextField4, jFormattedTextField14);
}//GEN-LAST:event_jFormattedTextField1PropertyChange

private void jFormattedTextField2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField2PropertyChange
    fieldsAdd(jFormattedTextField1, jFormattedTextField2, jFormattedTextField3, jFormattedTextField4, jFormattedTextField14);
}//GEN-LAST:event_jFormattedTextField2PropertyChange

private void jFormattedTextField3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField3PropertyChange
    fieldsAdd(jFormattedTextField1, jFormattedTextField2, jFormattedTextField3, jFormattedTextField4, jFormattedTextField14);
}//GEN-LAST:event_jFormattedTextField3PropertyChange

private void jFormattedTextField14PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField14PropertyChange
    fieldsMultiply(jFormattedTextField14, jFormattedTextField28, jFormattedTextField10);
}//GEN-LAST:event_jFormattedTextField14PropertyChange

private void jFormattedTextField28PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField28PropertyChange
    fieldsMultiply(jFormattedTextField14, jFormattedTextField28, jFormattedTextField10);
}//GEN-LAST:event_jFormattedTextField28PropertyChange

private void jFormattedTextField17PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField17PropertyChange
    fieldsMultiply(jFormattedTextField17, jFormattedTextField26, jFormattedTextField27);
}//GEN-LAST:event_jFormattedTextField17PropertyChange

private void jFormattedTextField26PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField26PropertyChange
    fieldsMultiply(jFormattedTextField17, jFormattedTextField26, jFormattedTextField27);
}//GEN-LAST:event_jFormattedTextField26PropertyChange

private void jFormattedTextField15PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField15PropertyChange
    fieldsDivide(jFormattedTextField15, jFormattedTextField16, jFormattedTextField17);
}//GEN-LAST:event_jFormattedTextField15PropertyChange

private void jFormattedTextField16PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField16PropertyChange
    fieldsDivide(jFormattedTextField15, jFormattedTextField16, jFormattedTextField17);
}//GEN-LAST:event_jFormattedTextField16PropertyChange

private void jFormattedTextField5PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField5PropertyChange
    rates1Multiply(jFormattedTextField5, jFormattedTextField9);
}//GEN-LAST:event_jFormattedTextField5PropertyChange

private void jFormattedTextField9PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField9PropertyChange
    rates1Multiply(jFormattedTextField5, jFormattedTextField9);
}//GEN-LAST:event_jFormattedTextField9PropertyChange

private void jFormattedTextField6PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField6PropertyChange
    rates2Multiply(jFormattedTextField6, jFormattedTextField11);
}//GEN-LAST:event_jFormattedTextField6PropertyChange

private void jFormattedTextField11PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField11PropertyChange
    rates2Multiply(jFormattedTextField6, jFormattedTextField11);
}//GEN-LAST:event_jFormattedTextField11PropertyChange

private void jFormattedTextField7PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField7PropertyChange
    rates3Multiply(jFormattedTextField7, jFormattedTextField12);
}//GEN-LAST:event_jFormattedTextField7PropertyChange

private void jFormattedTextField12PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField12PropertyChange
    rates3Multiply(jFormattedTextField7, jFormattedTextField12);
}//GEN-LAST:event_jFormattedTextField12PropertyChange

private void jFormattedTextField8PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField8PropertyChange
    rates4Multiply(jFormattedTextField8, jFormattedTextField13);
}//GEN-LAST:event_jFormattedTextField8PropertyChange

private void jFormattedTextField13PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField13PropertyChange
    rates4Multiply(jFormattedTextField8, jFormattedTextField13);
}//GEN-LAST:event_jFormattedTextField13PropertyChange

private void jFormattedTextField18PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField18PropertyChange
    rates1Multiply2(jFormattedTextField18, jFormattedTextField22);
}//GEN-LAST:event_jFormattedTextField18PropertyChange

private void jFormattedTextField22PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField22PropertyChange
    rates1Multiply2(jFormattedTextField18, jFormattedTextField22);
}//GEN-LAST:event_jFormattedTextField22PropertyChange

private void jFormattedTextField19PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField19PropertyChange
    rates2Multiply2(jFormattedTextField19, jFormattedTextField23);
}//GEN-LAST:event_jFormattedTextField19PropertyChange

private void jFormattedTextField23PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField23PropertyChange
    rates2Multiply2(jFormattedTextField19, jFormattedTextField23);
}//GEN-LAST:event_jFormattedTextField23PropertyChange

private void jFormattedTextField20PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField20PropertyChange
    rates3Multiply2(jFormattedTextField20, jFormattedTextField24);
}//GEN-LAST:event_jFormattedTextField20PropertyChange

private void jFormattedTextField24PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField24PropertyChange
    rates3Multiply2(jFormattedTextField20, jFormattedTextField24);
}//GEN-LAST:event_jFormattedTextField24PropertyChange

private void jFormattedTextField21PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField21PropertyChange
    rates4Multiply2(jFormattedTextField21, jFormattedTextField25);
}//GEN-LAST:event_jFormattedTextField21PropertyChange

private void jFormattedTextField25PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField25PropertyChange
    rates4Multiply2(jFormattedTextField21, jFormattedTextField25);
}//GEN-LAST:event_jFormattedTextField25PropertyChange

private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
    SiteAdmin.setManoDeObra(3, getTotal());
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

private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    jPanel1.requestFocusInWindow();
}//GEN-LAST:event_formMouseClicked
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
    private javax.swing.JFormattedTextField jFormattedTextField27;
    private javax.swing.JFormattedTextField jFormattedTextField28;
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
    private javax.swing.JLabel jLabel27;
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
