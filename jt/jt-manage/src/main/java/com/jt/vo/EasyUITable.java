package com.jt.vo;

import com.jt.pojo.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITable {
    private Long total;
    private List rows;

    /**
     * 对象转化为JSON时,必须对象的什么方法??? get方法
     * JSON转化为对象时,调用的是对象的setxxx方法
     * json转化时,必须提供get/set方法
     */
    /*public String getJT(){

        return "你好我是京淘";
    }*/

}
