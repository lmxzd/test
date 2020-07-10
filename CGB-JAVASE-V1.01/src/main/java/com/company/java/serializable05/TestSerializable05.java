package com.company.java.serializable05;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 广义的序列化:转换为字符串了,而不是字节,但是类似了,因为String转字节很简单
 * 将pojo对象转换为JSON格式的字符串
 * 完成
 */
public class TestSerializable05 {
	public static void main(String[] args) throws Exception {
		//构建Channel对象
		Channel c = new Channel();
		c.setId(100);
		c.setName("新闻");
		c.setState(1);
		//将对象转换为json格式字符串
		ObjectMapper om = new ObjectMapper();
		String s1=om.writeValueAsString(c);
		System.out.println(s1);
		//将字符串转换为对象
		Object o1=om.readValue(s1, Channel.class);
		System.out.println(o1);
	}
}
