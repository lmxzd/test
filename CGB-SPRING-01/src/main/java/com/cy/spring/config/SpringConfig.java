package com.cy.spring.config;

import org.springframework.context.annotation.ComponentScan;
/**
 * 用于告诉spring容器从从指定包进行bean的扫描
 * @author soft01
 * @Component
 */
@ComponentScan("com.cy.spring.beans")
public class SpringConfig {//相当于spring-configs.xml文件,在这个里面设置各种规范
	
}
