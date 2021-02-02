package com.jt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.Jedis;

@Configuration  //表示一个配置类  一般会与@Bean的注解联用
@PropertySource("classpath:/redis.properties") //导入配置文件
public class RedisConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;

    @Bean   //将方法的返回值结果,交给spring容器进行管理.
    public Jedis jedis(){

        return new Jedis(host, port);
    }

}