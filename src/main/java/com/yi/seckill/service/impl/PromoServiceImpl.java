package com.yi.seckill.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.yi.seckill.dao.PromoMapper;
import com.yi.seckill.dto.Promo;
import com.yi.seckill.model.PromoModel;
import com.yi.seckill.service.PromoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 秒杀
 * @author YI
 * @date 2018-12-21 17:15:22
 */
@Service
public class PromoServiceImpl implements PromoService {
    @Resource
    private PromoMapper promoMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        Promo promo = promoMapper.getPromoByItemId(itemId);

        PromoModel promoModel = new PromoModel();
        BeanUtil.copyProperties(promo, promoModel);

        if (promo == null || promoModel == null){
            return null;
        }

        // 秒杀活动状态，-1：已经结束、0:未开始、1:进行中
        Date date = new Date();
        if (DateUtil.date(date).isBefore(promoModel.getStartData())){
            promoModel.setStatus(0);
        }else if (DateUtil.date(date).isAfter(promoModel.getEndData())){
            promoModel.setStatus(-1);
        }else {
            promoModel.setStatus(1);
        }

        return promoModel;
    }
}
