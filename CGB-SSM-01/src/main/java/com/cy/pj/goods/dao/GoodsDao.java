package com.cy.pj.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.cy.pj.goods.pojo.Goods;

public interface GoodsDao {//会话对象就是这个接口的实现类吗?接口的实现类中会用到sqlsession对象
	@Select("select * from tb_goods")
	List<Goods> findGoods();
}
