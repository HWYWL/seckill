package com.yi.seckill.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 * @author YI
 * @date 2018-12-20 18:00:15
 */
public class OrderInfo  implements Serializable {

  /**
   * 订单号
   */
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private String id;

  /**
   * 用户id
   */
  private Integer userId;

  /**
   * 商品id
   */
  private Integer itemId;

  /**
   * 购买商品的数量
   */
  private Integer amount;

  /**
   * 购买商品单价
   */
  private BigDecimal itemPrice;

  /**
   * 购买金额
   */
  private BigDecimal orderPrice;

  /**
   * 订单创建时间
   */
  private Date crtTime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  public BigDecimal getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(BigDecimal itemPrice) {
    this.itemPrice = itemPrice;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public BigDecimal getOrderPrice() {
    return orderPrice;
  }

  public void setOrderPrice(BigDecimal orderPrice) {
    this.orderPrice = orderPrice;
  }

  public Date getCrtTime() {
    return crtTime;
  }

  public void setCrtTime(Date crtTime) {
    this.crtTime = crtTime;
  }
}
