package com.yi.seckill.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yi.seckill.dao.PromoMapper;
import com.yi.seckill.dto.Promo;
import com.yi.seckill.model.PromoModel;
import com.yi.seckill.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 秒杀
 * @author YI
 * @date 2018-12-21 17:15:22
 */
@Service
public class PromoServiceImpl implements PromoService {
    @Autowired
    PromoMapper promoMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        Promo promo = promoMapper.getPromoByItemId(itemId);

        PromoModel promoModel = new PromoModel();
        BeanUtil.copyProperties(promo, promoModel);

        return promoModel;
    }
}
