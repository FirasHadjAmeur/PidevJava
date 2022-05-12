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
public class Categoryexercice {
    
    private int id;
    private String name;
    private String description;
    private String photo;
    private int like_dislike;

    public Categoryexercice() {
    }

    public Categoryexercice(int id, String name, String description, String photo, int like_dislike) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.like_dislike = like_dislike;
    }

    public Categoryexercice(String namecat, String desccat, String photocat, int likediscat) {
        this.name = namecat;
        this.description = desccat;
        this.photo = photocat;
        this.like_dislike = likediscat;
   //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getLike_dislike() {
        return like_dislike;
    }

    public void setLike_dislike(int like_dislike) {
        this.like_dislike = like_dislike;
    }

    @Override
    public String toString() {
        return "Categoryexercice{" + "id=" + id + ", name=" + name + ", description=" + description + ", photo=" + photo + ", like_dislike=" + like_dislike + '}';
    }
    
    
    
    
    
}
