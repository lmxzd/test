package com.company.java.oop.instance03;
/**
 * 	案例分析:演示对象销毁
 * @author soft01
 * 完成
 */

//通常把最大堆和最小堆一样大小,因为不然最小堆就会一直改变大小
//-Xmx5m -Xms5m -XX:+PrintGCDetails
class ClassA{
	/**对象在回收执行会执行此方法*/
	@Override
	protected void finalize() throws Throwable {//重写就执行重写的,没有就执行object的对象在回收执行前会执行此方法
		// TODO Auto-generated method stub
		System.out.println("finalize()");
	}
}
public class TestObjectInstance02{
	public static void main(String[] args) {
		//演示:手动启动GC
//		new ClassA();//这种会启动GC
//		ClassA a = new ClassA();//这种是不会启动GC的,
//		System.gc();
		//演示:自动启动GC,内存不足时可能会触发
		byte[] byte1=new byte[1024*1024];//1m 大对象直接放堆里
		byte[] byte2=new byte[1024*1024];
		byte[] byte3=new byte[1024*1024];
		byte[] byte4=new byte[1024*1024];
		byte[] byte5=new byte[1024*1024];
	}
}
