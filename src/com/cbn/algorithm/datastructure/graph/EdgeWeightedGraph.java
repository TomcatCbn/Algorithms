package com.cbn.algorithm.datastructure.graph;

import java.util.LinkedHashSet;

/**
 * 加权无向图的API
 * 
 * @author boning
 *
 */
public class EdgeWeightedGraph {
	private final int V;// 顶点总数
	private int E;// 边的总数
	private LinkedHashSet<Edge>[] adj;// 邻接表

	// 创建一幅包含V个顶点的空图
	public EdgeWeightedGraph(int v) {
		this.V = v;
		this.E = 0;
		adj = new LinkedHashSet[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new LinkedHashSet<>();
		}

	}

	// 图的顶点数
	public int V() {
		return V;
	}

	// 图的边数
	public int E() {
		return E;
	}

	// 向图中添加一条边e
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
	}

	// 和V相关联的所有边
	public Iterable<Edge> adj(int v) {
		return adj[v];
	}

	// 图的所有边
	public Iterable<Edge> edges() {
		LinkedHashSet<Edge> res = new LinkedHashSet<>();
		for (int v = 0; v < V; v++) {
			for (Edge e : adj[v]) {
				if (e.other(v) > v)//防止集合出现两次
					res.add(e);
			}
		}
		return res;
	}
}
