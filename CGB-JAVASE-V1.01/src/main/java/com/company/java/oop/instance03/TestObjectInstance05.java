package com.company.java.oop.instance03;

import java.util.HashMap;
import java.util.Map;

/** 基于外部环境进行设计(对象创建以后存储到池中,需要时从池中获取)
* a)提供池对象
* b)将创建的对象放入池中
* c)需要时从池中取
* 完成
*/
//这个Demo中为什么默认是单例,单例和线程并发问题结合起来 ???设计单例也是设计高并发的一种,以后的可以类似设置
class A{
	public A() {
		System.out.println("A{}");
	}
}
class B{
	public B() {
		System.out.println("B{}");
	}
}
class ObjectContainer{//思考Spring框架中的对象单例
	//相当于对象池
	public static Map<String, Object> objMap=new HashMap<String,Object>();
	//初始化时向池中储存对象
	static {
		objMap.put("key01", new A());
		objMap.put("key02", new B());
	}
	//从池中获取对象
	public static Object get(String key) {
		return objMap.get(key);
	}
}
public class TestObjectInstance05 {
	public static void main(String[] args) {
		Object a2 = ObjectContainer.get("key01");
		Object a1 = ObjectContainer.get("key01");//这里怎么用构造方法,不用比较的方法??
	}
}
