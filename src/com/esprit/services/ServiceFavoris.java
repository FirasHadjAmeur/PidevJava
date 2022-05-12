/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.modeles.Favoris;
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
public class ServiceFavoris implements IIService<Favoris> {
     Connection cnx = DataSource.getInstance().getCnx();


    @Override
    public boolean delete(Favoris t) {
        String sql = "DELETE FROM `favoris` WHERE id=?";
        
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
    public List<Favoris> getAll() throws SQLException {
        List<Favoris> list = new ArrayList<>();
        String query = "SELECT * FROM `favoris`";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
 

            Favoris fav = new Favoris(rs.getObject(1),rs.getObject(2),rs.getObject(3),rs.getObject(4),rs.getObject(5),rs.getObject(6),rs.getObject(7),rs.getObject(8));
            list.add(fav);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;   
    }

    @Override
    public Favoris getOne(Favoris t) {
        String query = "SELECT * FROM `favoris` WHERE id='" + t.getId()+"'";
        try {
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                                                         
                Favoris matc = new Favoris(rs.getObject(1),rs.getObject(2),rs.getObject(3),rs.getObject(4),rs.getObject(5),rs.getObject(6),rs.getObject(7),rs.getObject(8));
                return matc;
            } 
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return new Favoris();
    }

    @Override
    public Favoris getById(int id) {
        String query = "SELECT * FROM `matchs` WHERE id='" + id+"'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Favoris tou = new Favoris(rs.getObject(1), rs.getObject(2),rs.getObject(3), rs.getObject(4),rs.getObject(5),rs.getObject(6),rs.getObject(7),rs.getObject(8));
                return tou;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Favoris();
    }

    @Override
    public boolean add(Favoris t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Favoris t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
     
    
}
