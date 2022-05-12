/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Favoris;
import com.esprit.services.ServiceFavoris;
import com.esprit.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.shoppingcart.utils.AlertUtils;

/**
 * FXML Controller class
 *
 * @author Dragon
 */
public class FavorisFrontController implements Initializable {

    ServiceFavoris favoriCrud = new ServiceFavoris();
    ObservableList<Favoris> observableListFavori = FXCollections.observableArrayList();
    public static Favoris favoriActuel;
    public static Favoris favoriClicked;
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Favoris> idTableau;
    @FXML
    private TableColumn<Favoris, String> cTournoi;
    @FXML
    private TableColumn<Favoris, String> cEquipeA;
    @FXML
    private TableColumn<Favoris, String> cEquipeB;
    @FXML
    private TableColumn<Favoris, Date> cdateM;
    @FXML
    private TableColumn<Favoris, Integer> cScoreA;
    @FXML
    private TableColumn<Favoris, Integer> cScoreB;
    @FXML
    private TextField txrecherche;
    @FXML
    private Button btnModifier;
    @FXML
    private Text textTournoiIdActuel;
    @FXML
    private Button btnAjout1;
    Connection cnx = DataSource.getInstance().getCnx();
     Connection connexion = DataSource.getInstance().getCnx();
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
     
     public String getNomEquipeByID(int id) throws SQLException{
         String xx = "";
       String req = "SELECT `nom` FROM `equipes` WHERE id="+id;
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

           while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                 xx = rst.getString("nom");
            }
           return xx;
     }

    public String getNomTournoiByID(int id) throws SQLException{
         String xx = "";
       String req = "SELECT `nom` FROM `tournois` WHERE id="+id;
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

           while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                 xx = rst.getString("nom");
            }
           return xx;
     }   
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        favoriActuel = null;
        //btnAjoutFav.setDisable(true);
        Date date = new Date();

        List<Favoris> listefavoris;
        try {
            listefavoris = favoriCrud.getAll();
          
                for (int i = 0; i < listefavoris.size(); i++) {
                        observableListFavori.add(listefavoris.get(i));
                        System.out.println(listefavoris.get(i).getId());
                  
                }
                loadDate();  
        } catch (SQLException ex) {
            Logger.getLogger(AfficherTournoisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Favoris> listFed;
        try {
            listFed = favoriCrud.getAll();
            observableListFavori.clear();
            observableListFavori.addAll(listFed);
      
        } catch (SQLException ex) {
            Logger.getLogger(FavorisFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (Favoris favori : observableListFavori){
            try {   
                favori.setNom_equipe1((getNomEquipeByID(favori.getEquipe1_id()))); 
                favori.setNom_equipe2((getNomEquipeByID(favori.getEquipe2_id()))); 
            } catch (SQLException ex) {
                Logger.getLogger(FavorisFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
        for (Favoris favori : observableListFavori){
            try {   
                favori.setNom_tournoi((getNomTournoiByID(favori.getTournoi_id()))); 
                
            } catch (SQLException ex) {
                Logger.getLogger(FavorisFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
        
        idTableau.setItems(observableListFavori);

        cTournoi.setCellValueFactory(new PropertyValueFactory<>("Nom_tournoi"));
        cEquipeA.setCellValueFactory(new PropertyValueFactory<>("Nom_equipe1"));
        cEquipeB.setCellValueFactory(new PropertyValueFactory<>("Nom_equipe2"));
        cdateM.setCellValueFactory(new PropertyValueFactory<>("date_match"));
        //cRefM.setCellValueFactory(new PropertyValueFactory<>("ref_match"));
        cScoreA.setCellValueFactory(new PropertyValueFactory<>("score_a"));
        cScoreB.setCellValueFactory(new PropertyValueFactory<>("score_b"));
        idTableau.setItems(observableListFavori);
        RechercheT();
    }    


    @FXML
    private void changerTournoiActuel(MouseEvent event) {
          btnModifier.setDisable(false);
      //  btnSupprimer.setDisable(false);
        favoriActuel = idTableau.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void RechercheT() {
         // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Favoris> filteredData = new FilteredList<>(observableListFavori, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(favoris -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (favoris.getRef_match().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(favoris.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Favoris> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(idTableau.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        idTableau.setItems(sortedData);
    }

    @FXML
    private void suppFavori(ActionEvent event) throws SQLException {
         if (event.getSource() == btnModifier) {
           Favoris m=idTableau.getSelectionModel().getSelectedItem();  
//          EvenementServices cs = new EvenementServices();
//            cs.DeleteEvenement(e);
        favoriCrud.delete(m);
            loadDate();  
        AlertUtils.makeSuccessNotification("Match supprimé !!");
        }
    }

    @FXML
    private void AfficherMatchs(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("MatchsFront.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void loadDate() throws SQLException {
         ObservableList <Favoris> mList = FXCollections.observableArrayList();
       
        cTournoi.setCellValueFactory(new PropertyValueFactory<>("Nom_tournoi"));
        cEquipeA.setCellValueFactory(new PropertyValueFactory<>("Nom_equipe1"));
        cEquipeB.setCellValueFactory(new PropertyValueFactory<>("Nom_equipe2"));
        cdateM.setCellValueFactory(new PropertyValueFactory<>("date_match"));
        cScoreA.setCellValueFactory(new PropertyValueFactory<>("score_a"));
        cScoreB.setCellValueFactory(new PropertyValueFactory<>("score_b"));

           List old = favoriCrud.getAll();
           mList.addAll(old);
           for (Favoris favori : mList){
            favori.setNom_equipe1((getNomEquipeByID(favori.getEquipe1_id())));
           }
           for (Favoris favori : mList){
            favori.setNom_equipe2((getNomEquipeByID(favori.getEquipe2_id())));
           }
           for (Favoris favori : mList){
            favori.setNom_tournoi((getNomTournoiByID(favori.getTournoi_id())));
           }
           idTableau.setItems(mList);
           idTableau.refresh();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("InterfaceMembre.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
