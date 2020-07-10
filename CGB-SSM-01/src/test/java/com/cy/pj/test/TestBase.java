package com.cy.pj.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cy.pj.common.config.SpringRepositoryConfig;

public class TestBase {
	/**
	 * 初始化ApplicationContext(继承BeanFactory)严格意义上实际还是属于一个BeanFactory对象
	 * 说明:ApplicationContext对象为BeanFactory工厂提供了作用域(场景或者上下文)
	 * 例如Singleton Request 
	 */
	 protected AnnotationConfigApplicationContext ctx;
	 @Before
	 public void init() {
		 /*Context对象创建时会读取配置上的注解@ComponentScan
		 并基于注解上描述的包名,在对应的目录进行类的查找扫描
		 并检查类上是否有特定注解(例如@Component,@Service,...)修饰
		 假如有,则有spring  ??
		 */
	  ctx=new AnnotationConfigApplicationContext(
			  SpringRepositoryConfig.class);
	 }
	 @Test
	 public void testCtx() {
		 System.out.println(ctx);
	 }
	 /**销毁容器*/
	 @After//因为是一个成员变量,所以单元测试结束也不会关闭,所以要主动调用close()
	 public void close() {
		 ctx.close();
	 }
}
