/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.modeles.Commande;
import com.esprit.modeles.Livraison;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author azizm
 */
public class ServiceLivraison  implements IServices<Livraison>{
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(Livraison t) {
        try {
            String requete = "INSERT INTO livraison (commandeId,nom,prenom,email,adresse,numTelephone,date,totalCost) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getCommandeId());
             pst.setString(2, t.getNom());
            pst.setString(3, t.getPrenom());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getAdresse());
            pst.setInt(6, t.getNumTelephone());
            pst.setString(7, t.getDate());
            pst.setInt(8, t.getTotalCost());
            pst.executeUpdate();
            System.out.println(" Livvraison passee !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
   
    @Override
    public List<Livraison> afficher() {
        List<Livraison> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM livraison";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
            list.add(new Livraison(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), rs.getInt(7),rs.getString(8),rs.getInt(9))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public void supprimer(Livraison t) {
    }

    @Override
    public void modifier(Livraison t) {
    }
    
    public List<Livraison> trierreservationdate() {
         ArrayList<Livraison> listReservaion = new ArrayList<>();
         try {
            String req = "Select * from livraison";
          PreparedStatement st =   cnx.prepareStatement(req);
            ResultSet res = st.executeQuery(req);
            Livraison re=null;
            while(res.next()){
                re =new Livraison(res.getInt(1),res.getInt(2), res.getString(3),res.getString(4),res.getString(5), res.getString(6), res.getInt(7),
                        res.getString(8),res.getInt(9)); 
                listReservaion.add(re);
            }
             Collections.sort(listReservaion, dateComparator);
             Collections.reverse(listReservaion);
             
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
         return listReservaion;
    }
    
     public static Comparator<Livraison> dateComparator = new Comparator<Livraison>() {

        @Override
	public int compare(Livraison r1, Livraison r2) {
            
            
            String date1 = r1.getDate();
        String date2 =  r2.getDate();


           return date1.compareTo(date2);

	  
    }
    };

    @Override
    public boolean add(Livraison t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Livraison t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Livraison t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Livraison> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Livraison getOne(Livraison t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Livraison getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
