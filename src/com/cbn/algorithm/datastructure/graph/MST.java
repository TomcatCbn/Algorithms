package com.cbn.algorithm.datastructure.graph;

/**
 * 最小生成树
 * 
 * 顶点：使用一个由顶点索引的布尔数组marked[]，如果顶点v在树中，那么marked[v]的值为true 边：选择以下两种数据之一：
 * 1.一条队列mst来保存最小生成树中的边 2.一个由顶点索引的Edge对象的数组edgeTo[]，其中edgeTo[v]为将v连接到树中的Edge对象
 * 横切边：使用一条优先队列MinPQ<Edge>来根据权重比较所有边
 * 
 * @author boning
 */
public interface MST {
	// 构造函数
	// public MST(EdgeWeightedGraph G) {
	//
	// }

	// 最小生成树的所有边
	public abstract Iterable<Edge> edges();

	// 最小生成树的权重
	public abstract double weight();
}
