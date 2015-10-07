package com.cbn.leetcode;

import java.util.HashMap;
import java.util.HashSet;

import com.cbn.leetcode.model.ListNode;
import com.cbn.leetcode.model.TreeNode;

/**
 * Leetcode解答
 * 
 * @author boning
 *
 */
public class Solution {
	/**
	 * #290 Given a pattern and a string str, find if str follows the same
	 * pattern.
	 * 
	 * @param pattern
	 * @param str
	 * @return
	 */
	public boolean wordPattern(String pattern, String str) {
		String[] strs = str.split(" ");
		int length = pattern.length();
		if (length != strs.length)
			return false;
		HashMap<Character, String> map = new HashMap<Character, String>();
		HashSet<String> map2 = new HashSet<String>();
		for (int i = 0; i < length; i++) {
			char temp = pattern.charAt(i);
			String tempstr = strs[i];
			if (map.containsKey(temp)) {
				if (!map.get(temp).equals(tempstr)) {
					return false;
				}
			} else {
				map.put(temp, tempstr);
				if (map2.contains(tempstr))
					return false;
				else
					map2.add(tempstr);
			}
		}
		return true;
	}

	/**
	 * #283 Given an array nums, write a function to move all 0's to the end of
	 * it while maintaining the relative order of the non-zero elements. For
	 * example, given nums = [0, 1, 0, 3, 12], after calling your function, nums
	 * should be [1, 3, 12, 0, 0].
	 * 
	 * @param nums
	 */
	public void moveZeroes(int[] nums) {
		int numsOfZero = 0;
		int length = nums.length;
		if (length == 1)
			return;
		for (int i = 0; i < length; i++) {
			if (nums[i] == 0)
				numsOfZero++;
			else {
				if (numsOfZero != 0) {
					nums[i - numsOfZero] = nums[i];
					nums[i] = 0;
				}
			}
		}
		// for(int i=length-numsOfZero;i<length;i++){
		// nums[i]=0;
		// }
	}

	/**
	 * #278 Suppose you have n versions [1, 2, ..., n] and you want to find out
	 * the first bad one, which causes all the following ones to be bad.
	 * 
	 * @param n
	 * @return
	 */
	public int firstBadVersion(int n) {
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (isBadVersion(mid)) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		if (isBadVersion(lo))
			return lo;
		else
			return lo + 1;
	}

	private boolean isBadVersion(int mid) {
		return false;
	}

	/**
	 * #258 Given a non-negative integer num, repeatedly add all its digits
	 * until the result has only one digit.
	 * 
	 * @param num
	 * @return
	 */
	public int addDigits(int num) {
		return (num < 10) ? num : ((num - 10) % 9) + 1;
	}

	/**
	 * #104 Given a binary tree, find its maximum depth.
	 * 
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root) {
		return (root == null) ? 0 : Math.max(1 + maxDepth(root.left), 1 + maxDepth(root.right));
	}

	/**
	 * #237 Write a function to delete a node (except the tail) in a singly
	 * linked list, given only access to that node. Supposed the linked list is
	 * 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the
	 * linked list should become 1 -> 2 -> 4 after calling your function.
	 * 
	 * @param node
	 */
	public void deleteNode(ListNode node) {
		if (node == null)
			return;
		if (node.next != null) {
			node.val = node.next.val;
			node.next = node.next.next;
		}
	}

	/**
	 * #100 Given two binary trees, write a function to check if they are equal
	 * or not. Two binary trees are considered equal if they are structurally
	 * identical and the nodes have the same value.
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		if (p == null || q == null)
			return false;
		if (p.val == q.val) {
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		} else
			return false;
	}

	/**
	 * #191 Write a function that takes an unsigned integer and returns the
	 * number of '1' bits it has (also known as the Hamming weight). For
	 * example, the 32-bit integer ’11' has binary representation
	 * 00000000000000000000000000001011, so the function should return 3.
	 * 
	 * 
	 * @param n
	 * @return
	 */
	public int hammingWeight(int n) {
		int res = 0;
		while (n != 0) {
			res = (n ^ (n - 1)) == 1 ? res + 1 : res;
			// res = (n & 1) == 1 ? res + 1 : res;
			n = n >>> 1;
		}
		return res;
	}

	/**
	 * #217 Given an array of integers, find if the array contains any
	 * duplicates. Your function should return true if any value appears at
	 * least twice in the array, and it should return false if every element is
	 * distinct.
	 * 
	 * @param nums
	 * @return
	 */
	public boolean containsDuplicate(int[] nums) {
		int length = nums.length;
		if (length <= 1)
			return false;
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < length; i++) {
			set.add(nums[i]);
			if (set.size() != (i + 1))
				return true;
		}
		return false;
	}

	/**
	 * Given a non-negative number represented as an array of digits, plus one
	 * to the number. The digits are stored such that the most significant digit
	 * is at the head of the list.
	 * 
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {
		int n = digits.length;
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}

			digits[i] = 0;
		}

		int[] newNumber = new int[n + 1];
		newNumber[0] = 1;

		return newNumber;
	}
}
