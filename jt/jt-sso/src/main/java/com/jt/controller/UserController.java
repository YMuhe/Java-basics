package com.jt.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

import java.util.List;

//要求返回的数据都是JSON
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 完成用户数据的校验 JT-SSO项目
     * url地址: http://sso.jt.com/user/check/{param}/{type}
     * 参数:    param 校验数据/type 校验类型/callback 回调函数
     * 返回值:  SysResult VO对象     data:true数据存在/false 数据不存在可以使用
     * 提示:  该请求是JSONP请求方式,,所以需要特定的格式封装...
     */
     @RequestMapping("/check/{param}/{type}")
     public JSONPObject checkUser(@PathVariable String param,@PathVariable Integer type, String callback){

         boolean flag = userService.checkUser(param,type);  //表示数据不存在,可以使用
         SysResult sysResult = SysResult.success(flag);
         return new JSONPObject(callback, sysResult);
     }


    /**
     * 测试: 要求查询user表中的所有的数据
     */
    @RequestMapping("/findAll")
    public List<User> findAll(){

        return userService.findAll();
    }

    /**
     * 业务需求:
     *     根据用户ticket信息,查询用户信息
     * 1.url地址:http://sso.jt.com/user/query/8d5fc189ccde43f7a6b6bf4aecd9eb0e?callback=jsonp1613793443098&_=1613793443147
     * 2.请求参数: ticket信息
     * 3.返回值结果:SysResult对象
     * 注意: JSONP方式进行跨域请求. callback(JSON)
     */
    @RequestMapping("/query/{ticket}")
    public JSONPObject findUserByTicket(String callback,@PathVariable String ticket){

        //利用ticket从redis中动态获取数据
        String json = jedisCluster.get(ticket);
        //User user = ObjectMapperUtil.toObj(json, User.class);
        if(StringUtils.hasLength(json)){
            return new JSONPObject(callback, SysResult.success(json));
        }
        return new JSONPObject(callback,SysResult.fail());
    }










}
