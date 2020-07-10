package com.company.java.serializable05;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 案例分析:对象序列化和反序列化 
 * 1)将一个Message对象序列化 
 * 2)从文件中反序列化出对象
 * 反序列化出的每个对象都是不一样的(但是每个只能对应一次)
 * @author soft01
 *完成
 */

class Message implements Serializable {
	//根据类的结构信息生成 如果不写,类结构变化,值就变化,每个存储数据的类都会有这个值
	private static final long serialVersionUID = -7645525381855434378L;
	private int id;
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Message[id=" + id + ",content=" + content + "]";
	}
	
}

public class TestSerializable01 {
	public static void main(String[] args) throws Exception {
		Message msg = new Message();
		msg.setId(100);
		msg.setContent("hello cgb1907");
		// 将如上对象序列化到文件(写) 序列化是写,反序列化是读
		// 1.构建流对象
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("f1.dat"));//默认存在于当前工作空间
		// 2.将对象序列化
		out.writeObject(msg);
		// 3.释放资源
		out.close();
		System.out.println(out);//流对象即使关闭,依然可以使用其中的方法,只是没有传输功能(传输功能的方法)了
		System.out.println("序列化完成");
		// 将文件中的内容反序列化(读)
		// 1.构建流对象
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("f1.dat"));
		// 2.将内容反序列化为对象
		Object obj1 = in.readObject();
//		Object obj2 = in.readObject();
		// 3.释放资源
		in.close();
		System.out.println(msg == obj1);
		System.out.println(msg.equals(obj1));
		System.out.println(obj1);
	}

}
