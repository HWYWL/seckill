package com.yi.seckill.dao;

import com.yi.seckill.dto.OrderInfo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * 操作订单信息数据库
 * @author YI
 * @date 2018-12-21 10:13:31
 */
public interface OrderInfoMapper extends Mapper<OrderInfo> {
    /**
     * 根据id查找数据
     * @param id 主键id
     * @return
     */
    @Select("SELECT * FROM order_info AS e1 where e1.id = #{id};")
    OrderInfo selectByPrimaryId(Integer id);
}