package com.company.java.generic05;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
/**
 * 泛型方法应用
 * 特点:修饰符 <泛型> 返回值类型 方法名(参数列表)
 * @author soft01
 * 完成
 */
class ObjectFactroy{
	//泛型方法,Class<T> 这个是类泛型 修饰Class 这个是指字节码对象可以为任意类型(Class底层有一个<T>)
		public <T> T newInstance(Class<T> cls) throws Exception {
			return cls.newInstance();
		}
		/**判定一个对象是否是Collection类型*/
		public <T> boolean isCollection(Class<T> cls) {
			return Collection.class.isAssignableFrom(cls);
		}
}
class ContainerUtils <E>{
	public  static <E> void sort(List<E> list) {}
	public void show(List <E> list) {}
}
public class TestGeneric03 {
	public static void main(String[] args) throws Exception {
		ObjectFactroy of = new ObjectFactroy();
		Date date = of.newInstance(Date.class);
		System.out.println(date);
		 boolean bl = of.isCollection(List.class);
		 System.out.println(bl);
	}
}

