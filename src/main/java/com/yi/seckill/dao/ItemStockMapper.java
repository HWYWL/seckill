package com.yi.seckill.dao;

import com.yi.seckill.model.ItemStock;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * 操作商品库存信息数据库
 * @author YI
 * @date 2018-12-20 10:25:42
 */
public interface ItemStockMapper extends Mapper<ItemStock> {
    /**
     * 根据id查找数据
     * @param id 主键id
     * @return
     */
    @Select("SELECT * FROM item_stock AS e1 where e1.id = #{id};")
    ItemStock selectByPrimaryId(Integer id);
}
