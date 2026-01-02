/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import Database.MySqlConnection;

/**
 *
 * @author amritchand
 */
public class ResetPassDao {
    
    private MySqlConnection db = new MySqlConnection();

    // Check email in employee and admin tables
    public boolean emailExists8(String emailRecover) {

        String defaultAdmin1 = "chandamrit62@gmail.com";
        if (emailRecover.equalsIgnoreCase(defaultAdmin1)) {
            return true;
        }

        // check from employee table
        String sql = "SELECT em_email FROM employee WHERE em_email=?";
        try (Connection conn = db.openconnection();
             PreparedStatement ps4 = conn.prepareStatement(sql)) {

            ps4.setString(1, emailRecover);
            ResultSet rs = ps4.executeQuery();
            return rs.next(); 

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

