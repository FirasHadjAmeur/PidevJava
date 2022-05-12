/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.modeles.Cart;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azizm
 */
public class ServiceCartt implements IServices<Cart>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Cart t) {
          try {
            String requete = "INSERT INTO cart (quantity,productid) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getQuantity());
            pst.setString(2, t.getProductid());
            pst.executeUpdate();
            System.out.println("Produit Ajoutée a la Cart !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Cart t) {
     try{
        String requete = "DELETE FROM cart WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t.getId());
        pst.executeUpdate();
        System.out.println("Cart Supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
        public int deleteCart(int id) throws SQLException {
        int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "DELETE FROM cart WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCartt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public void modifier(Cart t) {
   
        try {
            String requete = "UPDATE cart SET quantity=?, WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getQuantity());
            pst.executeUpdate();
            System.out.println("Cart Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    @Override
    public List<Cart> afficher() {
          List<Cart> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM cart";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Cart(rs.getInt(1),rs.getString(2))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public boolean add(Cart t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Cart t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Cart t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cart> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cart getOne(Cart t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cart getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 

    }

