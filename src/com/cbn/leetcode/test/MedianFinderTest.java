package com.cbn.leetcode.test;

import org.junit.Test;

import com.cbn.leetcode.model.MedianFinder;

public class MedianFinderTest {
	@Test
	public void testMedianFinder(){
		MedianFinder mf= new MedianFinder();
		mf.addNum(1);
		mf.addNum(3);
		mf.addNum(4);
		mf.addNum(2);
		mf.addNum(5);
		System.out.println(mf.findMedian());
	}
}
