package com.company.java.oop.instance03;
/**
 * 	案例分析:对象设计(设计一个单例对象:首先要确保的是线程安全,只有线程安全才是创建单例,常见的
 * 线程非安全是由不是原子性操作导致的)
 * 	单例对象设计?类的实例对象再一个JVM内存中只有一份
 * 1)对类自身进行设计?(构建方法私有化,外界不能直接构建对象)
 * a)构建方法私有化
 * b)提供共有方法,获取单例对象
 * 2)基于外部环境进行设计(对象创建以后存储到池中,需要时从池中获取)
 * a)提供池对象
 * b)将创建的对象放入池中
 * c)需要时从池中取
 * @author soft01
 *	线程不安全的单例设计问题?
 *1)如何理解线程不安全?多线程并发执行时导致了数据的不正确性
 *2)导致线程不安全的因素?
 *	a)存在多个线程并发执行
 *	b)多个线程存在共享数据
 *	c)多个线程在共享数据上的操作不是原子操作
 *2)如何保证线程安全?
 *	a)原子操作:借助于锁synchronized,性能降低
 *	b)加锁,也就是把非原子性操作锁为原子性操作
 *	//那线程里面的那个锁是怎么回事呢?(就那个线程各种状态的图) 
 *	c)取消共享,但是这种不能并发执行了
 */
/**
 * 	线程非安全的懒汉单例设计
 * 懒汉特点:对象何时需要何时创建
 * 应用场景,单线程 
 * 完成
 */
class Singleton01{
	private Singleton01() {
		//为什么多线程比较不可以直接比较线程引用?线程对象是两个地址肯定不一样,所以这里用构造
		//方法打印几次的方法,且run方法返回值为void所以不能设置引用比较引用
		System.out.println("Singleton01");
	}
	private static Singleton01 instance;
	//这个方法是运用private的特性,不让直接new对象,只能通过这个方法
	public static Singleton01 getInstance() {
		if(instance==null) {
			instance = new Singleton01();
		}
		return instance;
	}
}
/**
 * 	线程安全懒汉单例设计:思路保证方法操作的原子性,
 * 	借助synchronized进行实现,但性能下降
 * 应用场景:大对象,可以频繁访问,并发量很小的场景
 */
class Singleton02{
	private Singleton02() {
		System.out.println("Singleton02");
	}
	private static Singleton02 instance;
	/**同步方法:线程安全,性能下降
	 */
	public static synchronized Singleton02 getInstance() {
		if(instance==null) {
			instance = new Singleton02();
		}
		return instance;//多例,利用private的特性
	}
}
/**
 * 双重判断懒汉单例:实现对Singleton02的优化
 *应用场景:大对象,稀少用,可以线程并发(并发量不太大的场景)
 */
class Singleton03{
	private Singleton03() {
		System.out.println("Singleton03");
	}
	/**
	 * 	volatile 用于修饰属性:
	 * 1)可以保证对象修改时的可见性  CPUcache中是不可见的,volatile可以禁用cache,直接让cpu操作
	 * 内存,因为操作内存是一样的,所以对象修改时对另一个CPU是可见的,而修改操作会使得另一个CPU
	 * 阻塞
	 * 2)可以禁止指令重排序(这个地方就是用的这个性质) 
	 * volatile修饰的变量会禁止JVM进行指令重排序,严格按本身的顺序执行,不会出现一些意外情况
	 *  因为可见了,所以别的线程进来就会看到已经有对象了,所以就会直接return了,但是还没有初始化
	 *  暂时这么理解吧
	 * 3)不能保证原子性(还要用synchronized保证其原子性)
	 */
	private static  volatile Singleton03   instance;
	/**同步方法:通过双重验证,减少阻塞次数,提高性能,但是这个有问题,会判断两次,
	 * 	且也有可能会阻塞较多线程   synchronized(Singleton03.class)是什么意思 锁读到内存中的这个类
	 * (这个就是体现是否是好代码了)
	 */
	public static Singleton03 getInstance() {
		if(instance==null) {
			synchronized (Singleton03.class) {
				if(instance==null) {
					instance = new Singleton03();
					//1)分配内存
					//2)初始化属性,执行构造方法
					//3)将对象赋值给instance
				}
			}
		}
		return instance;//多例,利用private的特性
	}
}
/**
 * 饿汉单例:
 * 类加载时则创建对象实例(饿汉式)
 * 应用场景:小对象,频繁用,可以高并发
 * 类加载时会初始化各项静态变量和非静态变量,那方法会初始化吗??方法会怎么执行呢?会解析,方便方法的调用
 */
class Singleton04{
	//int[] array =new int[1024*1024*1024];
	private Singleton04() {
		System.out.println("Singleton04");
	}
	/**对象在类加载时初始化,*/
	private static final Singleton04 instance=new Singleton04();
	public static Singleton04 getInstance() {
		return instance;
	}
}
/**
 * 基于内部类实现饿汉单例:懒加载的饿汉式(两种都兼并到了,所以可以用大对象,也可以频繁用)
 * 特点:延迟对象创建,减少资源占用,提高系统性能
 * 应用场景:大对象,频繁用,可以线程高并发
 */
class Singleton05{
	private Singleton05() {
		System.out.println("Singleton05");
	}
	static class Inner{//内部类用到才会加载(初始化)
		private static final Singleton05 instance=new Singleton05();
	}
	public static Singleton05 getInstance() {
		return Inner.instance;//这样用静态资源会比较多,方法区可能会内存溢出 因为静态方法都是放在方法区的
	}
//	public static void show() {}//这种情况访问show时不会创建对象
//	public void display() {}//这种情况下访问display因为要用getInstance()创建对象,所以会访问到内部类进而创建对象

}
/**
 * 基于枚举实现的饿汉单例
 * 类加载时创建,且创建一次
 */
enum Singleton06{//特殊的类,也会编译为.class,当加载的时候就会创建实例
	instance;//一个实例(类加载时创建) 
	public void show(){}//想访问show() 需要用Singleton06.instance.show()枚举的性质
}

public class TestObjectInstance04 {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Thread t1 = new Thread() { //在主线程中生成子线程的写法
			public void run() {
				Singleton03.getInstance();
			};
		};
		for (int i = 0; i < 10000; i++) {
			
		
		new Thread() {
			public void run() {
				Singleton03.getInstance();
			};
		}.start();;
		}
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		t1.start();
		t3.start();
		long end = System.currentTimeMillis();
				System.out.println(end-start);
	}
}
