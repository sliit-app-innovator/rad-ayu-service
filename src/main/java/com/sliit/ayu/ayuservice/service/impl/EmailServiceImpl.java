package com.sliit.ayu.ayuservice.service.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.sliit.ayu.ayuservice.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class EmailServiceImpl {

    @Autowired
    private SendGrid sendGrid;

    @Value("${admin.notification.email}")
    private String sender;

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    public void sendEmail(List<String> to, String subject, String body) {
        to.forEach(email -> {
            try {
                sendEmail(email, subject, body );
            } catch (Exception e) {
                log.error("Error while sending email", e);
            }
        });
    }

    public void sendEmail(String to, String subject, String body) throws Exception {
        Email from = new Email("damith.gg@gmail.com");
        Email toEmail = new Email(to);

        Content content = new Content("text/plain", body);

        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setTemplateId("d-9c96db6602414dd99104958e1a13f5d2");

        Personalization personalization = new Personalization();
        personalization.addTo(toEmail);

      //  personalization.addSubstitution("%name%", "Damith Sulochana  DDD"); // Replace %name% with your template substitution tag
        personalization.addDynamicTemplateData("name", "Damith Sulochana  SSS");

        mail.addPersonalization(personalization);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
