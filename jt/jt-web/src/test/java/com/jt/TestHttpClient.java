package com.jt;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestHttpClient {

    /**
     * 1.实例化HttpClient对象
     * 2.定义url地址
     * 3.封装请求方式GET/POST/PUT....
     * 4.发送请求获取响应的结果.response
     * 5.判断响应是否正确. 200表示请求正确,获取响应的结果.
     * 6.解析服务器返回值.获取有效数据
     */
    @Test//在java代码中发起http请
    public void testGet() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        String url = "https://www.cctv.com/";
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        int status  = httpResponse.getStatusLine().getStatusCode();   //获取状态码
        if(status == 200 ){
            HttpEntity entity = httpResponse.getEntity();   //获取响应的实体对象
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println(result);
        }
    }
}
