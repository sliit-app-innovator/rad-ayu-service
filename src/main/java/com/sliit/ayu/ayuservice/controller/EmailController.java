package com.sliit.ayu.ayuservice.controller;

import com.sliit.ayu.ayuservice.service.EmailService;
import com.sliit.ayu.ayuservice.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/ayu/service/v1/email")
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        try {
            emailService.sendEmail("damith.gg@gmail.com", "SUBJECT", "Test content");
            return "Email sent successfully!";
        } catch (IOException e) {
            return "Failed to send email.";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}