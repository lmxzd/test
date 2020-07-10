package com.company.java.generic05;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 限定通配符应用:方法参数或返回值
 * @author soft01
 *	泛型相当于是方法参数的修饰
 *	完成
 */
class PrintUtil{
	//上界限定通配符
	public static void doPrint(List<? extends CharSequence> list) {
		//List集合中只能存放上界为CharSequence的元素
		System.out.println(list);
	}
	//下界限定通配符
	public static void doPrint(Set<? super Integer> set) {
		System.out.println(set);
	}
}
public class TestGeneric04 {
	public static void main(String[] args) throws ClassNotFoundException {
		Class<String> c1=String.class;
		//"?"在这里表示通配符,代表一种实际参数
		//?只能用于变量的定义上
		//这里写Object都不行,因为Object它识别不了,这里的Object只是被看做一种大的类型,但是不是万物皆Object
		Class<?> c2=Class.forName("java.lang.Object");
		List<String> list1 = new ArrayList<String>();
		list1.add("100");
		Set<Number> list2=new HashSet<Number>();
		list2.add(200);
		PrintUtil.doPrint(list1);
		PrintUtil.doPrint(list2);
	}
}
