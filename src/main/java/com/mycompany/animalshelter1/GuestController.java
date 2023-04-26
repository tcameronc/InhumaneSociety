/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.animalshelter1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class GuestController implements Initializable {
    @FXML
    private TableView tableView;
    @FXML
    TableColumn number = new TableColumn("number");

    
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    try {
            loadData();
        } catch (SQLException ex) {
            
            System.out.println(ex.toString());
        }
        intializeColumns();
        CreateSQLiteTable();
    }

    String databaseURL = "jdbc:sqlite:src/main/resources/com/mycompany/animalshelter1/home.db";
        private ObservableList<Home1> data;

        public GuestController() throws SQLException {
        this.data = FXCollections.observableArrayList();
    }

    

    private void intializeColumns() {
        number = new TableColumn("Number");
        number.setMinWidth(0);
        number.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("number"));

        TableColumn sex = new TableColumn("Sex");
        sex.setMinWidth(0);
        sex.setCellValueFactory(new PropertyValueFactory<Home1, String>("sex"));

        TableColumn species = new TableColumn("Species");
        species.setMinWidth(0);
        species.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("species"));

        TableColumn breed = new TableColumn("Breed");
        breed.setMinWidth(0);
        breed.setCellValueFactory(new PropertyValueFactory<Home1, String>("breed"));
        TableColumn name = new TableColumn("name");
        name.setMinWidth(0);
        name.setCellValueFactory(new PropertyValueFactory<Home1, String>("name"));
                
        TableColumn age = new TableColumn("age");
        age.setMinWidth(0);
        age.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("age"));
        
        TableColumn temper = new TableColumn("temper");
        temper.setMinWidth(0);
        temper.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("temper"));
         
        TableColumn adoptable = new TableColumn("adoptable");
        adoptable.setMinWidth(0);
        adoptable.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("adoptable"));
        
        tableView.setItems(data);
        tableView.getColumns().addAll(number, sex, species, breed, name, age, temper, adoptable);
        
    }
    
    private void loadData() throws SQLException{
        Connection conn = null;
        Statement stmt = null;
 
        try {

            // create a connection to the database
            conn = DriverManager.getConnection(databaseURL);

            System.out.println("Connection to SQLite has been established.");
            String sql = "SELECT * FROM home where Adoptable = 1;";
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Home1 home1;
                home1 = new Home1(rs.getInt("number"), rs.getString("sex"), rs.getString("species"), rs.getString("Breed"), rs.getInt("age"), rs.getString("name"), rs.getInt("temper"), rs.getInt("adoptable"));
                System.out.println(home1.getNumber() + " - " + home1.getSex() + " - " + home1.getSpecies() + " - " + home1.getBreed()+ " - " + home1.getAge()+ " - " + home1.getName()+ " - " +home1.getTemper()+ " - " + home1.getAdoptable());
                data.add(home1);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    

    private void CreateSQLiteTable() {
        String sql = "CREATE TABLE IF NOT EXISTS home (\n"
                + "	number integer PRIMARY KEY,\n"
                + "	sex text NOT NULL,\n"
                + "	species text NOT NULL,\n"
                + "	breed text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(databaseURL);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);  
    
        System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

}
