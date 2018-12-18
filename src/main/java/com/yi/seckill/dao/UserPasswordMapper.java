package com.yi.seckill.dao;

import com.yi.seckill.model.UserPassword;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * 操作用户密码数据库
 * @author YI
 * @date 2018-12-16 10:47:45
 */
public interface UserPasswordMapper extends Mapper<UserPassword> {
    /**
     * 根据id查找数据
     * @param id 主键id
     * @return
     */
    @Select("SELECT * FROM `user_password` WHERE id = #{id}")
    UserPassword selectByPrimaryId(Integer id);
}