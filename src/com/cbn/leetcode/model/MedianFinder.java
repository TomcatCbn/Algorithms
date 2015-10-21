package com.cbn.leetcode.model;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * #295	Find Median from Data Stream
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder mf = new MedianFinder(); mf.addNum(1); mf.findMedian();
 * 找到中间数
 * @author boning
 *
 */
public class MedianFinder {
	Queue<Integer> q = new PriorityQueue<Integer>(), z = q, t, Q = new PriorityQueue<Integer>(Collections.reverseOrder());

	public void addNum(int num) {
		(t = Q).add(num);
		(Q = q).add((q = t).poll());//交换位置
	}

	public double findMedian() {
		return (Q.peek() + z.peek()) / 2.;
	}
	
//  Queue[] q = {new PriorityQueue<Integer>(), new PriorityQueue<Integer>(Collections.reverseOrder())};
//  int i = 0;
//
//  public void addNum(int num) {
//      q[i].add(num);
//      q[i^=1].add(q[i^1].poll());
//  }
//
//  public double findMedian() {
//      return ((int)(q[1].peek()) + (int)(q[i].peek())) / 2.0;
//  }
}