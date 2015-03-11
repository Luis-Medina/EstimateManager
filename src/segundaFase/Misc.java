/*
 * Misc.java
 *
 * Created on June 16, 2008, 11:05 PM
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
public class Misc extends javax.swing.JPanel implements Serializable, Printable {

    private static final long serialVersionUID = 3L;

    /** Creates new form Misc */
    public Misc() {
        initComponents();
        this.setSize(new Dimension(687, 548));
    }

    public Misc(ArrayList<TextValuesComp> list) {
        initComponents();
        this.setSize(new Dimension(687, 548));
        myDeserialize(list);
    }

    public void makeEditable() {
        jFormattedTextField3.setEditable(true);
        jFormattedTextField4.setEditable(true);
        jFormattedTextField20.setEditable(true);
        jFormattedTextField21.setEditable(true);
        jFormattedTextField6.setEditable(true);
        jFormattedTextField7.setEditable(true);
        jFormattedTextField8.setEditable(true);
        jFormattedTextField9.setEditable(true);
    }

    public void makeUnEditable() {
        jFormattedTextField3.setEditable(false);
        jFormattedTextField4.setEditable(false);
        jFormattedTextField20.setEditable(false);
        jFormattedTextField21.setEditable(false);
        jFormattedTextField6.setEditable(false);
        jFormattedTextField7.setEditable(false);
        jFormattedTextField8.setEditable(false);
        jFormattedTextField9.setEditable(false);
        jFormattedTextField3.setBackground(jPanel1.getBackground());
        jFormattedTextField4.setBackground(jPanel1.getBackground());
        jFormattedTextField20.setBackground(jPanel1.getBackground());
        jFormattedTextField21.setBackground(jPanel1.getBackground());
        jFormattedTextField6.setBackground(jPanel1.getBackground());
        jFormattedTextField7.setBackground(jPanel1.getBackground());
        jFormattedTextField8.setBackground(jPanel1.getBackground());
        jFormattedTextField9.setBackground(jPanel1.getBackground());
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
            for (Component c : jPanel1.getComponents()) {
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
        Component[] original = jPanel1.getComponents();
        ArrayList<TextValuesComp> temp = new ArrayList<TextValuesComp>();
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
        double temp5 = 0;
        double temp6 = 0;
        double temp7 = 0;
        double temp8 = 0;
        try {
            temp1 = ((Number) jFormattedTextField22.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp2 = ((Number) jFormattedTextField23.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp3 = ((Number) jFormattedTextField5.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp4 = ((Number) jFormattedTextField17.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp5 = ((Number) jFormattedTextField18.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp6 = ((Number) jFormattedTextField24.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp7 = ((Number) jFormattedTextField19.getValue()).doubleValue();
        } catch (Exception e) {
        }
        try {
            temp8 = ((Number) jFormattedTextField10.getValue()).doubleValue();
        } catch (Exception e) {
        }
        return temp1 + temp2 + temp3 + temp4 + temp5 + temp6 + temp7 + temp8;
    }

    public double getEquipo() {
        try {
            return ((Number) jFormattedTextField10.getValue()).doubleValue();
        } catch (Exception e) {
            return 0;
        }
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jFormattedTextField9 = new javax.swing.JFormattedTextField();
        jFormattedTextField10 = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jFormattedTextField11 = new javax.swing.JFormattedTextField();
        jFormattedTextField12 = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        jFormattedTextField13 = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        jFormattedTextField14 = new javax.swing.JFormattedTextField();
        jFormattedTextField15 = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        jFormattedTextField16 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jFormattedTextField17 = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        jFormattedTextField18 = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jFormattedTextField19 = new javax.swing.JFormattedTextField();
        jLabel25 = new javax.swing.JLabel();
        jFormattedTextField20 = new javax.swing.JFormattedTextField();
        jFormattedTextField21 = new javax.swing.JFormattedTextField();
        jLabel26 = new javax.swing.JLabel();
        jFormattedTextField22 = new javax.swing.JFormattedTextField();
        jFormattedTextField23 = new javax.swing.JFormattedTextField();
        jFormattedTextField24 = new javax.swing.JFormattedTextField();

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
        jLabel1.setText("Supervisión");

        jLabel2.setText("Gerencia");

        jLabel3.setText("Foreman");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16));
        jLabel4.setText("Movilización");

        jLabel5.setText("Transportar y habilitar vagon");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16));
        jLabel6.setText("Gasolina");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16));
        jLabel8.setText("Transportación");

        jLabel9.setText("Gerencia");

        jLabel10.setText("Foreman");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16));
        jLabel11.setText("Alojamientos");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 16));
        jLabel13.setText("Misceláneos");

        jLabel14.setText("Herramientas y equipo");

        jLabel15.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("$ mensual");

        jFormattedTextField3.setEditable(false);
        jFormattedTextField3.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField3.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField3.setName("jFormattedTextField3"); // NOI18N
        jFormattedTextField3.setValue(AdminValues.getGerenciaRate());
        jFormattedTextField3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField3PropertyChange(evt);
            }
        });

        jFormattedTextField4.setEditable(false);
        jFormattedTextField4.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField4.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField4.setName("jFormattedTextField4"); // NOI18N
        jFormattedTextField4.setValue(AdminValues.getForemanRate());
        jFormattedTextField4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField4PropertyChange(evt);
            }
        });

        jFormattedTextField5.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField5.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField5.setName("jFormattedTextField5"); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("$ mensual");

        jFormattedTextField6.setEditable(false);
        jFormattedTextField6.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField6.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField6.setName("jFormattedTextField6"); // NOI18N
        jFormattedTextField6.setValue(AdminValues.getGasolina());
        jFormattedTextField6.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField6PropertyChange(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("$ mensual");

        jFormattedTextField7.setEditable(false);
        jFormattedTextField7.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField7.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField7.setName("jFormattedTextField7"); // NOI18N
        jFormattedTextField7.setValue(AdminValues.getTransGerencia());
        jFormattedTextField7.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField7PropertyChange(evt);
            }
        });

        jFormattedTextField8.setEditable(false);
        jFormattedTextField8.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField8.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField8.setName("jFormattedTextField8"); // NOI18N
        jFormattedTextField8.setValue(AdminValues.getTransForeman());
        jFormattedTextField8.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField8PropertyChange(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("$ mensual");

        jFormattedTextField9.setEditable(false);
        jFormattedTextField9.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField9.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField9.setName("jFormattedTextField9"); // NOI18N
        jFormattedTextField9.setValue(AdminValues.getAlojamientos());
        jFormattedTextField9.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField9PropertyChange(evt);
            }
        });

        jFormattedTextField10.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField10.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField10.setName("jFormattedTextField10"); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Tiempo");

        jFormattedTextField11.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField11.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField11.setName("jFormattedTextField11"); // NOI18N
        jFormattedTextField11.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField11PropertyChange(evt);
            }
        });

        jFormattedTextField12.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField12.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField12.setName("jFormattedTextField12"); // NOI18N
        jFormattedTextField12.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField12PropertyChange(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Tiempo");

        jFormattedTextField13.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField13.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField13.setName("jFormattedTextField13"); // NOI18N
        jFormattedTextField13.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField13PropertyChange(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Tiempo");

        jFormattedTextField14.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField14.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField14.setName("jFormattedTextField14"); // NOI18N
        jFormattedTextField14.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField14PropertyChange(evt);
            }
        });

        jFormattedTextField15.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField15.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField15.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField15.setName("jFormattedTextField15"); // NOI18N
        jFormattedTextField15.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField15PropertyChange(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Tiempo");

        jFormattedTextField16.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField16.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField16.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField16.setName("jFormattedTextField16"); // NOI18N
        jFormattedTextField16.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField16PropertyChange(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Sub-Total");

        jFormattedTextField1.setEditable(false);
        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setName("jFormattedTextField1"); // NOI18N

        jFormattedTextField2.setEditable(false);
        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField2.setName("jFormattedTextField2"); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Total");

        jFormattedTextField17.setEditable(false);
        jFormattedTextField17.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField17.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField17.setName("jFormattedTextField17"); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Total");

        jFormattedTextField18.setEditable(false);
        jFormattedTextField18.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField18.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField18.setName("jFormattedTextField18"); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Total");

        jFormattedTextField19.setEditable(false);
        jFormattedTextField19.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField19.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField19.setName("jFormattedTextField19"); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Multiplicador");

        jFormattedTextField20.setEditable(false);
        jFormattedTextField20.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        jFormattedTextField20.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField20.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField20.setName("jFormattedTextField20"); // NOI18N
        jFormattedTextField20.setValue(AdminValues.getMultiplier());
        jFormattedTextField20.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField20PropertyChange(evt);
            }
        });

        jFormattedTextField21.setEditable(false);
        jFormattedTextField21.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        jFormattedTextField21.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField21.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField21.setName("jFormattedTextField21"); // NOI18N
        jFormattedTextField21.setValue(AdminValues.getMultiplier());
        jFormattedTextField21.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField21PropertyChange(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Total");

        jFormattedTextField22.setEditable(false);
        jFormattedTextField22.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField22.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField22.setName("jFormattedTextField22"); // NOI18N

        jFormattedTextField23.setEditable(false);
        jFormattedTextField23.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField23.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField23.setName("jFormattedTextField23"); // NOI18N

        jFormattedTextField24.setEditable(false);
        jFormattedTextField24.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField24.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField24.setName("jFormattedTextField24"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(jLabel4)
                    .addComponent(jLabel13)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jFormattedTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jFormattedTextField9, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField7, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFormattedTextField8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jFormattedTextField12, 0, 0, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23)
                            .addComponent(jLabel12)
                            .addComponent(jFormattedTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField2))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField20)
                                    .addComponent(jFormattedTextField21))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFormattedTextField23, 0, 0, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField22, 0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jFormattedTextField24, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField18, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel11, jLabel13, jLabel14, jLabel4, jLabel5, jLabel6, jLabel8});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jFormattedTextField6, jFormattedTextField7, jFormattedTextField8, jFormattedTextField9, jLabel15, jLabel16, jLabel17, jLabel18});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jFormattedTextField3, jFormattedTextField4});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jFormattedTextField11, jFormattedTextField12, jFormattedTextField13, jFormattedTextField14, jFormattedTextField15, jFormattedTextField16, jLabel19, jLabel20, jLabel21, jLabel22});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jFormattedTextField17, jFormattedTextField18, jFormattedTextField19, jLabel12, jLabel23, jLabel24});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel15)
                    .addComponent(jLabel19)
                    .addComponent(jLabel7)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel20)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel17)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jFormattedTextField3, jFormattedTextField4, jFormattedTextField5, jFormattedTextField6, jFormattedTextField7, jFormattedTextField8, jFormattedTextField9, jLabel15, jLabel16, jLabel17, jLabel18});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jFormattedTextField11, jFormattedTextField12, jFormattedTextField13, jFormattedTextField14, jFormattedTextField15, jFormattedTextField16, jLabel19, jLabel20, jLabel21, jLabel22});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jFormattedTextField1, jFormattedTextField17, jFormattedTextField18, jFormattedTextField19, jFormattedTextField2, jLabel12, jLabel23, jLabel24, jLabel7});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

private void jFormattedTextField3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField3PropertyChange
    fieldsMultiply(jFormattedTextField3, jFormattedTextField11, jFormattedTextField1);
    fieldsMultiply(jFormattedTextField1, jFormattedTextField20, jFormattedTextField22);
}//GEN-LAST:event_jFormattedTextField3PropertyChange

private void jFormattedTextField11PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField11PropertyChange
    fieldsMultiply(jFormattedTextField3, jFormattedTextField11, jFormattedTextField1);
    fieldsMultiply(jFormattedTextField1, jFormattedTextField20, jFormattedTextField22);
}//GEN-LAST:event_jFormattedTextField11PropertyChange

private void jFormattedTextField20PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField20PropertyChange
    fieldsMultiply(jFormattedTextField3, jFormattedTextField11, jFormattedTextField1);
    fieldsMultiply(jFormattedTextField1, jFormattedTextField20, jFormattedTextField22);
}//GEN-LAST:event_jFormattedTextField20PropertyChange

private void jFormattedTextField4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField4PropertyChange
    fieldsMultiply(jFormattedTextField4, jFormattedTextField12, jFormattedTextField2);
    fieldsMultiply(jFormattedTextField2, jFormattedTextField21, jFormattedTextField23);
}//GEN-LAST:event_jFormattedTextField4PropertyChange

private void jFormattedTextField12PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField12PropertyChange
    fieldsMultiply(jFormattedTextField4, jFormattedTextField12, jFormattedTextField2);
    fieldsMultiply(jFormattedTextField2, jFormattedTextField21, jFormattedTextField23);
}//GEN-LAST:event_jFormattedTextField12PropertyChange

private void jFormattedTextField21PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField21PropertyChange
    fieldsMultiply(jFormattedTextField4, jFormattedTextField12, jFormattedTextField2);
    fieldsMultiply(jFormattedTextField2, jFormattedTextField21, jFormattedTextField23);
}//GEN-LAST:event_jFormattedTextField21PropertyChange

private void jFormattedTextField6PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField6PropertyChange
    fieldsMultiply(jFormattedTextField6, jFormattedTextField13, jFormattedTextField17);
}//GEN-LAST:event_jFormattedTextField6PropertyChange

private void jFormattedTextField13PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField13PropertyChange
    fieldsMultiply(jFormattedTextField6, jFormattedTextField13, jFormattedTextField17);
}//GEN-LAST:event_jFormattedTextField13PropertyChange

private void jFormattedTextField7PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField7PropertyChange
    fieldsMultiply(jFormattedTextField7, jFormattedTextField14, jFormattedTextField18);
}//GEN-LAST:event_jFormattedTextField7PropertyChange

private void jFormattedTextField14PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField14PropertyChange
    fieldsMultiply(jFormattedTextField7, jFormattedTextField14, jFormattedTextField18);
}//GEN-LAST:event_jFormattedTextField14PropertyChange

private void jFormattedTextField8PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField8PropertyChange
    fieldsMultiply(jFormattedTextField8, jFormattedTextField15, jFormattedTextField24);
}//GEN-LAST:event_jFormattedTextField8PropertyChange

private void jFormattedTextField15PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField15PropertyChange
    fieldsMultiply(jFormattedTextField8, jFormattedTextField15, jFormattedTextField24);
}//GEN-LAST:event_jFormattedTextField15PropertyChange

private void jFormattedTextField9PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField9PropertyChange
    fieldsMultiply(jFormattedTextField9, jFormattedTextField16, jFormattedTextField19);
}//GEN-LAST:event_jFormattedTextField9PropertyChange

private void jFormattedTextField16PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField16PropertyChange
    fieldsMultiply(jFormattedTextField9, jFormattedTextField16, jFormattedTextField19);
}//GEN-LAST:event_jFormattedTextField16PropertyChange

private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
    SiteAdmin.setMiscTotal(getTotal());
}//GEN-LAST:event_formComponentHidden

private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
    jPanel1.requestFocusInWindow();
}//GEN-LAST:event_jPanel1MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private static javax.swing.JFormattedTextField jFormattedTextField10;
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
    // End of variables declaration//GEN-END:variables
}
