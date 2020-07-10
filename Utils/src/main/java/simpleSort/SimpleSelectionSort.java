package simpleSort;

import littleCase.Swap;

/**
 * 简单选择排序
 * 选择第i到n个数的最小值放在第i个位置，比较花费的时间是累加（n-i）从i=1到n-1，比较次数为n（n-1）/2，
 * 移动次数最小为0，最大为n-1次（有个系数3）
 * 时间复杂度还是n2，但是相比冒泡要快一点点
 * 1w的数组，大概用时125ms，如果是顺序数组，则是70ms左右
 * 10w的长度，大概在10s左右，大概是冒泡排序的一半，所以也间接验证了时间复杂度推导的正确性
 * 如果是顺序队列，时间在4s左右，比直接插入排序要慢很多，证明了直接插入排序的优越性
 * （时间大概可以说明问题就可以了，因为中间可能还有别的不清楚的操作会耗时，比如随机数实际不能说明有序无序的程度）
 * @author Administrator
 *
 */
public class SimpleSelectionSort {
	public static int[] sort(int[] arr){
		if(arr==null || arr.length < 2 ){
            return arr;
        }
		//通过这个代码的结构也可以看出来和这个算法当时的思维是一样的，所以当见更多的东西，有经验之后，算法的能力也就培养起来了
		for (int i = 0; i <arr.length-1; i++) {
			int min = i;
			for(int j =i+1;j<=arr.length-1;j++) {
				//这里要用每次比较的最小值来和下一位比较，所以通式要有最小值（简单的记一下，当然也可以通过推导来解决）
				//这里还要用min做下标，是因为最终是个交换操作，而不是单纯的赋值操作，所以需要用下标来交换
				if(arr[min]>arr[j]) {
					min=j;
				}
			}
			if(min!=i) {
				Swap.swap(arr, i, min);
			}
		}
		return arr;
	}
}
