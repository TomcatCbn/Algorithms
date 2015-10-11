package com.cbn.algorithm.datastructure;

/**
 * 一种有序的泛型符号表操作API
 * 
 * @author boning
 *
 * @param <K>
 * @param <V>
 */
public interface ST<K extends Comparable<K>, V> {
	/**
	 * 将键值对存入表中（若值为空则将Key从表中删除）
	 * 
	 * @param key
	 * @param value
	 */
	void put(K key, V value);

	/**
	 * 获取键key对应的值（若键不存在则返回null）
	 * 
	 * @param key
	 * @return
	 */
	V get(K key);

	/**
	 * 从表中删除key及其对应的值
	 * 
	 * @param key
	 */
	void delete(K key);

	/**
	 * 键key是否存在于表中
	 * 
	 * @param key
	 * @return
	 */
	boolean contains(K key);

	/**
	 * 表是否为空
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 返回表中的键值对数量
	 * 
	 * @return
	 */
	int size();

	/**
	 * 最小的键
	 * 
	 * @return
	 */
	K min();

	/**
	 * 最大的键
	 * 
	 * @return
	 */
	K max();

	/**
	 * 小于等于key的最大键
	 * 
	 * @param key
	 * @return
	 */
	K floor(K key);

	/**
	 * 大于等于key的最小键
	 * 
	 * @param key
	 * @return
	 */
	K ceiling(K key);

	/**
	 * 小于key的键的数量
	 * 
	 * @param key
	 * @return
	 */
	int rank(K key);

	/**
	 * 排名为k的键
	 * 
	 * @param k
	 * @return
	 */
	K select(int k);

	/**
	 * 删除最小的键
	 */
	void deleteMin();

	/**
	 * 删除最大的键
	 */
	void deleteMax();

	/**
	 * [lo..hi]之间键的数量
	 * 
	 * @param lo
	 * @param hi
	 * @return
	 */
	int size(K lo, K hi);

	/**
	 * [lo..hi]之间的所有键，已排序
	 * 
	 * @param lo
	 * @param hi
	 * @return
	 */
	Iterable<K> keys(K lo, K hi);

	/**
	 * 表中的所有键的集合，已排序
	 * 
	 * @return
	 */
	Iterable<K> keys();
}
