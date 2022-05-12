package com.esprit.views;

import com.shoppingcart.utils.AlertUtils;
import com.shoppingcart.utils.Mailer;
import com.esprit.modeles.Equipe;
import com.esprit.services.EquipeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterEquipeController implements Initializable {

    @FXML
    private TextField inputNom;
    @FXML
    private TextField inputnbrvic;
    @FXML
    private TextField inputnbrperte;
    @FXML
    private TextField inputnbrnull;
    @FXML
    private CheckBox inputSus;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
    private void ajout(ActionEvent event) throws SQLException, IOException, Exception {
       
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
                EquipeCrud.AddEquipe(equipe);
                retour(event);
                   Mailer.sendMail("nadhir.kraiem@esprit.tn", "Equipe Ajouter", "<h1>Notification</h1> <br/> <h2><b>Une Equipe a été ajouté</b></h2>");
                  AlertUtils.makeSuccessNotification("Equipe ajoutée !!");
            }

//MissionCrud rt = new MissionCrud();
//        rt.(new mission(inputId.getText(),inputDescription.getText(), Date.valueOf(Dateid.getValue()), inputnbheure.getText(), inputprix.getText()));
//        JOptionPane.showMessageDialog(null, "evenement ajouté");
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
