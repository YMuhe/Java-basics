package com.jt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/getMsg")
    public String getMsg(){

        return "您好springBoot";
    }
}
