package simpleSort;

import littleCase.Swap;
/**
 * 几种冒泡排序，经过测试，也发现第三种改良的冒泡排序是最好使用的
 * 1w长度的数组测试时间分别大概在250 200 180 左右
 * 如果是10w长度，第三种排序大概在20s左右
 * @author Administrator
 * 最好的情况，没有数据交换，只需要比较n-1次，不需要交换，时间复杂度为 n 。
 * 最坏的情况，排序表是逆序的情况时需要比较 n（n-1）/2  并作等数量级的记录移动（3倍的比较次数，因为牵扯到交换）。时间复杂度为n2
 * （时间复杂度要包括比较和移动数据的时间）
 */
public class BubbleSort {
	/**
	 * 最容易写出的排序代码了，不过这个简单易懂的代码，却有缺陷。
	 * 观察后发现，在排序好1和2的位置后，对其余关键字的排序没有什么帮助
	 * (3还被换到了最后1位)。也就是说，这个算法的效率是非常低的。甚至不能说是冒泡排序
	 * 时间复杂度为n2
	 * @param arr需要排序的数组
	 * @return arr排序完成的数组
	 */
	public static int[] sortOne(int[] arr){
		if(arr==null || arr.length < 2 ){
            return arr;
        }
		for (int i = 0; i <arr.length-1; i++) {
			for(int j =i+1;j<=arr.length-1;j++) {//这里是因为j要比i多取一个，所以一个有=，一个没有=
				if(arr[i]>arr[j]) {
					Swap.swap(arr, i, j);
				}
			}
		}
		return arr;
	}
	/**
	 * 这个算是最基础的冒泡排序，小的数字像泡泡一样从下往上上升上来，而且i每循环一次，第一位就确定了，时间复杂度是n2，但是相比较
	 * 第一种时间要短一点，因为很多数字已经浮到上面去了
	 * @param arr需要排序的数组
	 * @return arr需要排序的数组
	 */
	public static int[] sortTwo(int[] arr){
		if(arr==null || arr.length < 2 ){
            return arr;
        }
		for (int i = 0; i <arr.length-1; i++) {
			for(int j =arr.length-1;j>i;j--) {
				if(arr[j-1]>arr[j]) {
					Swap.swap(arr, j-1, j);
				}
			}
		}
		return arr;
	}
	/**
	 * 改进版冒泡排序，加一个标记，如果发生变化就继续外层循环，如果没有变化则说明下面的已经都是有序的了，
	 * 已经排好了，就可以节约时间。
	 * @param arr需要排序的数组
	 * @return arr需要排序的数组
	 */
	public static int[] sortThree(int[] arr){
		if(arr==null || arr.length < 2 ){
            return arr;
        }
		boolean flag=true;//用作标记，如果发生变化就继续外层循环，如果没有变化则说明下面的已经都是有序的了，就可以节约时间。
		for (int i = 0; i <arr.length-1&&flag; i++) {
			flag=false;
			for(int j =arr.length-1;j>i;j--) {
				if(arr[j-1]>arr[j]) {
					Swap.swap(arr, j-1, j);
					flag=true;
				}
			}
		}
		return arr;
	}
}
