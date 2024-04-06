package com.doan.elearning.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
 import org.springframework.mail.javamail.JavaMailSender;


import java.util.List;


import org.springframework.stereotype.Service;

@Service
public class EmailUtil {
   @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(List<String> diaChiDen, String tieuDe, String noiDung) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(diaChiDen.toArray(new String[0]));
            simpleMailMessage.setSubject(tieuDe);
            simpleMailMessage.setText(noiDung);

            mailSender.send(simpleMailMessage);
       
          
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
}