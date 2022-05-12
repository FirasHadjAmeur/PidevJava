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
public class Products {
    private int id;
    private String title;
    private float price ;
    private String image; 

    public Products(String title, float price) {
        this.title = title;
        this.price = price;
    }

    public Products() {
    }

    public Products(String title, float price, String image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public Products(int id, String title,  String image,float price) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
    }

  
    

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Products{" + "id=" + id + ", title=" + title + ", price=" + price + ", image=" + image + '}';
    }

  
    
}
