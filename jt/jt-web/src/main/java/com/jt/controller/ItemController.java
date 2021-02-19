package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.service.DubboItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller     //页面跳转.......
public class ItemController {

    //先启动服务消费者 暂时不校验是否有提供者
    @Reference(check = false)
    private DubboItemService itemService;


}
