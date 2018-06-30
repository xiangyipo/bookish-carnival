package com.imooc.mapper;

import com.imooc.pojo.SysUser;

public interface SysUserMapperCustom {

    String userPasswordEncrypt(String password);

    int updatePassword(SysUser sysUser);
}