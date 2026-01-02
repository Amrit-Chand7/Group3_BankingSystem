/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;

/**
 *
 * @author amritchand
 */
public class MySqlConnection implements Database{

    @Override
    public Connection openconnection() {
        try{
           
            String password = "ammu@221";
            String username = "root";
            String database = "BankingSystem";
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/"+ database,username,password);  
                
            System.out.println("Successful");
                
            return connection;
              
        }catch(SQLException e){
            System.out.println("Connection Failed: " + e.getMessage());
            return null;
        }
        
        
    }

    @Override
    public void closeConnection(Connection conn) {
        try{
            if (conn !=null && !conn.isClosed()){
            conn.close();
        }
       
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try{
            Statement stmp = conn.createStatement();
            ResultSet result= stmp.executeQuery(query);
            return result;
        }catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
       try{
           Statement stmp = conn.createStatement();
           int result= stmp.executeUpdate(query);
           return result;
       }catch(SQLException e){
           System.out.println(e);
           return -1;
        }
        
    }

    public Connection getConnection() {
        return openconnection();
    }
    
    
    
}