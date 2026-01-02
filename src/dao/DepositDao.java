/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import Database.MySqlConnection;
import model.DepositModel;
/**
 *
 * @author jenis
 */

public class DepositDao {
    
    private final MySqlConnection db = new MySqlConnection();

    // check if account exists
    public boolean isAccountExists(String accountNumber) {
        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT 1 FROM accounts WHERE account_number=?"
            );
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    // deposit money
    public boolean depositMoney(DepositModel m) {
        try {
            Connection con = db.getConnection();

            // update balance
            PreparedStatement ps1 = con.prepareStatement(
                "UPDATE accounts SET balance = balance + ? WHERE account_number=?"
            );
            ps1.setDouble(1, m.getAmount());
            ps1.setString(2, m.getAccountNumber());
            ps1.executeUpdate();

            // insert transaction
            PreparedStatement ps2 = con.prepareStatement(
                "INSERT INTO transactions(account_number, amount, type) VALUES (?, ?, 'DEPOSIT')"
            );
            ps2.setString(1, m.getAccountNumber());
            ps2.setDouble(2, m.getAmount());
            ps2.executeUpdate();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
