/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;


import com.esprit.modeles.Exercice;
import edu.db3a4.interfaces.IExercice;
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
public class ExerciceCRUD implements IExercice<Exercice>{
    
    public ObservableList<Exercice> observableListLocataire = FXCollections.observableArrayList();   

    @Override
    public void ajouterExercice(Exercice t) {
        try {
            String requete = "INSERT INTO exercice (name,nbrset,photo,like_dislike,id_categorie_id)"
                    + "VALUES ('"+t.getName()+"','"+t.getNbrset()+"','"+t.getPhoto()+"','"+t.getLike_dislike()+"','"+t.getId_categorie_id()+"')";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("Exercice ajoutée");
    
           
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerExercice(Exercice t) {
        try {
            String requete = "DELETE FROM exercice where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Exercice supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateExercice(Exercice t) {
       try {
            String requete = "UPDATE Exercice SET name=? , nbrset=? ,photo=? , like_dislike=? , id_category_id=? WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(6, t.getId());
            pst.setString(1, t.getName());
            pst.setInt(2, t.getNbrset());
            pst.setString(3, t.getPhoto());
            pst.setInt(4, t.getLike_dislike());
            pst.setInt(5, t.getId_categorie_id());
           
            pst.executeUpdate();
            System.out.println("Exercice modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Exercice> displayExercice() {
         List<Exercice> exerciceList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM Exercice";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Exercice p = new Exercice();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setNbrset(rs.getInt("nbrset"));
                p.setPhoto(rs.getString("photo"));
                p.setLike_dislike(rs.getInt("like_dislike"));
                p.setId_categorie_id(rs.getInt("id_category_id"));
                        
                exerciceList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return exerciceList;
    }
    
    
    public ObservableList<Exercice> afficherExercice()
{
    try {
            
            String requete = "SELECT * from exercice";
            Statement st;
            st = MyConnection.getInstance().getCnx()
                    .createStatement();
             ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                                                        
                observableListLocataire.add( new Exercice(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
            }
    }
             catch (SQLException ex) {
            Logger.getLogger(ExerciceCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          return observableListLocataire;
    
    
    
}
    
    
    public Exercice getExercice(int id){
          Exercice u =new Exercice();
         try {
             String requete="select * from exercice where id=?";
             PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
        
             pst.setInt(1, id );
             ResultSet rs=pst.executeQuery();
              while(rs.next()){
           
  u =new Exercice(rs.getInt("id"),rs.getString("name"),rs.getInt("nbrset"),rs.getString("photo"),rs.getInt("like_dislike"),rs.getInt("id_categorie_id"))  ;
              ;}
         } catch (SQLException ex) {
             Logger.getLogger(ExerciceCRUD.class.getName()).log(Level.SEVERE, null, ex);
         }
         return u;
         
     }
 
      public void modifierExercice2(String id, String name, String nbrset ,String photo ,String like_dislike , String id_category_id ) {
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement("UPDATE exercice SET  name= '"+name+"', nbrset = '"+nbrset+"', photo = '"+photo+"' , like_dislike = '"+like_dislike+"' ,  id_categorie_id = '"+id_category_id+"'  WHERE id = '"+id+"' ");
            pst.executeUpdate();
            System.out.println("Exercice modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
