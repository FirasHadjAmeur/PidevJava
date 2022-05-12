/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import com.esprit.modeles.Categorie;
import com.esprit.services.ServiceCategorie;
import static sun.management.Agent.warning;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutCategorieController implements Initializable {

    @FXML
    private AnchorPane ajoutercategoriepane;
    @FXML
    private JFXTextField Fournisseur;
    @FXML
    private JFXTextField Nom;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton retourner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void ajouterCategorie(ActionEvent event) {
        Categorie c = new Categorie();
//            List<String> myList = new ArrayList<String>();
            
                Categorie p = new Categorie();
//                    p.setId(rs.getInt(1));

                p.setNom(Nom.getText());
                p.setFournisseur(Fournisseur.getText());
                 
//**************************************************************************************************
//                    myList.add(rs.getString(3));
                ServiceCategorie ms = new ServiceCategorie();
                ms.add(p);

                
                
              
                     //setNode(FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/AfficherCategorie.fxml")));
                new Alert(Alert.AlertType.INFORMATION, "Catégorie ajoutée").show();

                  ajoutercategoriepane.toBack();
    }

    @FXML
    private void back(ActionEvent event) {
        ajoutercategoriepane.toBack();
    }
    
    private void setNode(Node node) {
        ajoutercategoriepane.getChildren().clear();
        ajoutercategoriepane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }
    
}
