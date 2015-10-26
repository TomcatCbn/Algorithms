package com.cbn.algorithm.datastructure;

/**
 * 当我们提到一个结点的颜色时，我们指的是指向该结点的连接的颜色，反之亦然。
 * 
 * 
 * @author boning
 *
 * @param <Key>
 * @param <Val>
 */
public class RedBlackTree<Key extends Comparable<Key>, Val> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root;

	public void put(Key key, Val val) {
		root = put(root, key, val);
		root.color = BLACK;
	}
	//红黑树的插入实现
	private Node put(Node h, Key key, Val val) {
		if (h == null) // 标准的插入操作，和父结点用红链接相连
			return new Node(key, val, 1, RED);
		int cmp = key.compareTo(h.key);
		if (cmp < 0)
			h.left = put(h.left, key, val);
		else if (cmp > 0)
			h.right = put(h.right, key, val);
		else
			h.val = val;

		if (isRed(h.right) && !isRed(h.left)) // 左黑右红
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) // 左红并且左子结点也是红
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right)) // 左右都为红
			flipColors(h);

		// 更新h结点的size
		h.N = 1 + size(h.left) + size(h.right);
		return h;
	}

	// 左旋转
	Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}

	// 右旋转
	Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}

	// 将自结点的两个红色子结点的颜色由红变黑，同时将父结点的颜色由黑变红
	void flipColors(Node h) {
		h.left.color = BLACK;
		h.right.color = BLACK;
		h.color = RED;
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		return x.N;
	}

	private boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.color;
	}

	private class Node {
		Key key;
		Val val;
		Node left, right;
		int N;// 这颗子树中的节点总数
		boolean color;// 由其父节点指向它的连接的颜色

		Node(Key key, Val val, int N, boolean color) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
}
