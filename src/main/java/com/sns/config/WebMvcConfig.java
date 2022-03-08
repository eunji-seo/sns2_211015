package com.sns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**") // http://localhost/images/toma1019_16456453342/sun.png
		///Users/seoeunji/Library/Containers/com.linearity.vn/Data/Documents/6.spring_project/sns-211015/workspace/images/
		//D:\\서은지_211015\\6_spring-project\\sns\\sns_workspace\\images/
		.addResourceLocations("file:////D:\\서은지_211015\\6_spring-project\\sns\\sns_workspace\\images/"); // 실제 파일 저장 위치
		
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**")// **아래 디렉토리까지 확인
		.excludePathPatterns("/static/**","/error","/user/sign_out"); //권한 검사 하지 않는 Path 예외
		
	}
	
}

