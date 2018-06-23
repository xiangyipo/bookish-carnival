package com.imooc.service;

import com.imooc.pojo.SysSlideshow;

import java.util.List;

public interface SysSlideshowService {
    void saveSysSlideshow(SysSlideshow sysSlideshow);

    void updateSysSlideshow(SysSlideshow sysSlideshow);

    void deleteSysSlideshow(String slideshowId);

    SysSlideshow querySysSlideshowById(String slideshowId);

    List<SysSlideshow> querySysSlideshowListPaged(SysSlideshow sysSlideshow, Integer page, Integer pageSize);

    int selectCount(SysSlideshow sysSlideshow);

    List<SysSlideshow> select(SysSlideshow sysSlideshow);

    int getMaxRank();

}