package com.cbn.algorithm.datastructure.graph;

/**
 * 有向图的可达性
 * 在G中找到从S出发，可达的所有点
 * @author boning
 *
 */
public class DirectedDFS {
	private boolean[] marked;

	// 在G中找到从s可达的所有顶点
	public DirectedDFS(Digraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}

	// 在G中找到从sources中的所有顶点可达的所有顶点
	public DirectedDFS(Digraph G, Iterable<Integer> sources) {
		marked = new boolean[G.V()];
		for (int s : sources)
			if (!marked[s])
				dfs(G, s);
	}

	// v是可达的吗
	public boolean marked(int v) {
		return marked[v];
	}

	// 深度优先遍历
	private void dfs(Digraph G, int v) {
		marked(v);
		for (int w : G.adj(v))
			if (!marked[w])
				dfs(G, w);
	}

	public static void main(String[] args) {
	}
}
