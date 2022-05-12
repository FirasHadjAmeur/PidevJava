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
public class Materiels {
    int id,qte;
    String nom;
    int categoriem_id;
    String Nom_Categorie;

    public Materiels() {
    }

   /* public Materiels(Object id, Object categoriem_id, Object nom, Object qte) {
        this.id =(int) id;
        this.qte =(int) qte;
        this.nom =(String) nom;
        this.categoriem_id =(int) categoriem_id;
    }*/
    
    public Materiels(Object id, Object Nom_Categorie, Object nom, Object qte) {
        this.id =(int) id;
        this.qte =(int) qte;
        this.nom =(String) nom;
        this.Nom_Categorie =(String) Nom_Categorie;
    }

    /*public Materiels(int id, int qte, String nom) {
        this.id = id;
        this.qte = qte;
        this.nom = nom;
    }


    public Materiels(Object qte, Object nom) {
        this.qte =(int) qte;
        this.nom =(String) nom;
    } */ 
    
    public Materiels(Object  qte, Object nom, Object categoriem_id) {
        this.qte =(int) qte;
        this.nom =(String) nom;
        this.categoriem_id =(int) categoriem_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCategoriem_id() {
        return categoriem_id;
    }

    public void setCategoriem_id(int categoriem_id) {
        this.categoriem_id = categoriem_id;
    }

    @Override
    public String toString() {
        return   "\t" + nom + "\t\t\t\t\t\t" + qte + "\t\t\t\t\t" + Nom_Categorie  ;
    }

    
}


