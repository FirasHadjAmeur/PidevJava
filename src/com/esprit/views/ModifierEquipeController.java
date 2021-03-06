/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.views;

import com.shoppingcart.utils.AlertUtils;
import com.esprit.modeles.Equipe;
import com.esprit.services.EquipeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifierEquipeController implements Initializable {

    @FXML
    private TextField inputNom;
    @FXML
    private TextField inputnbrvic;
    @FXML
    private TextField inputnbrperte;
    @FXML
    private TextField inputnbrnull;

  Equipe e= AfficherEquipeController.equipeActuelle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        inputNom.setText(e.getNom()); 
        inputnbrvic.setText(Integer.toString(e.getNbr_vic()));
        inputnbrperte.setText(Integer.toString(e.getNbr_per()));
        inputnbrnull.setText(Integer.toString(e.getNbr_null()));

    }    

  @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {
       
        if (inputNom.getText().isEmpty() | inputnbrvic.getText().isEmpty() | inputnbrperte.getText().isEmpty() | inputnbrnull.getText().isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Remplir les champs vides");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Remplir les champs vides");
            al.showAndWait();
        } else {
            Boolean update = true;
            EquipeService EquipeCrud = new EquipeService();
            String nom = inputNom.getText();
            int nbrvic = Integer.parseInt(inputnbrvic.getText());
            int nbrperte = Integer.parseInt(inputnbrperte.getText());
             int nbrnull = Integer.parseInt(inputnbrnull.getText());
            Equipe equipe = new Equipe(
                   e.getId(),
                    nom,
                    nbrvic,
                   nbrperte,
                   nbrnull,
                    0
            );

            List<Equipe> listequipe = EquipeCrud.ShowEquipe();
            for (int i = 0; i < listequipe.size(); i++) {
                if (nom.equals(listequipe.get(i).getNom())) {
                    System.out.println("erreur!!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("cette Equipe deja existe!!! ");
                    alert.showAndWait();
                    update = false;
                }
//                 System.out.println(listmission.get(i).getNom());
            }
            if (update) {
                EquipeCrud.UpdateEquipe(equipe,equipe.getId());
                retour(event);
                  AlertUtils.makeSuccessNotification("Equipe Modifi?? !!");
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("F??licitation");
//                alert.setHeaderText(null);
//                alert.setContentText("Equipe Modifi?? !!");
//                alert.showAndWait();
            }

//MissionCrud rt = new MissionCrud();
//        rt.(new mission(inputId.getText(),inputDescription.getText(), Date.valueOf(Dateid.getValue()), inputnbheure.getText(), inputprix.getText()));
//        JOptionPane.showMessageDialog(null, "evenement ajout??");
        }
    }

 @FXML
    private void retour(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("AfficherEquipe.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
}
