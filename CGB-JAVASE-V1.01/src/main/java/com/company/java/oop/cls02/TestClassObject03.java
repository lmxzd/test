package com.company.java.oop.cls02;

/**
 * 案例:分析类加载器?
 * 1)类的加载由类加载器负责?
 * 2)类加载器默认按怎样的规则加载类?(双亲委派模型:源码中的LoaderClass 保证类的字节码只被加载一次)
 * 		具体双亲委派的加载机制可以参考ClassLoader类的LoderClass方法
 * 3)我们如何定义类加载器?(直接或间接继承ClassLoader类) 为什么AppClassLoader继承于URLClassLoader
 * 3.1)参考tomcat,spring,mybatis中定义的类加载器
 * 3.2)思考为什么框架中要自己写类加载器 一种原因是字节码增强,另外比如加密解密,
 * 	热部署:在线升级,在加载时将原本的Class替换为需要的Class,等有特殊项目要求,进行自定义类加载器
 */
public class TestClassObject03 {
	public static void main(String[] args) {
		//1.AppClassLoader(java,应用类加载器,自己写的类基本都是这个加载) 随着版本升级ClassLoader也会变化
		ClassLoader loader01 = ClassLoader.getSystemClassLoader();
		System.out.println(loader01);//tostring 名字加地址
		//2.ExtClassLoader (java,扩展类加载器,负责加载jre\lib\ext\*.jar)
		//这个getparent不是继承关系,而是有个parent的成员变量,伪继承,真组合
		ClassLoader loader02 = loader01.getParent();
		System.out.println(loader02);
		//3.BootstrapClassLoader(根类加载器,c写的,这个类对象是拿不到的 负责加载jre\lib\rt.jar)
		ClassLoader loader03 = loader02.getParent();
		System.out.println(loader03.getClass());
	}
}
