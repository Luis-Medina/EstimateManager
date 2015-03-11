/*
 * Finders.java
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
public class Finders {
    
    public static Patron findPatron(String text){
        Patron p = new Patron("Patron incorrecto");
        for(int i=0; i<StartWizard.patrones.size(); i++){
            if(text.equalsIgnoreCase(StartWizard.patrones.get(i).getNumber())){
                p = StartWizard.patrones.get(i);
            }
        }
        return p;
    }
    
    public static Articulo findArticulo(int text){
        Articulo a = new Articulo(0, "N/A", "N/A", 0);
        for(int i=0; i<StartWizard.articulos.size(); i++){
            if(text == StartWizard.articulos.get(i).getNumber()){
                a = StartWizard.articulos.get(i);
            }
        }
        return a;
    }
    
    public static Material findMaterial(String name){
        Material m = new Material("Patron incorrecto", 0);
        for(int i=0; i<StartWizard.materiales.size(); i++){
            if(name.equalsIgnoreCase(StartWizard.materiales.get(i).getName())){
                m = StartWizard.materiales.get(i);
            }
        }
        return m;
    }
    
    public static Poste findPoste(String clase, int alto){
        Poste pdb = new Poste(0, "N/A", 0);
        for (Poste p: StartWizard.postes){
            if(p.getClase().equals(clase) && p.getHeight() == alto)
                pdb = p;
        }
        return pdb;
    }
    
    
}
