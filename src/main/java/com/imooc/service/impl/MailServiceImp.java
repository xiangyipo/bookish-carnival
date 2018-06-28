package com.imooc.service.impl;

import com.imooc.cacheTools.UserCache;
import com.imooc.pojo.Mail;
import com.imooc.pojo.SysUser;
import com.imooc.service.MailService;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

@Service("mailService")
public class MailServiceImp implements MailService {


    @Resource
    UserCache userCache;

    private static Mail mail = new Mail();
    private static JavaMailSenderImpl mailSender = createMailSender();

    /**
     * 邮件发送器
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost(mail.getHOST());
        sender.setPort(mail.getPORT());
        sender.setUsername(mail.getUSERNAME());
        sender.setPassword(mail.getPASSWORD());
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "25000");
        p.setProperty("mail.smtp.auth", "false");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * subject 主题
     * html 发送内容
     */
    @Override
    public void sendMail(String subject, String html) throws UnsupportedEncodingException, MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        List<SysUser> sysUserList = userCache.getSysUserList();
        for (SysUser sysUser : sysUserList) {
            messageHelper.setFrom(sysUser.getUserMailbox());
            messageHelper.setTo(sysUser.getUserMailbox());
            mailSender.send(mimeMessage);
        }
    }
}
