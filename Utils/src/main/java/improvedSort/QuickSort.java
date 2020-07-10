package improvedSort;

import littleCase.Swap;
import simpleSort.StraightInsertionSort;

/**
 * 快速排序是由TonyHoare发明，图灵奖获得者，20世纪10大算法之一，目前最快的排序算法，这里的是经过优化之后的
 * 一共有三个优化
 * 第一个：优化数据量较小时，利用直接插入排序
 * 第二个：尾递归优化，将末尾的递归优化为迭代
 * 第三个：利用随机原则，三数取中以找到更好的轴
 * 但是自己写的和源码重大的还有很大差距
 * 测试结果：
 * 1w数量级 140ms
 * 10w数量级 6~7s
 * 100w数量级 耗时过多，和电脑性能有关，因为用到递归，分配内存占用时间过多，这并不能说明算法的好坏，只是电脑性能
 * 导致,不，还是算法的问题，源码自带的sort快排很快
 * 源码的sort排序
 * 1e数量级只有20s左右，比希尔以及堆排都要快，也可能是测试方法的原因，源码的sort排序用到很多排序，如果在某个数量级
 * 则用什么排序，如果是较有序，则用什么排序。是一个分段并且优化过的排序，效果当然比纯粹的快排要好的多，而且java源码
 * 使用的是双轴快排
 * @author Administrator
 * 排序的一些总结：
 * 其实以上的排序算法可以不要返回值，因为数组的修改在堆内直接操作内存。
 * 加上也无所谓，这里只是设计的关系，这里有个疑问，是否和线程的发展有关(callable有返回值future，runable没有)？
 * 以上学的都是内部排序，也就是可以直接放在内存中的排序算法，对于更大数量级的排序，内存放不下的排序，也就是外排序
 * 递归在归并和快速中用到较多，多理解理解，递归树又是什么东西？一个函数内部有两个递归，这就是递归树，
 * 递归树的深度近似是log2n，故递归树和排序有着很大的关系。
 * 单一的递归可以看成压栈和弹栈，尾递归实际也是一样的压栈和弹栈，只不过可以进行相应的优化
 */
public class QuickSort {
	public static int[] sort(int[] arr) {
		qSort(arr, 0, arr.length-1);
		return arr;
	}
	private static int[] qSort(int[] arr, int low,int high) {
		int pivot;
		//当递归调用到处理数据较小的数组时转而用直接插入排序，用来减少栈内存的利用，这里以7为界限
		if((high-low)>50) {
			while(low<high) {
				pivot=partition(arr, low, high);
				qSort(arr, low, pivot-1);
				low=pivot+1;
			}
		}else {
			StraightInsertionSort.sort(arr);
		}
		return arr;
	}
	private static int partition(int[] arr,int low,int high) {
		int pivotkey,temp;
		//三数取中，相应的也可以9数取中等等，因为这个轴越接近与中间性能越高
		avg(arr, low, high);
		pivotkey=arr[low];
		temp=pivotkey;
		while(low<high) {
			//这里也要加上low<high的约束，因为外面的约束是循环一遍结束才会判断，这里走不到上面的判断。
			while(low<high&&arr[high]>=pivotkey) 
				high--;
			arr[low]=arr[high];
			while(low<high&&arr[low]<=pivotkey) 
				low++;
			arr[high]=arr[low];
		}
		arr[low]=temp;
		return low;
	}
	/**
	 * 三数取中
	 */
	private static int avg(int[] arr, int low, int high) {
		int m = (low+high)/2;
		if(arr[low]>arr[high])Swap.swap(arr, low, high);
		if(arr[m]>arr[high])Swap.swap(arr, m, high);
		if(arr[m]>arr[low])Swap.swap(arr, m, low);
		return low;
	}
}
