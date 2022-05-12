/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Matchs;
import com.esprit.services.ServiceMatchs;
import com.esprit.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class MatchsFrontController implements Initializable {

    
     ServiceMatchs matchCrud = new ServiceMatchs();
    ObservableList<Matchs> observableListMatch = FXCollections.observableArrayList();
    public static Matchs matchActuel;
    public static Matchs matchClicked;
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Matchs> idTableau;
    @FXML
    private TableColumn<Matchs, String> cTournoi;
    @FXML
    private TableColumn<Matchs, String> cEquipeA;
    @FXML
    private TableColumn<Matchs, String> cEquipeB;
    @FXML
    private TableColumn<Matchs, Date> cdateM;
    @FXML
    private TableColumn<Matchs, Integer> cScoreA;
    @FXML
    private TableColumn<Matchs, Integer> cScoreB;
    @FXML
    private TextField txrecherche;
    private Button btnModifier;
    @FXML
    private Text textTournoiIdActuel;
    @FXML
    private Button btnAjout1;
  Connection cnx = DataSource.getInstance().getCnx();
    /**
     * Initializes the controller class.
     */
    
    Connection connexion = DataSource.getInstance().getCnx();
    @FXML
    private Button btnAjoutFav;
    @FXML
    private Button btnRetour;
    
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
        matchActuel = null;
        btnAjoutFav.setDisable(true);
        Date date = new Date();

        List<Matchs> listematchs;
        try {
            listematchs = matchCrud.getAll();
          
                for (int i = 0; i < listematchs.size(); i++) {
                        observableListMatch.add(listematchs.get(i));
                        System.out.println(listematchs.get(i).getId());
                  
                }
                loadDate();  
        } catch (SQLException ex) {
            Logger.getLogger(AfficherTournoisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Matchs> listFed;
        listFed = matchCrud.getAll();
        observableListMatch.clear();
        observableListMatch.addAll(listFed);
      
        for (Matchs match : observableListMatch){
            try {   
                match.setNom_equipe1((getNomEquipeByID(match.getEquipe1_id()))); 
                match.setNom_equipe2((getNomEquipeByID(match.getEquipe2_id()))); 
            } catch (SQLException ex) {
                Logger.getLogger(AfficherMatchsController.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
        for (Matchs match : observableListMatch){
            try {   
                match.setNom_tournoi((getNomTournoiByID(match.getTournoi_id()))); 
                
            } catch (SQLException ex) {
                Logger.getLogger(AfficherMatchsController.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
        
        idTableau.setItems(observableListMatch);

        cTournoi.setCellValueFactory(new PropertyValueFactory<>("Nom_tournoi"));
        cEquipeA.setCellValueFactory(new PropertyValueFactory<>("Nom_equipe1"));
        cEquipeB.setCellValueFactory(new PropertyValueFactory<>("Nom_equipe2"));
        cdateM.setCellValueFactory(new PropertyValueFactory<>("date_match"));
        //cRefM.setCellValueFactory(new PropertyValueFactory<>("ref_match"));
        cScoreA.setCellValueFactory(new PropertyValueFactory<>("score_a"));
        cScoreB.setCellValueFactory(new PropertyValueFactory<>("score_b"));
        idTableau.setItems(observableListMatch);
        RechercheT();
    }    


    @FXML
    private void changerTournoiActuel(MouseEvent event) {
        btnAjoutFav.setDisable(false);
      //  btnSupprimer.setDisable(false);
        matchActuel = idTableau.getSelectionModel().getSelectedItem();
    }
    
    
    private void loadDate() throws SQLException {
        ObservableList <Matchs> mList = FXCollections.observableArrayList();
       
        cTournoi.setCellValueFactory(new PropertyValueFactory<>("Nom_tournoi"));
        cEquipeA.setCellValueFactory(new PropertyValueFactory<>("Nom_equipe1"));
        cEquipeB.setCellValueFactory(new PropertyValueFactory<>("Nom_equipe2"));
        cdateM.setCellValueFactory(new PropertyValueFactory<>("date_match"));
        cScoreA.setCellValueFactory(new PropertyValueFactory<>("score_a"));
        cScoreB.setCellValueFactory(new PropertyValueFactory<>("score_b"));

           List old = matchCrud.getAll();
           mList.addAll(old);
           for (Matchs match : mList){
            match.setNom_equipe1((getNomEquipeByID(match.getEquipe1_id())));
           }
           for (Matchs match : mList){
            match.setNom_equipe2((getNomEquipeByID(match.getEquipe2_id())));
           }
           for (Matchs match : mList){
            match.setNom_tournoi((getNomTournoiByID(match.getTournoi_id())));
           }
           idTableau.setItems(mList);
           idTableau.refresh();
    }      
    
    @FXML
    private void RechercheT() {
         // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Matchs> filteredData = new FilteredList<>(observableListMatch, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(matchs -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (matchs.getRef_match().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(matchs.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Matchs> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(idTableau.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        idTableau.setItems(sortedData);
    }

    @FXML
    private void ajoutFavori(ActionEvent event) {
        if (event.getSource() == btnAjoutFav) {
           Matchs m=idTableau.getSelectionModel().getSelectedItem();  
//          EvenementServices cs = new EvenementServices();
//            cs.DeleteEvenement(e);
        String query = "INSERT INTO `favoris`(`tournoi_id`, `equipe1_id`, `equipe2_id`, `date_match`, `ref_match`, `score_a`, `score_b`) VALUES (?,?,?,?,?,?,?)";   
      
         // matchCrud.update(matchh);
        try {
        
        PreparedStatement ps = cnx.prepareStatement(query);
        
          ps.setObject(1, m.getTournoi_id());
          ps.setObject(2, m.getEquipe1_id());
          ps.setObject(3, m.getEquipe2_id());
            ps.setObject(4, m.getDate_match());
            ps.setObject(5, m.getRef_match());
            ps.setObject(6, m.getScore_a());
            ps.setObject(7, m.getScore_b());
           // ps.setObject(8, 420);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       // matchCrud.delete(m);
           // loadDate();  
        AlertUtils.makeSuccessNotification("Match Ajouté aux Favoris !!");
        }
    }

    @FXML
    private void AfficherFavoris(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("FavorisFront.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
