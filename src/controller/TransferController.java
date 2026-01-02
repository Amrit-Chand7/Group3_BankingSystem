/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import dao.TransferDao;
import model.TransferModel;
import javax.swing.JOptionPane;
/**
 *
 * @author Anup Kunwar
 */
public class TransferController {
    private final TransferDao dao = new TransferDao();

    public boolean transferMoney(String sourceAcc, String destAcc, String amountText) {

        if (sourceAcc.isEmpty() || destAcc.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields!");
            return false;
        }

        if (sourceAcc.equals(destAcc)) {
            JOptionPane.showMessageDialog(null, "Source and destination cannot be the same!");
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

        if (!dao.isAccountExists(sourceAcc)) {
            JOptionPane.showMessageDialog(null, "Source account not found!");
            return false;
        }

        if (!dao.isAccountExists(destAcc)) {
            JOptionPane.showMessageDialog(null, "Destination account not found!");
            return false;
        }

        if (!dao.hasEnoughBalance(sourceAcc, amount)) {
            JOptionPane.showMessageDialog(null, "Insufficient funds in source account!");
            return false;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
            "Transfer Rs." + amount + " from " + sourceAcc + " to " + destAcc + "?",
            "Confirm Transfer", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return false;
        }

        TransferModel model = new TransferModel(sourceAcc, destAcc, amount);
        boolean success = dao.transferMoney(model);

        if (success) {
            JOptionPane.showMessageDialog(null, "Transfer successful!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Transfer failed!");
            return false;
        }
    }
}