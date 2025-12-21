/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.DeleteCustomerDao;
import javax.swing.JOptionPane;

/**
 *
 * @author amritchand
 */
public class DeleteCustomerController {
    
    private final DeleteCustomerDao dao20 = new DeleteCustomerDao();
    
    
    public boolean confirmAndDelete(String accNum, String accBalance) {
        

        
        int choice2 = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to delete this account?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);
    
        
        if (choice2 != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Customer deletion canceled");
            return false;
        }
                          
            
        boolean deleted2 = dao20.deleteByAccountNumber(accNum);
            
        if (deleted2) {
            JOptionPane.showMessageDialog(null, "Customer deleted successfully!");
            return true;
                
        }else {
            JOptionPane.showMessageDialog(null, "Failed to delete customer.");
            return false;
        }
    }
}
