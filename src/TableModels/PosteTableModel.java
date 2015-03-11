/*
 * PosteTableModel.java
 *
 * Created on October 5, 2007, 1:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package TableModels;

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
public class PosteTableModel extends AbstractTableModel {

    private ArrayList<Poste> datalist = new ArrayList<Poste>();
    private String[] columns = {"Nombre", "Clase", "Alto (pies)", "Precio del poste ($)", "Precio con materiales ($)"};
    private double posteOnlyTotal = 0;
    private double posteGrandTotal = 0;
    
    public int getRowCount() {
        return datalist.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public PosteTableModel() {
    }

    public PosteTableModel(ArrayList<Poste> l) {
        datalist = l;
    }

    public Object getValueAt(int row, int col) {
        Poste p = (Poste) datalist.get(row);
        switch (col) {
            case 0:
                return p.getName();
            case 1:
                return p.getClase();
            case 2:
                return p.getHeight();
            case 3:
                return p.getPrice();
            case 4:
                return p.getTotalPrice();
            default:
                return null;
        }
    }

    
    public void addPoste(Poste p) {
        datalist.add(p);
        fireTableDataChanged();
    }
    

    
    public void setPoste(Poste p, int index){
        datalist.set(index, p); 
        fireTableDataChanged();
    }

    /*
    public void addPosteList(ArrayList<Poste> l) {
        datalist.addAll(l);
        fireTableDataChanged();
    }
     */

    public Poste getPosteAt(int row) {
        return (Poste) datalist.get(row);
    }

    public Poste removePosteAt(int row) {
        Poste p = (Poste) datalist.remove(row);
        fireTableDataChanged();
        return p;
    }

    @Override
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
            case 4:
                return Double.class;
            default:
                return Object.class;
        }
    }

    public boolean isCellEditable() {
        return false;
    }

    public double getTotal(){
        double total = 0;
        for(Poste p : datalist) {
            total += p.getTotalPrice();
        }
        return total;
    }
    
    public double getPosteOnlyTotal(){
        double total = 0;
        for(Poste p : datalist) {
            total += p.getPrice();
        }
        return total;
    }
    
}
