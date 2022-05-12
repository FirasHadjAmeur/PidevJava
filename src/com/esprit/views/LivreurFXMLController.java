/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Cart;
import com.esprit.modeles.Livreur;
import com.esprit.services.ServiceCartt;
import com.esprit.services.ServiceLivreur;
import com.esprit.utils.DataSource;
import static com.esprit.utils.DataSource.ConnectDb;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizm
 */
public class LivreurFXMLController implements Initializable {

    @FXML
    private TableView<Livreur> table_livreur;
    @FXML
    private TableColumn<Livreur,Integer> IdLivreur;
    @FXML
    private TableColumn<Livreur,String> NomLivreur;
    @FXML
    private TableColumn<Livreur,String> PrenomLivreur;
    @FXML
    private TableColumn<Livreur,String> EmailLivreur;
    @FXML
    private TableColumn<Livreur,Integer> NumTelLivreur;
    @FXML
    private TableColumn<Livreur,String> AdresseLivreur;
 ObservableList<Livreur> list = FXCollections.observableArrayList(); 
  ObservableList<Livreur> listl = FXCollections.observableArrayList(); 
  ObservableList<Livreur> listR = FXCollections.observableArrayList();
    @FXML
    private Button ajouterliv;
    @FXML
    private Button modifierliv;
    @FXML
    private TextField ftr;
    @FXML
    private TextField idm;
    @FXML
    private AnchorPane root;
/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection cnx = DataSource.getInstance().getCnx();
         ResultSet rs = null;
        try {
            rs = cnx.createStatement().executeQuery("SELECT * FROM livreur");
        } catch (SQLException ex) {
            Logger.getLogger(CartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                list.add(new Livreur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6)));
                
                table_livreur.setItems(list);
                table_livreur.refresh();
                
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
       
            IdLivreur.setCellValueFactory(new PropertyValueFactory<>("idLivreur"));
            NomLivreur.setCellValueFactory(new PropertyValueFactory<>("nomLivreur"));
            PrenomLivreur.setCellValueFactory(new PropertyValueFactory<>("prenomLivreur"));
            EmailLivreur.setCellValueFactory(new PropertyValueFactory<>("email"));
            NumTelLivreur.setCellValueFactory(new PropertyValueFactory<>("numTelephoneLivreur"));
            AdresseLivreur.setCellValueFactory(new PropertyValueFactory<>("adresseLivreur"));

            table_livreur.setItems(list);
            UpdateTable();
            
            
            
    }
     @FXML
    private void SupprimerLivreur(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Livreur x = gettempLivreur(edittedcell);

        if (x != null) {

            int i = x.getIdLivreur();
            ServiceLivreur liv = new ServiceLivreur();

            int s = liv.deleteLivreur(i);
            

            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Produit deleted");
                alert.showAndWait();
                table_livreur.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }
          UpdateTable();
    }
    public Livreur gettempLivreur(TableColumn.CellEditEvent edittedCell) {
        Livreur test = table_livreur.getSelectionModel().getSelectedItem();
        return test;
    }
        
public void UpdateTable() throws SQLException{
            
       IdLivreur.setCellValueFactory(new PropertyValueFactory<>("idLivreur"));
            NomLivreur.setCellValueFactory(new PropertyValueFactory<>("nomLivreur"));
            PrenomLivreur.setCellValueFactory(new PropertyValueFactory<>("prenomLivreur"));
            EmailLivreur.setCellValueFactory(new PropertyValueFactory<>("email"));
            NumTelLivreur.setCellValueFactory(new PropertyValueFactory<>("numTelephoneLivreur"));
            AdresseLivreur.setCellValueFactory(new PropertyValueFactory<>("adresseLivreur"));
        
         listl = DataSource.getDataLivreur();
        
        table_livreur.setItems(listl);
        }
    @FXML
 private void ajouterLiv(ActionEvent event) {
        ajouterliv.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Ajouter Livreur");
            Parent root = FXMLLoader.load(getClass().getResource("AjouterLiveurFXML.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
 }
    @FXML
 private void modifierLiv(ActionEvent event) {
        modifierliv.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Modifier Livreur");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierLivreurFXML.fxml"));
            Parent root = loader.load();
            ModifierLivreurFXMLController controller = (ModifierLivreurFXMLController) loader.getController();
            Livreur userById = getIdLivreur(Integer.parseInt(idm.getText()));
            controller.setContent(userById);
           /* Parent root = FXMLLoader.load(getClass().getResource("modifierLivreurFXML.fxml"));*/

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
 }

    @FXML
  public void recherche() throws SQLException{
    ServiceLivreur re= new ServiceLivreur() ;
    List<Livreur>results = new ArrayList<>();
    results = re.afficher();
     FilteredList<Livreur> filteredData = new FilteredList<>(list , b -> true);
		Livreur r = new Livreur();
		// 2. Set the filter Predicate whenever the filter changes.
		ftr.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(livreur-> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (livreur.getAdresseLivreur().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (livreur.getPrenomLivreur().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
                                else if (livreur.getNomLivreur().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
				else if (String.valueOf(r.getIdLivreur()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Livreur> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table_livreur.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table_livreur.setItems(sortedData);
               
        
    }
 
    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
            int index = table_livreur.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    idm.setText(IdLivreur.getCellData(index).toString());
    
    
    
    }
    private Livreur getIdLivreur(int idLivreur) {
        System.out.println(idLivreur);
   Livreur livreur = null;
    Connection cnx = DataSource.getInstance().getCnx();
    String query ="select * from livreur where idLivreur ='"+idLivreur+"'";
    Statement st;
    ResultSet rs;
            try{
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            System.out.print(rs);
            
            while(rs.next()){
                  livreur = new Livreur(rs.getInt("idLivreur"), rs.getString("nomLivreur"), rs.getString("prenomLivreur"), rs.getString("email"),rs.getInt("numTelephoneLivreur"), rs.getString("adresseLivreur"));
               
                          

            }    
        }catch(Exception ex){
            ex.printStackTrace();
        }
            System.out.println(livreur);
    return livreur;
    }

    @FXML
    private void return_home(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/DashboardAdmin.fxml"));
        root.getChildren().setAll(pane);
    }
  
}