/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.modeles.Matchs;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dragon
 */
public class ServiceMatchs implements IIService<Matchs> {

     Connection cnx = DataSource.getInstance().getCnx();
     
    @Override
    public boolean add(Matchs t) {
       String query = "INSERT INTO `matchs`(`tournoi_id`, `equipe1_id`, `equipe2_id`, `date_match`, `ref_match`, `score_a`, `score_b`) VALUES (?,?,?,?,?,?,?)";   
      
    
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
          
          ps.setObject(1, t.getTournoi_id());
          ps.setObject(2, t.getEquipe1_id());
          ps.setObject(3, t.getEquipe2_id());
            ps.setObject(4, t.getDate_match());
            ps.setObject(5, t.getRef_match());
            ps.setObject(6, t.getScore_a());
            ps.setObject(7, t.getScore_b());
 
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true; 
    }

    @Override
    public boolean update(Matchs t) {
        String sql = "UPDATE `matchs` SET `tournoi_id`=?,`equipe1_id`=?,`equipe2_id`=?,`date_match`=?,`ref_match`=?,`score_a`=?,`score_b`=? WHERE id =?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            
            ps.setObject(1, t.getTournoi_id()); 
            ps.setObject(2, t.getEquipe1_id()); 
            ps.setObject(3, t.getEquipe2_id()); 
            ps.setObject(4, t.getDate_match()); 
            ps.setObject(5, t.getRef_match());
            ps.setObject(6, t.getScore_a());
            ps.setObject(7, t.getScore_b());
            ps.setObject(8, t.getId());
            
            System.out.println("updated");
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Matchs t) {
        String sql = "DELETE FROM `matchs` WHERE id=?";
        
        try {
            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setObject(1, t.getId());

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
    public List<Matchs> getAll() {
        List<Matchs> list = new ArrayList<>();
        String query = "SELECT * FROM `matchs`";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
 

            Matchs matc = new Matchs(rs.getObject(1),rs.getObject(2),rs.getObject(3),rs.getObject(4),rs.getObject(5),rs.getObject(6),rs.getObject(7),rs.getObject(8));
            list.add(matc);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;   
    }

    @Override
    public Matchs getOne(Matchs t) {
        String query = "SELECT * FROM `matchs` WHERE id='" + t.getId()+"'";
        try {
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                                                         
                Matchs matc = new Matchs(rs.getObject(1),rs.getObject(2),rs.getObject(3),rs.getObject(4),rs.getObject(5),rs.getObject(6),rs.getObject(7),rs.getObject(8));
                return matc;
            } 
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return new Matchs();
    }

    @Override
    public Matchs getById(int id) {
         String query = "SELECT * FROM `matchs` WHERE id='" + id+"'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Matchs tou = new Matchs(rs.getObject(1), rs.getObject(2),rs.getObject(3), rs.getObject(4),rs.getObject(5),rs.getObject(6),rs.getObject(7),rs.getObject(8));
                return tou;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Matchs();
    }
    
}
