/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import Database.MySqlConnection;
import model.NoticeModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Anup Kunwar
 */

public class NoticeDao {

    private final MySqlConnection db = new MySqlConnection();

    private final String INSERT_NOTICE = "INSERT INTO notices (message) VALUES (?)";

    // Add a new notice
    public void addNotice(NoticeModel notice) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_NOTICE)) {

            stmt.setString(1, notice.getMessage());
            stmt.executeUpdate();
        }
    }
}