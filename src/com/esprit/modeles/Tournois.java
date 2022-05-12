/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.modeles;

import java.util.Date;

/**
 *
 * @author Dragon
 */
public class Tournois {
    private int id;
    private String nom;
    private Date date_debut,date_fin;
    private int prime;
    
    /**
     *
     * @param id
     * @param prime
     * @param nom
     * @param <error>
     * @param date_debut
     * @param date_fin
     */
    public Tournois() {
    }

    
    public Tournois (Object nom, Object date_debut, Object date_fin,Object prime) {
       
        this.nom = (String) nom;
        this.date_debut = (Date) date_debut;
        this.date_fin = (Date) date_fin;
         this.prime = (int) prime;
    }

    public Tournois(Object id, Object nom, Object date_debut, Object date_fin, Object prime) {
        this.id = (int) id;
        this.prime = (int) prime;
        this.nom = (String) nom;
        this.date_debut = (Date) date_debut;
        this.date_fin = (Date) date_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrime() {
        return prime;
    }

    public void setPrime(int prime) {
        this.prime = prime;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

  

  

   
    
}
