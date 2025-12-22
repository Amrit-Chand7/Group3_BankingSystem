/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import dao.NoticeDao;
import model.NoticeModel;
import javax.swing.*;
import java.sql.SQLException;

/**
 *
 * @author Anup Kunwar
 */

public class NoticeController {

    private final NoticeDao noticeDao;

    public NoticeController() {
        this.noticeDao = new NoticeDao();
    }

    // Save notice
    public boolean saveNotice(String message) {
        // 1. Validate input
        if (message == null || message.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Notice cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 2. Ask confirmation
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to post this notice?",
                "Confirm Notice",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Notice posting canceled.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }


        try {
           
            NoticeModel notice = new NoticeModel(message);
           
            noticeDao.addNotice(notice);

            JOptionPane.showMessageDialog(null, "Notice posted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to post notice: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }
}