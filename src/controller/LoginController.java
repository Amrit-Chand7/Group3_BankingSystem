/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.UserDao;
import model.User;
import view.AdminUi;
import view.Login;
import javax.swing.JOptionPane;

/**
 *
 * @author amritchand
 */

public class LoginController {

    private UserDao userDao;
    private Login loginView;

    public LoginController(Login loginView) {
        this.loginView = loginView;
        this.userDao = new UserDao();
    }

    public void login(String email, String password) {
        if((email == null || email.trim().isEmpty()) && (password == null || password.trim().isEmpty())) {
            JOptionPane.showMessageDialog(loginView, "Please enter your email and password.");
            return;
        }
        // Step 1: Check for empty fields
        if(email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(loginView, "Please enter your email.");
            return; // stop further execution
        }
        if(password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(loginView, "Please enter your password.");
            return; // stop further execution
        }
        User user = userDao.getUserByEmailAndPassword(email, password);
        if(user != null) {
            if(user.getRole().trim().equalsIgnoreCase("admin")) {
                // Open Admin window
                AdminUi adminUi = new AdminUi();
                adminUi.setVisible(true);
               
            } else {
                JOptionPane.showMessageDialog(loginView, "Only admin login implemented now.");
            }
        } else {
            JOptionPane.showMessageDialog(loginView, "Invalid email or password!");
        }
    }
}

