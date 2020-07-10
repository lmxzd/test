package improvedSort;

/**
 * 有两个排序方法，一个是利用递归，一个是利用迭代替代递归，第二种效率更好
 * 关于递归：递归调用是方法的依次调用，和是否有返回值无关。如果理解的话用压栈和弹栈更好记忆
 * 递归过程中的边界问题
 * 数量级为10 耗时0
 * 数量级为1w 耗时 400ms
 * 数量级为10w 耗时 25s
 * 数量级为100w 耗时过多  原因可能是因为频繁的递归调用浪费了性能和时间 还是什么创建数组的 原因？和网上摘录的对比一下
 * 因为这个代码每次递归都需要用new一个数组
 * 等待用迭代的方式来做实验？
 * 归并排序是一种比较占用内存，但却效率高且稳定的算法。
 * 归并排序需要进行log2 n次归并操作，每层归并操作，要比较n/2次，赋值n次，所以时间复杂度为nlogn，
 * 而且不存在最优最差情况。
 * @author Administrator
 *
 */
public class MergingSort {
	/**
	 * 利用递归的方式
	 * @param arr
	 */
	public static int[] sortOne(int[] arr) {
		if(arr==null || arr.length < 2 ){
            return arr;
        }
		//这里第二个arr可以重新new，只要长度和第一个一样即可，也就是将原数组作为最终数组的意思，就少new一个了
		Msort(arr, arr, 0, arr.length-1);
		return arr;
	}
	/**
	 * 递归分离，归并原数组，得到新得到的数组
	 * @param oldarr 原数组
	 * @param newarr 新得到的数组
	 * @param s 开始操作的起始元素下标
	 * @param t 结束操作的最终元素下标
	 * @return 返回目标数组，返回值可要可不要，因为操作的是数组，只需要分清逻辑关系即可
	 */
	private static int[] Msort(int[] oldarr,int[] newarr ,int s,int t) {
		int m;
		int[] arrTemp = new int [oldarr.length];
		
		/*
		 * 如果s==t，则说明已经将原数组分离为n个一个数组成的数组，而且这个数在的位置也是和原数组一样的，
		 * 也就是说把原本数组分离为n个{null,null,s,...,null}的形式，也就是递归的终止条件
		 */
		if(s==t) {
			newarr[s]=oldarr[s];
		}else {
		//连续自然数求中间数的公式,可以用位运算优化
		m=(s+t)/2;
		//递归将原数组转换为上面说的那种形式
		Msort(oldarr, arrTemp, s, m);
		/*
		 * 这里是分为两个，是因为用的是2路归并，也就是说用的是2叉树，这里有个疑问，
		 * 那是否可以用三叉树，三叉树又有什么性质呢？
		 */
		Msort(oldarr, arrTemp, m+1, t);
		//将分开的有序数组归并起来
		Merge(arrTemp, newarr, s, m, t);
		}
		return newarr;
	}
	/**
	 * 将两个有序数组（这里用的是一个长数组，可以减少创建数组，一次递归调用只创建了一个数组）归并成为有序数组
	 * 谁小谁给目标数组赋值，而且谁小谁++，让下一个继续和另一个数组的此位置比较
	 * @param arrTemp 长数组（看成两个有序数组）
	 * @param targetArr 目标数组
	 * @param i 第一个有序数组的起始位置
	 * @param m 第一个有序数组的结束位置，m+1即是第二个有序数组的起始位置
	 * @param n 归并为有序数组之后的大小，实际是从上一步递归得到的一个值，并不是从归并完了之后得到的这个值，只是正好
	 * 等于这个值
	 * @return 返回目标数组，返回值可要可不要，因为操作的是数组，只需要分清逻辑关系即可
	 */
	private static int[] Merge(int[] arrTemp,int [] targetArr,int i,int m,int n) {
		int j,k,l;
		for(j=m+1,k=i;i<=m&&j<=n;k++) {
			if(arrTemp[i]>arrTemp[j])
				targetArr[k]=arrTemp[j++];
			else 
			targetArr[k]=arrTemp[i++];
		}
		//要注意这里,会存在少存一个数的情况，下面这两行代码只会运行一个或不运行（猜测）
		if(i<=m) {
			for(l=0;l<=m-i;l++) {
				targetArr[k+l]=arrTemp[i+l];
			}
		}
		if(j<=n) {
			for(l=0;l<=n-j;l++) {
				targetArr[k+l]=arrTemp[j+l];
			}
		}
		return targetArr;
	}
	
	/**
	 * 用迭代代替递归
	 */
	public static int[] sortTwo(int[] arr) {
		return arr;
	}
}
