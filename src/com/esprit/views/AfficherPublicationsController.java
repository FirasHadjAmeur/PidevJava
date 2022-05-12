package com.esprit.views;

import com.esprit.modeles.Publication;
import com.esprit.modeles.Commentaire;
import com.esprit.services.PublicationService;
import com.esprit.services.CommentaireService;
import com.esprit.services.LikeService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherPublicationsController implements Initializable {
    
    @FXML
    private ListView<Publication> listPublications;
    
    @FXML
    private Button addPublicationBtn;
         
    private Stage stage;
        
    public String idPublication;
    
    @FXML
    private TextField searchTxft;
    
    private ObservableList<Publication> observableList;
    
    @FXML
    private Button statisticBtn;
    
    @FXML
    private Button sortBtn;
    @FXML
    private AnchorPane root;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                     
       PublicationService ps = new PublicationService();
       observableList = ps.displayAll();
       listPublications.setItems(observableList);
        
       initListView();
    } 
    
    public void initListView(){
        
        listPublications.setCellFactory(new Callback<ListView<Publication>, ListCell<Publication>>() {

            @Override
            public ListCell<Publication> call(ListView<Publication> list) {

                return new ListCell<Publication>() {

                    @Override
                    protected void updateItem(Publication item, boolean empty) {

                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        }
                        else {

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("publication.fxml"));
                            try {
                                Parent publicationView = loader.load();
                                setGraphic(publicationView);
                                PublicationController pc = loader.getController();
                                
                                pc.setId(item.getId()); 
                                pc.setTitle(item.getTitre());
                                pc.setDate(item.getDate().toString());
                                pc.setContent(item.getContenu());
                                pc.setImage(item.getImage());
                                                                
                                LikeService ls = new LikeService();
                                pc.setNbrLike(ls.countLike(item.getId()));
                                pc.setNbrDislike(ls.countDislike(item.getId()));  
                                
                                CommentaireService cs = new CommentaireService();
                                List<Commentaire> listComments = cs.displayAllByPublicationByDate(Integer.parseInt(item.getId()));
                                pc.setComments(listComments);

                                pc.setList(observableList);
                            }
                            catch (IOException ex) {
                                System.out.println("Error " + ex.getMessage());
                            }
                        }
                    }
                };
            }
        });
    } 
    
    
    @FXML
    private void addPublication(ActionEvent event) {
       
        Stage dialog = new Stage();

	FXMLLoader loader = new FXMLLoader(getClass().getResource("addPublication.fxml"));
	try {
            Parent parent = loader.load();

            AddPublicationController apc = loader.getController();
            apc.setList(observableList);

            Scene dialogScene = new Scene(parent);
            dialog.setScene(dialogScene);
            dialog.show();
        }
	catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
	}
    }

    @FXML
    private void searchPublication(ActionEvent event) {
        
        searchTxft.setOnKeyPressed((KeyEvent keyEvent) -> {
            
            if (keyEvent.getCode() == KeyCode.ENTER) {         
              
                PublicationService ps = new PublicationService();
                ObservableList<Publication> list = ps.getPublicationByTitle(searchTxft.getText());
                
                ObservableList<Publication> items = FXCollections.observableArrayList(list);
                listPublications.setItems(items);
            }
        });
    }     


    @FXML
    private void getStatistics(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Statistics.fxml"));
        try {
            Parent statView = loader.load();
                         
            StatisticsController sc = loader.getController();
            Scene scene = new Scene(statView);
            Stage stage = (Stage) addPublicationBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        } 
    }

    @FXML
    private void sortList(ActionEvent event) {
        
        PublicationService ps = new PublicationService();
        ObservableList<Publication> list = ps.sortByDate();
                
        ObservableList<Publication> items = FXCollections.observableArrayList(list);
        listPublications.setItems(items);
    }

    @FXML
    private void returnHomeaction(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/InterfaceMembre.fxml"));
        root.getChildren().setAll(pane);
    }
}
