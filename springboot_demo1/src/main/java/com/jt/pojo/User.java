package com.jt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data   //动态添加get/set/toString/equals/hash方法
@Accessors(chain = true)    //链式加载
@NoArgsConstructor
@AllArgsConstructor     //要么都没有.要写一起写
public class User {

    private Integer id;
    private String name;
    private Integer age;
    private String sex;

   /* //链式加载原理  重构set方法
    public User setId(Integer id){
        this.id = id;
        return this;
    }

    public User setName(String name){
        this.name = name;
        return this;
    }*/




}
