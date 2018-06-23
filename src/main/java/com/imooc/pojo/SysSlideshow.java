package com.imooc.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_slideshow")
@Data
public class SysSlideshow {
    @Id
    private String id;

    private String url;

    private Integer state;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_user_id")
    private String updateUserId;

    private Integer rank;

    private String remark;
}