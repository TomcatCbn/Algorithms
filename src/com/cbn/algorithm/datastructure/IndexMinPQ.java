package com.cbn.algorithm.datastructure;

/**
 * 索引优先队列
 * 
 * @author boning
 *
 */
public class IndexMinPQ<Item extends Comparable<Item>> {
	private final int[] pq;// 存放索引
	private final Item[] index;// 存放具体Item数组
	private final int[] parents;// 存储Item数组对应在pq里的序列号
	private int N = 0;// 元素个数

	/**
	 * 创建一个最大容量为maxNde 优先队列，索引的取值范围为0至maxN-1
	 * 
	 * @param maxN
	 */
	public IndexMinPQ(int maxN) {
		pq = new int[maxN + 1];
		index = (Item[]) new Comparable[maxN];
		parents = new int[maxN];
		for (int i = 0; i < maxN; i++) {
			pq[i + 1] = -1;
			parents[i] = -1;
		}
	}

	/**
	 * 插入一个元素，将它和索引id相关联
	 * 
	 * @param id
	 * @param item
	 */
	public void insert(int id, Item item) {
		pq[++N] = id;
		parents[id] = N;
		index[id] = item;
		swim(N);
	}

	/**
	 * 将索引为k的元素设为Item
	 * 
	 * @param k
	 * @param item
	 */
	public void change(int k, Item item) {
		index[k] = item;
		int parent = parents[k];
		if (1 == parent) {
			sink(parent);
			return;
		}

		if (less(item, index[pq[parent / 2]])) {
			swim(parent);
		} else
			sink(parent);
	}

	/**
	 * 是否存在索引为k的元素
	 * 
	 * @param id
	 * @return
	 */
	public boolean contains(int id) {
		return null == index[id];
	}

	/**
	 * 删去索引k及相关联的元素
	 * 
	 * @param k
	 */
	public void delete(int k) {
		int temp = parents[k];
		exch(temp, N--);
		sink(temp);
		parents[k] = -1;
		index[k] = null;

	}

	/**
	 * 返回最小元素
	 * 
	 * @return
	 */
	public Item min() {
		return index[pq[1]];
	}

	/**
	 * 返回最小元素的索引
	 * 
	 * @return
	 */
	public int minIndex() {
		return pq[1];
	}

	/**
	 * 删除最小元素并返回它的索引
	 * 
	 * @return
	 */
	public int delMin() {
		int id = pq[1];
		exch(1, N);
		sink(1);
		index[id] = null;
		pq[N--] = -1;
		parents[id] = -1;
		return id;
	}

	/**
	 * 是否为空
	 */
	public boolean isEmpty() {
		return N == 0;
	}

	/**
	 * 元素的数量
	 * 
	 * @return
	 */
	public int size() {
		return N;
	}

	private void swim(int k) {
		while (k > 1 && less(index[pq[k]], index[pq[k / 2]])) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	private void exch(int k, int i) {
		int temp = pq[k];
		parents[pq[k]] = i;
		pq[k] = pq[i];
		parents[pq[i]] = k;
		pq[i] = temp;
	}

	private boolean less(Item item, Item item2) {

		return item.compareTo(item2) < 0;
	}

	private void sink(int i) {
		while (2 * i < N) {
			int j = 2 * i;
			if (j < N && less(index[pq[j + 1]], index[pq[j]]))
				j++;
			if (less(index[pq[i]], index[pq[j]]))
				break;
			exch(i, j);
			i = j;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i = 1; i <= N; i++) {
			sb.append("<" + pq[i] + ", " + index[pq[i]].toString() + ">");
			if (i != N)
				sb.append(",");
		}
		sb.append("}");
		return sb.toString();

	}
}
