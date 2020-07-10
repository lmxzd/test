package com.company.java.cache04;
/**
 * 通过此Cache记录命中率
 * @author soft01
 *没问题了,完成
 */
public class LogCache implements Cache{
	private int requests;
	private int hits;
	private Cache cache;
	public LogCache(Cache cache) {
		this.cache=cache;
	}
	@Override
	public void putObject(Object key, Object value) {
		cache.putObject(key, value);
	}
	
	@Override
	public Object getObject(Object key) {
		requests++;
		Object obj  = cache.getObject(key);
		if(obj!=null) {
			hits++;
		}
		System.out.println("命中率:"+ (hits*1.0)/requests);
		return obj;
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
		return cache.size();
	}
	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return cache.toString();
		}
	public static void main(String[] args) {
		LogCache logCache = new LogCache(new PerpetualCache());
		logCache.putObject("A", 100);
		logCache.putObject("B", 200);
		logCache.putObject("C", 300);
		logCache.putObject("", 300);
		System.out.println(logCache);
		logCache.getObject("D");
		logCache.getObject("A");
		logCache.getObject("B");
		System.out.println(logCache);
	}
}
