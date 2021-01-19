package com.jt.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    /*
    * 完成用户信息入库
    */
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("郑爽").setAge(30).setSex("女");
        //单表sql语句几乎不写
        userMapper.insert(user);
    }

    /**
     * 查询案例1: 查询Id=4 的用户
     * Id代表主机的含义
     */
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(4);
        System.out.println(user);
    }

    /**
     * 查询案例2: 查询name="八戒"的人
     * Sql: select * from user where name="八戒"
     * QueryWrapper<> 条件构造器 用来拼接where条件
     * 常见逻辑运算符:   = eq, > gt , < lt
     *                  >= ge, <= le
     */
    @Test
    public void testSelectByName(){
       /*
        该方法只能做=号的判断
        User user = new User();
        user.setName("八戒");
        //根据对象中不为null的属性拼接where条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);*/

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "小乔");
        System.out.println(userMapper.selectList(queryWrapper));
    }





}
