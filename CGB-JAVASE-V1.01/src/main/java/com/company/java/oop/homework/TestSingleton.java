package com.company.java.oop.homework;


/**
 * 如何对Singleton类进行设计,才能保证:
 * 1)此类对外只有一个实例
 * 2)此类对象可以在高并发环境下频繁使用.
 * 3)此类的对象在本类加载时不会创建实例
 * 已完成
 */
class Singleton{
	private Singleton() {
		System.out.println("Singleton()");
	}
	static class Inner{
		private static final Singleton instance=new Singleton();
	}
	public static Singleton getInstance() {
		return Inner.instance;
	}
	
}
public class TestSingleton {
	public static void main(String[] args) {
		for (int i = 0; i <5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Singleton.getInstance();
				}
			}).start();
		}
	}
}
