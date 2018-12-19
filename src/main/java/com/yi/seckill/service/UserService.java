package com.yi.seckill.service;

import com.yi.seckill.common.CommonService;
import com.yi.seckill.model.UserInfo;
import com.yi.seckill.model.UserModel;

public interface UserService extends CommonService<UserInfo> {
    /**
     * 根据id查找数据,包含连表的密码
     * @param id 用户id
     * @return
     */
    UserModel selectByPrimaryAllId(Integer id);

    /**
     * 根据用户手机号码查找数据
     * @param telphone 用户手机号码
     * @return
     */
    UserInfo selectByTelPhone(String telphone);
}
