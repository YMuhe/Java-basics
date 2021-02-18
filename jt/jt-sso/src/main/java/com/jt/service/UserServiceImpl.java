package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    private static Map<Integer,String> columnMap = new HashMap<>();
    static {
        columnMap.put(1, "username");
        columnMap.put(2, "phone");
        columnMap.put(3, "email");
    }

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAll() {

        return userMapper.selectList(null);
    }

    //如果数据库中存在 返回true   不存在false
    //如何判断 数据库中是否存在
    //type=1 username  type=2 phone  type=3 email
    @Override
    public boolean checkUser(String param, Integer type) {
        //String column = type==1?"username":(type==2?"phone":"email");
        String column = columnMap.get(type);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(column,param);
        int count = userMapper.selectCount(queryWrapper);//0|1
        //return count>0?true:false;
        //int a = 1/0;
        return count>0;
    }
}
