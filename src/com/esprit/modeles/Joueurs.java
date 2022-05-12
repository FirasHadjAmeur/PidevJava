/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.modeles;

/**
 *
 * @author LENOVO
 */
public class Joueurs {
     private int id,equipes_id,numero,nbr_partie_jouer;
private String nom,prenom,email,nom_equipe;

    public Joueurs() {
    }

  public Joueurs(int id,String nom, String prenom,int equipes_id,String email, int numero, int nbr_partie_jouer) {
        this.id=id;
        this.equipes_id = equipes_id;
        this.numero = numero;
        this.nbr_partie_jouer = nbr_partie_jouer;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Joueurs(String nom, String prenom,int equipes_id,String email, int numero, int nbr_partie_jouer) {
        this.equipes_id = equipes_id;
        this.numero = numero;
        this.nbr_partie_jouer = nbr_partie_jouer;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipes_id() {
        return equipes_id;
    }

    public void setEquipes_id(int equipes_id) {
        this.equipes_id = equipes_id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNbr_partie_jouer() {
        return nbr_partie_jouer;
    }

    public void setNbr_partie_jouer(int nbr_partie_jouer) {
        this.nbr_partie_jouer = nbr_partie_jouer;
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
    
    public String getNom_equipe() {
        return nom_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }


}
