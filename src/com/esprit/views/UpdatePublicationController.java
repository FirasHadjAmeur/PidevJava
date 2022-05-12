/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Publication;
import com.esprit.services.PublicationService;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class UpdatePublicationController implements Initializable {

    @FXML
    private TextField titleTxf;
    
    @FXML
    private TextArea contentTxa;
    
    @FXML
    private Button addImagebtn;
    
    private ImageView imageView;
    
    @FXML
    private Label idPublication;
    @FXML
    private VBox imgBox;
    
    private Stage dialog;
    private Scene scene;
    
    private Stage stage;
private String filename;
    private ObservableList<Publication> observableList;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void updatePublication(ActionEvent event) {  
        
        if (getTitle().isEmpty() || getContent().isEmpty() || getImage().isEmpty()){
                       
            Alert alerts = new Alert(Alert.AlertType.WARNING);
            alerts.setTitle("Warning");
            alerts.setHeaderText(null);
            alerts.setContentText("Veuillez remplir les champs");
            alerts.show();
        }
        
        PublicationService ps = new PublicationService();
        Publication p = new Publication(getId(), getTitle(), getContent(), "");
        ps.update(p);  
        
        // rafraichir la liste des publications
        observableList.clear();
        observableList.addAll(ps.displayAllByUser(1));
        
        dialog.close();
    }

    @FXML
    private void addImage(ActionEvent event) {
    }
    
    public void setTitle(String title){
        this.titleTxf.setText(title);
    }
    
    public void setContent(String content){
        this.contentTxa.setText(content);
    }
    
     public void setId(String content){
        this.idPublication.setText(content);
    }
     
    public void setImage(String filename){
       /* Image i = new Image("file:///C:/Users/HAYKEL/Desktop/" + filename);
        ImageView imgView = new ImageView(i);
        this.imgBox.getChildren().add(imgView);*/
        this.filename = filename;
        //Image i = new Image("file\\C:\\Users\\HAYKEL\\Desktop\\" + filename);  
        File file =new File("C:\\Users\\HAYKEL\\Desktop\\"+filename);
        
        Image image1= new Image(file.toURI().toString());
        ImageView imgView = new ImageView(image1);
        this.imgBox.getChildren().add(imgView);
       // this.imgBox.setImage(image1);
        
        
    }
    
    public ObservableList<Node> getImage(){
        return this.imgBox.getChildren();
    }
     
    public String getTitle(){
        return this.titleTxf.getText();
    }
    
     public String getContent(){
        return this.contentTxa.getText();
    }
    
    public String getId(){
        return this.idPublication.getText();
    }
    
    public void setDialog(Stage dialog){
        this.dialog = dialog;
    }
    
     public void setStage(Stage stage){
        this.stage = stage;
    }
     
    public void setList(ObservableList<Publication> publications) {
        this.observableList = publications;
    }
    
}
