package com.yi.seckill.common;

/**
 * 包装设计模式，实现通用异常
 * @author YI
 * @date 2018-12-18 14:39:40
 */
public class BusinessException extends Exception implements CommonError{
    private CommonError commonError;

    /**
     * 直接接收EmBusinessError传参，用于构造业务异常
     * @param commonError
     */
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    public BusinessException(CommonError commonError, String errMsg) {
        super(errMsg);
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);

        return this;
    }
}
