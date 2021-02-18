package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//要求返回的数据都是JSON
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 测试: 要求查询user表中的所有的数据
     */
    @RequestMapping("/findAll")
    public List<User> findAll(){

        return userService.findAll();
    }
}
