/*
 * CableTV.java
 *
 * Created on June 16, 2008, 10:30 PM
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
public class CableTV extends javax.swing.JPanel implements Serializable, Printable {

    private double elect1SubTotal = 0;
    private double ayuda1SubTotal = 0;
    private double labor1SubTotal = 0;
    private double foreman1SubTotal = 0;
    private double elect2SubTotal = 0;
    private double ayuda2SubTotal = 0;
    private double labor2SubTotal = 0;
    private double foreman2SubTotal = 0;
    private double temp1;
    private double temp2;
    private static final long serialVersionUID = 3L;

    /** Creates new form CableTV */
    public CableTV() {
        initComponents();
        this.setSize(new Dimension(687, 548));
    }
    
    public CableTV(ArrayList<TextValuesComp> list) {
        initComponents();
        this.setSize(new Dimension(687, 548));
        myDeserialize(list);
    }
    
    public void makeEditable(){
        jFormattedTextField12.setEditable(true);
        jFormattedTextField13.setEditable(true);
        jFormattedTextField14.setEditable(true);
        jFormattedTextField4.setEditable(true);
        jFormattedTextField5.setEditable(true);
        jFormattedTextField6.setEditable(true);
        jFormattedTextField17.setEditable(true);
        jFormattedTextField18.setEditable(true);        
    }
    
    public void makeUnEditable(){
        jFormattedTextField12.setEditable(false);
        jFormattedTextField13.setEditable(false);
        jFormattedTextField14.setEditable(false);
        jFormattedTextField4.setEditable(false);
        jFormattedTextField5.setEditable(false);
        jFormattedTextField6.setEditable(false);
        jFormattedTextField17.setEditable(false);
        jFormattedTextField18.setEditable(false);
        jFormattedTextField12.setBackground(jPanel1.getBackground());
        jFormattedTextField13.setBackground(jPanel1.getBackground());
        jFormattedTextField14.setBackground(jPanel1.getBackground());
        jFormattedTextField4.setBackground(jPanel1.getBackground());
        jFormattedTextField5.setBackground(jPanel1.getBackground());
        jFormattedTextField6.setBackground(jPanel1.getBackground());
        jFormattedTextField17.setBackground(jPanel1.getBackground());
        jFormattedTextField18.setBackground(jPanel1.getBackground());
    }
    
    @Override
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
    
    public double getTotal() {
        temp1 = 0;
        temp2 = 0;
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

    private void setFirstSubTotal() {
        double result = elect1SubTotal + ayuda1SubTotal + labor1SubTotal + foreman1SubTotal;
        result *= StartWizard.getWorkhours();
        jFormattedTextField7.setValue(result);
    }

    private void setSecondSubTotal() {
        double result = elect2SubTotal + ayuda2SubTotal + labor2SubTotal + foreman2SubTotal;
        result *= StartWizard.getWorkhours();
        jFormattedTextField15.setValue(result);
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
        jLabel11 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        jFormattedTextField17 = new javax.swing.JFormattedTextField();
        jLabel25 = new javax.swing.JLabel();
        jFormattedTextField23 = new javax.swing.JFormattedTextField();
        jFormattedTextField24 = new javax.swing.JFormattedTextField();
        jFormattedTextField25 = new javax.swing.JFormattedTextField();
        jFormattedTextField26 = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jFormattedTextField9 = new javax.swing.JFormattedTextField();
        jFormattedTextField10 = new javax.swing.JFormattedTextField();
        jFormattedTextField11 = new javax.swing.JFormattedTextField();
        jFormattedTextField12 = new javax.swing.JFormattedTextField();
        jFormattedTextField13 = new javax.swing.JFormattedTextField();
        jFormattedTextField14 = new javax.swing.JFormattedTextField();
        jFormattedTextField15 = new javax.swing.JFormattedTextField();
        jFormattedTextField16 = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        jFormattedTextField19 = new javax.swing.JFormattedTextField();
        jFormattedTextField20 = new javax.swing.JFormattedTextField();
        jFormattedTextField21 = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jFormattedTextField22 = new javax.swing.JFormattedTextField();
        jFormattedTextField18 = new javax.swing.JFormattedTextField();

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
        jLabel1.setText("Mano de Obra Cable TV y Teléfono");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tuberia"));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cantidad de Tuberia (pies lineales)");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Inst. Tub. Diaria");

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Dias de Trabajo");

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Personal Utilizado");

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("$ x hora");

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("$ x dia");

        jLabel8.setText("Electricistas");

        jLabel9.setText("Ayudante de Electricistas");

        jLabel10.setText("Labor");

        jLabel11.setText("Total");

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

        jFormattedTextField3.setEditable(false);
        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField3.setName("jFormattedTextField3"); // NOI18N
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
        jFormattedTextField4.setValue(AdminValues.getElectricistaRate());
        jFormattedTextField4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField4PropertyChange(evt);
            }
        });

        jFormattedTextField5.setEditable(false);
        jFormattedTextField5.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField5.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField5.setName("jFormattedTextField5"); // NOI18N
        jFormattedTextField5.setValue(AdminValues.getAyudanteElectRate());

        jFormattedTextField6.setEditable(false);
        jFormattedTextField6.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField6.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField6.setName("jFormattedTextField6"); // NOI18N
        jFormattedTextField6.setValue(AdminValues.getLaborRate());

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

        jLabel23.setText("Foreman");

        jFormattedTextField17.setEditable(false);
        jFormattedTextField17.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField17.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField17.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField17.setName("jFormattedTextField17"); // NOI18N
        jFormattedTextField17.setValue(AdminValues.getForemanRate());

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Qty.");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField26, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField25, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField24, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jFormattedTextField17, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jFormattedTextField5)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jFormattedTextField2)
                        .addComponent(jFormattedTextField4)
                        .addComponent(jFormattedTextField6)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField3)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField7))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jFormattedTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jFormattedTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Alambrado"));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Cantidad de Casas y Pedestales");

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Inst. Diaria");

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Dias de Trabajo");

        jLabel15.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Personal Utilizado");

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("$ x hora");

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 11));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("$ x dia");

        jLabel18.setText("Electricistas");

        jLabel19.setText("Ayudante de Electricistas");

        jLabel20.setText("Labor");

        jLabel21.setText("Total");

        jFormattedTextField9.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField9.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField9.setName("jFormattedTextField9"); // NOI18N
        jFormattedTextField9.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField9PropertyChange(evt);
            }
        });

        jFormattedTextField10.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField10.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField10.setName("jFormattedTextField10"); // NOI18N
        jFormattedTextField10.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField10PropertyChange(evt);
            }
        });

        jFormattedTextField11.setEditable(false);
        jFormattedTextField11.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField11.setName("jFormattedTextField11"); // NOI18N
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
        jFormattedTextField12.setValue(AdminValues.getElectricistaRate());
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
        jFormattedTextField13.setValue(AdminValues.getAyudanteElectRate());
        jFormattedTextField13.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField13PropertyChange(evt);
            }
        });

        jFormattedTextField14.setEditable(false);
        jFormattedTextField14.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField14.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField14.setName("jFormattedTextField14"); // NOI18N
        jFormattedTextField14.setValue(AdminValues.getLaborRate());
        jFormattedTextField14.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField14PropertyChange(evt);
            }
        });

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

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11));
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
        jFormattedTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField21ActionPerformed(evt);
            }
        });
        jFormattedTextField21.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField21PropertyChange(evt);
            }
        });

        jLabel24.setText("Foreman");

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

        jFormattedTextField18.setEditable(false);
        jFormattedTextField18.setFormatterFactory(StartWizard.getMyFormatter());
        jFormattedTextField18.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField18.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField18.setName("jFormattedTextField18"); // NOI18N
        jFormattedTextField18.setValue(AdminValues.getForemanRate());
        jFormattedTextField18.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField18PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField21, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField20, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField19, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField22, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel24))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFormattedTextField18, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField16, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField11)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField15))))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jFormattedTextField19, jFormattedTextField20, jFormattedTextField21});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jFormattedTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jFormattedTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jFormattedTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jFormattedTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(176, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jFormattedTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
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
    try {
        double first = ((Number) jFormattedTextField1.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField2.getValue()).doubleValue();
        double result = Math.round(first / second);
        jFormattedTextField3.setValue(result);
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField1PropertyChange

private void jFormattedTextField2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField2PropertyChange
    try {
        double first = ((Number) jFormattedTextField1.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField2.getValue()).doubleValue();
        double result = Math.round(first / second);
        jFormattedTextField3.setValue(result);
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField2PropertyChange

private void jFormattedTextField9PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField9PropertyChange
    try {
        double first = ((Number) jFormattedTextField9.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField10.getValue()).doubleValue();
        double result = Math.round(first / second);
        jFormattedTextField11.setValue(result);
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField9PropertyChange

private void jFormattedTextField10PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField10PropertyChange
    try {
        double first = ((Number) jFormattedTextField9.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField10.getValue()).doubleValue();
        double result = Math.round(first / second);
        jFormattedTextField11.setValue(result);
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField10PropertyChange

private void jFormattedTextField23PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField23PropertyChange
    try {
        double first = ((Number) jFormattedTextField23.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField4.getValue()).doubleValue();
        double result = first * second;
        elect1SubTotal = result;
        setFirstSubTotal();
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField23PropertyChange

private void jFormattedTextField24PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField24PropertyChange
    try {
        double first = ((Number) jFormattedTextField24.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField5.getValue()).doubleValue();
        double result = first * second;
        ayuda1SubTotal = result;
        setFirstSubTotal();
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField24PropertyChange

private void jFormattedTextField25PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField25PropertyChange
    try {
        double first = ((Number) jFormattedTextField25.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField6.getValue()).doubleValue();
        double result = first * second;
        labor1SubTotal = result;
        setFirstSubTotal();
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField25PropertyChange

private void jFormattedTextField26PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField26PropertyChange
    try {
        double first = ((Number) jFormattedTextField26.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField17.getValue()).doubleValue();
        double result = first * second;
        foreman1SubTotal = result;
        setFirstSubTotal();
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField26PropertyChange

private void jFormattedTextField19PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField19PropertyChange
    try {
        double first = ((Number) jFormattedTextField19.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField12.getValue()).doubleValue();
        double result = first * second;
        elect2SubTotal = result;
        setSecondSubTotal();
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField19PropertyChange

private void jFormattedTextField20PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField20PropertyChange
    try {
        double first = ((Number) jFormattedTextField20.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField13.getValue()).doubleValue();
        double result = first * second;
        ayuda2SubTotal = result;
        setSecondSubTotal();
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField20PropertyChange

private void jFormattedTextField21PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField21PropertyChange
    try {
        double first = ((Number) jFormattedTextField21.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField14.getValue()).doubleValue();
        double result = first * second;
        labor2SubTotal = result;
        setSecondSubTotal();
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField21PropertyChange

private void jFormattedTextField22PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField22PropertyChange
    try {
        double first = ((Number) jFormattedTextField22.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField18.getValue()).doubleValue();
        double result = first * second;
        foreman2SubTotal = result;
        setSecondSubTotal();
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField22PropertyChange

private void jFormattedTextField7PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField7PropertyChange
    try {
        double total = ((Number) jFormattedTextField7.getValue()).doubleValue() * ((Number) jFormattedTextField3.getValue()).doubleValue();
        jFormattedTextField8.setValue(total);
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField7PropertyChange

private void jFormattedTextField3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField3PropertyChange
    try {
        double total = ((Number) jFormattedTextField7.getValue()).doubleValue() * ((Number) jFormattedTextField3.getValue()).doubleValue();
        jFormattedTextField8.setValue(total);
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField3PropertyChange

private void jFormattedTextField11PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField11PropertyChange
    try {
        double total = ((Number) jFormattedTextField11.getValue()).doubleValue() * ((Number) jFormattedTextField15.getValue()).doubleValue();
        jFormattedTextField16.setValue(total);
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField11PropertyChange

private void jFormattedTextField15PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField15PropertyChange
    try {
        double total = ((Number) jFormattedTextField11.getValue()).doubleValue() * ((Number) jFormattedTextField15.getValue()).doubleValue();
        jFormattedTextField16.setValue(total);
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField15PropertyChange

private void jFormattedTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField21ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jFormattedTextField21ActionPerformed

private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
    jPanel1.requestFocusInWindow();
}//GEN-LAST:event_jPanel1MouseClicked

private void jFormattedTextField12PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField12PropertyChange
try {
        double first = ((Number) jFormattedTextField19.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField12.getValue()).doubleValue();
        double result = first * second;
        elect2SubTotal = result;
        setSecondSubTotal();
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField12PropertyChange

private void jFormattedTextField13PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField13PropertyChange
try {
        double first = ((Number) jFormattedTextField20.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField13.getValue()).doubleValue();
        double result = first * second;
        ayuda2SubTotal = result;
        setSecondSubTotal();
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField13PropertyChange

private void jFormattedTextField14PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField14PropertyChange
try {
        double first = ((Number) jFormattedTextField21.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField14.getValue()).doubleValue();
        double result = first * second;
        labor2SubTotal = result;
        setSecondSubTotal();
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField14PropertyChange

private void jFormattedTextField18PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField18PropertyChange
try {
        double first = ((Number) jFormattedTextField22.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField18.getValue()).doubleValue();
        double result = first * second;
        foreman2SubTotal = result;
        setSecondSubTotal();
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField18PropertyChange

private void jFormattedTextField4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField4PropertyChange
    try {
        double first = ((Number) jFormattedTextField23.getValue()).doubleValue();
        double second = ((Number) jFormattedTextField4.getValue()).doubleValue();
        double result = first * second;
        elect1SubTotal = result;
        setFirstSubTotal();
    } catch (Exception e) {
    }
}//GEN-LAST:event_jFormattedTextField4PropertyChange

private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
    SiteAdmin.setManoDeObra(6, getTotal());
}//GEN-LAST:event_formComponentHidden

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
