package com.company.java.oop.cls02;

/**
 * 案例分析:演示字节码对象的获取方式和唯一性
 * 类加载分析参数-XX:+TraceClassLoading 可以查看虚拟机类加载过程
 * 1)类的字节码对象何时创建?类加载时
 * 2)类的字节码对象类型?(Class类型)
 * 3)字节码对象的存储区域?(堆区,一个JVM一份)
 * 4)字节码对象关联的内容(类的结构信息,存储在方法区)
 */
public class TestClassObject01 {
	public static void main(String[] args) throws Exception {
		// 获取类的字节码对象,一般情况下只有一个
		//先回加载父类和用到的其它类
		Class<?> c1 = Object.class;
		Class<?> c2 = new Object().getClass();
		Class<?> c3 = Class.forName("java.lang.Object");
		System.out.println(c1 == c2);
		System.out.println(c2 == c3);
	}
}
