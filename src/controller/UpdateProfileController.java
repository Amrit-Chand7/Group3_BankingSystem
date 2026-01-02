/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import javax.swing.JOptionPane;
import dao.UpdateProfileDao;

/**
 *
 * @author Anup Kunwar
 */

public class UpdateProfileController {

    private final UpdateProfileDao profileDao = new UpdateProfileDao();

    public boolean updateProfile(String loginEmail, String empName, String empPhone, String empAddress) {

        // 1. Empty field validation
        if (empName.trim().isEmpty()
                || empPhone.trim().isEmpty() || empAddress.trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Please fill all fields");
            return false;
        }

        // 2. Phone validation (basic)
        if (!empPhone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Enter valid 10-digit phone number");
            return false;
        }

        // 3. Check email exists
        if (!profileDao.verifyEmail(loginEmail)) {
            JOptionPane.showMessageDialog(null, "Email does not exist");
            return false;
        }

        // 4. Confirmation
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to update your profile?",
                "Confirm Update",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Update cancelled");
            return false;
        }

        // 5. Update profile
        if (profileDao.updateProfile(loginEmail, empName, empPhone, empAddress)) {
            JOptionPane.showMessageDialog(null, "Profile updated successfully");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update profile");
            return false;
        }
    }
}

