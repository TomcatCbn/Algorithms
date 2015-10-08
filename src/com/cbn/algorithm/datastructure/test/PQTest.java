package com.cbn.algorithm.datastructure.test;

import org.junit.Test;

import com.cbn.algorithm.datastructure.IndexMinPQ;
import com.cbn.algorithm.datastructure.MaxPQ;

public class PQTest {
	@Test
	public void testMaxPQ() {
		MaxPQ pq = new MaxPQ(new Integer[] { 2, 5, 3, 6, 7, 9 });
		System.out.println(pq.toString());
	}

	@Test
	public void testIndexMinPQ() {
		IndexMinPQ<String> pq = new IndexMinPQ<String>(8);
		pq.insert(0, "K");
		pq.insert(1, "E");
		pq.insert(2, "D");
		pq.insert(3, "C");
		pq.insert(4, "B");
		pq.insert(5, "F");
		pq.insert(6, "G");
		pq.insert(7, "R");
		System.out.println(pq.toString());
		pq.change(2, "A");
		System.out.println(pq.toString());
		//pq.delete(1);
		pq.delMin();
		System.out.println(pq.toString());
	}
}
