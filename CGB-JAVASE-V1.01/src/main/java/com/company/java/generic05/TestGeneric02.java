package com.company.java.generic05;
/**
 * 案例分析:将泛型内原本无意义的参数可以转换为有意义的参数
 * @author soft01
 *完成
 */
interface Task<Param, Result>{
	Result execute(Param param) ;
}
class IntegerConverTask implements Task<String,Integer>{

	@Override
	public Integer execute(String param) {
		return Integer.valueOf(param);
	}
	
}
public class TestGeneric02 {
	public static void main(String[] args) {
		Task<String ,Integer> task =new IntegerConverTask();
		Integer a = task.execute("1000");
		System.out.println(a);
	}
}
