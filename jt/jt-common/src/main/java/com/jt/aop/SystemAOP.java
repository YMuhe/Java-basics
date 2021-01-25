package com.jt.aop;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//定义全局异常的处理机制
@RestControllerAdvice
public class SystemAOP {

    @ExceptionHandler({RuntimeException.class})
    public Object result(Exception e){

        e.printStackTrace();
        return SysResult.fail();
    }
}
