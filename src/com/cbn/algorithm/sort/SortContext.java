package com.cbn.algorithm.sort;

public class SortContext {
	private ISort sort;

	public ISort getSort() {
		return sort;
	}

	public void setSort(ISort sort) {
		this.sort = sort;
	}

	public SortContext(ISort sort) {
		super();
		this.sort = sort;
	}

	public SortContext() {
	}

	@SuppressWarnings("rawtypes")
	public void doSort(Comparable[] a) {
		sort.sort(a);
	}

	@SuppressWarnings("rawtypes")
	public boolean isSorted(Comparable[] a) {
		return sort.isSorted(a);
	}
}
