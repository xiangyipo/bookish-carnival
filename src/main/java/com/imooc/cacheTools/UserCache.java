package com.imooc.cacheTools;


import com.imooc.pojo.SysUser;
import com.imooc.service.SysUserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户账号密码的缓存
 * 用于方便查询(登录人员不多)把数据放到内存中提高性能、 后期可以集成redis
 * 初始化的时候便去查询数据
 * 十分钟刷新一次
 */
@Configuration
public class UserCache implements InitializingBean {

    List<SysUser> list = new ArrayList();

    @Resource
    SysUserService sysUserService;

    long time = System.currentTimeMillis();

    /**
     * 提供最新的用户列表
     */
    public List<SysUser> getSysUserList() {
        updateList();
        return list;
    }

    /**
     * 实现Springd的InitializingBean接口
     * 初始化调用afterPropertiesSet方法
     */
    @Override
    public void afterPropertiesSet() {
        updateList();
    }

    /**
     * 更新LIST数据
     */
    public void updateList() {
        list = sysUserService.selectAll();
    }

    /**
     * 判断用户账号密码是否正确
     */
    public SysUser isUser(SysUser user) {
        refresh();
        for (SysUser users : list) {
            if (users.getUserNumber().equals(user.getUserNumber())) {
                String decode = sysUserService.userPasswordEncrypt(user.getUserPassword());
                if (users.getUserPassword().equals(decode)) {
                    return users;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 判断10分钟到了刷新缓存
     */
    public void refresh() {
        if (System.currentTimeMillis() - time > 1000 * 10) {
            time = System.currentTimeMillis();
            updateList();
        }
    }

}
