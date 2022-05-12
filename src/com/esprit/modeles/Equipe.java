/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.modeles;

/**
 *
 * @author LENOVO
 */
public class Equipe {
     private int id,nbr_vic,nbr_per,nbr_null,matchs_id,suspension;
     private String nom;

    public Equipe() {
    }


    public Equipe(int id,String nom,int nbr_vic, int nbr_per, int nbr_null, int suspension) {
        this.id=id;
        this.nbr_vic = nbr_vic;
        this.nbr_per = nbr_per;
        this.nbr_null = nbr_null;
        this.suspension = suspension;
        this.nom = nom;
    }

    public Equipe(String nom,int nbr_vic, int nbr_per, int nbr_null, int suspension) {
        this.nbr_vic = nbr_vic;
        this.nbr_per = nbr_per;
        this.nbr_null = nbr_null;
        this.suspension = suspension;
        this.nom = nom;
    }
public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbr_vic() {
        return nbr_vic;
    }

    public void setNbr_vic(int nbr_vic) {
        this.nbr_vic = nbr_vic;
    }

    public int getNbr_per() {
        return nbr_per;
    }

    public void setNbr_per(int nbr_per) {
        this.nbr_per = nbr_per;
    }

    public int getNbr_null() {
        return nbr_null;
    }

    public void setNbr_null(int nbr_null) {
        this.nbr_null = nbr_null;
    }

    public int getMatchs_id() {
        return matchs_id;
    }

    public void setMatchs_id(int matchs_id) {
        this.matchs_id = matchs_id;
    }

    public int getSuspension() {
        return suspension;
    }

    public void setSuspension(int suspension) {
        this.suspension = suspension;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
 

}
