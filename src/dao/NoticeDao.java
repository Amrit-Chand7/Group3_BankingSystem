/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import Database.MySqlConnection;
import model.NoticeModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Anup Kunwar
 */

public class NoticeDao {

    private final MySqlConnection db = new MySqlConnection();

    public boolean postNotice(NoticeModel notice) {

        
        if (notice.getMessage() == null || notice.getMessage().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Notice cannot be empty!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Are you sure?",
                "Confirm Notice Post",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null,
                    "Notice posting cancelled.",
                    "Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        
        String sql = "INSERT INTO notices (message) VALUES (?)";
        Connection conn = null;

        try {
            conn = db.openconnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, notice.getMessage());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Notice posted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Database Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;

        } finally {
            db.closeConnection(conn);
        }
    }
}
