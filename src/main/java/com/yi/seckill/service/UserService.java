package com.yi.seckill.service;

import com.yi.seckill.common.BusinessException;
import com.yi.seckill.common.CommonService;
import com.yi.seckill.model.UserInfo;
import com.yi.seckill.model.UserModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户数据接口
 * @author YI
 * @date 2018-12-19 15:48:07
 */
public interface UserService extends CommonService<UserInfo> {
    /**
     * 根据id查找数据,包含连表的密码
     * @param id 用户id
     * @return
     */
    UserModel selectByPrimaryAllId(Integer id);

    /**
     * 查找所有用户数据
     * @return
     */
    List<UserInfo> selectAllUser();

    /**
     * 根据用户手机号码查找数据,包含连表的密码
     * @param telphone 用户手机号码
     * @return
     */
    UserModel selectByPrimaryAllTelPhone(String telphone);

    /**
     * 根据用户手机号码查找数据
     * @param telphone 用户手机号码
     * @return
     */
    UserInfo selectByTelPhone(String telphone);

    /**
     * 保存注册用户
     * @param userModel
     */
    void insertSelective(UserModel userModel);

    /**
     * 用户登录
     * @param telphone  用户手机号码
     * @param otpCode   短信验证码
     * @param password  密码
     * @param type      登录类型 1:账号密码登录、2:手机+验证码登录
     * @param httpServletRequest
     */
    UserInfo login(String telphone, String otpCode, String password, Integer type, HttpServletRequest httpServletRequest) throws BusinessException;
}
