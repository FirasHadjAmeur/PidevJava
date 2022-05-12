/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.views;

import com.shoppingcart.utils.AlertUtils;
import com.esprit.modeles.Joueurs;
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
public class ModifierJoueurController implements Initializable {

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
    Joueurs j =AfficherJoueurController.joueurActuelle;

  public ModifierJoueurController() {
        connexion = Connexion.getInstance().getCnx();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inputNom.setText(j.getNom()); 
        inputPrenom.setText(j.getPrenom()); 
inputEmail.setText(j.getEmail());
inputnumero.setText(Integer.toString(j.getNumero()));
inputnbrPartie.setText(Integer.toString(j.getNbr_partie_jouer()));
         try {
     String req1 = "select * from equipes WHERE id=" +j.getEquipes_id()+ " ";
             System.out.println(req1);
            Statement stm1 = connexion.createStatement();
            ResultSet rst1 = stm1.executeQuery(req1);
            
            while (rst1.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                String xx1 = rst1.getString("nom");
                comboEquipe.setValue(xx1);
            }

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
//comboEquipe.setValue(Integer.toString(j.getEquipes_id()));
    }    

 @FXML
    private void Modifier(ActionEvent event) throws SQLException, IOException {
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
                    j.getId(),
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
                JoueurCrud.UpdateJoueur(joueur,joueur.getId());
                retour(event);
                 AlertUtils.makeSuccessNotification("joueur modifié !!");
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Félicitation");
//                alert.setHeaderText(null);
//                alert.setContentText("joueur modifié !!");
//                alert.showAndWait();
            }

//MissionCrud rt = new MissionCrud();
//        rt.(new mission(inputId.getText(),inputDescription.getText(), Date.valueOf(Dateid.getValue()), inputnbheure.getText(), inputprix.getText()));
//        JOptionPane.showMessageDialog(null, "evenement ajouté");
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
