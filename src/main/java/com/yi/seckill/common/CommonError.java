package com.yi.seckill.common;

/**
 * 错误接口
 * @author YI
 * @date 2018-12-18 14:26:39
 */
public interface CommonError {
    /**
     * 获取错误码
     * @return
     */
    int getErrCode();

    /**
     * 获取错误信息
     * @return
     */
    String getErrMsg();

    /**
     * 设置错误信息
     * @param errMsg 错误信息
     * @return
     */
    CommonError setErrMsg(String errMsg);
}
