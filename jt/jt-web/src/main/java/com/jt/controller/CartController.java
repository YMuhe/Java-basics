package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.service.DubboCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Reference(check = false)
    private DubboCartService cartService;

    /**
     * 展现购物车列表信息  根据userId查询购物车记录
     * url: http://www.jt.com/cart/show.html
     * 参数: 根据userId查询购物车数据信息
     * 返回值: 购物车展现页面
     * 页面取值方式: ${cartList}
     */
    @RequestMapping("/show")
    public String show(Model model){
        long userId = 7L;   //暂时写死  后期维护
        List<Cart> cartList = cartService.findCartListByUserId(userId);
        //利用model对象将数据保存到request对象中
        model.addAttribute("cartList",cartList);
        return "cart";
    }

}
