package com.cbn.algorithm.sort.impl;

/**
 * 自底向上的归并排序
 * 
 * @author boning
 *
 */
public class MergeBuSort extends MergeSort {
	//private Comparable[] aux;// 归并所需要的辅助数组，继承自父类

	@Override
	/**
	 * 首先我们进行的是两两归并，把把每个元素想象成一个大小为1的数组 然后是四四归并，八八归并，一直下去。
	 */
	public void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for (int sz = 1; sz < N; sz <<= 1) {
			for (int lo = 0; lo < N - sz; lo += sz + sz) {
				merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
			}
		}
	}

}
