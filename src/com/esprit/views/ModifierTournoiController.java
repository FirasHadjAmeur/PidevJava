/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Tournois;
import com.esprit.services.ServiceTournois;
import com.esprit.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import com.shoppingcart.utils.AlertUtils;
/**
 * FXML Controller class
 *
 * @author Dragon
 */
public class ModifierTournoiController implements Initializable {

    @FXML
    private Button btnRetour;
    @FXML
    private Text topText;
    @FXML
    private TextField inputNom;
    @FXML
    private Button btnModifier;
    @FXML
    private TextField inputprime;
    @FXML
    private DatePicker inputdatedeb;
    @FXML
    private DatePicker inputdatefin;
    PreparedStatement preparedStatement;
    Tournois t= AfficherTournoisController.tournoiActuel;
    // e => t
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      /*  Date DateDeb = t.getDate_debut();
        Date DateFin = t.getDate_fin();
        ZoneId defaultZoneId = ZoneId.systemDefault();
		
	
	Instant instant = DateDeb.toInstant();
	LocalDate localDatedeb = instant.atZone(defaultZoneId).toLocalDate();         

        inputNom.setText(t.getNom());
        inputdatedeb.setValue(localDatedeb);
        instant = DateFin.toInstant();
	LocalDate localDateFin = instant.atZone(defaultZoneId).toLocalDate(); 
        inputdatefin.setValue(localDateFin);
        inputprime.setText(String.valueOf(t.getPrime()));
     */
         inputNom.setText(t.getNom());
         inputprime.setText(String.valueOf(t.getPrime()));
    }    

    @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {
         Connection connection = DataSource.getInstance().getCnx();
        String datedebstring = String.valueOf(inputdatedeb.getValue());
        String datefinstring = String.valueOf(inputdatefin.getValue());    
    
        
        
        if (inputNom.getText().isEmpty() | datedebstring.isEmpty() | datefinstring.isEmpty()  | inputprime.getText().isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Remplir les champs vides");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Remplir les champs vides");
            al.showAndWait();
        } else {
            ServiceTournois TCrud = new ServiceTournois();
            LocalDate localDateDeb = inputdatedeb.getValue();
             LocalDate localDateFin = inputdatefin.getValue();
            
            
           String nom = inputNom.getText();
           Date datedeb = java.sql.Date.valueOf(localDateDeb);
           Date datefin = java.sql.Date.valueOf(localDateFin);
           int pirme = Integer.parseInt(inputprime.getText());
           
           Tournois tournoi = new Tournois (
           t.getId(),
           nom,
           datedeb,
           datefin,
           pirme
                   );
           TCrud.update(tournoi);
                retour(event);
                  AlertUtils.makeSuccessNotification("Tournoi Modifié !");
           
        }
        
    }
    
        @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherTournois.fxml"));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }

    
}
