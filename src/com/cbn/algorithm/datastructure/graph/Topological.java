package com.cbn.algorithm.datastructure.graph;

/**
 * 拓扑排序的API
 * 
 * 一幅有向无环图的拓扑顺序即为所有顶点的逆后序排列
 * @author boning
 *
 */
public class Topological {

	private Iterable<Integer> order;// 顶点的拓扑排序

	public Topological(Digraph G) {
		DirectedCycle cycleFinder = new DirectedCycle(G);
		if (!cycleFinder.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
		}
	}

	// G是有向无环图吗
	public boolean isDAG() {
		return order != null;
	}

	// 拓扑有序的所有顶点
	public Iterable<Integer> order() {
		return order;
	}
}
