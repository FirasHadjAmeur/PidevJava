/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;


import com.esprit.modeles.Exercice;


import com.esprit.services.ExerciceCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author safwen
 */
public class ShowExerciceController implements Initializable {

    @FXML
    private TableView<Exercice> table;
    public static  TableView<Exercice> table2;
    @FXML
    private TableColumn<Exercice, String> col_id;
    @FXML
    private TableColumn<Exercice, String> col_name;
    @FXML
    private TableColumn<Exercice, String> col_nbrset;
    @FXML
    private TableColumn<Exercice, String> col_photo;
    @FXML
    private TableColumn<Exercice, String> col_likedislike;
    
    @FXML
    private TableColumn<Exercice, String> col_idcategory;
    

    public static ObservableList<Exercice>oblist=FXCollections.observableArrayList();
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnstat;
    
     public static int id;
    @FXML
    private AnchorPane root;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
          table2=table;
         
        ExerciceCRUD c = new ExerciceCRUD();
        
         col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
         col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
         col_nbrset.setCellValueFactory(new PropertyValueFactory<>("nbrset"));
         col_photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
         col_likedislike.setCellValueFactory(new PropertyValueFactory<>("like_dislike"));
         col_idcategory.setCellValueFactory(new PropertyValueFactory<>("id_categorie_id"));
         
        
                 
         
         
         
                 
         table.setItems(c.afficherExercice());
         edittabcol();
    }    
    
    
     private void edittabcol(){
                    
       table.setEditable(true);
                       
    }

    @FXML
    private void ajouteract(MouseEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddExercice.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            stage.setTitle("Ajouter Exercice");
            
                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifieract(MouseEvent event) {
         try {
    Exercice u =table2.getSelectionModel().getSelectedItem();            
    if(u== null)
    {//msg alertt
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Modifier Exercice");
alert.setHeaderText(null);
alert.setContentText("Vous devez selectionnez un Exercice!");

alert.showAndWait();
        return;
            }
                 id=u.getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateExercice.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            stage.setTitle("Modifier Exercice");
             System.out.println(u);
             System.out.println(u.getId());    

                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    private void supprimieract(MouseEvent event) {
        Exercice u =table.getSelectionModel().getSelectedItem();
           if(u== null)
    {//msg alertt
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Supprimer Exercice");
alert.setHeaderText(null);
alert.setContentText("Vous devez selectionnez un Exercice!");

alert.showAndWait();
        return;
            }
        
   
    ExerciceCRUD UC = new ExerciceCRUD();
    UC.supprimerExercice(u);
     table.setItems(UC.afficherExercice());
    
    }

    @FXML
    private void statact(MouseEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistiques.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            stage.setTitle("Statistiques");
            
                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     public int idd(){
        return id ;
    }

    @FXML
    private void return_menu(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/MenuV1.fxml"));
        root.getChildren().setAll(pane);
    }
    
}
