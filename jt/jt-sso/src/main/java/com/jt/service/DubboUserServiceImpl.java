package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

import java.util.UUID;

@Service(timeout = 3000)
public class DubboUserServiceImpl implements DubboUserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisCluster jedisCluster;


    @Override
    public void saveUser(User user) {
        //将密码md5加密
        byte[] bytes = user.getPassword().getBytes();
        String md5Pass = DigestUtils.md5DigestAsHex(bytes);
        user.setPassword(md5Pass);
        //要求email不为null 暂时使用电话代替
        user.setEmail(user.getPhone());
        userMapper.insert(user);
    }

    /**
     * 业务说明: 实现用户单点登录操作
     *  1.根据用户名和密码查询数据库
     * @param user
     * @return
     */
    @Override
    public String doLogin(User user) {//username/password不为null
        //密文加密
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        //条件构造器  根据对象中不为null的属性充当where条件 查询的是user的全部信息
        User userDB = userMapper.selectOne(new QueryWrapper(user));

        String ticket = null;
        if(userDB !=null){
            //用户名和密码正确
            ticket = UUID.randomUUID().toString().replace("-", "");
            //数据安全性 没有办法得到保证  要对敏感数据进行脱敏处理
            userDB.setPassword("123456你猜猜?");
            String json = ObjectMapperUtil.toJSON(userDB);
            jedisCluster.setex(ticket, 7*24*60*60, json);

        }
        return ticket;
    }
}
