package com.cy.pj.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.goods.pojo.Goods;

public interface GoodsDao<E> {
	//1.通过id(用array封装起来)删除元素 ,或者用不定参数...
	int deleteObjects(int[] array);
	int deleteObjects(Integer...ids);//不定参数写在参数类型的后面
	//2.通过id查询元素
	List<E> selectObjects(Integer...ids);
	List<Goods> findPageObjects(
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);
	//3.用注解来省略映射文件中的sql语句 (只适用于简单的情况,复杂的话不易读程)
	//也就是说先看注解,再看xml映射文件  value可以省略
	@Select(value = {"select count(*) from tb_goods",""})//这个value可以省略,而且这个里面可以写多条语句
	int getRowCount();
}
