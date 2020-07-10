package com.cy.pj.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.goods.dao.Dao;
import com.cy.pj.goods.pojo.Goods;

@Service
public class ServiceImpl implements com.cy.pj.goods.service.Service{
	@Autowired
	private Dao dao;
	@Override
	public List<Goods> findGoods() {
		return dao.findGoods();
	}
	
}
