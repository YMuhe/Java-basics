package com.jt.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.params.SetParams;

@SpringBootTest //目的:动态获取spring容器中的数据
public class TestRedis {

    @Autowired
    private Jedis jedis;

    @Test
    public void testSpringJedis(){
        jedis.set("jedis", "spring对象测试");
        System.out.println(jedis.get("jedis"));
    }





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

    //原子性
    @Test
    public void test02(){
        Jedis jedis = new Jedis("192.168.126.129", 6379);
        jedis.set("c", "测试redis");
        //需求1: 如果数据不存在时,才会为数据赋值.
        jedis.setnx("d","测试setnx方法");
        System.out.println(jedis.get("d"));

        //需求2: 需要为数据添加超时时间,同时满足原子性的要求
                //jedis.set("s", "为数据添加超时时间");
                //有时程序中断了,下列的方法将不会执行.
                //jedis.expire("s", 20);
                //System.out.println(jedis.ttl("s"));
        //为数据添加超时时间
        jedis.setex("s", 20, "为数据添加超时111");
        System.out.println("获取超时时间:"+jedis.ttl("s"));
    }

    /**
     *  需求: 如果数据存在才修改,并且为数据添加超时时间,满足原子性要求
     *  SetParams:
     *          XX: 数据存在时赋值.
     *          NX: 数据不存在时赋值
     *          EX: 添加超时时间单位秒
     *          PX: 添加超时时间单位毫秒
     */
    @Test
    public void test03(){
        Jedis jedis = new Jedis("192.168.126.129", 6379);
        jedis.flushAll();
        SetParams setParams = new SetParams();
        setParams.xx().ex(20);
        jedis.set("a", "测试方法",setParams);
        System.out.println(jedis.get("a"));
    }

    @Test
    public void testHash(){
        Jedis jedis = new Jedis("192.168.126.129",6379);
        jedis.hset("达内", "dept", "财务部");
        jedis.hset("达内", "users", "[{id:1,name:'张三'},{id:2,name:'李四'}]");
        System.out.println(jedis.hget("达内", "dept"));
    }

    @Test
    public void testList(){
        Jedis jedis = new Jedis("192.168.126.129",6379);
        jedis.lpush("list", "1","2","3");
        System.out.println(jedis.rpop("list")); //队列
    }

    //弱事务控制
    @Test
    public void testTx(){
        Jedis jedis = new Jedis("192.168.126.129",6379);
        Transaction transaction = jedis.multi();  //开启事务
        try {
            transaction.set("k", "k");
            transaction.set("c", "c");
            transaction.exec();     //提交事务
        }catch (Exception e){
            e.printStackTrace();
            transaction.discard();  //回滚事务
        }
    }
}
