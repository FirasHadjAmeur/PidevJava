/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.modeles.Tournois;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dragon
 */
public class ServiceTournois implements IIService<Tournois>{

    Connection cnx = DataSource.getInstance().getCnx();
    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    Date date = new Date();  
    
    @Override
    public boolean add(Tournois t) {
          String query = "INSERT INTO `tournois`(`nom`, `date_debut`, `date_fin`, `prime`) VALUES (?,?,?,?)";
        try {
         //LocalDate localDateDeb = datedebutFid.getValue();
     //    LocalDate localDateFin = datefinFid.getValue();
    //    Date datedeb = java.sql.Date.valueOf(localDateDeb);
      //   Date datefin = java.sql.Date.valueOf(localDateFin);
            PreparedStatement ps = cnx.prepareStatement(query);
            
          
           
            
            ps.setObject(1, t.getNom());
            ps.setObject(2, t.getDate_debut());
            ps.setObject(3, t.getDate_fin());
            ps.setObject(4, t.getPrime());
 
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Tournois t) {
         String sql = "UPDATE `tournois` SET `nom`=?,"
                    + "`date_debut`=?,"
                    + "`date_fin`=?,"
                    + "`prime`=? WHERE id =?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);

            ps.setObject(1, t.getNom()); 
            ps.setObject(2, t.getDate_debut());
            ps.setObject(3, t.getDate_fin());
            ps.setObject(4, t.getPrime());
            ps.setObject(5, t.getId());
            
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
    public boolean delete(Tournois t)  {
         String sql = "DELETE FROM `tournois` WHERE id=?";
        
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
    public List<Tournois> getAll()throws SQLException {
 List<Tournois> list = new ArrayList<>();
        String query = "SELECT * FROM `tournois`";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Tournois tou = new Tournois(rs.getObject(1), rs.getObject(2),rs.getObject(3), rs.getObject(4),rs.getObject(5));
                 list.add(tou);
            }
            
      } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;    
    }

    @Override
    public Tournois getOne(Tournois t) {
       String query = "SELECT * FROM `tournois` WHERE id='" + t.getId()+"'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Tournois tou = new Tournois(rs.getObject(1), rs.getObject(2),rs.getObject(3), rs.getObject(4),rs.getObject(5));
                return tou;
            } 
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Tournois();
    }

    @Override
    public Tournois getById(int id) {
       String query = "SELECT * FROM `tournois` WHERE id='" + id+"'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Tournois tou = new Tournois(rs.getObject(1), rs.getObject(2),rs.getObject(3), rs.getObject(4),rs.getObject(5));
                return tou;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Tournois();
    }
    
}
