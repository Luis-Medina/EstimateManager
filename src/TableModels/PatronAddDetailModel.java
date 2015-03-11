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
public class PatronAddDetailModel extends AbstractTableModel {

    private ArrayList<PatronLine> source;
    private String[] columns = {"Número", "Descripción", "Cantidad"};

    public int getRowCount() {
        return source.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public ArrayList<PatronLine> getList() {
        return source;
    }

    public void setQuantities(ArrayList<PatronLine> lines) {
        for (PatronLine patlin : source) {
            for (PatronLine pl : lines) {
                if (pl.getArticle().getNumber() == patlin.getArticle().getNumber()) {
                    patlin.setQuantity(pl.getQuantity());
                }
            }
        }
    }

    public PatronAddDetailModel() {
    }

    public PatronAddDetailModel(ArrayList<Articulo> arl) {
        source = new ArrayList<PatronLine>();
        for (Articulo a : arl) {
            source.add(new PatronLine(a, 0));
        }
    }

    public PatronLine getRow(int row) {
        return source.get(row);
    }

    public Object getValueAt(int row, int col) {
        PatronLine pl = source.get(row);
        switch (col) {
            case 0:
                return pl.getArticle().getNumber();
            case 1:
                return pl.getArticle().getDescription();
            case 2:
                if (pl.getQuantity() == -1) {
                    return "AS REQ";
                }
                return pl.getQuantity();
            default:
                return null;
        }
    }

    public void addPatronLine(PatronLine a) {
        source.add(a);
        fireTableDataChanged();
    }

    public PatronLine removePatronLineAt(int row) {
        PatronLine pl = source.get(row);
        source.remove(row);
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
        PatronLine pl = source.get(row);
        switch (col) {
            case 2:
                pl.setQuantity(((Number) value).intValue());
                break;
        }
    }
}
