package com.yi.seckill.dao;

import com.yi.seckill.dto.Promo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * 操作商品信息数据库
 * @author YI
 * @date 2018-12-20 10:25:42
 */
public interface PromoMapper extends Mapper<Promo> {
    /**
     * 根据id查找数据
     * @param id 主键id
     * @return
     */
    @Select("SELECT * FROM promo AS e1 where e1.id = #{id};")
    Promo selectByPrimaryId(Integer id);

    /**
     * 根据itemId获取秒杀活动
     * @param itemId 商品id
     * @return
     */
    @Select("SELECT * FROM promo AS e1 where e1.item_id = #{itemId};")
    Promo getPromoByItemId(Integer itemId);
}
