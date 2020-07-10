package com.company.java.cache04;
/**
 * 定义缓存标准的一个接口
 * @author soft01
 *
 */
public interface Cache {
	/**存储对象*/
	void putObject(Object key,Object value);
	/**基于key取对象*/
	Object getObject(Object key);
	/**基于key移除对象*/
	Object removeObject(Object key);
	/**清缓存*/
	void clear();
	/**定义大小*/
	int size();
	//...
}
