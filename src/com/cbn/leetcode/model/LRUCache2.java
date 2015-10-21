package com.cbn.leetcode.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 用HashMap存储元素<key,CacheNode>,CacheNode是一个双向链表，一个头是最近使用的元素，一个尾是最久未使用，
 * 最后用一个int确保CacheNode结点的个数
 * 
 * @author boning
 *
 */
public class LRUCache2 {
	int totalCap;// 总容量
	int currCap;// 目前个数
	Map<Integer, CacheNode> map;
	CacheNode lru, mru;

	public LRUCache2(int capacity) {
		totalCap = capacity;
		map = new HashMap<Integer, CacheNode>();
	}

	public int get(int key) {
		int val = map.containsKey(key) ? map.get(key).value : -1;
		if (map.containsKey(key)) {
			if (map.get(key) == mru)
				return val;
			else if (map.get(key) == lru) {
				lru = lru.newer;
				lru.older = null;
			} else {
				map.get(key).older.newer = map.get(key).newer;
				map.get(key).newer.older = map.get(key).older;
			}
			mru.newer = map.get(key);
			map.get(key).older = mru; // don't forget this!!!
			mru = map.get(key);
			mru.newer = null;
		}
		return val;
	}

	public void set(int key, int value) {
		if (map.containsKey(key)) {
			map.get(key).value = value;// 更新key-value对
			if (map.get(key) == mru) // 如果更新的元素恰好是最新的元素 不做移位修改
				return;
			else if (map.get(key) == lru) {// 如果更新的元素恰好是最久未使用的元素，则删除它，移到队头
				lru = lru.newer;
				lru.older = null;
			} else {
				map.get(key).older.newer = map.get(key).newer;
				map.get(key).newer.older = map.get(key).older;
			}
			mru.newer = map.get(key);
			map.get(key).older = mru;
			mru = map.get(key);
			mru.newer = null;
		} else {
			CacheNode cNode = new CacheNode(key, value);
			map.put(key, cNode);
			if (currCap == totalCap) {
				map.remove(lru.key);
				if (lru == mru) { // capacity==1;
					lru = cNode;
					mru = cNode;
					return;
				}
				lru = lru.newer;
				lru.older = null;
			} else {
				if (currCap++ == 0) { // this ++ covers both if case and not if
										// case below.
					lru = cNode;
					mru = cNode;
					return;
				}
			}
			mru.newer = cNode;
			cNode.older = mru;
			mru = cNode;
			mru.newer = null;
		}
	}

	class CacheNode {
		int key;
		int value;
		CacheNode newer;// ==结点左边新加入的
		CacheNode older;// ==结点右边

		CacheNode(int k, int v) {
			key = k;
			value = v;
		}
	}
}
