package com.yi.seckill.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀
 * @author YI
 * @date 2018-12-21 17:22:52
 */
public class Promo implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

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

  public String getPromoName() {
    return promoName;
  }

  public void setPromoName(String promoName) {
    this.promoName = promoName;
  }

  public BigDecimal getPromoItemPrice() {
    return promoItemPrice;
  }

  public void setPromoItemPrice(BigDecimal promoItemPrice) {
    this.promoItemPrice = promoItemPrice;
  }

  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
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
}
