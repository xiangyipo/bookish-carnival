package com.imooc.controller.web;

import com.imooc.cacheTools.UserCache;
import com.imooc.pojo.IMoocJSONResult;
import com.imooc.pojo.SysUser;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 所有的Contoller的父类
 * 提供给其他Contoller继承获取SESSION、request、response使用、备注：（线程安全）
 * 获取当前登录的ID、用户名
 * 提供一个登录接口
 */
@RestController
public class WebContoller {

    public static Integer pageSize = 10;


    @Autowired
    private Sid sid;

    @Autowired
    public HttpServletRequest request;


    @Autowired
    public HttpServletResponse response;


    @Autowired
    UserCache userCache;

    /**
     * 获取唯一ID
     */
    protected String getId() {
        String soleId = sid.nextShort();
        return soleId;
    }


    /**
     * 获取session
     */
    protected HttpSession getSession() {
        return request.getSession();
    }

    /**
     * 获取当前登录用户
     */
    protected SysUser getUser() {
        SysUser user = (SysUser) getSession().getAttribute("sysUser");
        return user;
    }


    /**
     * 获取当前登录用户ID
     */
    protected String getUserId() {
        return getUser().getId();
    }

    /**
     * 获取当前登录用户姓名
     */
    protected String getUserName() {
        return getUser().getUserName();
    }


    /**
     * 登录判断
     */
    @PostMapping("/login")
    public IMoocJSONResult login(SysUser sysUser) {
        SysUser user = userCache.isUser(sysUser);
        boolean index = false;
        if (user != null) {
            index = true;
            getSession().setAttribute("sysUser", user);
        }
        return IMoocJSONResult.ok(index);
    }
}
