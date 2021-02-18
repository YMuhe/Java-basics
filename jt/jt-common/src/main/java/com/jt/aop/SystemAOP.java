package com.jt.aop;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.vo.SysResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

//定义全局异常的处理机制
@RestControllerAdvice
public class SystemAOP {

    /**
     * 业务说明: 由于jsonp跨域调用即使错误,也应该按照callback(JSON)的方式返回
     * 所以应该重构全局异常处理.,关于JSONP返回的问题
     * 思路: 由于jsonp调用一般都会携带callback参数 所以可以通过该参数是否存在判断是否为JSONP调用!!!
     * @param
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    public Object result(Exception e, HttpServletRequest request){
        e.printStackTrace();    //打印错误日志

        String callback = request.getParameter("callback");
        if(StringUtils.hasLength(callback)){
            //如果callback有值  按照jsonp方式返回
            return new JSONPObject(callback, SysResult.fail());
        }
        return SysResult.fail();
    }
}
