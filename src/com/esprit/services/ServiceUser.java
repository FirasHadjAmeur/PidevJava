/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.esprit.modeles.Admin;
import com.esprit.modeles.Membre;
import com.esprit.modeles.User;
import com.esprit.utils.DataSource;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
 
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Achref
 */
public class ServiceUser implements IService<User> {

    Connection cnx = DataSource.getInstance().getCnx();
       public int existeMail(User u) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from user WHERE email = '" + u.getEmail_user() + "'");
        int size = 0;
        rs.next();
        size = rs.getInt(1);
        return size;
    }
    @Override
    public void ajouter(User u) {

        String req = "INSERT INTO user`(email`, password, name, last_name, roles, image)VALUES (?,?,?,?,?,?) ";
        try {
            String mdp = Hash();

           
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getEmail_user());
            ps.setString(2, mdp + u.getMdp_user());
            ps.setString(3, u.getPrenom_user());
            ps.setString(4, u.getNom_user());
            if (u instanceof Admin) {
                ps.setString(5, "admin");
                ps.setString(6, "");
            } else if (u instanceof Membre) {
                ps.setString(5, "[]");
                ps.setString(6, u.getPdp());
            } else {
                return;
            }
            ps.executeUpdate();
            System.out.println("user added !");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<User> getAll() {
        ObservableList<User> list = FXCollections.observableArrayList();
        try {

            String req = "SELECT * FROM user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = new User();
                u.setId_user(rs.getInt("id"));
                u.setNom_user(rs.getString("name"));
                u.setPrenom_user(rs.getString("last_name"));
                u.setEmail_user(rs.getString("email"));
                u.setMdp_user(rs.getString("password"));
                u.setRole(rs.getString("roles"));

                list.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;

    }
    
public void generatePDF(File file) throws IOException, DocumentException {
 Font catFont = new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
    Document doc = new Document(PageSize.LEGAL);
    
        ObservableList<User> liste = this.getAll();
        PdfPTable table = new PdfPTable(6);
        PdfPCell c1 = new PdfPCell(new Phrase("ID"));
        table.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase("NOM"));
        table.addCell(c2);
        PdfPCell c3 = new PdfPCell(new Phrase("PRENOM"));
        table.addCell(c3);
        PdfPCell c4 = new PdfPCell(new Phrase("EMAIL"));
        table.addCell(c4);
        PdfPCell c5 = new PdfPCell(new Phrase("ROLE"));
        table.addCell(c5);

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file.getPath()));
            doc.open();
            
      doc.add(new Phrase("Liste des utilisateurs  :",catFont));
  ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_RIGHT , new Phrase("Date d'impression:" +(new Date())),doc.right(),doc.top()+20,0);
 
liste.stream().map((item) -> {
      int id = item.getId_user();
      table.addCell(String.valueOf(id));
      String nom_user = item.getNom_user();
      table.addCell(nom_user);
      String prenom_user = item.getPrenom_user();
      table.addCell(prenom_user);
      String email_user = item.getEmail_user();
      table.addCell(email_user);
      
      String role = item.getRole();
                return role;
            }).forEachOrdered((role) -> {
                table.addCell(role);
            });
            doc.add(table);
            doc.close();

              System.out.println(" Users Pdf Generated With Success");
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
        }
    }

    @Override
    public boolean modifier(User u) {
        String req = "update user set name = ? , last_name = ?  where id = ? ";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getNom_user());
            ps.setString(2, u.getPrenom_user());
            ps.setInt(4, u.getId_user());
            ps.executeUpdate();
            System.out.println("user Modified !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean supprimer(User u) {
        String req = "DELETE FROM user WHERE id = ? ";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, u.getId_user());
            ps.executeUpdate();
            System.out.println("User Deleted");
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public String Hash() throws Exception {

        String mdp_user = "";

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp_user.getBytes());

        byte byteData[] = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }

    public ObservableList<User> getUser(String nom) {
        ObservableList<User> list = FXCollections.observableArrayList();
        try {

            String req = "SELECT * FROM user where name like '%" + nom + "%' ";
            PreparedStatement st = cnx.prepareStatement(req);

            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = new User();
                u.setId_user(rs.getInt("id"));
                u.setNom_user(rs.getString("name"));
                u.setPrenom_user(rs.getString("last_name"));
                u.setEmail_user(rs.getString("email"));
                u.setMdp_user(rs.getString("password"));
                u.setRole(rs.getString("roles"));

                list.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;

    }

    public void sendMail(String receveursList, String object, String corps) {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "25");

        String MonEmail = "hamdi.achref@esprit.tn";
        String password = "bigbigbang0";

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(MonEmail, password);
            }

        });

        javax.mail.Message message = prepareMessage(session, MonEmail, receveursList, object, corps);

        try {
            javax.mail.Transport.send(message);
        } catch (javax.mail.MessagingException ex) {
            System.out.println();
        }

        System.err.println("Message envoy??e avec succ??s");
    }

    private static javax.mail.Message prepareMessage(Session session, String email, String receveursList, String object, String corps) {
        javax.mail.Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(email));

            message.setSubject(object);
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(receveursList));
            message.setText(corps);

            return message;
        } catch (javax.mail.MessagingException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public String getAlphaNumericString(int n) {

        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb 
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public boolean verifierEmailBd(String email) { //Controle De Saisie si mail existe
        PreparedStatement stmt = null;
        ResultSet rst = null;
        try {
            String sql = "SELECT * FROM user WHERE email=?";
            stmt = cnx.prepareStatement(sql);
            stmt.setString(1, email);
            rst = stmt.executeQuery();
            if (rst.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean testEmail(String mail) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
        return matcher.find();
    }

    public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }

    public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()???[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);

    public boolean testPassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }

   public String crypterPassword(String password) {
        String hashValue = "";
        try {

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();

        } catch (Exception e) {
        }

        return hashValue;
    }

    public void modifierPassword(String mail, String password) {
        PreparedStatement stmt;
        try {

            String sql = "UPDATE user SET password=? WHERE email=?";
            stmt = cnx.prepareStatement(sql);
            stmt.setString(1, password);
            stmt.setString(2, mail);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public User getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllByUser(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findById(int id) {
        User r = new User();
        try {
            String req = "SELECT * FROM user where id=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                r.setId_user(rs.getInt("id"));
                r.setNom_user(rs.getString("name"));
                r.setPrenom_user(rs.getString("last_name"));
                r.setEmail_user(rs.getString("email"));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return r;
    }
}