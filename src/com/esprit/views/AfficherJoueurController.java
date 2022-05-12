/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.views;

import com.shoppingcart.utils.AlertUtils;
import com.esprit.modeles.Equipe;
import com.esprit.modeles.Joueurs;
import static com.esprit.views.AfficherEquipeController.equipeActuelle;
import com.esprit.services.EquipeService;
import com.esprit.services.JoueurService;
import com.esprit.utils.Connexion;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherJoueurController implements Initializable {

//    EquipeService EquipeCrud = new EquipeService();
    JoueurService JoueurCrud = new JoueurService();
    ObservableList<Joueurs> observableListJoueur = FXCollections.observableArrayList();
    public static Joueurs joueurActuelle;
    public static Joueurs joueurClicked;
    
    @FXML
    private TableView<Joueurs> idTableau;
    @FXML
    private TableColumn<Joueurs, String> columnNom;
    @FXML
     private TableColumn<Joueurs, String> columnPrenom;
    @FXML
    private TableColumn<Joueurs, String> columnEmail;
    @FXML
    private TableColumn<Joueurs, Integer> columnNumero;
    @FXML
    private TableColumn<Joueurs, Integer> columnNbrPartie;
    @FXML
    private TableColumn<Joueurs, String> columnEquipe;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Text textEquipeIdActuelle;
    @FXML
    private TextField txrecherche;
     Connection connexion;  
      public AfficherJoueurController() {
        connexion = Connexion.getInstance().getCnx();
    }

     
     public String getNomEquipeByID(int id) throws SQLException{
         String xx = "";
       String req = "select nom from equipes where id="+id;
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
        joueurActuelle = null;
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);
        Date date = new Date();

        List<Joueurs> listeJoueur;
        try {
            listeJoueur = JoueurCrud.ShowJoueur();
          
                for (int i = 0; i < listeJoueur.size(); i++) {
                        observableListJoueur.add(listeJoueur.get(i));
                        System.out.println(listeJoueur.get(i).getNom());
                  
                }
                loadDate();  
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

//      DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//      System.out.println(format.format(date));
       
        List<Joueurs> listFed;
        try {
                listFed = JoueurCrud.ShowJoueur();
        observableListJoueur.clear();
        observableListJoueur.addAll(listFed);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEquipeController.class.getName()).log(Level.SEVERE, null, ex);
        }
      for (Joueurs joueur : observableListJoueur){
            try {   
                joueur.setNom_equipe((getNomEquipeByID(joueur.getEquipes_id())));  
            } catch (SQLException ex) {
                Logger.getLogger(AfficherJoueurController.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
        
        idTableau.setItems(observableListJoueur);

        columnNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        columnNumero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        columnNbrPartie.setCellValueFactory(new PropertyValueFactory<>("nbr_partie_jouer"));
        columnEquipe.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
        idTableau.setItems(observableListJoueur);
        Recherche();
    }


    private void loadDate() throws SQLException {
           ObservableList<Joueurs> abList = FXCollections.observableArrayList();

            columnNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        columnNumero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        columnNbrPartie.setCellValueFactory(new PropertyValueFactory<>("nbr_partie_jouer"));
         
            columnEquipe.setCellValueFactory(new PropertyValueFactory<>("nom_equipe"));
    

           List old = JoueurCrud.ShowJoueur();
           abList.addAll(old);
           for (Joueurs joueur : abList){
            joueur.setNom_equipe((getNomEquipeByID(joueur.getEquipes_id())));
           }
           idTableau.setItems(abList);
           idTableau.refresh();
       }    

   @FXML
    private void Modifier(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("ModifierJoueur.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ajouterJoueur(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("AjouterJoueur.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AfficherEquipe(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("AfficherEquipe.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

   @FXML
    private void supp(ActionEvent event) throws SQLException 
    {
           if (event.getSource() == btnSupprimer) {
           int e=idTableau.getSelectionModel().getSelectedItem().getId();  
//          EvenementServices cs = new EvenementServices();
//            cs.DeleteEvenement(e);
        JoueurCrud.DeleteJoueur(e);
            loadDate();  
AlertUtils.makeSuccessNotification("Joueur supprimer !!");
       // JOptionPane.showMessageDialog(null, "Joueur supprimer");
        }
    }

 public void Recherche() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Joueurs> filteredData = new FilteredList<>(observableListJoueur, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(equipe -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (equipe.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(equipe.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Joueurs> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(idTableau.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        idTableau.setItems(sortedData);
    }

 @FXML
    private void changerJoueurActuelle(MouseEvent event) {
        btnModifier.setDisable(false);
        btnSupprimer.setDisable(false);
        joueurActuelle = idTableau.getSelectionModel().getSelectedItem();
    }

}
