/*
 * InstalacionPostes.java
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
import omarproject.*;

/**
 *
 * @author  Luiso
 */
public class InstalacionPostes extends javax.swing.JPanel implements Serializable, Printable {

    private static double total1 = 0;
    private static double total2 = 0;
    private static double total3 = 0;
    private static double total4 = 0;
    private static final long serialVersionUID = 3L;

    /** Creates new form InstalacionPostes */
    public InstalacionPostes() {
        initComponents();
        this.setSize(new Dimension(687, 548));
    }

    public InstalacionPostes(ArrayList<TextValuesComp> list) {
        initComponents();
        this.setSize(new Dimension(687, 548));
        myDeserialize(list);
    }

    public void makeEditable() {
        jFormattedTextField15.setEditable(true);
        jFormattedTextField13.setEditable(true);
        jFormattedTextField14.setEditable(true);
        jFormattedTextField18.setEditable(true);
    }

    public void makeUnEditable() {
        jFormattedTextField15.setEditable(false);
        jFormattedTextField13.setEditable(false);
        jFormattedTextField14.setEditable(false);
        jFormattedTextField18.setEditable(false);
        jFormattedTextField15.setBackground(jPanel1.getBackground());
        jFormattedTextField13.setBackground(jPanel1.getBackground());
        jFormattedTextField14.setBackground(jPanel1.getBackground());
        jFormattedTextField18.setBackground(jPanel1.getBackground());
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
        double temp4 = 0;
        try {
            temp1 = ((Number) jFormattedTextField7.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp2 = ((Number) jFormattedTextField8.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp3 = ((Number) jFormattedTextField9.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp4 = ((Number) jFormattedTextField17.getValue()).doubleValue();
        } catch (Exception e) {
        }
        return temp1 + temp2 + temp3 + temp4;
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
            calculateTotal();
        } catch (Exception e) {
        }
    }

    private void rates2Multiply(JFormattedTextField field1, JFormattedTextField field2) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            total2 = first * second;
            calculateTotal();
        } catch (Exception e) {
        }
    }

    private void rates3Multiply(JFormattedTextField field1, JFormattedTextField field2) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            total3 = first * second;
            calculateTotal();
        } catch (Exception e) {
        }
    }

    private void rates4Multiply(JFormattedTextField field1, JFormattedTextField field2) {
        try {
            double first = ((Number) field1.getValue()).doubleValue();
            double second = ((Number) field2.getValue()).doubleValue();
            total4 = first * second;
            calculateTotal();
        } catch (Exception e) {
        }
    }

    private void calculateTotal() {
        double result = (total1 + total2 + total3 + total4) * StartWizard.getWorkhours();
        jFormattedTextField16.setValue(result);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jFormattedTextField9 = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jFormattedTextField10 = new javax.swing.JFormattedTextField();
        jFormattedTextField11 = new javax.swing.JFormattedTextField();
        jFormattedTextField12 = new javax.swing.JFormattedTextField();
        jFormattedTextField13 = new javax.swing.JFormattedTextField();
        jFormattedTextField14 = new javax.swing.JFormattedTextField();
        jFormattedTextField15 = new javax.swing.JFormattedTextField();
        jFormattedTextField16 = new javax.swing.JFormattedTextField();
        jFormattedTextField17 = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jFormattedTextField18 = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        jFormattedTextField19 = new javax.swing.JFormattedTextField();
        jFormattedTextField20 = new javax.swing.JFormattedTextField();
        jFormattedTextField21 = new javax.swing.JFormattedTextField();
        jFormattedTextField22 = new javax.swing.JFormattedTextField();

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16));
        jLabel1.setText("Instalación de Postes (Incluye: Mano de Obra, Digger, Operador y Diesel)");

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel2.setText("Cantidad de Postes");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("$ x postes");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Total");

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel5.setText("Cantidad de Postes");

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("$ x postes");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Total");

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel8.setText("Self-Support");

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("$ x postes");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Total");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField1.setName("jFormattedTextField1"); // NOI18N
        jFormattedTextField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField1PropertyChange(evt);
            }
        });

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField2.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField2.setName("jFormattedTextField2"); // NOI18N
        jFormattedTextField2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField2PropertyChange(evt);
            }
        });

        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField3.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField3.setName("jFormattedTextField3"); // NOI18N
        jFormattedTextField3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField3PropertyChange(evt);
            }
        });

        jFormattedTextField4.setFormatterFactory(StartWizard.getMyFormatter());
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

        jFormattedTextField6.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField6.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField6.setName("jFormattedTextField6"); // NOI18N
        jFormattedTextField6.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField6PropertyChange(evt);
            }
        });

        jFormattedTextField7.setEditable(false);
        jFormattedTextField7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField7.setName("jFormattedTextField7"); // NOI18N

        jFormattedTextField8.setEditable(false);
        jFormattedTextField8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField8.setName("jFormattedTextField8"); // NOI18N

        jFormattedTextField9.setEditable(false);
        jFormattedTextField9.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField9.setName("jFormattedTextField9"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(94, 94, 94)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jLabel9))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))))))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFormattedTextField9)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField8)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                .addGap(178, 178, 178))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Ensamblaje de Postes Lighting"));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel11.setText("Cantidad de Postes");

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel12.setText("Ensamblaje Diario");

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 11));
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

        jLabel18.setText("Ayudante de Electricista");

        jLabel19.setText("Labor");

        jFormattedTextField10.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField10.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField10.setName("jFormattedTextField10"); // NOI18N
        jFormattedTextField10.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField10PropertyChange(evt);
            }
        });

        jFormattedTextField11.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField11.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField11.setName("jFormattedTextField11"); // NOI18N
        jFormattedTextField11.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField11PropertyChange(evt);
            }
        });

        jFormattedTextField12.setEditable(false);
        jFormattedTextField12.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField12.setName("jFormattedTextField12"); // NOI18N
        jFormattedTextField12.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField12PropertyChange(evt);
            }
        });

        jFormattedTextField13.setEditable(false);
        jFormattedTextField13.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField13.setName("jFormattedTextField13"); // NOI18N
        jFormattedTextField13.setValue(AdminValues.getElectricistaRate());
        jFormattedTextField13.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField13PropertyChange(evt);
            }
        });

        jFormattedTextField14.setEditable(false);
        jFormattedTextField14.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField14.setName("jFormattedTextField14"); // NOI18N
        jFormattedTextField14.setValue(AdminValues.getAyudanteElectRate());
        jFormattedTextField14.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField14PropertyChange(evt);
            }
        });

        jFormattedTextField15.setEditable(false);
        jFormattedTextField15.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField15.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField15.setName("jFormattedTextField15"); // NOI18N
        jFormattedTextField15.setValue(AdminValues.getLaborRate());
        jFormattedTextField15.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField15PropertyChange(evt);
            }
        });

        jFormattedTextField16.setEditable(false);
        jFormattedTextField16.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField16.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField16.setName("jFormattedTextField16"); // NOI18N
        jFormattedTextField16.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField16PropertyChange(evt);
            }
        });

        jFormattedTextField17.setEditable(false);
        jFormattedTextField17.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField17.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField17.setName("jFormattedTextField17"); // NOI18N

        jLabel20.setText("Total");

        jLabel21.setText("Foreman");

        jFormattedTextField18.setEditable(false);
        jFormattedTextField18.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField18.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField18.setName("jFormattedTextField18"); // NOI18N
        jFormattedTextField18.setValue(AdminValues.getForemanRate());
        jFormattedTextField18.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField18PropertyChange(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Qty.");

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

        jFormattedTextField22.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField22.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField22.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField22.setName("jFormattedTextField22"); // NOI18N
        jFormattedTextField22.setValue(0);
        jFormattedTextField22.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField22PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField19, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField20, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField21, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField22, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jFormattedTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jFormattedTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField12)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField16))
                        .addGap(144, 144, 144))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jFormattedTextField13, jFormattedTextField14, jFormattedTextField15, jFormattedTextField18, jLabel15});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel22)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jFormattedTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(jFormattedTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel2, jPanel4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

private void jFormattedTextField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField1PropertyChange
    fieldsMultiply(jFormattedTextField1, jFormattedTextField4, jFormattedTextField7);
}//GEN-LAST:event_jFormattedTextField1PropertyChange

private void jFormattedTextField4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField4PropertyChange
    fieldsMultiply(jFormattedTextField1, jFormattedTextField4, jFormattedTextField7);
}//GEN-LAST:event_jFormattedTextField4PropertyChange

private void jFormattedTextField2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField2PropertyChange
    fieldsMultiply(jFormattedTextField2, jFormattedTextField5, jFormattedTextField8);
}//GEN-LAST:event_jFormattedTextField2PropertyChange

private void jFormattedTextField5PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField5PropertyChange
    fieldsMultiply(jFormattedTextField2, jFormattedTextField5, jFormattedTextField8);
}//GEN-LAST:event_jFormattedTextField5PropertyChange

private void jFormattedTextField3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField3PropertyChange
    fieldsMultiply(jFormattedTextField3, jFormattedTextField6, jFormattedTextField9);
}//GEN-LAST:event_jFormattedTextField3PropertyChange

private void jFormattedTextField6PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField6PropertyChange
    fieldsMultiply(jFormattedTextField3, jFormattedTextField6, jFormattedTextField9);
}//GEN-LAST:event_jFormattedTextField6PropertyChange

private void jFormattedTextField10PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField10PropertyChange
    fieldsDivide(jFormattedTextField10, jFormattedTextField11, jFormattedTextField12);
}//GEN-LAST:event_jFormattedTextField10PropertyChange

private void jFormattedTextField11PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField11PropertyChange
    fieldsDivide(jFormattedTextField10, jFormattedTextField11, jFormattedTextField12);
}//GEN-LAST:event_jFormattedTextField11PropertyChange

private void jFormattedTextField12PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField12PropertyChange
    fieldsMultiply(jFormattedTextField12, jFormattedTextField16, jFormattedTextField17);
}//GEN-LAST:event_jFormattedTextField12PropertyChange

private void jFormattedTextField16PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField16PropertyChange
    fieldsMultiply(jFormattedTextField12, jFormattedTextField16, jFormattedTextField17);
}//GEN-LAST:event_jFormattedTextField16PropertyChange

private void jFormattedTextField19PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField19PropertyChange
    rates1Multiply(jFormattedTextField19, jFormattedTextField13);
}//GEN-LAST:event_jFormattedTextField19PropertyChange

private void jFormattedTextField13PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField13PropertyChange
    rates1Multiply(jFormattedTextField19, jFormattedTextField13);
}//GEN-LAST:event_jFormattedTextField13PropertyChange

private void jFormattedTextField20PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField20PropertyChange
    rates2Multiply(jFormattedTextField20, jFormattedTextField14);
}//GEN-LAST:event_jFormattedTextField20PropertyChange

private void jFormattedTextField14PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField14PropertyChange
    rates2Multiply(jFormattedTextField20, jFormattedTextField14);
}//GEN-LAST:event_jFormattedTextField14PropertyChange

private void jFormattedTextField21PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField21PropertyChange
    rates3Multiply(jFormattedTextField21, jFormattedTextField15);
}//GEN-LAST:event_jFormattedTextField21PropertyChange

private void jFormattedTextField15PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField15PropertyChange
    rates3Multiply(jFormattedTextField21, jFormattedTextField15);
}//GEN-LAST:event_jFormattedTextField15PropertyChange

private void jFormattedTextField22PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField22PropertyChange
    rates4Multiply(jFormattedTextField22, jFormattedTextField18);
}//GEN-LAST:event_jFormattedTextField22PropertyChange

private void jFormattedTextField18PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField18PropertyChange
    rates4Multiply(jFormattedTextField22, jFormattedTextField18);
}//GEN-LAST:event_jFormattedTextField18PropertyChange

private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
    SiteAdmin.setManoDeObra(5, getTotal());
}//GEN-LAST:event_formComponentHidden

private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
    jPanel1.requestFocusInWindow();
}//GEN-LAST:event_jPanel2MouseClicked

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
    private javax.swing.JFormattedTextField jFormattedTextField20;
    private javax.swing.JFormattedTextField jFormattedTextField21;
    private javax.swing.JFormattedTextField jFormattedTextField22;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
