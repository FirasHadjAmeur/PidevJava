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
public class Like {
    
    private int id;
    private int rate;
    private String idPublication;
    
    public Like(){
   }

   //constructeur parametré
   public Like(int rate){
       this.rate = rate;
   }
   
   public Like(int rate, String idPublication){
       this.rate = rate;
       this.idPublication = idPublication;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
    
    public String getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(String idPublication) {
        this.idPublication = idPublication;
    }

    @Override
    public String toString() {
        return "bonsoir id = " + this.id;
    }

    
    
}
