package com.jt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration  //表示一个配置类  一般会与@Bean的注解联用
@PropertySource("classpath:/redis.properties") //导入配置文件
public class RedisConfig {

    //springBoot整合Redis集群
    @Value("${redis.nodes}")
    private String nodes;   //node,node,node

    @Bean
    public JedisCluster jedisCluster(){
        Set<HostAndPort> nodesSet = new HashSet<>();
        String[] nodeArray = nodes.split(",");
        for (String node : nodeArray){ //node=IP:PORT
            String host = node.split(":")[0];
            int port = Integer.parseInt(node.split(":")[1]);
            HostAndPort hostAndPort = new HostAndPort(host, port);
            nodesSet.add(hostAndPort);
        }
        return new JedisCluster(nodesSet);
    }







   /* @Value("${redis.nodes}")
    private String nodes;   //node,node,node

    @Bean
    public ShardedJedis shardedJedis(){

        List<JedisShardInfo> shards = new ArrayList<>();
        String[] nodeArray = nodes.split(",");
        for (String node : nodeArray){  //host:port
            String host = node.split(":")[0];
            int port = Integer.parseInt(node.split(":")[1]);
            shards.add(new JedisShardInfo(host,port));
        }
        return new ShardedJedis(shards);
    }*/



   /* @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;

    @Bean   //将方法的返回值结果,交给spring容器进行管理.
    public Jedis jedis(){

        return new Jedis(host, port);
    }*/

}
