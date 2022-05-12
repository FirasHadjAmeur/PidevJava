/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.modeles;

/**
 *
 * @author HP
 */
public class Categorie {
    int id;
    String nom,fournisseur;

    
    
    
    public Categorie() {
    }
    

    public Categorie(Object id, Object nom, Object fournisseur) {
        this.id =(int) id;
        this.nom =(String) nom;
        this.fournisseur =(String) fournisseur;
    }

    public Categorie(Object nom, Object fournisseur) {
        this.nom =(String) nom;
        this.fournisseur =(String) fournisseur;
    }

    public Categorie(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
    

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom=" + nom + ", fournisseur=" + fournisseur + '}';
    }
    
}


