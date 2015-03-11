/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package omarproject;

import java.io.Serializable;

/**
 *
 * @author Luiso
 */
public class PatronLine implements Comparable<PatronLine>, Serializable {

    private Articulo article;
    private int quantity;
    private double price;
    private static final long serialVersionUID = 3L;
    
    public PatronLine(Articulo art, int number){
        article = art;
        quantity = number;
        if(number < 0)
            price = 0;
        else
            price = art.getPrice() * number;
    }

    public PatronLine(Articulo art, int number, double precio){
        article = art;
        quantity = number;
        price = precio;
    }
    
    public double getPrice() {
        if(price < 0)
            return 0;
        return article.getPrice() * quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public Articulo getArticle(){
        return article;       
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setArticle(Articulo a){
        article = a;
        price = quantity * article.getPrice();
    }
    
    public void setQuantity(int i){
        quantity = i;
        price = quantity * article.getPrice();
    }
    
    public void addToQuantity(int i){
        quantity = quantity + i;
        price = quantity * article.getPrice();
    }
    
    public void subtractFromQuantity(int i){
        quantity = quantity - i;
        price = quantity * article.getPrice();
    }

    @Override
    public int compareTo(PatronLine o) {
        if(article.getNumber() == o.getArticle().getNumber())
            return 0;
        if(article.getNumber() > o.getArticle().getNumber())
            return 1;
        return -1;
    }

    @Override
    public String toString(){
        return article.getDescription() + " , " + quantity;
    }
}
