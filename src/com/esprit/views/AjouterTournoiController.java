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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.shoppingcart.utils.AlertUtils;

/**
 * FXML Controller class
 *
 * @author Dragon
 */
public class AjouterTournoiController implements Initializable {

    @FXML
    private Button btnRetour;
    @FXML
    private Text topText;
    @FXML
    private TextField inputNom;
    @FXML
    private TextField inputprime;
    @FXML
    private DatePicker inputdatedeb;
    @FXML
    private DatePicker inputdatefin;

    PreparedStatement preparedStatement;
    @FXML
    private Button btnAjout;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

 

    @FXML
    private void ajoute(ActionEvent event) throws SQLException, IOException {
        
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
            ServiceTournois TournoiCrud = new ServiceTournois();
            List<Tournois> listetournois = TournoiCrud.getAll();
            boolean update=true;
           
            for (int i = 0; i < listetournois.size(); i++) {
                if (inputNom.getText().equals(listetournois.get(i).getNom())) {
                    System.out.println("erreur!!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Tournoi deja existe ! ");
                    alert.showAndWait();
                    update = false;
                }
            }
            
            if (update) {
             LocalDate localDateDeb = inputdatedeb.getValue();
             LocalDate localDateFin = inputdatefin.getValue();
            
            
           String nom = inputNom.getText();
           Date datedeb = java.sql.Date.valueOf(localDateDeb);
           Date datefin = java.sql.Date.valueOf(localDateFin);
           int pirme = Integer.parseInt(inputprime.getText());
           
           Tournois tournoi = new Tournois (
           nom,
           datedeb,
           datefin,
           pirme
           );
           TournoiCrud.add(tournoi);
           retour(event);
           AlertUtils.makeSuccessNotification("Tournoi Ajouté !");
            }
            
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
