/*
 * Articulo.java
 *
 * Created on October 5, 2007, 1:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package omarproject;

import java.io.Serializable;

/**
 *
 * @author Luiso
 */
public class Articulo implements Serializable{
    
    private int number;
    private String description;
    private String code;
    private double price;    
    private static final long serialVersionUID = 3L;
    
    /** Creates a new instance of Articulo */
    public Articulo(int num, String desc, String codigo, double precio) {
        number = num;
        description = desc;
        code = codigo;
        price = precio;
    }
    
    public int getNumber(){
        return number;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getCode(){
        return code;
    }
    
    public double getPrice(){
        return price;
    }
    
    public void setNumber(int num){
        number = num;
    }
    
    public void setDescription(String desc){
        description = desc;
    }
    
    public void setCode(String codigo){
        code = codigo;
    }
    
    public void setPrice(double precio){
        price = precio;
    }
    
}
