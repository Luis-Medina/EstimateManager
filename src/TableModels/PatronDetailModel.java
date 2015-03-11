/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TableModels;

import omarproject.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luiso
 */
public class PatronDetailModel extends AbstractTableModel{

    private Patron source;
    private String[] columns = {"Número", "Descripción", "Cantidad"};

    public int getRowCount() {
        return source.getLines().size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public PatronDetailModel() {
    }

    public PatronDetailModel(Patron p) {
        source = p;
    }

    public Object getValueAt(int row, int col) {
        PatronLine pl = source.getLines().get(row);
        switch (col) {
            case 0:
                return pl.getArticle().getNumber();
            case 1:
                return pl.getArticle().getDescription();
            case 2:
                if(pl.getQuantity() ==  -1)
                    return "AS REQ";
                return pl.getQuantity();
            default:
                return null;
        }
    }

    public void addPatronLine(PatronLine a) {
        source.addLine(a);
        fireTableDataChanged();
    }

    public PatronLine removePatronLineAt(int row) {
        PatronLine pl = (PatronLine) source.getLines().get(row);
        source.getLines().remove(row);
        fireTableDataChanged();
        return pl;
    }

    public Class getColumnClass(int col) {
        switch (col) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            default:
                return Object.class;
        }
    }
    
     public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 0: 
                return false;
            case 1: 
                return false;
            case 2: 
                return true;
            default:
                return false;
        }
    }
     
    public void setValueAt(Object value, int row, int col) {
        ArrayList<PatronLine> pl = source.getLines();
        switch (col) {
            case 0:
                pl.get(row).getArticle().setNumber(((Number) value).intValue());
                break;
            case 1:
                pl.get(row).getArticle().setDescription(value.toString());
                break;
            case 2:
                pl.get(row).setQuantity(((Number) value).intValue());
                break;
        }
    }
}
