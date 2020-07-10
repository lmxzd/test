package com.company.java.oop.homework;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 分析如下代码,回答如下问题:
 * 1)类的设计是否存在问题?存在什么问题? 线程安全
 * 2)假如存在问题,如何解决这个问题?		
 * 初步分析:
 * SimpleDateFormat对象本身线程不安全(不允许多线程共享)
 * SimpleDateFormat对象应用:
 * 1)取消多线程共享(每次使用就创建对象,不是一个线程只有一个对象)
 * 2)应用时对对象进行加锁(并发会降低,对性能有影响)
 * 3)用ThreadLocal保证一个线程只有一个,且修改可见,禁止重排序 
 * 4)用线程安全的时间处理方法替代SimpleDateFormat(DateFormatter，DateTimeFormatter)
 */
class DateUtils{
	private static ThreadLocal<SimpleDateFormat>  tLocal= new ThreadLocal<SimpleDateFormat>();
	//获取DateUtils对象
	public SimpleDateFormat getInstance() {
		//1.从tLocal中获取当前对象,
		SimpleDateFormat sdf= tLocal.get();
		//2.判断是否为空,为空的话就创建对象并注入tLocal的map对象
		if(sdf==null) {
			tLocal.set(sdf);
		}
		//3.获取对象
		return sdf;
	}
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");

	public static Date parse(String source)throws Exception{
		return sdf.parse(source);
	}
	public static String format(Date date) {
		return sdf.format(date);
	}
}
public class TestDateUtils {
	public static void main(String[] args) {
		DateUtils.format(new Date());
	}
}
