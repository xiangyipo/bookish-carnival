package com.imooc.controller;

import com.imooc.controller.web.WebContoller;
import com.imooc.pojo.IMoocJSONResult;
import com.imooc.service.MailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/mail/")
@RestController
public class MailContoller extends WebContoller {

    @Resource
    MailService mailService;


    /**
    * 向所有后台管理发送邮件
    * */
    @RequestMapping("sendMail")
    public IMoocJSONResult sendMail(){
        String subject = "客户信息";
        String html = "要发送邮件的内容----目前没有模板---定义好模板！！！";
        try {
            mailService.sendMail(subject,html);
        } catch (Exception e) {
            e.printStackTrace();
            return IMoocJSONResult.ok("发送失败");
        }
        return IMoocJSONResult.ok("发送成功");
    }
}
