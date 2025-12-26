/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.UserDao;
import javax.swing.JOptionPane;

/**
 *
 * @author amritchand
 */

public class LoginController {
    
    UserDao userDao = new UserDao();

    public String login(String email, String password) {
        if((email == null || email.trim().isEmpty()) && (password == null || password.trim().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Please enter your email and password.");
            return null;
        }
        // Step 1: Check for empty fields
        if(email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your email.");
            return null; // stop further execution
        }
        if(password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your password.");
            return null; // stop further execution
        }
        
        String role = userDao.getUserByEmailAndPassword(email, password);
        if ("admin".equals(role)) {
            
            return "admin";
            
        }
        else{
            role = userDao.checkEmployee1(email, password);
            if ("employee".equals(role)) {
                return "employee";
            }
            else {
                JOptionPane.showMessageDialog(null, "Invalid email or password!");
            }
        }
           
    return null;
    }
}

