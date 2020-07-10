package com.company.java.oop.instance03;

/**
 * 如何保证Looper对象,在被多个线程访问时,每个线程一份 这个是共享问题,问老师了,看老师怎么解决??
 * (因为ThreadLocal是线程安全的,所以只有一个)
 * (在外部大的看还是多例) 说明:此设计为一种线程内部单例设计(可以借助ThreadLocal)
 * ThreadLocal应用:它提供了一种线程绑定机制,可以将某个对象绑定到当前线程, 
 * 也可以从当前线程获取某个对象 
 * 多例对象直接new就可以了
 * 基本完成
 */
//这个源码看一下  hashmap也是线程安全的吗?不是
class Looper {
	private Looper() {
		System.out.println("Looper()");
	}
	
	private static ThreadLocal<Looper> tLocal = new ThreadLocal<Looper>();
	/**获取Looper对象*/
	public static Looper getLooper() {
		//1.从当前线程获取looper对象
		//(get方法做了什么)请问从当前线程的什么地方取looper
		/*1)获取当前线程对象
		 * 2)获取当前线程中的Map对象:ThreadLocalMap 
		 * 3)检查map对象是否存在,不存在则创建
		 * 4)存在则从map获取Looper对象,key为ThreadLocal对象(具体则是tLocal)
		 */
		Looper looper = tLocal.get();//F7是退回上一个类
		//2.若没有looper对象,则创建对象并与线程绑定
		if (looper == null) {
			//(set方法做了什么)绑定到线程(存储到ThreadLocalMap)
			/*1)获取当前线程对象
			 * 2)获取当前线程中的Map对象,ThreadLocalMap
			 * 3)将ThreadLocal对象(tLocal)作为key,Looper对象作为value存到Map中
			 */
			tLocal.set(new Looper());
		}
		//3.返回looper对象
		return looper;
	}
}

public class TestObjectInstance06 {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread() {
				public void run() {
					Looper.getLooper();
					Looper.getLooper();
					Looper.getLooper();
				};
			}.start();
		}
	}
}
