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
public class MyList<E> extends ArrayList<PatronLine>{

    public void addPatronLine(PatronLine nuevo) {
        boolean found = false;
        for (PatronLine toCheck : this) {
            if (toCheck.compareTo(nuevo) == 0) {
                toCheck.addToQuantity(nuevo.getQuantity());
                found = true;
            }
        }
        if (!found) {
            this.add(new PatronLine(nuevo.getArticle(), nuevo.getQuantity()));
        }
    }

    public void removePatronLine(PatronLine vieja){
        boolean delete = false;
        PatronLine toRemove = null;
        for(PatronLine toCheck : this){
            if(toCheck.compareTo(vieja) == 0){
                toCheck.subtractFromQuantity(vieja.getQuantity());
                if(toCheck.getQuantity() == 0){
                    delete = true;
                    toRemove = toCheck;
                }
            }
        }
        if(delete)
            this.remove(toRemove);
    }
    
}
