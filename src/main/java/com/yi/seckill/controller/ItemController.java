package com.yi.seckill.controller;

import com.yi.seckill.common.BusinessException;
import com.yi.seckill.common.EmBusinessError;
import com.yi.seckill.model.ItemModel;
import com.yi.seckill.model.UserInfo;
import com.yi.seckill.service.ItemService;
import com.yi.seckill.service.OrderService;
import com.yi.seckill.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

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
     * 根据id查询商品商品
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public MessageResult detail(@RequestParam(name = "id")Integer id) {
        ItemModel itemModel = itemService.selectByPrimaryAllId(id);

        return MessageResult.ok(itemModel);
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

    /**
     * 购买商品
     * @param itemId    商品id
     * @param amount  购买的商品数量
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public MessageResult buy(@RequestParam(name = "itemId")Integer itemId,
                             @RequestParam(name = "amount")Integer amount) throws BusinessException {
        MessageResult result = MessageResult.ok();
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin){
            throw new BusinessException(EmBusinessError.USER_IS_NOT_LOGGED_IN);
        }

        UserInfo userInfo = (UserInfo) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        orderService.createOrder(userInfo.getId(), itemId, amount);

        result.setMsg("商品购买成功");

        return result;
    }
}
