package com.yi.seckill.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * @author YI
 * @date 2018-12-20 10:21:45
 */
public class Item implements Serializable {

  /**
   * 主键id
   */
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  /**
   * 商品名称
   */
  private String title;
  /**
   * 商品价格
   */
  private BigDecimal price;
  /**
   * 商品描述
   */
  private String description;
  /**
   * 商品销量
   */
  private Integer sales;
  /**
   * 商品描述图片URL
   */
  private String imgUrl;
  /**
   * 商品创建时间
   */
  private Date crtTime;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getSales() {
    return sales;
  }

  public void setSales(Integer sales) {
    this.sales = sales;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public Date getCrtTime() {
    return crtTime;
  }

  public void setCrtTime(Date crtTime) {
    this.crtTime = crtTime;
  }
}
