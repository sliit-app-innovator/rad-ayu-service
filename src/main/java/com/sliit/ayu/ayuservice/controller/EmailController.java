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
         //   emailService.sendNewUserCreation("damith.gg@gmail.com", null);
            return "Email sent successfully!";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}