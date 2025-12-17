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
/**
 *
 * @author Anup Kunwar
 */

public class UpdateProfileDao {

    private final MySqlConnection db = new MySqlConnection();

    // Verify email exists
    public boolean verifyEmail(String empEmail) {
        String sql = "SELECT 1 FROM employee WHERE em_email = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empEmail);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update profile details
    public boolean updateProfile(String empEmail, String empName, String empPhone, String empAddress) {

        String sql = "UPDATE employee SET em_full_name = ?, em_phone_number = ?, em_address = ? WHERE em_email = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empName);
            ps.setString(2, empPhone);
            ps.setString(3, empAddress);
            ps.setString(4, empEmail);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

