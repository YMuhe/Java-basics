package com.jt.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Predicate;
@Component  //将对象交给Spring容器管理
public class UserInterceptor implements HandlerInterceptor {

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
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //实现用户重定向
        response.sendRedirect("/user/login.html");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
