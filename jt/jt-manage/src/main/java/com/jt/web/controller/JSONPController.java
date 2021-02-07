package com.jt.web.controller;

import com.jt.pojo.ItemDesc;
import com.jt.util.ObjectMapperUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class JSONPController {

    //返回值要求:  callback(json串)
   @RequestMapping("/web/testJSONP")
    public String jsonp(String callback){
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(1000L).setItemDesc("JSONP调用成功!!!")
                .setCreated(new Date()).setUpdated(new Date());
        String json = ObjectMapperUtil.toJSON(itemDesc);
        return callback + "( " + json + ")";
    }

}
