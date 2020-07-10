package com.company.java.oop.feature04;

import java.util.LinkedHashMap;
/**
 * 设计一个缓存对象 数组(随机访问,速度快)
 * hashmap(通过key,访问速度也快)
 */
/**
 * 构建一个简单的LruCache对象
 * LinkedHashMap
 * 1)存储结构:链表+散列表
 * 2)存储算法:FIFO(先进先出) LRU(最近最少使用算法)((Least recently used)
 * 已完成
 */
class SimpleLruChche extends LinkedHashMap<Object, Object>{
	private static final long serialVersionUID = 1L;
	public SimpleLruChche() {//构造方法不能继承,所以这里来写
		super();
	}
	private int a;
	public SimpleLruChche(int a) {
		//第二个是加载因子,扩容因子
		//第三个是设置访问是否计入影响 默认false FIFO算法 先进先出 只和添加有关系,即只会被添加影响
		//设置为true  访问计入影响,访问也会起到影响. LRU 最近最少使用的放在old端,也是先进先出 
		//当设置为true时,要注意的是:看哪个先出不能只看最后几行,要结合cache大小从头看起
		//如果打印的 话,是按从先到后,也就是从老到新
		super(a,0.75f, true);//看一下这个方法的源码设计,怎么通过设置为true实现LRU算法
		this.a=a;
	}
	/**
	 * 通过如下方法的返回值告诉map对象是否要移除元素
	 * 1)true 表示移除
	 * 2)false表示不移除 父类默认返回值为false 默认是不会移除,是扩容的
	 * 此方法会在put方法执行时调用
	 */
	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<Object, Object> eldest) {
//		return false;//如果是true会放一个移除一个
		if(size()>a)return true;//这里的size()是hashmap中的函数,因为继承了的关系
		return false;
	}
}
public class TestExtends {
	public static void main(String[] args) {
	
		SimpleLruChche cache= new SimpleLruChche (3);
		cache.put("A", 100);
		cache.put("B", 200);
		cache.put("C", 300);
//		cache.put("B", 200);//hashmap中是不允许出现重复值的
		cache.get("B");
		cache.get("A");
		cache.get("C");
		cache.get("C");
		cache.get("C");
		cache.get("A");
		cache.get("B");
		cache.put("D", 400);
		
//		cache.put("D", 400);
//		cache.put("E", 500);
		System.out.println(cache);
	}	
}
