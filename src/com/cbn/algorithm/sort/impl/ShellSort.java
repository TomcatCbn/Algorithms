package com.cbn.algorithm.sort.impl;

/**
 * 希尔排序（插入排序的改进版）
 * 
 * @author boning
 *
 */
public class ShellSort extends SortBasic {
	private int n = 3;

	public void setn(int x) {
		this.n = x;
	}

	@Override
	public void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		while (h < N / n)
			h = n * h + 1;
		while (h >= 1) {
			for (int i = h; i < N; i++) {
				// 将a[i]插入到a[i-h],a[i-2h],a[i-3h]...
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
					exch(a, j, j - h);
				}
			}
			h /= 3;
		}
	}

}
