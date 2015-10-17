package com.cbn.leetcode.model;

import java.util.HashMap;

public class LRUCache3 {
	private class Node {
		public int val;
		public Node last = null;
		public Node next = null;

		public Node(int val) {
			this.val = val;
		}
	}

	private class DoubleLinkedList {
		public Node head = null;
		public Node tail = null;

		// 插入节点至尾部
		public void addNode(Node p) {
			if (p == null)
				return;
			if (head == null) {
				this.head = p;
				this.tail = p;
			} else {
				this.tail.next = p;
				p.last = this.tail;
				this.tail = p;
			}
		}

		// 节点p被使用(get/set)，移至链表尾部
		public void moveNodeToTail(Node p) {
			if (this.tail == p)
				return;
			if (this.head == p) {
				this.head = this.head.next;
				head.last = null;
			} else {
				p.last.next = p.next;
				p.next.last = p.last;
			}
			p.next = null;
			p.last = this.tail;
			this.tail.next = p;
			this.tail = p;
		}

		// 移除头结点
		public Node removehead() {
			if (this.head == null)
				return null;
			Node temp = this.head;
			if (this.head == this.tail) {
				this.head = null;
				this.tail = null;
			} else {
				this.head = this.head.next;
				this.head.last = null;
				temp.next = null;
			}
			return temp;
		}
	}

	// 构造器
	public LRUCache3(int capacity) {
	        if(capacity < 1)
	            throw new RuntimeException("!!!!!!!!");
	        this.capacity = capacity;
	        this.keyNodeMap = new HashMap<Integer, Node>();
	        this.nodeKeyMap = new HashMap<Node, Integer>();
	        this.nodeList = new DoubleLinkedList();
	    }

	// get元素
	public int get(int key) {
		if (keyNodeMap.containsKey(key)) {
			Node temp = keyNodeMap.get(key);
			nodeList.moveNodeToTail(temp);
			return temp.val;
		}
		return -1;
	}

	// set元素
	public void set(int key, int value) {
		if (keyNodeMap.containsKey(key)) {
			Node temp = keyNodeMap.get(key);
			temp.val = value;
			nodeList.moveNodeToTail(temp);
		} else {
			Node temp = new Node(value);
			keyNodeMap.put(key, temp);
			nodeKeyMap.put(temp, key);
			nodeList.addNode(temp);
			// 关键点：如果超过capacity，则移除头结点
			if (keyNodeMap.size() == (this.capacity + 1)) {
				Node headTemp = nodeList.removehead();
				int keyTemp = nodeKeyMap.get(headTemp);
				keyNodeMap.remove(keyTemp);
				nodeKeyMap.remove(headTemp);
			}
		}
	}

	// 缓存尺寸
	private int capacity;
	private DoubleLinkedList nodeList = null;
	// key->Node(value)的map
	private HashMap<Integer, Node> keyNodeMap = null;
	// Node(value) -> key的map, 用set方法中获取头结点的key
	private HashMap<Node, Integer> nodeKeyMap = null;
}
