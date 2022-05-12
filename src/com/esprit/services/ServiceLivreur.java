/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.modeles.Livreur;
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

   
public class ServiceLivreur implements IServices<Livreur>{
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(Livreur t) {
        try {
            String requete = "INSERT INTO livreur (nomLivreur, prenomLivreur,email,numTelephoneLivreur,adresseLivreur) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNomLivreur());
            pst.setString(2, t.getPrenomLivreur());
            pst.setString(3, t.getEmail());
            pst.setInt(4, t.getNumTelephoneLivreur());
            pst.setString(5, t.getAdresseLivreur());
            
            pst.executeUpdate();
            System.out.println("Livreur Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(Livreur t) {
        try {
            String requete = "DELETE FROM livreur WHERE idLivreur=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdLivreur());
            pst.executeUpdate();
            System.out.println("Livreur Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public int deleteLivreur(int idLivreur) throws SQLException {
        int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "DELETE FROM livreur WHERE idLivreur=" + idLivreur;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public void modifier(Livreur t) {
        try {
            String requete = "UPDATE livreur SET `nomLivreur`=?, `prenomLivreur`=? , `email`=?, `adresseLivreur`=?, `numTelephoneLivreur`=? WHERE `idLivreur`=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
              
            pst.setString(1, t.getNomLivreur());
            pst.setString(2, t.getPrenomLivreur());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getAdresseLivreur());
            pst.setInt(5, t.getNumTelephoneLivreur());
            pst.setInt(6, t.getIdLivreur());
            pst.executeUpdate();
            System.out.println("Livreur Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Livreur> afficher() {
        List<Livreur> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM livreur";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Livreur(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5),rs.getString(6))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public boolean add(Livreur t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Livreur t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Livreur t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Livreur> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Livreur getOne(Livreur t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Livreur getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

}


