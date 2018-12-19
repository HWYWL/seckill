package com.yi.seckill.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yi.seckill.common.BusinessException;
import com.yi.seckill.common.EmBusinessError;
import com.yi.seckill.common.EmLoginType;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户操作逻辑实现
 *
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
    public List<UserInfo> selectAllUser() {
        return userInfoMapper.selectAll();
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

    @Override
    public UserInfo login(String telphone, String otpCode, String password, Integer type,
                          HttpServletRequest httpServletRequest) throws BusinessException {

        UserInfo userInfo = new UserInfo();
        // 手机号+密码登录
        if (type == EmLoginType.USERNAME_PHONE_PASSWORD.getTypeCode()) {
            UserModel userModel = selectByPrimaryAllTelPhone(telphone);
            // 用户不存在
            if (userModel == null) {
                throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
            } else if (StrUtil.equals(userModel.getEncrptPassword(), password)) {
                BeanUtil.copyProperties(userModel, userInfo);
            } else {
                throw new BusinessException(EmBusinessError.USER_LOGIN_PASSWORD_ERROR);
            }
        } else if (type == EmLoginType.PHONE_OTP.getTypeCode()) {
            userInfo = selectByTelPhone(telphone);
            // 验证码登录
            String inSessionOtpCode = (String) httpServletRequest.getSession().getAttribute(telphone);
            // 用户不存在
            if (userInfo == null) {
                throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
            } else if (StrUtil.equals(otpCode, inSessionOtpCode)) {
                System.out.println("登陆成功");
            } else {
                throw new BusinessException(EmBusinessError.OTP_CODE_NOT_EXIST);
            }
        }

        return userInfo;
    }
}
