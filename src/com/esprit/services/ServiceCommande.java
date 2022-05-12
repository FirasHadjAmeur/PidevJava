/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.modeles.Commande;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
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
public class ServiceCommande implements IServices<Commande>{
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(Commande t) {
        try {
            String requete = "INSERT INTO commande (idcart,nom,prenom,email,adresse,numTelephone,date,totalCost) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdcart());
            pst.setString(2, t.getNom());
            pst.setString(3, t.getPrenom());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getAdresse());
            pst.setInt(6, t.getNumTelephone());
            pst.setDate(7, (Date) t.getDate());
            pst.setInt(8, t.getTotalCost());
            pst.executeUpdate();
            System.out.println("commande ajouter !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(Commande t) {
        try {
            String requete = "DELETE FROM commande WHERE commandeId=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getCommandeId());
            pst.executeUpdate();
            System.out.println("commande annuler !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public int deleteCommande(int id) throws SQLException {
        int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "DELETE FROM commande WHERE commandeId=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
   
    @Override
    public List<Commande> afficher() {
        List<Commande> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM commande";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Commande(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), rs.getInt(7),rs.getDate(8),rs.getInt(9))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

  
    public void modifier(Commande t) { }

    @Override
    public boolean add(Commande t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Commande t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Commande t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commande> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commande getOne(Commande t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commande getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   

}




