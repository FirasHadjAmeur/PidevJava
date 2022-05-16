/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.User;
import com.esprit.services.ServiceUser;
import com.esprit.test.FXMainAdmin;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Achref
 */
public class Gestion_userController implements Initializable {

    @FXML
    private TableColumn<User, Integer> cl_id_user;
    @FXML
    private TableColumn<User, String> cl_nom_user;
    @FXML
    private TableColumn<User, String> cl_prenom_user;
  
    @FXML
    private TableColumn<User, String> cl_email_user;
    @FXML
    private TableView<User> table;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
   
    @FXML
    private TextField id;
    @FXML
    private Button up;
    @FXML
    private Button del;
    @FXML
    private TextField recherche_user;
    @FXML
    private TableView<User> table1;
    @FXML
    private TableColumn<User, Integer> cl_id_user1;
    @FXML
    private TableColumn<User, String> cl_nom_user1;
    @FXML
    private TableColumn<User, String> cl_prenom_user1;
    @FXML
    private TableColumn<User, String> cl_email_user1;
    @FXML
    private TableColumn<User, String> cl_role_user1;
    @FXML
    private TableColumn<User, String> cl_role;
    @FXML
    private TextField mail;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button pdf;
    
    ServiceUser sp = new ServiceUser();


     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setDisable(true);
        table1.setVisible(false);
        showUser();
    }
 
public  void showUser(){
    ServiceUser sp = new ServiceUser();
    ObservableList<User>list = sp.getAll();
    cl_id_user.setCellValueFactory(new PropertyValueFactory<>("id"));
    cl_nom_user.setCellValueFactory(new PropertyValueFactory<>("last_name"));
    cl_prenom_user.setCellValueFactory(new PropertyValueFactory<>("name"));
    //cl_tel_user.setCellValueFactory(new PropertyValueFactory<>("tel_user"));
    cl_email_user.setCellValueFactory(new PropertyValueFactory<>("email"));
    cl_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
    table.setItems(list);
}
    @FXML
    private void handleMouseAction(MouseEvent event) {
         User user = table.getSelectionModel().getSelectedItem();
         id.setText(""+user.getId_user());
         nom.setText(""+user.getNom_user());
         prenom.setText(""+user.getPrenom_user());
      //   tel.setText(""+user.getTel_user());
         mail.setText(""+user.getEmail_user());
         id.setDisable(true);
         
    }

    @FXML
    private void update(ActionEvent event) {
        
       int  id_u  = Integer.parseInt(id.getText());
       ServiceUser sp = new ServiceUser();
       int options = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Vous etes sure de Modifier  cet utilisateur" ,"SERIOUS QUESTION", options, 3);
        if (result == JOptionPane.YES_OPTION) {
         User a = new User(id_u,nom.getText(), prenom.getText());
        sp.modifier(a);
                        JOptionPane.showMessageDialog(null, "L'utilisateur a été modifié");

        showUser();
        } else if (result == JOptionPane.NO_OPTION) {
            
           showUser();
        }    
    }

   
    @FXML
    private void delete(ActionEvent event) {
        int  id_u  = Integer.parseInt(id.getText());
        ServiceUser sp = new ServiceUser();
        User a = new User(id_u);
      
        int options = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Vous êtes sure de Supprimer  cet utilisateur" ,"SERIOUS QUESTION", options, 3);
        if (result == JOptionPane.YES_OPTION) {
        sp.supprimer(a);
        JOptionPane.showMessageDialog(null, "L'utilisateur a été supprimé");
         
        showUser();
        } else if (result == JOptionPane.NO_OPTION) {

            showUser();
        } 
        
        
    }

    public void recherche(ActionEvent event){
    ServiceUser sp = new ServiceUser();
    
    ObservableList<User>list = sp.getUser(recherche_user.getText());
     cl_id_user.setCellValueFactory(new PropertyValueFactory<>("id"));
    cl_nom_user.setCellValueFactory(new PropertyValueFactory<>("last_name"));
    cl_prenom_user.setCellValueFactory(new PropertyValueFactory<>("name"));
    //cl_tel_user.setCellValueFactory(new PropertyValueFactory<>("tel_user"));
    cl_email_user.setCellValueFactory(new PropertyValueFactory<>("email"));
    cl_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
    table1.setItems(list);
    }
    
    @FXML
    private void EnterRecherche(ActionEvent event) {
        String h = "";
        recherche_user.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    recherche(event);
                    if (recherche_user.getText().equals(h)) {
                        table1.setVisible(false);
                        table.setVisible(true);
                    } else {
                        table1.setVisible(true);
                    }
                }
            }
        });
    }

    @FXML
    private void handleMouseAction1(MouseEvent event) {
        User user = table1.getSelectionModel().getSelectedItem();
         id.setText(""+user.getId_user());
         nom.setText(""+user.getNom_user());
         prenom.setText(""+user.getPrenom_user());
        // tel.setText(""+user.getTel_user());
         mail.setText(""+user.getEmail_user());
         
         id.setDisable(true);
    }

    @FXML
    private void diselect(ActionEvent event) {
        id.setText("");
         nom.setText("");
         prenom.setText("");
        // tel.setText("");
         mail.setText("");
         id.setDisable(true);
    }

    @FXML
    private void ActionDashboard(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/DashboardAdmin.fxml"));
        rootPane.getChildren().setAll(pane);  
    }

    @FXML
     public void generatePDF() throws IOException, DocumentException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (.pdf)", ".pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(FXMainAdmin.primaryStage);
                        JOptionPane.showMessageDialog(null, " Le PDF a été généré");

        sp.generatePDF(file);
    }

    

}
