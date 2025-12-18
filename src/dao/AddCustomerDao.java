/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.AddCustomerModel;
import java.sql.*;
import Database.MySqlConnection;
/**
 *
 * @author jenis
 */

public class AddCustomerDao {

    private final MySqlConnection db = new MySqlConnection();

    // SQL queries with exact column names
    private final String INSERT_CUSTOMER = "INSERT INTO customers (full_name1, email1, dob1, phone1, address1, id_proof1, account_type1, account_number1) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String CHECK_EMAIL = "SELECT 1 FROM customers WHERE email1 = ? LIMIT 1";
    private final String CHECK_ACCOUNT_NUMBER = "SELECT 1 FROM customers WHERE account_number1 = ? LIMIT 1";

    // Add new customer
    public void addCustomer(AddCustomerModel customer) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CUSTOMER)) {

            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getDob());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getAddress());
            stmt.setString(6, customer.getIdProof());
            stmt.setString(7, customer.getAccountType());
            stmt.setString(8, customer.getAccountNumber());

            stmt.executeUpdate();
        }
    }

    // Check if email already exists
    public boolean isEmailExists(String email) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(CHECK_EMAIL)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Check if account number already exists
    public boolean isAccountNumberExists(String accountNumber) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(CHECK_ACCOUNT_NUMBER)) {
            stmt.setString(1, accountNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
