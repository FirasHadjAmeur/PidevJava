/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.esprit.modeles.Categorie;
import com.esprit.modeles.Materiels;
import com.esprit.utils.DataSource;

/**
 *
 * @author HP
 */
public class ServiceMateriels implements IServices<Materiels> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public boolean add(Materiels t) {
        String query = "INSERT INTO materiels (`nom`, `qte`,`categoriem_id`) VALUES(?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
           //  ps.setObject(1, t.getId());
           // ps.setObject(1, t.getNom());
            ps.setObject(1, t.getNom());
            ps.setObject(2, t.getQte());
            ps.setObject(3, t.getCategoriem_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Materiels t) {
        String query = "UPDATE materiels SET nom=?, categoriem_id=?, qte=?  WHERE id =?";


        try {
            PreparedStatement ps = cnx.prepareStatement(query);
         
            ps.setObject(1, t.getNom());
            ps.setObject(2, t.getCategoriem_id());
            ps.setObject(3, t.getQte());
            ps.setObject(4, t.getId());
            

           
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
    public boolean delete(Materiels t) {
        String sql = "DELETE FROM materiels WHERE id=?";

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
    public List<Materiels> getAll() {
        List<Materiels> list = new ArrayList<>();
        String query = "Select * from materiels";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                String quer = "Select nom from categoriem where id=" + rs.getObject(2);
                Statement stc = cnx.createStatement();
                ResultSet rsc = stc.executeQuery(quer);
                rsc.next();
                Materiels m = new Materiels(rs.getObject(1),rsc.getObject(1), rs.getObject(3), rs.getObject(4));
                
                
                
                

 
 
                list.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Materiels getOne(Materiels t) {
        
        String query = "select * from materiels where id=" + t.getId();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Materiels p = new Materiels(rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4));
                return p;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Materiels();
        
    }

    @Override
    public Materiels getById(int id) {
         String query = "select * from materiels where id=" + id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Materiels p = new Materiels(rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4));
                return p;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return new Materiels();
        
        
    }

    @Override
    public void ajouter(Materiels t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Materiels t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Materiels t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Materiels> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
}
