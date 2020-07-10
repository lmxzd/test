package com.company.java.oop.cls02;

import java.util.Arrays;
//类的设计:属性延迟初始化(使用次数相对较少,但占用空间较大)
class ClassC {
	//使用内部类设计,对数组对象进行初始化
	static class Inner {
		static int[] array = new int[1024 * 1024];
	}

	static void doMethod01() {
		System.out.println("ClassC.doMethod01()");
	}

	static void doMethod02() {
		//这里可以直接使用Inner是因为内部类相当于是一个属性,静态的可以拿
		System.out.println(Arrays.toString(Inner.array));
	}
}

public class TestClassObject06 {
	public static void main(String[] args) {
		//如果这个方法调用很多,用内部类设计就会较好,不然每次调用方法01都会新建数组,浪费空间
		ClassC.doMethod01();
	}
}
