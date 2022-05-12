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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherEquipeController implements Initializable {

    EquipeService EquipeCrud = new EquipeService();
    ObservableList<Equipe> observableListEquipe = FXCollections.observableArrayList();
    public static Equipe equipeActuelle;
    public static Equipe equipeClicked;
    
    @FXML
    private TableView<Equipe> idTableau;
    @FXML
    private TableColumn<Equipe, String> columnNom;
    @FXML
    private TableColumn<Equipe, Integer> columnNbrVic;
    @FXML
    private TableColumn<Equipe, Integer> columnNbrPer;
    @FXML
    private TableColumn<Equipe, Integer> columnNbrNull;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Text textEquipeIdActuelle;
    @FXML
    private TextField txrecherche;
    @FXML
    private AnchorPane root;
    @FXML
    private Button btnAjout;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnAjout1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          equipeActuelle = null;
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);
        Date date = new Date();

        List<Equipe> listequipe;
        try {
            listequipe = EquipeCrud.ShowEquipe();
          
                for (int i = 0; i < listequipe.size(); i++) {
                        observableListEquipe.add(listequipe.get(i));
                        System.out.println(listequipe.get(i).getNom());
                  
                }
                loadDate();  
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

//      DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//      System.out.println(format.format(date));
       
        List<Equipe> listFed;
        try {
            listFed = EquipeCrud.ShowEquipe();
        observableListEquipe.clear();
        observableListEquipe.addAll(listFed);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        idTableau.setItems(observableListEquipe);

        columnNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        columnNbrVic.setCellValueFactory(new PropertyValueFactory<>("nbr_vic"));
        columnNbrPer.setCellValueFactory(new PropertyValueFactory<>("nbr_per"));
        columnNbrNull.setCellValueFactory(new PropertyValueFactory<>("nbr_null"));
        idTableau.setItems(observableListEquipe);
        Recherche();
    }    

 @FXML
    private void ajouterEquipe(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("AjouterEquipe.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("ModifierEquipe.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AfficherJoueur(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("AfficherJoueur.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

   @FXML
    private void supp(ActionEvent event) throws SQLException 
    {
           if (event.getSource() == btnSupprimer) {
           int e=idTableau.getSelectionModel().getSelectedItem().getId();  
//          EvenementServices cs = new EvenementServices();
//            cs.DeleteEvenement(e);
        EquipeCrud.DeleteEquipe(e);
            loadDate();  
        AlertUtils.makeSuccessNotification("Equipe supprimer !!");
        }
    }

  private void loadDate() throws SQLException {
        ObservableList<Equipe> abList = FXCollections.observableArrayList();
       
        columnNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
columnNbrVic.setCellValueFactory(new PropertyValueFactory<>("nbr_vic"));
   columnNbrPer.setCellValueFactory(new PropertyValueFactory<>("nbr_per"));
        columnNbrNull.setCellValueFactory(new PropertyValueFactory<>("nbr_null"));

        List old = EquipeCrud.ShowEquipe();
        abList.addAll(old);
        idTableau.setItems(abList);
        idTableau.refresh();
    }
    @FXML
  public void Recherche() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Equipe> filteredData = new FilteredList<>(observableListEquipe, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(equipe -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (equipe.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(equipe.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Equipe> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(idTableau.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        idTableau.setItems(sortedData);
    }

 @FXML
    private void changerEquipeActuelle(MouseEvent event) {
        btnModifier.setDisable(false);
        btnSupprimer.setDisable(false);
        equipeActuelle = idTableau.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void returnHome(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/DashboardAdmin.fxml"));
        root.getChildren().setAll(pane);
    }
    
}
