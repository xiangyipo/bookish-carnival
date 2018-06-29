package com.imooc.service.impl;

import com.imooc.cacheTools.UserCache;
import com.imooc.pojo.SysUser;
import com.imooc.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("mailService")
public class MailServiceImp implements MailService {


    @Resource
    UserCache userCache;

    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String from;


    /**
     * subject 主题
     * html 发送内容
     * 启动线程去发送
     */
    @Override
    public void sendMail(String subject, String html) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(html);
        List<SysUser> sysUserList = userCache.getSysUserList();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (SysUser sysUser : sysUserList) {
                    message.setTo(sysUser.getUserMailbox());
                    mailSender.send(message);
                }
            }
        }).start();
    }
}
