/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement; 
import java.util.ArrayList;
import java.util.List;
import model.AddCustomerModel;


/**
 *
 * @author amritchand
 */
public class ManageCustomerDao {
    
    private final MySqlConnection db = new MySqlConnection();

    public List<AddCustomerModel> fetchCustomers() {
        
        List<AddCustomerModel> list = new ArrayList<>();

        try {
            Connection con = db.getConnection();
            Statement st = con.createStatement();
  
            String sql = "SELECT * FROM customers";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                AddCustomerModel c = new AddCustomerModel();
                c.setFullName(rs.getString("full_name1"));
                c.setPhone(rs.getString("phone1"));
                c.setAccountNumber(rs.getString("account_number1"));
                c.setAccountType(rs.getString("account_type1"));
                list.add(c);
              
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
         return list;
    }
    
    // Check if account number OR phone exists
    public boolean isAccountOrPhoneExists(String searchtxt1) {
        
        try {
            Connection con = db.getConnection();
            String sql = "SELECT 1 FROM customers WHERE account_number1 = ? OR phone1 = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, searchtxt1);
            ps.setString(2, searchtxt1);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // returns true if a row exists
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}

