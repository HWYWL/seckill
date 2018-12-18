package com.yi.seckill.service.impl;

import com.yi.seckill.dao.UserInfoMapper;
import com.yi.seckill.model.UserInfo;
import com.yi.seckill.model.UserModel;
import com.yi.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserModel selectByPrimaryAllId(Integer id) {
        return userInfoMapper.selectByPrimaryAllId(id);
    }

    @Override
    public UserInfo selectById(Integer id) {
        return userInfoMapper.selectByPrimaryId(id);
    }
}
