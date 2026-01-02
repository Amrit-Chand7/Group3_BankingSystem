/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.ViewNoticeDao;
import java.util.List;
import model.NoticeModel;

/**
 *
 * @author Anup Kunwar
 */
public class ViewNoticeController {
    
    private final ViewNoticeDao dao12 = new ViewNoticeDao();
    
    public List<NoticeModel> fetchNotices() {
        return dao12.fetchNotices();
    }
    
}

    

    
