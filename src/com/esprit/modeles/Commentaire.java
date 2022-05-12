/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.modeles;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author HP
 */
public class Commentaire {
    
    private int id;
    private String contenu;
    private Date date;
    private int idPublication;
    
    public Commentaire(){
   }

   //constructeur parametré
   public Commentaire(int idC, String contenuC, Timestamp dateC){
       this.id = idC;
       this.contenu = contenuC;
       this.date = dateC;
   }

   public Commentaire(String contenuC, Timestamp dateC, int idPublication){
       this.contenu = contenuC;
       this.date = dateC;
       this.idPublication = idPublication;   
   }
   
   public Commentaire(String contenuC, int idPublication){
       this.contenu = contenuC;
       this.idPublication = idPublication;   
   }
   
   public Commentaire(String contenuC, Timestamp dateC){
       this.contenu = contenuC;
       this.date = dateC;
   }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    public Date getDate() {
        return new Timestamp(new Date().getTime());
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }
    
    @Override
    public String toString() {
        return "bonsoir id = " + this.id;
    }
    
    
}



