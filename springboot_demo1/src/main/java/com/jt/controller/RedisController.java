package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    //spel表达式
    //该注解只能获取spring容器中的数据.前提条件就是该数据必须被容器加载
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;

    @RequestMapping("/getNode")
    public String getNode(){    //IP:PORT

        return host + ":"+ port;
    }
}
