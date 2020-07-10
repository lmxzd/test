package com.cy.pj.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.alibaba.druid.pool.DruidDataSource;
/**springmvc  m层的数据持久模块的配置包括 数据源配置,包括连接池DataSource和mybatis的第三方配置整合*/
@Configuration
@MapperScan("com.cy.pj.goods.dao")//扫描这个包下的接口
public class SpringRepositoryConfig {
	/**整合连接池对象,将他交给spring来管理,自己创建对象,自己回收对象*/
	@Lazy(false)//默认不延时加载
	@Scope("singleton")//默认是单例作用域
	@Bean(value="druid",initMethod="init",destroyMethod="close")
	public DruidDataSource dataSource() {
		DruidDataSource ds=new DruidDataSource();
		//可以省略设置驱动
		ds.setUrl("jdbc:mysql:///dbgoods?serverTimezone=GMT%2B8");
		ds.setUsername("root");
		ds.setPassword("root");
		return ds;
	}
	/**整合Mybatis框架*/
	//@Autowired//默认会有这个值,按类型自动给dataSource注入设置的值
	@Bean("sqlSessionFactory")
	public SqlSessionFactory newSqlSessionFactory(DataSource dataSource) throws Exception {
		//构建SqlSessionFactoryBean对象
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		//调用FactoryBean的getObject方法创建SqlSessionFactory
		//底层会使用SqlSessionFactoryBuilder创建
		return factoryBean.getObject();
	}
}
