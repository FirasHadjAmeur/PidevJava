package com.esprit.test;



import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class NewFXtournoisback extends Application{
      @Override
    public void start(Stage primaryStage) throws SQLException {
        
        try {
            //Parent root =FXMLLoader.load(getClass().getResource("AfficherJoueur.fxml"));
            Parent root =FXMLLoader.load(getClass().getResource("../views/AfficherMatchs.fxml"));
           
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Gestion Des tournois");
            primaryStage.setScene(scene);
                primaryStage.show();
//            Equipe e=new Equipe("Barca", 13, 2,3,0);
//            EquipeService es=new EquipeService();
//            es.AddEquipe(e);

        } catch (IOException ex) {
            Logger.getLogger(NewFXtournoisback.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
