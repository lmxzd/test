package com.cy.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.cy.pj.goods.dao.GoodsDao;
import com.cy.pj.goods.pojo.Goods;

public class TestGoodsDao02 extends TestBaseWithXml{
	@Test
	public void testGetRowCount() {
		//1.获取SqlSession对象 (会话对象)
		SqlSession session = factory.openSession();
		//2.执行业务
		//底层自动生成代理对象,这一步已经和Mapper映射文件建立连接了,连接到对应的命名空间
		//然后获取GoodsDao接口的映射,然后就可以用方法来代替String串了,主要便于检查而已
		//建立映射关系不是这一步,建立是addMapper,底层来实现,这一步是getMapper是获取对应的接口,然后
		//去找对应的命名空间+id
		//性能稍微的变差主要是在这一步,因为用接口,底层需要创建实例对象(代理对象),然后再调用select等方法
		//但是例子01中是直接
		GoodsDao<?> dao = session.getMapper(GoodsDao.class);
		int rows = dao.getRowCount();
		System.out.println(rows);
		//3.提交事务
		session.commit();
		//4.释放资源
		session.close();		
	}
	@Test
	public void testDeleteObjects() {
		//1.获取SqlSession对象 (会话对象)
		SqlSession session = factory.openSession();
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
		SqlSession session = factory.openSession();
		//2.执行查询业务
		GoodsDao<?> dao = session.getMapper(GoodsDao.class);
		List<Goods> list = dao.findPageObjects(0, 3);
		//3.提交事务
		session.commit();
		//4.释放资源
		session.close();
	}
	@Test
	public void testSelectObjects() {
		SqlSession session = factory.openSession();
		GoodsDao<?> dao = session.getMapper(GoodsDao.class);
		List<Goods> list = (List<Goods>) dao.selectObjects(1,2);
		System.out.println(list);
		session.commit();
		session.close();
	}
}
