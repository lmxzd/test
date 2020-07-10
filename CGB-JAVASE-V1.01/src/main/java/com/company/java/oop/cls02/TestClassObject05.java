package com.company.java.oop.cls02;
/**
 * 	触发类加载的时机(隐式加载:不是直接通过类加载器进行加载的):相应的会初始化
 * 	1)访问类的静态属性?(分情况)
 * 	1.1)访问使用static final 修饰的八种基本类型和String类型时,不会触发类加载(默认有编译优化)String有常量池,但不是因为常量池的原因
 * 	所以设置静态变量能使用final就使用,底层会进行编译优化
 * 	1.2)访问只有static 修饰的任意属性时的都会触发类加载.
 * 	2)访问类的静态方法 ,会触发,带final的呢?也会触发
 * 	3)访问类的对象(new类的时候)
 *
 */
class ClassB {
	static int [] array = new int[1024];
	static final Integer b=100; //基本类型的包装类型都不可以,无论是不是在-128~127之间
	static final int a =200;
	static final String c= "c";
	static final Double d=20.0;
	static final void doMethod01() {
		System.out.println("ClassB.doMethod01");
	}
	//还有一些例子
}
//-XX:+TraceClassLoading 
public class TestClassObject05 {
	//访问main方法会加载
	public static void main(String[] args) {
//		ClassB c1;//以类定义一个变量时不会触发类加载
//		int[] array = ClassB.array;//会触发类加载,也就是说会初始化
//		System.out.println(array);
//		int a = ClassB.a;//不会触发
//		System.out.println(a);
//		Integer b = ClassB.b;//会触发
//		System.out.println(b);
//		int b = ClassB.b;
//		System.out.println(b);//也会触发,说明和接收的无关,而要看刚开始设置的是什么
		ClassB.doMethod01();//会触发
//		new ClassB();//会触发
	}
}
