package com.tencoding.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Value("${file.path}")
	private String uploadFolder;
	
	// 스토리 이미지 뿌리기  (WebMvcConfigurer에 있는 메서드)
	// 가상 경로를 만들어서 실제 경로로 접근해서 보여주게 하는 기능
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		System.out.println("file:///" + uploadFolder);
		//file:///C:/springimageDir/tencoBlog/upload/49c477fd-adf9-480c-8738-f23e57c7c7d4_common.jfif
		registry.addResourceHandler("/upload/**") // URL 패턴을 만들어주는 녀석 /upload/ -> 낚아챔
		.addResourceLocations("file:///" + uploadFolder) // 파일에 접근 | 설정해놓은 경로  실제 물리적인 경로
		.setCachePeriod(60 * 10 * 6) // 캐시의 지속시간 설정(초)
		.resourceChain(true) // 리소스 찾는것을 최적화 하기 위해서(성능때문에 그냥 걸어 놓는게 좋다)
		.addResolver(new PathResourceResolver());
		
		
	}
	
	
	// 필터를 등록하게 해주는 놈!
	@Bean
	public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean(){
		FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new XssEscapeServletFilter());
		filterRegistrationBean.setOrder(1); // 순서
		filterRegistrationBean.addUrlPatterns("/*"); //어떤 Url을 지정할건지
		
		
		return filterRegistrationBean;
		
	}
	

	
}
