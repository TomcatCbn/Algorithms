package com.cbn.algorithm.sort.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cbn.algorithm.sort.SortContext;
import com.cbn.algorithm.sort.impl.ImprovedQuickSort;
import com.cbn.algorithm.sort.impl.InsertionSort;
import com.cbn.algorithm.sort.impl.MergeBuSort;
import com.cbn.algorithm.sort.impl.MergeSort;
import com.cbn.algorithm.sort.impl.Quick3WaySort;
import com.cbn.algorithm.sort.impl.QuickSort;
import com.cbn.algorithm.sort.impl.SelectionSort;
import com.cbn.algorithm.sort.impl.ShellSort;

public class SortTest {
	SortContext sortUtils;
	Integer[] a;

	@Before
	public void init() {
		sortUtils = new SortContext();
		a = new Integer[] { 6, 4, 10, 9, 7, 7, 8, 10, 11, 15, 1, 3, 21 };
	}

	// 选择排序
	@Test
	public void testSelectionSort() {
		sortUtils.setSort(new SelectionSort());
		sortUtils.doSort(a);
		Assert.assertEquals(true, sortUtils.isSorted(a));
	}

	// 插入排序
	@Test
	public void testInsertionSort() {
		sortUtils.setSort(new InsertionSort());
		sortUtils.doSort(a);
		Assert.assertEquals(true, sortUtils.isSorted(a));
	}

	// 希尔排序
	@Test
	public void testShellSort() {
		sortUtils.setSort(new ShellSort());
		sortUtils.doSort(a);
		Assert.assertEquals(true, sortUtils.isSorted(a));
	}

	// 自顶向下归并排序
	@Test
	public void testMergeSort() {
		sortUtils.setSort(new MergeSort());
		sortUtils.doSort(a);
		Assert.assertEquals(true, sortUtils.isSorted(a));
	}

	// 自底向上归并排序
	@Test
	public void testMergeBuSort() {
		sortUtils.setSort(new MergeBuSort());
		sortUtils.doSort(a);
		Assert.assertEquals(true, sortUtils.isSorted(a));
	}

	// 快速排序
	@Test
	public void testQuickSort() {
		sortUtils.setSort(new QuickSort());
		sortUtils.doSort(a);
		Assert.assertEquals(true, sortUtils.isSorted(a));
	}

	// 改进快速排序
	@Test
	public void testImprovedQuickSorSort() {
		sortUtils.setSort(new ImprovedQuickSort());
		sortUtils.doSort(a);
		Assert.assertEquals(true, sortUtils.isSorted(a));
	}

	// 3向快速排序
	@Test
	public void testQuick3WaySort() {
		sortUtils.setSort(new Quick3WaySort());
		sortUtils.doSort(a);
		Assert.assertEquals(true, sortUtils.isSorted(a));
	}
}
