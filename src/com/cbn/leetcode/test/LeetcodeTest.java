package com.cbn.leetcode.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cbn.leetcode.Solution;
import com.cbn.leetcode.model.ListNode;
import com.cbn.leetcode.model.Point;

/**
 * 
 * @author boning
 *
 */
public class LeetcodeTest {
	private static Solution solution;
	public static void main(String[] args) {
		System.out.println(1<<0);
	}
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

	@Test
	public void testDeleteNode() {
		ListNode head = new ListNode(1);
		ListNode temp = head;
		temp.next = new ListNode(2);
		temp = temp.next;
		temp.next = new ListNode(3);
		temp = temp.next;
		temp.next = new ListNode(4);
		solution.deleteNode(temp);
		temp = head;
		while (temp != null) {
			System.out.print(temp.toString());
			temp = temp.next;
		}
	}

	@Test
	public void testHammingWeight() {
		int n = 11;
		Assert.assertEquals(3, solution.hammingWeight(n));

	}

	@Test
	public void testIsAnagram() {
		String s = "a";
		String t = "b";
		Assert.assertEquals(false, solution.isAnagram(s, t));
	}

	@Test
	public void testDeleteDuplicates() {
		ListNode head = new ListNode(1);
		ListNode temp = head;
		temp.next = new ListNode(1);
		temp = temp.next;
		temp.next = new ListNode(2);
		temp = temp.next;
		temp.next = new ListNode(3);
		temp = temp.next;
		temp.next = new ListNode(3);
		solution.deleteDuplicates(head);
	}

	@Test
	public void testReverseList() {
		ListNode head = new ListNode(1);
		ListNode temp = head;
		temp.next = new ListNode(2);
		temp = temp.next;
		temp.next = new ListNode(3);
		temp = temp.next;
		temp.next = new ListNode(4);
		temp = temp.next;
		temp.next = new ListNode(5);
		head = solution.reverseList(head);
	}
	
	@Test
	public void testRemoveDuplicates(){
		System.out.println(solution.removeDuplicates(new int[]{1,1,1}));
	}
	
	@Test
	public void testmissingNumber(){
		System.out.println(solution.missingNumber(new int[]{0}));
	}
	
	@Test
	public void testmaxPoints(){
		Point[] points=new Point[3];
		points[0]=new Point(0,0);
		points[1]=new Point(-1,-1);
		points[2]=new Point(2,2);
		System.out.println(solution.maxPoints(points));
	}
}
