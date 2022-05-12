/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.modeles;

import java.util.Date;

/**
 *
 * @author azizm
 */
public class Cart {
	
	
	private int id;
	private int quantity;
	private String productid;
       

    public Cart() {
    }

    public Cart(int quantity, String productid) {
        this.quantity = quantity;
        this.productid = productid;
    }

    public Cart(int id, int quantity, String productid) {
        this.id = id;
        this.quantity = quantity;
        this.productid = productid;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", quantity=" + quantity + ", productid=" + productid + '}';
    }
    
        
        
	

}

