package com.jt.service;

import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    //httpClient在java代码中 是万能的请求方式......
    //封装好的高级API SpringCloud 底层实现...
    //jt-web服务器  由jt-sso动态获取
    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        String url = "http://sso.jt.com/user/findAll";
        HttpGet httpGet = new HttpGet(url);
        HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                //说明请求正确
                String json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                userList = ObjectMapperUtil.toObj(json,userList.getClass());
                //拿到对象之后,按照指定的业务要求,对数据进行二次加工 解密/添加某些数据
                //xxxxxxxxxx
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
