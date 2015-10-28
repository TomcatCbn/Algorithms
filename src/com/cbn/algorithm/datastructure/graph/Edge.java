package com.cbn.algorithm.datastructure.graph;

/**
 * 加权边的API
 * 
 * @author boning
 *
 */
public class Edge implements Comparable<Edge>{

	private final int v;
	private final int w;
	private final double weight;

	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	// 边的权重
	public double weight() {
		return weight;
	}

	// 边两端的顶点之一
	public int either() {
		return v;
	}

	// 另一个顶点
	public int other(int v) {
		if (this.v == v)
			return w;
		if (this.w == v)
			return v;
		throw new RuntimeException("Inconsistent edge");
	}

	// 将这条边与that比较
	public int compareTo(Edge that) {
		if (this.weight < that.weight)
			return -1;
		else if (this.weight > that.weight)
			return 1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return String.format("%d-%d %.2f", v, w, weight);
	}

}
