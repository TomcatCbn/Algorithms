package com.cbn.algorithm.sort.impl;
/**
 * 自顶向下的归并排序
 * @author boning
 *
 */
public class MergeSort extends SortBasic {
	protected Comparable[] aux;// 归并所需的辅助数组

	@Override
	public void sort(Comparable[] a) {
		aux = new Comparable[a.length];// 一次性分配辅助数组空间
		sort(a, 0, a.length - 1);
	}

	private void sort(Comparable[] a, int lo, int hi) {
		// 将数组a[lo..hi]排序
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid);// 左半边排序
		sort(a, mid + 1, hi);// 右半边排序
		merge(a, lo, mid, hi);// 归并
	}

	/**
	 * 该方法先将所有元素复制到aux[]中，然后再归并到a[]中。 方法在归并时进行了4个条件判断： 左半边用尽（取右半边的元素）
	 * 右半边用尽（取左半边的元素） 右半边的当前元素小于左半边的当前元素 右半边的当前元素大于左半边的当前元素
	 * 
	 * @param a
	 * @param lo
	 * @param mid
	 * @param hi
	 */
	protected void merge(Comparable[] a, int lo, int mid, int hi) {
		// 将a[lo..mid]与a[mid+1..hi]归并
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {// 将a[lo..hi]复制到aux[lo..hi]
			aux[k] = a[k];
		}
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[i], aux[j]))
				a[k] = aux[i];
			else
				a[k] = aux[j];
		}
	}

}
