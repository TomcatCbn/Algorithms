package com.cbn.algorithm.sort.impl;
/**
 * 选择排序
 * @author boning
 *
 */
@SuppressWarnings("rawtypes")
public class SelectionSort extends SortBasic {

	@Override
	public void sort(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i + 1; j < N; j++) {
				if (less(a[j], a[min])) {
					min = j;
				}
			}
			exch(a, i, min);
		}
	}

}
