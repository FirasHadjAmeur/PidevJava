/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Publication;
import com.esprit.services.PublicationService;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddPublicationController implements Initializable {

    @FXML
    private TextField titleTxf;
    @FXML
    private TextArea contentTxa;
    @FXML
    private Button addImagebtn;
    @FXML
    private Button addPubBtn;
    @FXML
    private Label idPublication;
    
     private Desktop desktop = Desktop.getDesktop();
     private File file;
     
     private Stage stage;
     
     private ObservableList<Publication> observableList;
    @FXML
    private ImageView imageView;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addImage(ActionEvent event) {
        
        final FileChooser fileChooser = new FileChooser(); 
  
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        
        file = fileChooser.showOpenDialog(this.stage);
        if (file != null) {
            Image i = new Image("file:///" + file.getAbsolutePath());
            imageView.setImage(i);
        }
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    private void addPublication(ActionEvent event) {
        
        if (titleTxf.getText().isEmpty() || contentTxa.getText().isEmpty()){ // control de saisie sur image
                       
            Alert alerts = new Alert(Alert.AlertType.WARNING);
            alerts.setTitle("Warning");
            alerts.setHeaderText(null);
            alerts.setContentText("Veuillez remplir les champs!");
            alerts.show();
        } else {
            PublicationService ps = new PublicationService();
            Publication p = new Publication(titleTxf.getText(), contentTxa.getText(), file.getName());
                  
            ps.create(p);
             //	System.out.println(ps.displayAllByUser(1));	
            // rafraichir la liste des publications
            observableList.clear();
            observableList.addAll(ps.displayAllByUser(1));
            
            closeStage(event);
        }  
    }
     
    public void setList(ObservableList<Publication> publications) {
        this.observableList = publications;
    }
    
    private void closeStage(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
 /*
    public boolean SendMail(){
        
        String from = "pidevers3a10@gmail.com";
        String to = "ahmedriadh.khezami@esprit.tn";
        String host = "localhost";
        String password = "Testtest123";
        String sub;
        String content;
        
        sub = "Bienvenue sur notre Plateforme";
        content = "Bonjour Mr Riadh KHEZAMI. Une nouvelle publication a été ajoutée à la plateforme";
            
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");            
           
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, password);
            }
        });
        
        try {
            MimeMessage m = new MimeMessage(session);
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(sub);
            m.setText(content);
            Transport.send(m);
            return true;
         } catch (MessagingException e) {
            e.printStackTrace();
         }

        return false;
    }
*/

    @FXML
    private void cancelAdd(ActionEvent event) {
        closeStage(event);
    }
}
