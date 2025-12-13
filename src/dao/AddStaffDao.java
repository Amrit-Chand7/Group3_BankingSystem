/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.AddStaffModel;
import java.sql.*;
import Database.MySqlConnection;
import static dao.PasswordUtils.hashPassword;
/**
 *
 * @author amritchand
 */


public class AddStaffDao {

    private MySqlConnection db = new MySqlConnection();

    // SQL queries
    private final String INSERT_EMPLOYEE = "INSERT INTO employee (em_full_name, em_email, em_password, em_phone_number, em_address, em_role) VALUES (?, ?, ?, ?, ?, ?)";
    private final String CHECK_EMAIL = "SELECT 1 FROM employee WHERE em_email = ? LIMIT 1";

    // Add a new employee
    public void addEmployee(AddStaffModel employee) throws SQLException {
        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_EMPLOYEE)) {
            
            stmt.setString(1, employee.getEmFullName());
            stmt.setString(2, employee.getEmEmail());
            
            // Hash password before saving
            String hashedPassword = hashPassword(employee.getEmPassword());
            
            stmt.setString(3, hashedPassword);
            stmt.setString(4, employee.getEmPhoneNumber());
            stmt.setString(5, employee.getEmAddress());
            stmt.setString(6, employee.getEmRole());

            stmt.executeUpdate();
            
        }

    }

    // Check if email already exists
    public boolean isEmailExists(String email) throws SQLException {
       try (Connection conn = db.getConnection();
               PreparedStatement stmt = conn.prepareStatement(CHECK_EMAIL)) {
           stmt.setString(1, email);
           try (ResultSet rs = stmt.executeQuery()) {
               return rs.next(); // if a record exists, email exists
           }
        }
    }
}