/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import javax.swing.JOptionPane;
import dao.NewPasswordDao;

/**
 *
 * @author amritchand
 */
public class NewPassController {

    public boolean validatePassword(String newPass1, String confirmPass1, String emai20) {
        if (newPass1.isEmpty() || confirmPass1.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in both fields.");
            return false;
        }

        if (!newPass1.equals(confirmPass1)) {
            JOptionPane.showMessageDialog(null, "New Password and Confirm Password do not match!");
            return false;
        }

        if (newPass1.length() < 6) {
            JOptionPane.showMessageDialog(null, "Password must be at least 6 characters.");
            return false;
        }
        
        NewPasswordDao changePass3 = new NewPasswordDao();
        boolean changePass4 = changePass3.ChangeOldPass(newPass1, emai20);

        return true;
    }
}

