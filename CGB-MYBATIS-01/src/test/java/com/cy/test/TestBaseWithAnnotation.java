package com.cy.test;

import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;

/**
 * 不通过mybatis-configs.xml,通过手动的创建对象,自己书写创建过程来实现mybatis功能,这样性能会好一些
 * 然后下一步还可以分为两种方式:
 * 1.xml文件+接口
 * 2.用注解和接口
 * @author soft01
 *
 */
public class TestBaseWithAnnotation {
	protected SqlSessionFactory sqlSessionFactory;
	@Before
	public void init() throws IOException {
		//1.创建数据源对象(PooledDataSource)
		PooledDataSource dataSource = (PooledDataSource)dataSource();
		//2.创建事务工厂对象(通过此对象创建事务管理器对象)
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		//3.创建环境对象(开发环境,实现基础配置)
		Environment environment = new Environment("development", transactionFactory, dataSource);
		//4.创建配置中心对象
		Configuration config = new Configuration(environment);
		//5.创建SqlSessionFactory对象
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
		registerMapper(config);
	}
	private void registerMapper(Configuration config) throws IOException {
		/*6.1:利用注解方式(用于简单sql):
		先和接口添加映射,如果接口上有注解,则用该注解,那用mapper.xml的该怎么用呢? 见6.2
		*/
//		config.addMapper(GoodsDao.class);//这一步不用写,底层parse阶段可以自动add绑定的接口(自己查源码获得)
		/*6.2:利用xxxMapper.xml配置文件方式(用于复杂sql,便于读程): 待做
		这一步将接口和xml配置文件建立映射,建立之后就可以和映射文件xxxMapper连接了,连接之后就可以用XxxMapper
		文件中的sql语句了
		*/
		String resource = "mapper/GoodsMapper.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
        XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, config, resource, config.getSqlFragments());
        mapperParser.parse();
	}
	private DataSource dataSource() {
		PooledDataSource dataSource = new PooledDataSource();
		dataSource.setDriver("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///dbgoods?serverTimezone=GMT");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
}
