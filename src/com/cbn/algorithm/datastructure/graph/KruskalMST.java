package com.cbn.algorithm.datastructure.graph;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

import com.cbn.algorithm.UnionFind;

/**
 * @author boning.cbn
 * 我们使用一条优先队列来将边按照权重排序，用一个union-find数据结构来识别会形成的环，以及一条队列来保存最小生成树的所有边。
 *
 */
public class KruskalMST implements MST {
	private Queue<Edge> mst;// 存放最小生成树的边

	public KruskalMST(EdgeWeightedGraph G) {
		// TODO Auto-generated constructor stub
		mst = new ArrayDeque<>();
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (Edge e : G.edges()) {
			pq.add(e);
		}

		UnionFind uf = new UnionFind(G.V());

		// 只要包含所有边的最小堆不为空，并且mst队列中的边个数达到了顶点数-1
		while (!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.remove();// 从pq得到权重最小的边和它的顶点
			int v = e.either(), w = e.other(v);
			if (uf.isConnected(v, w))
				continue;// 忽略已经连接的顶点
			uf.union(v, w);// 合并分量
			mst.add(e);// 添加到最小生成树中

		}
	}

	@Override
	public Iterable<Edge> edges() {
		return mst;
	}

	@Override
	public double weight() {
		double res = 0;
		for (Edge e : mst) {
			res += e.weight();
		}
		return res;
	}

}
