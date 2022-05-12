
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Livreur;
import com.esprit.services.ServiceLivreur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author azizm
 */
public class AjouterLiveurFXMLController implements Initializable{

    @FXML
    private TextField fnumerotelephonelivreur;
    @FXML
    private TextField femail;
    @FXML
    private TextField fprenomlivreur;
    @FXML
    private TextField fnomlivreur;
    @FXML
    private TextField fadresselivreur;
    @FXML
    private AnchorPane livv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
       
    }    

    @FXML
    private void AjoutLivreur(ActionEvent event) throws SQLException, IOException {
         
         ServiceLivreur sl = new ServiceLivreur();
        int nl = Integer.parseInt(fnumerotelephonelivreur.getText());
        sl.ajouter(new Livreur(fnomlivreur.getText(),fprenomlivreur.getText(),femail.getText(),nl,fadresselivreur.getText()));
        JOptionPane.showMessageDialog(null,"Livreur Ajoutée");
         
      
        AnchorPane pane = FXMLLoader.load(getClass().getResource("LivreurFXML.fxml"));
           livv.getChildren().setAll(pane);
    }
    
      
    
}
