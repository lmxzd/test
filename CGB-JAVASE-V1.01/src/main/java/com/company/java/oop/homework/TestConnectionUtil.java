package com.company.java.oop.homework;
/**
 * 问题:
 * 每天学一个算法,多些练习,多些代码,没时间怎么办?
 * 1)Jdbc中的Connection对象可以多线程共享吗? 不可以,connection来控制事务
 * 2)Connection对象在多线程中如何应用?一个线程一个连接
 * 基本完成
 */
class Connection{
	public  Connection() {
		System.out.println("Connection()");
	}
}//模拟JDK中的Connection
class ConnectionUtil{
	//这里不是线程池,只是一个本地线程
	private static ThreadLocal<Connection> tLocal = new ThreadLocal<Connection>() ;
//	{//加大括号说明是ThreadLocal的子类对象
//		//此方法何时调用?
//		//当从当前线程获取绑定对象 时,假如当前线程没有绑定的对象,则调用此方法创建对象并绑定到当前线程(这里用的是构造方法)
//		protected Connection initialValue() {
//			return new Connection(); //重写完这个方法就把set那一步也做了  ??多看几遍
//		};
//	};
	public static Connection getInstance() {
		return tLocal.get();
	}
}

public class TestConnectionUtil {
	public static void main(String[] args) {
//		//主线程默认有map了,所以获取当前线程的时候用的是主线程
		
		ConnectionUtil.getInstance();//这里也是一个线程调用一个对象
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
//				ConnectionUtil.getInstance();
//				ConnectionUtil.getInstance();
				ConnectionUtil.getInstance();
				ConnectionUtil.getInstance();//这里只是一个同样的线程多次调用对象
			}
		}).start();
	}
}
