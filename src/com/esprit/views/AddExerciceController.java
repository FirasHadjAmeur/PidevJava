/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;


import com.esprit.modeles.Exercice;
import com.esprit.services.ExerciceCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author safwen
 */
public class AddExerciceController implements Initializable {

  
    @FXML
    private Button btnaddcomm;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField nbrst;
    @FXML
    private TextField photo;
    @FXML
    private TextField likedislike;
    @FXML
    private TextField idcategory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.initOwner(owner);
    alert.show();
}

    @FXML
    private void AddCommande(MouseEvent event) {
        
         if (name.getText().isEmpty()||nbrst.getText().isEmpty()||photo.getText().isEmpty()||likedislike.getText().isEmpty()||idcategory.getText().isEmpty()){
                showAlert(Alert.AlertType.ERROR, likedislike.getScene().getWindow(),
                        "Form Error!", "Veuillez remplir tous les champs!");}
  
        //int idexercice = Integer.parseInt(id.getText());
        String nameee = name.getText();
        int nbrsttt = Integer.parseInt(nbrst.getText());
        String photott = photo.getText();
        int likeee = Integer.parseInt(likedislike.getText());
        int idcat = Integer.parseInt(idcategory.getText());
        
        Exercice c = new Exercice(nameee,nbrsttt,photott,likeee,idcat);
        ExerciceCRUD CC = new ExerciceCRUD();
        

        CC.ajouterExercice(c);
         try {
            Parent root = FXMLLoader.load(getClass().getResource("ShowExercice.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Dashboard");
            primaryStage.setScene(scene);
         
            primaryStage.show();
           //  Stage stage1 = (Stage) nom_client.getScene().getWindow();
             // stage1.close();
        
      } catch (IOException ex) {
            Logger.getLogger(AddExerciceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
