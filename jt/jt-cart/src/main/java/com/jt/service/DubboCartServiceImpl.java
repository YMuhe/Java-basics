package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service(timeout = 3000)
public class DubboCartServiceImpl implements DubboCartService{

    @Autowired
    private CartMapper cartMapper;


}
