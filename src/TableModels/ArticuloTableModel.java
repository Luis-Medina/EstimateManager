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

    private MyList datalist = new MyList();
    private String[] columns = {"Código", "Descripción", "Cantidad", "Precio"};
    private double total = 0;

    public int getRowCount() {
        try{
        return datalist.size();
        }
        catch(Exception e){
            return 0;
        }
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public ArticuloTableModel() {
    }

    public ArticuloTableModel(MyList pl) {
        datalist = pl;
        /*for (PatronLine patlin : pl) {
            if (patlin.getQuantity() != 0) {
                datalist.add(patlin);
            }
        }*/
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

    public void doRefresh() {
        datalist.clear();
        total = 0;
        for (PatronLine patlin : SiteAdmin.getConteo()) {
            if (patlin.getQuantity() != 0) {
                datalist.add(patlin);
                total += patlin.getPrice();
            }
        }
        fireTableDataChanged();
        SiteAdmin.updateArticulosTotal();
    }

    public double getTotal() {
        total = 0;
        for (PatronLine pl : datalist) {
            total += pl.getPrice();
        }
        return total;
    }
}
