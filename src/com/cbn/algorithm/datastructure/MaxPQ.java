package com.cbn.algorithm.datastructure;

/**
 * 最大堆
 * 
 * @author boning
 *
 * @param <K>
 */
public class MaxPQ<K extends Comparable<K>> {
	private int N = 0;
	private K[] pq;// 存储pq[1..N],pq[0]没有使用，基于堆的完全二叉树

	public MaxPQ() {

	}

	public MaxPQ(int max) {
		pq = (K[]) new Comparable[max + 1];
	}

	/**
	 * 通过给定数组，构建堆
	 * 
	 * @param a
	 */
	@SuppressWarnings("unchecked")
	public MaxPQ(K[] a) {
		int length = a.length;
		pq = (K[]) new Comparable[length + 1];
		for (int i = 1; i <= length; i++) {
			insert(a[i - 1]);
		}
	}

	public void insert(K v) {
		pq[++N] = v;
		swim(N);
	}

	public K max() {
		return pq[1];
	}

	public K delMax() {
		K res = pq[1];
		exch(1, N);
		sink(1);
		pq[N--] = null;// 防止对象游离
		return res;
	}

	public boolean isEmpty() {
		return 0 == N;
	}

	public int size() {
		return N;
	}

	/**
	 * 比较大小
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}

	/**
	 * 交换元素
	 * 
	 * @param i
	 * @param j
	 */
	private void exch(int i, int j) {
		K t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

	/**
	 * 由下至上的堆有序化（上浮）
	 * 
	 * @param k
	 */
	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k / 2, k);
			k = k / 2;
		}
	}

	/**
	 * 由上至下的堆有序化（下沉）
	 * 
	 * @param k
	 */
	private void sink(int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && less(j, j + 1))
				j++;// 右边比左边大
			if (!less(k, j))
				break;// 父如果大于子，直接停止
			exch(k, j);
			k = j;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 1; i <= N; i++) {
			sb.append(pq[i].toString() + " ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}
}
