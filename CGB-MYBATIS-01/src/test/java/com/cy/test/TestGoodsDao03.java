package com.cy.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.cy.pj.goods.dao.GoodsDao;
import com.cy.pj.goods.pojo.Goods;

public class TestGoodsDao03 extends TestBaseWithAnnotation{
	@Test
	public void testGetRowCount() {
		SqlSession session = sqlSessionFactory.openSession();
		GoodsDao<?> dao=session.getMapper(GoodsDao.class);
		int rows = dao.getRowCount();
		System.out.println(rows);
		session.commit();
		session.close();
	}
	@Test
	public void testDeleteObjects() {
		//1.获取SqlSession对象 (会话对象)这个封装了一系列的api 
		SqlSession session = sqlSessionFactory.openSession();
		//2.执行删除业务 
		//底层自动生成代理对象,这一步已经和Mapper映射文件建立连接了,连接到对应的命名空间
		GoodsDao<?> dao = session.getMapper(GoodsDao.class);
		int rows = dao.deleteObjects(new int[] {1,2});
		System.out.println(rows);
		//3.提交事务
		//		session.commit();
		//4.释放资源
		session.close();
	}
	@Test
	public void testFindPageObjects() {
		//1.获取SqlSession对象 (会话对象)
		SqlSession session = sqlSessionFactory.openSession();
		//2.执行查询业务
		GoodsDao<?> dao = session.getMapper(GoodsDao.class);
		List<Goods> list = dao.findPageObjects(0, 3);
		for (Goods g : list) {
			System.out.println(g);
		}
		//3.提交事务
		session.commit();
		//4.释放资源
		session.close();
	}
	@Test
	public void testSelectObjects() {
		SqlSession session = sqlSessionFactory.openSession();
		GoodsDao<?> dao = session.getMapper(GoodsDao.class);
		List<Goods> list = (List<Goods>) dao.selectObjects(1,2);
		System.out.println(list);
		session.commit();
		session.close();
	}
}
