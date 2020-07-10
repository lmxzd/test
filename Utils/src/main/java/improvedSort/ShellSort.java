package improvedSort;

/**
 * 希尔排序科学家shell发明
 * 实验结果
 * 10数量级 耗时7ms
 * 1w数量级 耗时28ms
 * 10w数量级 耗时 64ms
 * 100w数量级 耗时 405ms（反应慢，只是打印过于耗时）测试中第一个可以快速百万级的排序
 * 1000w数量级 耗时5-6s
 * 1e数量级 耗时 83s左右
 * 总体的时间复杂度为 n的（3/2）次幂，最好是n的1.3次幂 最快是n2，平均在nlogn-n2之间
 * 说明希尔排序是一个耗时稳定的排序（但不是稳定排序，不要混淆），不愧是第一个超过n2时间复杂度的排序算法
 * 核心思想是把一个长长的序列通过增量分解为若干个较短的序列（基本平均分割），然后逐步缩小增量，
 * 每个增量都会使序列趋向基本有序，最终增量缩小为1，算法退化为直接插入排序，又因为序列此时已趋向基本有序，直接插入排序效率
 * 大为提高，由此可见，这个算法还是非常巧妙的。
 * （这中间感觉还会有多余的比较和移动，可以通过寻找合适的增量来适当解决？）
 * @author Administrator
 *
 */
public class ShellSort {
	public static int[] sort(int[] arr) {
		if(arr==null || arr.length < 2 ){
            return arr;
        }
		int increment= arr.length;
		while(increment>1) {
			//设置增量，经验值是dlta[k]=2（t-k+1）次幂再-1，这个也可以勉强使用，这个增量的选择一定程度上决定了这个算法的好坏
			//最终增量会变成1，也就是化成了直接插入排序（这个时候的序列经过之前的很多次排列，已经变为基本有序了，所以用直接插入
			//排序，速度会很快）
			increment = increment/3+1;
			for(int i=increment;i<arr.length;i++){
				if(arr[i]<arr[i-increment]) {
					//理论上来说，局部变量存在的时间越少越好，所以这样设计其实更合理，但是这个对速度影响不大，直接写在开头也可以。
					int j,temp = arr[i];
					for(j=i-increment;j>=0&&temp<arr[j];j-=increment) {
						arr[j+increment]=arr[j];
						//						//这样的代码就是多移动了一次，变成冒泡排序了，就不好。
						//						if(arr[i]<arr[j]) {
						//							Swap.swap(arr, i, j);
						//						}
					}
					//要注意最后的边界问题，分析过程清晰才不会犯错
					arr[j+increment]=temp;
				}
			}
		}
		return arr;
	}
}
