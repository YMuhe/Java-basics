package com.jt.demo.service;

import com.jt.demo.pojo.User;

import java.util.List;

public interface UserService {

    //1.获取userList集合
    List<User> findAll();
}
