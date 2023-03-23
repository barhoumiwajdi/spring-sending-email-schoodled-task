package com.fivepoints.spring.controllers;

import com.fivepoints.spring.payload.reponses.MessageResponse;
import com.fivepoints.spring.services.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/e-mails")
public class  SendMailController {

    @Autowired
    SendMailService sendMailService;

    @PostMapping(value="simple")
    public ResponseEntity<MessageResponse> sendEmail()
    {
        this.sendMailService.sendEmail();
        return ResponseEntity.ok().body(new MessageResponse("Mail send successfully!"));
    }

    @PostMapping("sendEmailWithHTML")
    public ResponseEntity<MessageResponse> sendEmailWithHTML()
    {
        try {
            this.sendMailService.sendEmailWithHTML();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(new MessageResponse("Mail send successfully!"));
    }

    @PostMapping("sendEmailWithAttachment")
    public ResponseEntity<MessageResponse> sendEmailWithAttachment()
    {
        try {
            this.sendMailService.sendEmailWithAttachment();
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(new MessageResponse("Mail send successfully!"));
    }

}
