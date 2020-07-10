package com.company.java.oop.feature04;
/**
 * 基于OCP原则Open Close Principle
 * 实现对DefaultMailService的功能扩展
 * 1)继承
 * 2)组合
 * @author soft01
 *完成
 */
interface MailService{
	void send(String msg);
}
class DefaultMailService implements MailService{

	@Override
	public void send(String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
	}
	
}
//基于OCP(开闭原则)通过继承方式(is    a)实现对DefaultMailService对象功能扩展
class TimeMailService extends DefaultMailService{
	@Override
	public void send(String msg) {
		// TODO Auto-generated method stub
		System.out.println(System.currentTimeMillis());
		super.send(msg);
		System.out.println(System.currentTimeMillis());
	}
}

//基于OCP(开闭原则)通过组合方式(has a)(依赖关系)实现对DefaultMailService对象功能扩展
class LogMailService{
	//依赖关系(has a)(有一个),有依赖时尽量依赖于抽象
	private MailService mailService;
//	//通过构造方法为属性赋值(DI)
//	public LogMailService(MailService mailService) {
//		this.mailService=mailService;
//	}
	//通过set方法为属性赋值(DI)
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	public void send(String msg) {
		System.out.println(System.currentTimeMillis());
		mailService.send(msg);
		System.out.println(System.currentTimeMillis());
	}
}

public class TestCompose01 {
	public static void main(String[] args) {
		MailService mailService=new TimeMailService();
		mailService.send("hello");
		//构造方法实现注入(这个地方new了)
//		LogMailService logMailService = new LogMailService(new DefaultMailService());
		//set方法实现注入
		LogMailService logMailService = new LogMailService();
		//在上面直接new会减少代码书写量,但是每次加载都会直接生成对象
		logMailService.setMailService(new DefaultMailService());
		logMailService.send("hello");
	}
//	private static MailService dms;
//
//	public static MailService getDms() {
//		return dms;
//	}
//
//	public static void setDms(MailService dms) {
//		TestCompose01.dms = dms;
//	}
//	public static void main(String[] args) {
//		String msg="abc";
//		MailService dms = new DefaultMailService();
//		TestCompose01.setDms(dms);
//		System.out.println(System.nanoTime());
//		TestCompose01.getDms().send(msg);
//		System.out.println(System.nanoTime());  
	//修改到上面 其实这个也一样的,但是这个在main方法里太多代码了,没有起到复用和封装
//	}
}
