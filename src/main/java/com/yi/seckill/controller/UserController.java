package com.yi.seckill.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.yi.seckill.common.BusinessException;
import com.yi.seckill.common.EmBusinessError;
import com.yi.seckill.common.EmLoginType;
import com.yi.seckill.model.UserInfo;
import com.yi.seckill.model.UserModel;
import com.yi.seckill.service.UserService;
import com.yi.seckill.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
     * 验证码
     * @return
     */
    @RequestMapping(value = "verifyCode", method = RequestMethod.GET)
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 定义图形验证码的长和宽
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(120, 37, 4, 0);

        captcha.write(response.getOutputStream());
    }

    /**
     * 用户注册接口
     * @param telphone 手机号码
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public MessageResult register(@RequestParam(name = "telphone") String telphone,
                                  @RequestParam(name = "otpCode") String otpCode,
                                  @RequestParam(name = "name") String name,
                                  @RequestParam(name = "gender") Integer gender,
                                  @RequestParam(name = "age") Integer age) throws BusinessException {

        // 获取存取session中的otpCode
        String inSessionOtpCode = (String)this.httpServletRequest.getSession().getAttribute("telphone");

        // 判断验证码是否相同
        if (!StrUtil.equals(inSessionOtpCode, otpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "验证码错误！！！");
        }

        return MessageResult.ok();
    }

    /**
     * 用户手机号码注册校验接口（是否已经注册）
     * @param telphone 手机号码
     * @return
     */
    @RequestMapping(value = "checkPhone", method = RequestMethod.POST)
    public MessageResult checkPhone(@RequestParam(name = "telphone") String telphone) throws BusinessException {

        if (StrUtil.isEmpty(telphone)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        UserInfo userInfo = userService.selectByTelPhone(telphone);

        // 不为空表示用户已经存在
        if (userInfo != null){
            throw new BusinessException(EmBusinessError.USER_IS_EXIST);
        }

        return MessageResult.ok();
    }

    /**
     * 用户登录接口
     * @param telphone 手机号码
     * @param otpCode   验证码
     * @param password  密码
     * @param type  登录方式    1:账号密码登录、2:手机+验证码登录
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public MessageResult login(@RequestParam(name = "telphone") String telphone,
                                  @RequestParam(name = "otpCode") String otpCode,
                                  @RequestParam(name = "password") String password,
                                  @RequestParam(name = "type") Integer type) {

    // 手机号或者用户名登录
    if (type != null && type.intValue() == EmLoginType.USERNAME_PHONE_PASSWORD.getTypeCode()){

    }else if (type != null && type.intValue() == EmLoginType.PHONE_OTP.getTypeCode()){
        // 验证码登录
    }

        return MessageResult.ok();
    }
}
