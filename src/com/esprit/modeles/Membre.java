/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.modeles;

/**
 *
 * @author Faty
 */
public class Membre extends User {

    public Membre(int id_user, String nom_user, String prenom_user, String email_user, String mdp_user) {
        super(id_user, nom_user, prenom_user, email_user, mdp_user);
    }

    public Membre(String nom_user, String prenom_user, String email_user, String mdp_user) {
        super(nom_user, prenom_user, email_user, mdp_user);
    }

    public Membre() {
    }

    public Membre(String email_user, String mdp_user) {
        super(email_user, mdp_user);
    }

    public Membre(int id_user, String nom_user, String prenom_user, String email_user, String mdp_user, String pdp) {
        super(id_user, nom_user, prenom_user, email_user, mdp_user, pdp);
    }

    public Membre(String nom_user, String prenom_user, String email_user, String mdp_user, String pdp) {
        super(nom_user, prenom_user, email_user, mdp_user, pdp);
    }
    
    
}