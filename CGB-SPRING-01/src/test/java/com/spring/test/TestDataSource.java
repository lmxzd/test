package com.spring.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;



public class TestDataSource extends TestBase {
	@Test
	public void testDuridDataSource() throws SQLException {
		//第一个参数和方法名相同 或者注解里面自己设置的,底层是个拼串
		DataSource ds=ctx.getBean("druid",DataSource.class);
		Assert.assertNotEquals(null, ds);
		System.out.println(ds.getConnection());
	}
	@Test
	public void testHiKariCPDataSource() throws SQLException {
		DataSource ds = ctx.getBean("hiKariCP",DataSource.class);
		Assert.assertNotEquals(null, ds);
		System.out.println(ds.getConnection());
	}
	@Test
	public void dataSource() throws SQLException {
		//也可以这样通过返回值类型来找相应的datasource但是这样如果有多个连接池就不可以了,所以现在这种情况
		//是会报错的
		DataSource ds = ctx.getBean(DataSource.class);
		Assert.assertNotEquals(null, ds);
		System.out.println(ds.getConnection());
	}
}
