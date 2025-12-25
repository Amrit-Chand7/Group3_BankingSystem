/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.TransactionModel;

/**
 *
 * @author jenis
 */
public class FetchTransactionDao {
        private final MySqlConnection db = new MySqlConnection();

    public List<TransactionModel> fetchTransaction() {
    
        List<TransactionModel> list1 = new ArrayList<>();
        String query = "SELECT transaction_id, account_number, amount, type, date FROM transactions ORDER BY date DESC";

        try {
            Connection con = db.getConnection();

            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs3 = pst.executeQuery();
            
            while (rs3.next()) {
                
                TransactionModel t = new TransactionModel();
                t.setTransactionId(rs3.getInt("transaction_id"));
                t.setAccountNumber(rs3.getString("account_number"));
                t.setAmount(rs3.getDouble("amount"));
                t.setType(rs3.getString("type"));
                t.setDate(rs3.getTimestamp("date"));

                list1.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list1;
    }
}
