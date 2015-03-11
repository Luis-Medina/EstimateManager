/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package segundaFase;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Luiso
 */
public class ColorRenderer extends DefaultTableCellRenderer {

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
