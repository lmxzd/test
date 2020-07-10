package com.company.java.oop.instance03;
/**
 * 1. 案例分析:对象的创建时的内存分配
 * 1)堆
 * 2)栈(未逃逸的小对象)
 * @author soft01
 * 2.如何检测对象分配在哪里了?
 * JVM参数配置:
 * -XX:+PrintGC 输出GC基本信息           
 * 3.JDK8中逃逸分析默认开启了吗?开启了
 * -XX:+DoEscapeAnalysis
 * 4.JDK中最大堆,最小堆配置?
 * 	1)最大堆: -Xmx5m
 * 	2)最小堆: -Xms5m
 * 5.JVM参数测试(在堆内存有限的情况下测试JVM逃逸分析)从侧面分析对象分配在栈上(开启逃逸分析就会分配到栈上)会提高性能:
 * 	1)-Xmx5m -Xms5m -XX:	-DoEscapeAnalysis -XX:+PrintGC 关闭逃逸分析 这种就不考虑分配在栈上了
 * 	2)-Xmx5m -Xms5m -XX:+DoEscapeAnalysis -XX:+PrintGC 开启逃逸分析 性能高 但如果是逃逸对象,反而会影响效率,多一次操作
 * 6.-XX:+printFlagsInitial 输出默认参数
 * 	总结:
 * 1.对象创建时有可能分配在堆上也有可能分配在栈上
 * 2.方法内部创建的小对象,并且没有逃逸,在开启逃逸分析的情况下,可能分配在栈上
 * 3.一个线程默认栈大小是1m,用完后就放在堆上
 * 4.默认打开逃逸分析对JVM的执行会有性能的提高(因为栈上分配的对象,不需要启动GC进行回收)
 * 5.设计对象时假如对象不会被多线程共享,多个方法共享,那么此时对象的引用应尽量使用局部变量
 * 完成
 */	
public class TestObjectInstance01 {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
				
		for (int i = 0; i < 1000000; i++) {
			alloc();
		} 
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	//jdk 6以后,HotSpot虚拟机已经支持运行时对对象进行逃逸分析
	//如何理解对象逃逸?方法内创建的对象没有被外界引用,称之为未逃逸,反之亦凡
	//现阶段的jdk中未逃逸的小对象可能会分配在栈上
	static byte[] array;
	static void alloc() {
		//创建一个只能存储一个字节的对象
		array =new byte[1];
		array[0]=10;
	}
}
