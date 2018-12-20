package com.yi.seckill.validator;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数校验
 * @author YI
 * @date 2018-12-20 10:40:30
 */
public class ValidationResult {
    /**
     * 检验结果是否有错
     */
    private boolean hasErrors = false;

    private Map<String, String> errorMsgMap = new HashMap<>();

    /**
     * 实现通用的通过格式化字符串信息获取错误的msg方法
     * @return
     */
    public String getErrMsg(){
        return StrUtil.join(",", errorMsgMap.values().toArray());
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }
}
