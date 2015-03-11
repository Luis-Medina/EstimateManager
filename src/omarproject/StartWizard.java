package omarproject;

/*
 * StartWizard.java
 *
 * Created on October 5, 2007, 1:24 PM
 */
import java.awt.Cursor;
import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import segundaFase.PasswordBox;
import segundaFase.SiteAdmin;
import segundaFase.SiteMaterial;
import segundaFase.TextValuesComp;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author  Luiso
 */
public class StartWizard extends javax.swing.JFrame {

    private static String dirName = System.getProperty("user.dir");
    static ArrayList<Articulo> articulos = new ArrayList<Articulo>();
    static ArrayList<Material> materiales = new ArrayList<Material>();
    static ArrayList<Patron> patrones = new ArrayList<Patron>();
    static ArrayList<Poste> postes = new ArrayList<Poste>();
    static boolean saved = false;
    private final static int workhours = 8;
    private static DefaultFormatterFactory myFormatter;
    private static MyVerifier verifier = new MyVerifier();
    private static SiteAdmin sa;
    public static JFileChooser chooser = new JFileChooser();
    private static DefaultFormatterFactory mine;
    private static String filename;
    private Cipher desCipher;
    private SecretKey secretKey;
    private byte[] key = "estaesla1.5veyronclaveesperofuncione".getBytes();
    private static char[] password = {'a', 'd', 'm', 'i', 'n'};
    private PasswordBox pb;
    static String databaseDate;
    static ArrayList<TextValuesComp> adminValues;
    private static MyFileFilter filter;

    /** Creates new form StartWizard */
    public StartWizard() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            readPassword();
        } catch (Exception e) {
        }
        try {
            loadPrimitiveData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database not found. Make sure the file " +
                    "'primitiveData.dat' is in the same directory as the program.");
        }
        setPrices();
        initComponents();
        createPercentFormatter();
        myFormatter = new DefaultFormatterFactory(
                new NumberFormatter(NumberFormat.getCurrencyInstance()),
                new NumberFormatter(NumberFormat.getCurrencyInstance()),
                new NumberFormatter(NumberFormat.getNumberInstance()));
        createChooser();
        filter = new MyFileFilter();
        chooser.setFileFilter(filter);
        setLocationRelativeTo(null);
    }
    
    public static MyFileFilter getFilter(){
        return filter;
    }

    public static ArrayList<TextValuesComp> getAdminValues() {
        return adminValues;
    }

    public static void setAdminValues(ArrayList<TextValuesComp> list) {
        adminValues = list;
    }

    public static String getDatabaseDate() {
        return databaseDate;
    }

    public static void setDatabaseDate(String s) {
        databaseDate = s;
    }

    public static int getWorkhours() {
        return workhours;
    }

    public static MyVerifier getVerifier() {
        return verifier;
    }

    public static String getDirName() {
        return dirName;
    }

    public static DefaultFormatterFactory getMine() {
        return mine;
    }

    public static char[] getPassword() {
        return password;
    }

    public static void setPassword(char[] password) {
        StartWizard.password = password;
    }

    public static String getFilename() {
        return filename;
    }

    private void readPassword() {
        FileInputStream fis = null;
        {
            ObjectInputStream ois = null;
            try {
                DESKeySpec desKeySpec = new DESKeySpec(key);
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                secretKey = keyFactory.generateSecret(desKeySpec);
                desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                desCipher.init(Cipher.DECRYPT_MODE, secretKey);
                fis = new FileInputStream(dirName + "\\pas.des");
                BufferedInputStream bis = new BufferedInputStream(fis);
                CipherInputStream cis = new CipherInputStream(bis, desCipher);
                ois = new ObjectInputStream(cis);
                password = (char[]) ois.readObject();
                ois.close();
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(StartWizard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(StartWizard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(StartWizard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(StartWizard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StartWizard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StartWizard.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(StartWizard.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    ois.close();
                } catch (IOException ex) {
                    Logger.getLogger(StartWizard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void savePassword() {
        {
            FileOutputStream fos = null;
            {
                ObjectOutputStream oos = null;
                try {
                    DESKeySpec desKeySpec = new DESKeySpec(key);
                    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                    secretKey = keyFactory.generateSecret(desKeySpec);
                    desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                    desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
                    fos = new FileOutputStream(dirName + "\\pas.des");
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    CipherOutputStream cos = new CipherOutputStream(bos, desCipher);
                    oos = new ObjectOutputStream(cos);
                    // Write objects
                    oos.writeObject(password);
                    oos.flush();
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(AdminValues.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchPaddingException ex) {
                    Logger.getLogger(AdminValues.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(AdminValues.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(AdminValues.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(AdminValues.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fos.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AdminValues.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        oos.close();
                    } catch (IOException ex) {
                        Logger.getLogger(AdminValues.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    private void createChooser() {
        chooser = new JFileChooser() {

            public void approveSelection() {
                File file = getSelectedFile();
                if (file != null && file.exists() && getDialogType() == SAVE_DIALOG) {
                    int answer = showSaveDisplayQuestion(file);
                    if (answer == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
                super.approveSelection();

            }
        };
    }

    private void setPrices() {
        for (Patron p : patrones) {
            for (PatronLine pl : p.getLines()) {
                pl.setPrice(pl.getPrice());
            }
        }
    }

    private int showSaveDisplayQuestion(File file) {
        String message = "El archivo '" + file.getName() +
                "' ya existe. Quiere re-escribirlo?";
        return JOptionPane.showOptionDialog(null,
                message,
                "Save Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                null,
                null);
    }

    private void createPercentFormatter() {
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

    public static DefaultFormatterFactory getMyFormatter() {
        return myFormatter;
    }

    public static ArrayList<Articulo> getArticles() {
        return articulos;
    }

    public static ArrayList<Material> getMaterials() {
        return materiales;
    }

    public static ArrayList<Patron> getPatrones() {
        return patrones;
    }

    public static ArrayList<Poste> getPostes() {
        return postes;
    }

    private void openProject() {
        ObjectInputStream in;
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            filename = selectedFile.getAbsolutePath();
            try {
                in = new ObjectInputStream(new FileInputStream(filename));
                ArrayList<ArrayList<TextValuesComp>> siteInfo = (ArrayList<ArrayList<TextValuesComp>>) in.readObject();
                String name = (String) in.readObject();
                String dateCreated = (String) in.readObject();
                String dateModified = (String) in.readObject();
                int difficulty = (Integer) in.readObject();
                String location = (String) in.readObject();
                String comments = (String) in.readObject();
                ArrayList<SiteMaterial> siteMaterials = (ArrayList<SiteMaterial>) in.readObject();
                ArrayList<Poste> posts = (ArrayList<Poste>) in.readObject();
                ArrayList<PatronLine> pat = (ArrayList<PatronLine>) in.readObject();
                in.close();
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                sa = new SiteAdmin(siteInfo, name, dateCreated, dateModified, difficulty, location, comments, siteMaterials, posts, pat);
                sa.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } catch (IOException ex) {
                Logger.getLogger(StartWizard.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StartWizard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CEG - Estimate Manager");
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closing(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Funciones Administrativas");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Settings1.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Spreadsheet.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Editar Base de Datos");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Abrir proyecto existente");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/My-Docs.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Comenzar proyecto nuevo");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Text-Edit.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem4.setText("Leer información de archivos de texto");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator2);

        jMenuItem2.setText("New Project");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Open Project");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem5.setText("About");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jLabel1, jLabel2, jLabel3, jLabel4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2)))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        savePassword();
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    sa = new SiteAdmin();
    sa.setVisible(true);
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    openProject();
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    try {
        if (pb.isShowing()) {
            pb.toFront();
            pb.setState(Frame.NORMAL);
        } else {
            pb = new PasswordBox("Database", this);
            pb.setVisible(true);
        }
    } catch (Exception e) {
        pb = new PasswordBox("Database", this);
        pb.setVisible(true);
    }
}//GEN-LAST:event_jButton3ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    try {
        if (pb.isShowing()) {
            pb.toFront();
            pb.setState(Frame.NORMAL);
        } else {
            pb = new PasswordBox("Admin", this);
            pb.setVisible(true);
        }
    } catch (Exception e) {
        pb = new PasswordBox("Admin", this);
        pb.setVisible(true);
    }
}//GEN-LAST:event_jButton4ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    SiteAdmin pm = new SiteAdmin();
    pm.setVisible(true);
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_jMenuItem2ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    openProject();
}//GEN-LAST:event_jMenuItem3ActionPerformed

private void closing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closing
    savePassword();
    System.exit(0);
}//GEN-LAST:event_closing

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    Object[] options = {"Sí", "No"};
    int option = JOptionPane.showOptionDialog(null, "Esta seguro que " +
            "quiere re-escribir la base de datos con la información de los archivos de texto?", "Confirmar",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, options, options[0]);
    if (option == 0) {
        writePrimitiveData();
    }

}//GEN-LAST:event_jMenuItem4ActionPerformed

private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
    About about = new About();
    about.setVisible(true);
}//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new StartWizard().setVisible(true);
            }
        });
    }

    public static void loadPrimitiveData() {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(dirName + "\\primitiveData.dat"));
        } catch (FileNotFoundException ex) {
            //JOptionPane.showMessageDialog(null, ex.toString());
        } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
        try {
            articulos = (ArrayList<Articulo>) in.readObject();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        try {
            materiales = (ArrayList<Material>) in.readObject();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        try {
            patrones = (ArrayList<Patron>) in.readObject();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        try {
            postes = (ArrayList<Poste>) in.readObject();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        try {
            databaseDate = (String) in.readObject();
        } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, "No date for database found");
        } catch (ClassNotFoundException ex) {
            // JOptionPane.showMessageDialog(null, ex.toString());
        }
        try {
            adminValues = (ArrayList<TextValuesComp>) in.readObject();
        } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, "No admin values found");
        } catch (ClassNotFoundException ex) {
            //JOptionPane.showMessageDialog(null, ex.toString());
        } /*try {
        password = (char[]) in.readObject();
        } catch (IOException ex) {
        ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
        }*/ finally {
            try {
                in.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        }
    }

    private void writePrimitiveData() {

        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    dirName + "\\manual.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                String number = str;
                int num = Integer.parseInt(number);
                String description = in.readLine();
                String code = in.readLine();
                String price = in.readLine();
                Double precio = Double.parseDouble(price);
                Articulo a = new Articulo(num, description, code, precio);
                articulos.add(a);
            }
            in.close();
        } catch (IOException e) {
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    dirName + "\\materiales.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                String name = str;
                String price = in.readLine();
                Double num = Double.parseDouble(price);
                Material m = new Material(name, num);
                materiales.add(m);
                in.readLine();
            }
            in.close();
        } catch (IOException e) {
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    dirName + "\\patrones.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                String name = str;
                Patron a = new Patron(name);
                try {
                    while ((str = in.readLine()).contains(",")) {
                        int i = str.indexOf(",");
                        String art = str.substring(0, i);
                        int art1 = Integer.parseInt(art);
                        String cant = str.substring(i + 1);
                        int cantidad = Integer.parseInt(cant);
                        PatronLine pl = new PatronLine(Finders.findArticulo(art1), cantidad);
                        a.addLine(pl);
                    }
                    patrones.add(a);
                } catch (Exception e) {
                }
            }
            in.close();
        } catch (IOException e) {
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    dirName + "\\postes.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                String clase = str;
                String alto = in.readLine();
                Integer i = Integer.parseInt(alto);
                String price = in.readLine();
                Double precio = Double.parseDouble(price);
                Poste p = new Poste(i, clase, precio);
                postes.add(p);
                in.readLine();
            }
            in.close();
        } catch (IOException e) {
        }

        /*for(Material m : materiales)
        System.out.println(m.getName() + " , " + m.getPrice());
        for(Articulo a : articulos)
        System.out.println(a.getNumber());
        for(Patron p : patrones)
        System.out.println(p.getNumber());
         */

        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(dirName + "\\primitiveData.dat"));
            out.writeObject(articulos);
            out.writeObject(materiales);
            out.writeObject(patrones);
            out.writeObject(postes);
            out.close();
            JOptionPane.showMessageDialog(null, "Se creó el archivo exitosamente.");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
