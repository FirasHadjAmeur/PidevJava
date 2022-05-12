/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.modeles.Membre;
import com.esprit.modeles.User;
import com.esprit.services.ServiceUser;
import com.esprit.utils.DataSource;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
/**
 * FXML Controller class
 *
 * @author Achref
 */
public class SingUpMembreController implements Initializable {
    Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private Button add_membre_btn;
    @FXML
    private TextField prenom_membre;
    @FXML
    private TextField nom_membre;
    @FXML
    private TextField tel_membre;
    @FXML
    private TextField email_membre;
    @FXML
    private PasswordField mdp_membre;
    @FXML
    private PasswordField mdp_membre1;
    @FXML
    private TextField generatedString;
    @FXML
    private TextField text;
    String C ;
    @FXML
    private Button switch_signin_membre_btn;
 

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private ImageView img;
    @FXML
    private ImageView qr;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

     int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 4;
    Random random = new Random();

    String generateString = random.ints(leftLimit, rightLimit + 1)
      .limit(targetStringLength)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();
     C = generateString ;
     
    generatedString.setText("" + generateString + "");
    
    
    }

    @FXML
    private void action_add_membre(ActionEvent event) throws IOException, SQLException {

        ServiceUser sp = new ServiceUser();
        
        if (prenom_membre.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What's your firstname ?");
            alert.showAndWait();
            return;
        }
        if (nom_membre.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What's your lastname ?");
            alert.showAndWait();
            return;
        }
        
        if (!(tel_membre.getText().matches("\\d{8}")) && tel_membre.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What 's your telephone number ?");
            alert.showAndWait();
            return;

        } 
        if (tel_membre.getText().length()!=8) 
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Invalid phone number ");
            alert.showAndWait();
            return;

        }
          if (verifemail(email_membre.getText())== false) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez verifier votre mail ");
            alert.showAndWait();
            return;
        }
        if (mdp_membre.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What's your password ?");
            alert.showAndWait();
            return;
        }
        if ((mdp_membre.getText().equals(mdp_membre1.getText()))==false) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Les mots de passe ne sont pas identiques ");
            alert.showAndWait();
            return;
        }
          
         if ((text.getText().equals(C)==false)) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Les deux chaines ne sont pas identiques ");
            alert.showAndWait();
            return;
        }
        User m = new Membre(prenom_membre.getText(), nom_membre.getText(), Integer.parseInt(tel_membre.getText()), email_membre.getText(), mdp_membre.getText(), "src\\imagesUser\\" +email_membre.getText()+".png" );    
      if(sp.existeMail(m)==0){
      sp.ajouter(m);
      
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("L'utilisateur a ajouté avec succés");
                        alert.setContentText(" Felecitations");

            alert.showAndWait();
             root = FXMLLoader.load(getClass().getResource("SignInMembre.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
      }else {
       Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("compte existe deja ");
            alert.showAndWait();
      }

    }
      

    @FXML
    private void action_switch_signin_membre(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SignInMembre.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

  
  

 
    @FXML
    private String importImg(ActionEvent event) {
         String id =email_membre.getText();
          Path to = null;
         String  m = null;
         String path = "src/imagesUser/";
         JFileChooser chooser = new JFileChooser();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg","jpeg","PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           m = chooser.getSelectedFile().getAbsolutePath();
//            System.out.println("You chose to open this file: " +m
//                    );
            
            if(chooser.getSelectedFile() != null){
                
               try {
                   Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to = Paths.get(path+"\\"+id+".png");
                   CopyOption[] options = new CopyOption[]{
                       StandardCopyOption.REPLACE_EXISTING,
                       StandardCopyOption.COPY_ATTRIBUTES
                   };
                   Files.copy(from, to, options);
                   System.out.println("added");
//                saveSystem(selectedFile, )
                       System.out.println(to);

               } catch (IOException ex) {
                   System.out.println();
               }
            }
             //src.setText(to.toString());
        
    }
      return to.toString(); 
    }
    private boolean verifemail (String email)
    {
       String e ="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
       Pattern em = Pattern.compile(e, Pattern.CASE_INSENSITIVE);
       Matcher matcher = em.matcher(email);
        return matcher.find();
    }

        public void affichpdp() {
        String Id_stat = email_membre.getText();
        String f_name = Id_stat;
        String Path_name = new File("src/imagesUser/").getAbsolutePath();
        String image = Path_name + "\\" + Id_stat + ".PNG";
        //System.out.println(image);
        ImageView i = new ImageView();
        File f = new File(image);
        Image im = new Image(f.toURI().toString());
        img.setImage(im);
    }
        
}











