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
public class Publication {
    
   private String id ;
   private String titre;
   private String contenu;
   private Timestamp date;
   private String image;
   private int idUser; 
   
   //constructeur par defaut
   public Publication(){
   }
   
   public Publication(String titreP ,String contenuP, String imageP){
    this.contenu = contenuP;
    this.titre = titreP;
    this.image = imageP;
   }

   public Publication(String idP, String titreP ,String contenuP, String imageP){
        this.id = idP;
        this.contenu = contenuP;
        this.titre = titreP;
        this.image = imageP;
   }
   
   //constructeur parametré
   public Publication(String titreP, String contenuP, Timestamp dateP, String imageP){
       this.titre = titreP;
       this.contenu = contenuP;
       this.date = dateP;
       this.image = imageP;
   }
   
    public Publication(String titreP, String contenuP, Timestamp dateP, String imageP, int idUser){
       this.titre = titreP;
       this.contenu = contenuP;
       this.date = dateP;
       this.image = imageP;
       this.idUser = idUser;
   }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
   
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    public Date getDate() {
        //return new Timestamp(new Date().getTime());
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
   
    @Override
    public String toString() {
        return "bonjour id = " + this.id;
    }
           
   
   
   
}

