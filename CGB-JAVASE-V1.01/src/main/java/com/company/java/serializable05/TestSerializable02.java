package com.company.java.serializable05;

import java.io.Serializable;

import com.company.java.cache04.Cache;
import com.company.java.cache04.PerpetualCache;
import com.company.java.cache04.SerializableCache;

/**
 * 为什么要写序列化cache :反序列化出不同对象?? 写到磁盘具体的文件里就可以了因为写到磁盘里的大都是字节的形式   
 * 反序列化就可以生成不同对象
 * @author soft01
 *	将Problem问题对象序列化到SerializableCache对象，
 * 	需要时再从Cache对象去获取(需要进行反序列化)
 * 完成
 */
class Problem implements Serializable{//想要序列化,必须有这个方法
	private static final long serialVersionUID = -262656415760758122L;
	private Integer id;
	//问题标题
	private String title;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Problem [id=" + id + ", title=" + title + "]";
	}
}
public class TestSerializable02 {
	public static void main(String[] args) {
//		Problem pb = new Problem(new SerializableCache(new PerpetualCache()));
//		pb.setId(1);
//		pb.setTitle("新闻");
//		pb.put("A", pb);// 不能这么写,这样逻辑上是行不通的 自己用方法不能把自己放进去
//		Object obj =pb.get("A");
//		System.out.println(obj);
		Problem p=new Problem();
		p.setId(100);
		p.setTitle("为什么写序列化的cache对象");
		SerializableCache cache=new SerializableCache(new PerpetualCache());//修饰就是多了一些功能,虽然底层还是用的Perpetual存的,但是要多加上seri的额外代码
		cache.putObject("p", p);//处理p就不应该再把相应的函数写到p中了,否则也会出现bug,就像上面的例子
		Object obj=cache.getObject("p");
		System.out.println(obj);
	}
}
