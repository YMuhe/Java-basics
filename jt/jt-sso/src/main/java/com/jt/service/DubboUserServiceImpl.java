package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

@Service(timeout = 3000)
public class DubboUserServiceImpl implements DubboUserService{

    @Autowired
    private UserMapper userMapper;


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
}
