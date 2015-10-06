package com.cbn.algorithm.test;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import com.cbn.algorithm.UnionFind;

public class UnionFindTest {
	@Test
	public void testUF() {
		UnionFind uf = new UnionFind(10);
		try {
			uf.input(new File("tinyUF.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(true, uf.isConnected(1, 0));
		Assert.assertEquals(false, uf.isConnected(2, 3));
	}
}
