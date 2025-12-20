/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ManageCustomerDao;
import java.util.List;
import javax.swing.JOptionPane;
import model.AddCustomerModel;

/**
 *
 * @author amritchand
 */

public class ManageCustomerController {
   
    private final ManageCustomerDao dao = new ManageCustomerDao();
    
    public List<AddCustomerModel> getAllCustomers() {
        return dao.fetchCustomers();
    }
    
    ManageCustomerDao check = new ManageCustomerDao();
    
    public boolean checkAccount(String searchtxt1) {
        if (!check.isAccountOrPhoneExists(searchtxt1)) {
            JOptionPane.showMessageDialog(null, "Account not found!");
            return false;
        }
        return true; // account exists
        
    }
            
}
