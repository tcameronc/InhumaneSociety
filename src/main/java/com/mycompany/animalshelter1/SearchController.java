/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.animalshelter1;
import java.time.*;  
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author chuong nguyen
 */
public class SearchController implements Initializable {
     @FXML
    private TableView tableView;
     
         @FXML
    private VBox vBox;

        @FXML
    private void switchToAdd() throws IOException {
        App.setRoot("Add");
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
     private TextField breed;
      @FXML
     private TextField sex;
            @FXML
     private TextField species;
            @FXML
     private TextField name;
          @FXML
    TableColumn number = new TableColumn("number");
      
      
    String databaseURL = "jdbc:sqlite:src/main/resources/com/mycompany/animalshelter1/InhumaneSocietydb.db";
        private ObservableList<Home1> data;

        public SearchController() throws SQLException {
        this.data = FXCollections.observableArrayList();
    }

      
    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void drawText() {
        //Drawing a text 
        Text text = new Text("The Shelter Database");

        //Setting the font of the text 
        text.setFont(Font.font("Edwardian Script ITC", 55));

        //Setting the position of the text 
//        text.setX(600);
//        text.setY(100);
        //Setting the linear gradient 
        Stop[] stops = new Stop[]{
            new Stop(0, Color.DARKSLATEBLUE),
            new Stop(1, Color.RED)
        };
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

        //Setting the linear gradient to the text 
        text.setFill(linearGradient);
        // Add the child to the grid
        vBox.getChildren().add(text);

    }
    
    private void intializeColumns() {
        number = new TableColumn("number");
        number.setMinWidth(50);
        number.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("number"));

        TableColumn sex = new TableColumn("Sex");
        sex.setMinWidth(50);
        sex.setCellValueFactory(new PropertyValueFactory<Home1, String>("sex"));

        TableColumn species = new TableColumn("Species");
        species.setMinWidth(50);
        species.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("species"));

        TableColumn breed = new TableColumn("Breed");
        breed.setMinWidth(50);
        breed.setCellValueFactory(new PropertyValueFactory<Home1, String>("breed"));
        TableColumn name = new TableColumn("name");
        name.setMinWidth(50);
        name.setCellValueFactory(new PropertyValueFactory<Home1, String>("name"));
                
        TableColumn age = new TableColumn("age");
        age.setMinWidth(50);
        age.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("age"));
        
        
        TableColumn temper = new TableColumn("temper");
        temper.setMinWidth(50);
        temper.setCellValueFactory(new PropertyValueFactory<Home1, Integer>("temper"));
         
        TableColumn adoptable = new TableColumn("adoptable");
        adoptable.setMinWidth(50);
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
            String sql = "SELECT * FROM AdoptionTable;";
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

    
    @SuppressWarnings("empty-statement")
    public ObservableList<Home1> search(String _Sex, String _Species, String _Breed, String _Name) throws SQLException {
        ObservableList<Home1> searchResult = FXCollections.observableArrayList();
        // read data from SQLite database
        CreateSQLiteTable();
        String sql = "Select * from AdoptionTable ";

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
                    String Sex = rs.getString("Sex");
                    String Species = rs.getString("Species");
                    String Breed = rs.getString("Breed");
                    int Age = rs.getInt("Age");
                    String Name = rs.getString("Name");
                    int Temper = rs.getInt("Temper");
                    int Adoptable = rs.getInt("Adoptable");
                    

                    Home1 home = new Home1(Number,Sex,Species,Breed,Age,Name,Temper,Adoptable);
                    searchResult.add(home);
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return searchResult;
    }

    @FXML
    private void search(ActionEvent event) throws IOException, SQLException {
        String _Sex = sex.getText().trim();
        String _Species = species.getText().trim();
        String _Breed = breed.getText().trim();
        String _Name = name.getText().trim();

        ObservableList<Home1> tableItems = search( _Sex,_Species,_Breed,_Name);
        tableView.setItems(tableItems);

    }

    private void CreateSQLiteTable() {
String sql = "CREATE TABLE IF NOT EXISTS home (\n"
                + "	number integer PRIMARY KEY,\n"
                + "	sex text NOT NULL,\n"
                + "	species text NOT NULL,\n"
                + "	age interger NOT NULL\n"
                + "	name text NOT NULL\n"
                + "	temper interger NOT NULL\n"
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



  


