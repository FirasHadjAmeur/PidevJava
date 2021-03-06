/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.utils.MyConnection;
import javafx.event.EventHandler;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author safwen
 */
public class StatistiquesController implements Initializable {

    @FXML
    private PieChart pc2;

    
     ObservableList <PieChart.Data> ol = FXCollections.observableArrayList();
     ObservableList <PieChart.Data> o2 = FXCollections.observableArrayList();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
        
        
          try {
           
        
        String requete = "SELECT exercice.name, Count(*) AS Nombre_de_Fois FROM exercice GROUP BY exercice.name";
        PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
        ResultSet rs =  pst.executeQuery(requete);
        
        
     
        while(rs.next()){
            
            ol.addAll(new PieChart.Data(rs.getString(1),rs.getInt(2)));
                    pc2.setData(ol);
                    
                    pc2.setLegendSide(Side.LEFT);
                    
                    FadeTransition f = new FadeTransition(Duration.seconds(6),pc2);
                  
                    f.setFromValue(0);
                    f.setToValue(1);
                    f.play();
        }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                     for (PieChart.Data data : pc2.getData())
                     {
                         data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,  new EventHandler<MouseEvent>() {
                             
                             public void handle(MouseEvent event) {
                               JOptionPane.showMessageDialog(null,"Name Exercice   -- "+ data.getName()+ "nombre d'Exercice --" +(int)data.getPieValue());   
                             }
                         });
                     }
                     
       
                     
    }    

   
}
