package com.sliit.ayu.ayuservice.service.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.sliit.ayu.ayuservice.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmailServiceImpl {

    @Autowired
    private SendGrid sendGrid;

    @Value("${admin.notification.email}")
    private String sender;

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${sendgrid.api.new.user.template}")
    private String newUserTemplate;

    public void sendNewUserCreation(UserDTO userDTO) {

        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
            Email from = new Email(sender);
            Email toEmail = new Email(userDTO.getEmail());

            Mail mail = new Mail();
            mail.setFrom(from);
            mail.setTemplateId(newUserTemplate);

            Personalization personalization = new Personalization();
            personalization.addTo(toEmail);

            personalization.addDynamicTemplateData("name", userDTO.getFirstName() + " " + userDTO.getLastName());
            personalization.addDynamicTemplateData("username", userDTO.getUsername());
            personalization.addDynamicTemplateData("password", userDTO.getPassword());

            mail.addPersonalization(personalization);

            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();

            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                log.info("EMAIL SENT STATUS {}, MESSAGE {}, HEADERS {}", response.getStatusCode(), response.getBody(), response.getHeaders());
            } catch (IOException ex) {
                log.error("EMAIL SENT ERROR ", ex);
            }
        }
    }
}
