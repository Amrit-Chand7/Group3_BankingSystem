/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.sql.Timestamp;

/**
 *
 * @author Anup Kunwar
 */
public class NoticeModel {
    private int id;
    private String message;
    private Timestamp createdAt;

    public NoticeModel() {}

    public NoticeModel(String message) {
        this.message = message;
    }

    public NoticeModel(int id, String message, Timestamp createdAt) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
    
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
