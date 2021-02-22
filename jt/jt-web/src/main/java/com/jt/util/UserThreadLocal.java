package com.jt.util;

import com.jt.pojo.User;

public class UserThreadLocal {
    //线程的一个属性  ThreadLocal是线程安全的
    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void set(User user){
        threadLocal.set(user);
    }

    public static User get(){

        return threadLocal.get();
    }

    public static void remove(){

        threadLocal.remove();   //删除数据
    }
}
