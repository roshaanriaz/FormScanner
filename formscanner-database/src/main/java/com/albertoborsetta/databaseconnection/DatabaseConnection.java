/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albertoborsetta.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author adnan0944
 */
public class DatabaseConnection {
    
    public static void main(String[] args) {
        DatabaseConnection dc = new DatabaseConnection();
        dc.getAll();
        
    }
    
    private void displayActor(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("implementation_info_id") + "\t"
                    + rs.getString("implementation_info_name"));
 
        }
    }
    public void getAll() {
 
        String SQL = "Select * FROM information_schema.sql_implementation_info";
 
        try (Connection conn = connectDatabase();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
            // display actor information
            displayActor(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public Connection connectDatabase(){
        Connection c = null;
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
            
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("not connected");
        }
        System.out.println("Opened Database Successfully");
        return c;
    }
    
    
}
