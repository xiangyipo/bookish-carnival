package com.imooc.controller;

import com.imooc.controller.web.WebContoller;
import com.imooc.pojo.IMoocJSONResult;
import com.imooc.pojo.SysUser;
import com.imooc.service.SysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysUser控制器
 */
@RestController
@RequestMapping("/user/")
public class SysUserContoller extends WebContoller {

    @Resource
    SysUserService sysUserService;


    /**
     * 系统用户分页查询列表
     */
    @PostMapping("queryUserListPaged")
    public IMoocJSONResult queryUserListPaged(SysUser sysUser, Integer page) {
        if (page == null) {
            page = 1;
        }
        List<SysUser> userList = sysUserService.queryUserListPaged(sysUser, page, pageSize);

        int count = sysUserService.selectCount(sysUser);
        int totalPages = (double) count / 10 > count / 10 ? (count / 10) + 1 : count / 10;

        Map result = new HashMap<>();
        result.put("count", count);
        result.put("totalPages", totalPages);
        result.put("list", userList);
        result.put("page", page);

        return IMoocJSONResult.ok(result);
    }


    /**
     * 增加系统用户
     */
    @PostMapping("/saveUser")
    public IMoocJSONResult saveUser(SysUser user){
        user.setId(getId());
        int i = sysUserService.saveUser(user);
        return IMoocJSONResult.ok("增加用户信息成功!!!用户账号为:"+i+"，密码默认为:123456");
    }

    /**
     * 修改系统用户
     */
    @PostMapping("/updateUser")
    public IMoocJSONResult updateUser(SysUser user) {
        sysUserService.updateUser(user);
        return IMoocJSONResult.ok("修改用户信息成功");
    }

    /**
     * 删除系统用户
     */
    @PostMapping("/deleteUser")
    public IMoocJSONResult deleteUser(String userId) {
        sysUserService.deleteUser(userId);
        return IMoocJSONResult.ok("删除成功");
    }

    /**
     * 根据ID获取用户信息
     */
    @PostMapping("/queryUserById")
    public IMoocJSONResult queryUserById(String userId) {
        return IMoocJSONResult.ok(sysUserService.queryUserById(userId));
    }

    /**
     * 修改用户密码
     */
    @PostMapping("/updatePws")
    public IMoocJSONResult updatePws(SysUser sysUser) {
        sysUser.setId(getUserId());
        sysUserService.updatePassword(sysUser);
        return IMoocJSONResult.ok(true);
    }

    /**
    * 修改用户状态
    * */
    @PostMapping("/updateMailboxIndex")
    public IMoocJSONResult updateMailboxIndex(SysUser sysUser) {
        sysUser.setId(getUserId());
        sysUserService.updateMailboxIndex(sysUser);
        return IMoocJSONResult.ok(true);
    }
}
