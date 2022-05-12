/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Tournois;
import com.esprit.services.ServiceTournois;
import com.shoppingcart.utils.AlertUtils;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDateTime;
/**
 * FXML Controller class
 *
 * @author Dragon
 */
public class AfficherTournoisController implements Initializable {
    
    ServiceTournois tournoiCrud = new ServiceTournois();
    ObservableList<Tournois> observableListTournoi = FXCollections.observableArrayList();
    public static Tournois tournoiActuel;
    public static Tournois tournoiClicked;
    
    
    @FXML
    private Button btnAjout;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Tournois> idTableau;
    @FXML
    private TableColumn<Tournois, String> columnNom;
    @FXML
    private TableColumn<Tournois, Date> columnDateDeb;
    @FXML
    private TableColumn<Tournois, Date> columnDateFin;
    @FXML
    private TextField txrecherche;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjout1;
    @FXML
    private TableColumn<Tournois, Integer> columnPrime;
    @FXML
    private Text textTournoiIdActuel;
    @FXML
    private Button btnretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tournoiActuel = null;
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);
        Date date = new Date();

        List<Tournois> listetournois;
        try {
            listetournois = tournoiCrud.getAll();
          
                for (int i = 0; i < listetournois.size(); i++) {
                        observableListTournoi.add(listetournois.get(i));
                        System.out.println(listetournois.get(i).getId());
                  
                }
                loadDate();  
        } catch (SQLException ex) {
            Logger.getLogger(AfficherTournoisController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    // DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    //  System.out.println(format.format(date));
       
        List<Tournois> listFed;
        try {
            listFed = tournoiCrud.getAll();
        observableListTournoi.clear();
        observableListTournoi.addAll(listFed);
         } catch (SQLException ex) {
            Logger.getLogger(AfficherTournoisController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        idTableau.setItems(observableListTournoi);

        columnNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        columnDateDeb.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        columnDateFin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        columnPrime.setCellValueFactory(new PropertyValueFactory<>("Prime"));
        idTableau.setItems(observableListTournoi);
        RechercheT();
    }    

    @FXML
    private void ajouterTounoi(ActionEvent event) throws IOException{
        Parent page1 = FXMLLoader.load(getClass().getResource("AjouterTournoi.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        
  Parent page1 = FXMLLoader.load(getClass().getResource("ModifierTournoi.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Affichermatchs(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("AfficherMatchs.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void supp(ActionEvent event) throws SQLException {
         if (event.getSource() == btnSupprimer) {
           Tournois t=idTableau.getSelectionModel().getSelectedItem();  
//          EvenementServices cs = new EvenementServices();
//            cs.DeleteEvenement(e);
        tournoiCrud.delete(t);
            loadDate();  
        AlertUtils.makeSuccessNotification("Tournoi supprimé !!");
        }
    }

 
    private void loadDate() throws SQLException {
        ObservableList <Tournois> tList = FXCollections.observableArrayList();
       
        columnNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        columnDateDeb.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        columnDateFin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        columnPrime.setCellValueFactory(new PropertyValueFactory<>("Prime"));

        List old = tournoiCrud.getAll();
        tList.addAll(old);
        idTableau.setItems(tList);
        idTableau.refresh();
    }

   // private void Recherche() {
     /*         // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Tournois> filteredData = new FilteredList<>(observableListTournoi, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tournois -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (tournois.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(tournois.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Tournois> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(idTableau.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        idTableau.setItems(sortedData);

    } */
    
      @FXML
    private void changerTournoiActuel(MouseEvent event) {
          btnModifier.setDisable(false);
        btnSupprimer.setDisable(false);
        tournoiActuel = idTableau.getSelectionModel().getSelectedItem();

 
    }

    @FXML
    private void RechercheT() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Tournois> filteredData = new FilteredList<>(observableListTournoi, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tournois -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (tournois.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(tournois.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Tournois> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(idTableau.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        idTableau.setItems(sortedData);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
  
