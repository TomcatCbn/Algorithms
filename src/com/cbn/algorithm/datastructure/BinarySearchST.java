package com.cbn.algorithm.datastructure;

/**
 * 基于有序数组实现符号表ST接口
 * 
 * @author boning
 *
 * @param <K>
 * @param <V>
 */
public class BinarySearchST<K extends Comparable<K>, V> implements ST<K, V> {
	private K[] keys;
	private V[] values;
	private int N;

	public BinarySearchST(int capacity) {
		keys = (K[]) new Comparable[capacity];
		values = (V[]) new Comparable[capacity];
	}

	@Override
	public int size() {
		return N;
	}

	@Override
	public V get(K key) {
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0)
			return values[i];
		return null;
	}

	@Override
	public boolean isEmpty() {
		return N == 0;
	}

	/**
	 * 基于有序数组的二分查找
	 * 
	 * @param key
	 * @return 对于命中 返回该键的位置;未命中 返回小于它的键的数量
	 */
	public int rank(K key) {
		int lo = 0, hi = N - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}

	public void put(K key, V value) {
		// 查找键，找到则更新值，否则创建新的元素
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			values[i] = value;
			return;
		}
		for (int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			values[j] = values[j - 1];
		}
		keys[i] = key;
		values[i] = value;
		N++;
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteMin() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMax() {
		// TODO Auto-generated method stub

	}

	@Override
	public int size(K lo, K hi) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(K key) {
		// TODO Auto-generated method stub

	}

	@Override
	public K min() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K max() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K floor(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K ceiling(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K select(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<K> keys(K lo, K hi) {
		// TODO Auto-generated method stub
		return null;
	}
}
