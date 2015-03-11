/*
 * Totales.java
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
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import omarproject.AdminValues;
import omarproject.StartWizard;

/**
 *
 * @author  Luiso
 */
public class Totales extends javax.swing.JPanel implements Serializable, Printable {

    private DefaultFormatterFactory mine;
    private double mdot;
    private double equipo;
    private double exc;
    private double horm;
    private double mst;
    private double misc;
    private double gruas;
    private double mdot1;
    private double subtotal2;
    private double seguros;
    private double overhead;
    private double subtotal3;
    private double profit;
    private double total;
    private static final long serialVersionUID = 3L;

    /** Creates new form Totales */
    public Totales() {
        inititalizePercentFormat();
        initComponents();
        this.setSize(new Dimension(687, 548));
    }

    public Totales(ArrayList<TextValuesComp> list) {
        inititalizePercentFormat();
        initComponents();
        this.setSize(new Dimension(687, 548));
        myDeserialize(list);
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

    public void setSiteTotal() {
       initializeFields();
    }

    private void inititalizePercentFormat() {
        NumberFormat pDispFormat = NumberFormat.getPercentInstance();
        pDispFormat.setMinimumFractionDigits(2);
        NumberFormat percentEditFormat = NumberFormat.getNumberInstance();
        percentEditFormat.setMinimumFractionDigits(2);
        NumberFormatter percentEditFormatter =
                new NumberFormatter(percentEditFormat) {

                    public String valueToString(Object o)
                            throws ParseException {
                        Number number = (Number) o;
                        if (number != null) {
                            double d = number.doubleValue() * 100.0;
                            number = new Double(d);
                        }
                        return super.valueToString(number);
                    }

                    public Object stringToValue(String s)
                            throws ParseException {
                        Number number = (Number) super.stringToValue(s);
                        if (number != null) {
                            double d = number.doubleValue() / 100.0;
                            number = new Double(d);
                        }
                        return number;
                    }
                };
        mine = new DefaultFormatterFactory(
                new NumberFormatter(pDispFormat),
                new NumberFormatter(pDispFormat),
                percentEditFormatter);
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

    private void setManoDeObraRow() {
        double subtotal = ((Number) jFormattedTextField2.getValue()).doubleValue();
        double percent = ((Number) jFormattedTextField23.getValue()).doubleValue();
        double percentValue = subtotal * percent;
        jFormattedTextField1.setValue(percentValue);
        jFormattedTextField18.setValue(percentValue + subtotal);
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

    private void initializeFields() {
        mdot = SiteAdmin.getManoDeObraTotal();
        equipo = SiteAdmin.getMisc().getEquipo();
        exc = SiteAdmin.getExcTotal();
        horm = SiteAdmin.getHormTotal();
        mst = SiteAdmin.getMaterialesSiteTotal();
        misc = SiteAdmin.getMiscTotal();
        gruas = SiteAdmin.getGruasTotal();
        jFormattedTextField2.setValue(mdot);
        jFormattedTextField6.setValue(equipo);
        jFormattedTextField3.setValue(exc);
        jFormattedTextField4.setValue(horm);
        jFormattedTextField5.setValue(mst);
        jFormattedTextField7.setValue(misc);
        jFormattedTextField24.setValue(gruas);
        setManoDeObraRow();
        mdot1 = ((Number) jFormattedTextField18.getValue()).doubleValue();
        double postesTotal = SiteAdmin.getPosteTableModel().getPosteOnlyTotal();
        double materialesPostesTotal = SiteAdmin.getArticuloTableModel().getTotal();
        jFormattedTextField9.setValue(postesTotal);
        jFormattedTextField10.setValue(materialesPostesTotal);
        subtotal2 = mdot1 + equipo + exc + horm + mst + misc + postesTotal + materialesPostesTotal;
        jFormattedTextField8.setValue(subtotal2);
        seguros = subtotal2 * ((Number) jFormattedTextField20.getValue()).doubleValue();
        jFormattedTextField15.setValue(seguros);
        overhead = subtotal2 * ((Number) jFormattedTextField21.getValue()).doubleValue();
        jFormattedTextField16.setValue(overhead);
        subtotal3 = subtotal2 + seguros + overhead;
        jFormattedTextField19.setValue(subtotal3);
        profit = subtotal3 * ((Number) jFormattedTextField22.getValue()).doubleValue();
        jFormattedTextField11.setValue(profit);
        total = subtotal3 + profit;
        jFormattedTextField13.setValue(total);
        fieldsDivide(jFormattedTextField13, jFormattedTextField17, jFormattedTextField14);
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
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jFormattedTextField21 = new javax.swing.JFormattedTextField();
        jFormattedTextField20 = new javax.swing.JFormattedTextField();
        jFormattedTextField15 = new javax.swing.JFormattedTextField();
        jFormattedTextField13 = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jFormattedTextField19 = new javax.swing.JFormattedTextField();
        jFormattedTextField17 = new javax.swing.JFormattedTextField();
        jFormattedTextField22 = new javax.swing.JFormattedTextField();
        jFormattedTextField11 = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jFormattedTextField14 = new javax.swing.JFormattedTextField();
        jFormattedTextField16 = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jFormattedTextField24 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jFormattedTextField23 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jFormattedTextField18 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField9 = new javax.swing.JFormattedTextField();
        jFormattedTextField10 = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(687, 548));
        setPreferredSize(new java.awt.Dimension(687, 548));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(687, 548));
        jPanel1.setPreferredSize(new java.awt.Dimension(687, 548));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel16.setText("Total");

        jLabel13.setText("Profit");

        jFormattedTextField21.setBorder(null);
        jFormattedTextField21.setEditable(false);
        jFormattedTextField21.setFormatterFactory(mine);
        jFormattedTextField21.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField21.setName("jFormattedTextField21"); // NOI18N
        jFormattedTextField21.setValue(AdminValues.getOverheadPercent());

        jFormattedTextField20.setBorder(null);
        jFormattedTextField20.setEditable(false);
        jFormattedTextField20.setFormatterFactory(mine);
        jFormattedTextField20.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField20.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField20.setName("jFormattedTextField20"); // NOI18N
        jFormattedTextField20.setValue(AdminValues.getSeguroPercent());

        jFormattedTextField15.setEditable(false);
        jFormattedTextField15.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField15.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField15.setName("jFormattedTextField15"); // NOI18N

        jFormattedTextField13.setEditable(false);
        jFormattedTextField13.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField13.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField13.setName("jFormattedTextField13"); // NOI18N

        jLabel11.setText("Overhead");

        jFormattedTextField19.setEditable(false);
        jFormattedTextField19.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField19.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField19.setName("jFormattedTextField19"); // NOI18N

        jFormattedTextField17.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jFormattedTextField17.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField17.setInputVerifier(StartWizard.getVerifier());
        jFormattedTextField17.setName("jFormattedTextField17"); // NOI18N
        jFormattedTextField17.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextField17PropertyChange(evt);
            }
        });

        jFormattedTextField22.setBorder(null);
        jFormattedTextField22.setEditable(false);
        jFormattedTextField22.setFormatterFactory(mine);
        jFormattedTextField22.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField22.setName("jFormattedTextField22"); // NOI18N
        jFormattedTextField22.setValue(AdminValues.getProfitPercent());

        jFormattedTextField11.setEditable(false);
        jFormattedTextField11.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField11.setName("jFormattedTextField11"); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Sub-total");

        jLabel10.setText("Seguros");

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel14.setText("Total");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Subtotal 2");

        jFormattedTextField8.setEditable(false);
        jFormattedTextField8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField8.setName("jFormattedTextField8"); // NOI18N

        jLabel15.setText("Total x unidad");

        jLabel20.setText("Unidades");

        jFormattedTextField14.setEditable(false);
        jFormattedTextField14.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField14.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField14.setName("jFormattedTextField14"); // NOI18N

        jFormattedTextField16.setEditable(false);
        jFormattedTextField16.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField16.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField16.setName("jFormattedTextField16"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jFormattedTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField17, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))))))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jFormattedTextField11, jFormattedTextField13, jFormattedTextField14, jFormattedTextField15, jFormattedTextField16, jFormattedTextField17, jFormattedTextField19, jFormattedTextField8});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jFormattedTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jFormattedTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jFormattedTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jFormattedTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jFormattedTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jFormattedTextField15, jFormattedTextField16, jFormattedTextField21});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jFormattedTextField11, jFormattedTextField22, jLabel13});

        jFormattedTextField24.setEditable(false);
        jFormattedTextField24.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField24.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField24.setName("jFormattedTextField24"); // NOI18N

        jLabel5.setText("Equipo rentado");

        jFormattedTextField2.setEditable(false);
        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField2.setName("jFormattedTextField2"); // NOI18N

        jFormattedTextField4.setEditable(false);
        jFormattedTextField4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField4.setName("jFormattedTextField4"); // NOI18N

        jLabel2.setText("Total excavacion");

        jLabel1.setText("Total mano de obra");

        jLabel4.setText("Materiales del Site");

        jLabel17.setText("Gruas y Transformadores");

        jLabel8.setText("Total Miscelaneos");

        jFormattedTextField7.setEditable(false);
        jFormattedTextField7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField7.setName("jFormattedTextField7"); // NOI18N

        jFormattedTextField6.setEditable(false);
        jFormattedTextField6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField6.setAutoscrolls(false);
        jFormattedTextField6.setName("jFormattedTextField6"); // NOI18N

        jFormattedTextField5.setEditable(false);
        jFormattedTextField5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField5.setName("jFormattedTextField5"); // NOI18N

        jFormattedTextField23.setBorder(null);
        jFormattedTextField23.setEditable(false);
        jFormattedTextField23.setFormatterFactory(mine);
        jFormattedTextField23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField23.setName("jFormattedTextField23"); // NOI18N
        jFormattedTextField23.setValue(0.18);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Total");

        jLabel3.setText("Total hormigon");

        jFormattedTextField1.setEditable(false);
        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField1.setName("jFormattedTextField1"); // NOI18N

        jFormattedTextField3.setEditable(false);
        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField3.setName("jFormattedTextField3"); // NOI18N

        jFormattedTextField18.setEditable(false);
        jFormattedTextField18.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField18.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField18.setName("jFormattedTextField18"); // NOI18N

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Total");

        jFormattedTextField9.setEditable(false);
        jFormattedTextField9.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jFormattedTextField10.setEditable(false);
        jFormattedTextField10.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        jFormattedTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel18.setText("Total de postes");

        jLabel19.setText("Total de materiales");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jFormattedTextField24, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(2, 2, 2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField23)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField18, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                        .addGap(115, 115, 115))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel8});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jFormattedTextField10, jFormattedTextField24, jFormattedTextField7, jFormattedTextField9});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jFormattedTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(11, 11, 11))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jFormattedTextField2, jFormattedTextField24, jFormattedTextField3, jFormattedTextField4, jFormattedTextField5, jFormattedTextField6, jFormattedTextField7});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(653, 653, 653)
                        .addComponent(jLabel16)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(266, 266, 266))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
    initializeFields();
}//GEN-LAST:event_formComponentShown

private void jFormattedTextField17PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextField17PropertyChange
    fieldsDivide(jFormattedTextField13, jFormattedTextField17, jFormattedTextField14);
}//GEN-LAST:event_jFormattedTextField17PropertyChange

private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
    initializeFields();
}//GEN-LAST:event_formFocusGained

private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
    jPanel1.requestFocusInWindow();
}//GEN-LAST:event_jPanel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField10;
    private javax.swing.JFormattedTextField jFormattedTextField11;
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
