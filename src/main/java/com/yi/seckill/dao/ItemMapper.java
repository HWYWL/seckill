package com.yi.seckill.dao;

import com.yi.seckill.dto.Item;
import com.yi.seckill.model.ItemModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 操作商品信息数据库
 * @author YI
 * @date 2018-12-20 10:25:42
 */
public interface ItemMapper extends Mapper<Item> {
    /**
     * 根据id查找数据
     * @param id 主键id
     * @return
     */
    @Select("SELECT * FROM item AS e1 where e1.id = #{id};")
    Item selectByPrimaryId(Integer id);

    /**
     * 根据id查找数据,包含连表的密码
     * @param id 主键id
     * @return
     */
    @Select("SELECT e1.*,e2.stock FROM item AS e1 INNER JOIN item_stock AS e2 ON e1.id = #{id} and e1.id = e2.item_id;")
    ItemModel selectByPrimaryAllId(Integer id);

    /**
     * 获取所有商品
     * @return
     */
    @Select("SELECT e1.*,e2.stock FROM item AS e1 INNER JOIN item_stock AS e2 ON e1.id = e2.item_id;")
    List<ItemModel> selectByPrimaryAll();

    /**
     * 商品销量增加
     * @param itemId  商品id
     * @param ammount 销量
     */
    @Update("UPDATE item SET sales = sales + #{ammount} WHERE ( ( id = #{itemId} ) )")
    void increaseSales(@Param("itemId")Integer itemId, @Param("ammount")Integer ammount);
}
