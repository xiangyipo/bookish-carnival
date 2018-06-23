package com.imooc.service.impl;

import com.github.pagehelper.PageHelper;
import com.imooc.mapper.SysSlideshowMapper;
import com.imooc.mapper.SysSlideshowMapperCustom;
import com.imooc.pojo.SysSlideshow;
import com.imooc.service.SysSlideshowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("sysSlideshowService")
public class SysSlideshowServiceImpl implements SysSlideshowService {

    @Resource
    SysSlideshowMapper sysSlideshowMapper;

    @Resource
    SysSlideshowMapperCustom sysSlideshowMapperCustom;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveSysSlideshow(SysSlideshow sysSlideshow) {
        sysSlideshow.setCreateTime(new Date());
        sysSlideshow.setUpdateTime(new Date());
        sysSlideshow.setRemark(sysSlideshow.getRemark().trim());
        int maxRank = getMaxRank();
        maxRank += 1;
        sysSlideshow.setRank(maxRank);
        sysSlideshow.setState(1);
        sysSlideshowMapper.insert(sysSlideshow);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSysSlideshow(SysSlideshow sysSlideshow) {
        if(!StringUtils.isEmpty(sysSlideshow.getRemark())){
            sysSlideshow.setRemark(sysSlideshow.getRemark().trim());
        }
        sysSlideshowMapper.updateByPrimaryKeySelective(sysSlideshow);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSysSlideshow(String slideshowId) {
        sysSlideshowMapper.deleteByPrimaryKey(slideshowId);
    }

    @Override
    public SysSlideshow querySysSlideshowById(String slideshowId) {
        return sysSlideshowMapper.selectByPrimaryKey(slideshowId);
    }

    @Override
    public List<SysSlideshow> querySysSlideshowListPaged(SysSlideshow sysSlideshow, Integer page, Integer pageSize) {

        // 开始分页
        PageHelper.startPage(page, pageSize);

        Example example = new Example(SysSlideshow.class);
        Example.Criteria criteria = example.createCriteria();

        /**
         * 模糊查询字段筛选
         * */
        /*if (sysSlideshow.getState() != 0) {
            criteria.andLike("state", "%" + sysSlideshow.getState() + "%");
        }*/
        example.orderBy("rank").asc();
        List<SysSlideshow> sysSlideshowList = sysSlideshowMapper.selectByExample(example);
        return sysSlideshowList;
    }

    @Override
    public int selectCount(SysSlideshow sysSlideshow) {
        return sysSlideshowMapper.selectCount(sysSlideshow);
    }

    @Override
    public List<SysSlideshow> select(SysSlideshow sysSlideshow) {
        return sysSlideshowMapper.select(sysSlideshow);
    }

    @Override
    public int getMaxRank() {
        Object maxRank = sysSlideshowMapperCustom.getMaxRank();
        if (maxRank == null) {
            return 0;
        }
        return (Integer) maxRank;
    }
}
