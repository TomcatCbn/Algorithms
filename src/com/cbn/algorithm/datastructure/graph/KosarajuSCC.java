package com.cbn.algorithm.datastructure.graph;

/**
 * 计算强连通分量的Kosaraju算法
 * 
 * @author boning
 *
 */
public class KosarajuSCC {
	private boolean[] marked;// 已访问过的顶点
	private int[] id;// 强连通分量的标识符
	private int count;// 强连通分量的数量

	public KosarajuSCC(Digraph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		//在反向图中进行深度优先搜索（ReversePost）
		DepthFirstOrder order = new DepthFirstOrder(G.reverse());
		//在原始的有向图中进行深度优先搜索，用原图的反向图的逆顺序排列作为检查顺序
		for (int s : order.reversePost()) {
			if (!marked[s]) {
				dfs(G, s);
				count++;// 所有在同一个递归dfs()调用中被访问到的顶点都在同一个强连通分量中
			}
		}
	}

	private void dfs(Digraph g, int s) {
		marked[s] = true;
		id[s] = count;
		for (int w : g.adj(s)) {
			if (!marked[w])
				dfs(g, w);
		}
	}

	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}

	public int id(int v) {
		return id[v];
	}

	public int count() {
		return count;
	}

}
