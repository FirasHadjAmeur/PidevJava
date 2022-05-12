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
public class Matchs {
    
    private int id,tournoi_id,equipe1_id,equipe2_id;
    private Date date_match;
    private String ref_match;
    private int score_a,score_b;
    private String nom_tournoi,nom_equipe1,nom_equipe2;
    private int favori;

    /**
     *
     * @param id
     * @param tournoi_id
     * @param equipe1_id
     * @param equipe2_id
     * @param date_match
     * @param ref_match
     * @param score_a
     * @param score_b

     */
    
    public Matchs() {
    }

    public Matchs(Object tournoi_id, Object equipe1_id, Object equipe2_id, Object date_match, Object ref_match,Object score_a, Object score_b) {
        this.tournoi_id = (int) tournoi_id;
        this.equipe1_id = (int) equipe1_id;
        this.equipe2_id = (int) equipe2_id;
        this.date_match = (Date) date_match;
        this.ref_match =(String) ref_match;
        this.score_a = (int) score_a;
        this.score_b = (int) score_b;

    }

            
    public Matchs(Object id, Object tournoi_id, Object equipe1_id, Object equipe2_id, Object date_match, Object ref_match,Object score_a, Object score_b) {
        this.id = (int) id;
        this.tournoi_id = (int) tournoi_id;
        this.equipe1_id = (int) equipe1_id;
        this.equipe2_id = (int) equipe2_id;
        this.date_match = (Date) date_match;
        this.ref_match =(String) ref_match;
        this.score_a = (int) score_a;
        this.score_b = (int) score_b;

    }

  
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTournoi_id() {
        return tournoi_id;
    }

    public void setTournoi_id(int tournoi_id) {
        this.tournoi_id = tournoi_id;
    }

    public int getEquipe1_id() {
        return equipe1_id;
    }

    public void setEquipe1_id(int equipe1_id) {
        this.equipe1_id = equipe1_id;
    }

    public int getEquipe2_id() {
        return equipe2_id;
    }

    public void setEquipe2_id(int equipe2_id) {
        this.equipe2_id = equipe2_id;
    }
    public Date getDate_match() {
        return date_match;
    }

    public void setDate_match(Date date_match) {
        this.date_match = date_match;
    }
    
    public String getRef_match() {
        return ref_match;
    }

    public void setRef_match(String ref_match) {
        this.ref_match = ref_match;
    }

    public int getScore_a() {
        return score_a;
    }

    public void setScore_a(int score_a) {
        this.score_a = score_a;
    }

    public int getScore_b() {
        return score_b;
    }

    public void setScore_b(int score_b) {
        this.score_b = score_b;
    }

    public String getNom_tournoi() {
        return nom_tournoi;
    }

    public void setNom_tournoi(String nom_tournoi) {
        this.nom_tournoi = nom_tournoi;
    }

    public String getNom_equipe1() {
        return nom_equipe1;
    }

    public void setNom_equipe1(String nom_equipe1) {
        this.nom_equipe1 = nom_equipe1;
    }

    public String getNom_equipe2() {
        return nom_equipe2;
    }

    public void setNom_equipe2(String nom_equipe2) {
        this.nom_equipe2 = nom_equipe2;
    }

    public int getFavori() {
        return favori;
    }

    public void setFavori(int favori) {
        this.favori = favori;
    }

    
    
    
    @Override
    public String toString() {
        return "Matchs{" + "id=" + id + ", tournoi_id=" + tournoi_id + ", equipe1_id=" + equipe1_id + ", equipe2_id=" + equipe2_id + ", date_match=" + date_match + ", ref_match=" + ref_match + ", score_a=" + score_a + ", score_b=" + score_b + '}';
    }


    
}
