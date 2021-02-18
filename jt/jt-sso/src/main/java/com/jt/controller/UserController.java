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
     * 完成用户数据的校验 JT-SSO项目
     * url地址: http://sso.jt.com/user/check/{param}/{type}
     * 参数:    param 校验数据/type 校验类型/callback 回调函数
     * 返回值:  SysResult VO对象     data:true数据存在/false 数据不存在可以使用
     * 提示:  该请求是JSONP请求方式,,所以需要特定的格式封装...
     */







    /**
     * 测试: 要求查询user表中的所有的数据
     */
    @RequestMapping("/findAll")
    public List<User> findAll(){

        return userService.findAll();
    }
}
