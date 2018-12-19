package com.yi.seckill.common;

/**
 * 登录方式
 * @author YI
 * @date 2018-12-19 11:05:33
 */
public enum EmLoginType {
    USERNAME_PHONE_PASSWORD(1, "账号密码登录"),
    PHONE_OTP(2, "手机+验证码登录");

    private int typeCode;
    private String loginMsg;

    EmLoginType(int typeCode, String loginMsg) {
        this.typeCode = typeCode;
        this.loginMsg = loginMsg;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public String getLoginMsg() {
        return loginMsg;
    }
}
