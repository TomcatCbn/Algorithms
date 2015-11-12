package com.cbn.algorithm.datastructure.graph;

/**
 * 加权有向边的数据结构 与Edge的either()和other()方法不同，这里定义了from()和to()方法
 * 
 * @author boning.cbn
 *
 */
public class DirectedEdge {
	private final int v;
	private final int w;
	private final double weight;

	/**
	 * 创建一个含有V个顶点的空有向图
	 * 
	 * @param V
	 */
	public DirectedEdge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	/**
	 * 边的权重
	 * 
	 * @return
	 * 
	 */
	public double weight() {
		return weight;
	}

	/**
	 * 指出这条边的顶点
	 * 
	 * @return
	 */
	public int from() {
		return v;
	}

	/**
	 * 这条边指向的顶点
	 * 
	 * @return
	 */
	public int to() {
		return w;
	}

	@Override
	public String toString() {
		return "DirectedEdge [v=" + v + ", w=" + w + ", weight=" + weight + "]";
	}

}
