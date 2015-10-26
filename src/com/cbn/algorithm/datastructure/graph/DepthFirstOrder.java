package com.cbn.algorithm.datastructure.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.DelayQueue;

/**
 * 有向图基于深度优先搜索的顶点排序
 * 前序：就是dfs()调用的顺序
 * 后序：就是顶点遍历完成的顺序
 * @author boning
 *
 */
public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre;// 所有顶点的前序排列
	private Queue<Integer> post;// 所有顶点的后序排列
	private Stack<Integer> reversePost;// 所有顶点的逆后序排列

	public DepthFirstOrder(Digraph G) {
		pre = new ArrayDeque<Integer>();
		post = new ArrayDeque<Integer>();
		reversePost = new Stack<Integer>();

		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v])
				dfs(G, v);
		}
	}

	private void dfs(Digraph g, int v) {
		pre.add(v);// 前序排列

		marked[v] = true;
		for (int w : g.adj(v)) {
			if (!marked[w])
				dfs(g, w);
		}
		post.add(v);// 后序排列
		reversePost.push(v);// 逆后序排列
	}

	public Iterable<Integer> pre() {
		return pre;
	}

	public Iterable<Integer> post() {
		return post;
	}

	public Iterable<Integer> reversePost() {
		return reversePost;
	}
}
