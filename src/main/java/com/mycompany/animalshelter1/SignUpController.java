/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.animalshelter1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.concurrent.TimeUnit;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class SignUpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void switchtoLogin() throws IOException {
        App.setRoot("login");
    }
    @FXML
    private Button SignUp;
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private PasswordField pswd_field;
    @FXML
    private Label message_lbl;
    @FXML
    private PasswordField idfield;
      public void FacultySignUp(ActionEvent event) throws IOException {
      Connection cn1 = null;

            try
             {
                // db parameters (assumes movies.db is in the same directory)
            String url = "jdbc:sqlite:src\\main\\resources\\com\\mycompany\\animalshelter1\\InhumaneSocietydb.db";
            // create a connection to the database
            cn1 = DriverManager.getConnection(url);

            System.out.println("Connection to mysql has been established.");
            
             String sql = "Select * from Credentials where EmployeeNumber like";
              String sql2 = "Select * from Credentials where ID like";

             if (txtFieldUsername.getText()=="")
             {
                 message_lbl.setText("no username found");
             }
             else if (pswd_field.getText() == "")
             {
                 message_lbl.setText("no password found");
             }
             else if (idfield.getText() == "")
             {
                 message_lbl.setText("An ID number is needed!");
             }
             else
             {


                // create a ResultSet
                 PreparedStatement stmt; 

                stmt = cn1.prepareStatement( "Select * from Credentials where Username = "+txtFieldUsername.getText());
                //stmt.setString(1,"%"+txtFieldUsername.getText()+"%");     

                ResultSet rs = stmt.executeQuery();

                if(rs.next()==true)//username is not unique
                {
                    message_lbl.setText("Username already taken");
                }
                   stmt.close();
                  stmt = cn1.prepareStatement( "Select * from Credentials where ID = "+idfield.getText());
                ResultSet rs2 = stmt.executeQuery();
                 if (rs2.next()==true)
                {
                    message_lbl.setText("ID already taken");

                }
                else
                {
                    String inSQL = "INSERT INTO Credentials(Username,Password,ID) VALUES("+txtFieldUsername.getText()+','+pswd_field.getText()+','+idfield.getText()+')';
                    System.out.println(inSQL);

                    System.out.println("username and ID is unique");
                    System.out.println(inSQL);
                    PreparedStatement pstmt = cn1.prepareStatement(inSQL);
                    
                    message_lbl.setText("SignUp Sucessful. You will now be redirected to login page");
                    pstmt.executeUpdate();
                        try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    App.setRoot("login");
                }
             }
             

             }
                 catch(SQLException re)
              {
                System.out.println(re.getMessage());
              }
    }
      

    
}
