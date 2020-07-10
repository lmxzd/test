package com.company.java.oop.cls02;
/**
 * 案例分析:考察类的被动加载
 * @author soft01
 *
 */
class ClassE{
	static int a =100;
	static {
		System.out.println("ClassE.static{}");
	}
}

class ClassF extends ClassE{
	static int b=200;
	static {
		System.out.println("ClassF.static{}");
	}
}

public class TestClassObject08 {
	public static void main(String[] args) {
		//ClassE 为主动加载
		//ClassF 为被动加载(不执行static初始化操作) 子类中访问父类的属性
		System.out.println(ClassF.a);
		System.out.println(ClassF.b);
	}
}
