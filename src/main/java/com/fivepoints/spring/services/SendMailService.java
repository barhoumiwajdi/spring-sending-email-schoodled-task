package com.fivepoints.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(){
        System.out.println("Sending Email...");
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("wajdi.barhoumi26@gmail.com");
        mail.setSubject("Testing from Spring Boot");
        mail.setText("Hello World \n Spring Boot Email");
        // Send mail with javaMailSender
        javaMailSender.send(mail);
    }

    // You can also send email in Spring with Thymeleaf
    // Check this link for more infos:
    // Link: https://www.thymeleaf.org/doc/articles/springmail.html
    public void sendEmailWithHTML() throws MessagingException {
        System.out.println("Sending Email...");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // encoding = utf-8
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setTo("Feres.cherif150817@gmail.com");
        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // Add true argument to set type to text/html :
        helper.setText("<h1>E-mail send with HTML Formatting!</h1>", true);

        // Send mail
        javaMailSender.send(mimeMessage);

    }

    // Other way to create an attachment file in the mail helper
    // Link : https://mkyong.com/spring/spring-sending-e-mail-with-attachment/
    public void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo("wajdi.barhoumi26@gmail.com");
        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image! Testing from Spring Boot</h1>", true);
        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
        javaMailSender.send(mimeMessage);

    }
}
