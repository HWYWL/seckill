package com.yi.seckill.dao;

import com.yi.seckill.dto.UserInfo;
import com.yi.seckill.model.UserModel;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * 操作用户信息数据库
 * @author YI
 * @date 2018-12-16 10:47:45
 */
public interface UserInfoMapper extends Mapper<UserInfo> {
    /**
     * 根据id查找数据,包含连表的密码
     * @param id 主键id
     * @return
     */
    @Select("SELECT e1.*,e2.encrpt_password FROM user_info AS e1 INNER JOIN user_password AS e2 ON e1.id = #{id} and e1.id = e2.user_id;")
    UserModel selectByPrimaryAllId(Integer id);

    /**
     * 根据id查找数据
     * @param id 主键id
     * @return
     */
    @Select("SELECT * FROM user_info AS e1 where e1.id = #{id};")
    UserInfo selectByPrimaryId(Integer id);

    /**
     * 根据用户手机号码查找数据,包含连表的密码
     * @param telphone 用户手机号码
     * @return
     */
    @Select("SELECT e1.*,e2.encrpt_password FROM user_info AS e1 INNER JOIN user_password AS e2 ON e1.telphone = #{telphone} and e1.id = e2.user_id;")
    UserModel selectByPrimaryAllTelPhone(String telphone);
}