package com.jt.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestObjectMapper {

    //能否转化list集合?????
    @Test
    public void test01() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        //将对象转化为JSON  调用的是对象的get方法获取属性/属性的值
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(1000L).setItemDesc("对象与json转化")
                .setCreated(new Date()).setUpdated(new Date());
        String json = objectMapper.writeValueAsString(itemDesc);
        System.out.println(json);

        //将JSON串转化为对象 调用的是对象的set方法为对象属性赋值
        ItemDesc itemDesc2 = objectMapper.readValue(json, ItemDesc.class);
        System.out.println(itemDesc2.getItemDesc());
    }


}
