/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

/**
 *
 * @author ma1898
 */

public class LogController implements Initializable{
        TableColumn Time = new TableColumn("Time");
            String databaseURL = "jdbc:sqlite:src/main/resources/com/mycompany/animalshelter1/InhumaneSocietydb.db";
        @FXML
    private TableView tableView;

        private ObservableList<Log> data;
                public LogController() throws SQLException {
        this.data = FXCollections.observableArrayList();
    }
@FXML
    private void switchToHome() throws IOException {
        App.setRoot("home");
    }
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
        private void intializeColumns() {    
        
      
        Time = new TableColumn("Time");
        Time.setMinWidth(250);
        Time.setCellValueFactory(new PropertyValueFactory<Log, Integer>("Time"));

        TableColumn SQL = new TableColumn("SQL");
        SQL.setMinWidth(500);
        SQL.setCellValueFactory(new PropertyValueFactory<Log, String>("SQL"));

        
        
        tableView.setItems(data);
        tableView.getColumns().addAll( Time, SQL);

    }
          private void CreateSQLiteTable() {
        String sql = "CREATE TABLE IF NOT EXISTS LogTable (\n"
                + "	LogID integer PRIMARY KEY,\n"
                + "	Time innteger NOT NULL,\n"
                + "	SQL text NOT NULL,\n"
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
          
             private void loadData() throws SQLException{
        Connection conn = null;
        Statement stmt = null;
 
        try {

            // create a connection to the database
            conn = DriverManager.getConnection(databaseURL);

            System.out.println("Connection to SQLite has been established.");
            String sql = "SELECT * FROM LogTable;";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Log log1;
                log1 = new Log(rs.getInt("LogID"), rs.getString("Time"), rs.getString("SQL"));
                System.out.println(log1.getLogID() + " - " + log1.getTime() + " - " + log1.getSQL() );
                data.add(log1);
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
    
}
