package com.cbn.algorithm.sort.impl;

import com.cbn.algorithm.sort.ISort;

@SuppressWarnings("rawtypes")
public abstract class SortBasic implements ISort {

	public abstract void sort(Comparable[] a);

	@SuppressWarnings("unchecked")
	protected static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	protected static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	protected static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

}
