package com.company.java.cache04;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化Cache 
 * 转换为字节数组
 * @author soft01
 *要先知道怎么做,api去网上查
 *完成
 */
public class SerializableCache implements Cache {
	/** 通过此Cache存储数据*/
	private Cache cache;
	public SerializableCache(Cache cache) {
		this.cache= cache;
	}
	
	/**负责序列化*/
	//序列化就是把对象以字节数组的形式存到Cache中
	//(Cache的话是模拟的缓存,但是实际没有实现连接数据库)看一下mybatis怎么连接的数据库??
	private byte[] serialize(Object object) throws IOException {//关外层流内层流自动关闭
		//1.构建字节数组输出流对象
		ByteArrayOutputStream bos = new ByteArrayOutputStream();//这个输出流是节点流吗?是
		//2.构建对象输出流
		ObjectOutputStream oos = new ObjectOutputStream(bos);//这个是处理流
		//3.执行对象序列化
		oos.writeObject(object);//执行了这个就完成序列化了,下面的return的是
		//4.释放资源  流对象释放资源之后还可以拿到其中的东西吗?可以
		oos.close();
		System.out.println(oos);
		return bos.toByteArray();
	}
	/**负责反序列化
	 * @throws Exception
	 *  */
	private Object deserialize(byte[] array)throws Exception{
		//1.构建字节数组输入流对象(此对象负责从数组读数据)
		ByteArrayInputStream is = new ByteArrayInputStream(array);
		//2.构建对象输入流(负责将字节转换为对象)
		ObjectInputStream os = new ObjectInputStream(is);
		//3.执行对象序列化
		Object obj = os.readObject();
		//4.释放资源
		is.close();
		os.close();
		return obj;
	} 
	@Override
	//key不会变化,传进来怎样就是怎么样,主要是value的处理
	//map对象中的key只是一个hash值(无论是什么,都转换为hash),value是真实可用的对象
	public void putObject(Object key, Object value) {
		//1.将对象序列化(字节数组)
		try {
			byte[] array=serialize(value);
		//2.将字节对象存储到cache
			cache.putObject(key, array);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);//因为实现的是接口中方法,所以将检查异常转换为运行时异常,不用Throws接收
		}
	}
	@Override
	public Object getObject(Object key) {
		//1.从Cache中获取对象(字节数组)
		byte[] array =(byte[]) cache.getObject(key);//可以直接这样转到byte
		//2.将字节数组反序列化为对象
		try {
			Object obj = deserialize(array);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);//将检查异常转换为运行时异常
		}
	}
	@Override
	public Object removeObject(Object key) {
		// TODO Auto-generated method stub
		return cache.removeObject(key);
	}
	@Override
	public void clear() {
		cache.clear();
	}
	@Override
	public int size() {
		return cache.size();
	}

}
