package com.cbn.leetcode.test;

import org.junit.Test;

import com.cbn.leetcode.model.LRUCache;

public class LRUCacheTest {
	@Test
	public void testLRUCache() {
		LRUCache lru = new LRUCache(2);
		lru.set(2, 1);
		lru.set(2, 2);
		System.out.println(lru.get(2));
		lru.set(1, 1);
		lru.set(4, 1);
		System.out.println(lru.get(2));
	}

	@Test
	public void testLRUCache2() {
		LRUCache lru = new LRUCache(2);
		lru.set(2, 1);
		lru.set(1, 1);
		System.out.println(lru.get(2));
		lru.set(4, 1);
		lru.set(2, 3);
		lru.set(1, 1);
		System.out.println(lru.get(1));
		System.out.println(lru.get(2));
	}
	
	@Test
	public void testLRUCache3() {
		LRUCache lru = new LRUCache(1);
		lru.set(2, 1);
		System.out.println(lru.get(2));
		lru.set(3, 2);
		System.out.println(lru.get(2));
		System.out.println(lru.get(3));
	}
}
