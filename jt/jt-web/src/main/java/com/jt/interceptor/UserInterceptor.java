package com.jt.interceptor;

import com.jt.pojo.User;
import com.jt.util.CookieUtil;
import com.jt.util.ObjectMapperUtil;
import com.jt.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Predicate;
@Component  //将对象交给Spring容器管理
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 通过拦截器实现拦截,重定向系统登录页面
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     *
     * 返回值说明:
     *      boolean   false 表示拦截 一般配合重定向方式使用
     *
     * 业务调用:
     *      1.获取cookie中的数据
     *      2.检查redis中是否有数据
     *      3.校验用户是否登录.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取cookie中的数据,检查是否登录
        String ticket = CookieUtil.getCookieValue(request, "JT_TICKET");
        if(StringUtils.hasLength(ticket) && jedisCluster.exists(ticket)){//判断数据是否有值
                //2.动态获取user信息
                String json = jedisCluster.get(ticket);
                //3.将json转化为用户对象
                User user = ObjectMapperUtil.toObj(json, User.class);
                //利用request对象实现
                request.setAttribute("JT_USER", user);
                //利用ThreadLocal方式实现
                UserThreadLocal.set(user);
                return true;    //表示程序放行
        }

        //实现用户重定向
        response.sendRedirect("/user/login.html");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //防止内存泄露
        request.removeAttribute("JT_USER");
        UserThreadLocal.remove();
    }
}
