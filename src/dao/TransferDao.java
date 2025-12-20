/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Database.MySqlConnection;
import model.TransferModel;
import java.sql.*;
/**
 *
 * @author Anup Kunwar
 */
public class TransferDao {
    private final MySqlConnection db =  new MySqlConnection();

    public boolean isAccountExists(String accountNumber) {
        String query = "SELECT 1 FROM accounts WHERE account_number = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Account exists check failed: " + e.getMessage());
            return false;
        }
    }

    public boolean hasEnoughBalance(String accountNumber, double amount) {
        String query = "SELECT balance FROM accounts WHERE account_number = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance") >= amount;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Balance check failed: " + e.getMessage());
            return false;
        }
    }

    public boolean transferMoney(TransferModel m) {
        Connection con = null;
        try {
            con = db.getConnection();
            con.setAutoCommit(false);

            // Deduct from source
            String updateSource = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            try (PreparedStatement ps = con.prepareStatement(updateSource)) {
                ps.setDouble(1, m.getAmount());
                ps.setString(2, m.getSourceAccount());
                int rows = ps.executeUpdate();
                if (rows == 0) throw new Exception("Source account update failed");
            }

            // Add to destination
            String updateDest = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            try (PreparedStatement ps = con.prepareStatement(updateDest)) {
                ps.setDouble(1, m.getAmount());
                ps.setString(2, m.getDestinationAccount());
                int rows = ps.executeUpdate();
                if (rows == 0) throw new Exception("Destination account update failed");
            }

            // Log withdrawal (safe: only essential columns)
            String insertWithdraw = "INSERT INTO transactions (account_number, amount, type) VALUES (?, ?, 'WITHDRAWAL')";
            try (PreparedStatement ps = con.prepareStatement(insertWithdraw)) {
                ps.setString(1, m.getSourceAccount());
                ps.setDouble(2, m.getAmount());
                ps.executeUpdate();
            }

            // Log deposit
            String insertDeposit = "INSERT INTO transactions (account_number, amount, type) VALUES (?, ?, 'DEPOSIT')";
            try (PreparedStatement ps = con.prepareStatement(insertDeposit)) {
                ps.setString(1, m.getDestinationAccount());
                ps.setDouble(2, m.getAmount());
                ps.executeUpdate();
            }

            con.commit();
            return true;

        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
            e.printStackTrace();
            if (con != null) {
                try { con.rollback(); } catch (SQLException ex) {}
            }
            return false;
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException ex) {}
            }
        }
    }
}