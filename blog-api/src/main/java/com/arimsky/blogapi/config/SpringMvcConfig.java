package com.arimsky.blogapi.config;

import com.arimsky.blogapi.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


/**
 * @ClassName: SpringMvcConfig
 * @author: aRimsiky
 * @date: 2021/10/20
 */

@SpringBootConfiguration
public class SpringMvcConfig implements WebMvcConfigurer {
	@Resource
	private LoginInterceptor loginInterceptor;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//跨域配置，不可设置为*，不安全,
		registry.addMapping("/**").allowedOrigins("http://localhost:8080");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/test")
				.addPathPatterns("/comments/create/change")
				.addPathPatterns("/articles/publish");
	}
}



