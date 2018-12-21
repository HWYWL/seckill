package com.yi.seckill.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀模型
 * @author YI
 * @date 2018-12-21 17:24:08
 */
public class PromoModel {
    private Integer id;

    /**
     * 秒杀活动状态，-1：已经结束、0:未开始、1:进行中
     */
    private Integer status;

    /**
     * 秒杀名称
     */
    private String promoName;

    /**
     * 秒杀开始时间
     */
    private Date startData;

    /**
     * 秒杀结束时间
     */
    private Date endData;

    /**
     * 商品id
     */
    private Integer itemId;

    /**
     * 秒杀活动的商品价格
     */
    private BigDecimal promoItemPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public Date getStartData() {
        return startData;
    }

    public void setStartData(Date startData) {
        this.startData = startData;
    }

    public Date getEndData() {
        return endData;
    }

    public void setEndData(Date endData) {
        this.endData = endData;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(BigDecimal promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }
}
