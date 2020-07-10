package littleCase;

public class InitData {
	public static void initArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			 arr[i] = (int) Math.ceil(Math.random() * 100000000);
	        }
	}
}
