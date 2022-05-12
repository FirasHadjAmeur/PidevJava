/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Dragon
 */
public class AfficherTournoisController implements Initializable {

    @FXML
    private Button btnAjout;
    @FXML
    private Button btnretour;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<?> idTableau;
    @FXML
    private TableColumn<?, ?> columnNom;
    @FXML
    private TableColumn<?, ?> columnDateDeb;
    @FXML
    private TableColumn<?, ?> columnDateFin;
    @FXML
    private TableColumn<?, ?> columnPrime;
    @FXML
    private TextField txrecherche;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Text textTournoiIdActuel;
    @FXML
    private Button btnAjout1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterTounoi(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void changerTournoiActuel(MouseEvent event) {
    }

    @FXML
    private void RechercheT(ActionEvent event) {
    }

    @FXML
    private void Modifier(ActionEvent event) {
    }

    @FXML
    private void supp(ActionEvent event) {
    }

    @FXML
    private void Affichermatchs(ActionEvent event) {
    }
    
}
