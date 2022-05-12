/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import com.esprit.modeles.Categorie;
import com.esprit.modeles.Materiels;
import com.esprit.services.ServiceMateriels;
import com.esprit.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutMaterielController implements Initializable {

    @FXML
    private AnchorPane ajoutercategoriepane;
    @FXML
    private JFXTextField tfQte;
    @FXML
    private JFXTextField tfNom;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXComboBox<String> comCat;
    @FXML
    private ImageView imageaff;
    Connection cnx = DataSource.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String req = "select * from categoriem";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                String xx = rst.getString("nom");
                
                
                comCat.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // TODO
    }    

    @FXML
    private void ajouterCategorie(ActionEvent event) throws SQLException {
        int categoriem_id=0;
        ServiceMateriels sm= new ServiceMateriels();
            String nom = tfNom.getText();
            int qte = Integer.parseInt(tfQte.getText());
            String categorie=comCat.getValue();        
            
            
            PreparedStatement preparedStatement = cnx.prepareStatement(
            "select * from categoriem WHERE nom LIKE ? ");
            preparedStatement.setString(1, categorie + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
              categoriem_id = resultSet.getInt("id");
            }
            Materiels mat = new Materiels(
                    qte,
                    nom,
                    categoriem_id);
            sm.add(mat);
        
    }

    @FXML
    private void back(ActionEvent event) {
    }
    
}
