/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import animatefx.animation.FadeIn;
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
import javafx.scene.layout.Pane;
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
    @FXML
    private Pane context;
    @FXML
    private Button joueursbtngo;

    /**
     * Initializes the controller class.
     */
    
     public void setUi(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource(location + ".fxml")));
    };
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Utilisateur(ActionEvent event) throws IOException {
    /*   AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/Gestion_user.fxml"));
        rootPane.getChildren().setAll(pane);  */
        setUi("Gestion_user");
        new FadeIn(context).play();
    }

    @FXML
    private void Actionlog(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/SignInAdmin.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void commande_a(ActionEvent event) throws IOException {
        /* AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/AffichageCommande.fxml"));
        rootPane.getChildren().setAll(pane);
        */
         setUi("AffichageCommande");
        new FadeIn(context).play();
        
    }

    @FXML
    private void livraison_btn(ActionEvent event) throws IOException {
         /*AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/LivraisonFXML.fxml"));
        rootPane.getChildren().setAll(pane);
        */
         setUi("LivraisonFXML");
        new FadeIn(context).play();
    }

    @FXML
    private void livreur_btn(ActionEvent event) throws IOException {
        /* AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/LivreurFXML.fxml"));
        rootPane.getChildren().setAll(pane);
        */
        setUi("LivreurFXML");
        new FadeIn(context).play();
    }

    @FXML
    private void Actioncategbtn(ActionEvent event) throws IOException {
       /* AnchorPane pane = FXMLLoader.load(getClass().getResource("AffichageCategorie.fxml"));
        rootPane.getChildren().setAll(pane);
        
        */
        setUi("AffichageCategorie");
        new FadeIn(context).play();
    }

    @FXML
    private void Actionctournoibtn(ActionEvent event) throws IOException {
     /* Parent page1 = FXMLLoader.load(getClass().getResource("AfficherTournois.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); */
        setUi("AfficherTournois");
        new FadeIn(context).play();
    }

    @FXML
    private void equipbtngo(ActionEvent event) throws IOException {
       /* Parent page1 = FXMLLoader.load(getClass().getResource("AfficherEquipe.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();*/
        setUi("AfficherEquipe");
        new FadeIn(context).play();
    }

    @FXML
    private void ActionMaterielsbtn(ActionEvent event) throws IOException {
        
                 setUi("AffichageMateriel");
        new FadeIn(context).play();
    }

    @FXML
    private void Actionmatchsbtn(ActionEvent event) throws IOException {
        setUi("AfficherMatchs");
        new FadeIn(context).play();
    }

    @FXML
    private void joueursbtngo(ActionEvent event) throws IOException {
        
         setUi("AfficherJoueur");
        new FadeIn(context).play();
    }
    
    
}
