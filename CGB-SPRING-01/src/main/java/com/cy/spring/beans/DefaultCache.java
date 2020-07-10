package com.cy.spring.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
	@Component 注解描述的类表示是由spring管理的一般组件对象
	@Lazy 用于告诉spring容器此对象要延迟加载,描述类的实例对象在使用时才创建
	@Scope 用于告诉spring容器此bean的作用域 单例作用域
	1)singleton (单例作用域-默认,会存储到池中,容器关闭时会自动销毁)
	2)prototype (多例作用域,每次获取都创建新对象,不会存储到池中,且spring容器不负责销毁,GC来销毁)
 */
@Lazy
@Scope("singleton") //也可以设置为多例作用域:prototype每次获取都会创建新的实例对象
@Component //@Controller,@Service,...
public class DefaultCache {
	public DefaultCache() {
		System.out.println("DefaultCache()");
	}
	/**
	 * jdk1.7之后,用于描述对象初始化的方法,在构造方法之后
	 */
	@PostConstruct //告诉spring 此对象初始化时执行init方法 方法名不重要,可以随便设置,主要是注解比较重要
	public void init() {
		System.out.println("init()");
	}
	/**
	 * 用于描述销毁前会执行的方法
	 */
	@PreDestroy//告诉spring 此对象销毁前执行close方法
	public void close() {
		System.out.println("close()");
	}
}
