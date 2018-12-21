package com.yi.seckill.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.StrUtil;
import com.yi.seckill.common.BusinessException;
import com.yi.seckill.common.EmBusinessError;
import com.yi.seckill.dao.ItemStockMapper;
import com.yi.seckill.dao.OrderInfoMapper;
import com.yi.seckill.dao.SequenceInfoMapper;
import com.yi.seckill.dto.OrderInfo;
import com.yi.seckill.dto.SequenceInfo;
import com.yi.seckill.model.*;
import com.yi.seckill.service.ItemService;
import com.yi.seckill.service.OrderService;
import com.yi.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单接口实现
 * @author YI
 * @date 2018-12-21 10:17:48
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;
    @Autowired
    ItemStockMapper itemStockMapper;
    @Autowired
    SequenceInfoMapper sequenceInfoMapper;

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException {
        // 校验下单状态，下单商品是否存在，用户是否合法，购买数量是否正确
        ItemModel itemModel = itemService.selectByPrimaryAllId(itemId);
        if (itemModel == null){
            throw new BusinessException(EmBusinessError.ITEM_NOT_EXIST);
        }

        // 购买数量
        if (amount <= 0){
            throw new BusinessException(EmBusinessError.STOCK_OF_ITEM_IS_SHORT, "商品下单数量不正确");
        }

        // 库存不足
        if (itemModel.getStock() < amount){
            throw new BusinessException(EmBusinessError.STOCK_OF_ITEM_IS_SHORT);
        }

        // 用户不存在
        UserModel userModel = userService.selectByPrimaryAllId(userId);
        if (userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        // 落单减库存
        boolean decreaseStock = itemService.decreaseStock(itemId, amount);
        if (!decreaseStock){
            throw new BusinessException(EmBusinessError.STOCK_OF_ITEM_IS_SHORT);
        }

        // 订单入库
        OrderInfo orderInfo = new OrderInfo();
        OrderModel orderModel = new OrderModel(userId, itemId, amount, itemModel.getPrice(), itemModel.getPrice().multiply(new BigDecimal(amount)));
        BeanUtil.copyProperties(orderModel, orderInfo);

        orderInfo.setId(generateOrderNo());

        orderInfoMapper.insertSelective(orderInfo);

        // 商品销量怎家
        itemService.increaseSales(itemId, amount);

        return orderModel;
    }

    @Override
    public Object selectById(Integer id) {
        return orderInfoMapper.selectByPrimaryId(id);
    }

    /**
     * 生成16位订单号, 使用@Transactional中的REQUIRES_NEW事务方式，新开一个事务，保证全局id唯一性
     * @return 订单号
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BusinessException.class)
    String generateOrderNo(){
        StringBuilder stringBuilder = new StringBuilder();
        // 前八位为时间信息 年月日
        String nowDate = DatePattern.PURE_DATE_FORMAT.format(new Date());
        stringBuilder.append(nowDate);

        // 中间6位
        SequenceInfo sequenceInfo = sequenceInfoMapper.selectByPrimaryName("order_info");
        Integer sequence = sequenceInfo.getCurrentValue();

        sequenceInfoMapper.updateByPrimaryNameInCurrentValue("order_info", sequence + sequenceInfo.getStep());

        // 如果小于6位前面补0
        String sequenceStr = String.valueOf(sequence);
        if (sequenceStr.length() < 6){
            sequenceStr = StrUtil.padPre(sequenceStr, 6 -sequenceStr.length(), '0');
        }

        stringBuilder.append(sequenceStr);

        // 分库分表标识位
        stringBuilder.append("00");

        return stringBuilder.toString();
    }
}
