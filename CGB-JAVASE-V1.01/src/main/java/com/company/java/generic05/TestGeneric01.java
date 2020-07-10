package com.company.java.generic05;

import java.util.ArrayList;

/**
 * 一些泛型的使用规范,通过上面这些题来看,基本完成
 * 1.分析如下泛型定义是否正确
 * //泛型尖括号里面可以设置为任意值,这个任意值相当于一个形式
 * ,除非是String Object这种本身有意义的类型
 * 1)class A<Parameter>{} 
 * 2)interface B<Param,Result>{} //像这个就只是一个形式
 * 3)class C{
 * 			public <T> void doMethod(T t){}
 *		}
 *上面三个均是正确的
 *4)class D<T> {//错误,类上的泛型不作用于静态方法
 *			static void doMethod(T t){}
 *		}
 * @author soft01
 *2.分析如下代码,检查代码是否存在问题?
 *class A<T>{}
 *class B extends A<T>{}//错误
 *class C <T> extends A<T>{}//正确
 *class E extends A<String>{}//正确  为什么正确呢?固定的子类就可以不写
 *3.分析如下代码,检查代码是否有问题?
 *interface A<K,V>{}
 *class B implements A <K,V>{}//错误
 *class C<K,V> implements A <K,V>{}//正确
 *class D implements A<String,Object>{}//正确
 *class E<K> implements A<K,Object>{}//正确
 *4..分析如下代码,检查代码是否有问题?
 *List<Object> list = new ArrayList<String>();//错误
 *List<String> list = new ArrayList<Object>();//错误
 *List<? extends Object> list = new ArrayList<String>();//正确
 *List<? super String > list = new ArrayList <Object>();//正确
 */
//泛型接口(接口定义时使用泛型用于约束方法参数类型,返回值类型)
interface Container<T>{
	void add (T t);
	T get(int i);
	int size();
}
/*泛型类:当使用一个类实现泛型接口时(抽象类情况和一般类时一样的)
 * 1)类定义时要么也声明为泛型类(和接口保持一致)
 * 2)类定义时要么为接口传入具体类型
 */
//情况1:
abstract class  AbsContainer<T> implements Container<T>{
	@Override
	public int size() {
		return 0;
	}
}
//情况2:
class ArrayContainer extends AbsContainer<Integer>{
	private Integer[] array;
	public ArrayContainer() {
		array = new Integer [15];
	}
	@Override
	public void add(Integer t) {
		//...
	}
	@Override
	public Integer get(int i) {
		return null;
	}
}
/*
 * 
 */

public class TestGeneric01 {
	public static void main(String[] args) {
		ArrayContainer c = new ArrayContainer();
		c.add(100);
	}
}
