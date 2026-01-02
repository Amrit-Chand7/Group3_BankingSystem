/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DepositDao;
import model.DepositModel;
import javax.swing.JOptionPane;

/**
 *
 * @author amritchand
 */


public class DepositController {

    DepositDao dao = new DepositDao();

    // simple deposit method
    public boolean depositMoney(String accountNumber, String amountText) {

        if (accountNumber.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields!");
            return false;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(null, "Amount must be > 0");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount!");
            return false;
        }

        // check account
        if (!dao.isAccountExists(accountNumber)) {
            JOptionPane.showMessageDialog(null, "Account not found!");
            return false;
        }
        // 4. Confirmation
        int confirm1 = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to Deposit", 
            "Confirm Deposit", JOptionPane.YES_NO_OPTION);  
        
        if(confirm1 == JOptionPane.YES_OPTION) {
            
            DepositModel model = new DepositModel(accountNumber, amount);
            boolean success = dao.depositMoney(model);
        
            if (success) {
                JOptionPane.showMessageDialog(null, "Deposit successful!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Deposit failed!");
                return false;
            }
        }
        else{
            return false;
        }
    }
}
