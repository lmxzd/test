package com.cy.pj.common.config;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**这个整合是很关键的,底层实现这应该是比较重要的一个模块*/
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	//Service,Repository(Dao)
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("getRootConfigClasses()");
		return new Class[] {SpringRepositoryConfig.class,SpringServiceConfig.class};
	}
	//View,Controller
	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("getServletConfigClasses()");
		return new Class[] {SpringWebConfig.class};
	}
	@Override
	protected String[] getServletMappings() {
		System.out.println("getServletMappings()");
		return new String[] {"/"};
	}

}