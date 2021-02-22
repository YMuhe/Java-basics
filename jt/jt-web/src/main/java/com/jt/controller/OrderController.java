package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.service.DubboCartService;
import com.jt.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Reference
    private DubboCartService cartService;

    /**
     * 业务说明:
     *      当点击按钮时,跳转到订单确认页面  cartList购物车数据
     * URL地址：http://www.jt.com/order/create.html
     * 参数:  userId
     * 返回值: 页面逻辑名称 order-cart
     * 页面取值: ${carts}
     */
    @RequestMapping("/create")
    public String orderCreate(Model model){

        long userId = UserThreadLocal.get().getId();
        List<Cart> cartList = cartService.findCartListByUserId(userId);
        model.addAttribute("carts",cartList);
        return "order-cart";
    }





}
