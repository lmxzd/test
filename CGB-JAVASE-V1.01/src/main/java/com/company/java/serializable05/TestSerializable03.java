package com.company.java.serializable05;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * 重写序列化方法,加密对象中的某些属性
 * 一般序列化的话是在pojo类内,必须写writeObject 和readObject 按照官方指定标准定义
 * @author soft01
 *完成
 */
/**问题答复对象*/
class Replay implements Serializable{
	private static final long serialVersionUID = -6547602555716911192L;
	/**唯一标识*/
	private int id;
	/**回复内容*///只加密这一个属性
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
		return "Replay [id=" + id + ", content=" + content + "]";
	}
	/**
	 * 自定义序列化过程:按照官方指定标准定义writeObject方法
	 * @param os
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream os)throws IOException{
		//1.获取加密对象(借助JDK中的Base64工具类)
		Encoder encoder = Base64.getEncoder();
		//2.对内容进行加密
		byte[] result = encoder.encode(content.getBytes());//将需要加密的String类型转换为字节数组才可以进行加密
		content = new String(result);//将加密过的对象再转换为字符串
		//3.对内容进行序列化
		os.defaultWriteObject();
	}
	/**
	 * 自定义反序列化过程:按照官方指定标准定义readObject方法
	 * @param is
	 * @throws Exception
	 */
	private void readObject(ObjectInputStream is)throws Exception{
		//1,对内容进行反序列化(对数组进行反序列化出对象)
		is.defaultReadObject();
		//2.获取解密对象(数组)
		Decoder decoder = Base64.getDecoder();
		//3.对内容进行解密(解密完之后转换为String类型)
		byte[] array = decoder.decode(content);//解密是对于String类型而言
		content = new String (array);
	}
}
public class TestSerializable03 {
	private static Object doServer() throws Exception {
		//1.启动服务 
		ServerSocket server = new ServerSocket(9999);
		//2.等待客户端连接
		Socket socket = server.accept();//阻滞以等待访问
		//3.socket对象可以直接获得input流然后读取socket的内容//有时间看一下源码
		//为什么获取流,流对象是一种什么样的对象
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		Object obj = ois.readObject();
		System.out.println(obj);
		//4.释放资源
		server.close();
		socket.close();
		ois.close();
		return obj;
	}
	private static void doClient(Object obj) throws Exception {
		//1.创建客户端Socket对象
		Socket socket1 = new Socket("127.0.0.1",9999);
		//2.获取流对象进行数据写操作
		ObjectOutputStream out=
		    	new ObjectOutputStream(socket1.getOutputStream());
		    	out.writeObject(obj);
		 //3.释放资源
		 socket1.close();
		 out.close();
	}
	public static void main(String[] args) throws Exception {
		new Thread() {
		public void run() {
				try {
					doServer();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		};
		}.start();
		Replay replay=new Replay();
		replay.setId(100);
		replay.setContent("hello cgb1907");
		doClient(replay);
		//这里本身要用join 然后还要用线程间的通讯,但是现在没有做,直接上面的线程快,再运行下面的,现在线程不安全,会出现错误
		//线程安全的不一定要去用,线程不安全的也不一定不可用,要看有没有线程问题
	}
	

	
}
