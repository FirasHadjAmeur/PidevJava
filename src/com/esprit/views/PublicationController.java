/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Commentaire;
import com.esprit.modeles.Like;
import com.esprit.modeles.Publication;
import com.esprit.services.CommentaireService;
import com.esprit.services.LikeService;
import com.esprit.services.PublicationService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PublicationController implements Initializable {

    @FXML
    private Label pubTitle;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Label pubContent;
    @FXML
    private Button likeBtn;
    @FXML
    private Button dislikeBtn;
    @FXML
    private TextArea commentTxtArea;
    @FXML
    private Button commentBtn;
    @FXML
    private VBox commentsBox;
    
    private String id;
    private String filename;
    
    private int nbrLike;
    private int nbrDislike;
    
    private Stage stage;
    @FXML
    private VBox myBox;
    @FXML
    private HBox h1;
    @FXML
    private HBox h2;
    @FXML
    private HBox h3;
    
    private ObservableList<Publication> observableList;
    @FXML
    private ImageView imageView;
    @FXML
    private Label pubDate;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setTitle(String title) {
	this.pubTitle.setText(title);
    }

    public void setContent(String content) {
	this.pubContent.setText(content);
    }
    
    public void setDate(String date) {
	this.pubDate.setText(date);
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getTitle(){
        return this.pubTitle.getText();
    }
    
    public String getContent(){
        return this.pubContent.getText();
    }
    
    public void setImage(String filename){
        this.filename = filename;
        //Image i = new Image("file\\C:\\Users\\HAYKEL\\Desktop\\" + filename);  
        File file =new File("C:\\Users\\HAYKEL\\Desktop\\"+filename);
        Image image1= new Image(file.toURI().toString());
        this.imageView.setImage(image1); 
    
    
    
    
    
    
    }
    
    public String getImage(){
        return this.filename;
    }
    
    public void setNbrLike(int nbrLike){
        likeBtn.setText(nbrLike + " J'aime");
    }
    
    public int getNbrLike(){
        return this.nbrLike;
    }
    
     public void setNbrDislike(int nbrDislike){
        dislikeBtn.setText(nbrDislike + " Je n'aime pas");
    }
    
    public int getNbrDislike(){
        return this.nbrDislike;
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public Stage getStage(){
        return stage;
    }
    
    
    public void setComments(List<Commentaire> listComments){
        for(Commentaire com : listComments){
            Label cl = new Label(com.getContenu());
            commentsBox.getChildren().add(cl);
        }
    }

    @FXML
    private void deletePublication(ActionEvent event) {
        
        Stage dialog = new Stage();
        
        VBox deleteBox = new VBox();
        Label deleteLabel = new Label("Êtes-vous sûre de vouloir supprimer cette publication ?");
        Button delBtn = new Button("Supprimer");
        deleteBox.getChildren().add(deleteLabel);
        deleteBox.getChildren().add(delBtn);
        Scene dialogScene = new Scene(deleteBox);
                                        
        dialog.setScene(dialogScene);
        dialog.show(); 
                                        
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
                
            @Override
            public void handle(final ActionEvent e) {

                PublicationService ps = new PublicationService();

                ps.delete(getId());

                // rafraichir la liste des publications
                observableList.clear();
                observableList.addAll(ps.displayAllByUser(1));
                
                dialog.close();
             }
         });
    }

    @FXML
    private void updatePublication(ActionEvent event) {
        
        Stage dialog = new Stage();
                                        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updatePublication.fxml"));
        try {
                                                                                                                                    
            Parent upView = loader.load();
            Scene dialogScene = new Scene(upView);
            dialog.setScene(dialogScene);
            dialog.show();

            UpdatePublicationController upc = loader.getController();
            upc.setId(getId()); 
            upc.setTitle(getTitle());
            upc.setContent(getContent());
            upc.setImage(getImage());

            upc.setList(observableList);
            
            upc.setDialog(dialog);
            upc.setStage(stage);
                                                                                                                                                                                                  
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        } 
    }

    @FXML
    private void addLike(ActionEvent event) {
        
        LikeService ls = new LikeService();
        
        likeBtn.setText(ls.countLike(getId()) + 1 + " J'aime");
        Like like = new Like(1, getId());
        ls.create(like);
    }

    @FXML
    private void addDislike(ActionEvent event) {
        
        LikeService ls = new LikeService();
        
        dislikeBtn.setText(ls.countDislike(getId()) + 1 + " Je n'aime pas");
        Like like = new Like(0, getId());
        ls.create(like);
    }

    @FXML
    private void addComment(ActionEvent event) {
        
        CommentaireService cs = new CommentaireService();
        Commentaire commentaire = new Commentaire(commentTxtArea.getText(), Integer.parseInt(getId()));
        cs.create(commentaire);
        
        commentsBox.getChildren().add(new Label(commentTxtArea.getText()));
        commentTxtArea.clear();
    }
    
   public void setList(ObservableList<Publication> publications) {
        this.observableList = publications;
    }
    
}
