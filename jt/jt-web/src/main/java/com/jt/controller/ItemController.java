package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.DubboItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller     //页面跳转.......
public class ItemController {

    //先启动服务消费者 暂时不校验是否有提供者
    @Reference(check = false)
    private DubboItemService itemService;

    /**
     *
     * 业务需求: 根据商品id号查询商品/商品详情信息
     * url地址:  http://www.jt.com/items/562379.html
     * 参数:     restFul风格
     * 返回值:   跳转页面item.jsp
     * 页面取值:  Item/ItemDesc对象
     */
    @RequestMapping("/items/{itemId}")
    public String findItemById(@PathVariable Long itemId, Model model){

        Item item = itemService.findItemById(itemId);
        ItemDesc itemDesc = itemService.findItemDescById(itemId);
        //需要将数据保存到域对象中
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", itemDesc);
        return "item";
    }






}
