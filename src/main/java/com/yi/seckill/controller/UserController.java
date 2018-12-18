package com.yi.seckill.controller;

import com.yi.seckill.common.BusinessException;
import com.yi.seckill.common.EmBusinessError;
import com.yi.seckill.model.UserInfo;
import com.yi.seckill.model.UserModel;
import com.yi.seckill.service.UserService;
import com.yi.seckill.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 * @author YI
 * @date 2018-12-18 10:46:02
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    UserService userService;

    /**
     * 获取用户基本信息
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "userInfo", method = RequestMethod.POST)
    public MessageResult userInfo(Integer id) throws Exception {
        UserInfo userInfo = userService.selectById(id);

        if (userInfo == null){
            throw new Exception("用户不存在");
        }

        return MessageResult.ok(userInfo);
    }

    /**
     * 获取用户基本信息，包含密码
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "userModel", method = RequestMethod.POST)
    public MessageResult userModel(Integer id) throws BusinessException {
        UserModel userModel = userService.selectByPrimaryAllId(id);

        if (userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        return MessageResult.ok(userModel);
    }
}
