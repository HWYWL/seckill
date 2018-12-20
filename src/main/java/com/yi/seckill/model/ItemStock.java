package com.yi.seckill.model;

import java.io.Serializable;

/**
 * 库存
 * @author YI
 * @date 2018-12-20 10:22:10
 */
public class ItemStock implements Serializable {

  private Integer id;
  /**
   * 库存
   */
  private Integer stock;
  /**
   * 商品id
   */
  private Integer itemId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }
}
