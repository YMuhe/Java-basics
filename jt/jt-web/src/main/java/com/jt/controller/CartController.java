package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.service.DubboCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 业务说明:  实现用户购物车数据新增
     * url: http://www.jt.com/cart/add/1474391990.html
     * 参数: 购物车表单提交  Cart对象
     * 返回值: 重定向到跳转到购物车展现页面
     *
     * 扩展内容: 如果restFul的参数,与对象的属性名称一致,则可以直接赋值
     */
    @RequestMapping("/add/{itemId}")
    public String addCart(Cart cart){
        long userId = 7;
        cart.setUserId(userId);
        cartService.addCart(cart);
        return "redirect:/cart/show.html";
    }










}
