/*
 * ArticuloTableModel.java
 *
 * Created on October 5, 2007, 1:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package TableModels;

/**
 *
 * @author Luiso
 */
import omarproject.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/*
 * ArticuloTableModel.java
 *
 * Created on September 27, 2007, 10:25 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import segundaFase.SiteAdmin;

/**
 *
 * @author Luiso
 */
public class ArticuloTableModel extends AbstractTableModel {

    private MyList<PatronLine> datalist;
    private String[] columns = {"Código", "Descripción", "Cantidad", "Precio"};
    private double total = 0;

    public int getRowCount() {
        return datalist.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public ArticuloTableModel() {
    }

    public ArticuloTableModel(MyList<PatronLine> pl) {
        datalist = pl;
    }

    public Object getValueAt(int row, int col) {
        PatronLine pl = (PatronLine) datalist.get(row);
        switch (col) {
            case 0:
                return pl.getArticle().getCode();
            case 1:
                return pl.getArticle().getDescription();
            case 2:
                if (pl.getQuantity() < 0) {
                    return "AS REQ";
                }
                return pl.getQuantity();
            case 3:
                if (pl.getQuantity() < 0) {
                    return 0;
                }
                return pl.getPrice();
            default:
                return null;
        }
    }

    public Class getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return Double.class;
            default:
                return Object.class;
        }
    }

    public boolean isCellEditable() {
        return false;
    }

    public double getTotal() {
        return total;
    }

    public void addArticulo(PatronLine pl){
        datalist.addPatronLine(pl);
        fireTableDataChanged();
    }

    public void removeArticulos(Poste p){
        for(Patron patron : p.getPatrones()){
            for(PatronLine pl : patron.getLines()){
                datalist.removePatronLine(pl);
            }
        }
        fireTableDataChanged();
    }

    public void removeArticulos(ArrayList<Patron> list){
        for(Patron patron : list){
            for(PatronLine pl : patron.getLines()){
                datalist.removePatronLine(pl);
            }
        }
        fireTableDataChanged();
    }
}
