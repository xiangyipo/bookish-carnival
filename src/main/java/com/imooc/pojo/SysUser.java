package com.imooc.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_user")
@Data
public class SysUser {
    @Id
    private String id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_sex")
    private String userSex;

    @Column(name = "user_age")
    private Integer userAge;

    @Column(name = "user_number")
    private String userNumber;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_mailbox")
    private String userMailbox;
}