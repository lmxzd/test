package com.company.java.oop.instance03;


import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**把这个打印出来的细节搞清楚
 * 	案例分析:对象引用(类似一个指针,指向具体的某个对象)
 * @author soft01
 *	Java中对象的引用类型
 *	1)强引用(此引用引用的对象不会被回收)
 *	2)弱引用(此引用引用的对象在GC(只要触发GC就会被回收)时会被回收)  
 *	3)软引用(此引用引用的对象在内存不足时会被回收,触发GC时不一定会回收)
 *	4)虚引用(此引用没什么用,一般可以记录被回收的对象)
 *完成
 */
class ClassB{
	public void doMethod() {
		System.out.println("doMethod()");
	}
	@Override
		protected void finalize() throws Throwable {
			// TODO Auto-generated method stub
			System.out.println("finalize()");
		}
}
//-Xmx5m -Xms5m -XX:+PrintGCDetails
//触发GC不一定是内存满了才触发,到一个触发值就会触发 
//一个问题finalize()会在弱引用被GC回收时执行两次,解决方案:内存不溢出不会
//另一个问题,软引用被回收的时候GC运行5次,但是finalize()方法只执行3次 ?????
public class TestObjectInstance03 {
	public static void main(String[] args) throws Exception {
		//1.强引用
		ClassB c1= new ClassB();
		//2.弱引用
		WeakReference<ClassB> c2=new WeakReference<ClassB>(new ClassB());
		//3.软引用
		SoftReference<ClassB> c3 =new SoftReference<ClassB>(new ClassB());
		//4.虚引用(PhantomReference-记录被回收的对象)
		ReferenceQueue<ClassB> rq = new ReferenceQueue<ClassB>();
		PhantomReference<ClassB> c4=new PhantomReference<ClassB>(new ClassB(), rq);
		//演示对象不足时的自动GC   
		List<byte[]> list = new ArrayList<byte[]>();
		for (int i = 0; i < 4000; i++) {
			list.add(new byte[1024]);
//			Thread.sleep(10);
			//通过对象get方法可以获取引用的对象  get()方法是获取引用
			if(c2.get()!=null) { //if不加大括号的形式,加分号
				c2.get().doMethod();
			}
//			if(c3.get()!=null) {
//				c3.get().doMethod();
//			}
//			if (c4.get()!=null)c4.get().doMethod(); //虚引用
		}
	}
}
