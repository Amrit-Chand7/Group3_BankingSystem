/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.FetchDetailsDao;

/**
 *
 * @author amritchand
 */
public class FetchDetailsController {

    FetchDetailsDao customerDao = new FetchDetailsDao();

    public String[] getfetchByAccountNumber(String accountNumber) {
        return customerDao.fetchByAccountNumber(accountNumber);
    }
}



