/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achref
 */
public class DashboardAdminController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button logobnt1;
    @FXML
    private Button equipbtngo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Utilisateur(ActionEvent event) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/Gestion_user.fxml"));
        rootPane.getChildren().setAll(pane);  
    }

    @FXML
    private void Actionlog(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/SignInAdmin.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void commande_a(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/AffichageCommande.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void livraison_btn(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/LivraisonFXML.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void livreur_btn(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/LivreurFXML.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void Actioncategbtn(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/AffichageCategorie.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void Actionctournoibtn(ActionEvent event) throws IOException {
      Parent page1 = FXMLLoader.load(getClass().getResource("AfficherTournois.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void equipbtngo(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("AfficherEquipe.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
}
