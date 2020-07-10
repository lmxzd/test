package com.company.java.cache04;
/**
 * 线程安全的cache对象(因为hashmap是线程不安全的,所以需要写这个对象)
 * @author soft01
 *基本完成,只是加了个锁
 */
public class SynchronizedCache implements Cache{
	private Cache cache;
	public SynchronizedCache( Cache cache) {
		this.cache=cache;
	}
	
	@Override
	public synchronized void  putObject(Object key, Object value) {
		cache.putObject(key, value);
	}

	@Override
	public synchronized Object getObject(Object key) {
		return cache.getObject(key);
	}

	@Override
	public Object removeObject(Object key) {
		return cache.removeObject(key);
	}

	@Override
	public void clear() {
		cache.clear();
	}

	@Override
	public int size() {
		return  cache.size();
	}
	public static void main(String[] args) {
		SynchronizedCache synchronizedCache = new SynchronizedCache(new PerpetualCache());
		new Thread() {
			public void run() {
				synchronizedCache.putObject("A", 100);
				//这个如何模拟多线程呢?
			};
		}.start();
		new Thread() {
			public void run() {
				synchronizedCache.getObject("A");
			};
		}.start();
	}
}
