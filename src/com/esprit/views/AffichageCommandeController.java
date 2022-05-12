/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Cart;
import com.esprit.modeles.Commande;
import com.esprit.modeles.Livraison;
import com.esprit.modeles.Livreur;
import com.esprit.services.ServiceCartt;
import com.esprit.services.ServiceCommande;
import com.esprit.services.ServiceLivraison;
import com.esprit.services.ServiceLivreur;
import com.esprit.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author azizm
 */
public class AffichageCommandeController implements Initializable {

    @FXML
    private TableView<Commande> table_commande;

    @FXML
    private TableColumn<?,?> total;
    @FXML
    private TableColumn<?,?> commandeId;
    @FXML
    private TableColumn<?,?> idcart;
    @FXML
    private TableColumn<?,?> nom;
    @FXML
    private TableColumn<?,?> prenom;
    @FXML
    private TableColumn<?,?> email;
    @FXML
    private TableColumn<?,?> adresse;
    @FXML
    private TableColumn<?,?> numtelephone;
    @FXML
    private TableColumn<?,?> date;
ObservableList<Commande> listC; 
    @FXML
    private Button Cannuler;
    @FXML
    private Button Cvalider;
    @FXML
    private TextField Cnom;
    @FXML
    private TextField Cnom1;
    @FXML
    private TextField Cnom2;
    @FXML
    private TextField Cnom3;
    @FXML
    private TextField Cnom4;
    @FXML
    private TextField Cnom5;
    @FXML
    private TextField Cnom6;
    @FXML
    private TextField Cnom7;
    @FXML
    private AnchorPane comm;
    

    public AffichageCommandeController() {
        this.listC = FXCollections.observableArrayList();
    }
    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection cnx = DataSource.getInstance().getCnx();
         ResultSet rs = null;
        try {
            rs = cnx.createStatement().executeQuery("SELECT * FROM commande");
        } catch (SQLException ex) {
            Logger.getLogger(AffichageCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                listC.add(new Commande(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getDate(8),rs.getInt(9)));
                
                table_commande.setItems(listC);
                table_commande.refresh();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AffichageCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
    }    
     private void afficher() throws SQLException{
       
            commandeId.setCellValueFactory(new PropertyValueFactory<>("commandeId"));
            idcart.setCellValueFactory(new PropertyValueFactory<>("idcart"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            numtelephone.setCellValueFactory(new PropertyValueFactory<>("numTelephone"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            total.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

            table_commande.setItems(listC);
            
            
            
            
    }
    @FXML
    private void AnnulerCommande(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Commande x = gettempCommande(edittedcell);

        if (x != null) {

            int i = x.getCommandeId();
            ServiceCommande cat = new ServiceCommande();

            int s = cat.deleteCommande(i);
            

            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Commande Annuler");
                alert.showAndWait();
                table_commande.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }
          UpdateTable();
    }
    public Commande gettempCommande(TableColumn.CellEditEvent edittedCell) {
        Commande test = table_commande.getSelectionModel().getSelectedItem();
        return test;
    }
        
    

    
        

   
   
        public void UpdateTable() throws SQLException{
        commandeId.setCellValueFactory(new PropertyValueFactory<>("commandeId"));
            idcart.setCellValueFactory(new PropertyValueFactory<>("idcart"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            numtelephone.setCellValueFactory(new PropertyValueFactory<>("numTelephone"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            total.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

         listC = DataSource.getDataCommande();
        
        table_commande.setItems(listC);
        }
@FXML
    private void ValiderCommande(ActionEvent event) throws SQLException, IOException {
        ServiceLivraison r = new ServiceLivraison();
                int c = Integer.parseInt(Cnom.getText());

        int i = Integer.parseInt(Cnom5.getText());
       int v = Integer.parseInt(Cnom7.getText());

        r.ajouter(new Livraison(c,Cnom1.getText(),Cnom2.getText(),Cnom3.getText(),Cnom4.getText(),i,Cnom6.getText(),v));

        JOptionPane.showMessageDialog(null,"Commande Valider !");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("LivraisonFXML.fxml"));
           comm.getChildren().setAll(pane);
    }
    @FXML
    private void getselected(javafx.scene.input.MouseEvent event) throws SQLException {
        
        int index = table_commande.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
          //  Connection cnx = DataSource.getInstance().getCnx();
     //ResultSet rsd =null ;
    Cnom.setText(commandeId.getCellData(index).toString());
    Cnom1.setText(nom.getCellData(index).toString());
    Cnom2.setText(prenom.getCellData(index).toString());
    Cnom3.setText(email.getCellData(index).toString());
    Cnom4.setText(adresse.getCellData(index).toString());
    Cnom5.setText(numtelephone.getCellData(index).toString());
    Cnom6.setText(date.getCellData(index).toString());
    Cnom7.setText(total.getCellData(index).toString());
    
     
    }

    @FXML
    private void return_cmd(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/DashboardAdmin.fxml"));
        comm.getChildren().setAll(pane);
    }


 
 }

