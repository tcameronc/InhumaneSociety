/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.animalshelter1;

import java.time.*;
import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalTime;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
/**
 *
 * @author ma1898
 */
public class LoginController implements Initializable {
       /**
     * Initializes the controller class.
     */
    
    public static String CurrentUser;
    public static int userID;
    
    
    @FXML
    private Button Fact_login_btn;
     @FXML
    private Button signup;
     @FXML
    private Button Guest_login_btn;

     @FXML 
     private PasswordField pswd_field;
     @FXML
     private TextField txtFiledUsername;
     @FXML
     private Label message_lbl;
    @Override
    public void initialize(URL url, ResourceBundle RB) {
        // TODO
        
    }    
    
    public void Facultylogin(ActionEvent event) throws IOException {
           Connection cn1 = null;
           Statement stmt = null;
            try
             {
                // db parameters (assumes movies.db is in the same directory)
            String url = "jdbc:sqlite:src\\main\\resources\\com\\mycompany\\animalshelter1\\InhumaneSocietydb.db";
            // create a connection to the database
            cn1 = DriverManager.getConnection(url);

            System.out.println("Connection to mysql has been established.");
            String sql = "SELECT * FROM Credentials;";
            // Ensure we can query the actors table
            stmt = cn1.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String userName = rs.getString("Username").toLowerCase();
                String passWord = rs.getString("Password");
    
                
                String enteredUser = txtFiledUsername.getText().toString().toLowerCase();
                String enteredPass = pswd_field.getText().toString();
                
                
                  if (userName.equals(enteredUser) && passWord.equals(enteredPass)) {
                    System.out.println("[LOGIN SUCCESSFUL] - " + enteredUser);
                    this.CurrentUser = enteredUser.substring(0, 1).toUpperCase() + enteredUser.substring(1);
                    this.userID = rs.getInt("ID");
                    TriggerLogTable(cn1, enteredUser, "User Login");        
                    App.setRoot("home");
                    rs.close();
                    break;
                }
             }
              if (!rs.next()) {
                    message_lbl.setText("Invalid Username / Password!");
            }
            rs.close();
            }
             catch(SQLException re)
              {
                System.out.println(re.getMessage());
              }
    }         
    public void guestlogin(ActionEvent event) throws IOException {

                    App.setRoot("guest");

    }       
    public void signup(ActionEvent event) throws IOException {

                    App.setRoot("SignUp");

    }    
  private void TriggerLogTable(Connection conn, String username ,String statement) throws SQLException
  {           
      
            String updateLog = "INSERT INTO LogTable (LogID,Time, SQL)"
                    + "VALUES ('" + username +"','"+LocalDateTime.now()+"','"+statement+ " : "+username+"');";
    
           try (conn;
                Statement stmt2 = conn.createStatement()){
                   stmt2.execute(updateLog);
        }
           
    }   
}