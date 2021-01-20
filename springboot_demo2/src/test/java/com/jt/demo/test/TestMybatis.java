package com.jt.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.demo.mapper.UserMapper;
import com.jt.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestMybatis {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll(){

        List<User> userList = userMapper.findAll();
        System.out.println(userList);
    }

    /*
    * 完成用户信息入库
    */
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("郑爽").setAge(30).setSex("女");
        //单表sql语句几乎不写
        userMapper.insert(user);
    }

    /**
     * 查询案例1: 查询Id=4 的用户
     * Id代表主机的含义
     */
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(4);
        System.out.println(user);
    }

    /**
     * 查询案例2: 查询name="八戒"的人
     * Sql: select * from user where name="八戒"
     * QueryWrapper<> 条件构造器 用来拼接where条件
     * 常见逻辑运算符:   = eq, > gt , < lt
     *                  >= ge, <= le
     */
    @Test
    public void testSelectByName(){
       /*
        该方法只能做=号的判断
        User user = new User();
        user.setName("八戒");
        //根据对象中不为null的属性拼接where条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);*/

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "小乔");
        System.out.println(userMapper.selectList(queryWrapper));
    }

    /**
     * 查询案例3: 查询age>18岁,并且name中包含 *精*用户
     * Sql: select * from user where age>18 and name like '%精%'
     * 如果中间是and连接符可以省略不写
     */
     @Test
     public void testSelectAnd(){
         QueryWrapper<User> queryWrapper = new QueryWrapper<>();
         queryWrapper.gt("age", 18)
                     .like("name", "精");
         System.out.println(userMapper.selectList(queryWrapper));
     }

    /**
     * 查询案例4:
     *  查询name不为null的用户,并且 sex=女, name要求以"君"结尾
     *  最后将数据按照id倒序排列
     */
     @Test
     public void testSelectDemo4(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("name")
                    .eq("sex", "女")
                    .likeLeft("name", "君")
                    .orderByDesc("id");
         System.out.println(userMapper.selectList(queryWrapper));
     }

    /**
     * 批量查询:  查询id=1,2,5,7,8 1000 的数据
     * 关键字:    in
     */
     @Test
     public void testSelectIn(){
         //数据基本结构没有取值方法.所以需要通过集合处理.
         Integer[] idArray = {1,2,5,7};
         //数组转化时使用包装类型
         List<Integer> idList = Arrays.asList(idArray);
         QueryWrapper queryWrapper = new QueryWrapper();
         queryWrapper.in("id", idList);
         System.out.println(userMapper.selectList(queryWrapper));

         //该方法与上述操作sql一致,只不过写法不同
         userMapper.selectBatchIds(idList);

         //获取数据表中第一个字段信息(主键)
         System.out.println(userMapper.selectObjs(null));
     }

     /*
        MP更新操作
        1.更新id=71的数据 name改为张翰
        2.将name="郑爽" 改为name="胡彦斌" sex改为男  age=20
        userMapper.update(修改后的数据对象set条件,修改的条件构造器where条件)
     * */
     @Test
     public void update(){
         User user = new User();
         user.setName("胡彦斌").setSex("男").setAge(20);
         UpdateWrapper updateWrapper = new UpdateWrapper();
         updateWrapper.eq("name", "郑爽");
         userMapper.update(user,updateWrapper);
         System.out.println("更新操作成功!!!!");

         /*User user = new User();
         //id当做where条件 其他不为null的属性当做set条件
         user.setId(71).setName("张翰");
         userMapper.updateById(user);*/
     }


}
