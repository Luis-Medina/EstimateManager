/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package segundaFase;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luiso
 */
public class SiteMaterialModel extends AbstractTableModel {

    private double total = 0;
    private ArrayList<SiteMaterial> datalist = new ArrayList<SiteMaterial>();
    private String[] columns = {"SiteMaterial", "Cantidad de Piezas", "%1", "Total Piezas", "Precio", "Total"};

    public int getRowCount() {
        return datalist.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public SiteMaterialModel() {
    }

    public SiteMaterialModel(ArrayList<SiteMaterial> pl) {
       datalist = pl;
    }
    
    public void addSiteMaterial(SiteMaterial a) {
        datalist.add(a);
        fireTableDataChanged();
    }

    public Object getValueAt(int row, int col) {
        SiteMaterial pl = (SiteMaterial) datalist.get(row);
        switch (col) {
            case 0:
                return pl.getName();
            case 1:
                return pl.getQuantity();
            case 2:
                return pl.getOnePercent();
            case 3:
                return pl.getQtyPlusOne();
            case 4:
                return pl.getPrice();
            case 5:
                return pl.getTotalPrice();
            default:
                return null;
        }
    }
    
    public void setValueAt(Object value, int row, int col) {
        SiteMaterial pl = (SiteMaterial) datalist.get(row);
        switch (col) {
            case 1:
                total -= pl.getTotalPrice();
                pl.setQuantity((Integer) value);
                total += pl.getTotalPrice();
                fireTableDataChanged();
                SiteAdmin.updateSiteTotal(total);
        }
    }

    public Class getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return Integer.class;
            case 3:
                return Integer.class;
            case 4:
                return Double.class;
            case 5:
                return Double.class;
            default:
                return Object.class;
        }
    }

    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return false;
            case 3:
                return false;
            case 4:
                return false;
            case 5:
                return false;
            default:
                return true;
        }
    }

    
}

