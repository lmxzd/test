package com.cy.pj.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**springMVC中vc的配置的配置*/
@Configuration
@ComponentScan("com.cy.pj.goods.controller")//扫描这个包下的类
@EnableWebMvc//这个注解什么意思?  启用mvc默认配置
public class SpringWebConfig implements WebMvcConfigurer{
	/**启动静态资源处理*/
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	/**配置视图解析器*/
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("WEB-INF/pages/", ".html");
	}
}
