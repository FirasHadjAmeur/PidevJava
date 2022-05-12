/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/afficherPublications.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Publications");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }       
      
    }
    
    public void setStage() {
        Stage dialog = new Stage();
        VBox dialogVbox = new VBox(20);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
