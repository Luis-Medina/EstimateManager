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
public class SiteMaterial implements Serializable{

    private String name;
    private int quantity;
    private int onePercent;
    private int qtyPlusOne;
    private double price;
    private double totalPrice;
    private static final long serialVersionUID = 3L;
    
    public SiteMaterial(String nombre, int cantidad, double precio){
        name = nombre;
        quantity = cantidad;
        onePercent = (int) Math.round((quantity*.01));
        qtyPlusOne = quantity + onePercent;
        price = precio;
        totalPrice = qtyPlusOne * price;
    }
    
    public SiteMaterial(String nombre, int cantidad, double precio, int up, int cmu, double total){
        name = nombre;
        quantity = cantidad;
        onePercent = up;
        qtyPlusOne =cmu;
        price = precio;
        totalPrice = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOnePercent() {
        return onePercent;
    }

    public void setOnePercent(int onePercent) {
        this.onePercent = onePercent;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQtyPlusOne() {
        return qtyPlusOne;
    }

    public void setQtyPlusOne(int qtyPlusOne) {
        this.qtyPlusOne = qtyPlusOne;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        onePercent = (int) Math.round((quantity*.01));
        qtyPlusOne = quantity + onePercent;
        totalPrice = qtyPlusOne * price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
}
