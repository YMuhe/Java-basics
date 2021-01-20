package com.jt.demo.controller;

import com.jt.demo.pojo.User;
import com.jt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll(){

        return userService.findAll();
    }

    /**
     * 实现页面跳转
     * url:http://localhost:8090/userList  userList.jsp
     * 页面取值: ${userList}
     * 如何将数据赋值给域对象????
     */
    @RequestMapping("/userList")
    //@ResponseBody //1.将返回值结果转化为JSON  2.表示程序结束  3.不会执行视图解析器配置
    public String userList(Model model){

        List<User> userList = userService.findAll();
        //利用model将数据保存到request域
        model.addAttribute("userList",userList);
        return "userList";
    }





}
