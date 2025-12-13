/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import dao.NoticeDao;
import model.NoticeModel;
/**
 *
 * @author Anup Kunwar
 */
public class NoticeController {

    private final NoticeDao noticeDao = new NoticeDao();

    public boolean saveNotice(String text) {
        NoticeModel model = new NoticeModel(text);
        return noticeDao.postNotice(model);
    }
}