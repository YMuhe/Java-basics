package com.jt.config;

import com.jt.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration						  //web项目中的web.xml文件
public class MvcConfigurer implements WebMvcConfigurer{

	@Autowired
	private UserInterceptor userInterceptor;

	//添加拦截器配置
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(userInterceptor)
				.addPathPatterns("/cart/**","/order/**");
	}

	//开启匹配后缀型配置
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		
		configurer.setUseSuffixPatternMatch(true);
	}
}
