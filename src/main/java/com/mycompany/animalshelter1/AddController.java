/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.animalshelter1;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Chuong
 */
public class AddController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void switchToSearch() throws IOException {
        App.setRoot("search");
    }
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }
      @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
    @FXML
    private TableView tableView;
    @FXML
    private Button InsertButton;
    
    @FXML
    TableColumn number = new TableColumn("number");
    
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
        private ObservableList<Home1> data;

        public AddController() throws SQLException {
        this.data = FXCollections.observableArrayList();
    }

    

    private void intializeColumns() {    
        
        number = new TableColumn("Number");
        number.setMinWidth(100);
        number.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("number"));

        TableColumn sex = new TableColumn("Sex");
        sex.setMinWidth(100);
        sex.setCellValueFactory(new PropertyValueFactory<Home1, String>("sex"));

        TableColumn species = new TableColumn("Species");
        species.setMinWidth(100);
        species.setCellValueFactory(new PropertyValueFactory<Home1, String>("species"));

        TableColumn breed = new TableColumn("Breed");
        breed.setMinWidth(100);
        breed.setCellValueFactory(new PropertyValueFactory<Home1, String>("breed"));
        
        TableColumn name = new TableColumn("Name");
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory<Home1, String>("name"));
                
        TableColumn age = new TableColumn("Age");
        age.setMinWidth(100);
        age.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("age"));
        
        
        TableColumn temper = new TableColumn("Temper");
        temper.setMinWidth(100);
        temper.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("temper"));
         
        TableColumn adoptable = new TableColumn("Adoptable");
        adoptable.setMinWidth(100);
        adoptable.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("adoptable"));
        
        tableView.setItems(data);
        tableView.getColumns().addAll(number, sex, species, breed, name, age, temper, adoptable);

    }
        @FXML
    private TextField TxtBreed;
     @FXML
    private TextField TxtSpecies;
      @FXML
    private TextField TxtSex;
     @FXML
    private TextField TxtNumber;
     @FXML
    private TextField TxtAge;
    @FXML
    private TextField TxtName;     
    @FXML
    private TextField TxtTemper;
    @FXML
    private TextField TxtAdoptable;
     @FXML
     private Label footerLabel;
    public void insert( int number, String sex
    , String species
    , String breed
    , int age
    , String name
    , int temper
    , int adoptable) throws SQLException {
        int last_inserted_id = 0;
        Connection conn = null;
        try {
            // create a connection to the database

            conn = DriverManager.getConnection(databaseURL);

            System.out.println("Connection to SQLite has been established.");

            System.out.println("Inserting one record!");

            String sql = "INSERT INTO AdoptionTable(number,sex,species,breed,age,name,temper,adoptable, username) VALUES(?,?,?,?,?,?,?,?, 1113)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, number);
            pstmt.setString(2, sex);
            pstmt.setString(3, species);
            pstmt.setString(4, breed);
            pstmt.setInt(5, age);
            pstmt.setString(6, name);
            pstmt.setInt(7, temper);
            pstmt.setInt(8, adoptable);

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
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
        System.out.println("last_inserted_id " + last_inserted_id);
  
        data.add(new Home1(number,sex,species,breed,age,name,temper,adoptable));
    }
    

    public void handleAddAnimal(ActionEvent actionEvent) {

        System.out.println("Number: " + TxtNumber.getText() + "\nSex: " + TxtSex.getText() + "\nSpecies: " + TxtSpecies.getText()+  "\nBreed: " + TxtBreed.getText());

        try {
            // insert a new rows
            insert(Integer.parseInt(TxtNumber.getText()), 
                    TxtSex.getText(),TxtSpecies.getText(),TxtBreed.getText(),
                    Integer.parseInt(TxtAge.getText()),TxtName.getText(),Integer.parseInt(TxtTemper.getText()),Integer.parseInt(TxtAdoptable.getText()));

            System.out.println("Data was inserted Successfully");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        TxtNumber.setText("");
        TxtSex.setText("");
        TxtSpecies.setText("");
        TxtBreed.setText("");
        TxtAge.setText("");
        TxtTemper.setText("");
        TxtAdoptable.setText("");
        TxtName.setText("");
        

        footerLabel.setText("Record inserted into table successfully!");
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
                Home1 home1;
                home1 = new Home1(rs.getInt("number"), rs.getString("sex"), rs.getString("species"), rs.getString("Breed"), rs.getInt("Age"), rs.getString("name"), rs.getInt("temper"), rs.getInt("adoptable"));
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
                + "	age integer NOT NULL\n"
                + "	name text NOT NULL\n"
                + "	temper integer NOT NULL\n"
                + "	adoptable text NOT NULL\n"
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
