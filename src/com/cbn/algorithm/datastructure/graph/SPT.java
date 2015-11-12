package com.cbn.algorithm.datastructure.graph;

/**
 * @author boning.cbn
 * 单点最短路径问题，给出起点s，计算的结果是一颗最短路径树SPT，
 * 它包含了顶点s到所有可达的顶点的最短路径。
 *
 */
public interface SPT {
/**
 * 从顶点s到v的距离，如果不存在，则路径为无穷大	
 * @param v
 * @return
 */
double distTo(int v);
/**
 * 是否存在从顶点s到v的路径
 * @param v
 * @return
 */
boolean hasPathTo(int v);
/**
 * 从顶点s到v的路径，如果不存在则为null；
 * @param v
 * @return
 */
Iterable<DirectedEdge> pathTo(int v);
}
