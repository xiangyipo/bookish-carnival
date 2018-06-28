package com.imooc.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailService {
    void sendMail(String subject, String html) throws UnsupportedEncodingException, MessagingException;
}
