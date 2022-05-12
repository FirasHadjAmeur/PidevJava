/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.utils;

import com.esprit.modeles.Cart;
import com.esprit.modeles.Commande;
import com.esprit.modeles.Livraison;
import com.esprit.modeles.Livreur;
import com.esprit.modeles.Products;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class DataSource {
    
    private Connection cnx;
    private String url = "jdbc:mysql://localhost:3306/pidev3";

    private String user = "root";
    private String password = "";
    
    private static DataSource instance;
    
    private DataSource() {
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DataSource getInstance() {
        if(instance == null)
            instance = new DataSource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
     public static Connection ConnectDb(){
        try {
            
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev3","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    public static ObservableList<Products> getDataProducts(){
        Connection conn = ConnectDb();
        ObservableList<Products> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from products");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Products(rs.getInt("id") ,rs.getString("title"),rs.getString("image"), rs.getFloat("price")));              
            }
        } catch (Exception e) {
        }
        return list;
    }
     
      public static ObservableList<Cart> getDataCart(){
        Connection conn = ConnectDb();
        ObservableList<Cart> list1 = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from cart");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list1.add(new Cart(rs.getInt("id") ,rs.getInt("quantity"),rs.getString("productid")));               
            }
        } catch (Exception e) {
        }
        return list1;
    }
        public static ObservableList<Livreur> getDataLivreur(){
        Connection conn = ConnectDb();
        ObservableList<Livreur> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from Livreur");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                 list.add(new Livreur(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5),rs.getString(6)));               
            }
        } catch (Exception e) {
        }
        return list;
    }
         public static ObservableList<Commande> getDataCommande(){
        Connection conn = ConnectDb();
        ObservableList<Commande> listC = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from commande");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                listC.add(new Commande(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), rs.getInt(7),rs.getDate(8),rs.getInt(9))); 
            }
        } catch (Exception e) {
        }
        return listC;
    }
            public static ObservableList<Livraison> getDataLivraison(){
        Connection conn = ConnectDb();
        ObservableList<Livraison> listL = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from livraison");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                listL.add(new Livraison(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), rs.getInt(7),rs.getString(8),rs.getInt(9))); 
            }
        } catch (Exception e) {
        }
        return listL;
    }
}
