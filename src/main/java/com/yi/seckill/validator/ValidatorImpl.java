package com.yi.seckill.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 校验实现逻辑
 * @author YI
 * @date 2018-12-20 10:46:39
 */
@Component
public class ValidatorImpl implements InitializingBean {
    private Validator validator;

    /**
     * 实现检验方法，返回检验结果
     * @throws Exception
     */
    public ValidationResult validate(Object bean){
        ValidationResult result = new ValidationResult();

        // 有异常的时候返回大于0
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        if (constraintViolationSet.size() > 0){
            // 有异常错误
            result.setHasErrors(true);

            // 遍历异常
            constraintViolationSet.forEach( e ->{
                // 错误信息
                String errMsg = e.getMessage();
                // 发生错误bean中的方法
                String propertyName = e.getPropertyPath().toString();

                result.getErrorMsgMap().put(propertyName, errMsg);
            });
        }

        return result;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        // 将hibernate validator通过工厂的初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
