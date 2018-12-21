package com.yi.seckill.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 库存
 * @author YI
 * @date 2018-12-20 10:22:10
 */
public class ItemStock implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  /**
   * 库存
   */
  private Integer stock;
  /**
   * 商品id
   */
  private Integer itemId;

  public ItemStock() {
    super();
  }

  public ItemStock(Integer stock, Integer itemId) {
    this.stock = stock;
    this.itemId = itemId;
  }

  public ItemStock(Integer id, Integer stock, Integer itemId) {
    this.id = id;
    this.stock = stock;
    this.itemId = itemId;
  }



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
