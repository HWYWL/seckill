package com.yi.seckill.controller;

import com.yi.seckill.common.BusinessException;
import com.yi.seckill.common.EmBusinessError;
import com.yi.seckill.utils.MessageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 公共Controller
 * @author YI
 * @date 2018-12-18 15:06:24
 */
public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public MessageResult handlerException(HttpServletRequest request, Exception ex){
        HashMap<String, Object> map = new HashMap<>(16);

        if (ex instanceof BusinessException){
            BusinessException businessException = (BusinessException) ex;
            map.put("errCode", businessException.getErrCode());
            map.put("errMsg", businessException.getErrMsg());
            log.error(businessException.getErrMsg());
        }else {
            map.put("errCode", EmBusinessError.UNKONWN_ERROR.getErrCode());
            map.put("errMsg", EmBusinessError.UNKONWN_ERROR.getErrMsg());
            log.error(ex.getMessage());
        }

        return MessageResult.errorMap(map);
    }
}
