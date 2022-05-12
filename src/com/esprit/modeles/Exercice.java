/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.modeles;

/**
 *
 * @author safwen
 */
public class Exercice {
    private int id;
    private String name;
    private int nbrset;
    private String photo;
    private int like_dislike;
    private int id_categorie_id;

    public Exercice() {
    }

    public Exercice(int id, String name, int nbrset, String photo, int like_dislike, int id_categorie_id) {
        this.id = id;
        this.name = name;
        this.nbrset = nbrset;
        this.photo = photo;
        this.like_dislike = like_dislike;
        this.id_categorie_id = id_categorie_id;
    }

    public Exercice(String nameee, int nbrsttt, String photott, int likeee, int idcat) {
        this.name = nameee;
        this.nbrset = nbrsttt;
        this.photo = photott;
        this.like_dislike = likeee;
        this.id_categorie_id = idcat;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNbrset() {
        return nbrset;
    }

    public String getPhoto() {
        return photo;
    }

    public int getLike_dislike() {
        return like_dislike;
    }

    public int getId_categorie_id() {
        return id_categorie_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNbrset(int nbrset) {
        this.nbrset = nbrset;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setLike_dislike(int like_dislike) {
        this.like_dislike = like_dislike;
    }

    public void setId_categorie_id(int id_categorie_id) {
        this.id_categorie_id = id_categorie_id;
    }

    @Override
    public String toString() {
        return "Exercice{" + "id=" + id + ", name=" + name + ", nbrset=" + nbrset + ", photo=" + photo + ", like_dislike=" + like_dislike + ", id_categorie_id=" + id_categorie_id + '}';
    }
    
    
}
