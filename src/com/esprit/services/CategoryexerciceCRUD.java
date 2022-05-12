/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.modeles.Categoryexercice;

import edu.db3a4.interfaces.ICategoryexercice;
import com.esprit.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sofie
 */
public class CategoryexerciceCRUD implements ICategoryexercice<Categoryexercice> {

    public ObservableList<Categoryexercice> observableListLocataire = FXCollections.observableArrayList();   
    
    @Override
    public void ajouterCategoryexercice(Categoryexercice t) {
         try {
             
             
            String requete = "INSERT INTO categoryexercice (name,description,photo,like_dislike)"
                    + "VALUES ('"+t.getName()+"','"+t.getDescription()+"','"+t.getPhoto()+"','"+t.getLike_dislike()+"')";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("categoryexercice ajoutée");
    
           
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCategoryexercice(Categoryexercice t) {
         try {
            String requete = "DELETE FROM categoryexercice where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Categoryexercice supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateCategoryexercice(Categoryexercice t) {
        try {
            String requete = "UPDATE Categoryexercice SET name=? , description=? ,photo=? , like_dislike=?  WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(5, t.getId());
            pst.setString(1, t.getName());
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getPhoto());
            pst.setInt(4, t.getLike_dislike());
           
            pst.executeUpdate();
            System.out.println("Categoryexercice modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Categoryexercice> displayCategoryexercice() {
              List<Categoryexercice> CategoryexerciceList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Categoryexercice";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Categoryexercice p = new Categoryexercice();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPhoto(rs.getString("photo"));
                p.setLike_dislike(rs.getInt("like_dislike"));
                
                        
                CategoryexerciceList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CategoryexerciceList;
    }
   
      public ObservableList<Categoryexercice> afficherCategoryexercice()
{
    try {
            
            String requete = "SELECT * from categoryexercice";
            Statement st;
            st = MyConnection.getInstance().getCnx()
                    .createStatement();
             ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                                                        
                observableListLocataire.add( new Categoryexercice(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
            }
    }
             catch (SQLException ex) {
            Logger.getLogger(CategoryexerciceCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          return observableListLocataire;
    
    
    
}
    
    
    public Categoryexercice getCategoryexercice(int id){
          Categoryexercice u =new Categoryexercice();
         try {
             String requete="select * from categoryexercice where id=?";
             PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
        
             pst.setInt(1, id );
             ResultSet rs=pst.executeQuery();
              while(rs.next()){
           
  u =new Categoryexercice(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getString("photo"),rs.getInt("like_dislike"))  ;
              ;}
         } catch (SQLException ex) {
             Logger.getLogger(CategoryexerciceCRUD.class.getName()).log(Level.SEVERE, null, ex);
         }
         return u;
         
     }
 
      public void modifierCategoryexercice2(String id, String name, String description ,String photo ,String like_dislike  ) {
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement("UPDATE categoryexercice SET id= '"+id+"', name= '"+name+"', description = '"+description+"', photo = '"+photo+"' , like_dislike = '"+like_dislike+"'   WHERE id = '"+id+"' ");
            pst.executeUpdate();
            System.out.println("Categoryexercice modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
