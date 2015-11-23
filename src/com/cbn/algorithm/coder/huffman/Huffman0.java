package com.cbn.algorithm.coder.huffman;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 单词查找树的结点表示
 * @author boning.cbn
 *
 */
public class Huffman0 {
	private final FileInputStream input;
	private final FileOutputStream output;
	
	public Huffman0(FileInputStream input, FileOutputStream output) {
		this.input=input;
		this.output=output;
	}
	
	/**
	 *前缀码的展开，解码 
	 */
	public  void expand(){
		Node root = readTrie();
		int N = input.read()
	}
	private String[] buildCode(Node root){
		//使用单词查找树构造编译表
		String[] st = new String[256];
		buildCode(st,root,"");
		return st;
	}
	private void buildCode(String[] st , Node x, String s){
		//使用单词查找树构造编译表
		if(x.isLeaf()){
			st[x.ch]=s;return;
		}
		buildCode(st,x.left,s+'0');
		buildCode(st, x.right, s+'1');
	}
	private  Node readTrie() {
		// TODO Auto-generated method stub
		return null;
	}

	private static class Node implements Comparable<Node> {
		// 霍夫曼单词查找树中的结点
		private char ch;// 内部结点不会使用该变量
		private int freq;// 展开过程不会使用该变量
		private final Node left, right;

		public Node(char ch, int freq, Node left, Node right) {

			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		@Override
		public int compareTo(Node that) {
			return this.freq - that.freq;
		}

	}
}
