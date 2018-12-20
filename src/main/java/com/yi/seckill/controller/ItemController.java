package com.yi.seckill.controller;

import com.yi.seckill.common.BusinessException;
import com.yi.seckill.model.ItemModel;
import com.yi.seckill.service.ItemService;
import com.yi.seckill.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品
 * @author YI
 * @date 2018-12-20 11:27:00
 */
@RestController
@RequestMapping("/item")
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class ItemController extends BaseController{
    @Autowired
    ItemService itemService;

    /**
     * 获取所有商品
     * @return
     */
    @RequestMapping("/selectAllItem")
    public MessageResult selectAllItem() {
        List<ItemModel> itemModels = itemService.listItem();

        return MessageResult.ok(itemModels);
    }

    /**
     * 创建商品
     * @param title         商品名称
     * @param price         商品价格
     * @param description   商品描述
     * @param stock         商品库存
     * @param imgUrl        商品描述图片URL
     * @return
     */
    @RequestMapping("/createItem")
    public MessageResult createItem(@RequestParam(name = "title")String title,
                                    @RequestParam(name = "price")BigDecimal price,
                                    @RequestParam(name = "description")String description,
                                    @RequestParam(name = "stock")Integer stock,
                                    @RequestParam(name = "imgUrl")String imgUrl) throws BusinessException {
        ItemModel itemModel = new ItemModel(title, price, stock, description, imgUrl);

        ItemModel item = itemService.createItem(itemModel);

        return MessageResult.ok(item);
    }
}
