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
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achref
 */
public class InterfaceMembreController implements Initializable {

    @FXML
    private AnchorPane rootPane3;
    @FXML
    private Pane paneId;
    @FXML
    private Button profilbtn;
    @FXML
    private Button logoffbtn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ActionProfilbtn(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/ProfilMembre.fxml"));
        rootPane3.getChildren().setAll(pane);
    }

    
    

    @FXML
    private void ActionLogOffbtn(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/SignInMembre.fxml"));
        rootPane3.getChildren().setAll(pane);
    }

    @FXML
    private void ActionPubbtn(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/afficherPublications.fxml"));
        rootPane3.getChildren().setAll(pane);
        
    }
    
   /* private void equipbtngo(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/AfficherEquipe.fxml"));
        rootPane3.getChildren().setAll(pane);
        
    }*/

    @FXML
    private void Actionexercicebtn(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/MenuV1.fxml"));
        rootPane3.getChildren().setAll(pane);
    }

    private void Actioncategbtn(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/AffichageCategorie.fxml"));
        rootPane3.getChildren().setAll(pane);
    }

    @FXML
    private void ActionLIVRAISbtn(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/CartFXML.fxml"));
        rootPane3.getChildren().setAll(pane);
    }

    @FXML
    private void ActionMATCHSbtn(ActionEvent event) throws IOException {
       Parent page1 = FXMLLoader.load(getClass().getResource("MatchsFront.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
