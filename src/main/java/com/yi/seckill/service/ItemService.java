package com.yi.seckill.service;

import com.yi.seckill.common.BusinessException;
import com.yi.seckill.common.CommonService;
import com.yi.seckill.dto.Item;
import com.yi.seckill.model.ItemModel;

import java.util.List;

/**
 * 商品接口
 * @author YI
 * @date 2018-12-20 10:29:31
 */
public interface ItemService extends CommonService<Item> {
    /**
     * 创建商品
     * @param itemModel 商品模型
     * @return 商品模型
     */
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    /**
     * 商品列表浏览
     */
    List<ItemModel> listItem();

    /**
     * 商品详情浏览
     * @param id 商品id
     * @return 商品模型
     */
    ItemModel selectByPrimaryAllId(Integer id);

    /**
     * 库存扣减
     * @param itemId    商品id
     * @param amount    扣减的库存商量
     * @return true为成功，false为失败
     */
    boolean decreaseStock(Integer itemId, Integer amount);

    /**
     * 商品销量增加
     * @param itemId  商品id
     * @param ammount 销量
     */
    void increaseSales(Integer itemId, Integer ammount);
}
