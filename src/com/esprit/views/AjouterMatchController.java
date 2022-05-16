/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import animatefx.animation.FadeIn;
import com.esprit.modeles.Matchs;
import com.esprit.services.ServiceMatchs;
import com.esprit.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.shoppingcart.utils.AlertUtils;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Dragon
 */
public class AjouterMatchController implements Initializable {

    @FXML
    private Button btnRetour;
    @FXML
    private Text topText;
    @FXML
    private Button btnAjout;
    @FXML
    private TextField inputRefM;
    @FXML
    private TextField inputScoreA;
    @FXML
    private TextField inputScoreB;
    @FXML
    private ComboBox<String> comboEquipe2;
    @FXML
    private ComboBox<String> comboTournoi;
    @FXML
    private ComboBox<String> comboEquipe1;

    
    Connection connexion = DataSource.getInstance().getCnx();
    @FXML
    private DatePicker inputDateM;
    @FXML
    private Pane context;
    /**
     * Initializes the controller class.
     */
    
    
    public int getIDEquipeByNom(String nom) throws SQLException {
       int res_id=0;
        PreparedStatement preparedStatement = connexion.prepareStatement(
            "SELECT * FROM `equipes` WHERE nom LIKE ? ");
            preparedStatement.setString(1, nom + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
             res_id = resultSet.getInt("id");
            }
            return res_id;
     }
    public int getIDTournoiByNom(String nom) throws SQLException {
       int res_id=0;
        PreparedStatement preparedStatement = connexion.prepareStatement(
            "SELECT * FROM `tournois` WHERE nom LIKE ? ");
            preparedStatement.setString(1, nom + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
             res_id = resultSet.getInt("id");
            }
            return res_id;
     }
     public void setUi(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource(location + ".fxml")));
    };
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            String req = "SELECT * FROM `equipes`";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                String xx = rst.getString("nom");
                comboEquipe1.getItems().add(xx);
                comboEquipe2.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
       try {
            String req = "SELECT * FROM `tournois`";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                String xx = rst.getString("nom");
                comboTournoi.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }  
    
     @FXML
    private void ajout(ActionEvent event) throws SQLException, IOException {

       String DateMstring = String.valueOf(inputDateM.getValue());
        if (inputRefM.getText().isEmpty() | DateMstring.isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Remplir les champs vides");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Remplir les champs vides");
            al.showAndWait();
        } else {
            
            ServiceMatchs MatchCrud = new ServiceMatchs();
            
           LocalDate localDateM = inputDateM.getValue();
           Date dateM = java.sql.Date.valueOf(localDateM);
           
            String ref_match = inputRefM.getText();
            int score_a = Integer.parseInt(inputScoreA.getText());
            int score_b = Integer.parseInt(inputScoreB.getText());        
            
            String equipe1=comboEquipe1.getValue();
            String equipe2=comboEquipe2.getValue();
            String tournoi=comboTournoi.getValue();
            
            
            Matchs match = new Matchs(
                    getIDTournoiByNom(tournoi),
                    getIDEquipeByNom(equipe1),
                    getIDEquipeByNom(equipe2),
                    dateM,
                   ref_match,
                   score_a,
                    score_b
            );
           MatchCrud.add(match);
        //   retour(event);
           AlertUtils.makeSuccessNotification("Match Ajouté !");
            
            } 
    }

   
    


    @FXML
    private void retour(ActionEvent event) throws IOException {
        /*Parent root = FXMLLoader.load(getClass().getResource("AfficherMatchs.fxml"));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();*/
                
        
   
    }
    
}