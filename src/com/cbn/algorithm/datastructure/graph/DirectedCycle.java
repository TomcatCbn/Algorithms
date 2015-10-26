package com.cbn.algorithm.datastructure.graph;

import java.util.Stack;
/**
 * 寻找有向环
 * @author boning
 *
 */
public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;// 有向环中的所有顶点
	private boolean[] onStack;// 递归调用的栈上所有的顶点

	public DirectedCycle(Digraph G) {
		onStack = new boolean[G.V()];
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v])
				dfs(G, v);
		}
	}

	private void dfs(Digraph g, int v) {
		onStack[v] = true;
		marked[v] = true;
		for (int w : g.adj(v)) {
			if (this.hasCycle())
				return;
			else if (!marked[w])
				dfs(g, w);
			else if (onStack[w]) {
				cycle = new Stack<Integer>();
				for (int x = v; v != w; x = edgeTo[x])
					cycle.push(x);
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}

	public boolean hasCycle() {
		return null != cycle;
	}

	public Iterable<Integer> cycle() {
		return cycle;
	}
}
