package com.company.java.oop.feature04;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 构建一个cache对象,通过组合的方式实现以下功能
 * 1)存储结构:散列表
 * 2)存储算法:FIFO(first in first out)
 */
class SimpleFifoCache{
	private int maxCap;
	public SimpleFifoCache(int maxCap) {
		this.maxCap=maxCap;
	}
	/**通过此map存储对象*/
	//满足先进先出就可以了,打印出来的顺序,具体怎么排的不用管,hashmap不能保证怎么排的,
		//但是用了likedlist就可以保证先进先出
	private Map<Object,Object> cacheMap=new HashMap<Object,Object>();
	
	/**通过此 队列 集合记录元素顺序*/
	private Deque<Object> keyOrders=new LinkedList<Object>();
	
	public Object getObject(Object key) {
		//初步的尝试实现  最近最少使用
//		keyOrders.getLast();
//		keyOrders.
//		Object oldkey=keyOrders.removeFirst();
//		cacheMap.remove(oldkey);
//		cacheMap.put(key,cacheMap.get(key));
		return cacheMap.get(key);
	}
	
	/**存储对象*/
	public void putObject(Object key, Object value) {
		//将算法分解为小步骤,逻辑思维能力是需要培养的,多学算法
		//1.记录Key对象(入队操作,放在队尾)
		keyOrders.addLast(key);//
		
		//2.判断容器是否已满,满了则移除容器中最早放入的entry
		if(cacheMap.size()==maxCap) {
			//2.1队列中key进行(出队操作,移除队首) 	先进先出
			Object oldkey=keyOrders.removeFirst();
			//2.2移除这个key的entry节点
			cacheMap.remove(oldkey);
		}
		
		//3.添加key/value
		cacheMap.put(key, value);
	}
	@Override
	public String toString() {
		return cacheMap.toString();
	}
}
public class TestCompose02 {
	public static void main(String[] args) {
		SimpleFifoCache cache = new SimpleFifoCache(3);
		cache.putObject("A", 100);
		cache.putObject("B", 200);
		cache.putObject("C", 300);
		cache.putObject("D", 400);
		cache.putObject("A", 100);
		cache.getObject("A");
		System.out.println(cache);
		}
}
