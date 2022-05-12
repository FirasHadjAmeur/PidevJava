/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ModificationCategorieController implements Initializable {

    @FXML
    private AnchorPane anchorPanceModifierCategorie;
    @FXML
    private JFXTextField tfFournisseur;
    @FXML
    private JFXTextField tfNom;
    @FXML
    private JFXButton modifier;
    int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id=    AffichageCategorieController.d.getId();
        tfNom.setText(AffichageCategorieController.d.getNom());
        tfFournisseur.setText(AffichageCategorieController.d.getFournisseur());
        // TODO
    }    

    @FXML
    private void modifierCategorie(ActionEvent event) {
        Categorie p = new Categorie();
                   // p.setId(rs.getInt(1));

                p.setNom(tfNom.getText());
                p.setFournisseur(tfFournisseur.getText());
                p.setId(id);
                //**************************************************************************************************
//                    myList.add(rs.getString(3));
                ServiceCategorie ms = new ServiceCategorie();
                ms.update(p);
                anchorPanceModifierCategorie.toBack();
//                setNode(FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/AfficherCategorie.fxml")));
                new Alert(Alert.AlertType.INFORMATION, "Catégorie modifiée").show();
    }

    @FXML
    private void back(ActionEvent event) {
        anchorPanceModifierCategorie.toBack();
    }
    
    private void setNode(Node node) {
        anchorPanceModifierCategorie.getChildren().clear();
        anchorPanceModifierCategorie.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
           }
    
}
