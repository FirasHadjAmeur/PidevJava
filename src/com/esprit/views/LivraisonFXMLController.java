/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Commande;
import com.esprit.modeles.Livraison;
import com.esprit.modeles.Livreur;
import com.esprit.services.ServiceCartt;
import com.esprit.services.ServiceLivraison;
import com.esprit.utils.DataSource;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author azizm
 */
public class LivraisonFXMLController implements Initializable {

    @FXML
    private TableView<Livraison> table_livraison;
    @FXML
    private TableColumn<?,?> livraisonid;
   
ObservableList<Livraison> list = FXCollections.observableArrayList(); 
    @FXML
    private TableColumn<?, ?> commandeId;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> adresse;
    @FXML
    private TableColumn<?, ?> numtelephone;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> total;
    @FXML
    private AnchorPane root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     Connection cnx = DataSource.getInstance().getCnx();
     
         ResultSet rs = null;
        try {
            rs = cnx.createStatement().executeQuery("SELECT * FROM livraison");
        } catch (SQLException ex) {
            Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                list.add(new Livraison(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), rs.getInt(7),rs.getString(8),rs.getInt(9))); 
                
                table_livraison.setItems(list);
                table_livraison.refresh();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(LivreurFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
    }    
     private void afficher() throws SQLException{
       
            livraisonid.setCellValueFactory(new PropertyValueFactory<>("livraisonId"));
            commandeId.setCellValueFactory(new PropertyValueFactory<>("commandeId"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            numtelephone.setCellValueFactory(new PropertyValueFactory<>("numTelephone"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            total.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

            table_livraison.setItems(list);
            
            
            
            
    }

    @FXML
    private void pdfreport(ActionEvent event) throws DocumentException, FileNotFoundException {
            Commande service = new Commande();
           Document pdfReport = new Document();
           PdfWriter.getInstance(pdfReport, new FileOutputStream("Livraison.pdf"));
            pdfReport.open();
            pdfReport.add(new Paragraph("Livraison"));
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            
          
          PdfPTable my_report_table = new PdfPTable(9);
          
          /* PdfPCell tableCellColumn = new PdfPCell(new Phrase("debut vol"));
           my_report_table.addCell(tableCellColumn);
            tableCellColumn = new PdfPCell(new Phrase("fin volt"));
           my_report_table.addCell(tableCellColumn);*/
          
           PdfPCell  tableCellColumn = new PdfPCell(new Phrase("adresse"));
           my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("commandeId"));
          my_report_table.addCell(tableCellColumn);
          tableCellColumn = new PdfPCell(new Phrase("date"));
            my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("email"));
           my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("livraisonId"));
           my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("nom"));
           my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("numTelephone"));
           my_report_table.addCell(tableCellColumn);
            tableCellColumn = new PdfPCell(new Phrase("prenom"));
           my_report_table.addCell(tableCellColumn);
           
           
            tableCellColumn = new PdfPCell(new Phrase("totalCost"));
            my_report_table.addCell(tableCellColumn);
            
            
            double h= 0;
            table_livraison.getItems().forEach((Livraison e) -> {
                
               PdfPCell tableCell = new PdfPCell(new Phrase(e.getAdresse()));
                my_report_table.addCell(tableCell);
                
                String idc = "" + e.getCommandeId();
                  tableCell = new PdfPCell(new Phrase(idc));
                my_report_table.addCell(tableCell);
                
              
                tableCell = new PdfPCell(new Phrase(e.getEmail()));
                my_report_table.addCell(tableCell);
                
                 String idl = "" + e.getLivraisonId();
                tableCell = new PdfPCell(new Phrase(idl));
                my_report_table.addCell(tableCell);
                
                tableCell = new PdfPCell(new Phrase(e.getNom()));
                my_report_table.addCell(tableCell);
                
                 String numT = "" + e.getNumTelephone();
                tableCell = new PdfPCell(new Phrase(numT));
                my_report_table.addCell(tableCell);
                
                tableCell = new PdfPCell(new Phrase(e.getPrenom()));
                my_report_table.addCell(tableCell);
                
                String Tot = "" + e.getTotalCost();
                tableCell = new PdfPCell(new Phrase(Tot));
                my_report_table.addCell(tableCell); 
                
                
                
                 
            });
            /* Attach report table to PDF */
            pdfReport.add(my_report_table);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            
            
            
            pdfReport.close();
            
            Alert alertReservation = new Alert(Alert.AlertType.INFORMATION);
            alertReservation.setTitle("Extraction en PDF");
            alertReservation.setHeaderText(null);
            alertReservation.setContentText("PDF report has been created.\nYou'll find "
                    + "the file under: C:\\Users\\HAYKEL\\Desktop\\PIDEVPDF");
            alertReservation.showAndWait();
    }

@FXML
    private void trier(ActionEvent event) {
          ObservableList<Livraison> recList = FXCollections.observableArrayList();
      livraisonid.setCellValueFactory(new PropertyValueFactory<>("livraisonId"));
            commandeId.setCellValueFactory(new PropertyValueFactory<>("commandeId"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            numtelephone.setCellValueFactory(new PropertyValueFactory<>("numTelephone"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            total.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        ServiceLivraison rt = new ServiceLivraison();
        List old = rt.trierreservationdate();
        recList.addAll(old);
        table_livraison.setItems(recList);
        table_livraison.refresh();
    }

    @FXML
    private void return_home(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/DashboardAdmin.fxml"));
        root.getChildren().setAll(pane);
    }
     
  
    
}
