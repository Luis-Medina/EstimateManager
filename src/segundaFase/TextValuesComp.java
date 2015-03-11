/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package segundaFase;

import java.io.Serializable;

/**
 *
 * @author Luiso
 */
public class TextValuesComp implements Serializable{

    private String name;
    private double value;
    private static final long serialVersionUID = 3L;
    
    public TextValuesComp(String n, double d){
        name = n;
        value = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
}
