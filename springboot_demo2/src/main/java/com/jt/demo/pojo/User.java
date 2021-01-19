package com.jt.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName	//("user")	//定义表名与对象之间的关系
public class User implements Serializable {
	@TableId(type = IdType.AUTO)	//主键自增
	private Integer id;
	//@TableField("name")			//字段映射 如果名称相同,则可以省略不写
	private String name;
	private Integer age;
	private String sex;

}
