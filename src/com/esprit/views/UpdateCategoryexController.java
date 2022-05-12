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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author safwen
 */
public class UpdateCategoryexController implements Initializable {

    @FXML
    private Button btnmodif;

 
    ShowCategoryexerciceController a = new ShowCategoryexerciceController();
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
          CategoryexerciceCRUD uc = new CategoryexerciceCRUD();
           
           Categoryexercice aa = new Categoryexercice();
                aa=uc.getCategoryexercice(a.idd());
        System.out.println(aa);
                 
                  
       id.setText(String.valueOf(aa.getId()));
       name.setText(String.valueOf(aa.getName()));
       description.setText(String.valueOf(aa.getDescription()));
       photo.setText(String.valueOf(aa.getPhoto()));
       likedislike.setText(String.valueOf(aa.getLike_dislike()));
     
    }    

    @FXML
    private void modifact(MouseEvent event) {
        int Id_cmd=a.idd();
           
    
    
            String id_commande=id.getText();
            String namecat=name.getText();
            String desccat=description.getText();
            String photocat=photo.getText();
            String likedislikecat=likedislike.getText();

            int opt = JOptionPane.showConfirmDialog(null, "Confirmer la Modification ?","Modifier",JOptionPane.YES_NO_OPTION);
      if(opt==0){
            CategoryexerciceCRUD pcd = new CategoryexerciceCRUD();
            pcd.modifierCategoryexercice2(String.valueOf(Id_cmd),namecat,desccat,photocat,likedislikecat);
      }  
            try {
            Parent root = FXMLLoader.load(getClass().getResource("ShowCategoryexercice.fxml"));
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
