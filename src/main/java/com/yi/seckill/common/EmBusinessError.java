package com.yi.seckill.common;

/**
 * 错误信息
 * @author YI
 * @date 2018-12-18 12:04:51
 */
public enum EmBusinessError implements CommonError {
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKONWN_ERROR(10002, "未知错误"),

    USER_NOT_EXIST(20001, "用户不存在"),
    USER_IS_EXIST(20002, "用户已经存在"),
    USER_LOGIN_PASSWORD_ERROR(20003, "用户名或密码错误"),
    USER_IS_NOT_LOGGED_IN(20004, "用户未登录"),

    OTP_CODE_NOT_EXIST(30001, "验证码不匹配"),
    ITEM_NOT_EXIST(40001, "商品不存在"),
    STOCK_OF_ITEM_IS_SHORT(40002, "商品库存不足");

    private int errCode;
    private String errMsg;

    EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
