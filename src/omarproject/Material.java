/*
 * Material.java
 *
 * Created on October 5, 2007, 1:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package omarproject;

/**
 *
 * @author Luiso
 */
import java.io.Serializable;

/**
 *
 * @author Luiso
 */
public class Material implements Serializable {

    private String name;
    private double price;
    private static final long serialVersionUID = 3L;

    /**
     * Creates a new instance of Material
     */
    public Material(String nombre, double precio) {
        name = nombre;
        price = precio;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void changePrice(double precio) {
        price = precio;
    }

    public void setName(String nombre) {
        name = nombre;
    }
}
