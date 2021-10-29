package com.arimsky.blogapi.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @ClassName: SpringMvcConfig
 * @author: aRimsiky
 * @date: 2021/10/20
 */

@SpringBootConfiguration
public class SpringMvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//跨域配置，不可设置为*，不安全,
		registry.addMapping("/**").allowedOrigins("http://localhost:8080");
	}


}



