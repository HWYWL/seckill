package com.yi.seckill.service;

import com.yi.seckill.model.PromoModel;

/**
 * 秒杀接口
 * @author YI
 * @date 2018-12-21 17:15:22
 */
public interface PromoService {
    /**
     * 获取秒杀商品
     * @param itemId    秒伤商品id
     * @return
     */
    PromoModel getPromoByItemId(Integer itemId);
}
