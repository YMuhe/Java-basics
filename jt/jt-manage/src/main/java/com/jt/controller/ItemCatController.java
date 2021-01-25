package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 业务: 实现商品分类的查询
     * URL地址: http://localhost:8091/itemCat/list?id=xxx
     * 请求参数: 传递节点的ID
     * 返回值:  List<EasyUITree>对象   页面JS-VO~~~~POJO--DB
     */
    @RequestMapping("/list")
    public List<EasyUITree> findItemCatList(Long id){
        //1.查询一级商品分类信息
        Long parentId = (id==null?0L:id);
        return itemCatService.findItemCatList(parentId);
    }


}
