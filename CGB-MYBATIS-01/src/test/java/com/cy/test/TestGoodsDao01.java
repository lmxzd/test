package com.cy.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.cy.pj.goods.pojo.Goods;
/**
 * 测试对象持久化的实现
 * @author soft01
 * 和2接口对比一下
 */
public class TestGoodsDao01 extends TestBaseWithXml{
	@Test 
	public void testInsertObject() {
		//1.创建Goods对象
		Goods g = new Goods();
		g.setId(5L);
		g.setName("扑克牌");
		g.setRemark("斗地主");
		//2.将Goods对象写入数据库
		//2.1获取SqlSession对象(会话对象)
		SqlSession session =factory.openSession();
		//2.2将对象持久化,(对象写操作)
		//这一步  命名空间.元素ID  因为是重复操作就提出去,用接口,然后底层生成代理对象来拼串(框架的作用)
		String statement = "com.cy.pj.goods.dao.GoodsDao.insertObject";
		//第一个参数是链接映射文件的位置,第二个是存储数据的pojo对象,第一个也会调第二个的方法
		int rows = session.update(statement,g);//创建连接是在这一步创建的
		System.out.println(rows);
		//2.3提交事务
		session.commit();//connection.commit  
		//2.4释放资源
		session.close();
	}
	@Test
	public void testDeleteObject() {
		SqlSession session = factory.openSession();
		String statement = "com.cy.pj.goods.dao.GoodsDao.deleteObjects";
		int rows = session.delete(statement,new int [] {1,2,3});
		System.out.println("被影响行数:"+rows);
//		session.commit();
		session.close();
	}
}
