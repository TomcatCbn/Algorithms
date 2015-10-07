package com.cbn.algorithm.sort.impl;

public class ImprovedQuickSort extends SortBasic{
	private int M=5;
	@Override
	public void sort(Comparable[] a) {
		sort(a,0,a.length-1);
	}
	
	public void setM(int m) {
		M = m;
	}

	private void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo+M){
			insertionSort(a,lo,hi);
			return;
		}
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	private void insertionSort(Comparable[] a, int lo , int hi) {
		for (int i = lo+1; i <= hi; i++) {
			for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
				exch(a, j, j - 1);
			}
		}
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
