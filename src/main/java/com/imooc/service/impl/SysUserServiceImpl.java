package com.imooc.service.impl;

import com.github.pagehelper.PageHelper;
import com.imooc.mapper.SysUserMapper;
import com.imooc.mapper.SysUserMapperCustom;
import com.imooc.pojo.SysUser;
import com.imooc.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    /**
     * 生成的Mapper
     */
    @Resource
    SysUserMapper sysUserMapper;

    /**
     * 自定义SQL的Mapper
     */
    @Resource
    SysUserMapperCustom sysUserMapperCustom;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(SysUser user) {
        sysUserMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(SysUser user) {
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(String userId) {
        sysUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public SysUser queryUserById(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(page, pageSize);

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();

        /**
         * 模糊查询字段筛选
         * */
        if (!StringUtils.isEmptyOrWhitespace(user.getUserName())) {
            criteria.andLike("userName", "%" + user.getUserName() + "%");
        }
        example.orderBy("userAge").asc();
        List<SysUser> userList = sysUserMapper.selectByExample(example);

        return userList;
    }

    @Override
    public String userPasswordEncrypt(String password) {
        return sysUserMapperCustom.userPasswordEncrypt(password);
    }

    @Override
    public List<SysUser> selectAll() {
        return sysUserMapper.selectAll();
    }

    @Override
    public int selectCount(SysUser user) {
        return sysUserMapper.selectCount(user);
    }
}
