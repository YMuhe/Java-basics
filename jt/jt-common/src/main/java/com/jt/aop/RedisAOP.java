package com.jt.aop;

import com.jt.anno.CacheFind;
import com.jt.util.ObjectMapperUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.util.Arrays;

/*@Service
@Controller
@Repository*/
@Component  //组件 将类交给spring容器管理
@Aspect     //表示我是一个切面
public class RedisAOP {

    @Autowired
    private Jedis jedis;

    /*
    * 实现AOP业务调用
    * 1.拦截指定的注解
    * 2.利用环绕通知实现
    * 实现步骤:
    *       1.获取KEY  必须先获取注解 从注解中获取key?
    *       2.校验redis中是否有值
    *     *
    * 3.知识点补充:
    *   指定参数名称进行传值,运行期绑定参数类型完成注解的拦截
    *   joinPoint必须位于参数的第一位.
    */
    @Around("@annotation(cacheFind)")
    public Object around(ProceedingJoinPoint joinPoint,CacheFind cacheFind){
        Object result = null;
        //key=业务名称::参数
        String key = cacheFind.key();
        String args = Arrays.toString(joinPoint.getArgs());
        key = key + "::" + args;

        //2.校验是否有值
        if(jedis.exists(key)){
            String json = jedis.get(key);
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Class returnType = methodSignature.getReturnType();

            result = ObjectMapperUtil.toObj(json,returnType);
            System.out.println("AOP查询redis缓存");

        }else{
            //redis中没有数据,所以需要查询数据库,将数据保存到缓存中
            try {
                result = joinPoint.proceed();
                String json = ObjectMapperUtil.toJSON(result);
                //是否设定超时时间
                if(cacheFind.seconds()>0){
                    jedis.setex(key, cacheFind.seconds(), json);
                }else{
                    jedis.set(key,json);
                }
                System.out.println("AOP查询数据库");
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        return result;
    }


    /**
     *   //1.获取key 注解  方法对象  类 方法名称  参数
     *         Class targetClass = joinPoint.getTarget().getClass();
     *         //2.获取方法对象
     *         String methodName = joinPoint.getSignature().getName();
     *         Object[] args = joinPoint.getArgs();
     *         Class[] classArgs = new Class[args.length];
     *         for(int i=0;i<args.length;i++){
     *             classArgs[i] = args[i].getClass();
     *         }
     *         try {
     *             //反射实例化对象
     *             Method method = targetClass.getMethod(methodName,classArgs);
     *             CacheFind cacheFind = method.getAnnotation(CacheFind.class);
     *             String key = cacheFind.key();
     *             System.out.println(key);
     *         } catch (NoSuchMethodException e) {
     *             e.printStackTrace();
     *         }
     */










    //公式 aop = 切入点表达式   +   通知方法
    //@Pointcut("bean(itemCatServiceImpl)")
    //@Pointcut("within(com.jt.service.*)")
    //@Pointcut("execution(* com.jt.service.*.*(..))")   //.* 当前包的一级子目录
   /* @Pointcut("execution(* com.jt.service..*.*(..))")  //..* 当前包的所有的子目录
    public void pointCut(){

    }*/

    //如何获取目标对象的相关参数?
    //ProceedingJoinPoint is only supported for around advice
   /* @Before("pointCut()")
    public void before(JoinPoint joinPoint){    //连接点
        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("目标对象:"+target);
        System.out.println("方法参数:"+Arrays.toString(args));
        System.out.println("类名称:"+className);
        System.out.println("方法名称:"+methodName);
    }*/


}
