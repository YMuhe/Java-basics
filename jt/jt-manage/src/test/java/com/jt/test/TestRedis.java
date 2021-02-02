package com.jt.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class TestRedis {

    /**
     * 主要目的测试程序远程操作Redis是否有效
     * 配置redis服务:
     *      1.redis需要关闭IP绑定模式
     *      2.redis关闭保护模式
     *      3.redis最好开启后端运行
     *
     * 完成redis客户端操作
     */
    @Test
    public void test01() throws InterruptedException {
        //1.测试链接
        Jedis jedis = new Jedis("192.168.126.129",6379);
        jedis.set("a", "动态获取redis中的数据");
        System.out.println(jedis.get("a"));

        //2.测试数据是否存在
        if(jedis.exists("a")){
            jedis.set("a", "修改数据");
        }else{
            jedis.set("a", "新增数据");
        }

        //3.删除redis
        jedis.del("a");

        //4.清空所有的数据
        jedis.flushDB();
        jedis.flushAll();

        //5.为数据添加超时时间
        jedis.set("b", "设定超时时间");
        jedis.expire("b", 10);
        Thread.sleep(2000);
        System.out.println(jedis.ttl("b"));
    }





}
