package com.yi.seckill.controller;

import cn.hutool.core.util.RandomUtil;
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

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    HttpServletRequest httpServletRequest;

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

    /**
     * 获取用户otp短信接口
     * @param telphone 手机号码
     * @return
     */
    @RequestMapping(value = "getotp", method = RequestMethod.POST)
    public MessageResult getotp(String telphone) {
        // 随机验证码
        String otpCode = RandomUtil.randomNumbers(6);

        // 因为没使用redis，这里采用httpsession的方式绑定手机号码和otpcode
        httpServletRequest.getSession().setAttribute(telphone, otpCode);

        // 将otpCode发送给用户
        System.out.println("telphone:" + telphone + " =====> " + "optCode:" + otpCode);

        return MessageResult.ok();
    }

    /**
     * 获取用户otp短信接口
     * @param telphone 手机号码
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public MessageResult register(String telphone) {
        // 随机验证码
        String otpCode = RandomUtil.randomNumbers(6);

        // 因为没使用redis，这里采用httpsession的方式绑定手机号码和otpcode
        httpServletRequest.getSession().setAttribute(telphone, otpCode);

        // 将otpCode发送给用户
        System.out.println("telphone:" + telphone + " =====> " + "optCode:" + otpCode);

        return MessageResult.ok();
    }
}
