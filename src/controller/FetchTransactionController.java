/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.FetchTransactionDao;
import java.util.List;
import model.TransactionModel;

/**
 *
 * @author jenis
 */
public class FetchTransactionController {
    
    private final FetchTransactionDao dao10 = new FetchTransactionDao();
    
    public List<TransactionModel> fetchTransaction() {
        return dao10.fetchTransaction();
    }
    
}
