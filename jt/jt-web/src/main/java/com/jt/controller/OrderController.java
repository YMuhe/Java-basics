package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.Order;
import com.jt.service.DubboCartService;
import com.jt.service.DubboOrderService;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Reference
    private DubboCartService cartService;
    @Reference
    private DubboOrderService orderService;

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

    /**
     * 实现订单入库操作
     * url分析:   http://www.jt.com/order/submit
     * 参数:      整个form表单 Order对象
     * 返回值:    SysResult对象
     * 页面取值:  要求返回orderId
     */
    @RequestMapping("/submit")
    @ResponseBody
    public SysResult submit(Order order){
        long userId = UserThreadLocal.get().getId();
        order.setUserId(userId);
        String orderId = orderService.saveOrder(order);
        //完成订单之后,应该删除购物车.. 分布式事务....
        //cartService.deleteCart();
        return SysResult.success(orderId);
    }




}
