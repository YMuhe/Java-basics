package com.jt.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;

public class TestRedisShards {

    @Test
    public void testShards(){
        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(new JedisShardInfo("192.168.126.129",6379));
        shards.add(new JedisShardInfo("192.168.126.129",6380));
        shards.add(new JedisShardInfo("192.168.126.129",6381));
        ShardedJedis shardedJedis = new ShardedJedis(shards);
        //3台redis当做1台使用  内存容量扩大3倍.  79/80/81???
        shardedJedis.set("shards", "redis分片测试");
        System.out.println(shardedJedis.get("shards"));
    }
}
