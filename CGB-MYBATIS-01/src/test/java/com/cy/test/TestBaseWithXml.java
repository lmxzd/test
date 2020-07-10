package com.cy.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
/**
 * 建立sqlsession对象,让别的类继承
 * @author soft01
 *完成
 */
public class TestBaseWithXml {
	/**
	 * 借助此对象创建SqlSession(通过此对象
	 * 实现与数据库之间的会话)
	 */
	protected SqlSessionFactory factory;
    /**
     * 此方法会在@Test注解修饰的方法之前执行,
     * 通常用于做一些初始化操作(方法名自己定义)
     */
	@Before
	public void init()throws IOException{
		//这个相对路径是类路径,从类路径中读取配置文件
		InputStream in=Resources.getResourceAsStream("mybatis-configs.xml");//resources底层是文件流
		//解析流并对内容进行封装,然后构建工厂对象
		factory=new SqlSessionFactoryBuilder().build(in);
		//系统底层建造者模式构建工厂对象(此对象构建过程相对复杂)
	}
	@Test
	public void testSqlSessionConnection(){
		SqlSession session=factory.openSession();
		Connection conn=session.getConnection();
	}
}