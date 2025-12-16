/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.ChangePassDao;
import javax.swing.JOptionPane;

/**
 *
 * @author amritchand
 */
public class PassChangeController {
    
    private final ChangePassDao checkInfo= new ChangePassDao();

    public boolean changePassword(String adminEmail,String currentPass, String newPass, String confirmPass){
        
        if (adminEmail.trim().isEmpty() || currentPass.trim().isEmpty() || newPass.trim().isEmpty() || confirmPass.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter all the fields");
            return false;
        }
        
        if (!newPass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(null, "New password and confirm password do not match!");
            return false;   
        }
        if (newPass.length() < 8) {
            JOptionPane.showMessageDialog(null, "New password must be at least 8 characters.");
            return false;
            
        }
        
        if (!checkInfo.verifyEmail(adminEmail)){
            JOptionPane.showMessageDialog(null, "Email does not exist!");
            return false;
        }
        
        if (!checkInfo.verifyCurrentPassword(adminEmail,currentPass)){
            JOptionPane.showMessageDialog(null, "Current password is incorrect!");
            return false;
        }
        
        // 3. Ask for confirmation
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to change your password?",
            "Confirm Add",
            JOptionPane.YES_NO_OPTION
        ); 
        
        
        if (confirm != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Password change cancelled.");
            return false;
        }
        
        if (checkInfo.updatePassword(adminEmail, newPass)) {
            JOptionPane.showMessageDialog(null, "Password changed successfully!");
            return true;
            
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update password!");
            return false;
        }

    }
}

