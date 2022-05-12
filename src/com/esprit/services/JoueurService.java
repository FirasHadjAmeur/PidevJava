/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.modeles.Equipe;
import com.esprit.modeles.Joueurs;
import com.esprit.utils.Connexion;
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
 * @author LENOVO
 */
public class JoueurService {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public JoueurService() {
        cnx = Connexion.getInstance().getCnx();
    }

   public void AddJoueur(Joueurs j) throws SQLException{
         
              String req = "insert into joueurs (equipes_id,nom, prenom, email,numero,nbr_partie_jouer) values (?,?,?,?,?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);

              pst.setInt(1, j.getEquipes_id());
              pst.setString(2, j.getNom());
              pst.setString(3, j.getPrenom());
              pst.setString(4, j.getEmail());
              pst.setInt(5, j.getNumero());
              pst.setInt(6, j.getNbr_partie_jouer());
             
              //pst.setDate(5, (Date) c.getTime());
           
              pst.executeUpdate();
            System.out.println("done");
          } catch (SQLException ex) {
              Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
          }
     }
     public void DeleteJoueur(int e){
          try {
              String req = "DELETE from joueurs  WHERE id =" +e+ " ";
              
              ste = cnx.createStatement();
              ste.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);}
          }

     public void UpdateJoueur(Joueurs j,int cu)
        { String req ="UPDATE joueurs set equipes_id=? , nom=? , prenom=?, email=?, numero=?, nbr_partie_jouer=? WHERE id =" +cu+ " ";
        try {
              pst = cnx.prepareStatement(req);             
              pst.setInt(1, j.getEquipes_id());
              pst.setString(2, j.getNom());
              pst.setString(3, j.getPrenom());
              pst.setString(4, j.getEmail());
              pst.setInt(5, j.getNumero());
              pst.setInt(6, j.getNbr_partie_jouer());
              
               System.out.println(j.getEquipes_id());
              pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } 
        }

public List<Joueurs> ShowJoueur()throws SQLException{
        List<Joueurs> joueurs = new ArrayList<>();
        String sql="select * from joueurs";
        Statement ste;
  
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Joueurs j = new Joueurs();
                 j.setId(rs.getInt("id"));
                 j.setEquipes_id(rs.getInt("equipes_id"));
                 j.setNom(rs.getString("nom"));
                 j.setPrenom(rs.getString("prenom"));
                 j.setEmail(rs.getString("email"));
                 j.setNumero(rs.getInt("numero"));
                j.setNbr_partie_jouer(rs.getInt("nbr_partie_jouer")); 
                 System.out.println("vjh"+rs.getInt("nbr_partie_jouer"));
                 joueurs.add(j);
                 }
        
        return joueurs;
    }

}
