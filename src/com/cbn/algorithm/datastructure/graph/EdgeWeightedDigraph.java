package com.cbn.algorithm.datastructure.graph;

import java.util.LinkedHashSet;

/**
 * 加权有向图的数据结构
 * 
 * @author boning.cbn
 *
 */
public class EdgeWeightedDigraph {
	private final int V;// 顶点的个数
	private int E;// 边的个数
	private LinkedHashSet<DirectedEdge>[] adj;// 邻接表

	/**
	 * 创建一个含有V个顶点的空有向图
	 * 
	 * @param V
	 */
	public EdgeWeightedDigraph(int V) {
		this.V = V;
		this.E = 0;
		adj = new LinkedHashSet[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new LinkedHashSet<>();
		}

	}

	/**
	 * 顶点个数
	 * 
	 * @return
	 */
	public int V() {
		return V;
	}

	/**
	 * 边的总数
	 * 
	 * @return
	 */
	public int E() {
		return E;
	}

	/**
	 * 将边添加到有向图中
	 * 
	 * @param e
	 */
	public void addEdge(DirectedEdge e) {
		adj[e.from()].add(e);
		E++;
	}

	/**
	 * 从v指出的边
	 * 
	 * @param v
	 * @return
	 */
	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}

	/**
	 * 该有向图中的所有边
	 * 
	 * @return
	 */
	public Iterable<DirectedEdge> edges() {
		LinkedHashSet<DirectedEdge> res = new LinkedHashSet<>();
		for (int v = 0; v < V; v++) {
			for (DirectedEdge e : adj[v]) {
				res.add(e);
			}
		}
		return res;
	}

}
