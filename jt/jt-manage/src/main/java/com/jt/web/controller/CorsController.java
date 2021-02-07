package com.jt.web.controller;

import com.jt.pojo.ItemDesc;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@CrossOrigin    //表示该类中的所有的方法都可以进行跨域的操作
public class CorsController {

    /**
     * 实现jt-web 访问jt-manage的跨域访问
     * 标识方法可以允许指定的服务器跨域
     * methods: 可以允许访问的类型
     * maxAge:  长链接的有效期   -1是默认值 代表30分钟
     */
    @RequestMapping("/web/cors")
    @CrossOrigin(value = "http://www.jt.com",methods = RequestMethod.GET)
    public ItemDesc cors(){
        ItemDesc itemDesc = new ItemDesc().setItemDesc("CORS测试").setItemId(1000L);
        return itemDesc;
    }
}
