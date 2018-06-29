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
     */
    @RequestMapping("sendMail")
    public IMoocJSONResult sendMail() {
        String subject = "系统内容提示-有新客户请及时回复";
        String html = "有新客户请及时回复";
        try {
            mailService.sendMail(subject, html);
        } catch (Exception e) {
            e.printStackTrace();
            return IMoocJSONResult.ok("发送失败");
        }
        return IMoocJSONResult.ok("发送成功");
    }
}
