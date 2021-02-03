package com.jt.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)     //在方法中使用注解
@Retention(RetentionPolicy.RUNTIME) //运行期有效
public @interface CacheFind {

    String key();   //定义业务key
    int seconds() default -1;  //定义超时时间 -1表示不超时
}
