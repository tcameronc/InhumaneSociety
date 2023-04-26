/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.animalshelter1;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author chuong nguyen
 */
public class HomeController implements Initializable {
 
    @FXML
    private TextField textFieldSearch;
    
    @FXML
    private TextField textFieldSearch2;
    
    @FXML
    private TableView tableView;
       @FXML
    private void switchToLogs() throws IOException {
        App.setRoot("log");
    }
    
    
    @FXML
    private void switchToSearch() throws IOException {
        App.setRoot("search");
    }
    @FXML
    private void SwitchToDelete() throws IOException {
        App.setRoot("Delete");
    }

    @FXML
    private void switchToAdd() throws IOException {
        App.setRoot("Add");
    }
    
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
    @FXML
    TableColumn number = new TableColumn("number");

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

    String databaseURL = "jdbc:sqlite:src/main/resources/com/mycompany/animalshelter1/InhumaneSocietydb.db";
        private ObservableList<faculty> data;

        public HomeController() throws SQLException {
        this.data = FXCollections.observableArrayList();
    }

    

    private void intializeColumns() {
        number = new TableColumn("Number");
        number.setMinWidth(0);
        number.setCellValueFactory(new PropertyValueFactory<faculty, Integer>("number"));

        TableColumn sex = new TableColumn("Sex");
        sex.setMinWidth(0);
        sex.setCellValueFactory(new PropertyValueFactory<faculty, String>("sex"));

        TableColumn species = new TableColumn("Species");
        species.setMinWidth(0);
        species.setCellValueFactory(new PropertyValueFactory<faculty, Integer>("species"));

        TableColumn breed = new TableColumn("Breed");
        breed.setMinWidth(0);
        breed.setCellValueFactory(new PropertyValueFactory<faculty, String>("breed"));
        
        TableColumn name = new TableColumn("Name");
        name.setMinWidth(0);
        name.setCellValueFactory(new PropertyValueFactory<faculty, String>("name"));
                
        TableColumn age = new TableColumn("Age");
        age.setMinWidth(0);
        age.setCellValueFactory(new PropertyValueFactory<faculty, Integer>("age"));
        
        
        TableColumn temper = new TableColumn("Temper");
        temper.setMinWidth(0);
        temper.setCellValueFactory(new PropertyValueFactory<faculty, Integer>("temper"));
         
        TableColumn adoptable = new TableColumn("Adoptable");
        adoptable.setMinWidth(0);
        adoptable.setCellValueFactory(new PropertyValueFactory<faculty, Integer>("adoptable"));
        
        TableColumn username = new TableColumn("Username");
        username.setMinWidth(0);
        username.setCellValueFactory(new PropertyValueFactory<faculty, Integer>("username"));
        
        tableView.setItems(data);
        tableView.getColumns().addAll(number, sex, species, breed, name, age, temper, adoptable, username);
        

    }
    private void loadData() throws SQLException{
        Connection conn = null;
        Statement stmt = null;
 
        try {

            // create a connection to the database
            conn = DriverManager.getConnection(databaseURL);

            System.out.println("Connection to SQLite has been established.");
            String sql = "SELECT * FROM AdoptionTable;";
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                faculty faculty;
                faculty = new faculty(rs.getInt("number"), rs.getString("sex"), rs.getString("species"), rs.getString("Breed"), rs.getInt("age"), rs.getString("name"), rs.getInt("temper"), rs.getInt("adoptable"), rs.getInt("username"));
                System.out.println(faculty.getNumber() + " - " + faculty.getSex() + " - " + faculty.getSpecies() + " - " + faculty.getBreed()+ " - " + faculty.getAge()+ " - " + faculty.getName()+ " - " +faculty.getTemper()+ " - " + faculty.getAdoptable()+ " - " + faculty.getUsername() );
                data.add(faculty);
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
    
    @SuppressWarnings("empty-statement")
    public ObservableList<faculty> search(String search1, String search2) throws SQLException {
        ObservableList<faculty> searchResult = FXCollections.observableArrayList();
        // read data from SQLite database
        
       CreateSQLiteTable();
       String sql = "Select * from AdoptionTable where " + search1 + " like '%" + search2 +"%';";
        
        if (search1.isEmpty()) {
            sql += "Select * from AdoptionTable";
            System.out.println("invalidsearch");
        }



        System.out.println(sql);
        try (Connection conn = DriverManager.getConnection(databaseURL);
                Statement stmt = conn.createStatement()) {
            // create a ResultSet

            ResultSet rs = stmt.executeQuery(sql);
            // checking if ResultSet is empty
            if (rs.next() == false) {
                System.out.println("ResultSet in empty");
            } else {
                // loop through the result set
                do {

                    int Number = rs.getInt("Number");
                    String Sex = rs.getString("sex");
                    String Species = rs.getString("species");
                    String Breed = rs.getString("breed");
                    int Age = rs.getInt("age");
                    String Name = rs.getString("name");
                    int Temper = rs.getInt("temper");
                    int Adoptable = rs.getInt("adoptable");
                    int Username = rs.getInt("username");
                    

                    faculty faculty = new faculty(Number,Sex,Species,Breed,Age,Name,Temper,Adoptable,Username);
                    searchResult.add(faculty);
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return searchResult;
    }

    @FXML
    private void research(ActionEvent event) throws IOException, SQLException {
        
        String search1 = textFieldSearch.getText();
        String search2 = textFieldSearch2.getText();
        ObservableList<faculty> tableItems = search(search1,search2);
        tableView.setItems(tableItems);
        

    }
@FXML
    private void shows(ActionEvent event) throws IOException, SQLException {
        tableView.setItems(data);

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
  
    

