package com.jt.demo.mapper;

import java.util.List;

import com.jt.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

//@Mapper	//将结构交给Mybatis管理 之后统一交给spring管理
public interface UserMapper {
	
	List<User> findAll();
}
