package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//程序运行时 加载指定的指定的配置文件
@PropertySource(value = "classpath:/properties/user.properties",encoding = "UTF-8")
public class UserController {
    /**
     * 利用properties方式为属性赋值
     */
    @Value("${user.id}")
    private Integer id;
    @Value("${user.username}")
    private String name;

    @RequestMapping("/getMsg")
    public String getMsg(){

        return id + ":" + name;
    }
}
