package com.yi.seckill.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.yi.seckill.common.BusinessException;
import com.yi.seckill.common.EmBusinessError;
import com.yi.seckill.common.EmLoginType;
import com.yi.seckill.dto.UserInfo;
import com.yi.seckill.model.UserModel;
import com.yi.seckill.service.UserService;
import com.yi.seckill.utils.MessageResult;
import com.yi.seckill.validator.ValidationResult;
import com.yi.seckill.validator.ValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户
 *
 * @author YI
 * @date 2018-12-18 10:46:02
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Resource
    ValidatorImpl validator;

    /**
     * 查找所有用户数据
     * @return
     */
    @RequestMapping(value = "selectAllUser", method = RequestMethod.GET)
    public MessageResult selectAllUser() {
        List<UserInfo> userInfos = userService.selectAllUser();

        return MessageResult.ok(userInfos);
    }

    /**
     * 获取用户otp短信接口
     *
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
     * 验证验证码是否正确
     *
     * @param telphone 手机号码
     * @param code     验证码
     * @return
     */
    @RequestMapping(value = "cpcode", method = RequestMethod.POST)
    public MessageResult cpcode(@RequestParam(name = "telphone") String telphone, @RequestParam(name = "code") String code) throws BusinessException {
        // 获取session中的验证码
        String inSessionOtpCode = (String) httpServletRequest.getSession().getAttribute(telphone);

        if (!StrUtil.equals(code, inSessionOtpCode)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "验证码错误！！！");
        }

        return MessageResult.ok();
    }

    /**
     * 图像验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "verifyCode", method = RequestMethod.GET)
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 定义图形验证码的长和宽
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(120, 37, 4, 0);

        captcha.write(response.getOutputStream());
    }

    /**
     * 用户注册接口
     *
     * @param telphone     手机号码
     * @param otpCode      短信验证码
     * @param password     密码
     * @param passwordcp   第二次输入密码
     * @param registerMode 注册模式
     * @param name         用户名
     * @param gender       用户性别 1:男、2:女
     * @param age          用户年龄
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public MessageResult register(@RequestParam(name = "telphone") String telphone,
                                  @RequestParam(name = "otpCode") String otpCode,
                                  @RequestParam(name = "password") String password,
                                  @RequestParam(name = "passwordcp") String passwordcp,
                                  @RequestParam(name = "registerMode") String registerMode,
                                  @RequestParam(name = "name") String name,
                                  @RequestParam(name = "gender") Integer gender,
                                  @RequestParam(name = "age") Integer age) throws BusinessException {

        UserModel userModel = new UserModel(name, gender.byteValue(), age, telphone, registerMode, password);

        // 参数校验
        ValidationResult validate = validator.validate(userModel);
        if (validate.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, validate.getErrMsg());
        }

        // 获取存取session中的otpCode
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);

        // 判断验证码是否相同
        if (!StrUtil.equals(inSessionOtpCode, otpCode)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "验证码错误！！！");
        }

        // 判断两次输入密码是否相同
        if (!StrUtil.equals(password, passwordcp)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "两次输入的密码不一致！！！");
        }

        // 判断两次输入密码是否相同
        if (StrUtil.isEmpty(name) || gender == null || age == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "参数不能为空！！！");
        }

        userService.insertSelective(userModel);

        return MessageResult.ok();
    }

    /**
     * 用户手机号码注册校验接口（是否已经注册）
     *
     * @param telphone 手机号码
     * @return
     */
    @RequestMapping(value = "checkPhone", method = RequestMethod.POST)
    public MessageResult checkPhone(@RequestParam(name = "telphone") String telphone) throws BusinessException {

        if (StrUtil.isEmpty(telphone)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        UserInfo userInfo = userService.selectByTelPhone(telphone);

        // 不为空表示用户已经存在
        if (userInfo != null) {
            throw new BusinessException(EmBusinessError.USER_IS_EXIST);
        }

        return MessageResult.ok();
    }

    /**
     * 用户登录接口
     *
     * @param telphone 手机号码
     * @param otpCode  验证码
     * @param password 密码
     * @param type     登录方式    1:账号密码登录、2:手机+验证码登录
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public MessageResult login(@RequestParam(name = "telphone") String telphone,
                               @RequestParam(name = "otpCode") String otpCode,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "type") Integer type) throws BusinessException {

        if (StrUtil.isEmpty(telphone) || type == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        if (type == EmLoginType.USERNAME_PHONE_PASSWORD.getTypeCode() && StrUtil.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "密码不能为空");
        }

        if (type == EmLoginType.PHONE_OTP.getTypeCode() && StrUtil.isEmpty(otpCode)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "验证码不能为空");
        }

        UserInfo userInfo = userService.login(telphone, otpCode, password, type, this.httpServletRequest);

        // 把登录状态放在session中
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userInfo);

        return MessageResult.ok();
    }
}
