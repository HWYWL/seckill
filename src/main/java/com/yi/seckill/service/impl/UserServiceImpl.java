package com.yi.seckill.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yi.seckill.common.BusinessException;
import com.yi.seckill.dao.UserInfoMapper;
import com.yi.seckill.dao.UserPasswordMapper;
import com.yi.seckill.model.UserInfo;
import com.yi.seckill.model.UserModel;
import com.yi.seckill.model.UserPassword;
import com.yi.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户操作逻辑实现
 * @author YI
 * @date 2018-12-19 15:33:05
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserPasswordMapper userPasswordMapper;

    @Override
    public UserModel selectByPrimaryAllId(Integer id) {
        return userInfoMapper.selectByPrimaryAllId(id);
    }

    @Override
    public UserModel selectByPrimaryAllTelPhone(String telphone) {
        return userInfoMapper.selectByPrimaryAllTelPhone(telphone);
    }

    @Override
    public UserInfo selectByTelPhone(String telphone) {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("telphone", telphone);

        return userInfoMapper.selectOneByExample(example);
    }

    @Override
    public UserInfo selectById(Integer id) {
        return userInfoMapper.selectByPrimaryId(id);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public void insertSelective(UserModel userModel) {
        UserInfo userInfo = new UserInfo();
        UserPassword userPassword = new UserPassword();

        BeanUtil.copyProperties(userModel, userInfo);
        BeanUtil.copyProperties(userModel, userPassword);

        // 保存用户数据
        userInfoMapper.insertSelective(userInfo);
        userPassword.setUserId(userInfo.getId());

        // 保存用户密码数据
        userPasswordMapper.insertSelective(userPassword);
    }
}
