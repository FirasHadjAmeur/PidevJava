/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.utils.MaConnexion;
import com.esprit.modeles.Commentaire;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class CommentaireService {
    
    Connection cnx ;
    
    public CommentaireService(){
        //ouvrir une connection a la base de données
        cnx = MaConnexion.getInstance().getCnx();
    }
    
    public void create(Commentaire c){
        
        String query = "insert into commentaire(contenu,date_commentaire,publication_id, idUser) values('" 
                + c.getContenu() + "','" + c.getDate() + "','" + c.getIdPublication() + "','" + 1 + "')";
        
        Statement ste;
        try { 
            //executer la requette
            ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("commentaire Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }  
    
    public void update(Commentaire c){
        
        String query = "update commentaire set contenu = '" + c.getContenu() + "', date_commentaire = '" 
                + c.getDate() + "' where id = '" + c.getId() + "'"; 
             
        Statement ste;
        try { 
            //executer la requette
            ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("commentaire Modifiée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void delete(int id, String idPub){
        
        String query = "DELETE FROM commentaire WHERE id = '" + id + "' and publication_id = '" + idPub + "'";
        
        Statement ste;
        try { 
            //executer la requette
            ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("commentaire supprimée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public void deleteAll(String idPub){
        
        String query = "DELETE FROM commentaire WHERE publication_id = '" + idPub + "'";
        
        Statement ste;
        try { 
            //executer la requette
            ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("commentaires supprimée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Commentaire> displayAllByPublicationByDate(int idPublication){
        
        List<Commentaire> commentaireList = new ArrayList<>(); //instanciation
        String sql = "select * from commentaire where publication_id = '" + idPublication + "order by date_commentaire DESC'";
               // + idPublication + " order by date_commentaire DESC'";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Commentaire c = new Commentaire();
                c.setId(rs.getInt("id"));
                c.setContenu(rs.getString("contenu"));
                c.setDate(rs.getTimestamp(4));
                
                commentaireList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("liste de commentaires : " + commentaireList);
        return commentaireList;
    }
    
}
