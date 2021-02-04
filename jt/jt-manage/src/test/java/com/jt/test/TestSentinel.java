package com.jt.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class TestSentinel {

    @Test
    public void test01(){
        //定义哨兵的集合信息
        Set<String> sentinels = new HashSet<>();
        sentinels.add("192.168.126.129:26379");

        //定义链接池信息
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(200);    //链接池 最多200个链接
        poolConfig.setMaxIdle(20);      //最大空闲链接数20
        poolConfig.setMinIdle(10);      //最小空闲链接数10
        JedisSentinelPool pool = new JedisSentinelPool("mymaster",sentinels,poolConfig);
        //动态获取jedis链接
        Jedis jedis = pool.getResource();
        jedis.set("abc", "redis赋值操作");
        System.out.println(jedis.get("abc"));
        jedis.close();
    }
}
