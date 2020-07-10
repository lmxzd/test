package com.company.java.cache04;

import java.util.HashMap;

/**
 * 缓存对象,其特点
 * 1)存储结构:散列表
 * 2)淘汰算法:没有
 * 存储对象的类
 * 完成
 */
public class PerpetualCache implements Cache{
//has a,利用cache的功能书写自己的方法,实际是对cache进行了一个包装来实现别的功能
	private HashMap<Object, Object> cache = new HashMap<Object, Object>();
	@Override
	public void putObject(Object key, Object value) {
		cache.put(key, value);
	}

	@Override
	public Object getObject(Object key) {
		return cache.get(key);
	}

	@Override
	public Object removeObject(Object key) {
		return cache.remove(key);
		//移除的元素也是需要记录的,移除相当于是回到数据库了,或者有人购买了
		
	}

	@Override
	public void clear() {
		cache.clear();
	}

	@Override
	public int size() {
		return cache.size();
	}
	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return cache.toString();
		}

}
