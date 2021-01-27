package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘昱江
 * 时间 2021/1/27
 */
@RestController
public class PortController {

    //动态获取当前端口号信息
    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/port")
    public String getPort(){

        return "当前端口号:"+port;
    }

}
