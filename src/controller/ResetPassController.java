/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import dao.ResetPassDao;



/**
 *
 * @author amritchand
 */
public class ResetPassController {
    
    private ResetPassDao resetPassDao = new ResetPassDao();
    private String verificationCode;

    public boolean checkEmail(String emailRecover) {
        if (emailRecover.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an email.");
            return false;
        }
        
        
        boolean exists2 = resetPassDao.emailExists8(emailRecover.trim());
        
        if (!exists2) {
            JOptionPane.showMessageDialog(null, "Email not found.");
            return false;
        }
        return true;
    }
    
    // Method to generate a 6-digit code
    public String generateCode() {
        // Generate a number between 100000 and 999999
        int code = 100000 + (int)(Math.random() * 900000);
        
        verificationCode = String.valueOf(code);
        return verificationCode; 
    } 
    
    // Send email with JavaMail API
    public void sendEmail(String emailRecover) {
        
        String fromEmail = "chandamrit62@gmail.com";   
        String password8 = "sifl rqqt yqbe ecqr"; 

        // Create mail properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password8);
            }
        });

        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRecover));
            message.setSubject("Password Reset Code");
            message.setText("Your verification code is: " + verificationCode);

            // Send message
            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Verification code sent to " + emailRecover);

        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to send email!");
        }
    }
    
    // Verify user input code
    public boolean verifyCode(String inputCode) {
        if (inputCode.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the code.");
            return false;
        }
        else if(inputCode.equals(verificationCode)){
            return true;
        }
        return false;
    }
    
}

