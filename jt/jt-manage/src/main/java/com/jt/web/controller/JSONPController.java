package com.jt.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.ItemDesc;
import com.jt.util.ObjectMapperUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class JSONPController {

    //value 写需要回传的数据  callback(JSON)的过程由JSONPObject对象完成 省略了自己操作的步骤
    @RequestMapping("/web/testJSONP")
    public JSONPObject jsonp(String callback){
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(1000L).setItemDesc("JSONP调用成功!!!")
                .setCreated(new Date()).setUpdated(new Date());
        JSONPObject jsonpObject = new JSONPObject(callback, itemDesc);
        return jsonpObject;
    }






    //返回值要求:  callback(json串)
    /*@RequestMapping("/web/testJSONP")
    public String jsonp(String callback){
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(1000L).setItemDesc("JSONP调用成功!!!")
                .setCreated(new Date()).setUpdated(new Date());
        String json = ObjectMapperUtil.toJSON(itemDesc);
        System.out.println(json);
        return callback + "( " + json + ")";
    }*/



}
