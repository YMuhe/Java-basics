package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.service.UserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Reference(check = false)
    private DubboUserService dubboUserService;

    /**
     * 1.注册:http://www.jt.com/user/register.html   页面名称:register.html
     * 2.登录:http://www.jt.com/user/login.html      登录页面:login.html
     * 能否利用一个方法实现该功能呢?  restFul
     */
    @RequestMapping("/{moduleName}")
    public String module(@PathVariable String moduleName){

        return moduleName;
    }

    /**
     * httpClient代码测试
     * http://www.jt.com/user/findAll
     */
     @Autowired
     private UserService userService;

     @RequestMapping("/findAll")
     @ResponseBody
     public List<User> findAll(){

        return userService.findAll();
     }

    /**
     * 实现用户信息注册
     * url:http://www.jt.com/user/doRegister
     * 参数: 用户名/密码/电话
     * 返回值: SysResult对象  JSON
     *
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult doRegister(User user){

        dubboUserService.saveUser(user);
        return SysResult.success();
    }








}
