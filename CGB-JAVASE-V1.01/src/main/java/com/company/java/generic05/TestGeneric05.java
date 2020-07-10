package com.company.java.generic05;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 案例分析:类型擦除和反射机制的使用
 * 说明了在运行阶段所有泛型均变成Object,即所有泛型都没用,失去作用
 * 反射机制,用字节码对象获取获取方法对象,然后对方法对象进行回调(执行)
 * @author soft01
 *	通过反射技术在运行时将200这个整数存储到集合
 *完成
 */
public class TestGeneric05 {
	public static void main(String[] args) throws Exception{
		List<String> list = new ArrayList<String>();
		list.add("100");
		list.add("abc");
		Class<?> cls = list.getClass();
		Method method=cls.getMethod("add",int.class, Object.class);
		method.invoke(list,0, 200);
		System.out.println(list);
	}
}
