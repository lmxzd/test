package com.company.java.serializable05;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo 序列化不安全,所以用ThreadLocal保证线程安全
 */
public class KryoUtil {
//	private static final Kryo kyro = new Kryo(); //常量,不可改变,本身它只是作为工具来用的,所以不存在修不修改
	private static final ThreadLocal<Kryo> tLocal = new ThreadLocal<Kryo>() {
		protected Kryo initialValue() {
			return new Kryo();
		};
	};
	public static byte[] serialize(Object obj) {
		Kryo kryo = tLocal.get();//kryo相当于工具类
		kryo.register(obj.getClass());
		//2.1构建流对象(java中默认使用ObjectOutputStream,速度过慢)
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		Output out = new Output(bos);
		//2.2对象序列化
		kryo.writeObject(out, obj);//对某个对象用某个流来序列化
		out.close();
		//2.3释放资源
		return bos.toByteArray();
	}
	public static <T> Object deserialize(byte[] array,Class<T> cls) {
		Kryo kryo=tLocal.get();
	    kryo.register(cls);
	    //2.1构建流对象
	    Input in= new Input(new ByteArrayInputStream(array));
	    //2.2对象反序列化
	    Object obj = kryo.readObject(in, cls);
	    //2.3释放资源
	    in.close();
	    return obj;
	}
	public static void main(String[] args) {
		//1.构建Channel对象
		Channel c  = new Channel();
		c.setId(100);
		c.setName("张东");
		c.setState(1);
		//2.序列化
	}
}
