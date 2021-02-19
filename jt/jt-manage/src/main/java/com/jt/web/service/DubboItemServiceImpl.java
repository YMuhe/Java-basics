package com.jt.web.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.DubboItemService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(timeout = 3000)
public class DubboItemServiceImpl implements DubboItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;




    //查询商品信息
    @Override
    public Item findItemById(Long itemId) {
        System.out.println("测试代码!!!!");
        return itemMapper.selectById(itemId);
    }

    //查询商品详情信息
    @Override
    public ItemDesc findItemDescById(Long itemId) {

        return itemDescMapper.selectById(itemId);
    }
}
