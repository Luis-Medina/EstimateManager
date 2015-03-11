/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package omarproject;

import java.util.ArrayList;

/**
 *
 * @author Luiso
 */
public class MyList extends ArrayList<PatronLine> {

    public void addPatron(PatronLine nuevo) {
        if (size() == 0) {
            this.add(nuevo);
        } else {
            boolean found = false;
            for (PatronLine toCheck : this) {
                if (toCheck.compareTo(nuevo) == 0) {
                    toCheck.addToQuantity(nuevo.getQuantity());
                    found = true;
                    break;
                }
            }
            if (!found) {
                this.add(nuevo);
            }
        }
    }
}
