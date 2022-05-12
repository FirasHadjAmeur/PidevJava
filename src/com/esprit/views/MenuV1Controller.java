/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author safwen
 */
public class MenuV1Controller implements Initializable {

    @FXML
    private Button BtnComm;
    @FXML
    private Button BtnVerif;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CommAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ShowExercice.fxml"));
	Scene scene = new Scene(root);
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.setScene(scene);
	stage.show();
    }

    @FXML
    private void VerifAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ShowCategoryExercice.fxml"));
	Scene scene = new Scene(root);
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.setScene(scene);
	stage.show();
    }

    @FXML
    private void returnhome(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/InterfaceMembre.fxml"));
        root.getChildren().setAll(pane);
    }
    
}
