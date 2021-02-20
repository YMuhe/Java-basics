package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 3000)
public class DubboCartServiceImpl implements DubboCartService{

    @Autowired
    private CartMapper cartMapper;


    @Override
    public List<Cart> findCartListByUserId(long userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        return cartMapper.selectList(queryWrapper);
    }
}
