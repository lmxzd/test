package com.company.java.oop.cls02;
/**
 * 案例分析:通过类加载器对类进行显式加载
 * @author soft01
 *
 */
//-XX:+TraceClassLoading
class ClassA {
	/*
	 * 	类加载过程中静态代码可以执行,但也不是一定执行,如果初始化就会执行,但是不初始化就不会执行
	 */
	static {
		System.out.println("ClassA.static{}");
	}
}
public class TestClassObject04 {//public修饰的这个类是怎么加载的?
	public static void main(String[] args) throws Exception {
		ClassLoader loader = ClassLoader.getSystemClassLoader();
//		loader.loadClass("com.company.java.oop.cls02.ClassA");//这种方法可以加载类,不初始化类
		//这种方法会加载类中的静态代码块,这个是默认方法,默认会初始化,加载器选择默认加载器
//		Class.forName("com.company.java.oop.cls02.ClassA");
		//第一个参数是类的全路径,第二个参数是是否初始化,第三个参数是加载器的选择
		Class.forName("com.company.java.oop.cls02.ClassA", false, loader);
//		Class.forName("com.company.java.oop.cls02.ClassA", true, loader);
	}
}
