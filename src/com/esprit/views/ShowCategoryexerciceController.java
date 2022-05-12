/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Categoryexercice;

import com.esprit.services.CategoryexerciceCRUD;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
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
public class ShowCategoryexerciceController implements Initializable {

    @FXML
    private TableView<Categoryexercice> table;
    public static  TableView<Categoryexercice> table2;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;

    
     public static ObservableList<Categoryexercice>oblist=FXCollections.observableArrayList();
     public static int id;
    @FXML
    private Button btnpdf;
    @FXML
    private TableColumn<Categoryexercice, String> col_id;
    @FXML
    private TableColumn<Categoryexercice, String> col_name;
    @FXML
    private TableColumn<Categoryexercice, String> col_description;
    @FXML
    private TableColumn<Categoryexercice, String> col_photo;
    @FXML
    private TableColumn<Categoryexercice, String> col_likedislike;
    @FXML
    private AnchorPane root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          table2=table;

         CategoryexerciceCRUD c = new CategoryexerciceCRUD();
        
         col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
         col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
         col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
         col_photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
         col_likedislike.setCellValueFactory(new PropertyValueFactory<>("like_dislike"));
                 
         table.setItems(c.afficherCategoryexercice());
      
        // TODO
    }    
    
    @FXML
    private void addact(MouseEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCategoryex.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            stage.setTitle("Ajouter Categorie exercice");
            
                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifact(MouseEvent event) {
         try {
    Categoryexercice u =table2.getSelectionModel().getSelectedItem();            
    if(u== null)
    {//msg alertt
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Modifier Category Exercice");
alert.setHeaderText(null);
alert.setContentText("Vous devez selectionnez un Categorie!");

alert.showAndWait();
        return;
            }
                 id=u.getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateCategoryex.fxml"));
            Parent root =loader.load();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            stage.setTitle("Modifier Categorie");
             System.out.println(u);
             System.out.println(u.getId());    

                 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void suppact(MouseEvent event) {
        Categoryexercice u =table.getSelectionModel().getSelectedItem();
           if(u== null)
    {//msg alertt
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Supprimer Categorie");
alert.setHeaderText(null);
alert.setContentText("Vous devez selectionnez une Categorie!");

alert.showAndWait();
        return;
            }
        
   
    CategoryexerciceCRUD UC = new CategoryexerciceCRUD();
    UC.supprimerCategoryexercice(u);
     table.setItems(UC.afficherCategoryexercice());
    }

    public int idd(){
        return id ;
    }

    @FXML
    private void pdf(MouseEvent event) {
        
          PrinterJob job = PrinterJob.createPrinterJob();
 if(job != null){
            
   job.printPage(table);
   job.endJob();
 }
    }

    @FXML
    private void return_menu(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/MenuV1.fxml"));
        root.getChildren().setAll(pane);
    }
}
