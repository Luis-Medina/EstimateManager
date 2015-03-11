/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package omarproject;

import java.awt.Color;
import java.awt.Toolkit;
import java.text.NumberFormat;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Luiso
 */
public class MyVerifier extends InputVerifier {

    NumberFormat moneyFormat = (NumberFormat) NumberFormat.getNumberInstance();

    @Override
    public boolean verify(JComponent input) {
        JFormattedTextField field = (JFormattedTextField) input;
        String s = field.getText();
        if (s.equals("")) {
            return true;
        }
        try {
            moneyFormat.parse(s).doubleValue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean shouldYieldFocus(JComponent input) {
        boolean inputOK = verify(input);
        JFormattedTextField field = (JFormattedTextField) input;
        if (field.isEditable()) {
            if (inputOK) {
                input.setBackground(Color.WHITE);
                return true;
            } else {
                input.setBackground(Color.RED);
                Toolkit.getDefaultToolkit().beep();
                return false;
            }
        } else {
            return true;
        }
    }
}

