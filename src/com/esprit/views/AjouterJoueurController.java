/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.views;

import com.shoppingcart.utils.AlertUtils;
import com.shoppingcart.utils.Mailer;
import com.esprit.modeles.Equipe;
import com.esprit.modeles.Joueurs;
import com.esprit.services.EquipeService;
import com.esprit.services.JoueurService;
import com.esprit.utils.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterJoueurController implements Initializable {

    @FXML
    private TextField inputNom;
    @FXML
    private TextField inputPrenom;
    @FXML
    private TextField inputEmail;
    @FXML
    private TextField inputnumero;
    @FXML
    private TextField inputnbrPartie;
    @FXML
    private ComboBox<String> comboEquipe;

     Connection connexion;  

    public AjouterJoueurController() {
        connexion = Connexion.getInstance().getCnx();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
               try {
            String req = "select * from equipes";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                String xx = rst.getString("nom");
                comboEquipe.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    

   @FXML
    private void ajout(ActionEvent event) throws SQLException, IOException, Exception {
        int equipeId=0;
        int Suspenssion=0;
        if (inputNom.getText().isEmpty() | inputPrenom.getText().isEmpty() | inputEmail.getText().isEmpty() | inputnumero.getText().isEmpty()| inputnbrPartie.getText().isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Remplir les champs vides");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Remplir les champs vides");
            al.showAndWait();
        } else {
            Boolean update = true;
            JoueurService JoueurCrud = new JoueurService();
            String nom = inputNom.getText();
            String prenom = inputPrenom.getText();
            String email = inputEmail.getText();
            int numero = Integer.parseInt(inputnumero.getText());
            int nbrpartie = Integer.parseInt(inputnbrPartie.getText());        
            String equipe=comboEquipe.getValue();
            PreparedStatement preparedStatement = connexion.prepareStatement(
            "select * from equipes WHERE nom LIKE ? ");
            preparedStatement.setString(1, equipe + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
             equipeId = resultSet.getInt("id");
            }
            Joueurs joueur = new Joueurs(
                    nom,
                    prenom,
                    equipeId,
                   email,
                   numero,
                    nbrpartie
            );

            List<Joueurs> listeJoueur = JoueurCrud.ShowJoueur();
            for (int i = 0; i < listeJoueur.size(); i++) {
                if (nom.equals(listeJoueur.get(i).getNom())) {
                    System.out.println("erreur!!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("cette Joueur deja existe!!! ");
                    alert.showAndWait();
                    update = false;
                }
//                 System.out.println(listmission.get(i).getNom());
            }
            if (update) {
                JoueurCrud.AddJoueur(joueur);
                Mailer.sendMail("nadhir.kraiem@esprit.tn", "Joueur Ajouter", "<h1>Notification</h1> <br/> <h2><b>Un joueur a été ajouté</b></h2>");
                retour(event);
            AlertUtils.makeSuccessNotification("joueur ajoutée !!");
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Félicitation");
               alert.setHeaderText(null);
              alert.setContentText("joueur ajoutée !!");
               alert.showAndWait();
            }

//MissionCrud rt = new MissionCrud();
      //  rt.(new mission(inputId.getText(),inputDescription.getText(), Date.valueOf(Dateid.getValue()), inputnbheure.getText(), inputprix.getText()));
     //   JOptionPane.showMessageDialog(null, "evenement ajouté");
        }
    }

 @FXML
    private void retour(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("AfficherJoueur.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
