package com.cbn.algorithm.sort.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cbn.algorithm.sort.SortContext;
import com.cbn.algorithm.sort.impl.SelectionSort;

public class SortTest {
	SortContext sortUtils;
	@Before
	public void init(){
		 sortUtils = new SortContext();
	}
	@Test
	public void testSelectionSort(){
		sortUtils.setSort(new SelectionSort());
		Integer[] a= new Integer[]{6,4,10,9,7,7,8,10};
		sortUtils.doSort(a);
		Assert.assertEquals(true, sortUtils.isSorted(a));
	}
}
