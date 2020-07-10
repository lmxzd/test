package com.company.java.cache04;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 缓存对象,其特点
 * 1)存储结构:散列表
 * 2)淘汰算法FIFO先进先出:利用双向链表的先进先出特性
 * @author soft01
 *基本完成
 */
public class FifoCache implements Cache {
	//用链表来记录顺序,用链表中的addLast和removefirst方法来实现先进先出
	//用储存的方式来记录顺序
	//移除的链表中的最老的元素,就是用链表的先进先出特性来实现散列表的先进先出
	private Deque<Object> keyOrders= new LinkedList<Object>();
	private Cache cache;
	private int maxCap;
	public FifoCache(Cache cache,int maxCap) {
		this.cache=cache;
		this.maxCap=maxCap;
	}
	@Override
	public void putObject(Object key, Object value) {
		//1.存储key
		keyOrders.addLast(key);
		//2.满了移除对象
		if(size()==maxCap) {
			Object oldkey=keyOrders.removeFirst();
			cache.removeObject(oldkey);
		}
		//3.存储key/value
		cache.putObject(key, value);
	}

	@Override
	public Object getObject(Object key) {
		return cache.getObject(key);
	}

	@Override
	public Object removeObject(Object key) {
		return cache.removeObject(key);
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
		return "FifoCache [" + "cache=" + cache + "]";
		//这个打印说明了List集合可以存相同元素,但是Map中不可以存相同元素,结合下面main方法被注释掉的put操作
//		return "FifoCache [keyOrders=" + keyOrders + ", cache=" + cache + "]";
	}
	public static void main(String[] args) {
		FifoCache fifoCache = new FifoCache(new PerpetualCache(),3);
		fifoCache.putObject("A", 100);
//		fifoCache.putObject("A", 100);//结合上面被注释掉的打印
//		fifoCache.putObject("A", 100);
		fifoCache.putObject("B", 200);
		fifoCache.putObject("C", 300);
		fifoCache.putObject("D", 400);
		System.out.println(fifoCache);
	}
}
