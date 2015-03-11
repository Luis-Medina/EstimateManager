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
public class PostesModel extends AbstractTableModel {

    private ArrayList<Poste> datalist = new ArrayList<Poste>();
    private String[] columns = {"Clase", "Alto (pies)", "Precio ($)"};

    public int getRowCount() {
        return datalist.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public PostesModel() {
    }

    public PostesModel(ArrayList<Poste> l) {
        datalist = l;
    }
    
    public void setList(ArrayList<Poste> l) {
        datalist = l;
        fireTableDataChanged();
    }

    public Object getValueAt(int row, int col) {
        Poste m = (Poste) datalist.get(row);
        switch (col) {
            case 0:
                return m.getClase();
            case 1:
                return m.getHeight();
            case 2:
                return m.getPrice();
            default:
                return null;
        }
    }

    public void addPoste(Poste a) {
        datalist.add(a);
        fireTableDataChanged();
    }

    public void addPosteList(ArrayList<Poste> l) {
        datalist.addAll(l);
        fireTableDataChanged();
    }

    public Poste getPosteAt(int row) {
        return (Poste) datalist.get(row);
    }

    public Poste removePosteAt(int row) {
        Poste a = (Poste) datalist.remove(row);
        fireTableDataChanged();
        return a;
    }

    public Class getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return Double.class;    
            default:
                return Object.class;
        }
    }
    
    public void setValueAt(Object value, int row, int col) {
        Poste a = (Poste) datalist.get(row);
        switch (col) {
            case 0:
                a.setClase(value.toString());
                break;
            case 1:
                Integer valor = (Integer) value;
                a.setHeight(valor.intValue());
                break;
            case 2:
                Double valor1 = (Double) value;
                a.setPrice(valor1.doubleValue());
                break;    
        }
    }
    
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 0: //Name
                return true;
            case 1: //value
                return true;
            case 2: //location
                return true;
            default:
                return true;
        }
    }
}
