package com.imooc.service;

import com.imooc.pojo.SysUser;

import java.util.List;

public interface SysUserService {

    void saveUser(SysUser user);

    void updateUser(SysUser user);

    void deleteUser(String userId);

    SysUser queryUserById(String userId);

    List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize);

    String userPasswordEncrypt(String password);

    List<SysUser> selectAll();

    int selectCount(SysUser user);

    int updatePassword(SysUser user);

}
