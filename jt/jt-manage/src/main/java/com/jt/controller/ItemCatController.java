package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 实现商品分类查询
     * url地址:  http://localhost:8091/itemCat/findItemCatById?id=163
     * 参数:     id=163
     * 返回值结果: itemCat对象
     */
    @RequestMapping("/findItemCatById")
    public ItemCat findItemCatById(Long id){

        return itemCatService.findItemCatById(id);
    }


}
