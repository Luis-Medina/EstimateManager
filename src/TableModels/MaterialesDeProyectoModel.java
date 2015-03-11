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
public class MaterialesDeProyectoModel extends AbstractTableModel {

    private ArrayList<Material> datalist = new ArrayList<Material>();
    private String[] columns = {"Nombre", "Precio"};

    public int getRowCount() {
        return datalist.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public MaterialesDeProyectoModel() {
    }

    public MaterialesDeProyectoModel(ArrayList<Material> l) {
        datalist = l;
    }

    public Object getValueAt(int row, int col) {
        Material m = (Material) datalist.get(row);
        switch (col) {
            case 0:
                return m.getName();
            case 1:
                return m.getPrice();
            default:
                return null;
        }
    }

    public void addMaterial(Material a) {
        datalist.add(a);
        fireTableDataChanged();
    }

    public void addMaterialList(ArrayList<Material> l) {
        datalist.addAll(l);
        fireTableDataChanged();
    }

    public Material getMaterialAt(int row) {
        return (Material) datalist.get(row);
    }

    public Material removeMaterialAt(int row) {
        Material a = (Material) datalist.remove(row);
        fireTableDataChanged();
        return a;
    }

    public Class getColumnClass(int col) {
        switch (col) {
            case 0:
                return String.class;
            case 1:
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
            default:
                return true;
        }
    }

    public void setList(ArrayList<Material> l) {
        datalist = l;
        fireTableDataChanged();
    }
     
    public void setValueAt(Object value, int row, int col) {
        Material a = (Material) datalist.get(row);
        switch (col) {
            case 0:
                a.setName(value.toString());
                break;
            case 1:
                Double valor = (Double) value;
                a.changePrice(valor.doubleValue());
                break;   
        }
    }
    
   
}
