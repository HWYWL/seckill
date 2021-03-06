package com.yi.seckill.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yi.seckill.common.BusinessException;
import com.yi.seckill.common.EmBusinessError;
import com.yi.seckill.dao.ItemMapper;
import com.yi.seckill.dao.ItemStockMapper;
import com.yi.seckill.dao.PromoMapper;
import com.yi.seckill.dto.Item;
import com.yi.seckill.dto.Promo;
import com.yi.seckill.model.ItemModel;
import com.yi.seckill.dto.ItemStock;
import com.yi.seckill.model.PromoModel;
import com.yi.seckill.service.ItemService;
import com.yi.seckill.service.PromoService;
import com.yi.seckill.validator.ValidationResult;
import com.yi.seckill.validator.ValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品接口实现
 * @author YI
 * @date 2018-12-20 10:33:18
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ValidatorImpl validator;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    ItemStockMapper itemStockMapper;
    @Autowired
    PromoService promoService;

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        // 参数校验
        ValidationResult validate = validator.validate(itemModel);
        if (validate.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, validate.getErrMsg());
        }

        Item item = new Item();
        ItemStock itemStock = new ItemStock();

        BeanUtil.copyProperties(itemModel, item);
        BeanUtil.copyProperties(itemModel, itemStock);

        // 入库
        itemMapper.insertSelective(item);
        itemStock.setItemId(item.getId());
        itemStockMapper.insertSelective(itemStock);

        return this.selectByPrimaryAllId(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        return itemMapper.selectByPrimaryAll();
    }

    @Override
    public ItemModel selectByPrimaryAllId(Integer id) {
        ItemModel itemModel = itemMapper.selectByPrimaryAllId(id);
        PromoModel promoModel = promoService.getPromoByItemId(id);

        // 判断是否有秒杀活动
        if (promoModel != null && promoModel.getStatus() != -1){
            itemModel.setPromoModel(promoModel);
        }

        return itemModel;
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public boolean decreaseStock(Integer itemId, Integer amount) {
        int affectedRow = itemStockMapper.decreaseStock(itemId, amount);

        return affectedRow > 0;
    }

    @Override
    public void increaseSales(Integer itemId, Integer ammount) {
        itemMapper.increaseSales(itemId, ammount);
    }

    @Override
    public Item selectById(Integer id) {
        return itemMapper.selectByPrimaryId(id);
    }
}
