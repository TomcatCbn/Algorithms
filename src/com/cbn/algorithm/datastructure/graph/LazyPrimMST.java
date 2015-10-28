package com.cbn.algorithm.datastructure.graph;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 最小生成树的Prim算法的延时实现
 * 
 * 使用了一条优先队列来保存所有的横切边，一个由顶点索引的数组来标记树的顶点以及
 * 一条队列来保存最小生成树的边。
 * 
 * @author boning
 *
 */
public class LazyPrimMST implements MST {
	private boolean[] marked;// 最小生成树的顶点
	private Queue<Edge> mst;// 最小生成树的边
	private PriorityQueue<Edge> minQue;// 横切边（包括实效的边）

	public LazyPrimMST(EdgeWeightedGraph G) {
		minQue = new PriorityQueue<>(Collections.reverseOrder());
		marked = new boolean[G.V()];
		mst = new ArrayDeque<>();

		visit(G, 0);
		while (!minQue.isEmpty()) {
			Edge e = minQue.remove();

			int v = e.either(), w = e.other(v);
			if (marked[v] && marked[w])
				continue;// 跳过失效的边
			mst.add(e);// 将边添加到树中
			if (!marked[v])
				visit(G, v);// 将顶点v或w添加到树中
			if (!marked[w])
				visit(G, w);
		}
	}

	/**
	 * 为树添加一个顶点、将它标记为“已访问”并将与它关联的所有未失效的边加入优先队列， 以保证队列含有所有连接树顶点和非树顶点的边（可能包含失效的边）
	 * 
	 * @param g
	 * @param i
	 */
	private void visit(EdgeWeightedGraph g, int v) {
		marked[v] = true;
		for (Edge e : g.adj(v)) {
			if (!marked[e.other(v)])
				minQue.add(e);
		}
	}

	@Override
	public Iterable<Edge> edges() {

		return mst;
	}

	/**
	 * 遍历树的所有边并得到它们的权重之和
	 */
	@Override
	public double weight() {
		double res = 0;
		for (Edge e : mst) {
			res += e.weight();
		}
		return res;
	}

}
