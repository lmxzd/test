package com.cy.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.cy.pj.goods.dao.GoodsDao;
import com.cy.pj.goods.pojo.Goods;
/**
 * 测试mybatis中一级二级缓存的作用,以及多线程环境下缓存的作用
 * @author soft01
 * 缓存内部使用装饰模式
 * 基本完成
 */
public class TestCache01 extends TestBaseWithXml{
	
	@Test
	public void testFirstLevelCache() {
		//1.获取SqlSession对象 (会话对象)
		SqlSession session = factory.openSession();
		//2.执行查询业务
		GoodsDao<?> dao = session.getMapper(GoodsDao.class);
		List<Goods> list = dao.findPageObjects(0, 3);
		list = dao.findPageObjects(0, 3);
		//3.提交事务
		session.commit();
		//4.释放资源
		session.close();
	}
	@Test//test是结束之后默认结束内部的所有线程, 缓存也是会释放的??
	public void testSecondLevelCache() {
		//1.获取SqlSession对象 (会话对象)
		SqlSession session1 = factory.openSession();
		SqlSession session2 = factory.openSession();
		//2.执行查询业务
		GoodsDao<?> dao1 = session1.getMapper(GoodsDao.class);
		List<Goods> list1 = dao1.findPageObjects(0, 3);
		session1.commit();
		GoodsDao<?> dao2 = session2.getMapper(GoodsDao.class);
		List<Goods> list2 = dao2.findPageObjects(0, 3);
		//3.提交事务
		session2.commit();
		//4.释放资源
		session1.close();
		session2.close();
	}
	@Test//说明了多线程会使用二级缓存
	public void testManyThreadSecondLevelCache() throws Exception {
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					doQuery();
				};
			}.start();
			Thread.sleep(1000);
		}
	}
	private void doQuery() {
		//1.获取SqlSession对象 (会话对象)
		SqlSession session = factory.openSession();
		//2.执行查询业务
		GoodsDao<?> dao = session.getMapper(GoodsDao.class);
//		List<Goods> list = dao.findPageObjects(0, 3);
		int rows = dao.getRowCount();
		//3.提交事务
		session.commit();
		//4.释放资源
		session.close();
	}
}
