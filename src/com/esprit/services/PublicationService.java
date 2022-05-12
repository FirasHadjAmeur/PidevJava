/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;


import com.mysql.jdbc.PreparedStatement;
import com.esprit.utils.MaConnexion;
import com.esprit.modeles.Publication;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class PublicationService {
   
    Connection cnx ;
    
    public PublicationService(){
        //ouvrir une connection a la base de données
        cnx = MaConnexion.getInstance().getCnx();
    }
    
    public void create(Publication p){
        
        String query = "insert into publication(titre_pub, contenu_pub, date_pub, image_pub, idUser) values('" 
                + p.getTitre() + "','" + p.getContenu() + "','" + new Timestamp(new Date().getTime()) + "','" 
                + p.getImage() + "','" + 1 + "')";
        
        Statement ste;
        try { 
            //executer la requette
            ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("publication Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }  
    
    public void update(Publication p){
        
        String query = "update publication set titre_pub = '" + p.getTitre() + "', contenu_pub = '" 
                + p.getContenu() + "', date_pub = '" + new Timestamp(new Date().getTime()) + "' where id = '" + p.getId() + "'"; 
             
        Statement ste;
        try { 
            //executer la requette
            ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("publication Modifiée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void delete(String id){
        
        CommentaireService cs = new CommentaireService();
        cs.deleteAll(id);
        
        LikeService ls = new LikeService();
        ls.deleteAll(id);
        
        String query = "DELETE FROM publication WHERE id = '" + id + "'";
        
        Statement ste;
        try { 
            //executer la requette
            ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("publication supprimée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ObservableList<Publication> displayAllByUser(int idUser){
        
        ObservableList<Publication> publicationList = FXCollections.observableArrayList();
        String sql = "select * from publication where idUser = '" + idUser + "'";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Publication p = new Publication();
                p.setId(rs.getString("id"));
                p.setTitre(rs.getString(2));
                p.setContenu(rs.getString("contenu_pub"));
                p.setDate(rs.getTimestamp(4));
                p.setImage(rs.getString("image_pub"));
                
                publicationList.add(p);
                System.out.println(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("liste de publications : " + publicationList);
        return publicationList;
    }
    
    public ObservableList<Publication> displayAll(){
        
        ObservableList<Publication> publicationList = FXCollections.observableArrayList();
        String sql = "select * from publication";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Publication p = new Publication();
                p.setId(rs.getString("id"));
                p.setTitre(rs.getString(2));
                p.setContenu(rs.getString("contenu_pub"));
                p.setDate(rs.getTimestamp(4));
                p.setImage(rs.getString("image_pub"));
                
                publicationList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("liste de publications : " + publicationList);
        return publicationList;
    }
    
    public ObservableList<Publication> sortByDate(){
        
        ObservableList<Publication> sortedList = FXCollections.observableArrayList();
        String query = "SELECT * FROM `publication` ORDER BY date_pub DESC";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while(rs.next()){
                Publication p = new Publication();
                p.setId(rs.getString("id"));
                p.setTitre(rs.getString(2));
                p.setContenu(rs.getString("contenu_pub"));
                p.setDate(rs.getTimestamp(4));
                p.setImage(rs.getString("image_pub"));
                
                sortedList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("liste de publication triée par date : " + sortedList);
        return sortedList;
    }
    
    public ObservableList<Publication> getPublicationByTitle(String title) {
        
        ObservableList<Publication> list = FXCollections.observableArrayList();
        
        try {
            String sql = "SELECT * FROM publication where titre_pub like '%" + title + "%' ";
            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Publication p = new Publication();
                p.setId(rs.getString("id"));
                p.setTitre(rs.getString(2));
                p.setContenu(rs.getString("contenu_pub"));
                p.setDate(rs.getTimestamp(4));
                p.setImage(rs.getString("image_pub"));

                list.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
    
}

