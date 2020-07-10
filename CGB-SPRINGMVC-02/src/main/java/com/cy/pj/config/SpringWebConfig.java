package com.cy.pj.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**通过此类替代spring-configs.xml*/
/** 配置包扫描 */
@ComponentScan("com.cy")
/**  注册默认bean对象(mvc默认的一些bean对象 ),也就是启用mvc默认配置 */
@EnableWebMvc
public class SpringWebConfig implements WebMvcConfigurer {
	/** 配置 启动静态资源处理器 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	/** 配置视图解析器 */
	// 可以重写,也可以自己写bean注解
//	@Bean//各个注解是怎么运作的,写注解的目的是什么?
//	public ViewResolver configureViewResolvers() {
//		InternalResourceViewResolver vr = new InternalResourceViewResolver();
//		vr.setPrefix(prefix);
//		vr.setSuffix(suffix);
//		return vr;
//	}
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		//ViewResolverRegistry 的jsp方法底层还是调用的InternalResourceViewResolver 的set方法,或者用Constructor方法也可以
		registry.jsp("/WEB-INF/pages/", ".html");
	}
}
