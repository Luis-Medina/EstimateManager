/*
 * Poste.java
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
public class Poste implements Serializable{
    
    private String name;
    private int page;
    private int height;
    private String clase;
    private double price;
    private double totalPrice;
    private ArrayList<Patron> patrones = new ArrayList<Patron>();
    private static final long serialVersionUID = 3L;
    
    /** Creates a new instance of Poste */
    public Poste(int alto, String clas, double precio) {
        price = precio;
        height = alto;
        clase = clas;
        totalPrice = price;
    }
    
    public Poste(String nombre, int pagina, int alto, String clas) {
        name = nombre;
        page = pagina;
        height = alto;
        clase = clas;
        totalPrice = price;
    }
    
    public Poste(String nombre, int pagina, int alto, String clas, double precio) {
        name = nombre;
        page = pagina;
        height = alto;
        clase = clas;
        price = precio;
        totalPrice = price;
    }
    
    public void addPatron(Patron p){
        if(patrones.size() == 0)
            totalPrice = price;
        totalPrice = totalPrice + p.getPrice();
        patrones.add(p);
    }
    
    public int getPage(){
        return page;
    }
    
    public String getName(){
        return name;
    }
    
    public void setHeight(int h){
        height = h;
        try{
            if(!clase.equals(null)){
                findPrice();
                if(totalPrice == 0)
                    totalPrice = price;
            }
        }
        catch(Exception e){}
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setPage(int num){
        page = num;
    }
    
    public String getClase(){
        return clase;
    }
    
    public void setClase(String c){
        clase = c;
        if(height != 0){
            findPrice();
            if(totalPrice == 0)
                totalPrice = price;
        }
    }
    
    public double getPrice(){
        return price;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    private void findPrice(){
        for(Poste p: StartWizard.postes)
            if(p.getHeight() == this.height && p.getClase().equalsIgnoreCase(this.clase))
                price = p.getPrice();
    }
       
    public double getTotalPrice() {
        double temp = 0;
        for(Patron p : patrones)
            temp += p.getPrice();
        return temp + price;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public ArrayList<Patron> getPatrones(){
        return patrones;
    }
    
    public void removeAllPatrones(){
        patrones = new ArrayList<Patron>();
    }
    
    public int getListSize(){
        return patrones.size();
    }
    
    public void setPatrones(ArrayList<Patron> p){
        patrones = p;
        totalPrice = price;
        for(Patron pat : p)
            totalPrice = totalPrice + pat.getPrice();
    }
    
}
