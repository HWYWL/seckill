package com.yi.seckill.service;

import com.yi.seckill.common.BusinessException;
import com.yi.seckill.common.CommonService;
import com.yi.seckill.model.OrderModel;

/**
 * 订单接口
 * @author YI
 * @date 2018-12-21 10:15:40
 */
public interface OrderService extends CommonService {
    /**
     * 创建订单
     * @param userId    用户id
     * @param itemId    订单id
     * @param amount    购买的商品数量
     * @return
     */
    OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException;
}
