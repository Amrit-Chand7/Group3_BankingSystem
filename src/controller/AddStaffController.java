/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.AddStaffDao;
import model.AddStaffModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author amritchand
 */


public class AddStaffController {
    private AddStaffDao addStaffDao;

    public AddStaffController() {
        this.addStaffDao = new AddStaffDao();
    }

    // Add new employee
    public boolean addEmployee(String fullName, String email, String password, String phone, String address, String role) {
        // 1. Check for empty fields
        if(fullName.isEmpty() || email.isEmpty() || password.isEmpty() 
           || phone.isEmpty() || address.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Validate email format
        if(!email.contains(".")|| !email.contains("@")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email address!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
         
        }
        // Check role
        if(!role.equalsIgnoreCase("employee")) {
            JOptionPane.showMessageDialog(null, "Role must be 'Employee'!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            // 2. Check if email already exists
            if(addStaffDao.isEmailExists(email)) {
                JOptionPane.showMessageDialog(null, "An employee with this email already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // 3. Ask for confirmation
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to create this employee account?",
                    "Confirm Add",
                    JOptionPane.YES_NO_OPTION
            );

            if(confirm == JOptionPane.YES_OPTION) {
                // 4. Create employee object
                AddStaffModel employee = new AddStaffModel();
                employee.setEmFullName(fullName);
                employee.setEmEmail(email);
                employee.setEmPassword(password);
                employee.setEmPhoneNumber(phone);
                employee.setEmAddress(address);
                employee.setEmRole(role);

                // 5. Add employee to database
                addStaffDao.addEmployee(employee);

                // 6. Success message
                JOptionPane.showMessageDialog(null, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                // User canceled
                JOptionPane.showMessageDialog(null, "Employee creation canceled.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to add employee: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}