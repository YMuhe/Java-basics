package com.jt.demo.test;

import com.jt.demo.mapper.UserMapper;
import com.jt.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMybatis {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll(){

        List<User> userList = userMapper.findAll();
        System.out.println(userList);
    }
}
