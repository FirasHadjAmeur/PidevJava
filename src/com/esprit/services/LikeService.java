/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.utils.MaConnexion;
import com.esprit.modeles.Like;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class LikeService {
    
     
    Connection cnx ;
    
    public LikeService(){
        //ouvrir une connection a la base de données
        cnx = MaConnexion.getInstance().getCnx();
    }
    
    public void create(Like l){
        
        String query = "insert into `like`(rate,publication_id) values('" 
                + l.getRate() + "','" + l.getIdPublication() + "')";
        
        Statement ste;
        try { 
            //executer la requette
            ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("like ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }  
    
    public void delete(int id){
        
        String query = "DELETE FROM `like` WHERE id = '" + id + "'";
        
        Statement ste;
        try { 
            //executer la requette
            ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("like supprimée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void deleteAll(String idPub){
        
        String query = "DELETE FROM `like` WHERE publication_id = '" + idPub + "'";
        
        Statement ste;
        try { 
            //executer la requette
            ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("likes supprimée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int countLike(String idPublication){
         
        int count = 0;
        String query = "SELECT COUNT(*) FROM `like` WHERE rate = 1 and publication_id = '" 
                + idPublication + "'";
        
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("nombre like egale : " + count + " pour la publication : " + idPublication);
        return count;
        
    }
    
    public int countDislike(String idPublication){
         
        int count = 0;
        String query = "SELECT COUNT(*) FROM `like` WHERE rate = 0 and publication_id = '" 
                + idPublication + "'";
        
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("nombre dislike egale : " + count);
        return count;
    }
}
