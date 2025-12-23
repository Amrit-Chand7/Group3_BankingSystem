/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MySqlConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.NoticeModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement; 

/**
 *
 * @author Anup Kunwar
 */
public class ViewNoticeDao {
    

    private final MySqlConnection db = new MySqlConnection();

    public List<NoticeModel> fetchNotices() {
    
        List<NoticeModel> list1 = new ArrayList<>();
        String query = "SELECT id, message, created_at FROM notices ORDER BY created_at DESC";

        try {
            Connection con = db.getConnection();

            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs5 = pst.executeQuery();
            
            while (rs5.next())
            {
                NoticeModel notice = new NoticeModel();
                notice.setId(rs5.getInt("id"));
                notice.setMessage(rs5.getString("message"));
                notice.setCreatedAt(rs5.getTimestamp("created_at"));

                list1.add(notice);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list1;
    }
}

