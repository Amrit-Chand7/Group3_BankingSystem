/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author amritchand
 */
public class DeleteCustomerDao {
    
    private final MySqlConnection db = new MySqlConnection();
    
    public boolean deleteByAccountNumber(String accNum) {
        String query = "DELETE FROM customers WHERE account_number1 = ?";
        
        try (Connection con = db.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, accNum);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
