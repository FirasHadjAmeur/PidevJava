
package com.esprit.views;

import com.esprit.modeles.Cart;
import com.esprit.modeles.Commande;

import com.esprit.services.ServiceCommande;
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
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author azizm
 */
public class CommandeFXMLController implements Initializable {

    @FXML
    private TextField Cnom;
    @FXML
    private TextField Cprenom;
    @FXML
    private TextField Cemail;
    @FXML
    private TextField Cadresse;
    
    ObservableList<Commande> list = FXCollections.observableArrayList();
    @FXML
    private TextField CnumTelephonenumTelephone;
    @FXML
    private DatePicker Date;
      @FXML
    private ComboBox<Integer> idcart;
    /**
     * Initializes the controller class.
     */
    private Connection cnx= null ;
    private ResultSet rs = null ;
    
    private ResultSet rs2 = null ;
    
    private ResultSet rs3 = null ;
    @FXML
    private TextField idc;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
     Connection cnx = DataSource.getInstance().getCnx();
        try {
            rs = cnx.createStatement().executeQuery("SELECT id FROM cart");
        } catch (SQLException ex) {
            Logger.getLogger(CommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next())
                
                // Now add the comboBox addAll statement
                idcart.getItems().addAll(rs.getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(CommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    

    @FXML
    private void ajoutercommande(ActionEvent event) throws SQLException {
        
         Connection cnx = DataSource.getInstance().getCnx();
            cnx = DataSource.ConnectDb();
         ServiceCommande s2 = new ServiceCommande();
        String id9=idc.getText();
            rs = cnx.createStatement().executeQuery("SELECT productid FROM cart where id='"+id9+"'");
            rs.next();
            String nomp = rs.getString("productid");
            
            rs2 = cnx.createStatement().executeQuery("SELECT price FROM products where title='"+nomp+"'");
            rs2.next();
            
           int pri = rs2.getInt("price");
         
            rs3 = cnx.createStatement().executeQuery("SELECT quantity FROM cart where id='"+id9+"'");
            rs3.next();
            int qt = rs3.getInt("quantity");
            int tt= pri*qt+7;
            
        int nT = Integer.parseInt(CnumTelephonenumTelephone.getText());
        
        s2.ajouter(new Commande(Integer.parseInt(idc.getText()),Cnom.getText(),Cprenom.getText(),Cemail.getText(),Cadresse.getText(),nT, (java.util.Date) Date.getDayCellFactory(),tt));
        JOptionPane.showMessageDialog(null,"Commande Ajoutée");
          
       

        try {
            Stage stage = new Stage();
            stage.setTitle("Commande");
            Parent root = FXMLLoader.load(getClass().getResource("Paiement.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
 }
    
    void setContent(Cart cart) {
        System.out.println(cart.getId());
        idc.setText(String.valueOf(cart.getId()));
        
          
    }

    }
     
    
    