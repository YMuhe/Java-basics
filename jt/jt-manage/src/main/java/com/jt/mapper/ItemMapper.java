package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item>{
    /*Linux系统中严格区分大小写,所以表名及字段都必须与表一致...
    * 老师为什么我的程序在windows中正常执行 ,linux中报错???
    * */
    @Select("SELECT * FROM tb_item ORDER BY updated DESC  LIMIT #{start},#{rows}")
    List<Item> findItemByPage(int start, Integer rows);
    //能否利用注解的方式实现? 不可以,因为其中需要循环遍历 所以不能使用注解.
    //Mybatis高版本会自动的添加@Param注解 前提参数必须为多个.
    void deleteItems(Long[] ids);

    void insertItem(Item item);
}
