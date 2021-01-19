package com.jt.demo.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

//@Mapper	//将结构交给Mybatis管理 之后统一交给spring管理
//继承接口时 切记添加泛型
public interface UserMapper extends BaseMapper<User> {
	
	List<User> findAll();
}
