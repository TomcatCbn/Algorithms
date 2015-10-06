package com.cbn.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 连通性问题 加权quick-union算法
 * 
 * @author boning
 *
 */
public class UnionFind {
	private final int[] id;// 父链接数组
	private final int[] size;// 各个根节点所对应的分量大小
	private int N;// 连通分量的数量

	public UnionFind(int n) {
		N = n;
		id = new int[n];
		size = new int[n];
		init();
	}

	/**
	 * 初始化id数组
	 */
	private void init() {
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	/**
	 * 在p和q之间添加一条连接
	 * 
	 * @param p
	 * @param q
	 */
	private void union(int p, int q) {
		int index_p = find(p);
		int index_q = find(q);
		if (index_p == index_q)
			return;
		if (size[index_p] > size[index_q]) {
			id[index_q] = index_p;
		} else if (size[index_p] < size[index_q]) {
			id[index_p] = index_q;
		} else {
			id[index_q] = index_p;
			size[index_p]++;
		}
	}

	/**
	 * p所在的分量标示符
	 * 
	 * @param p
	 * @return
	 */
	private int find(int p) {
		return id[p] == p ? p : find(id[p]);
	}

	/**
	 * 如果p和q在于同一个分量中则返回true
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}

	public int count() {
		return N;
	}

	public void input(File file) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		String[] nums;
		try {
			while ((line = br.readLine()) != null) {
				nums = line.split(" ");
				union(Integer.valueOf(nums[0]), Integer.valueOf(nums[1]));
			}
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}
