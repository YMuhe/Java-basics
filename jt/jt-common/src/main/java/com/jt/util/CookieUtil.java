package com.jt.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//工具API 主要负责 新增cookie 删除cookie  根据key获取cookie  获取cookie的值
public class CookieUtil {

    public static void addCookie(HttpServletResponse response,String name, String value, int maxAge, String path, String domain){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    public static void delCookie(HttpServletResponse response,String name,String path, String domain){

        addCookie(response, name, "", 0, path, domain);
    }

    public static Cookie getCookie(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length >0){
            for (Cookie cookie : cookies){
                if(name.equals(cookie.getName())){

                    return cookie;
                }
            }
        }
        return null;
    }

    public static String getCookieValue(HttpServletRequest request,String name){
        Cookie cookie = getCookie(request, name);
        return cookie==null?null:cookie.getValue();
    }
}
