/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.modeles.Equipe;
import com.esprit.utils.Connexion;
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
 * @author LENOVO
 */
public class EquipeService {
  private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

  public EquipeService() {
         cnx = Connexion.getInstance().getCnx();
    }

      public void AddEquipe(Equipe e) throws SQLException{
         
              String req = "insert into equipes (nom, nbr_vic, nbr_per,nbr_null,suspension) values (?,?,?,?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              
              pst.setString(1, e.getNom());
              pst.setInt(2, e.getNbr_vic());
              pst.setInt(3, e.getNbr_per());
              pst.setInt(4, e.getNbr_null());
              pst.setInt(5, e.getSuspension());
              //pst.setDate(5, (Date) c.getTime());
           
              pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);
          }
     }

  public void DeleteEquipe(int e){
          try {
              String req = "DELETE from equipes  WHERE id =" +e+ " ";
              
              ste = cnx.createStatement();
              ste.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(EquipeService.class.getName()).log(Level.SEVERE, null, ex);}
          }

     public void UpdateEquipe(Equipe e,int cu)
        { String req ="UPDATE equipes set nom=? , nbr_vic=? , nbr_per=?, nbr_null=?, suspension=? WHERE id =" +cu+ " ";
        try {
              pst = cnx.prepareStatement(req);             
               pst.setString(1, e.getNom());
              pst.setInt(2, e.getNbr_vic());
              pst.setInt(3, e.getNbr_per());
              pst.setInt(4, e.getNbr_null());
              pst.setInt(5, e.getSuspension());
              
              
              pst.executeUpdate();
              System.out.println("Equipe modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } 
        }

     public List<Equipe> ShowEquipe()throws SQLException{
        List<Equipe> equipe = new ArrayList<>();
        String sql="select * from equipes";
        Statement ste;
  
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Equipe c = new Equipe();
                 c.setId(rs.getInt("id"));
                 c.setNom(rs.getString("nom"));
                 c.setNbr_vic(rs.getInt("nbr_vic"));
                c.setNbr_null(rs.getInt("nbr_null"));
                c.setNbr_per(rs.getInt("nbr_per"));
                c.setSuspension(rs.getInt("suspension"));
//                 int cate =rs.getInt("categorie");
//System.out.println(rs.getInt("category_id")+"ghv");
//                 String sql1="select * from categorie WHERE id="+rs.getInt("category_id");
//Statement ste1;
//                ste1 = cnx.createStatement();
//                 ResultSet rs1 = ste.executeQuery(sql1);
//  while(rs1.next())
//               { c.setCategorie(rs1.getString("type"));
//
//                }
                 equipe.add(c);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return equipe;
    }
    
}
