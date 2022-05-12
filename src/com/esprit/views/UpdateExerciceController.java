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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author safwen
 */
public class UpdateExerciceController implements Initializable {


    
    ShowExerciceController a = new ShowExerciceController();
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField nbrset;
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
          ExerciceCRUD uc = new ExerciceCRUD();
           
           Exercice aa = new Exercice();
                aa=uc.getExercice(a.idd());
        System.out.println(aa);
                 
                  
       id.setText(String.valueOf(aa.getId()));
       name.setText(String.valueOf(aa.getName()));
      nbrset.setText(String.valueOf(aa.getNbrset()));
       photo.setText(String.valueOf(aa.getPhoto()));
        likedislike.setText(String.valueOf(aa.getLike_dislike()));
       idcategory.setText(String.valueOf(aa.getId_categorie_id()));
      
    }    

    @FXML
    private void modifieract(MouseEvent event) {
        
    int Id_cmd=a.idd();
           
    
    
            String iddd=id.getText();
            String nameee=name.getText();
            String nbrsetee=nbrset.getText();
            String photooo=photo.getText();
            String likedisliiike=likedislike.getText();
            String idcatee=idcategory.getText();

            int opt = JOptionPane.showConfirmDialog(null, "Confirmer la Modification ?","Modifier",JOptionPane.YES_NO_OPTION);
      if(opt==0){
            ExerciceCRUD pcd = new ExerciceCRUD();
            pcd.modifierExercice2(String.valueOf(Id_cmd),nameee,nbrsetee,photooo,likedisliiike,idcatee);
      }  
            try {
            Parent root = FXMLLoader.load(getClass().getResource("ShowExercice.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Dashboard");
            primaryStage.setScene(scene);
         
            primaryStage.show();
            Stage stage1 = (Stage) name.getScene().getWindow();
            stage1.close();
        
      } catch (IOException ex) {
            Logger.getLogger(UpdateExerciceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
          
        
    }
    
}
