package com.cy.spring.beans;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
/**
 * 
 * @author soft01
 *@Configuration 由spring来管理的配置对象(bean对象),和component应用场景不同,其他没太多区别
 */
@Configuration//这个里面的component是什么关系??
public class DataSourceConfig {//spring-config.xml
	/**Bean注解用来描述方法和注解,描述方法时表示这个方法的返回值交给Spring管理
	 * 假如未指定key,默认为方法名,应用场景:一般用于第三方资源的整合,会结合@Configuration注解一起使用
	 * Configuration描述的是类中方法  ??
	 * */
	//<bean/>
	@Bean(value="druid",initMethod="init",destroyMethod="close")//这个括号里的配置是什么意思?
	public DataSource druid() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl("jdbc:mysql:///dbgoods?serverTimezone=GMT");
		datasource.setUsername("root");
		datasource.setPassword("root");
		return datasource;
	}
	@Bean(destroyMethod="close")//(value="hikariCP",initMethod="init",destroyMethod="close")
	public DataSource hiKariCP() {
		HikariDataSource datasource = new HikariDataSource();
		datasource.setJdbcUrl("jdbc:mysql:///dbgoods?serverTimezone=GMT");
		datasource.setUsername("root");
		datasource.setPassword("root");
		return datasource;
	}
}
