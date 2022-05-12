/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Livreur;
import com.esprit.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author azizm
 */
public class ModifierLivreurFXMLController implements Initializable {

    @FXML
    private TextField mnom;
    @FXML
    private TextField mprenom;
    @FXML
    private TextField memail;
    @FXML
    private TextField mnumerotelephone;
    @FXML
    private TextField madresse;
    @FXML
    private ComboBox<Integer> com1;
    @FXML
    private AnchorPane livvm;
    @FXML
    private TextField idml;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ResultSet rs1 = null;
            
                
            
            
                Connection cnx = DataSource.getInstance().getCnx();
        try {
            rs1 = cnx.createStatement().executeQuery("SELECT idLivreur FROM livreur");
        } catch (SQLException ex) {
            Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
     
        try {
            while (rs1.next()) {  // loop
                
                // Now add the comboBox addAll statement
                com1.getItems().addAll(rs1.getInt("idLivreur"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModifierLivreurFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    

    @FXML
    private void modifierLivreur(ActionEvent event) throws SQLException, IOException {
        Connection cnx = DataSource.ConnectDb();
            String value1 = mnom.getText();
            String value7 = idml.getText();
            
            String value2 = mprenom.getText();
            String value3 = memail.getText();
            int value4 = Integer.parseInt(mnumerotelephone.getText());
            String value5 = madresse.getText();
            String sql = "update livreur set nomLivreur= '"+value1+"',prenomLivreur= '"+value2+"',email= '"+
                    value3+"',numTelephoneLivreur= '"+value4+"',adresseLivreur= '"+value5+"' where idLivreur='"+value7+"' ";
        PreparedStatement pst = cnx.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");
             AnchorPane pane = FXMLLoader.load(getClass().getResource("LivreurFXML.fxml"));
           livvm.getChildren().setAll(pane);
        
    }
     void setContent(Livreur livreur) {
        System.out.println(livreur.getIdLivreur());
        idml.setText(String.valueOf(livreur.getIdLivreur()));
        
          
    }
    
}
