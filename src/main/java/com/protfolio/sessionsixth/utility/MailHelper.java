package com.protfolio.sessionsixth.utility;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.logging.Logger;

public class MailHelper {

    public static void sendEmail(JavaMailSender javaMailSender, String subject, String message, String... email){
        //Sending an email
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);

        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        try {
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            e.printStackTrace();
            Logger.getGlobal().info(e.getMessage());
        }
    }
}
