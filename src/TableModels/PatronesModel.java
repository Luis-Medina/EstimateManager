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
public class PatronesModel extends AbstractTableModel {

    private ArrayList<Patron> datalist = new ArrayList<Patron>();
    private String[] columns = {"Numero"};

    public int getRowCount() {
        return datalist.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public PatronesModel() {
    }

    public PatronesModel(ArrayList<Patron> l) {
        datalist = l;
    }

    public Object getValueAt(int row, int col) {
        Patron m = (Patron) datalist.get(row);
        switch (col) {
            case 0:
                return m.getNumber();
            default:
                return null;
        }
    }

    public void addPatron(Patron a) {
        datalist.add(a);
        fireTableDataChanged();
    }

    public void addPatronList(ArrayList<Patron> l) {
        datalist.addAll(l);
        fireTableDataChanged();
    }

    public Patron getPatronAt(int row) {
        return (Patron) datalist.get(row);
    }

    public Patron removePatronAt(int row) {
        Patron a = (Patron) datalist.remove(row);
        fireTableDataChanged();
        return a;
    }

    public Class getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            default:
                return Object.class;
        }
    }

    public void setList(ArrayList<Patron> l) {
        datalist = l;
        fireTableDataChanged();
    }

    public void setValueAt(Object value, int row, int col) {
        Patron a = (Patron) datalist.get(row);
        switch (col) {
            case 0:
                a.setNumber(value.toString());
                break;
        }
    }
    
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 0: //Name
                return true;
            default:
                return true;
        }
    }
}
