/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.AddCustomerDao;
import model.AddCustomerModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author jenis
 */

public class AddCustomerController {

    private final AddCustomerDao addCustomerDao;

    public AddCustomerController() {
        this.addCustomerDao = new AddCustomerDao();
    }
    
    public String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int digit = (int) (Math.random() * 10);
            accountNumber.append(digit);
        }
        return accountNumber.toString();
    }
    

    public boolean addCustomer(String fullName1, String email1, String dob1, String phone1, String address1, String idProof1, String accountType1, String accountNumber1) {
        // 1. Check for empty fields
        if(fullName1.isEmpty() || email1.isEmpty() || dob1.isEmpty() || phone1.isEmpty() || address1.isEmpty() || idProof1.isEmpty() || accountType1.isEmpty() || accountNumber1.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 2. Validate email format
        if(!email1.contains("@") || !email1.contains(".")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!phone1.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Phone number must be exactly 10 digits!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;        
        }
        try {
            // 3. Check if email or account number already exists
            if(addCustomerDao.isEmailExists(email1)) {
                JOptionPane.showMessageDialog(null, "Customer with this email already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if(addCustomerDao.isAccountNumberExists(accountNumber1)) {
                JOptionPane.showMessageDialog(null, "Account number already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // 4. Confirmation
            int confirm = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to create this customer?", 
                "Confirm Add", JOptionPane.YES_NO_OPTION);

            if(confirm == JOptionPane.YES_OPTION) {
                AddCustomerModel customer = new AddCustomerModel();
                customer.setFullName(fullName1);
                customer.setEmail(email1);
                customer.setDob(dob1);
                customer.setPhone(phone1);
                customer.setAddress(address1);
                customer.setIdProof(idProof1);
                customer.setAccountType(accountType1);
                customer.setAccountNumber(accountNumber1);

                // 5. Add to database
                addCustomerDao.addCustomer(customer);
                addCustomerDao.addCustomer1(customer);

                JOptionPane.showMessageDialog(null, "Customer added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Customer creation canceled.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to add customer: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
