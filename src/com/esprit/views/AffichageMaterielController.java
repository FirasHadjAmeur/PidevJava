/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import com.esprit.modeles.Categorie;
import com.esprit.modeles.Materiels;
import com.esprit.services.ServiceMateriels;
import com.esprit.utils.DataSource;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class AffichageMaterielController implements Initializable {

    ServiceMateriels sm = new ServiceMateriels();
    ObservableList<Materiels> observableListmat = FXCollections.observableArrayList(sm.getAll());
    
    @FXML
    private AnchorPane anchorpaneAfficherCategorie;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXTextField filtre;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXListView<Materiels> listmat;
    @FXML
    private JFXTextField tfnom;
    @FXML
    private JFXTextField tfqte;
    @FXML
    private JFXComboBox<String> comCat;
    Connection cnx = DataSource.getInstance().getCnx();
     Alert alertinfo = new Alert(Alert.AlertType.INFORMATION);
     Alert alert = new Alert(Alert.AlertType.WARNING);
     Alert alertconfirm = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private JFXTextField tfid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            String req = "select * from categoriem";
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                String xx = rst.getString("nom");
                
                
                comCat.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
tfid.setVisible(false);
affiche();
        
      

//      DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//      System.out.println(format.format(date));
       
        
      
        
    }    
public void affiche() {
    listmat.setItems(observableListmat);
        
    }
    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        int categoriem_id=0;
        ServiceMateriels sm= new ServiceMateriels();
            String nom = tfnom.getText();
            int qte = Integer.parseInt(tfqte.getText());
            String categorie=comCat.getValue();
            PreparedStatement preparedStatement = cnx.prepareStatement(
            "select * from categoriem WHERE nom LIKE ? ");
            preparedStatement.setString(1, categorie + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
              categoriem_id = resultSet.getInt("id");
            }
            
        Materiels mat = new Materiels(
                    qte,
                    nom,
                    categoriem_id);
        
            
            if(sm.add(mat)){
                alertinfo.setTitle("ADDED SUCCESSFULY ");
		
		alertinfo.setContentText("Product has been succelfuly added !");
		alertinfo.showAndWait();
                
                listmat.getItems().add(mat);
                ObservableList<Materiels> UpdatedListView = listmat.getItems();
                listmat.setItems(UpdatedListView);
                affiche();
                 
  
            }else{
            alert.setTitle("ADD ERROR ");
		
		alert.setContentText("ADD operation error !");
		alert.showAndWait();
            }
    
        
    }
        

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        int categoriem_id=0;
        Materiels p =new Materiels();
        int qte = Integer.parseInt(tfqte.getText());
        int id = Integer.parseInt(tfid.getText());
        String Nom = tfnom.getText();
        String categorie=comCat.getValue();
        PreparedStatement preparedStatement = cnx.prepareStatement(
            "select * from categoriem WHERE nom LIKE ? ");
            preparedStatement.setString(1, categorie + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
              categoriem_id = resultSet.getInt("id");
            }
        
        p.setId(id);
        p.setNom(Nom);
        p.setCategoriem_id(categoriem_id);
        p.setQte(qte);
        
        
         System.out.println("update "+sm.update(p));
       if( sm.update(p)){
            
            alertinfo.setTitle("UPDATE SUCCESSFULY ");
		
		alertinfo.setContentText("An existing Product has been succesfuly UPDATED !");
		alertinfo.showAndWait();
                
               listmat.getItems().set( listmat.getSelectionModel().getSelectedIndex(), p );
                
  
            }else{
            alert.setTitle("UPDATE UNSUCCESSFULY ");
		
		alert.setContentText("UPDATE operation error !");
		alert.showAndWait();
            }
    }

    @FXML
    private void search(KeyEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        int categoriem_id=0;
        Materiels p =new Materiels();
        int qte = Integer.parseInt(tfqte.getText());
        int id = Integer.parseInt(tfid.getText());
        String Nom = tfnom.getText();
        String categorie=comCat.getValue();
        PreparedStatement preparedStatement = cnx.prepareStatement(
            "select * from categoriem WHERE nom LIKE ? ");
            preparedStatement.setString(1, categorie + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
              categoriem_id = resultSet.getInt("id");
            }
        
        p.setId(id);
        p.setNom(Nom);
        p.setCategoriem_id(categoriem_id);
        p.setQte(qte);
        alertconfirm.setTitle("DELETE PRODUCT ");
      alertconfirm.setHeaderText("Are you sure want to delte this Product ?");
      alertconfirm.setContentText("Name :"+tfnom.getText());
      Optional<ButtonType> confirm = alertconfirm.showAndWait();
      
      if(confirm.get() == ButtonType.OK){
            if(sm.delete(p)){
            
                alertinfo.setTitle("DELETED SUCCESSFULY ");
		
		alertinfo.setContentText("An existing command has been succesfuly deleted !");
		alertinfo.showAndWait();
                
                 listmat.getItems().remove(listmat.getSelectionModel().getSelectedItem());
                    ObservableList<Materiels> UpdatedListView = listmat.getItems();
                    listmat.setItems(UpdatedListView);
                 
            }else{
                alert.setTitle("DELETED UNSUCCESSFULY ");
		
		alert.setContentText("Delete operation error !");
		alert.showAndWait();
            }
        }
    }

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void OnSelect(MouseEvent event) {
        listmat.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Materiels>() {
	@Override

	public void changed(ObservableValue<? extends Materiels> arg0, Materiels arg1, Materiels arg2) {

	Materiels p = listmat.getSelectionModel().getSelectedItem();
                                  
               String qte = String.valueOf(p.getQte());
               String id = String.valueOf(p.getId());
               
               
            tfnom.setText(p.getNom());
            tfqte.setText(qte);
            tfid.setText(id);
            
            
            
	}	

    });
    }
    
}
