/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Categoryexercice;
import com.esprit.services.CategoryexerciceCRUD;
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
public class AddCategoryexController implements Initializable {

  
    @FXML
    private Button btnajoutpanier;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField photo;
    @FXML
    private TextField likedislike;

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
    private void btnpanier(MouseEvent event) {
        
          if (name.getText().isEmpty()||description.getText().isEmpty()||photo.getText().isEmpty()||likedislike.getText().isEmpty()){
                showAlert(Alert.AlertType.ERROR, likedislike.getScene().getWindow(),
                        "Form Error!", "Veuillez remplir tous les champs!");}
          
        //int idcat = Integer.parseInt(id.getText());
        String namecat = name.getText();
        String desccat = description.getText();
        String photocat = photo.getText();
        int likediscat = Integer.parseInt(likedislike.getText());
       
        
        Categoryexercice c = new Categoryexercice(namecat,desccat,photocat,likediscat);
        CategoryexerciceCRUD CC = new CategoryexerciceCRUD();
        CC.ajouterCategoryexercice(c);
         try {
            Parent root = FXMLLoader.load(getClass().getResource("ShowCategoryexercice.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Dashboard");
            primaryStage.setScene(scene);
         
            primaryStage.show();
           //  Stage stage1 = (Stage) nom_client.getScene().getWindow();
             // stage1.close();
        
      } catch (IOException ex) {
            Logger.getLogger(AddCategoryexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
