/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.esprit.modeles.Categorie;
import com.esprit.utils.DataSource;

/**
 *
 * @author HP
 */
public class ServiceCategorie implements IServices<Categorie> {

    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public boolean add(Categorie t) {
        String query = "INSERT INTO categoriem (`nom`, `fournisseur`) VALUES(?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
           //  ps.setObject(1, t.getId());
           // ps.setObject(1, t.getNom());
            ps.setObject(1, t.getNom());
            ps.setObject(2, t.getFournisseur());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Categorie t) {
String query = "UPDATE categoriem SET nom=?, fournisseur=?  WHERE id =?";


        try {
            PreparedStatement ps = cnx.prepareStatement(query);
         
            ps.setObject(1, t.getNom());
            ps.setObject(2, t.getFournisseur());
            
            ps.setObject(3, t.getId());
            

           
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;    }

    @Override
    public boolean delete(Categorie t) {
        String sql = "DELETE FROM categoriem WHERE id  = ?";

        try {
            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setObject(1 , t.getId());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    
   
    
    

    @Override
    public List<Categorie> getAll() {
        List<Categorie> list = new ArrayList<>();
        String query = "Select * from categoriem";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                Categorie m = new Categorie(rs.getObject(1),rs.getObject(2),rs.getObject(3));
                list.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Categorie getOne(Categorie t) {
        String query = "select * from categoriem where id=" + t.getId();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Categorie p = new Categorie(rs.getObject(1), rs.getObject(2), rs.getObject(3));
                return p;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Categorie();
    }

    
   
    @Override
    public Categorie getById(int id) {
        String query = "select * from categoriem where id=" + id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Categorie p = new Categorie(rs.getObject(1), rs.getObject(2), rs.getObject(3));
                return p;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Categorie();
    }

    @Override
    public void ajouter(Categorie t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Categorie t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Categorie t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
