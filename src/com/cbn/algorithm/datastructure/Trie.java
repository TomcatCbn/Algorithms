package com.cbn.algorithm.datastructure;

import java.util.HashSet;

/**
 * 字典树
 * 
 * @author boning
 *
 */
public class Trie {
	private TrieNode trieNode = new TrieNode();

	public void addTrieNode(String word, int id) {
		addTrieNode(trieNode, word, id);
	}

	/**
	 * 插入操作
	 * 
	 * @param root
	 * @param word
	 * @param id
	 */
	private void addTrieNode(TrieNode root, String word, int id) {
		int length = word.length();
		if (length == 0)
			return;
		char[] words = word.toLowerCase().toCharArray();

		for (int i = 0; i < length; i++) {
			// 求字符地址，方便将该字符放入到26叉树中的哪一叉树中
			int k = words[i] - 'a';

			// 如果该叉树为空，则初始化
			if (root.childNodes[k] == null) {
				root.childNodes[k] = new TrieNode();

				// 记录下字符
				root.childNodes[k].nodeChar = words[i];
			}

			// 该id途径的节点
			root.childNodes[k].hashSet.add(id);
			if (i == length - 1) {
				root.childNodes[k].freq++;
			}
			root = root.childNodes[k];
		}

	}

	/**
	 * 检索单词的前缀，返回改前缀的Hash集合
	 * 
	 * @param s
	 * @return
	 */
	public HashSet<Integer> searchTrie(String s) {
		HashSet<Integer> hashset = new HashSet<Integer>();
		return searchTrie(trieNode, s, hashset);
	}

	private HashSet<Integer> searchTrie(TrieNode root, String word, HashSet<Integer> hashset) {
		int length = word.length();
		if (length == 0)
			return hashset;
		char[] words = word.toLowerCase().toCharArray();
		for (int i = 0; i < length; i++) {
			int k = words[i] - 'a';
			if (i == length - 1)
				// 采用动态规划的思想，word最后节点记录这个途径的id
				hashset = root.childNodes[k].hashSet;
			root = root.childNodes[k];
		}
		return hashset;
	}

	/**
	 * 词频统计
	 * 
	 * @param word
	 * @return
	 */
	public int wordCount(String word) {
		return wordCount(trieNode, word);
	}

	private int wordCount(TrieNode root, String word) {
		int length = word.length();
		if (length == 0)
			return 0;
		char[] words = word.toLowerCase().toCharArray();
		for (int i = 0; i < length; i++) {
			int k = words[i] - 'a';
			if (i == length - 1)
				return root.childNodes[k].freq;
			root = root.childNodes[k];
		}
		return 0;
	}

	/**
	 * 更新字典树
	 * 
	 * @param newWord
	 * @param oldWord
	 * @param id
	 */
	public void updateTrieNode(String newWord, String oldWord, int id) {
		updateTrieNode(trieNode, newWord, oldWord, id);
	}

	private void updateTrieNode(TrieNode root, String newWord, String oldWord, int id) {
		// 先删除
		deleteTrieNode(oldWord, id);

		// 再添加
		addTrieNode(newWord, id);
	}

	/**
	 * 删除
	 * 
	 * @param oldWord
	 * @param id
	 */
	public void deleteTrieNode(String oldWord, int id) {
		deleteTrieNode(trieNode, oldWord, id);
	}

	private void deleteTrieNode(TrieNode root, String oldWord, int id) {
		int length = oldWord.length();
		if (length == 0)
			return;
		char[] words = oldWord.toLowerCase().toCharArray();
		for (int i = 0; i < length; i++) {
			int k = words[i] - 'a';
			// 如果最后一个单词，则减去词频
			if (i == length - 1 && root.childNodes[k].freq > 0) {
				root.childNodes[k].freq--;
			}
			root.childNodes[k].hashSet.remove(id);
			root = root.childNodes[k];
		}
	}

	/**
	 * Trie树节点
	 * 
	 * @author boning
	 *
	 */
	static class TrieNode {
		// 26个字符，也就是26叉树
		public TrieNode[] childNodes;

		// 词频统计
		public int freq;

		// 记录该节点的字符
		public char nodeChar;

		// 插入记录时的编码ID
		public HashSet<Integer> hashSet = new HashSet<Integer>();

		// 构造器
		public TrieNode() {
			childNodes = new TrieNode[26];
			freq = 0;
		}
	}

}
