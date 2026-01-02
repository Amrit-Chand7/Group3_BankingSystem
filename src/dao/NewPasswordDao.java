/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author amritchand
 */
public class NewPasswordDao {
    
    private final MySqlConnection db =  new MySqlConnection();

    public boolean ChangeOldPass(String newPass1,String emai20) {
               
        String sql = "UPDATE employee SET em_password = ? WHERE em_email = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            String hashed2 = PasswordUtils.hashPassword(newPass1);
            
            System.out.print(hashed2);

            ps.setString(1, hashed2);
            ps.setString(2, emai20);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Password updated successfully!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update password. User not found.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
            return false;
        }
    }
}
 

