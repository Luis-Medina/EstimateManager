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
public class MaterialesEnManualModel extends AbstractTableModel {

    private ArrayList<Articulo> datalist = new ArrayList<Articulo>();
    private String[] columns = {"Número", "Descripción", "Código", "Precio ($)"};

    public int getRowCount() {
        return datalist.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public MaterialesEnManualModel() {
    }

    public MaterialesEnManualModel(ArrayList<Articulo> l) {
        datalist = l;
    }
    
    public void setList(ArrayList<Articulo> l){
        datalist = l;
        fireTableDataChanged();
    }

    public Object getValueAt(int row, int col) {
        Articulo a = (Articulo) datalist.get(row);
        switch (col) {
            case 0:
                return a.getNumber();
            case 1:
                return a.getDescription();
            case 2:
                return a.getCode();
            case 3:
                return a.getPrice();
            default:
                return null;
        }
    }

    public void addArticulo(Articulo a) {
        datalist.add(a);
        fireTableDataChanged();
    }

    public void addArticuloList(ArrayList<Articulo> l) {
        datalist.addAll(l);
        fireTableDataChanged();
    }

    public Articulo getArticuloAt(int row) {
        return (Articulo) datalist.get(row);
    }

    public Articulo removeArticuloAt(int row) {
        Articulo a = (Articulo) datalist.remove(row);
        fireTableDataChanged();
        return a;
    }

    public Class getColumnClass(int col) {
        switch (col) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Double.class;
            default:
                return Object.class;
        }
    }

    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 0: 
                return true;
            case 1: 
                return true;
            case 2: 
                return true;
            case 3: 
                return true;
            default:
                return true;
        }
    }

    public void setValueAt(Object value, int row, int col) {
        Articulo a = (Articulo) datalist.get(row);
        switch (col) {
            case 0:{
                Integer valor = (Integer) value;
                a.setNumber(valor.intValue());
                break;
            }
            case 1:{
                a.setDescription(value.toString());
                break;
            }
            case 2:{
                a.setCode(value.toString());
                break;
            }
            case 3:{
                Double valor = (Double) value;
                a.setPrice(valor.doubleValue());
                break;
            }
        }
    }
}
