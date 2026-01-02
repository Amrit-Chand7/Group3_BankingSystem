/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MySqlConnection;
import java.sql.*;

/**
 *
 * @author amritchand
 */
public class FetchDetailsDao {
    
    private final MySqlConnection db = new MySqlConnection();

    public String[] fetchByAccountNumber(String accountNumber) {
        
        String[] details = new String[9]; // array to store all values
        String query = "SELECT full_name1, email1, address1, phone1, dob1, account_type1, account_number1, id_proof1 FROM customers WHERE account_number1 = ?";
        String q2 = "SELECT balance FROM accounts WHERE account_number = ?";
        try {
            Connection con = db.getConnection();

           // fetch data from customers table
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, accountNumber);
            ResultSet rs1 = pst.executeQuery();
            
            if (!rs1.next()) {
                return null; // no customer found
            }

            details[0] = rs1.getString("full_name1");
            details[1] = rs1.getString("email1");
            details[2] = rs1.getString("address1");
            details[3] = rs1.getString("phone1");
            details[4] = rs1.getString("dob1");
            details[5] = rs1.getString("account_type1");
            details[6] = rs1.getString("account_number1");
            details[7] = rs1.getString("id_proof1");

            // fetch data from the accounts table
            PreparedStatement ps2 = con.prepareStatement(q2);
            ps2.setString(1, accountNumber);
            ResultSet rs2 = ps2.executeQuery();
            
            if (rs2.next()) {
                details[8] = rs2.getString("balance");
            } else {
                details[8] = "0"; // default if no balance
            }

            return details;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // account not found
    }
}

