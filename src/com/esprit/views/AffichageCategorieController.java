/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.esprit.modeles.Categorie;
import org.controlsfx.control.Notifications;
import com.esprit.services.ServiceCategorie;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class AffichageCategorieController implements Initializable {

    static Stage stageModifier;
    ObservableList<Categorie> observableListCategorie = FXCollections.observableArrayList();
    
    private AnchorPane anchorpaneAfficherCategorie;
    @FXML
    private TableView<Categorie> listcategorie;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton supprimer;
    @FXML
    private TableColumn<Categorie, String> colNom;
    @FXML
    private TableColumn<Categorie, String> colFour;
    @FXML
    private JFXTextField filtrenom;
    @FXML
    private JFXTextField filtrefour;
    ServiceCategorie service = new ServiceCategorie();
    private Categorie ct;
    static Categorie d;
    @FXML
    private Button rbtn;
    @FXML
    private AnchorPane root;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affiche();
        
        // TODO
    }    

    public void affiche() {
        ServiceCategorie produitService = new ServiceCategorie();
        ArrayList arrayList = (ArrayList) produitService.getAll();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listcategorie.setItems(observableList);
        
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colFour.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
        

        

        
        listcategorie.setStyle("-fx-font-weight: bold; -fx-font-size: 1.05em; ");

    }
     public void Recherche() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Categorie> filteredData = new FilteredList<>(observableListCategorie, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filtrenom.textProperty().addListener((observable, oldValue, newValue) -> {
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
        SortedList<Categorie> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(listcategorie.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        listcategorie.setItems(sortedData);
    }
    
    
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/AjoutCategorie.fxml"));
        Scene scene = new Scene(root);
        stageModifier = new Stage();
        stageModifier.setScene(scene);
        stageModifier.show();
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        if (listcategorie.getSelectionModel().isEmpty()) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choix invalide")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
        } else {
            d = listcategorie.getSelectionModel().getSelectedItem();

           
            
          
//        Stage stageModifier = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../views/ModificationCategorie.fxml"));
        Scene scene = new Scene(root);
        stageModifier = new Stage();
        stageModifier.setScene(scene);
        stageModifier.show();
            
            
           // setNode(FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/ModifierCategorie.fxml")));
        }
    }

    @FXML
    private void search(KeyEvent event) {
        
    }
    
    
    public void list() {
        ServiceCategorie produitService = new ServiceCategorie();
        ArrayList arrayList = (ArrayList) produitService.getAll();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listcategorie.setItems(observableList);

    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        if (listcategorie.getSelectionModel().isEmpty()) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Choix invalide")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
        } else {
            Categorie cat = listcategorie.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer cette categorie");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new ServiceCategorie().delete(cat);
                System.out.println(cat);
            }
        }
        list();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/DashboardAdmin.fxml"));
        root.getChildren().setAll(pane);
        
    }
    
    private void setNode(Node node) {
        anchorpaneAfficherCategorie.getChildren().clear();
        anchorpaneAfficherCategorie.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void refresh(ActionEvent event) {
        affiche();
    }

    
    
}
