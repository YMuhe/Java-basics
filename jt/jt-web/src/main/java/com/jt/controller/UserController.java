package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 1.注册:http://www.jt.com/user/register.html   页面名称:register.html
     * 2.登录:http://www.jt.com/user/login.html      登录页面:login.html
     * 能否利用一个方法实现该功能呢?  restFul
     */
    @RequestMapping("/{moduleName}")
    public String module(@PathVariable String moduleName){

        return moduleName;
    }
}
