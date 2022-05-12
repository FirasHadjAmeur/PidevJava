/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Publication;
import com.esprit.services.LikeService;
import com.esprit.services.PublicationService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class StatisticsController implements Initializable {
    
    @FXML
    private AnchorPane rootElement;
    @FXML
    private Button backBtn;
    @FXML
    private VBox box;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =  new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Calcul du nombre de j'aime/Je n'aime pas par publication");
        xAxis.setLabel("Publication");       
        yAxis.setLabel("Nombre de j'aime et Je n'aime pas");
 
        PublicationService ps = new PublicationService();
        List<Publication> listPublication = ps.displayAll();
        
        LikeService ls = new LikeService();
        
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("J'aime");       
        for(Publication p : listPublication){
            series1.getData().add(new XYChart.Data(p.getTitre(), ls.countLike(p.getId())));
        }
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Je n'aime pas");
        for(Publication p : listPublication){
            series2.getData().add(new XYChart.Data(p.getTitre(), ls.countDislike(p.getId())));
        } 
        
        bc.getData().addAll(series1, series2);
        
        box.getChildren().add(bc);
    }    

    @FXML
    private void back(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherPublications.fxml"));
        try {
            Parent afficherPubView = loader.load();
                         
            AfficherPublicationsController sc = loader.getController();
            Scene scene = new Scene(afficherPubView);
            Stage stage = (Stage) rootElement.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        } 
    }
    
}
