package com.sliit.ayu.ayuservice.service;

import java.util.List;

public interface EmailService {
    public void sendEmail(List<String> to, String subject, String body);
}
