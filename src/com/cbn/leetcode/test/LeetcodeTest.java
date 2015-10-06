package com.cbn.leetcode.test;

import org.junit.Before;
import org.junit.Test;
import com.cbn.leetcode.Solution;
import junit.framework.Assert;

/**
 * 
 * @author boning
 *
 */
public class LeetcodeTest {
	private static Solution solution;

	@Before
	public void init() {
		solution = new Solution();
	}

	@Test
	public void testWordPattern() {
		String pattern = "abba";
		String str = "dog dog dog dog";
		Assert.assertEquals(false, solution.wordPattern(pattern, str));
	}

	@Test
	public void testMoveZeroes() {
		int[] nums = new int[] { 0, 1, 0, 3, 12 };
		long l1 = System.currentTimeMillis();
		solution.moveZeroes(nums);
		long l2 = System.currentTimeMillis();
		System.out.println(l2 - l1);
		for (int i : nums) {
			System.out.println(i);
		}
	}
}
