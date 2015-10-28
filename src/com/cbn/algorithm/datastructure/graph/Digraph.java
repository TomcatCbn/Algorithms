package com.cbn.algorithm.datastructure.graph;

import java.io.InputStream;
import java.util.LinkedHashSet;

/**
 * 有向图API
 * 
 * @author boning
 *
 */
public class Digraph {
	private final int V;
	private int E;
	private LinkedHashSet<Integer>[] adj;

	// 创建一幅包含有V个顶点但没有边的有向图
	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (LinkedHashSet<Integer>[]) new LinkedHashSet[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new LinkedHashSet<Integer>();
		}
	}
	// 从输入流in中读取一幅有向图
	// public Digraph(InputStream in){
	// }

	// 顶点总数
	public int V() {
		return V;
	}

	// 边的总数
	public int E() {
		return E;
	}

	// 向有向图中添加一条边v->w
	public void addEdge(int v, int w) {
		adj[v].add(w);
		E++;
	}

	// 由v指出的边所连接的所有顶点
	public Iterable<Integer> adj(int v) {
		return (Iterable<Integer>) adj[v].iterator();
	}

	// 该图的反向图
	public Digraph reverse() {
		Digraph R = new Digraph(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj(v))
				R.addEdge(w, v);
		}
		return R;
	}

	
}
