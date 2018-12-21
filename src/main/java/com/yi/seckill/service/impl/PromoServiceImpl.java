package com.yi.seckill.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.yi.seckill.dao.PromoMapper;
import com.yi.seckill.dto.Promo;
import com.yi.seckill.model.PromoModel;
import com.yi.seckill.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

        if (promo == null || promoModel == null){
            return null;
        }

        // 判断秒杀状态
        DateTime date = DateUtil.date(new Date());
        if (DateUtil.date(promoModel.getStartData()).isAfter(date)){
            promoModel.setStatus(0);
        }else if (DateUtil.date(promoModel.getStartData()).isBefore(date)){
            promoModel.setStatus(-1);
        }else {
            promoModel.setStatus(1);
        }

        return promoModel;
    }
}
