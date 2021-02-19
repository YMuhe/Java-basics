package com.jt.web.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.service.DubboItemService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(timeout = 3000)
public class DubboItemServiceImpl implements DubboItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;



}
