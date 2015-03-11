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
public class MaterialesEnPosteModel extends AbstractTableModel{

    private ArrayList<PatronLine> datalist;
    private String[] columns={"Código", "Descripción", "Cantidad", "Precio ($)"};
    

    public int getRowCount() {
        return datalist.size();
    }
    
    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }
    
    public MaterialesEnPosteModel() {
    }

    public MaterialesEnPosteModel(ArrayList<PatronLine> l) {
        datalist = l;
    } 

    public Object getValueAt(int row, int col) {
        PatronLine a = (PatronLine) datalist.get(row);
        switch (col) {
            case 0:
                return a.getArticle().getCode();
            case 1:
                return a.getArticle().getDescription();
            case 2:
                if(a.getQuantity() < 0)
                    return "AS REQ";
                return a.getQuantity();
            case 3:
                if(a.getQuantity() < 0)
                    return 0;
                return a.getArticle().getPrice() * a.getQuantity();
            default:    
                return null;
        }
    }
    
    public void addPatronLine(PatronLine a) {
        datalist.add(a);
        fireTableDataChanged();
    }
  
    public void addPatronLineList(ArrayList<PatronLine> l) {
        datalist.addAll(l);
        fireTableDataChanged();
    }  
    
    public PatronLine getPatronLineAt(int row) {
        return (PatronLine)datalist.get(row);
    }
    
    public PatronLine removePatronLineAt(int row) {
        PatronLine a = (PatronLine)datalist.remove(row);
        fireTableDataChanged();
        return a;
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
    
    public boolean isCellEditable(){
        return false;
    }
    
}
