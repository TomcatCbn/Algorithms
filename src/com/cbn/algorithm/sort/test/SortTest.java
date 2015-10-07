package com.cbn.algorithm.sort.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cbn.algorithm.sort.SortContext;
import com.cbn.algorithm.sort.impl.InsertionSort;
import com.cbn.algorithm.sort.impl.SelectionSort;
import com.cbn.algorithm.sort.impl.ShellSort;

public class SortTest {
	SortContext sortUtils;
	Integer[] a;

	@Before
	public void init() {
		sortUtils = new SortContext();
		a = new Integer[] { 6, 4, 10, 9, 7, 7, 8, 10 };
	}

	@Test
	public void testSelectionSort() {
		sortUtils.setSort(new SelectionSort());
		sortUtils.doSort(a);
		Assert.assertEquals(true, sortUtils.isSorted(a));
	}

	@Test
	public void testInsertionSort() {
		sortUtils.setSort(new InsertionSort());
		sortUtils.doSort(a);
		Assert.assertEquals(true, sortUtils.isSorted(a));
	}

	@Test
	public void testShellSort() {
		sortUtils.setSort(new ShellSort());
		sortUtils.doSort(a);
		Assert.assertEquals(true, sortUtils.isSorted(a));
	}

}
