package com.imooc.mapper;

import com.imooc.pojo.SysUser;

import java.util.List;

public interface SysUserMapperCustom {

    String userPasswordEncrypt(String password);

    int getMaxUserNumber();

    int updatePassword(SysUser sysUser);

    int updateMailboxIndex(SysUser sysUser);

    List<SysUser> getReceiveMailboxUser();

}