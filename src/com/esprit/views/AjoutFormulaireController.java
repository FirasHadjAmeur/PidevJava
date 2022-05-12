/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Publication;
import com.esprit.services.PublicationService;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutFormulaireController implements Initializable {

    @FXML
    private TextField tf_titre;
    @FXML
    private Button btn_ajouter;
    @FXML
    private TextField tf_contenu;
    @FXML
    private ImageView mg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
       /* if (tf_titre.getText().equals("")==true)
        {
            ErrorLabel.setText("L'adresse e-mail est obligatoire !");
        } 
        else           // tous les conditions précédents sont faux
        {*/
            String titre = tf_titre.getText();
            String contenu = tf_contenu.getText();
            Publication p = new Publication(titre, contenu, new Timestamp(new Date().getTime()), "", 1);
            PublicationService ps = new PublicationService();
            
            ps.create(p);
           

            //btn_ajouter.getScene().setRoot(root);
    }
    
}
