/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



/**
 * UserDao handles login for the default admin only.
 */
public class UserDao {
    
    // Default admin details
    private final String DEFAULT_ADMIN_NAME = "Amrit Chand Thakuri";

    private final String DEFAULT_ADMIN_EMAIL = "amrit@gmail.com";

    // Hashed default admin password
    private final String password = PasswordUtils.hashPassword("ammu@221");
    
    private MySqlConnection db = new MySqlConnection();
    
    public UserDao() {
        try (Connection conn = db.openconnection()) {

            // Check if admin already exists
            String checkQuery = "SELECT id FROM admin WHERE email = ?";
            PreparedStatement psCheck = conn.prepareStatement(checkQuery);
            psCheck.setString(1, DEFAULT_ADMIN_EMAIL);
            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) { // admin does not exist
                // Insert default admin
                String insert = "INSERT INTO admin (full_name, email, password, role) VALUES (?, ?, ?, ?)";
                PreparedStatement psInsert = conn.prepareStatement(insert);
                psInsert.setString(1, DEFAULT_ADMIN_NAME);
                psInsert.setString(2, DEFAULT_ADMIN_EMAIL);
                psInsert.setString(3, password); 
                psInsert.setString(4, "admin");
                psInsert.executeUpdate();
                System.out.println("Default admin inserted successfully.");
            } else {
                System.out.println("Default admin already exists.");
            }

        } catch (Exception e) {
            System.err.println("Error inserting admin: " + e.getMessage());
        }
    }
       

    public String getUserByEmailAndPassword(String email, String pass){
        try (Connection conn = db.openconnection()) {
            String sql = "SELECT password, role FROM admin WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                String dbHashedPass = rs.getString("password");
                String role = rs.getString("role");
                if(dbHashedPass.equals(PasswordUtils.hashPassword(pass.trim()))){
                    return role; // return "admin"
                }
            }
        } catch(Exception e){
            System.err.println("Admin login error: " + e.getMessage());
        }
        return null;
    }
        
    public String checkEmployee1(String email, String password) {
        
        // CHECK EMPLOYEE 
        String hash1 = PasswordUtils.hashPassword(password);

        try (Connection conn = db.openconnection()) {
            String sql = "SELECT em_email, em_password, em_role FROM employee WHERE em_email=? AND em_password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, hash1);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return "employee";

            }
        } catch (Exception e) {
            System.err.println("Employee login error: " + e.getMessage());
        }
        
    // Return null if credentials are incorrect
    return null;
    }
}
