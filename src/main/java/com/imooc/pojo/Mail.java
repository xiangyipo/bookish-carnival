package com.imooc.pojo;

import lombok.Data;

@Data
public class Mail {

    private String HOST = "smtp.163.com";
    private Integer PORT = 25;
    private String USERNAME = "pbh3644@163.com";
    private String PASSWORD = "pbh.3644";
}
