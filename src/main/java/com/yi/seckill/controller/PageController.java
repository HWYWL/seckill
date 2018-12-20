package com.yi.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面
 * @author YI
 * @date 2018-12-18 16:49:01
 */
@Controller
public class PageController {

    /**
     * 主页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 找回密码
     * @return
     */
    @RequestMapping("page/getpass")
    public String getpass(){
        return "page/getpass";
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping("page/reg")
    public String reg(){
        return "page/reg";
    }

    /**
     * 添加商品页面
     * @return
     */
    @RequestMapping("/addItemPage")
    public String addItemPage(){
        return "page/addItem";
    }
}
