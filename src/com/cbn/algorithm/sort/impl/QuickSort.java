package com.cbn.algorithm.sort.impl;

/**
 * 普通快速排序
 * 
 * @author boning
 *
 */
public class QuickSort extends SortBasic {

	@Override
	public void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}

	private void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	/**
	 * 快速排序的切分方法
	 * 
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	private int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi + 1;
		Comparable v = a[lo];// 切分元素
		while (true) {
			// 扫描左右，检查扫描是否结束并交换元素
			while (less(a[++i], v))
				if (i == hi)
					break;
			while (less(v, a[--j]))
				if (j == lo)
					break;
			if (i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}

}
