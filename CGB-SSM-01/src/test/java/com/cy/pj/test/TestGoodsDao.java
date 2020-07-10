package com.cy.pj.test;

import java.util.List;

import org.junit.Test;

import com.cy.pj.goods.dao.GoodsDao;
import com.cy.pj.goods.pojo.Goods;
/**这个test的存在意义就是一步一步去验证*/
public class TestGoodsDao extends TestBase{
	@Test
    public void testFindGoods() {
   	   GoodsDao dao=ctx.getBean("goodsDao",GoodsDao.class);//通过beanFactory对象,来创建对应的bean
   	   List<Goods> list=dao.findGoods();
   	   for(Goods g:list) {
   		System.out.println(g);
   	   }
    }
}
