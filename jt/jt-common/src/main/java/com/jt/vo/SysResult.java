package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {
    //属性个数:  1.status 200/201   2.msg 服务器提示信息  3.data 服务器返回值数据
    private Integer status;    //200正确   201表示失败
    private String msg;        //服务器提示信息
    private Object data;       //服务器返回的数据

    //实现方法的封装
    public static SysResult fail(){

        return new SysResult(201,"服务器运行失败!!",null);
    }

    public static SysResult success(){

        return new SysResult(200, "服务器运行成功!!", null);
    }

    public static SysResult success(Object data){

        return new SysResult(200, "服务器运行成功!!", data);
    }

    public static SysResult success(String msg,Object data){

        return new SysResult(200, msg, data);
    }
}
