package com.jt.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/*@Service
@Controller
@Repository*/
@Component  //组件 将类交给spring容器管理
@Aspect     //表示我是一个切面
public class RedisAOP {

    //公式 aop = 切入点表达式   +   通知方法
    //@Pointcut("bean(itemCatServiceImpl)")
    //@Pointcut("within(com.jt.service.*)")
    //@Pointcut("execution(* com.jt.service.*.*(..))")   //.* 当前包的一级子目录
    @Pointcut("execution(* com.jt.service..*.*(..))")  //..* 当前包的所有的子目录
    public void pointCut(){

    }

    //如何获取目标对象的相关参数?
    //ProceedingJoinPoint is only supported for around advice
    @Before("pointCut()")
    public void before(JoinPoint joinPoint){    //连接点
        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("目标对象:"+target);
        System.out.println("方法参数:"+Arrays.toString(args));
        System.out.println("类名称:"+className);
        System.out.println("方法名称:"+methodName);
    }

    //作业: 利用自定义注解@CacheFind 实现缓存查询!!!!

}