package com.cbn.algorithm.sort.impl;

/**
 * 堆排序 先构建堆： 从右至左用sink()函数构造子堆。只需要扫描数组中的一般元素，因为我们可以跳过大小为1的子堆
 * 
 * @author boning
 *
 */
public class HeapSort extends SortBasic {

	@Override
	public void sort(Comparable[] a) {
		// 构建堆
		int N = a.length - 1;
		for (int k = N / 2; k >= 0; k--)
			sink(a, k, N);
		// 排序
		while (N >= 1) {
			exch(a, 0, N);
			sink(a, 0, N - 1);
			N--;
		}
	}

	public static void sink(Comparable[] a, int lo, int hi) {
		while (2 * lo + 1 <= hi) {
			int j = 2 * lo + 1;
			if (j < hi && less(a[j], a[j + 1]))
				j++;// 右边比左边大
			if (!less(a[lo], a[j]))
				break;// 父如果大于子，直接停止
			exch(a, lo, j);
			lo = j;
		}

	}

}
