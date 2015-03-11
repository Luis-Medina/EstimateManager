/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package omarproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import segundaFase.SiteAdmin;

/**
 *
 * @author Luiso
 */
public class Proyecto implements Serializable {
    
    private String name;
    private Date dateStarted;
    private Date dateEnded;
    private double cost;
    private ArrayList<Poste> posts;
    private PatronLine[] projectArticleCount;
    private String localization;
    private SiteAdmin site;
    
    public Proyecto(String name) {
        this.name = name;
    }

    public SiteAdmin getSite() {
        return site;
    }

    public void setSite(SiteAdmin site) {
        this.site = site;
    }
    
    private static final long serialVersionUID = 3L;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(Date dateEnded) {
        this.dateEnded = dateEnded;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Poste> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Poste> posts) {
        this.posts = posts;
    }

    public PatronLine[] getProjectArticleCount() {
        return projectArticleCount;
    }

    public void setProjectArticleCount(PatronLine[] projectArticleCount) {
        this.projectArticleCount = projectArticleCount;
    }
    

}
