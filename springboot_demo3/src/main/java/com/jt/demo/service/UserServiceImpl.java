package com.jt.demo.service;

import com.jt.demo.mapper.UserMapper;
import com.jt.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        //查询全部用户信息
        return userMapper.selectList(null);
    }
}
