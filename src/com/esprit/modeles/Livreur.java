/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.modeles;

/**
 *
 * @author azizm
 */
public class Livreur {
    private int idLivreur ;
    private String nomLivreur ; 
    private String prenomLivreur ; 
    private String email ;
    private String adresseLivreur ;
    private int numTelephoneLivreur ;

    public int getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(int idLivreur) {
        this.idLivreur = idLivreur;
    }

    public String getNomLivreur() {
        return nomLivreur;
    }

    public void setNomLivreur(String nomLivreur) {
        this.nomLivreur = nomLivreur;
    }

    public String getPrenomLivreur() {
        return prenomLivreur;
    }

    public void setPrenomLivreur(String prenomLivreur) {
        this.prenomLivreur = prenomLivreur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresseLivreur() {
        return adresseLivreur;
    }

    public void setAdresseLivreur(String adresseLivreur) {
        this.adresseLivreur = adresseLivreur;
    }

    public int getNumTelephoneLivreur() {
        return numTelephoneLivreur;
    }

    public void setNumTelephoneLivreur(int numTelephoneLivreur) {
        this.numTelephoneLivreur = numTelephoneLivreur;
    }

    public Livreur(String nomLivreur, String prenomLivreur, String email,  int numTelephoneLivreur,String adresseLivreur) {
        this.nomLivreur = nomLivreur;
        this.prenomLivreur = prenomLivreur;
        this.email = email;
         this.numTelephoneLivreur = numTelephoneLivreur;
        this.adresseLivreur = adresseLivreur;
    }

    public Livreur(int idLivreur, String nomLivreur, String prenomLivreur, String email,int numTelephoneLivreur, String adresseLivreur ) {
        this.idLivreur = idLivreur;
        this.nomLivreur = nomLivreur;
        this.prenomLivreur = prenomLivreur;
        this.email = email;
        
        this.numTelephoneLivreur = numTelephoneLivreur;
        this.adresseLivreur = adresseLivreur;
    }

    public Livreur() {
    }

    @Override
    public String toString() {
        return "Livreur{" + "idLivreur=" + idLivreur + ", nomLivreur=" + nomLivreur + ", prenomLivreur=" + prenomLivreur + ", email=" + email + ", adresseLivreur=" + adresseLivreur + ", numTelephoneLivreur=" + numTelephoneLivreur + '}';
    }

    
    
    
}