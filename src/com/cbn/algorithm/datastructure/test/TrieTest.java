package com.cbn.algorithm.datastructure.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;

import com.cbn.algorithm.datastructure.Trie;

public class TrieTest {
	@Test
	public void testTrie() throws FileNotFoundException {
		Trie trie = new Trie();
		File file = new File("wordscount.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String temp;
		long time1 = System.currentTimeMillis();
		try {
			while ((temp = reader.readLine()) != null) {
				String[] ss = temp.split(" ");
				trie.addTrieNode(ss[1], Integer.valueOf(ss[0]));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long time2 = System.currentTimeMillis();
		System.out.println(time2 - time1);
		// 检索以go开头的字符
		HashSet<Integer> set = trie.searchTrie("go");
		for (int i : set) {
			System.out.println("当前字符串id为：" + i);
		}
		System.out.println("字符\"go\"出现的次数为 " + trie.wordCount("go"));
		System.out.println(System.currentTimeMillis() - time2);

	}

}
