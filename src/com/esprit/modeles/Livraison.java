/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.modeles;

import java.util.Date;

/**
 *
 * @author azizm
 */
public class Livraison {
    private int livraisonId ;
    private int commandeId ;
      private String nom ; 
        private String prenom ;
        private String email;
        private String Adresse; 
        private int numTelephone;
	private String Date;
	private int totalCost;

    public Livraison() {
    }

    public Livraison(int livraisonId, int commandeId, String nom, String prenom, String email, String Adresse, int numTelephone, String Date, int totalCost) {
        this.livraisonId = livraisonId;
        this.commandeId = commandeId;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.Adresse = Adresse;
        this.numTelephone = numTelephone;
        this.Date = Date;
        this.totalCost = totalCost;
    }

    public Livraison(int commandeId, String nom, String prenom, String email, String Adresse, int numTelephone, String Date, int totalCost) {
        this.commandeId = commandeId;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.Adresse = Adresse;
        this.numTelephone = numTelephone;
        this.Date = Date;
        this.totalCost = totalCost;
    }

    public Livraison(int livraisonId, int commandeId, String nom, String prenom, String email, String Adresse, int numTelephone, int totalCost) {
        this.livraisonId = livraisonId;
        this.commandeId = commandeId;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.Adresse = Adresse;
        this.numTelephone = numTelephone;
        this.totalCost = totalCost;
    }

 

    public int getLivraisonId() {
        return livraisonId;
    }

    public void setLivraisonId(int livraisonId) {
        this.livraisonId = livraisonId;
    }

    public int getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(int commandeId) {
        this.commandeId = commandeId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public int getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(int numTelephone) {
        this.numTelephone = numTelephone;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Livraison{" + "livraisonId=" + livraisonId + ", commandeId=" + commandeId + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", Adresse=" + Adresse + ", numTelephone=" + numTelephone + ", Date=" + Date + ", totalCost=" + totalCost + '}';
    }

   
    
}