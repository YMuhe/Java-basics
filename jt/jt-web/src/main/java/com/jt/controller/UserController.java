package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.service.UserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Reference(check = false)
    private DubboUserService dubboUserService;
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 1.注册:http://www.jt.com/user/register.html   页面名称:register.html
     * 2.登录:http://www.jt.com/user/login.html      登录页面:login.html
     * 能否利用一个方法实现该功能呢?  restFul
     */
    @RequestMapping("/{moduleName}")
    public String module(@PathVariable String moduleName){

        return moduleName;
    }

    /**
     * httpClient代码测试
     * http://www.jt.com/user/findAll
     */
     @Autowired
     private UserService userService;

     @RequestMapping("/findAll")
     @ResponseBody
     public List<User> findAll(){

        return userService.findAll();
     }

    /**
     * 实现用户信息注册
     * url:http://www.jt.com/user/doRegister
     * 参数: 用户名/密码/电话
     * 返回值: SysResult对象  JSON
     *
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult doRegister(User user){

        dubboUserService.saveUser(user);
        return SysResult.success();
    }

    //前提条件是 同一个request对象!!!!!!
    //在01的方法中向request对象set一个值
    //Session级别: 在整个会话期间有效的 一个会话共享一个session
    public void 购物车(HttpServletRequest request){
        //利用request对象 进行数据传递....
        //request.setAttribute("User", "刘昱江");
        request.getSession().setAttribute("user", "刘昱江");
    }

    //当程序执行test02时,需要动态获取test01中的数据
    public void 订单(HttpServletRequest request){

        String name = request.getSession().getAttribute("user").toString();
    }


    /**
     *
     * 业务需求:  完成用户登录操作
     * URL地址:   http://www.jt.com/user/doLogin?r=0.6659570464851978
     * 请求参数:  用户名和密码
     * 返回值:    SysResult对象
     *
     * 知识点讲解:
     *     Cookie: 在客户端保存服务器数据,在客户端实现数据共享.
     *          cookie.setMaxAge(); cookie生命周期
     *          cookie.setMaxAge(0);     立即删除cookie
     *          cookie.setMaxAge(100);   设定100秒有效期 100秒之后自动删除
     *          cookie.setMaxAge(-1);    关闭会话后删除
     *    2.设定path cookie的权限设定
     *          cookie.setPath("/")      一般条件下设定为/ 通用
     *              权限:根目录及其子目录有效
     *          cookie.setPath("/user")
     *              权限:/user目录下有效
     *    3.设定Cookie资源共享
     *      cookie特点: 自己的域名下,只能看到自己的Cookie. 默认条件下不能共享的
     *      cookie.setDomain("jt.com"); 只有在xxx.jt.com的域名中实现数据共享
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public SysResult doLogin(User user, HttpServletResponse response){

        String ticket = dubboUserService.doLogin(user);
        if(!StringUtils.hasLength(ticket)){

            return SysResult.fail();
        }
        Cookie cookie = new Cookie("JT_TICKET", ticket);
        cookie.setMaxAge(7*24*60*60);   //设定7天有效
        cookie.setPath("/");  //请求在根目录中都可以获取cookie
        cookie.setDomain("jt.com");
        response.addCookie(cookie);
        return SysResult.success();
    }


    /**
     * 实现用户退出操作
     * url地址: http://www.jt.com/user/logout.html
     * 返回值:  重定向到系统首页
     */

   /* @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        //删除redis中的记录.  key-value   获取key   Cookie中有key  先获取cookie
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length >0 ){
            String ticket = null;
            for (Cookie cookie : cookies){
                if("JT_TICKET".equals(cookie.getName())){
                    ticket = cookie.getValue();
                    //删除cookie
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    cookie.setDomain("jt.com");
                    response.addCookie(cookie);
                    break;
                }
            }

            //删除redis
            if(StringUtils.hasLength(ticket)){
                jedisCluster.del(ticket);
            }

        }

        return "redirect:/"; //代表缺省值
    }*/





}
