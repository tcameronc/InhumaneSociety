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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



public class DeleteController implements Initializable {

    @FXML
    public TableView tableView;
    private ObservableList<Home1> data;
    
     @FXML
    private TextField age, adopt, temper;
    
    @FXML
    private Button DeleteActionButton;
    @FXML
    TableColumn number = new TableColumn("Number");
    @FXML
     private void switchToHome() throws IOException {
        App.setRoot("home");}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               // TODO
        try {
            loadData();
        } catch (SQLException ex) {
            
            System.out.println(ex.toString());
        }
        intializeColumns();
        CreateSQLiteTable();
    }    
    
          public DeleteController() throws SQLException {
        this.data = FXCollections.observableArrayList();
    }
        public void deleteRecord(int number, int SelectedIndex) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/mycompany/animalshelter1/InhumaneSocietydb.db");

            String sql = "DELETE FROM AdoptionTable WHERE Number=" + Integer.toString(number);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            tableView.getItems().remove(SelectedIndex);
            System.out.println("Record Deleted Successfully");
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    @FXML
    private void handleDeleteAction(ActionEvent event) throws IOException {
        System.out.println("Delete animals");
        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                System.out.println("Handle Delete Action");
                Home1 home = (Home1) tableView.getSelectionModel().getSelectedItem();
                System.out.println("Number: " + home.getNumber());
                System.out.println("Name: " + home.getName());
   
                deleteRecord(home.getNumber(), selectedIndex);
            }

        }
    }
     private void intializeColumns() {    
        
        number = new TableColumn("Number");
        number.setMinWidth(100);
        number.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("Number"));

        TableColumn sex = new TableColumn("Sex");
        sex.setMinWidth(100);
        sex.setCellValueFactory(new PropertyValueFactory<Home1, String>("Sex"));

        TableColumn species = new TableColumn("Species");
        species.setMinWidth(100);
        species.setCellValueFactory(new PropertyValueFactory<Home1, String>("Species"));

        TableColumn breed = new TableColumn("Breed");
        breed.setMinWidth(100);
        breed.setCellValueFactory(new PropertyValueFactory<Home1, String>("Breed"));
        
        TableColumn name = new TableColumn("Name");
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory<Home1, String>("Name"));
                
        TableColumn age = new TableColumn("Age");
        age.setMinWidth(100);
        age.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("Age"));        
        
        TableColumn temper = new TableColumn("Temper");
        temper.setMinWidth(100);
        temper.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("Temper"));
         
        TableColumn adoptable = new TableColumn("Adoptable");
        adoptable.setMinWidth(100);
        adoptable.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("Adoptable"));
        
        tableView.setItems(data);
        tableView.getColumns().addAll(number, sex, species, breed, name, age, temper, adoptable);

    }
     
         String databaseURL = "jdbc:sqlite:src/main/resources/com/mycompany/animalshelter1/InhumaneSocietydb.db";
         


      public void update(int Temper ,int Age,int Adoptable, int selectedIndex, int Number) throws SQLException {

        Connection conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(databaseURL);
            String sql = "UPDATE AdoptionTable SET Age= ?, Adoptable =?, Temper =? Where Number = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Integer.toString(Age));
            pstmt.setString(2, Integer.toString(Adoptable));
            pstmt.setString(3, Integer.toString(Temper));

            pstmt.setString(4, Integer.toString(Number));

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
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

      
      
    @FXML
    private void handleUpdateRecord(ActionEvent event) throws IOException, SQLException {

        //Check whether item is selected and set value of selected item to Label
        if (tableView.getSelectionModel().getSelectedItem() != null) {

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            System.out.println("Selected Index: " + selectedIndex);

            if (selectedIndex >= 0) {

                Home1 home1 = (Home1) tableView.getSelectionModel().getSelectedItem();
                System.out.println("Number: " + home1.getNumber());

                try {
                    // insert a new rows
                    update(Integer.parseInt(temper.getText()), Integer.parseInt(age.getText()), Integer.parseInt(adopt.getText()), selectedIndex, home1.getNumber());

                    System.out.println("Record updated successfully!");
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }

                age.setText("");
                adopt.setText("");
                temper.setText("");

                data.clear();
                loadData();
                tableView.refresh();
            }

        }

    }

      private void loadData() throws SQLException{
        Connection conn = null;
        Statement stmt = null;
 
        try {

            // create a connection to the database
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/mycompany/animalshelter1/InhumaneSocietydb.db");

            System.out.println("Connection to SQLite has been established.");
            String sql = "SELECT * FROM AdoptionTable;";
            // Ensure we can query the actors table
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Home1 home1;
                home1 = new Home1(rs.getInt("Number"), rs.getString("Sex"), rs.getString("Species"), rs.getString("Breed"), rs.getInt("Age"), rs.getString("Name"), rs.getInt("Temper"), rs.getInt("Adoptable"));
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
        String sql = "CREATE TABLE IF NOT EXISTS AdoptionTable (\n"
                + "	Number integer PRIMARY KEY,\n"
                + "	Sex text NOT NULL,\n"
                + "	Species text NOT NULL,\n"
                + "	Breed text NOT NULL\n"               
                + "	Age integer NOT NULL\n"
                + "	Name text NOT NULL\n"
                + "	Temper integer NOT NULL\n"
                + "	Adoptable text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/mycompany/animalshelter1/InhumaneSocietydb.db");
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);  
    
        System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
}
