package com.yi.seckill.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品模型
 * @author YI
 * @date 2018-12-20 10:01:49
 */
public class ItemModel implements Serializable {
    /**
     * 商品id
     */
    private Integer id;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String title;

    /**
     * 商品价格
     */
    @NotNull(message = "商品价格不能不填")
    @Min(value = 0, message = "商品价格必须大于0")
    private BigDecimal price;

    /**
     * 商品库存
     */
    @NotNull(message = "商品库存不能不填")
    private Integer stock;

    /**
     * 商品描述
     */
    @NotNull(message = "商品描述不能不填")
    private String description;

    /**
     * 商品销量
     */
    private Integer sales;

    /**
     * 商品描述图片URL
     */
    @NotNull(message = "商品描述图片信息不能不填")
    private String imgUrl;

    /**
     * 使用聚合模型，如果PromoModel不为空，表示还有秒杀活动
     */
    private PromoModel promoModel;

    public ItemModel() {
        super();
    }

    public ItemModel(String title, BigDecimal price, Integer stock, String description, String imgUrl) {
        this.title = title;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public ItemModel(String title, BigDecimal price, Integer stock, String description, Integer sales, String imgUrl) {
        this.title = title;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.sales = sales;
        this.imgUrl = imgUrl;
    }

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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", sales=" + sales +
                ", imgUrl='" + imgUrl + '\'' +
                ", promoModel=" + promoModel +
                '}';
    }
}
