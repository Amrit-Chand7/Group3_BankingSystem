/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static dao.PasswordUtils.hashPassword;

/**
 *
 * @author amritchand
 */
public class ChangePassDao {
    
    private final MySqlConnection db = new MySqlConnection();
    public boolean verifyEmail( String empEmail) {

        String sql = "SELECT 1 FROM employee WHERE em_email = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empEmail);
            ResultSet rs = ps.executeQuery();

            // If a record exists, email is valid
            return rs.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Verify current password
    public boolean verifyCurrentPassword(String empEmail, String currentPass) {
        String sql = "SELECT em_password FROM employee WHERE em_email = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empEmail);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String dbHashedPass = rs.getString("em_password");
                return dbHashedPass.equals(hashPassword(currentPass));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Update password
    public boolean updatePassword(String empEmail, String newPass) {
        String sql = "UPDATE employee SET em_password = ? WHERE em_email = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, hashPassword(newPass));
            ps.setString(2, empEmail);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

}