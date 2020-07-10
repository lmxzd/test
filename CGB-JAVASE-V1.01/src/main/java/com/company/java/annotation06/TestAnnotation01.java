package com.company.java.annotation06;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
/**
 * 注解的简单Demo
 * @author soft01
 *完成
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Select{
	String value();
}
interface GoodsDao{
	@Select("select * from goods where id = #{id}")
	Object findById(Integer id);
}
public class TestAnnotation01 {
	public static void main(String[] args) throws Exception {
		//获取GoodsDao接口中的FindById方法上的@Select注解内容
		//1.获取GoodsDao接口的字节码对象
		Class<GoodsDao> gd = GoodsDao.class;
		//2,获取接口中的findById方法对象
		Method method = gd.getMethod("findById", Integer.class);
		//3.获取方法上的@Select注解对象
//		Select cs = method.getDeclaredAnnotation(Select.class);
		Select cs = method.getAnnotation(Select.class);
		//4.获取注解上的Value属性的值
		String ve = cs.value();
		System.out.println(ve);
	}
}
