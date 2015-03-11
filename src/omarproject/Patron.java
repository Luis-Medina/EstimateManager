/*
 * Patron.java
 *
 * Created on October 5, 2007, 1:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package omarproject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Luiso
 */
public class Patron implements Serializable{
    
    private String number;
    private ArrayList<Articulo> articles = new ArrayList<Articulo>();
    private ArrayList<Integer> quantities = new ArrayList<Integer>();
    private ArrayList<PatronLine> lines = new ArrayList<PatronLine>();
    private static final long serialVersionUID = 3L;
    private double price;

         
    /** Creates a new instance of Patron */
    public Patron(String numero) {
        number = numero;
        price = 0;
    }
    
    public String getNumber(){
        return number;
    }
    
    public void addLine(PatronLine pl){
        articles.add(pl.getArticle());
        quantities.add(pl.getQuantity());
        lines.add(pl);
        price += pl.getPrice();
    }
        
    public double getPrice() {
        double total = 0;
        for(PatronLine pl : lines)
            total += pl.getPrice();
         return total;   
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public ArrayList<Articulo> getArticles(){
        return articles;
    }
    
    public ArrayList<Integer> getQuantities(){
        return quantities;
    }
    
    public ArrayList<PatronLine> getLines(){
        return lines;
    }
    
    public int getQuantity(int i){
        return quantities.get(i);
    }
    
    public String toString(){
        String s = "";
        for(int i=0; i<articles.size(); i++){
            s = s + (articles.get(i).getDescription() + " , " + quantities.get(i) + "\n");
        }
        return s;
    }
    
    public void setNumber(String s){
        number = s;
    }
    
}
