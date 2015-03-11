/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package omarproject;

import java.awt.Color;
import java.awt.Component;
import java.text.NumberFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Luiso
 */
public class IntegerRenderer extends DefaultTableCellRenderer {

    public IntegerRenderer() {
      super();
      setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    }

    public void setValue(Object value) {
      if ((value != null) && (value instanceof Number)) {
        Number numberValue = (Number) value;
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        value = formatter.format(numberValue.intValue());
      } 
      super.setValue(value);
    } 
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        setEnabled(table == null || table.isEnabled()); // see question above
        if ((row % 2) == 0) {
            setBackground(Color.getHSBColor((float) 0.3, (float) 0.1, (float) 1));
        } else {
            setBackground(Color.WHITE);
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);

        return this;
    }

}
