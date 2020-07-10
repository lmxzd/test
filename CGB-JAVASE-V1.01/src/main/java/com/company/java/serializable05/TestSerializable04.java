package com.company.java.serializable05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
/**
 * 对Channel对象进行高效率的序列化(采用kryo框架)序列化框架(注册,序列化数据,不序列化元数据)
 * @author soft01
 * 完成
 */
class Channel implements Serializable{
	private static final long serialVersionUID = -5996516514113734906L;
	private int id;
	private String name;
	private int state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", state=" + state + "]";
	}
}
public class TestSerializable04 {
	public static void main(String[] args) throws Exception {
		//1.构建Channel对象
		Channel c = new Channel();
		c.setId(100);
		c.setName("新闻");
		c.setState(1);
		//2.构建Kryo对象
		Kryo kryo = new Kryo();
		//注册类对象(目的是为类分配一个唯一ID,对象序列化的时候就不用再写完整的全局限定名了)
		kryo.register(Channel.class);
		//3.1构建流对象(Java中默认是使用ObjectOutputStream)
		Output out = new Output(new FileOutputStream("f1.dat"));
		//3.2对象序列化
		kryo.writeObject(out, c);
		out.close();
		byte[] b1 = out.toBytes();
		System.out.println(b1);
		//4.对字节进行反序列化
		//4.1构建流对象
		Input in = new Input(new FileInputStream("f1.dat"));//这个存在内存里本身就是字节的形式,所以可以一直反序列化创建对象
		//4.2字节反序列化
		Channel c2 = kryo.readObject(in,Channel.class);
		in.close();
		System.out.println(c2);
	}
}
